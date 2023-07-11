package org.mineworld.helper;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

/**
 * Helper methods for {@link DamageSource damage sources}
 */
public class DamageHelper {

    /**
     * Get a {@link DamageSource damage source} based on the {@link ResourceKey<DamageType> damage type resource key}
     *
     * @param level {@link Level The level reference}
     * @param damageType {@link ResourceKey<DamageType> The damage type resource key}
     * @return {@link DamageSource The damage source}
     */
    public static DamageSource source(Level level, ResourceKey<DamageType> damageType) {
        return new DamageSource(getRegistry(level).getHolderOrThrow(damageType));
    }

    /**
     * Get the {@link Registry<DamageType> damage type registry}
     *
     * @param level {@link Level The level reference}
     * @return {@link Registry<DamageType> The damage type registry}
     */
    private static Registry<DamageType> getRegistry(Level level) {
        return level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE);
    }

}