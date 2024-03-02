package org.mineworld.jei.category;

import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link IRecipeCategory JEI Recipe Category}
 * @param <T> {@link T The Recipe Type}
 */
public abstract class MWRecipeCategory<T extends Recipe<? extends Container>> implements IRecipeCategory<T> {

    /**
     * {@link IDrawable The JEI Screen Icon}
     */
    protected final IDrawable icon;
    /**
     * {@link RecipeType<T> The JEI Recipe Type}
     */
    private final RecipeType<T> recipeType;
    /**
     * {@link Component The JEI Screen Title}
     */
    private final Component title;
    /**
     * {@link IDrawable The Woodcutting Recipe Category Background}
     */
    protected IDrawable background;

    /**
     * Constructor. Set the {@link IRecipeCategory Recipe Category properties}
     *
     * @param guiHelper {@link IGuiHelper The GUI Helper}
     * @param iconSupplier {@link Supplier<ItemStack> The JEI Screen Icon Supplier}
     * @param recipeType {@link RecipeType<T> The JEI Recipe Type}
     * @param title {@link Component The JEI Screen Title}
     */
    public MWRecipeCategory(final IGuiHelper guiHelper, final Supplier<ItemStack> iconSupplier, final RecipeType<T> recipeType, final Component title) {
        this.icon = guiHelper.createDrawableItemStack(iconSupplier.get());
        this.recipeType = recipeType;
        this.title = title;
    }

    /**
     * Get the {@link RecipeType<T> JEI Recipe Type}
     *
     * @return {@link RecipeType<T> The JEI Recipe Type}
     */
    @Override
    public @NotNull RecipeType<T> getRecipeType() {
        return this.recipeType;
    }

    /**
     * Get the {@link Component JEI Screen Title}
     *
     * @return {@link Component The JEI Screen Title}
     */
    @Override
    public @NotNull Component getTitle() {
        return this.title;
    }

    /**
     * Get the {@link IDrawable JEI Recipe Category Icon}
     *
     * @return {@link IDrawable The JEI Recipe Category Icon}
     */
    @Override
    public @NotNull IDrawable getIcon() {
        return this.icon;
    }

    /**
     * Get the {@link IDrawable JEI Recipe Category Background}
     *
     * @return {@link IDrawable The JEI Recipe Category Background}
     */
    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    /**
     * Check if the {@link T Recipe} is handled
     *
     * @param recipe {@link T The current Recipe}
     * @return {@link Boolean True if the recipe is not marked as special}
     */
    @Override
    public boolean isHandled(final T recipe) {
        return !recipe.isSpecial();
    }

}
