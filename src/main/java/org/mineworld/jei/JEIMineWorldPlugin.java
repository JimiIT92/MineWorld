package org.mineworld.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWRecipeTypes;
import org.mineworld.helper.KeyHelper;
import org.mineworld.recipe.ForgingRecipe;
import org.mineworld.recipe.WoodcutterRecipe;

/**
 * Implementation class for the {@link MineWorld MineWorld} {@link JeiPlugin JEI plugin integration}
 */
@JeiPlugin
public class JEIMineWorldPlugin implements IModPlugin {

    /**
     * {@link RecipeType The woodcutting JEI recipe type}
     */
    public static RecipeType<WoodcutterRecipe> WOODCUTTING = new RecipeType<>(WoodcuttingRecipeCategory.ID, WoodcutterRecipe.class);
    /**
     * {@link RecipeType The forging table JEI recipe type}
     */
    public static RecipeType<ForgingRecipe> FORGING = new RecipeType<>(ForgingRecipeCategory.ID, ForgingRecipe.class);

    /**
     * Get the {@link ResourceLocation JEI plugin resource location id}
     *
     * @return {@link ResourceLocation The MineWorld JEI plugin id}
     */
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return KeyHelper.location("jei_plugin");
    }

    /**
     * Register the MineWorld JEI recipe categories
     *
     * @param recipeCategoryRegistration {@link IRecipeCategoryRegistration The recipe category recipeCategoryRegistration reference}
     */
    @Override
    public void registerCategories(final @NotNull IRecipeCategoryRegistration recipeCategoryRegistration) {
        recipeCategoryRegistration.addRecipeCategories(
                new WoodcuttingRecipeCategory(recipeCategoryRegistration.getJeiHelpers().getGuiHelper()),
                new ForgingRecipeCategory(recipeCategoryRegistration.getJeiHelpers().getGuiHelper()));
    }

    /**
     * Register the MineWorld recipes into JEI
     *
     * @param recipeRegistration {@link IRecipeRegistration The recipe registration reference}
     */
    @Override
    public void registerRecipes(final @NotNull IRecipeRegistration recipeRegistration) {
        final RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        recipeRegistration.addRecipes(WOODCUTTING, recipeManager.getAllRecipesFor(MWRecipeTypes.WOODCUTTING.get()));
        recipeRegistration.addRecipes(FORGING, recipeManager.getAllRecipesFor(MWRecipeTypes.FORGING.get()));
    }

}