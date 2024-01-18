package org.mineworld.block;

import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Abstract base class for a Rope Body Block
 */
public abstract class RopeBodyBlock extends AbstractRopeBlock {

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param direction {@link Direction The Rope direction}
     * @param shape {@link VoxelShape The Rope shape}
     */
    protected RopeBodyBlock(final Direction direction, final VoxelShape shape) {
        super(direction, shape);
    }

    /**
     * Update the {@link BlockState Rope Head Block State} on neighbor change
     *
     * @param blockState {@link BlockState The current Block State}
     * @param neighborBlockState {@link BlockState The neighbor Block State}
     * @return {@link BlockState The updated Rope Head Block State}
     */
    protected BlockState updateHeadAfterConvertedFromBody(final BlockState blockState, final BlockState neighborBlockState) {
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
        final RopeHeadBlock ropeHeadBlock = this.getHeadBlock();
        return direction.equals(this.ropeDirection) && !neighborBlockState.is(this) && !neighborBlockState.is(ropeHeadBlock) ?
                this.updateHeadAfterConvertedFromBody(blockState, ropeHeadBlock.getStateForPlacement(levelAccessor)) :
                super.updateShape(blockState, direction, neighborBlockState, levelAccessor, blockPos, neighborBlockPos);
    }

    /**
     * Get the {@link ItemStack Item Stack} for the inventory when the {@link Player player} middle mouse click the block
     *
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link ItemStack The Block Item Stack}
     */
    public @NotNull ItemStack getCloneItemStack(final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return new ItemStack(this.getHeadBlock());
    }

    /**
     * Get the {@link BlockPos Rope Head Position}
     *
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param block {@link Block The current Block}
     * @return {@link Optional<BlockPos> The Rope Head Block Pos, if any}
     */
    private Optional<BlockPos> getHeadPos(final BlockGetter blockGetter, final BlockPos blockPos, final Block block) {
        return BlockUtil.getTopConnectedBlock(blockGetter, blockPos, block, this.ropeDirection, this.getHeadBlock());
    }

    /**
     * Check if the Block can be replaced
     *
     * @param blockState {@link BlockState The current Block State}
     * @param context {@link BlockPlaceContext The block place context}
     * @return {@link Boolean True if the Block can be replaced}
     */
    public boolean canBeReplaced(final @NotNull BlockState blockState, final @NotNull BlockPlaceContext context) {
        final boolean flag = super.canBeReplaced(blockState, context);
        return (!flag || !context.getItemInHand().is(this.getHeadBlock().asItem())) && flag;
    }

    /**
     * Get the {@link Block Rope Body Block}
     *
     * @return {@link Block The Rope Body Block}
     */
    @Override
    protected Block getBodyBlock() {
        return this;
    }

}