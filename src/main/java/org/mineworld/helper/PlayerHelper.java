package org.mineworld.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
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

}