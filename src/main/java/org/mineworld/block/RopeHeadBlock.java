package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

/**
 * Abstact base class for a Rope block
 */
public abstract class RopeHeadBlock extends AbstractRopeBlock {

    /**
     * Constructor. Set the block properties
     *
     * @param ropeDirection {@link Direction The rope direction}
     * @param shape {@link VoxelShape The rope shape}
     */
    public RopeHeadBlock(final Direction ropeDirection, final VoxelShape shape) {
        super(ropeDirection, shape);
    }

    /**
     * Check if the block is randomly ticking
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link Boolean False}
     */
    public boolean isRandomlyTicking(final @NotNull BlockState blockState) {
        return false;
    }

    /**
     * Update the {@link BlockState current block state} on neighbor change
     *
     * @param blockState {@link BlockState The current block state}
     * @param neighborState {@link BlockState The neighbor block state}
     * @return {@link BlockState The updated block state}
     */
    protected BlockState updateBodyAfterConvertedFromHead(final BlockState blockState, final BlockState neighborState) {
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
    public @NotNull BlockState updateShape(final @NotNull BlockState blockState, final @NotNull Direction direction, final @NotNull BlockState neighborState, final @NotNull LevelAccessor levelAccessor, final @NotNull BlockPos blockPos, final @NotNull BlockPos neighborPos) {
        if (direction.equals(this.ropeDirection.getOpposite()) && !blockState.canSurvive(levelAccessor, blockPos)) {
            levelAccessor.scheduleTick(blockPos, this, 1);
        }
        return (!direction.equals(this.ropeDirection) || !neighborState.is(this) && !neighborState.is(this.getBodyBlock())) ?
                super.updateShape(blockState, direction, neighborState, levelAccessor, blockPos, neighborPos) :
                this.updateBodyAfterConvertedFromHead(blockState, this.getBodyBlock().defaultBlockState());
    }

    /**
     * Get the {@link RopeHeadBlock rope head block}
     *
     * @return {@link RopeHeadBlock The rope head block}
     */
    protected RopeHeadBlock getHeadBlock() {
        return this;
    }

}
