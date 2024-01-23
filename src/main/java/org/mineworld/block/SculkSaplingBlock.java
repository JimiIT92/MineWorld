package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWTreeGrowers;
import org.mineworld.helper.PropertyHelper;

/**
 * Implementation class for a {@link SaplingBlock Sculk Sapling Block}
 */
public class SculkSaplingBlock extends SaplingBlock {

    /**
     * Constructor. Set the {@link Properties Block Properties}
     */
    public SculkSaplingBlock() {
        super(MWTreeGrowers.SCULK_TREE_GROWER.get(), PropertyHelper.copy(Blocks.OAK_SAPLING));
    }

    /**
     * Check if the Block can be placed at the given {@link BlockPos location}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Boolean True if the Block can be placed}
     */
    @Override
    protected boolean mayPlaceOn(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos) {
        return blockState.is(Blocks.SCULK) || blockState.is(MWBlocks.SCULK_SOIL.get()) || super.mayPlaceOn(blockState, blockGetter, blockPos);
    }

}