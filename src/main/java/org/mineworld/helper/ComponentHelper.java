package org.mineworld.helper;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.Container;
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
     * @param name {@link String The Creative Mode Tab name}
     * @return The {@link CreativeModeTab Creative Mode Tab} {@link MutableComponent Mutable Translatable Component}
     */
    public static MutableComponent tab(final String name) {
        return get("itemGroup", name);
    }

    /**
     * Get the {@link MutableComponent Mutable Translatable Component} for a {@link Container Container}
     *
     * @param name {@link String The Container name}
     * @return The {@link Container Container} {@link MutableComponent Mutable Translatable Component}
     */
    public static MutableComponent container(final String name) {
        return get("container", name);
    }

    /**
     * Get the {@link MutableComponent Mutable Translatable Component} for a Splash Text
     *
     * @param name {@link String The Splash Text}
     * @return The Splash Text {@link MutableComponent Mutable Translatable Component}
     */
    public static MutableComponent splashText(final String name, final Object... args) {
        return get("splash", name, args);
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

    /**
     * Get the {@link MutableComponent Mutable Translatable Component} for a mod update
     *
     * @param message {@link String The update message}
     * @param args {@link String The update message arguments}
     * @return {@link MutableComponent The mod update Mutable Translatable Component}
     */
    public static MutableComponent updateMessage(final String message, final Object... args) {
        return get("update", message, args);
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

}