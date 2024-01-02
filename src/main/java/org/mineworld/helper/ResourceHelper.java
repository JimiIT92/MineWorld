package org.mineworld.helper;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.mineworld.MineWorld;

/**
 * Helper class for {@link ResourceLocation Resource Locations} and {@link ResourceKey Resource Keys}
 */
public final class ResourceHelper {

    /**
     * Get a {@link MineWorld MineWorld} {@link ResourceLocation Resource Location}
     *
     * @param name {@link String The resource name}
     * @return {@link ResourceLocation The Resource Location}
     */
    public static ResourceLocation resourceLocation(final String name) {
        return new ResourceLocation(MineWorld.MOD_ID, name);
    }

    /**
     * Get a {@link String string representation} of a {@link MineWorld MineWorld} {@link ResourceLocation Resource Location}
     *
     * @param name {@link String The resource name}
     * @return {@link String The Resource Location string}
     */
    public static String stringLocation(final String name) {
        return resourceLocation(name).toString();
    }

    /**
     * Check if a {@link ResourceKey Resource Key} is from {@link MineWorld MineWorld}
     *
     * @param resourceKey {@link ResourceKey The Resource Key to check}
     * @return {@link Boolean#TRUE True} if the namespace starts with the {@link MineWorld#MOD_ID MineWorld Mod ID}
     */
    public static boolean isFromMineWorld(final ResourceKey<?> resourceKey) {
        return resourceKey.location().getNamespace().equals(MineWorld.MOD_ID);
    }

}