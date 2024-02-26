package org.mineworld.helper;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Helper methods for {@link Item Items}
 */
public final class ItemHelper {

    /**
     * Damage an {@link ItemStack Item Stack} by 1
     *
     * @param itemStack {@link ItemStack The Item Stack to damage}
     * @param player {@link Player The player using the Item Stack}
     */
    public static void hurt(final ItemStack itemStack, final Player player) {
        hurt(itemStack, player, 1, null, null);
    }

    /**
     * Damage an {@link ItemStack Item Stack} by 1
     *
     * @param itemStack {@link ItemStack The Item Stack to damage}
     * @param player {@link Player The player using the Item Stack}
     * @param level {@link Level The level reference}
     * @param hand {@link InteractionHand The hand the Player used the Item with}
     */
    public static void hurt(final ItemStack itemStack, final Player player, final Level level, final InteractionHand hand) {
        hurt(itemStack, player, 1, level, hand);
    }

    /**
     * Damage an {@link ItemStack Item Stack} by 1
     *
     * @param itemStack {@link ItemStack The Item Stack to damage}
     */
    public static void hurt(final ItemStack itemStack) {
        hurt(itemStack, null, 1, null, null);
    }

    /**
     * Damage an {@link ItemStack Item Stack}
     *
     * @param itemStack {@link ItemStack The Item Stack to damage}
     * @param player {@link Player The player using the Item Stack}
     * @param amount {@link Integer The amount of damage to apply}
     * @param level {@link Level The level reference}
     * @param hand {@link InteractionHand The hand the Player used the Item with}
     */
    public static void hurt(final ItemStack itemStack, final Player player, final int amount, final Level level, final InteractionHand hand) {
        if(player == null) {
            return;
        }
        if(level != null && level.isClientSide() && hand != null) {
            player.swing(hand);
        }
        if(player.isCreative()) {
            return;
        }
        if(itemStack.isDamageableItem()) {
            itemStack.hurtAndBreak(amount, player, p -> p.broadcastBreakEvent(p.getUsedItemHand()));
        } else if(!player.isCreative()) {
            itemStack.shrink(amount);
        }
    }

    /**
     * Set a cooldown for an {@link Item item}
     *
     * @param player {@link Player The player using the Item}
     * @param item {@link Item The Item to set the cooldown to}
     * @param ticks {@link Integer How many ticks the Item should be set in cooldown}
     */
    public static void setCooldown(final Player player, final Item item, final int ticks) {
        player.getCooldowns().addCooldown(item, ticks);
    }

}