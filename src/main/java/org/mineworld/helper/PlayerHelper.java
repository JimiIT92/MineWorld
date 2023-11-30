package org.mineworld.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper methods for {@link Player players}
 */
public final class PlayerHelper {

    /**
     * Get the {@link Entity entities leashed} to a {@link Player player}
     *
     * @param player {@link Player The player}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link List<Entity> List of leashed entities, if any}
     */
    public static List<? extends Entity> getLeashedEntities(final Player player, final Level level, final BlockPos blockPos) {
        final double distance = 7.0D;
        final double x = blockPos.getX();
        final double y = blockPos.getY();
        final double z = blockPos.getZ();

        return level.getEntitiesOfClass(Mob.class, new AABB(x - distance, y - distance, z - distance, x + distance, y + distance, z + distance))
                .stream().filter(entity -> entity.getLeashHolder() != null && entity.getLeashHolder().is(player)).collect(Collectors.toList());
    }

    /**
     * Play a {@link SoundEvent sound} to the {@link Player player}
     *
     * @param player {@link Player The player to play the sound}
     * @param sound {@link SoundEvent The sound to play}
     */
    public static void playSound(final Player player, final SoundEvent sound) {
        player.level().playSound(player, player.getOnPos(), sound, SoundSource.PLAYERS, 1.0F, 1.0F);
    }

    /**
     * Get an {@link ItemStack armor slot Item Stack}
     *
     * @param player {@link Player The player}
     * @param slot {@link EquipmentSlot The equipment slot}
     * @return {@link ItemStack The armor item}
     */
    public static ItemStack getArmorSlotItem(final Player player, final EquipmentSlot slot) {
        if(!slot.isArmor()) {
            return ItemStack.EMPTY;
        }
        return player.getInventory().getArmor(slot.getIndex());
    }

    /**
     * Teleport a {@link Player player} to the given {@link BlockPos location}
     *
     * @param player {@link Player The player}
     * @param pos {@link BlockPos The location to teleport}
     */
    public static void teleport(final Player player, final BlockPos pos) {
        teleport(player, pos, null);
    }

    /**
     * Teleport a {@link Player player} to the given {@link BlockPos location}
     * and play a {@link SoundEvent sound}
     *
     * @param player {@link Player The player}
     * @param pos {@link BlockPos The location to teleport}
     * @param teleportSound {@link SoundEvent The sound to play after the teleport}
     */
    public static void teleport(final Player player, final @NotNull BlockPos pos, final SoundEvent teleportSound) {
        player.teleportTo(pos.getX(), pos.getY(), pos.getZ());
        if(teleportSound != null) {
            playSound(player, teleportSound);
        }
    }

}