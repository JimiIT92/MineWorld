package org.mineworld.helper;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.armortrim.TrimMaterial;
import org.mineworld.MineWorld;

/**
 * Helper class for {@link Component Text Components}
 */
public final class ComponentHelper {

    /**
     * Get the {@link MutableComponent Mutable Translatable Component} for a {@link CreativeModeTab Creative Mode Tab}
     *
     * @param name {@link String The Creative Mode Tab Name}
     * @return The {@link CreativeModeTab Creative Mode Tab} {@link MutableComponent Mutable Translatable Component}
     */
    public static MutableComponent tab(final String name) {
        return get("itemGroup", name);
    }

    /**
     * Get a {@link MineWorld MineWorld} {@link MutableComponent Mutable Translatable Component}
     *
     * @param prefix {@link String The Component translation key prefix}
     * @param suffix {@link String The Component translation key suffix}
     * @param args {@link Object The Component translation arguments}
     * @return {@link MutableComponent The Mutable Translatable Component}
     */
    private static MutableComponent get(final String prefix, final String suffix, final Object... args) {
        return get(prefix + "." + MineWorld.MOD_ID + "." + suffix, args);
    }

    /**
     * Get a {@link MutableComponent Mutable Translatable Component}
     *
     * @param key {@link String The Component translation key}
     * @param args {@link Object The Component translation arguments}
     * @return {@link MutableComponent The Mutable Translatable Component}
     */
    public static MutableComponent get(final String key, final Object... args) {
        return Component.translatable(key, args);
    }

    /**
     * Get the {@link MutableComponent Mutable Translatable Component} for a {@link TrimMaterial Trim Material}
     *
     * @param trimMaterialKey {@link ResourceKey<TrimMaterial> The Trim Material Resource Key}
     * @return {@link MutableComponent The Trim Material Mutable Translatable Component}
     */
    public static MutableComponent trimMaterial(final ResourceKey<TrimMaterial> trimMaterialKey) {
        return get(Util.makeDescriptionId("trim_material", trimMaterialKey.location()));
    }

}