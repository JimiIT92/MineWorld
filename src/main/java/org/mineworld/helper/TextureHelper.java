package org.mineworld.helper;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.HorseArmorItem;
import org.mineworld.MineWorld;

/**
 * Helper methods for Textures
 */
public final class TextureHelper {

    /**
     * Get an {@link HorseArmorItem Horse Armor} {@link ResourceLocation Texture location}
     *
     * @param armorMaterial {@link ArmorMaterial The Armor material}
     * @return {@link ResourceLocation The Horse Armor Texture location}
     */
    public static ResourceLocation horseArmor(final ArmorMaterial armorMaterial) {
        return texture("entity/horse/armor/horse_armor_" + ResourceHelper.armorMaterialName(armorMaterial));
    }

    /**
     * Get a {@link ResourceLocation Texture location}
     *
     * @param path {@link String The Texture path}
     * @return {@link ResourceLocation The Texture location}
     */
    private static ResourceLocation texture(final String path) {
        return new ResourceLocation(MineWorld.MOD_ID, "textures/" + path + ".png");
    }

}