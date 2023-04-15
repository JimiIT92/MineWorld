package org.mineworld.helper;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.mineworld.MineWorld;

/**
 * Helper methods for handling {@link ResourceKey resource keys}
 */
public final class KeyHelper {

    /**
     * Register a {@link ResourceKey resource key} for a {@link ConfiguredFeature configured feature}
     *
     * @param name {@link String The configured feature name}
     * @return {@link ResourceKey<ConfiguredFeature> Configured feature resource key}
     */
    public static ResourceKey<ConfiguredFeature<?, ?>> registerConfiguredFeatureKey(final String name) {
        return register(Registries.CONFIGURED_FEATURE, name);
    }

    /**
     * Register a {@link ResourceKey resource key} for a {@link PlacedFeature placed feature}
     *
     * @param name {@link String The placed feature name}
     * @return {@link ResourceKey<PlacedFeature> Configured feature resource key}
     */
    public static ResourceKey<PlacedFeature> registerPlacedFeatureKey(final String name) {
        return register(Registries.PLACED_FEATURE, name + "_placed");
    }

    /**
     * Register a {@link ResourceKey resource key} for a {@link TrimMaterial trim material}
     *
     * @param name {@link String The trim material name}
     * @return {@link ResourceKey<TrimMaterial> Trim material resource key}
     */
    public static ResourceKey<TrimMaterial> registerTrimMaterialKey(final String name) {
        return register(Registries.TRIM_MATERIAL, name);
    }

    /**
     * Register a {@link ResourceKey resource key}
     *
     * @param registry {@link Registry<T> The resource key registry}
     * @param name {@link String The resource key name}
     * @return {@link ResourceKey<T> The resource key}
     * @param <T> The resource key type
     */
    public static <T> ResourceKey<T> register(ResourceKey<Registry<T>> registry, String name) {
        return ResourceKey.create(registry, new ResourceLocation(MineWorld.MODID, name));
    }

}