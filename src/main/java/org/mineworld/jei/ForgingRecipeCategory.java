package org.mineworld.jei;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import org.mineworld.client.screen.ForgingTableScreen;
import org.mineworld.core.MWBlocks;
import org.mineworld.helper.ComponentHelper;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.KeyHelper;
import org.mineworld.recipe.ForgingRecipe;
import org.mineworld.recipe.WoodcutterRecipe;

/**
 * Implementation class for the Forging Table JEI recipe category
 */
public class ForgingRecipeCategory implements IRecipeCategory<ForgingRecipe> {

    /**
     * {@link ResourceLocation The forging table background texture location}
     */
    private final ResourceLocation FORGING_TABLE_BG = ForgingTableScreen.BACKGROUND_LOCATION;
    /**
     * {@link IDrawableStatic The static lava texture}
     */
    private final IDrawableStatic staticLava;
    /**
     * {@link IDrawableAnimated The animated lava texture}
     */
    private final IDrawableAnimated animatedLava;
    /**
     * {@link ResourceLocation The woodcutting recipe category id}
     */
    public static final ResourceLocation ID = KeyHelper.location("forging");
    /**
     * {@link IDrawable The woodcutting recipe category background}
     */
    private final IDrawable background;
    /**
     * {@link IDrawable The woodcutting recipe category icon}
     */
    private final IDrawable icon;
    /**
     * {@link Integer The forging time}
     */
    private final int forgingTime = 400;
    /**
     * {@link LoadingCache The cached loading arrows}
     */
    private final LoadingCache<Integer, IDrawableAnimated> cachedArrows;

    /**
     * Constructor. Set the recipe category background and icon
     *
     * @param guiHelper {@link IGuiHelper The gui helper}
     */
    public ForgingRecipeCategory(final IGuiHelper guiHelper) {
        staticLava = guiHelper.createDrawable(FORGING_TABLE_BG, 176, 0, 28, 5);
        animatedLava = guiHelper.createAnimatedDrawable(staticLava, 400, IDrawableAnimated.StartDirection.RIGHT, true);
        this.background = guiHelper.createDrawable(FORGING_TABLE_BG, 38, 3, 112, 79);
        this.icon = guiHelper.createDrawableItemStack(ItemHelper.getDefaultStack(MWBlocks.FORGING_TABLE));
        this.cachedArrows = CacheBuilder.newBuilder()
                .maximumSize(25)
                .build(new CacheLoader<>() {
                    @Override
                    public @NotNull IDrawableAnimated load(final @NotNull Integer forgingTime) {
                        return guiHelper.drawableBuilder(JEIHelper.getVanillaTextureLocation(), 82, 128, 24, 17)
                                .buildAnimated(forgingTime, IDrawableAnimated.StartDirection.LEFT, false);
                }
        });
    }

    /**
     * Get the {@link RecipeType JEI recipe type}
     *
     * @return {@link JEIMineWorldPlugin#FORGING The forging JEI recipe type}
     */
    @Override
    public @NotNull RecipeType<ForgingRecipe> getRecipeType() {
        return JEIMineWorldPlugin.FORGING;
    }

    /**
     * Get the {@link Component JEI recipe category title}
     *
     * @return {@link Component The JEI recipe category title}
     */
    @Override
    public @NotNull Component getTitle() {
        return ComponentHelper.container("forging.jei");
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
    public void setRecipe(final @NotNull IRecipeLayoutBuilder recipeLayoutBuilder, final @NotNull ForgingRecipe recipe, final @NotNull IFocusGroup focusGroup) {
        recipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, 3, 18).addIngredients(recipe.base());
        recipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, 42, 18).addIngredients(recipe.addition());
        recipeLayoutBuilder.addSlot(RecipeIngredientRole.CATALYST, 42, 48).addIngredients(Ingredient.of(Items.LAVA_BUCKET));
        recipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT, 93, 18).addItemStack(recipe.result());
    }

    /**
     * Check if the recipe has been handled
     *
     * @param recipe {@link ForgingRecipe The recipe to check}
     * @return {@link Boolean True if the recipe has been handled}
     */
    @Override
    public boolean isHandled(final ForgingRecipe recipe) {
        return !recipe.isSpecial();
    }

    /**
     * Draw the recipe
     *
     * @param recipe {@link ForgingRecipe The recipe to draw}
     * @param recipeSlotsView {@link IRecipeSlotsView The recipe slots view}
     * @param guiGraphics {@link GuiGraphics The recipe GUI graphics}
     * @param mouseX {@link Double The mouse X coordinate}
     * @param mouseY {@link Double The mouse Y coordinate}
     */
    @Override
    public void draw(final @NotNull ForgingRecipe recipe, final @NotNull IRecipeSlotsView recipeSlotsView, final @NotNull GuiGraphics guiGraphics, final double mouseX, final double mouseY) {
        animatedLava.draw(guiGraphics, 36, 66);
        this.cachedArrows.getUnchecked(forgingTime).draw(guiGraphics, 63, 18);
        drawExperience(recipe, guiGraphics ,0);
        drawForgeTime(recipe, guiGraphics ,66);
    }

    /**
     * Draw the recipe experience
     *
     * @param recipe {@link ForgingRecipe The recipe}
     * @param guiGraphics {@link GuiGraphics The recipe GUI graphics}
     * @param y {@link Integer The Y coordinate where to draw the experience}
     */
    private void drawExperience(final ForgingRecipe recipe, final GuiGraphics guiGraphics, final int y) {
        final float experience = recipe.experience();
        if (experience > 0) {
            drawText(Component.translatable("gui.jei.category.smelting.experience", experience), guiGraphics, y);
        }
    }

    /**
     * Draw the recipe forging time
     *
     * @param recipe {@link ForgingRecipe The recipe}
     * @param guiGraphics {@link GuiGraphics The recipe GUI graphics}
     * @param y {@link Integer The Y coordinate where to draw the forging time}
     */
    private void drawForgeTime(final ForgingRecipe recipe, final GuiGraphics guiGraphics, final int y) {
        final int cookTime = recipe.forgingTime();
        if (cookTime > 0) {
            drawText(Component.translatable("gui.jei.category.smelting.time.seconds", cookTime / 20), guiGraphics, y);
        }
    }

    /**
     * Draw a text on the screen
     *
     * @param text {@link Component The text to draw}
     * @param guiGraphics {@link GuiGraphics The recipe GUI graphics}
     * @param y {@link Integer The Y coordinate where to draw the text}
     */
    private void drawText(final Component text, final GuiGraphics guiGraphics, final int y) {
        final Font fontRenderer = Minecraft.getInstance().font;
        guiGraphics.drawString(fontRenderer, text, getWidth() - fontRenderer.width(text), y, 0xFF808080);
    }

}