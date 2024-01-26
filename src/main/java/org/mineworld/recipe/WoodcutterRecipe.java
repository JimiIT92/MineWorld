package org.mineworld.recipe;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWRecipeSerializers;
import org.mineworld.core.MWRecipeTypes;

/**
 * {@link MineWorld MineWorld} {@link SingleItemRecipe Woodcutter Recipe}
 */
public class WoodcutterRecipe extends SingleItemRecipe {

    /**
     * Constructor. Set the {@link Recipe Recipe Properties}
     *
     * @param name {@link String The Recipe name}
     * @param ingredient {@link Ingredient The Recipe ingredient}
     * @param result {@link ItemStack The Recipe result}
     */
    public WoodcutterRecipe(final String name, final Ingredient ingredient, final ItemStack result) {
        super(MWRecipeTypes.WOODCUTTING.get(), MWRecipeSerializers.WOODCUTTER.get(), name, ingredient, result);
    }

    /**
     * Check if the {@link Container Container} {@link Ingredient Ingredients} matches a {@link Recipe Recipe}
     *
     * @param container {@link Container The Recipe Container}
     * @param level {@link Level The level reference}
     * @return {@link Boolean True if the Ingredients matches a Recipe}
     */
    public boolean matches(final Container container, final @NotNull Level level) {
        return this.ingredient.test(container.getItem(0));
    }

    /**
     * Get the {@link ItemStack Item Stack} to display on Recipe Toasts Notifications
     *
     * @return {@link MWBlocks#WOODCUTTER The Woodcutter Item Stack}
     */
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(MWBlocks.WOODCUTTER.get());
    }

}