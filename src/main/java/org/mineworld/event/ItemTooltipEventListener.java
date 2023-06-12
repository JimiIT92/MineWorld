package org.mineworld.event;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.item.MWArmorMaterials;

import java.util.List;

/**
 * Event listener for the {@link ItemTooltipEvent item tooltip event}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, value = Dist.CLIENT)
public final class ItemTooltipEventListener {

    /**
     * Hide the default tooltips for cosmetic armor items
     *
     * @param event {@link ItemTooltipEvent The item tooltip event}
     */
    @SubscribeEvent
    public static void onItemTooltip(final ItemTooltipEvent event) {
        if(event.getItemStack().getItem() instanceof ArmorItem armorItem && MWArmorMaterials.isCosmetic(armorItem.getMaterial())) {
            final List<Component> tooltips = event.getToolTip();
            final boolean isAdvanced = event.getFlags().isAdvanced();
            final Component itemName = tooltips.get(0);
            final Component itemId = isAdvanced ? tooltips.get(tooltips.size() - 1) : null;
            tooltips.clear();
            tooltips.add(itemName);
            if(isAdvanced && itemId != null) {
                tooltips.add(itemId);
            }
        }
    }

}