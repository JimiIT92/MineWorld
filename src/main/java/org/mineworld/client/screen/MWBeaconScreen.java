package org.mineworld.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
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
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.KeyHelper;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link BeaconScreen beacon screen}
 */
@OnlyIn(Dist.CLIENT)
public class MWBeaconScreen extends BeaconScreen {

    /**
     * {@link ResourceLocation The becon screen texture location}
     */
    private static final ResourceLocation BEACON_LOCATION = KeyHelper.container("beacon");

    /**
     * Constructor. Set the beacon screen properties
     * based on an existing {@link BeaconScreen beacon screen}
     *
     * @param beaconScreen {@link BeaconScreen The beacon screen}
     */
    public MWBeaconScreen(final BeaconScreen beaconScreen) {
        this(beaconScreen.getMenu(), Minecraft.getInstance().player != null ? Minecraft.getInstance().player.getInventory() : null, beaconScreen.getTitle());
    }

    /**
     * Constructor. Set the beacon screen properties
     *
     * @param beaconMenu {@link BeaconMenu The beacon menu}
     * @param inventory {@link Inventory The beacon inventory}
     * @param title {@link Component The beacon screen title component}
     */
    public MWBeaconScreen(final BeaconMenu beaconMenu, final Inventory inventory, final Component title) {
        super(beaconMenu, inventory, title);
    }

    /**
     * Render the beacon screen background
     *
     * @param guiGraphics {@link GuiGraphics The screen GUI graphics}
     * @param partialTicks {@link Float The client partial ticks}
     * @param screenX {@link Integer The screen X coordinate}
     * @param screenY {@link Integer The screen Y coordinate}
     */
    @Override
    protected void renderBg(final @NotNull GuiGraphics guiGraphics, final float partialTicks, int screenX, int screenY) {
        RenderSystem.setShaderTexture(0, BEACON_LOCATION);
        final int guiX = (this.width - this.imageWidth) / 2;
        final int guiY = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(BEACON_LOCATION, guiX, guiY, 0, 0, this.imageWidth, this.imageHeight);
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0.0F, 0.0F, 100.0F);
        guiGraphics.renderItem(ItemHelper.getDefaultStack(Items.IRON_INGOT), guiX + 40, guiY + 102);
        guiGraphics.renderItem(ItemHelper.getDefaultStack(Items.GOLD_INGOT), guiX + 61, guiY + 102);
        guiGraphics.renderItem(ItemHelper.getDefaultStack(Items.DIAMOND), guiX + 82, guiY + 102);
        guiGraphics.renderItem(ItemHelper.getDefaultStack(Items.EMERALD), guiX + 103, guiY + 102);
        guiGraphics.renderItem(ItemHelper.getDefaultStack(MWItems.RUBY), guiX + 49, guiY + 119);
        guiGraphics.renderItem(ItemHelper.getDefaultStack(MWItems.SAPPHIRE), guiX + 70, guiY + 119);
        guiGraphics.renderItem(ItemHelper.getDefaultStack(Items.NETHERITE_INGOT), guiX + 91, guiY + 119);
        guiGraphics.pose().popPose();
    }

}