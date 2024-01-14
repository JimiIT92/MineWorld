package org.mineworld.helper;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

/**
 * Helper methods for {@link DamageSource Damage Sources}
 */
public final class DamageHelper {

    /**
     * Get a {@link DamageSource Damage Source} based on the {@link ResourceKey<DamageType> Damage Type Resource Key}
     *
     * @param level {@link Level The level reference}
     * @param damageType {@link ResourceKey<DamageType> The Damage Type Resource Key}
     * @return {@link DamageSource The Damage Source}
     */
    public static DamageSource source(final Level level, final ResourceKey<DamageType> damageType) {
        return new DamageSource(getRegistry(level).getHolderOrThrow(damageType));
    }

    /**
     * Get the {@link Registry<DamageType> Damage Type Registry}
     *
     * @param level {@link Level The level reference}
     * @return {@link Registry<DamageType> The Damage Type Registry}
     */
    private static Registry<DamageType> getRegistry(final Level level) {
        return level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE);
    }

}