package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.mineworld.helper.PropertyHelper;

import javax.annotation.Nullable;

/**
 * Abstract base class for a rope block
 */
public abstract class AbstractRopeBlock extends Block {

    /**
     * {@link Direction The rope direction}
     */
    protected Direction ropeDirection;
    /**
     * {@link VoxelShape The rope shape}
     */
    protected VoxelShape shape;

    /**
     * Constructor. Set the block properties
     *
     * @param direction {@link Direction The rope direction}
     * @param shape {@link VoxelShape The rope shape}
     */
    protected AbstractRopeBlock(Direction direction, VoxelShape shape) {
        super(PropertyHelper.basicBlockProperties(Material.DECORATION, MaterialColor.DIRT, 1.0F, 1.0F, false, SoundType.CAVE_VINES).noCollission().instabreak());
        this.ropeDirection = direction;
        this.shape = shape;
    }

    /**
     * Get the {@link BlockState block state} for the placed block
     *
     * @param blockPlaceContext {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed block state}
     */
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockState blockstate = blockPlaceContext.getLevel().getBlockState(blockPlaceContext.getClickedPos().relative(this.ropeDirection));
        return !blockstate.is(this.getHeadBlock()) && !blockstate.is(this.getBodyBlock()) ? this.getStateForPlacement(blockPlaceContext.getLevel()) : this.getBodyBlock().defaultBlockState();
    }

    /**
     * Get the {@link BlockState default block state}
     *
     * @param levelAccessor {@link LevelAccessor The level accessor reference}
     * @return {@link BlockState The default block state}
     */
    public BlockState getStateForPlacement(LevelAccessor levelAccessor) {
        return this.defaultBlockState();
    }

    /**
     * Check if the block can survive at the current location
     *
     * @param blockState {@link BlockState The current block state}
     * @param levelReader {@link LevelReader The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link Boolean True if the block can survive}
     */
    public boolean canSurvive(@NotNull BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockPos relativeBlockPos = blockPos.relative(this.ropeDirection.getOpposite());
        BlockState blockstate = levelReader.getBlockState(relativeBlockPos);
        return this.canAttachTo(blockstate) && (blockstate.is(this.getHeadBlock()) || blockstate.is(this.getBodyBlock()) ||
                blockstate.isFaceSturdy(levelReader, relativeBlockPos, this.ropeDirection) || blockstate.is(BlockTags.FENCES));
    }

    /**
     * Tick the block to check if it should break
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param random {@link RandomSource The random reference}
     */
    public void tick(BlockState blockState, @NotNull ServerLevel level, @NotNull BlockPos blockPos, @NotNull RandomSource random) {
        if (!blockState.canSurvive(level, blockPos)) {
            level.destroyBlock(blockPos, true);
        }
    }

    /**
     * Check if the block can be attached to another one
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link Boolean True}
     */
    protected boolean canAttachTo(BlockState blockState) {
        return true;
    }

    /**
     * Get the {@link VoxelShape block shape}
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param collisionContext {@link CollisionContext The collision context}
     * @return {@link VoxelShape The block shape}
     */
    public @NotNull VoxelShape getShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
        return this.shape;
    }

    /**
     * Makes the block able to catch fire
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link Level The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Boolean True}
     */
    @Override
    public boolean isFlammable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return true;
    }

    /**
     * Get the block {@link Integer flammability value}
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link Level The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 100}
     */
    @Override
    public int getFlammability(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return 100;
    }

    /**
     * Get the block {@link Integer fire spread speed value}
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link Level The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 15}
     */
    @Override
    public int getFireSpreadSpeed(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return 15;
    }

    /**
     * Get the {@link RopeHeadBlock rope head block}
     *
     * @return {@link RopeHeadBlock The rope head block}
     */
    protected abstract RopeHeadBlock getHeadBlock();

    /**
     * Get the {@link Block rope body block}
     *
     * @return {@link Block The rope body block}
     */
    protected abstract Block getBodyBlock();

}