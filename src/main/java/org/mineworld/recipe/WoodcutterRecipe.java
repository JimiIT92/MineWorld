package org.mineworld.recipe;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWRecipeSerializers;
import org.mineworld.core.MWRecipeTypes;

/**
 * Implementation class for a {@link MineWorld MineWorld} woodcutter recipe
 */
public class WoodcutterRecipe extends SingleItemRecipe {

    /**
     * Constructor. Set the recipe properties
     *
     * @param name {@link String The recipe name}
     * @param ingredient {@link Ingredient The recipe ingredient}
     * @param itemStack {@link ItemStack The recipe result}
     */
    public WoodcutterRecipe(final String name, final Ingredient ingredient, final ItemStack itemStack) {
        super(MWRecipeTypes.WOODCUTTING.get(), MWRecipeSerializers.WOODCUTTER.get(), name, ingredient, itemStack);
    }

    /**
     * Check if a recipe is correct
     *
     * @param container {@link Container The recipe container}
     * @param level {@link Level The level reference}
     * @return {@link Boolean True if the input id inside the container matches a recipe}
     */
    public boolean matches(final Container container, final @NotNull Level level) {
        return this.ingredient.test(container.getItem(0));
    }

    /**
     * Get the {@link ItemStack item stack to show on recipe unlocks}
     *
     * @return {@link MWBlocks#WOODCUTTER The woodcutter item stack}
     */
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(MWBlocks.WOODCUTTER.get());
    }

}
