package org.mineworld.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.BeaconScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.BeaconMenu;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWItems;
import org.mineworld.helper.TextureHelper;

/**
 * {@link MineWorld MineWorld} {@link BeaconScreen Beacon Screen}
 */
@OnlyIn(Dist.CLIENT)
public class MWBeaconScreen extends BeaconScreen {

    /**
     * {@link ResourceLocation The Beacon Screen Texture Location}
     */
    private static final ResourceLocation BEACON_LOCATION = TextureHelper.container("beacon");

    /**
     * Constructor. Set the {@link AbstractContainerScreen Screen Properties}
     *
     * @param beaconScreen {@link BeaconScreen The Beacon Screen}
     */
    public MWBeaconScreen(final BeaconScreen beaconScreen) {
        this(beaconScreen.getMenu(), Minecraft.getInstance().player != null ? Minecraft.getInstance().player.getInventory() : null, beaconScreen.getTitle());
    }

    /**
     * Constructor. Set the {@link AbstractContainerScreen Screen Properties}
     *
     * @param menu {@link BeaconMenu The Beacon menu}
     * @param inventory {@link Inventory The Screen inventory}
     * @param title {@link Component The Screen Title Component}
     */
    public MWBeaconScreen(final BeaconMenu menu, final Inventory inventory, final Component title) {
        super(menu, inventory, title);
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
    protected void renderBg(final @NotNull GuiGraphics guiGraphics, final float partialTicks, int screenX, int screenY) {
        RenderSystem.setShaderTexture(0, BEACON_LOCATION);
        final int guiX = (this.width - this.imageWidth) / 2;
        final int guiY = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(BEACON_LOCATION, guiX, guiY, 0, 0, this.imageWidth, this.imageHeight);
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0.0F, 0.0F, 100.0F);
        guiGraphics.renderItem(Items.IRON_INGOT.getDefaultInstance(), guiX + 40, guiY + 102);
        guiGraphics.renderItem(Items.GOLD_INGOT.getDefaultInstance(), guiX + 61, guiY + 102);
        guiGraphics.renderItem(Items.DIAMOND.getDefaultInstance(), guiX + 82, guiY + 102);
        guiGraphics.renderItem(Items.EMERALD.getDefaultInstance(), guiX + 103, guiY + 102);
        guiGraphics.renderItem(MWItems.RUBY.get().getDefaultInstance(), guiX + 49, guiY + 119);
        guiGraphics.renderItem(MWItems.SAPPHIRE.get().getDefaultInstance(), guiX + 70, guiY + 119);
        guiGraphics.renderItem(Items.NETHERITE_INGOT.getDefaultInstance(), guiX + 91, guiY + 119);
        guiGraphics.pose().popPose();
    }

}