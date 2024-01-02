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

}