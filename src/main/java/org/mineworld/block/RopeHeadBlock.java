package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

/**
 * Abstract base class for a Rope Head Block
 */
public abstract class RopeHeadBlock extends AbstractRopeBlock {

    /**
     * Constructor. Set the Block properties
     *
     * @param direction {@link Direction The Rope direction}
     * @param shape {@link VoxelShape The Rope shape}
     */
    public RopeHeadBlock(final Direction direction, final VoxelShape shape) {
        super(direction, shape);
    }

    /**
     * Check if the Block should randomly ticking
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean#FALSE False}
     */
    public boolean isRandomlyTicking(final @NotNull BlockState blockState) {
        return false;
    }

    /**
     * Update the {@link BlockState Rope Body Block State} on neighbor change
     *
     * @param blockState {@link BlockState The current Block State}
     * @param neighborBlockState {@link BlockState The neighbor Block State}
     * @return {@link BlockState The updated Rope Body Block State}
     */
    protected BlockState updateBodyAfterConvertedFromHead(final BlockState blockState, final BlockState neighborBlockState) {
        return neighborBlockState;
    }

    /**
     * Update the {@link BlockState Block State} on neighbor changes
     *
     * @param blockState {@link BlockState The current Block State}
     * @param direction {@link Direction The direction the changes are coming}
     * @param neighborBlockState {@link BlockState The neighbor Block State}
     * @param levelAccessor {@link LevelAccessor The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param neighborBlockPos {@link BlockPos The neighbor Block Pos}
     * @return {@link BlockState The updated Block State}
     */
    public @NotNull BlockState updateShape(final @NotNull BlockState blockState, final @NotNull Direction direction, final @NotNull BlockState neighborBlockState, final @NotNull LevelAccessor levelAccessor, final @NotNull BlockPos blockPos, final @NotNull BlockPos neighborBlockPos) {
        if (direction.equals(this.ropeDirection.getOpposite()) && !blockState.canSurvive(levelAccessor, blockPos)) {
            levelAccessor.scheduleTick(blockPos, this, 1);
        }
        return (!direction.equals(this.ropeDirection) || !neighborBlockState.is(this) && !neighborBlockState.is(this.getBodyBlock())) ?
                super.updateShape(blockState, direction, neighborBlockState, levelAccessor, blockPos, neighborBlockPos) :
                this.updateBodyAfterConvertedFromHead(blockState, this.getBodyBlock().defaultBlockState());
    }

    /**
     * Get the {@link RopeHeadBlock Rope Head Block}
     *
     * @return {@link RopeHeadBlock The Rope Head Block}
     */
    protected RopeHeadBlock getHeadBlock() {
        return this;
    }

}