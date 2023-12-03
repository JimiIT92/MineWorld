package org.mineworld.event;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ItemFrame;
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
 * Handle interactions with {@link Entity entities}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class EntityInteractEventListener {

    /**
     * Handle the {@link Player player} interaction with an {@link Entity entity}
     *
     * @param event {@link PlayerInteractEvent.EntityInteractSpecific Entity interact specific event}
     */
    @SubscribeEvent
    public static void onEntityInteract(final PlayerInteractEvent.EntityInteractSpecific event) {
        if(!event.isCanceled()) {
            final Player player = event.getEntity();
            final Entity target = event.getTarget();
            final ItemStack itemStack = event.getItemStack();
            final Item item = itemStack.getItem();
            if(target instanceof ItemFrame itemFrame && item instanceof ShearsItem) {
                handleItemFrameInteraction(event, player, itemFrame, itemStack);
                return;
            }
            if(target instanceof Shulker shulker) {
                if(item instanceof DyeItem) {
                    handleShulkerRecolorInteraction(event, player, shulker, itemStack);
                }
                else if(itemStack.is(Items.WATER_BUCKET)) {
                    handleShulkerDecolorInteraction(event, player, shulker, itemStack);
                }
            }
        }
    }

    /**
     * Cancel the {@link PlayerInteractEvent.EntityInteractSpecific event} and damage the {@link ItemStack used id stack}
     *
     * @param event {@link PlayerInteractEvent.EntityInteractSpecific The entity interact specific event}
     * @param result {@link InteractionResult The interaction result}
     * @param player {@link Player The player interacting with the entity}
     * @param itemStack {@link ItemStack The ItemStack used to interact}
     */
    private static void cancelEvent(final PlayerInteractEvent.EntityInteractSpecific event, final InteractionResult result, final Player player, final ItemStack itemStack) {
        ItemHelper.hurt(itemStack, player);
        event.setCanceled(true);
        event.setCancellationResult(result);
    }

    /**
     * Toggle invisibility on an {@link ItemFrame id frame} when shift-right clicked with some shears
     *
     * @param event {@link PlayerInteractEvent.EntityInteractSpecific The entity interact specific event}
     * @param player {@link Player The player interacting with the id frame}
     * @param itemFrame {@link ItemFrame The ItemFrame being interacted}
     * @param itemStack {@link ItemStack The ItemStack used to interact}
     */
    private static void handleItemFrameInteraction(final PlayerInteractEvent.EntityInteractSpecific event, final Player player, final ItemFrame itemFrame, final ItemStack itemStack) {
        if(!player.isShiftKeyDown()) {
            return;
        }
        final boolean invisible = !itemFrame.isInvisible();
        itemFrame.setInvisible(invisible);
        player.playSound(invisible ? SoundEvents.ITEM_FRAME_REMOVE_ITEM : SoundEvents.ITEM_FRAME_ADD_ITEM, 0.75F, 1.0F);
        cancelEvent(event, InteractionResult.CONSUME, player, itemStack);
    }

    /**
     * Recolor a {@link Shulker shulker} when right-clicked with a dye
     *
     * @param event {@link PlayerInteractEvent.EntityInteractSpecific The entity interact specific event}
     * @param player {@link Player The player interacting with the id frame}
     * @param shulker {@link Shulker The shulker being recolored}
     * @param itemStack {@link ItemStack The ItemStack used to interact}
     */
    private static void handleShulkerRecolorInteraction(final PlayerInteractEvent.EntityInteractSpecific event, final Player player, final Shulker shulker, final ItemStack itemStack) {
        final DyeColor color = DyeColor.getColor(itemStack);
        if(shulker.getVariant().isEmpty() || !shulker.getVariant().get().equals(color)) {
            shulker.setVariant(Optional.of(color));
            player.playSound(SoundEvents.DYE_USE, 0.75F, 1.0F);
            cancelEvent(event, InteractionResult.CONSUME, player, itemStack);
        }
    }

    /**
     * Decolor a {@link Shulker shulker} when right-clicked with a water bucket
     *
     * @param event {@link PlayerInteractEvent.EntityInteractSpecific The entity interact specific event}
     * @param player {@link Player The player interacting with the id frame}
     * @param shulker {@link Shulker The shulker being decolored}
     * @param itemStack {@link ItemStack The ItemStack used to interact}
     */
    private static void handleShulkerDecolorInteraction(final PlayerInteractEvent.EntityInteractSpecific event, final Player player, final Shulker shulker, final ItemStack itemStack) {
        if(shulker.getVariant().isPresent()) {
            shulker.setVariant(Optional.empty());
            player.playSound(SoundEvents.BUCKET_EMPTY, 0.75F, 1.0F);
            cancelEvent(event, InteractionResult.CONSUME, player, itemStack);
            player.addItem(ItemHelper.getDefaultStack(Items.BUCKET));
        }
    }

}