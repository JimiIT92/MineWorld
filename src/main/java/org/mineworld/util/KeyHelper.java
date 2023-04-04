package org.mineworld.util;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.mineworld.MineWorld;

/**
 * Helper methods for handling {@link ResourceKey resource keys}
 */
public final class KeyHelper {

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
