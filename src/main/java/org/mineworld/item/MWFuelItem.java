package org.mineworld.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;
import org.mineworld.core.MWItems;

/**
 * Implementation for a {@link Item fuel item}
 */
public class MWFuelItem extends Item {

    /**
     * {@link Integer Fuel burn time} in seconds
     */
    private final int burnTime;

    /**
     * Constructor. Set the {@link Integer burn time}
     *
     * @param burnTime {@link Integer Fuel burn time} in seconds
     */
    public MWFuelItem(final int burnTime) {
        super(MWItems.basicProperties());
        this.burnTime = burnTime;
    }

    /**
     * Get the {@link ItemStack Item Stack} burn time in ticks
     *
     * @param itemStack {@link ItemStack The Item Stack} being used as fuel
     * @param recipeType {@link RecipeType The recipe type}
     * @return {@link Integer Burn time} in ticks
     */
    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return this.burnTime * 20;
    }
}
