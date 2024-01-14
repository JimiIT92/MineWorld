package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.helper.PropertyHelper;

/**
 * Abstract base class for a Rope Block
 */
public abstract class AbstractRopeBlock extends Block {

    /**
     * {@link Direction The Rope direction}
     */
    protected final Direction ropeDirection;
    /**
     * {@link VoxelShape The Rope shape}
     */
    protected final VoxelShape shape;

    /**
     * Constructor. Set the Block properties
     *
     * @param direction {@link Direction The Rope direction}
     * @param shape {@link VoxelShape The Rope shape}
     */
    protected AbstractRopeBlock(final Direction direction, final VoxelShape shape) {
        super(PropertyHelper.block(MapColor.DIRT, 1.0F, 1.0F, false, SoundType.CAVE_VINES).noCollission().instabreak());
        this.ropeDirection = direction;
        this.shape = shape;
    }

    /**
     * Get the {@link BlockState Block State} after the block has been placed
     *
     * @param placeContext {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed Block State}
     */
    @Nullable
    @Override
    public BlockState getStateForPlacement(final BlockPlaceContext placeContext) {
        final BlockState blockstate = placeContext.getLevel().getBlockState(placeContext.getClickedPos().relative(this.ropeDirection));
        return !blockstate.is(this.getHeadBlock()) && !blockstate.is(this.getBodyBlock()) ? this.getStateForPlacement(placeContext.getLevel()) : this.getBodyBlock().defaultBlockState();
    }

    /**
     * Get the {@link BlockState Block State} after the block has been placed
     *
     * @param levelAccessor {@link LevelAccessor The level reference}
     * @return {@link BlockState The placed Block State}
     */
    public BlockState getStateForPlacement(final LevelAccessor levelAccessor) {
        return this.defaultBlockState();
    }

    /**
     * Check if the Block can stay at the given {@link BlockPos location}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param levelReader {@link LevelReader The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Boolean True if the block can survive}
     */
    public boolean canSurvive(final @NotNull BlockState blockState, final LevelReader levelReader, final BlockPos blockPos) {
        final BlockPos relativeBlockPos = blockPos.relative(this.ropeDirection.getOpposite());
        final BlockState blockstate = levelReader.getBlockState(relativeBlockPos);
        return this.canAttachTo(blockstate) && (blockstate.is(this.getHeadBlock()) || blockstate.is(this.getBodyBlock()) ||
                blockstate.isFaceSturdy(levelReader, relativeBlockPos, this.ropeDirection) || blockstate.is(BlockTags.FENCES));
    }

    /**
     * Ticks the Block
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    public void tick(final BlockState blockState, final @NotNull ServerLevel level, final @NotNull BlockPos blockPos, final @NotNull RandomSource randomSource) {
        if (!blockState.canSurvive(level, blockPos)) {
            level.destroyBlock(blockPos, true);
        }
    }

    /**
     * Check if the Block can be attached to another one
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean#TRUE True}
     */
    protected boolean canAttachTo(final BlockState blockState) {
        return true;
    }

    /**
     * Get the {@link VoxelShape Block Shape}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param collisionContext {@link CollisionContext The collision context}
     * @return {@link VoxelShape The Block Shape}
     */
    public @NotNull VoxelShape getShape(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull CollisionContext collisionContext) {
        return this.shape;
    }

    /**
     * Check if the Block can catch fire
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Boolean#TRUE True}
     */
    @Override
    public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return true;
    }

    /**
     * Get the Block {@link Integer flammability value}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 100}
     */
    @Override
    public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return 100;
    }

    /**
     * Get the Block {@link Integer fire spread speed value}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 15}
     */
    @Override
    public int getFireSpreadSpeed(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return 15;
    }

    /**
     * Get the {@link RopeHeadBlock Rope Head Block}
     *
     * @return {@link RopeHeadBlock The Rope Head Block}
     */
    protected abstract RopeHeadBlock getHeadBlock();

    /**
     * Get the {@link Block Rope Body Block}
     *
     * @return {@link Block The Rope Body Block}
     */
    protected abstract Block getBodyBlock();

}