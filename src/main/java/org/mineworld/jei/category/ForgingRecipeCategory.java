package org.mineworld.jei.category;

import com.google.common.base.Suppliers;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.client.screen.ForgingTableScreen;
import org.mineworld.core.MWBlocks;
import org.mineworld.helper.ComponentHelper;
import org.mineworld.helper.ResourceHelper;
import org.mineworld.jei.JEIMineWorldPlugin;
import org.mineworld.recipe.ForgingRecipe;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link IRecipeCategory JEI Forging Recipe Category}
 */
public class ForgingRecipeCategory extends MWRecipeCategory<ForgingRecipe> {

    /**
     * {@link ResourceLocation The Forging Recipe Category Id}
     */
    public static final ResourceLocation ID = ResourceHelper.resourceLocation("forging");
    /**
     * {@link Supplier<ItemStack> The JEI Screen Icon Supplier}
     */
    public static final Supplier<ItemStack> ICON_SUPPLIER = Suppliers.memoize(() -> MWBlocks.FORGING_TABLE.get().asItem().getDefaultInstance());
    /**
     * {@link IDrawableAnimated The Animated Lava Texture}
     */
    private final IDrawableAnimated animatedLava;
    /**
     * {@link LoadingCache The Cached Loading Arrows}
     */
    private final LoadingCache<Integer, IDrawableAnimated> cachedArrows;

    /**
     * Constructor. Set the {@link IRecipeCategory Recipe Category properties}
     *
     * @param guiHelper {@link IGuiHelper The GUI Helper}
     */
    public ForgingRecipeCategory(final IGuiHelper guiHelper) {
        super(guiHelper, ICON_SUPPLIER, JEIMineWorldPlugin.FORGING, ComponentHelper.container("forging.jei"));
        final ResourceLocation FORGING_TABLE_BG = ForgingTableScreen.BACKGROUND_LOCATION;
        final IDrawableStatic staticLava = guiHelper.createDrawable(FORGING_TABLE_BG, 176, 0, 28, 5);
        animatedLava = guiHelper.createAnimatedDrawable(staticLava, 400, IDrawableAnimated.StartDirection.RIGHT, true);
        this.background = guiHelper.createDrawable(FORGING_TABLE_BG, 38, 3, 112, 79);
        this.cachedArrows = CacheBuilder.newBuilder().maximumSize(25).build(new CacheLoader<>() {
                    @Override
                    public @NotNull IDrawableAnimated load(final @NotNull Integer forgingTime) {
                        return guiHelper.drawableBuilder(JEIMineWorldPlugin.getVanillaTextureLocation(), 82, 128, 24, 17)
                                .buildAnimated(forgingTime, IDrawableAnimated.StartDirection.LEFT, false);
                    }
        });
    }

    /**
     * Show a {@link Recipe Recipe} inside the JEI Screen
     *
     * @param recipeLayoutBuilder {@link IRecipeLayoutBuilder The Recipe Layout Builder}
     * @param recipe {@link ForgingRecipe The Recipe to show}
     * @param focusGroup {@link IFocusGroup The JEI Focus Group}
     */
    @Override
    public void setRecipe(final @NotNull IRecipeLayoutBuilder recipeLayoutBuilder, final @NotNull ForgingRecipe recipe, final @NotNull IFocusGroup focusGroup) {
        recipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, 3, 18).addIngredients(recipe.base());
        recipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, 42, 18).addIngredients(recipe.addition());
        recipeLayoutBuilder.addSlot(RecipeIngredientRole.CATALYST, 42, 48).addIngredients(Ingredient.of(Items.LAVA_BUCKET));
        recipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT, 93, 18).addItemStack(recipe.result());
    }

    /**
     * Draw the {@link ForgingRecipe Recipe}
     *
     * @param recipe {@link ForgingRecipe The current Recipe}
     * @param recipeSlotsView {@link IRecipeSlotsView The Recipe Slots View}
     * @param guiGraphics {@link GuiGraphics The GUI Graphics reference}
     * @param mouseX {@link Double The mouse X coordinate}
     * @param mouseY {@link Double The mouse Y coordinate}
     */
    @Override
    public void draw(final @NotNull ForgingRecipe recipe, final @NotNull IRecipeSlotsView recipeSlotsView, final @NotNull GuiGraphics guiGraphics, final double mouseX, final double mouseY) {
        animatedLava.draw(guiGraphics, 36, 66);
        this.cachedArrows.getUnchecked(recipe.forgingTime()).draw(guiGraphics, 63, 18);
        drawExperience(recipe, guiGraphics ,0);
        drawForgeTime(recipe, guiGraphics ,66);
    }

    /**
     * Draw the {@link ForgingRecipe Recipe} {@link Float Experience}
     *
     * @param recipe {@link ForgingRecipe The current Recipe}
     * @param guiGraphics {@link GuiGraphics The GUI Graphics reference}
     * @param y {@link Integer The Y coordinate where to draw the Experience}
     */
    private void drawExperience(final ForgingRecipe recipe, final GuiGraphics guiGraphics, final int y) {
        final float experience = recipe.experience();
        if (experience > 0) {
            drawText(Component.translatable("gui.jei.category.smelting.experience", experience), guiGraphics, y);
        }
    }

    /**
     * Draw the {@link ForgingRecipe Recipe} {@link Integer Forging Time}
     *
     * @param recipe {@link ForgingRecipe The current Recipe}
     * @param guiGraphics {@link GuiGraphics The GUI Graphics reference}
     * @param y {@link Integer The Y coordinate where to draw the Forging Time}
     */
    private void drawForgeTime(final ForgingRecipe recipe, final GuiGraphics guiGraphics, final int y) {
        final int forgingTime = recipe.forgingTime();
        if (forgingTime > 0) {
            drawText(Component.translatable("gui.jei.category.smelting.time.seconds", forgingTime / 20), guiGraphics, y);
        }
    }

    /**
     * Draw a {@link Component Text} on the JEI Screen
     *
     * @param text {@link Component The text to draw}
     * @param guiGraphics {@link GuiGraphics The GUI Graphics reference}
     * @param y {@link Integer The Y coordinate where to draw the text}
     */
    private void drawText(final Component text, final GuiGraphics guiGraphics, final int y) {
        final Font fontRenderer = Minecraft.getInstance().font;
        guiGraphics.drawString(fontRenderer, text, getWidth() - fontRenderer.width(text), y, 0xFF808080);
    }

}