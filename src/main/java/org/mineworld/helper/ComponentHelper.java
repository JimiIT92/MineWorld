package org.mineworld.helper;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.armortrim.TrimMaterial;
import org.mineworld.MineWorld;

/**
 * Helper methods for {@link Component components}
 */
public final class ComponentHelper {

    /**
     * Get the {@link Component component} for a container name
     *
     * @param name {@link String The container name}
     * @return {@link MutableComponent The container name component}
     */
    public static MutableComponent container(final String name) {
        return get("container", name);
    }

    /**
     * Get the {@link Component component} for a creative tab name
     *
     * @param name {@link String The creative tab name}
     * @return {@link MutableComponent The creative tab name component}
     */
    public static MutableComponent itemGroup(final String name) {
        return get("itemGroup", name);
    }

    /**
     * Get the {@link Component component} for a splash text
     *
     * @param name {@link String The splash text}
     * @param args {@link Object The translation arguments}
     * @return {@link MutableComponent The splash text component}
     */
    public static MutableComponent splashText(final String name, final Object... args) {
        return get("splash", name);
    }

    /**
     * Get the {@link Component component} for an update message
     *
     * @param name {@link String The update message}
     * @param args {@link Object The translation arguments}
     * @return {@link MutableComponent The update message component}
     */
    public static MutableComponent updateMessage(final String name, final Object... args) {
        return get("update", name, args);
    }

    /**
     * Get the {@link Component component} for a smithing template description
     *
     * @param name {@link String The smithing template name}
     * @param suffix {@link String The smithing template description part}
     * @return {@link MutableComponent The smithing template description component}
     */
    public static MutableComponent smithingTemplateDescription(final String name, final String suffix) {
        return get(Util.makeDescriptionId("id", KeyHelper.location("smithing_template." + name + "." + suffix)));
    }

    /**
     * Get the {@link Component component} for a smithing template upgrade description
     *
     * @param name {@link String The smithing template name}
     * @return {@link MutableComponent The smithing template description component}
     */
    public static MutableComponent smithingTemplateUpgradeDescription(final String name) {
        return get(Util.makeDescriptionId("upgrade", KeyHelper.location(name)));
    }

    /**
     * Get the {@link Component component} for a trim material
     *
     * @param key {@link ResourceKey<TrimMaterial> The trim material resource key}
     * @return {@link MutableComponent The trim material description component}
     */
    public static MutableComponent trimMaterial(final ResourceKey<TrimMaterial> key) {
        return get(Util.makeDescriptionId("trim_material", key.location()));
    }

    /**
     * Get a {@link Component translatable component}
     *
     * @param prefix {@link String The component key prefix}
     * @param suffix {@link String The component key suffix}
     * @param args {@link Object The translation arguments}
     * @return {@link Component The translatable component}
     */
    private static MutableComponent get(final String prefix, final String suffix, final Object... args) {
        return get(prefix + "." + MineWorld.MOD_ID + "." + suffix, args);
    }

    /**
     * Get a {@link Component translatable component}
     *
     * @param key {@link String The component translation key}
     * @param args {@link Object The translation arguments}
     * @return {@link Component The translatable component}
     */
    public static MutableComponent get(final String key, final Object... args) {
        return Component.translatable(key, args);
    }
}