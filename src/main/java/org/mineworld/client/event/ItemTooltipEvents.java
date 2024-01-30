package org.mineworld.client.event;

import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.core.MWArmorMaterials;

import java.util.List;

/**
 * Handle all events for {@link Tooltip Item Tooltips}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, value = Dist.CLIENT)
public final class ItemTooltipEvents {

    /**
     * Hide the default tooltips for cosmetic armor items
     *
     * @param event {@link ItemTooltipEvent The Item Tooltip Event}
     */
    @SubscribeEvent
    public static void onItemTooltip(final ItemTooltipEvent event) {
        if(!event.isCanceled() && event.getItemStack().getItem() instanceof ArmorItem armorItem && MWArmorMaterials.isCosmetic(armorItem.getMaterial())) {
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