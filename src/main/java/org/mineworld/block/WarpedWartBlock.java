package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWItems;
import org.mineworld.helper.PropertyHelper;

/**
 * Implementation class for a {@link NetherWartBlock warped wart plant}
 */
public class WarpedWartBlock extends NetherWartBlock {

    /**
     * Constructor. Set the block properties
     */
    public WarpedWartBlock() {
        super(PropertyHelper.copyFromBlock(Blocks.NETHER_WART));
    }

    /**
     * Get the {@link ItemStack block id stack}
     *
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     * @return {@link ItemStack The block id stack}
     */
    @Override
    public @NotNull ItemStack getCloneItemStack(final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return new ItemStack(MWItems.WARPED_WART.get());
    }

    /**
     * Check if the {@link DoublePlantBlock Warped Wart} can be placed on a {@link Block Block}
     *
     * @param state {@link BlockState The current BlockState}
     * @param level {@link BlockGetter The Level reference}
     * @param pos {@link BlockPos The current BlockPos}
     * @return {@link Boolean True} if the {@link DoublePlantBlock Warped Wart} can be placed on a {@link Block Block}
     */
    protected boolean mayPlaceOn(final BlockState state, final @NotNull BlockGetter level, final @NotNull BlockPos pos) {
        return state.is(Blocks.SOUL_SAND);
    }

}