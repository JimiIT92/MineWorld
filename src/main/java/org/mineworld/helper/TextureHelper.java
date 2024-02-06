package org.mineworld.helper;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.TrappedChestBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.WoodType;
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
        return entity("horse/armor/horse_armor_" + ResourceHelper.armorName(armorMaterial));
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
     * Get an {@link ResourceLocation Container Texture location}
     *
     * @param name {@link String The Container name}
     * @return {@link ResourceLocation The Container Texture location}
     */
    public static ResourceLocation container(final String name) {
        return texture("gui/container/" + name);
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
     * Get an {@link ResourceLocation Armor Texture location}
     *
     * @param armorMaterial {@link ArmorMaterial The Armor material}
     * @param slot {@link EquipmentSlot The Armor Slot}
     * @return {@link ResourceLocation The Armor Texture location}
     */
    public static ResourceLocation armor(final ArmorMaterial armorMaterial, final EquipmentSlot slot) {
        return armor(armorMaterial, slot.getName());
    }

    /**
     * Get an {@link ResourceLocation Armor Texture location}
     *
     * @param armorMaterial {@link ArmorMaterial The Armor material}
     * @param slotName {@link String The Armor Slot name}
     * @return {@link ResourceLocation The Armor Texture location}
     */
    public static ResourceLocation armor(final ArmorMaterial armorMaterial, final String slotName) {
        return texture("models/armor/" + ResourceHelper.armorName(armorMaterial, slotName));
    }

    /**
     * Get an {@link ResourceLocation Armor Texture location}
     *
     * @param armorMaterial {@link ArmorMaterial The Armor material}
     * @return {@link ResourceLocation The Armor Texture location}
     */
    public static ResourceLocation hat(final ArmorMaterial armorMaterial) {
        return armor(armorMaterial, "hat");
    }

    /**
     * Get a {@link ResourceLocation Texture location}
     *
     * @param modId {@link String The Mod Id}
     * @param path {@link String The Texture path}
     * @return {@link ResourceLocation The Texture location}
     */
    public static ResourceLocation texture(final String modId, final String path) {
        return ResourceHelper.resourceLocation(modId, "textures/" + path + ".png");
    }

    /**
     * Get a {@link ResourceLocation Texture location}
     *
     * @param path {@link String The Texture path}
     * @return {@link ResourceLocation The Texture location}
     */
    private static ResourceLocation texture(final String path) {
        return texture(MineWorld.MOD_ID, path);
    }

}