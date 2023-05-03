package org.mineworld.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.block.ForgingTableBlock;
import org.mineworld.helper.KeyHelper;
import org.mineworld.inventory.ForgingTableMenu;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link ForgingTableBlock forging table screen}
 */
@OnlyIn(Dist.CLIENT)
public class ForgingTableScreen extends AbstractContainerScreen<ForgingTableMenu> {

    /**
     * {@link ResourceLocation The forging table texture location}
     */
    public static final ResourceLocation BACKGROUND_LOCATION = KeyHelper.container("forging_table");
    /**
     * {@link ForgingTableMenu The forging table menu}
     */
    private final ForgingTableMenu menu;

    /**
     * Constructor. Set the screen properties
     *
     * @param menu {@link ForgingTableMenu The forging table menu}
     * @param inventory {@link Inventory The forging table inventory}
     * @param title {@link Component The forigng table title}
     */
    public ForgingTableScreen(ForgingTableMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.menu = menu;
    }

    /**
     * Render the screen
     *
     * @param poseStack {@link PoseStack The screen pose stack}
     * @param screenX {@link Integer The screen X coordinate}
     * @param screenY {@link Integer The screen Y coordinate}
     * @param partialTicks {@link Float The screen partial ticks}
     */
    public void render(final @NotNull PoseStack poseStack, final int screenX, final int screenY, final float partialTicks) {
        super.render(poseStack, screenX, screenY, partialTicks);
        this.renderTooltip(poseStack, screenX, screenY);
    }

    /**
     * Render the forging table background
     *
     * @param poseStack {@link PoseStack The forging table pose stack}
     * @param partialTicks {@link Float The screen partial ticks}
     * @param screenX {@link Integer The screen X coordinate}
     * @param screenY {@link Integer The screen Y coordinate}
     */
    @Override
    protected void renderBg(final @NotNull PoseStack poseStack, final float partialTicks, final int screenX, final int screenY) {
        this.renderBackground(poseStack);
        RenderSystem.setShaderTexture(0, BACKGROUND_LOCATION);
        final int x = this.leftPos;
        final int y = this.topPos;
        blit(poseStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
        if (this.menu.isLit()) {
            final int litProgress = Mth.clamp((28 * this.menu.getLitProgress() + 27 - 1) / 27, 0, 28);
            if (litProgress > 0) {
                blit(poseStack, x + 74, y + 69, 176, 0, litProgress, 5);
            }
        }

        final int forgingProgress = this.menu.getForgingProgress();
        blit(poseStack, x + 101, y + 21, 176, 5, forgingProgress + 1, 16);
    }

}