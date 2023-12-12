package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.PlantType;
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
     * Get the {@link PlantType plant type}
     *
     * @param level {@link BlockGetter The level reference}
     * @param pos {@link BlockPos The current BlockPos}
     * @return {@link PlantType#NETHER The nether plant type}
     */
    @Override
    public PlantType getPlantType(final BlockGetter level, final BlockPos pos) {
        return PlantType.NETHER;
    }
}