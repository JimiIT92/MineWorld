package org.mineworld.event;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.helper.ItemHelper;

import java.util.Optional;

/**
 * Handle all events for {@link Shulker Shulkers}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class ShulkerEvents {

    /**
     * Change the {@link Shulker Shulker} color when right clicking with a {@link DyeItem Dye}
     *
     * @param event {@link PlayerInteractEvent.EntityInteractSpecific The Entity Interact Specific Event}
     */
    @SubscribeEvent
    public static void onEntityInteractSpecific(final PlayerInteractEvent.EntityInteractSpecific event) {
        if(!event.isCanceled()) {
            final Entity target = event.getTarget();
            if(target instanceof Shulker shulker) {
                final ItemStack itemStack = event.getItemStack();
                final Player player = event.getEntity();
                if(itemStack.getItem() instanceof DyeItem) {
                    final DyeColor color = DyeColor.getColor(itemStack);
                    if(shulker.getVariant().isEmpty() || !shulker.getVariant().get().equals(color)) {
                        shulker.setVariant(Optional.of(color));
                        player.playSound(SoundEvents.DYE_USE, 0.75F, 1.0F);
                        cancelEvent(event, InteractionResult.CONSUME, player, itemStack);
                    }
                }
                else if(itemStack.is(Items.WATER_BUCKET)) {
                    if(shulker.getVariant().isPresent()) {
                        shulker.setVariant(Optional.empty());
                        player.playSound(SoundEvents.BUCKET_EMPTY, 0.75F, 1.0F);
                        cancelEvent(event, InteractionResult.CONSUME, player, itemStack);
                        player.addItem(Items.BUCKET.getDefaultInstance());
                    }
                }
            }
        }
    }

    /**
     * Cancel the {@link PlayerInteractEvent.EntityInteractSpecific Entity Interact Specific Event} and
     * set the {@link InteractionResult Interaction Result}
     *
     * @param event {@link PlayerInteractEvent.EntityInteractSpecific The Entity Interact Specific Event}
     * @param result {@link InteractionResult The Interaction Result}
     * @param player {@link Player The Player cancelling the event}
     * @param itemStack {@link ItemStack The current Item Stack}
     */
    private static void cancelEvent(final PlayerInteractEvent.EntityInteractSpecific event, final InteractionResult result, final Player player, final ItemStack itemStack) {
        ItemHelper.hurt(itemStack, player);
        event.setCanceled(true);
        event.setCancellationResult(result);
    }

}