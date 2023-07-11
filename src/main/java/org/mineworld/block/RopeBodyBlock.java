package org.mineworld.block;

import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public abstract class RopeBodyBlock extends AbstractRopeBlock {

    /**
     * Constructor. Set the block properties
     *
     * @param direction {@link Direction The rope direction}
     * @param shape     {@link VoxelShape The rope shape}
     */
    protected RopeBodyBlock(Direction direction, VoxelShape shape) {
        super(direction, shape);
    }

    /**
     * Update the {@link BlockState current block state} on neighbor change
     *
     * @param blockState {@link BlockState The current block state}
     * @param neighborState {@link BlockState The neighbor block state}
     * @return {@link BlockState The updated block state}
     */
    protected BlockState updateHeadAfterConvertedFromBody(BlockState blockState, BlockState neighborState) {
        return neighborState;
    }

    /**
     * Update the block based on neighbor changes
     *
     * @param blockState {@link BlockState The current block state}
     * @param direction {@link Direction The update direction}
     * @param neighborState {@link BlockState The neighbor block state}
     * @param levelAccessor {@link LevelAccessor The level accessor reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param neighborPos {@link BlockPos The neighbor block pos}
     * @return {@link BlockState The updated block state}
     */
    public @NotNull BlockState updateShape(@NotNull BlockState blockState, @NotNull Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor levelAccessor, @NotNull BlockPos blockPos, @NotNull BlockPos neighborPos) {
        if (direction.equals(this.ropeDirection.getOpposite()) && !blockState.canSurvive(levelAccessor, blockPos)) {
            levelAccessor.scheduleTick(blockPos, this, 1);
        }
        RopeHeadBlock ropeHeadBlock = this.getHeadBlock();
        return direction.equals(this.ropeDirection) && !neighborState.is(this) && !neighborState.is(ropeHeadBlock) ?
                this.updateHeadAfterConvertedFromBody(blockState, ropeHeadBlock.getStateForPlacement(levelAccessor)) :
                super.updateShape(blockState, direction, neighborState, levelAccessor, blockPos, neighborPos);
    }

    /**
     * Get the {@link ItemStack block id stack}
     *
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     * @return {@link ItemStack The block id stack}
     */
    public @NotNull ItemStack getCloneItemStack(@NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new ItemStack(this.getHeadBlock());
    }

    /**
     * Get the {@link BlockPos rope head pposition}
     *
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param block {@link Block The current block}
     * @return {@link Optional<BlockPos> The rope head block pos, if any}
     */
    private Optional<BlockPos> getHeadPos(BlockGetter blockGetter, BlockPos blockPos, Block block) {
        return BlockUtil.getTopConnectedBlock(blockGetter, blockPos, block, this.ropeDirection, this.getHeadBlock());
    }

    /**
     * Check if the block can be replaced
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockPlaceContext {@link BlockPlaceContext The block place context}
     * @return {@link Boolean True if the block can be replaced}
     */
    public boolean canBeReplaced(@NotNull BlockState blockState, @NotNull BlockPlaceContext blockPlaceContext) {
        boolean flag = super.canBeReplaced(blockState, blockPlaceContext);
        return (!flag || !blockPlaceContext.getItemInHand().is(this.getHeadBlock().asItem())) && flag;
    }

    /**
     * Get the {@link Block rope body block}
     *
     * @return {@link Block The rope body block}
     */
    @Override
    protected Block getBodyBlock() {
        return this;
    }
}
