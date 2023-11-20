package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.world.worldgen.tree.SculkTreeGrower;

/**
 * Implementation class for a {@link SaplingBlock sculk sapling block}
 */
public class SculkSaplingBlock extends SaplingBlock {

    /**
     * Constructor. Set the block properties
     */
    public SculkSaplingBlock() {
        super(new SculkTreeGrower(), PropertyHelper.copyFromBlock(Blocks.OAK_SAPLING));
    }

    /**
     * Check if the bush can be placed on a certain block
     *
     * @param state {@link BlockState The block state where the bush is being placed on}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link Boolean True if the bush can be placed on top of the provided block}
     */
    @Override
    protected boolean mayPlaceOn(final @NotNull BlockState state, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos) {
        return state.is(Blocks.SCULK) || super.mayPlaceOn(state, blockGetter, blockPos);
    }

}