package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.PlantType;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWItems;
import org.mineworld.helper.PropertyHelper;

public class WarpedWartBlock extends NetherWartBlock {

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     */
    public WarpedWartBlock() {
        super(PropertyHelper.copy(Blocks.NETHER_WART));
    }

    /**
     * Get the {@link ItemStack Item Stack} for the inventory when the {@link Player player} middle mouse click the block
     *
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link ItemStack The Block Item Stack}
     */
    @Override
    public @NotNull ItemStack getCloneItemStack(final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return new ItemStack(MWItems.WARPED_WART.get());
    }

    /**
     * Get the {@link PlantType plant type}
     *
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link PlantType#NETHER The Nether plant type}
     */
    @Override
    public PlantType getPlantType(final BlockGetter blockGetter, final BlockPos blockPos) {
        return PlantType.NETHER;
    }

}