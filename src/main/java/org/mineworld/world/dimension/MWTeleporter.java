package org.mineworld.world.dimension;

import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.block.MWPortalBlock;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

/**
 * {@link MineWorld MineWorld} {@link ITeleporter Teleporter}
 */
public abstract class MWTeleporter implements ITeleporter {

    /**
     * {@link ServerLevel The level reference}
     */
    protected final ServerLevel level;
    /**
     * {@link BlockState The portal frame Block State}
     */
    private final BlockState PORTAL_FRAME;

    /**
     * Constructor. Set the {@link ITeleporter Teleporter} properties
     *
     * @param level {@link ServerLevel The level reference}
     * @param portalFrame {@link BlockState The portal frame Block State}
     */
    public MWTeleporter(final ServerLevel level, final BlockState portalFrame) {
        this.level = level;
        this.PORTAL_FRAME = portalFrame;
    }

    /**
     * Get the {@link ResourceKey<Level> dimension key} this portal is referring to
     *
     * @return {@link ResourceKey<Level> The dimension key}
     */
    public abstract ResourceKey<Level> getDimension();

    /**
     * Get the {@link ResourceKey<PoiType> POI Type key} this portal is referring to
     *
     * @return {@link ResourceKey<PoiType> The POI Type key}
     */
    public abstract ResourceKey<PoiType> getPoiType();

    /**
     * Get the {@link BlockState portal frame block state}
     *
     * @return {@link BlockState The portal frame block state}
     */
    public abstract BlockState getPortalFrameState();

    /**
     * Check if there is already a portal near the {@link BlockPos current location}
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link BlockUtil.FoundRectangle The existing portal}
     */
    public Optional<BlockUtil.FoundRectangle> getExistingPortal(final BlockPos blockPos) {
        final PoiManager poiManager = this.level.getPoiManager();
        poiManager.ensureLoadedAndValid(this.level, blockPos, 64);
        return poiManager.getInSquare(poiType ->
                        poiType.is(getPoiType()), blockPos, 64, PoiManager.Occupancy.ANY).sorted(Comparator.<PoiRecord>comparingDouble(poi ->
                        poi.getPos().distSqr(blockPos)).thenComparingInt(poi ->
                        poi.getPos().getY())).filter(poi ->
                        this.level.getBlockState(poi.getPos()).hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
                .findFirst()
                .map(poi -> {
                    final BlockPos poiPos = poi.getPos();
                    this.level.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(poiPos), 3, poiPos);
                    final BlockState poiState = this.level.getBlockState(poiPos);
                    return BlockUtil.getLargestRectangleAround(poiPos, poiState.getValue(BlockStateProperties.HORIZONTAL_AXIS), 21, Direction.Axis.Y, 21, pos -> this.level.getBlockState(pos).equals(poiState));
                });
    }

    /**
     * Check if the portal can replace a {@link Block Block}
     *
     * @param blockPos {@link BlockPos.MutableBlockPos The current Block Pos}
     * @return {@link Boolean True if the portal can replace a Block}
     */
    private boolean canPortalReplaceBlock(final BlockPos.MutableBlockPos blockPos) {
        final BlockState blockState = this.level.getBlockState(blockPos);
        return blockState.canBeReplaced() && blockState.getFluidState().isEmpty();
    }

    /**
     * Check if a {@link Block Block} can sustain a portal frame
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param framePos {@link BlockPos.MutableBlockPos The frame Block Pos}
     * @param direction {@link Direction The current Direction}
     * @param offsetScale {@link Integer The offset scale}
     * @return {@link Boolean True if the Block can sustain a portal frame}
     */
    private boolean canHostFrame(final BlockPos blockPos, final BlockPos.MutableBlockPos framePos, final Direction direction, final int offsetScale) {
        final Direction clockwiseDirection = direction.getClockWise();
        for(int i = -1; i < 3; ++i) {
            for(int j = -1; j < 4; ++j) {
                framePos.setWithOffset(blockPos, direction.getStepX() * i + clockwiseDirection.getStepX() * offsetScale, j, direction.getStepZ() * i + clockwiseDirection.getStepZ() * offsetScale);
                if (j < 0 && !this.level.getBlockState(framePos).isSolid()) {
                    return false;
                }
                if (j >= 0 && !this.canPortalReplaceBlock(framePos)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Try to make a portal
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param axis {@link Direction.Axis The portal axis}
     * @return {@link BlockUtil.FoundRectangle The created portal}
     */
    public Optional<BlockUtil.FoundRectangle> makePortal(final BlockPos blockPos, final Direction.Axis axis) {
        final Direction positiveDirection = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        double d0 = -1.0D;
        BlockPos blockpos = null;
        double d1 = -1.0D;
        BlockPos blockpos1 = null;
        final WorldBorder worldBorder = this.level.getWorldBorder();
        final int height = Math.min(this.level.getMaxBuildHeight(), this.level.getMinBuildHeight() + this.level.getLogicalHeight()) - 1;
        final BlockPos.MutableBlockPos mutablePos = blockPos.mutable();

        BlockPos.spiralAround(blockPos, 16, Direction.EAST, Direction.SOUTH).forEach(checkPos -> { });
        for(BlockPos.MutableBlockPos checkPos : BlockPos.spiralAround(blockPos, 16, Direction.EAST, Direction.SOUTH)) {
            final int validStartHeight = Math.min(height, this.level.getHeight(Heightmap.Types.MOTION_BLOCKING, checkPos.getX(), checkPos.getZ()));
            if (worldBorder.isWithinBounds(checkPos) && worldBorder.isWithinBounds(checkPos.move(positiveDirection, 1))) {
                checkPos.move(positiveDirection.getOpposite(), 1);
                for(int startHeight = validStartHeight; startHeight >= this.level.getMinBuildHeight(); --startHeight) {
                    checkPos.setY(startHeight);
                    if (this.canPortalReplaceBlock(checkPos)) {
                        int i1 = startHeight;
                        while (startHeight > this.level.getMinBuildHeight() && this.canPortalReplaceBlock(checkPos.move(Direction.DOWN))) {
                            --startHeight;
                        }

                        if (startHeight + 4 <= height) {
                            int j1 = i1 - startHeight;
                            if (j1 <= 0 || j1 >= 3) {
                                checkPos.setY(startHeight);
                                if (this.canHostFrame(checkPos, mutablePos, positiveDirection, 0)) {
                                    final double distance = blockPos.distSqr(checkPos);
                                    if (this.canHostFrame(checkPos, mutablePos, positiveDirection, -1) && this.canHostFrame(checkPos, mutablePos, positiveDirection, 1) && (d0 == -1.0D || d0 > distance)) {
                                        d0 = distance;
                                        blockpos = checkPos.immutable();
                                    }

                                    if (d0 == -1.0D && (d1 == -1.0D || d1 > distance)) {
                                        d1 = distance;
                                        blockpos1 = checkPos.immutable();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (d0 == -1.0D && d1 != -1.0D) {
            blockpos = blockpos1;
            d0 = d1;
        }

        if (d0 == -1.0D) {
            int maxHeight = Math.max(this.level.getMinBuildHeight() + 1, 70);
            int i2 = height - 9;
            if (i2 < maxHeight) {
                return Optional.empty();
            }

            blockpos = (new BlockPos(blockPos.getX(), Mth.clamp(blockPos.getY(), maxHeight, i2), blockPos.getZ())).immutable();
            Direction direction1 = positiveDirection.getClockWise();
            if (!worldBorder.isWithinBounds(blockpos)) {
                return Optional.empty();
            }

            for(int x = -1; x < 2; ++x) {
                for(int y = 0; y < 2; ++y) {
                    for(int z = -1; z < 3; ++z) {
                        final BlockState portalState = z < 0 ? this.PORTAL_FRAME : Blocks.AIR.defaultBlockState();
                        mutablePos.setWithOffset(blockpos, y * positiveDirection.getStepX() + x * direction1.getStepX(), z, y * positiveDirection.getStepZ() + x * direction1.getStepZ());
                        this.level.setBlockAndUpdate(mutablePos, portalState);
                    }
                }
            }
        }

        for(int xZ = -1; xZ < 3; ++xZ) {
            for(int y = -1; y < 4; ++y) {
                if (xZ == -1 || xZ == 2 || y == -1 || y == 3) {
                    mutablePos.setWithOffset(blockpos, xZ * positiveDirection.getStepX(), y, xZ * positiveDirection.getStepZ());
                    this.level.setBlock(mutablePos, this.PORTAL_FRAME, 3);
                }
            }
        }

        final BlockState portalState = getPortalFrameState().setValue(MWPortalBlock.AXIS, axis);

        for(int xZ = 0; xZ < 2; ++xZ) {
            for(int y = 0; y < 3; ++y) {
                mutablePos.setWithOffset(blockpos, xZ * positiveDirection.getStepX(), y, xZ * positiveDirection.getStepZ());
                this.level.setBlock(mutablePos, portalState, 18);
            }
        }

        return Optional.of(new BlockUtil.FoundRectangle(blockpos.immutable(), 2, 3));
    }

    /**
     * Get the {@link PortalInfo portal information}
     *
     * @param entity {@link Entity The entity that is teleporting}
     * @param level {@link ServerLevel The level reference}
     * @param defaultPortalInfo {@link PortalInfo The vanilla portal information method reference}
     * @return {@link PortalInfo The portal information}
     */
    @Nullable
    @Override
    public PortalInfo getPortalInfo(final Entity entity, final ServerLevel level, final Function<ServerLevel, PortalInfo> defaultPortalInfo) {
        final Level entityLevel = entity.level();
        final ResourceKey<Level> dimension = getDimension();
        if (!entityLevel.dimension().equals(dimension) && !level.dimension().equals(getDimension())) {
            return null;
        }
        final WorldBorder border = level.getWorldBorder();
        final double coordinateDifference = DimensionType.getTeleportationScale(entityLevel.dimensionType(), level.dimensionType());
        final BlockPos portalPos = border.clampToBounds(entity.getX() * coordinateDifference, entity.getY(), entity.getZ() * coordinateDifference);
        return this.getOrMakePortal(entity, portalPos).map(result -> {
            final BlockState portalState = entity.level().getBlockState(entity.portalEntrancePos);
            Direction.Axis axis;
            Vec3 portalPosition;
            if (portalState.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
                axis = portalState.getValue(BlockStateProperties.HORIZONTAL_AXIS);
                final BlockUtil.FoundRectangle rectangle = BlockUtil.getLargestRectangleAround(entity.portalEntrancePos, axis, 21, Direction.Axis.Y, 21, blockPos -> entity.level().getBlockState(blockPos).equals(portalState));
                portalPosition = PortalShape.getRelativePosition(rectangle, axis, entity.position(), entity.getDimensions(entity.getPose()));
            } else {
                axis = Direction.Axis.X;
                portalPosition = new Vec3(0.5D, 0.0D, 0.0D);
            }

            return PortalShape.createPortalInfo(level, result, axis, portalPosition, entity, entity.getDeltaMovement(), entity.getYRot(), entity.getXRot());
        }).orElse(null);
    }

    /**
     * Get an existing portal or make a new one if it doesn't exist
     *
     * @param entity {@link Entity The entity that is teleporting}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link BlockUtil.FoundRectangle The portal}
     */
    protected Optional<BlockUtil.FoundRectangle> getOrMakePortal(final Entity entity, final BlockPos blockPos) {
        final Optional<BlockUtil.FoundRectangle> existingPortal = this.getExistingPortal(blockPos);
        if (existingPortal.isPresent()) {
            return existingPortal;
        }
        final Direction.Axis portalAxis = this.level.getBlockState(entity.portalEntrancePos).getOptionalValue(MWPortalBlock.AXIS).orElse(Direction.Axis.X);
        return this.makePortal(blockPos, portalAxis);
    }

    /**
     * Check if the {@link SoundEvent portal sound} should be played while teleporting
     *
     * @param player {@link ServerPlayer The player}
     * @param sourceLevel {@link ServerLevel The level the player is teleporting from}
     * @param destinationLevel {@link ServerLevel The level the player is teleporting to}
     * @return {@link Boolean#FALSE False}
     */
    @Override
    public boolean playTeleportSound(final ServerPlayer player, final ServerLevel sourceLevel, final ServerLevel destinationLevel) {
        return false;
    }

}