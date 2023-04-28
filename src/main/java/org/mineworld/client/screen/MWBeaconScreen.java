package org.mineworld.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
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

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link BeaconScreen beacon screen}
 */
@OnlyIn(Dist.CLIENT)
public class MWBeaconScreen extends BeaconScreen {

    /**
     * {@link ResourceLocation The becon screen texture location}
     */
    private static final ResourceLocation BEACON_LOCATION = new ResourceLocation(MineWorld.MOD_ID, "textures/gui/container/beacon.png");

    /**
     * Constructor. Set the beacon screen properties
     * based on an existing {@link BeaconScreen beacon screen}
     *
     * @param beaconScreen {@link BeaconScreen The beacon screen}
     */
    public MWBeaconScreen(final BeaconScreen beaconScreen) {
        this(beaconScreen.getMenu(), Minecraft.getInstance().player.getInventory(), beaconScreen.getTitle());
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
     * @param poseStack {@link PoseStack The screen pose stack}
     * @param partialTicks {@link Float The client partial ticks}
     * @param screenX {@link Integer The screen X coordinate}
     * @param screenY {@link Integer The screen Y coordinate}
     */
    @Override
    protected void renderBg(final @NotNull PoseStack poseStack, final float partialTicks, int screenX, int screenY) {
        RenderSystem.setShaderTexture(0, BEACON_LOCATION);
        final int guiX = (this.width - this.imageWidth) / 2;
        final int guiY = (this.height - this.imageHeight) / 2;
        blit(poseStack, guiX, guiY, 0, 0, this.imageWidth, this.imageHeight);
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.0F, 100.0F);
        this.itemRenderer.renderAndDecorateItem(poseStack, ItemHelper.getDefaultStack(Items.IRON_INGOT), guiX + 40, guiY + 102);
        this.itemRenderer.renderAndDecorateItem(poseStack, ItemHelper.getDefaultStack(Items.GOLD_INGOT), guiX + 61, guiY + 102);
        this.itemRenderer.renderAndDecorateItem(poseStack, ItemHelper.getDefaultStack(Items.DIAMOND), guiX + 82, guiY + 102);
        this.itemRenderer.renderAndDecorateItem(poseStack, ItemHelper.getDefaultStack(Items.EMERALD), guiX + 103, guiY + 102);
        this.itemRenderer.renderAndDecorateItem(poseStack, ItemHelper.getDefaultStack(MWItems.RUBY), guiX + 49, guiY + 119);
        this.itemRenderer.renderAndDecorateItem(poseStack, ItemHelper.getDefaultStack(MWItems.SAPPHIRE), guiX + 70, guiY + 119);
        this.itemRenderer.renderAndDecorateItem(poseStack, ItemHelper.getDefaultStack(Items.NETHERITE_INGOT), guiX + 91, guiY + 119);
        poseStack.popPose();
    }

}