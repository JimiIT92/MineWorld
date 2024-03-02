package org.mineworld.helper;


import net.minecraft.client.Minecraft;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper class for {@link Recipe Recipes}
 */
public final class RecipeHelper {

    /**
     * Get all the {@link Recipe Recipes} for the given {@link RecipeType Recipe Type}
     *
     * @param recipeType {@link RecipeType The Recipe Type}
     * @return {@link List<Recipe> The Recipe List}
     * @param <T> {@link T The Recipe Type}
     */
    public static <C extends Container, T extends Recipe<C>> List<T> getAllRecipes(final RecipeType<T> recipeType) {
        final RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        return getAllRecipes(recipeManager.getAllRecipesFor(recipeType));
    }

    /**
     * Get all the {@link Recipe Recipes} for the given {@link List<RecipeHolder> Recipe Holders}
     *
     * @param recipeHolderList {@link List<RecipeHolder> The Recipe Holders}
     * @return {@link List<Recipe> The Recipe List}
     * @param <T> {@link T The Recipe Type}
     */
    public static <T extends Recipe<?>> List<T> getAllRecipes(final List<RecipeHolder<T>> recipeHolderList) {
        return recipeHolderList.stream().map(recipeHolder -> recipeHolder.value()).collect(Collectors.toList());
    }

}