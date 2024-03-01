package org.mineworld.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * Helper class for {@link Player Players}
 */
public final class PlayerHelper {

    /**
     * Get an {@link ItemStack Armor Slot Item Stack}
     *
     * @param player {@link Player The Player}
     * @param slot {@link EquipmentSlot The equipment slot}
     * @return {@link ItemStack The Armor Slot Item Stack}
     */
    public static ItemStack getArmorSlotItem(final Player player, final EquipmentSlot slot) {
        if(!slot.isArmor()) {
            return ItemStack.EMPTY;
        }
        return player.getInventory().getArmor(slot.getIndex());
    }

    /**
     * Teleport the {@link Player Player} to the given {@link BlockPos Location}
     *
     * @param player {@link Player The Player to teleport}
     * @param blockPos {@link BlockPos The Location to teleport}
     */
    public static void teleport(final Player player, final BlockPos blockPos) {
        player.teleportTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    /**
     * Add an {@link ItemStack Item Stack} to the {@link Player Player} {@link Inventory Inventory}
     *
     * @param player {@link Player The Player}
     * @param itemStack {@link ItemStack The Item Stack to add}
     */
    public static void addItem(final Player player, final ItemStack itemStack) {
        final int selectedSlot = player.getInventory().selected;
        if(player.getInventory().getItem(selectedSlot).isEmpty()) {
            addItem(player, selectedSlot, itemStack);
        } else {
            player.addItem(itemStack);
        }
    }

    /**
     * Add an {@link ItemStack Item Stack} to the {@link Player Player} {@link Inventory Inventory}
     *
     * @param player {@link Player The Player}
     * @param slot {@link Integer The inventory slot}
     * @param itemStack {@link ItemStack The Item Stack to add}
     */
    public static void addItem(final Player player, final int slot, final ItemStack itemStack) {
        player.getInventory().add(slot, itemStack);
    }

}