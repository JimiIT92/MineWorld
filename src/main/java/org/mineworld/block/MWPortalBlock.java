package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.level.BlockEvent;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.world.dimension.MWTeleporter;

import javax.annotation.Nullable;

/**
 * Base class for a {@link MineWorld MineWorld} portal block
 */
public abstract class MWPortalBlock extends NetherPortalBlock {

    /**
     * Constructor. Set the block properties
     */
    public MWPortalBlock() {
        super(PropertyHelper.copyFromBlock(Blocks.NETHER_PORTAL));
    }

    /**
     * Get the {@link ItemStack block item stack}
     *
     * @param level {@link BlockGetter The block getter reference}
     * @param pos {@link BlockPos The current block pos}
     * @param state {@link BlockState The current block state}
     * @return {@link ItemStack#EMPTY An empty item stack}
     */
    @Override
    public @NotNull ItemStack getCloneItemStack(final @NotNull BlockGetter level, final @NotNull BlockPos pos, final @NotNull BlockState state) {
        return ItemStack.EMPTY;
    }

    /**
     * Try to spawn a portal
     *
     * @param level {@link LevelAccessor The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @return {@link Boolean True if a portal has been spawned}
     */
    public boolean trySpawnPortal(final LevelAccessor level, final BlockPos pos) {
        final MWPortalShape size = this.isPortal(level, pos);
        if (size != null && !onTrySpawnPortal(level, pos, size)) {
            size.createPortalBlocks();
            return true;
        }
        return false;
    }

    /**
     * Fire an event before trying to spawn a portal
     *
     * @param level {@link LevelAccessor The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param shape {@link MWPortalShape The portal shape}
     * @return {@link Boolean True if the event has not been canceled}
     */
    public static boolean onTrySpawnPortal(final LevelAccessor level, final BlockPos pos, final MWPortalShape shape) {
        return MinecraftForge.EVENT_BUS.post(new BlockEvent.PortalSpawnEvent(level, pos, level.getBlockState(pos), shape));
    }

    /**
     * Check if there is a {@link MWPortalShape portal shape} at the current location
     *
     * @param level {@link LevelAccessor The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @return {@link MWPortalShape The portal shape}
     */
    @Nullable
    public MWPortalShape isPortal(final LevelAccessor level, final BlockPos pos) {
        final TagKey<Block> portalBlocks = getPortalBlocks();
        final BlockState defaultPortalFrameState = getPortalFrameState();
        final MWPortalShape shape = new MWPortalShape(level, pos, Direction.Axis.X, portalBlocks, defaultPortalFrameState);
        if (shape.isValid() && shape.numPortalBlocks == 0) {
            return shape;
        }
        final MWPortalShape zAxisShape = new MWPortalShape(level, pos, Direction.Axis.Z, portalBlocks, defaultPortalFrameState);
        return zAxisShape.isValid() && zAxisShape.numPortalBlocks == 0 ? zAxisShape : null;
    }

    /**
     * Get the {@link TagKey<Block> portal block tags}
     *
     * @return {@link TagKey<Block> The portal block tags}
     */
    public abstract TagKey<Block> getPortalBlocks();

    /**
     * Get the {@link BlockState portal frame block state}
     *
     * @return {@link BlockState The portal frame block state}
     */
    public abstract BlockState getPortalFrameState();

    /**
     * Get the {@link ResourceKey<Level> dimension key} this portal is referring to
     *
     * @return {@link ResourceKey<Level> The dimension key}
     */
    public abstract ResourceKey<Level> getDimension();

    /**
     * Get the {@link MWTeleporter dimension teleporter}
     *
     * @param level {@link ServerLevel The level reference}
     * @return {@link MWTeleporter The dimension teleporter}
     */
    public abstract MWTeleporter getTeleporter(final ServerLevel level);

    /**
     * Update the {@link BlockState block state} based on neighbor updates
     *
     * @param state {@link BlockState The current block state}
     * @param direction {@link Direction The direction the update is coming from}
     * @param neighborState {@link BlockState The neighbor block state}
     * @param level {@link LevelAccessor The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param neighborPos {@link BlockPos The neighbor block pos}
     * @return {@link BlockState The updated block state}
     */
    @Override
    public @NotNull BlockState updateShape(final BlockState state, final Direction direction, final @NotNull BlockState neighborState, final @NotNull LevelAccessor level, final @NotNull BlockPos pos, final @NotNull BlockPos neighborPos) {
        final Direction.Axis axis = direction.getAxis();
        final Direction.Axis stateAxis = state.getValue(AXIS);
        return (stateAxis.equals(axis) || !axis.isHorizontal()) && neighborState.getBlock() != this && !(new MWPortalShape(level, pos, stateAxis, getPortalBlocks(), getPortalFrameState())).isComplete() ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    /**
     * Teleport an entity when is inside the portal
     *
     * @param state {@link BlockState The current block state}
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param entity {@link Entity The entity that is inside the portal}
     */
    @Override
    public void entityInside(final @NotNull BlockState state, final @NotNull Level level, final @NotNull BlockPos pos, final Entity entity) {
        if (!entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
            if (entity.isOnPortalCooldown()) {
                entity.setPortalCooldown();
            } else {
                final Level entityLevel = entity.level();
                if (!entityLevel.isClientSide() && !pos.equals(entity.portalEntrancePos)) {
                    entity.portalEntrancePos = pos.immutable();
                }
                final MinecraftServer server = entityLevel.getServer();
                final ResourceKey<Level> dimension = getDimension();
                final ResourceKey<Level> destination = entityLevel.dimension().equals(dimension) ? Level.OVERWORLD : dimension;
                if (server != null) {
                    ServerLevel destinationLevel = server.getLevel(destination);
                    if (destinationLevel != null && server.isNetherEnabled() && !entity.isPassenger()) {
                        entityLevel.getProfiler().push(dimension.location().getPath() + "_portal");
                        entity.setPortalCooldown();
                        entity.changeDimension(destinationLevel, getTeleporter(destinationLevel));
                        entityLevel.getProfiler().pop();
                    }
                }
            }
        }
    }

    /**
     * {@link MineWorld MineWorld} {@link PortalShape portal shape}
     */
    public static class MWPortalShape extends PortalShape {

        /**
         * {@link Integer The minimum portal width}
         */
        private static final int MIN_WIDTH = 2;
        /**
         * {@link Integer The maximum portal width}
         */
        public static final int MAX_WIDTH = 21;
        /**
         * {@link Integer The minimum portal height}
         */
        private static final int MIN_HEIGHT = 3;
        /**
         * {@link Integer The maximum portal height}
         */
        public static final int MAX_HEIGHT = 21;
        /**
         * {@link LevelAccessor The level reference}
         */
        private final LevelAccessor level;
        /**
         * {@link Direction.Axis The portal axis}
         */
        private final Direction.Axis axis;
        /**
         * {@link Direction The portal right direction}
         */
        private final Direction rightDirection;
        /**
         * {@link Integer The number of portal blocks}
         */
        private int numPortalBlocks;
        /**
         * {@link BlockPos The portal bottom left corner}
         */
        private BlockPos bottomLeft;
        /**
         * {@link Integer The portal height}
         */
        private int height;
        /**
         * {@link Integer The portal width}
         */
        private final int width;
        /**
         * {@link BlockBehaviour.StatePredicate The portal frame state predicate}
         */
        private final BlockBehaviour.StatePredicate FRAME;
        /**
         * {@link TagKey<Block> The portal frame blocks}
         */
        private final TagKey<Block> PORTAL_BLOCKS;
        /**
         * {@link BlockState The portal block state}
         */
        private final BlockState PORTAL_STATE;

        public MWPortalShape(final LevelAccessor level, final BlockPos pos, final Direction.Axis axis, final TagKey<Block> portalBlocks, final BlockState defaultFrameState) {
            super(level, pos, axis);
            this.level = level;
            this.axis = axis;
            this.rightDirection = axis.equals(Direction.Axis.X) ? Direction.WEST : Direction.SOUTH;
            this.PORTAL_BLOCKS = portalBlocks;
            this.FRAME = (state, getter, blockPos) -> state.is(PORTAL_BLOCKS);
            this.PORTAL_STATE = defaultFrameState;
            this.bottomLeft = this.calculateBottomLeft(pos);
            if (this.bottomLeft == null) {
                this.bottomLeft = pos;
                this.width = 1;
                this.height = 1;
            } else {
                this.width = this.calculateWidth();
                if (this.width > 0) {
                    this.height = this.calculateHeight();
                }
            }
        }

        /**
         * Get the {@link BlockPos portal bottom left corner}
         *
         * @param pos {@link BlockPos The current block pos}
         * @return {@link BlockPos The portal bottom left corner}
         */
        @Nullable
        private BlockPos calculateBottomLeft(BlockPos pos) {
            for(int i = Math.max(this.level.getMinBuildHeight(), pos.getY() - MAX_HEIGHT); pos.getY() > i && isEmpty(this.level.getBlockState(pos.below())); pos = pos.below()) { }
            final Direction direction = this.rightDirection.getOpposite();
            final int distance = this.getDistanceUntilEdgeAboveFrame(pos, direction) - 1;
            return distance < 0 ? null : pos.relative(direction, distance);
        }

        /**
         * Get the {@link Integer portal width}
         *
         * @return {@link Integer The portal width}
         */
        private int calculateWidth() {
            final int distance = this.getDistanceUntilEdgeAboveFrame(this.bottomLeft, this.rightDirection);
            return distance >= MIN_WIDTH && distance <= MAX_WIDTH ? distance : 0;
        }

        /**
         * Get the {@link Integer portal height}
         *
         * @return {@link Integer The portal height}
         */
        private int calculateHeight() {
            final BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
            final int distance = this.getDistanceUntilTop(mutablePos);
            return distance >= MIN_HEIGHT && distance <= MAX_HEIGHT && this.hasTopFrame(mutablePos, distance) ? distance : 0;
        }

        /**
         * Get the distance from the opposite portal frame
         *
         * @param pos {@link BlockPos The current block pos}
         * @param direction {@link Direction The portal direction}
         * @return {@link Integer The distance from the opposite portal frame}
         */
        private int getDistanceUntilEdgeAboveFrame(final BlockPos pos, final Direction direction) {
            final BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

            for (int i = 0; i <= MAX_WIDTH; ++i) {
                mutablePos.set(pos).move(direction, i);
                final BlockState blockstate = this.level.getBlockState(mutablePos);
                if (!isEmpty(blockstate)) {
                    if (FRAME.test(blockstate, this.level, mutablePos)) {
                        return i;
                    }
                    break;
                }

                final BlockState belowBlockState = this.level.getBlockState(mutablePos.move(Direction.DOWN));
                if (!FRAME.test(belowBlockState, this.level, mutablePos)) {
                    break;
                }
            }

            return 0;
        }

        /**
         * Check if the portal has the top frame
         *
         * @param pos {@link BlockPos The current block pos}
         * @param height {@link Integer The portal height}
         * @return {@link Boolean True if the portal has the top frame}
         */
        private boolean hasTopFrame(final BlockPos.MutableBlockPos pos, final int height) {
            for (int i = 0; i < this.width; ++i) {
                final BlockPos.MutableBlockPos mutablePos = pos.set(this.bottomLeft).move(Direction.UP, height).move(this.rightDirection, i);
                if (!FRAME.test(this.level.getBlockState(mutablePos), this.level, mutablePos)) {
                    return false;
                }
            }

            return true;
        }

        /**
         * Get the distance until the top of the portal
         *
         * @param pos {@link BlockPos The current block pos}
         * @return {@link Integer The distance until the top of the portal}
         */
        private int getDistanceUntilTop(final BlockPos.MutableBlockPos pos) {
            for (int i = 0; i < MAX_HEIGHT; ++i) {
                pos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDirection, -1);
                if (!FRAME.test(this.level.getBlockState(pos), this.level, pos)) {
                    return i;
                }

                pos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDirection, this.width);
                if (!FRAME.test(this.level.getBlockState(pos), this.level, pos)) {
                    return i;
                }

                for (int j = 0; j < this.width; ++j) {
                    pos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDirection, j);
                    final BlockState aboveBlockState = this.level.getBlockState(pos);
                    if (!isEmpty(aboveBlockState)) {
                        return i;
                    }

                    if (aboveBlockState.is(PORTAL_BLOCKS)) {
                        ++this.numPortalBlocks;
                    }
                }
            }

            return MAX_HEIGHT;
        }

        /**
         * Check if a {@link BlockState block state} is air, fire or a portal block
         *
         * @param state {@link BlockState The block state to check}
         * @return {@link Boolean True if is air, fire or a portal block}
         */
        private boolean isEmpty(final BlockState state) {
            return state.isAir() || state.is(BlockTags.FIRE) || state.is(PORTAL_STATE.getBlock());
        }

        /**
         * Check if the portal is valid
         *
         * @return {@link Boolean True if the portal is valid}
         */
        public boolean isValid() {
            return this.bottomLeft != null && this.width >= MIN_WIDTH && this.width <= MAX_WIDTH && this.height >= MIN_HEIGHT && this.height <= MAX_HEIGHT;
        }

        /**
         * Create the portal blocks
         */
        public void createPortalBlocks() {
            final BlockState blockstate = this.PORTAL_STATE.setValue(NetherPortalBlock.AXIS, this.axis);
            BlockPos.betweenClosed(this.bottomLeft, this.bottomLeft.relative(Direction.UP, this.height - 1).relative(this.rightDirection, this.width - 1)).forEach(pos -> this.level.setBlock(pos, blockstate, 18));
        }

        /**
         * Check if a portal is complete
         *
         * @return {@link Boolean True if the portal is complete}
         */
        public boolean isComplete() {
            return this.isValid() && this.numPortalBlocks == this.width * this.height;
        }
    }

}