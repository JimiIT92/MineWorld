package org.mineworld.event;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.helper.ItemHelper;

/**
 * Handle all events for the {@link ItemFrame Item Frame}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class ItemFrameEvents {

    /**
     * Toggle the {@link ItemFrame Item Frame} invisibility when right clicking with the {@link Items#SHEARS Shears}
     *
     * @param event {@link PlayerInteractEvent.EntityInteractSpecific The Entity Interact Specific Event}
     */
    @SubscribeEvent
    public static void onEntityInteractSpecific(final PlayerInteractEvent.EntityInteractSpecific event) {
        if(!event.isCanceled()) {
            final Entity target = event.getTarget();
            final ItemStack itemStack = event.getItemStack();
            final Item item = itemStack.getItem();
            if(target instanceof ItemFrame itemFrame && item instanceof ShearsItem) {
                final Player player = event.getEntity();
                if(!player.isShiftKeyDown()) {
                    return;
                }
                final boolean invisible = !itemFrame.isInvisible();
                itemFrame.setInvisible(invisible);
                player.playSound(invisible ? SoundEvents.ITEM_FRAME_REMOVE_ITEM : SoundEvents.ITEM_FRAME_ADD_ITEM, 0.75F, 1.0F);
                ItemHelper.hurt(itemStack, player);
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.CONSUME);
            }
        }
    }

}