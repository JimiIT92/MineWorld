package org.mineworld.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.TextureHelper;
import org.mineworld.inventory.ForgingTableMenu;

/**
 * {@link MineWorld MineWorld} {@link AbstractContainerScreen Forging Table Screen}
 */
@OnlyIn(Dist.CLIENT)
public class ForgingTableScreen extends AbstractContainerScreen<ForgingTableMenu> {

    /**
     * {@link ResourceLocation The Woodcutter Screen Texture Location}
     */
    public static final ResourceLocation BACKGROUND_LOCATION = TextureHelper.container("forging_table");
    /**
     * {@link ForgingTableMenu The Forging Table Menu}
     */
    private final ForgingTableMenu menu;

    /**
     * Constructor. Set the {@link AbstractContainerScreen Screen Properties}
     *
     * @param menu {@link ForgingTableMenu The Forging Table menu}
     * @param inventory {@link Inventory The Screen inventory}
     * @param title {@link Component The Screen Title Component}
     */
    public ForgingTableScreen(ForgingTableMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.menu = menu;
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
    @Override
    protected void renderBg(final @NotNull GuiGraphics guiGraphics, final float partialTicks, final int screenX, final int screenY) {
        RenderSystem.setShaderTexture(0, BACKGROUND_LOCATION);
        final int x = this.leftPos;
        final int y = this.topPos;
        guiGraphics.blit(BACKGROUND_LOCATION, x, y, 0, 0, this.imageWidth, this.imageHeight);
        if (this.menu.isLit()) {
            final int litProgress = Mth.clamp((28 * this.menu.getLitProgress() + 27 - 1) / 27, 0, 28);
            if (litProgress > 0) {
                guiGraphics.blit(BACKGROUND_LOCATION, x + 74, y + 69, 176, 0, litProgress, 5);
            }
        }

        final int forgingProgress = this.menu.getForgingProgress();
        guiGraphics.blit(BACKGROUND_LOCATION, x + 101, y + 21, 176, 5, forgingProgress + 1, 16);
    }

}