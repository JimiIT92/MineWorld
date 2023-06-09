package org.mineworld.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

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

}