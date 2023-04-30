package org.mineworld.jei;

import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.ModIds;
import net.minecraft.resources.ResourceLocation;

/**
 * Helper methods for the {@link JeiPlugin JEI plugin integration}
 */
public final class JEIHelper {

    /**
     * Get the {@link ResourceLocation vanilla textures location}
     *
     * @return {@link ResourceLocation The JEI vanilla textures location}
     */
    public static ResourceLocation getVanillaTextureLocation() {
        return new ResourceLocation(ModIds.JEI_ID, "textures/jei/gui/gui_vanilla.png");
    }

}