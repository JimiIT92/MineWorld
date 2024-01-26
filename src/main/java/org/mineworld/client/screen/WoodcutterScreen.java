package org.mineworld.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.TextureHelper;
import org.mineworld.inventory.WoodcutterMenu;
import org.mineworld.recipe.WoodcutterRecipe;

import java.util.List;
import java.util.Objects;

/**
 * {@link MineWorld MineWorld} {@link AbstractContainerScreen Woodcutter Screen}
 */
@OnlyIn(Dist.CLIENT)
public class WoodcutterScreen extends AbstractContainerScreen<WoodcutterMenu> {

    /**
     * {@link ResourceLocation The Woodcutter Screen Texture Location}
     */
    private static final ResourceLocation BG_LOCATION = TextureHelper.container("woodcutter");
    /**
     * {@link Float The GUI Scroll offset}
     */
    private float scrollOffset;
    /**
     * {@link Boolean If the Player is scrolling into the Screen}
     */
    private boolean scrolling;
    /**
     * {@link Integer The start Slot index}
     */
    private int startIndex;
    /**
     * {@link Boolean If the Screen is displaying some recipes}
     */
    private boolean displayRecipes;

    /**
     * Constructor. Set the {@link AbstractContainerScreen Screen Properties}
     *
     * @param menu {@link WoodcutterMenu The Woodcutter menu}
     * @param inventory {@link Inventory The Screen inventory}
     * @param title {@link Component The Screen Title Component}
     */
    public WoodcutterScreen(final WoodcutterMenu menu, final Inventory inventory, final Component title) {
        super(menu, inventory, title);
        menu.registerUpdateListener(this::containerChanged);
        --this.titleLabelY;
    }

    /**
     * Render the {@link AbstractContainerScreen Screen}
     *
     * @param guiGraphics {@link GuiGraphics The Screen Gui Graphics}
     * @param screenX {@link Integer The Screen X coordinate}
     * @param screenY {@link Integer The Screen Y coordinate}
     * @param partialTicks {@link Float The Screen partial ticks}
     */
    public void render(final @NotNull GuiGraphics guiGraphics, final int screenX, final int screenY, final float partialTicks) {
        super.render(guiGraphics, screenX, screenY, partialTicks);
        this.renderTooltip(guiGraphics, screenX, screenY);
    }

    /**
     * Render the {@link AbstractContainerScreen Screen} Background
     *
     * @param guiGraphics {@link GuiGraphics The Screen Gui Graphics}
     * @param partialTicks {@link Float The Screen partial ticks}
     * @param screenX {@link Integer The Screen X coordinate}
     * @param screenY {@link Integer The Screen Y coordinate}
     */
    protected void renderBg(final @NotNull GuiGraphics guiGraphics, final float partialTicks, final int screenX, final int screenY) {
        RenderSystem.setShaderTexture(0, BG_LOCATION);
        final int x = this.leftPos;
        final int y = this.topPos;
        guiGraphics.blit(BG_LOCATION, x, y, 0, 0, this.imageWidth, this.imageHeight);
        final int scrollOffset = (int)(41.0F * this.scrollOffset);
        guiGraphics.blit(BG_LOCATION, x + 119, y + 15 + scrollOffset, 176 + (this.isScrollBarActive() ? 0 : 12), 0, 12, 15);
        final int recipesX = this.leftPos + 52;
        final int recipesY = this.topPos + 14;
        final int recipesCount = this.startIndex + 12;
        this.renderButtons(guiGraphics, screenX, screenY, recipesX, recipesY, recipesCount);
        this.renderRecipes(guiGraphics, recipesX, recipesY, recipesCount);
    }

    /**
     * Render the {@link AbstractContainerScreen Screen} Tooltips
     *
     * @param guiGraphics {@link GuiGraphics The Screen Gui Graphics}
     * @param screenX {@link Integer The Screen X coordinate}
     * @param screenY {@link Integer The Screen Y coordinate}
     */
    protected void renderTooltip(final @NotNull GuiGraphics guiGraphics, final int screenX, final int screenY) {
        super.renderTooltip(guiGraphics, screenX, screenY);
        if (this.displayRecipes) {
            final int x = this.leftPos + 52;
            final int y = this.topPos + 14;
            final int count = this.startIndex + 12;
            final List<RecipeHolder<WoodcutterRecipe>> recipes = this.menu.getRecipes();
            for(int i = this.startIndex; i < count && i < this.menu.getNumRecipes(); ++i) {
                int currentIndex = i - this.startIndex;
                int column = x + currentIndex % 4 * 16;
                int row = y + currentIndex / 4 * 18 + 2;
                if (screenX >= column && screenX < column + 16 && screenY >= row && screenY < row + 18) {
                    guiGraphics.renderTooltip(this.font, recipes.get(i).value().getResultItem(Objects.requireNonNull(Objects.requireNonNull(this.minecraft).level).registryAccess()), screenX, screenY);
                }
            }
        }
    }

    /**
     * Render the {@link AbstractContainerScreen Screen} Buttons
     *
     * @param guiGraphics {@link GuiGraphics The Screen Gui Graphics}
     * @param screenX {@link Integer The Screen X coordinate}
     * @param screenY {@link Integer The Screen Y coordinate}
     * @param recipesX {@link Integer The Screen Recipes X coordinate}
     * @param recipesY {@link Integer The Screen Recipes Y coordinate}
     * @param count {@link Integer The number of Buttons to render}
     */
    private void renderButtons(final GuiGraphics guiGraphics, final int screenX, final int screenY, final int recipesX, final int recipesY, final int count) {
        for(int i = this.startIndex; i < count && i < this.menu.getNumRecipes(); ++i) {
            int currentIndex = i - this.startIndex;
            int column = recipesX + currentIndex % 4 * 16;
            int row = recipesY + (currentIndex / 4) * 18 + 2;
            int imageHeight = this.imageHeight;
            if (i == this.menu.getSelectedRecipeIndex()) {
                imageHeight += 18;
            } else if (screenX >= column && screenY >= row && screenX < column + 16 && screenY < row + 18) {
                imageHeight += 36;
            }
            guiGraphics.blit(BG_LOCATION, column, row - 1, 0, imageHeight, 16, 18);
        }
    }

    /**
     * Render the {@link AbstractContainerScreen Screen} Recipes
     *
     * @param guiGraphics {@link GuiGraphics The Screen Gui Graphics}
     * @param screenX {@link Integer The Screen X coordinate}
     * @param screenY {@link Integer The Screen Y coordinate}
     * @param count {@link Integer The number of Buttons to render}
     */
    private void renderRecipes(final GuiGraphics guiGraphics, final int screenX, final int screenY, final int count) {
        final List<RecipeHolder<WoodcutterRecipe>> recipes = this.menu.getRecipes();
        for(int i = this.startIndex; i < count && i < this.menu.getNumRecipes(); ++i) {
            int currentIndex = i - this.startIndex;
            int column = screenX + currentIndex % 4 * 16;
            int row = screenY + (currentIndex / 4) * 18 + 2;
            guiGraphics.renderItem(recipes.get(i).value().getResultItem(Objects.requireNonNull(Objects.requireNonNull(this.minecraft).level).registryAccess()), column, row);
        }
    }

    /**
     * Handle the mouse click
     *
     * @param mouseX {@link Double The mouse X coordinate}
     * @param mouseY {@link Double The mouse Y coordinate}
     * @param buttonId {@link Integer The mouse Button id}
     * @return {@link Boolean True if the mouse click has been handled correctly}
     */
    public boolean mouseClicked(final double mouseX, final double mouseY, final int buttonId) {
        this.scrolling = false;
        if (this.displayRecipes) {
            int x = this.leftPos + 52;
            int y = this.topPos + 14;
            final int count = this.startIndex + 12;

            for(int i = this.startIndex; i < count; ++i) {
                int currentIndex = i - this.startIndex;
                double mouseOffsetX = mouseX - (double)(x + currentIndex % 4 * 16);
                double mouseOffsetY = mouseY - (double)(y + currentIndex / 4 * 18);
                if (mouseOffsetX >= 0.0D && mouseOffsetY >= 0.0D && mouseOffsetX < 16.0D && mouseOffsetY < 18.0D && this.menu.clickMenuButton(Objects.requireNonNull(Objects.requireNonNull(this.minecraft).player), i)) {
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.WOOD_PLACE, 1.0F));
                    Objects.requireNonNull(this.minecraft.gameMode).handleInventoryButtonClick((this.menu).containerId, i);
                    return true;
                }
            }

            x = this.leftPos + 119;
            y = this.topPos + 9;
            if (mouseX >= (double)x && mouseX < (double)(x + 12) && mouseY >= (double)y && mouseY < (double)(y + 54)) {
                this.scrolling = true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, buttonId);
    }

    /**
     * Handle the drag of the mouse
     *
     * @param mouseX {@link Double The mouse X coordinate}
     * @param mouseY {@link Double The mouse Y coordinate}
     * @param buttonId {@link Integer The mouse Button id}
     * @param offsetX {@link Double The mouse X coordinate offset}
     * @param offsetY {@link Double The mouse Y coordinate offset}
     * @return {@link Boolean True if the mouse drag has been handled correctly}
     */
    public boolean mouseDragged(final double mouseX, final double mouseY, final int buttonId, final double offsetX, final double offsetY) {
        if (this.scrolling && this.isScrollBarActive()) {
            final int top = this.topPos + 14;
            final int y = top + 54;
            this.scrollOffset = ((float)mouseY - (float)top - 7.5F) / ((float)(y - top) - 15.0F);
            this.scrollOffset = Mth.clamp(this.scrollOffset, 0.0F, 1.0F);
            this.startIndex = (int)((double)(this.scrollOffset * (float)this.getOffscreenRows()) + 0.5D) * 4;
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, buttonId, offsetX, offsetY);
    }

    /**
     * Handle the mouse scrolling
     *
     * @param mouseX {@link Double The mouse X coordinate}
     * @param mouseY {@link Double The mouse Y coordinate}
     * @param scrollDistance {@link Double The mouse scroll distance}
     * @return {@link Boolean#TRUE True}
     */
    public boolean mouseScrolled(final double mouseX, final double mouseY, final double scrollDistance) {
        if (this.isScrollBarActive()) {
            final int offscreenRows = this.getOffscreenRows();
            final float scrollSize = (float)scrollDistance / (float)offscreenRows;
            this.scrollOffset = Mth.clamp(this.scrollOffset - scrollSize, 0.0F, 1.0F);
            this.startIndex = (int)((double)(this.scrollOffset * (float)offscreenRows) + 0.5D) * 4;
        }
        return true;
    }

    /**
     * Check if the scrollbar is active
     *
     * @return {@link Boolean True if the scrollbar is active}
     */
    private boolean isScrollBarActive() {
        return this.displayRecipes && this.menu.getNumRecipes() > 12;
    }

    /**
     * Get the {@link Integer number of rows outside of the Screen}
     *
     * @return {@link Integer The number of rows outside of the Screen}
     */
    protected int getOffscreenRows() {
        return (this.menu.getNumRecipes() + 4 - 1) / 4 - 3;
    }

    /**
     * Handle container changes
     */
    private void containerChanged() {
        this.displayRecipes = this.menu.hasInputItem();
        if (!this.displayRecipes) {
            this.scrollOffset = 0.0F;
            this.startIndex = 0;
        }
    }

}