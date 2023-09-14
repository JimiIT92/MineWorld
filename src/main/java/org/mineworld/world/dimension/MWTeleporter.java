package org.mineworld.world.dimension;

import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
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
import org.mineworld.block.MWPortalBlock;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;

/**
 * Base class for a {@link ITeleporter MineWorld teleporter}
 */
public abstract class MWTeleporter implements ITeleporter {

    /**
     * {@link ServerLevel The level reference}
     */
    protected final ServerLevel level;
    /**
     * {@link BlockState The portal block}
     */
    private final BlockState PORTAL_FRAME;

    /**
     * Constructor. Set the {@link ServerLevel level} reference and
     * the {@link BlockState portal frame block state}
     *
     * @param level {@link ServerLevel The level reference}
     * @param portalFrame {@link BlockState The portal frame block state}
     */
    public MWTeleporter(final ServerLevel level, final BlockState portalFrame) {
        this.level = level;
        this.PORTAL_FRAME = portalFrame;
    }

    /**
     * Get the {@link ResourceKey<Level> dimension key} this teleporter is referring to
     *
     * @return {@link ResourceKey<Level> The dimension key}
     */
    public abstract ResourceKey<Level> getDimension();

    /**
     * Get the {@link ResourceKey<PoiType> PoiType key} this teleporter is referring to
     *
     * @return {@link ResourceKey<PoiType> The PoiType key}
     */
    public abstract ResourceKey<PoiType> getPoiType();

    /**
     * Get the {@link BlockState portal block state}
     *
     * @return {@link BlockState The portal block state}
     */
    public abstract BlockState getPortalState();

    /**
     * Get an existing portal
     *
     * @param pos {@link BlockPos The current block pos}
     * @return {@link BlockUtil.FoundRectangle The existing portal}, if any
     */
    public Optional<BlockUtil.FoundRectangle> getExistingPortal(final BlockPos pos) {
        final PoiManager poiManager = this.level.getPoiManager();
        poiManager.ensureLoadedAndValid(this.level, pos, 64);
        return poiManager.getInSquare(poiType ->
                poiType.is(getPoiType()), pos, 64, PoiManager.Occupancy.ANY).sorted(Comparator.<PoiRecord>comparingDouble(poi ->
                    poi.getPos().distSqr(pos)).thenComparingInt(poi ->
                    poi.getPos().getY())).filter(poi ->
                    this.level.getBlockState(poi.getPos()).hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
                .findFirst()
                .map(poi -> {
                    final BlockPos poiPos = poi.getPos();
                    this.level.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(poiPos), 3, poiPos);
                    final BlockState poiState = this.level.getBlockState(poiPos);
                    return BlockUtil.getLargestRectangleAround(poiPos, poiState.getValue(BlockStateProperties.HORIZONTAL_AXIS), 21, Direction.Axis.Y, 21, blockPos -> this.level.getBlockState(blockPos).equals(poiState));
        });
    }

    /**
     * Check if the portal can replace a block
     *
     * @param portalPos {@link BlockPos.MutableBlockPos The portal block pos}
     * @return {@link Boolean True if the portal can replace a block}
     */
    private boolean canPortalReplaceBlock(final BlockPos.MutableBlockPos portalPos) {
        final BlockState blockState = this.level.getBlockState(portalPos);
        return blockState.canBeReplaced() && blockState.getFluidState().isEmpty();
    }

    /**
     * Check if a block can sustain a portal frame
     *
     * @param originalPos {@link BlockPos The current block pos}
     * @param offsetPos {@link BlockPos.MutableBlockPos The frame block pos}
     * @param direction {@link Direction The current direction}
     * @param offsetScale {@link Integer The offset scale}
     * @return {@link Boolean True if the block can sustain a portal frame}
     */
    private boolean canHostFrame(final BlockPos originalPos, final BlockPos.MutableBlockPos offsetPos, final Direction direction, final int offsetScale) {
        final Direction clockwiseDirection = direction.getClockWise();

        for(int i = -1; i < 3; ++i) {
            for(int j = -1; j < 4; ++j) {
                offsetPos.setWithOffset(originalPos, direction.getStepX() * i + clockwiseDirection.getStepX() * offsetScale, j, direction.getStepZ() * i + clockwiseDirection.getStepZ() * offsetScale);
                if (j < 0 && !this.level.getBlockState(offsetPos).isSolid()) {
                    return false;
                }

                if (j >= 0 && !this.canPortalReplaceBlock(offsetPos)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Try to make a portal
     *
     * @param pos {@link BlockPos The current block pos}
     * @param axis {@link Direction.Axis The portal axis}
     * @return {@link BlockUtil.FoundRectangle The portal}, if any
     */
    public Optional<BlockUtil.FoundRectangle> makePortal(final BlockPos pos, final Direction.Axis axis) {
        final Direction positiveDirection = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        double d0 = -1.0D;
        BlockPos blockpos = null;
        double d1 = -1.0D;
        BlockPos blockpos1 = null;
        final WorldBorder worldBorder = this.level.getWorldBorder();
        final int height = Math.min(this.level.getMaxBuildHeight(), this.level.getMinBuildHeight() + this.level.getLogicalHeight()) - 1;
        final BlockPos.MutableBlockPos mutablePos = pos.mutable();

        BlockPos.spiralAround(pos, 16, Direction.EAST, Direction.SOUTH).forEach(checkPos -> {

        });

        for(BlockPos.MutableBlockPos checkPos : BlockPos.spiralAround(pos, 16, Direction.EAST, Direction.SOUTH)) {
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
                                    final double distance = pos.distSqr(checkPos);
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

            blockpos = (new BlockPos(pos.getX(), Mth.clamp(pos.getY(), maxHeight, i2), pos.getZ())).immutable();
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

        final BlockState portalState = getPortalState().setValue(MWPortalBlock.AXIS, axis);

        for(int xZ = 0; xZ < 2; ++xZ) {
            for(int y = 0; y < 3; ++y) {
                mutablePos.setWithOffset(blockpos, xZ * positiveDirection.getStepX(), y, xZ * positiveDirection.getStepZ());
                this.level.setBlock(mutablePos, portalState, 18);
            }
        }

        return Optional.of(new BlockUtil.FoundRectangle(blockpos.immutable(), 2, 3));
    }

    /**
     * Get the {@link PortalInfo portal info}
     *
     * @param entity {@link Entity The entity that is teleporting}
     * @param level {@link ServerLevel The level the entity is teleporting to}
     * @param defaultPortalInfo {@link PortalInfo A reference to the vanilla method for getting portal info}
     * @return {@link PortalInfo The portal info}
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
     * @param pos {@link BlockPos The current block pos}
     * @return {@link BlockUtil.FoundRectangle The portal}, if any
     */
    protected Optional<BlockUtil.FoundRectangle> getOrMakePortal(final Entity entity, final BlockPos pos) {
        final Optional<BlockUtil.FoundRectangle> existingPortal = this.getExistingPortal(pos);
        if (existingPortal.isPresent()) {
            return existingPortal;
        }
        final Direction.Axis portalAxis = this.level.getBlockState(entity.portalEntrancePos).getOptionalValue(MWPortalBlock.AXIS).orElse(Direction.Axis.X);
        return this.makePortal(pos, portalAxis);
    }

    /**
     * Check if the teleport sound should be played while teleporting
     *
     * @param player {@link ServerPlayer The player}
     * @param sourceWorld {@link ServerLevel The level the player is teleporting from}
     * @param destWorld {@link ServerLevel The level the player is teleporting to}
     * @return {@link Boolean True if the teleport sound should be played}
     */
    @Override
    public boolean playTeleportSound(final ServerPlayer player, final ServerLevel sourceWorld, final ServerLevel destWorld) {
        return false;
    }

}