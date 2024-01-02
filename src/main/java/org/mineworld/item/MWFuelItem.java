package org.mineworld.item;

import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} class for a {@link Item Fuel Item}
 */
public class MWFuelItem extends Item {

    /**
     * {@link Integer The fuel burn time}
     */
    private final int burnTime;

    /**
     * Constructor. Set the {@link Integer fuel burn time}
     *
     * @param burnTime {@link Integer The fuel burn time}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     */
    public MWFuelItem(final int burnTime, final FeatureFlag... featureFlags) {
        super(PropertyHelper.item(featureFlags));
        this.burnTime = burnTime * 20;
    }

    /**
     * Get the {@link Integer fuel burn time}
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @param recipeType {@link RecipeType The current Recipe Type}
     * @return {@link #burnTime The fuel burn time}
     */
    @Override
    public int getBurnTime(final ItemStack itemStack, final @Nullable RecipeType<?> recipeType) {
        return this.burnTime;
    }

}