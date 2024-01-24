package org.mineworld.helper;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.TrappedChestBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.WoodType;

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
        return entity("horse/armor/horse_armor_" + ResourceHelper.armorMaterialName(armorMaterial));
    }

    /**
     * Get a {@link ChestBlockEntity Chest} {@link ResourceLocation Texture location}
     *
     * @param woodType {@link WoodType The Chest Wood Type}
     * @param chestType {@link String The Chest Type}
     * @param isTrappedChest If the Chest is a {@link TrappedChestBlockEntity Trapped Chest}
     * @return {@link ResourceLocation The Chest Texture location}
     */
    public static ResourceLocation chest(final WoodType woodType, final ChestType chestType, final boolean isTrappedChest) {
        return chest(ResourceHelper.woodName(woodType) + "/" + (isTrappedChest ? "trapped" : "normal") + ResourceHelper.chestTypeName(chestType));
    }

    /**
     * Get a {@link ChestBlockEntity Chest} {@link ResourceLocation Texture location}
     *
     * @param texturePath {@link String The Texture Path}
     * @return {@link ResourceLocation The Chest Texture location}
     */
    public static ResourceLocation chest(final String texturePath) {
        return ResourceHelper.resourceLocation("entity/chest/" + texturePath);
    }

    /**
     * Get an {@link ResourceLocation Entity Texture location}
     *
     * @param texturePath {@link String The Texture Path}
     * @return {@link ResourceLocation The Entity Texture location}
     */
    public static ResourceLocation entity(final String texturePath) {
        return texture("entity/" + texturePath);
    }

    /**
     * Get a {@link ResourceLocation Texture location}
     *
     * @param path {@link String The Texture path}
     * @return {@link ResourceLocation The Texture location}
     */
    private static ResourceLocation texture(final String path) {
        return ResourceHelper.resourceLocation("textures/" + path + ".png");
    }

}