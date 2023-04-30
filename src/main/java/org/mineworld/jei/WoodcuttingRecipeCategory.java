package org.mineworld.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.block.WoodcutterBlock;
import org.mineworld.core.MWBlocks;
import org.mineworld.helper.ItemHelper;
import org.mineworld.recipe.WoodcutterRecipe;

/**
 * Implementation class for the Woodcutting JEI recipe category
 */
public class WoodcuttingRecipeCategory implements IRecipeCategory<WoodcutterRecipe> {

    /**
     * {@link ResourceLocation The woodcutting recipe category id}
     */
    public static final ResourceLocation ID = new ResourceLocation(MineWorld.MOD_ID, "woodcutting");
    /**
     * {@link IDrawable The woodcutting recipe category background}
     */
    private final IDrawable background;
    /**
     * {@link IDrawable The woodcutting recipe category icon}
     */
    private final IDrawable icon;

    /**
     * Constructor. Set the recipe category background and icon
     *
     * @param guiHelper {@link IGuiHelper The gui helper}
     */
    public WoodcuttingRecipeCategory(final IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(JEIHelper.getVanillaTextureLocation(), 0, 220, 82, 34);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, ItemHelper.getDefaultStack(MWBlocks.WOODCUTTER));
    }

    /**
     * Get the {@link RecipeType JEI recipe type}
     *
     * @return {@link JEIMineWorldPlugin#WOODCUTTING The woodcutting JEI recipe type}
     */
    @Override
    public @NotNull RecipeType<WoodcutterRecipe> getRecipeType() {
        return JEIMineWorldPlugin.WOODCUTTING;
    }

    /**
     * Get the {@link Component JEI recipe category title}
     *
     * @return {@link Component The JEI recipe category title}
     */
    @Override
    public @NotNull Component getTitle() {
        return WoodcutterBlock.CONTAINER_TITLE;
    }

    /**
     * Get the {@link IDrawable JEI recipe category background}
     *
     * @return {@link IDrawable The JEI recipe category background}
     */
    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    /**
     * Get the {@link IDrawable JEI recipe category icon}
     *
     * @return {@link IDrawable The JEI recipe category icon}
     */
    @Override
    public @NotNull IDrawable getIcon() {
        return this.icon;
    }

    /**
     * Show a recipe inside the JEI plugin
     *
     * @param recipeLayoutBuilder {@link IRecipeLayoutBuilder The recipe layout builder}
     * @param recipe {@link WoodcutterRecipe The recipe to show}
     * @param focusGroup {@link IFocusGroup The focus group}
     */
    @Override
    public void setRecipe(final @NotNull IRecipeLayoutBuilder recipeLayoutBuilder, final @NotNull WoodcutterRecipe recipe, final @NotNull IFocusGroup focusGroup) {
        recipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, 1, 9).addIngredients(recipe.ingredient);
        recipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT, 61, 9).addItemStack(recipe.result);
    }

    /**
     * Check if the {@link WoodcutterRecipe recipe} is handled
     *
     * @param recipe {@link WoodcutterRecipe The recipe to check}
     * @return {@link Boolean True if the recipe is not marked as special}
     */
    @Override
    public boolean isHandled(final WoodcutterRecipe recipe) {
        return !recipe.isSpecial();
    }

}