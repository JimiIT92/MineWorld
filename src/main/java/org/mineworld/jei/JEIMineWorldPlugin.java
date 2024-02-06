package org.mineworld.jei;

import com.google.common.base.Suppliers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.ModIds;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IRecipeCatalystLookup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWRecipeTypes;
import org.mineworld.helper.RecipeHelper;
import org.mineworld.helper.ResourceHelper;
import org.mineworld.helper.TextureHelper;
import org.mineworld.jei.category.ForgingRecipeCategory;
import org.mineworld.jei.category.WoodcuttingRecipeCategory;
import org.mineworld.recipe.ForgingRecipe;
import org.mineworld.recipe.WoodcutterRecipe;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link IModPlugin JEI Plugin}
 */
@JeiPlugin
public final class JEIMineWorldPlugin implements IModPlugin {

    //#region Recipe Categories

    /**
     * {@link RecipeType The woodcutting JEI recipe type}
     */
    public static RecipeType<WoodcutterRecipe> WOODCUTTING = new RecipeType<>(WoodcuttingRecipeCategory.ID, WoodcutterRecipe.class);
    /**
     * {@link RecipeType The forging table JEI recipe type}
     */
    public static RecipeType<ForgingRecipe> FORGING = new RecipeType<>(ForgingRecipeCategory.ID, ForgingRecipe.class);

    //#endregion

    //#region Methods

    /**
     * Get the {@link MineWorld MineWorld} {@link ResourceLocation JEI Plugin Id}
     *
     * @return {@link ResourceLocation The MineWorld JEI Plugin Id}
     */
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ResourceHelper.resourceLocation("jei_plugin");
    }

    /**
     * Register the {@link MineWorld MineWorld} {@link IRecipeCategory JEI Recipe Categories}
     *
     * @param recipeCategoryRegistration {@link IRecipeCategoryRegistration The Recipe Category Registration reference}
     */
    @Override
    public void registerCategories(final @NotNull IRecipeCategoryRegistration recipeCategoryRegistration) {
        final IGuiHelper guiHelper = recipeCategoryRegistration.getJeiHelpers().getGuiHelper();
        recipeCategoryRegistration.addRecipeCategories(
                new WoodcuttingRecipeCategory(guiHelper),
                new ForgingRecipeCategory(guiHelper)
        );
    }

    /**
     * Add the {@link MineWorld MineWorld} Recipes to {@link IModPlugin JEI}
     *
     * @param recipeRegistration {@link IRecipeRegistration The Recipe Registration reference}
     */
    @Override
    public void registerRecipes(final @NotNull IRecipeRegistration recipeRegistration) {
        registerRecipes(recipeRegistration, WOODCUTTING, Suppliers.memoize(() -> MWRecipeTypes.WOODCUTTING.get()));
        registerRecipes(recipeRegistration, FORGING, Suppliers.memoize(() -> MWRecipeTypes.FORGING.get()));
    }

    /**
     * Add some {@link MineWorld MineWorld} Recipes to {@link IModPlugin JEI}
     *
     * @param recipeRegistration {@link IRecipeRegistration The Recipe Registration reference}
     * @param jeiRecipeType {@link RecipeType<T> The JEI Recipe Type}
     * @param recipeTypeSupplier {@link Supplier<net.minecraft.world.item.crafting.RecipeType> The Minecraft Recipe Type Supplier}
     * @param <C> {@link C The Recipe Container Type}
     * @param <T> {@link T The Recipe Type}
     */
    private <C extends Container, T extends Recipe<C>> void registerRecipes(final @NotNull IRecipeRegistration recipeRegistration, final RecipeType<T> jeiRecipeType, final Supplier<net.minecraft.world.item.crafting.RecipeType<T>> recipeTypeSupplier) {
        recipeRegistration.addRecipes(jeiRecipeType, RecipeHelper.getAllRecipes(recipeTypeSupplier.get()));
    }

    /**
     * Register the {@link MineWorld MineWorld} {@link IRecipeCatalystLookup JEI Recipe Catalysts}
     *
     * @param recipeCatalystRegistration {@link IRecipeCatalystRegistration The Recipe Catalyst Registration reference}
     */
    @Override
    public void registerRecipeCatalysts(final @NotNull IRecipeCatalystRegistration recipeCatalystRegistration) {
        registerRecipeCatalyst(recipeCatalystRegistration, WoodcuttingRecipeCategory.ICON_SUPPLIER, WOODCUTTING);
        registerRecipeCatalyst(recipeCatalystRegistration, ForgingRecipeCategory.ICON_SUPPLIER, FORGING);
    }

    /**
     * Register a {@link MineWorld MineWorld} {@link IRecipeCatalystLookup JEI Recipe Catalyst}
     *
     * @param recipeCatalystRegistration {@link IRecipeCatalystRegistration The Recipe Catalyst Registration reference}
     * @param itemStackSupplier {@link Supplier<ItemLike> The Supplier for the Item icon displayed by this catalyst}
     * @param recipeType {@link RecipeType<T> The JEI Recipe Type}
     * @param <C> {@link C The Recipe Container Type}
     * @param <T> {@link T The Recipe Type}
     */
    private <C extends Container, T extends Recipe<C>> void registerRecipeCatalyst(final IRecipeCatalystRegistration recipeCatalystRegistration, final Supplier<ItemStack> itemStackSupplier, final RecipeType<T> recipeType) {
        recipeCatalystRegistration.addRecipeCatalyst(itemStackSupplier.get(), recipeType);
    }

    /**
     * Get the {@link ResourceLocation JEI Vanilla Texture location}
     *
     * @return {@link ResourceLocation The JEI Vanilla Texture location}
     */
    public static ResourceLocation getVanillaTextureLocation() {
        return TextureHelper.texture(ModIds.JEI_ID, "jei/gui/gui_vanilla");
    }

    //#endregion

}