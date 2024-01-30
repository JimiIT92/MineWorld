package org.mineworld.helper;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import org.apache.commons.lang3.NotImplementedException;
import org.mineworld.MineWorld;
import org.mineworld.block.EtherealRuneBlock;
import org.mineworld.block.MWFireBlock;
import org.mineworld.core.MWColors;
import org.mineworld.core.MWDimensions;
import org.mineworld.core.MWItemTiers;
import org.mineworld.core.MWWoodTypes;
import org.mineworld.entity.block.MWPrimedTnt;

import java.util.Locale;

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
     * Parse a {@link String Resource String} into a {@link ResourceLocation Resource Location}
     *
     * @param resourceLocation {@link String The Resource Location String}
     * @return {@link ResourceLocation The Resource Location}
     */
    public static ResourceLocation parse(final String resourceLocation) {
        return ResourceLocation.tryParse(resourceLocation);
    }

    /**
     * Get the {@link String Resource Location Path}
     *
     * @param resourceLocation {@link ResourceLocation The Resource Location}
     * @return {@link String The Resource Location Path}
     */
    public static String path(final ResourceLocation resourceLocation) {
        return lower(resourceLocation.getPath());
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

    /**
     * Get the {@link MWPrimedTnt.Type TNT Type} name
     *
     * @param type {@link MWPrimedTnt.Type The TNT Type}
     * @return {@link String The TNT Type name}
     */
    public static String tntName(final MWPrimedTnt.Type type) {
        return lower(type.name()) + "_tnt";
    }

    /**
     * Get the {@link String Armor Material name}
     *
     * @param armorMaterial {@link ArmorMaterial The Armor Material}
     * @return {@link String The Armor Material name}
     */
    public static String armorName(final ArmorMaterial armorMaterial) {
        return path(parse(armorMaterial.getName()));
    }

    /**
     * Get the {@link String Armor Material name}
     *
     * @param armorMaterial {@link ArmorMaterial The Armor Material}
     * @param slot {@link EquipmentSlot The Armor Slot}
     * @return {@link String The Armor Material name}
     */
    public static String armorName(final ArmorMaterial armorMaterial, final EquipmentSlot slot) {
        return armorName(armorMaterial, slot.getName());
    }

    /**
     * Get the {@link String Armor Material name}
     *
     * @param armorMaterial {@link ArmorMaterial The Armor Material}
     * @param slotName {@link String The Armor Slot name}
     * @return {@link String The Armor Material name}
     */
    public static String armorName(final ArmorMaterial armorMaterial, final String slotName) {
        return armorName(armorMaterial) + "_" + slotName;
    }

    /**
     * Get the {@link String Hat name}
     *
     * @param armorMaterial {@link ArmorMaterial The Armor Material}
     * @return {@link String The Hat name}
     */
    public static String hat(final ArmorMaterial armorMaterial) {
        return armorName(armorMaterial, "hat");
    }

    /**
     * Get the {@link String Fire name} based on the {@link MWFireBlock.MWFireType Fire Type}
     *
     * @param fireType {@link MWFireBlock.MWFireType The Fire Type}
     * @return {@link String The Fire name}
     */
    public static String fireName(final MWFireBlock.MWFireType fireType) {
        return path(parse(lower(fireType.name())));
    }

    /**
     * Get the {@link String Ethereal Rune name} based on the {@link EtherealRuneBlock.Types Ethereal Rune Type}
     *
     * @param runeType {@link EtherealRuneBlock.Types The Ethereal Rune Type}
     * @return {@link String The Ethereal Rune name}
     */
    public static String etherealRuneName(final EtherealRuneBlock.Types runeType) {
        return "ethereal_rune_" + path(parse(lower(runeType.name())));
    }

    /**
     * Get the {@link MWDimensions.Dimensions Dimension} name
     *
     * @param dimension {@link MWDimensions.Dimensions The Dimension}
     * @return {@link String The Dimension name}
     */
    public static String dimensionName(final MWDimensions.Dimensions dimension) {
        return lower(dimension.name());
    }

    /**
     * Get the {@link String portal name} based on the {@link MWDimensions.Dimensions Dimension}
     *
     * @param dimension {@link MWDimensions.Dimensions The Dimension}
     * @return {@link String The Dimension portal name}
     */
    public static String portalName(final MWDimensions.Dimensions dimension) {
        return dimensionName(dimension) + "_portal";
    }

    /**
     * Get the {@link String Wood name} based on the {@link WoodType Wood Type}
     *
     * @param woodType {@link WoodType The Wood Type}
     * @return {@link String The Wood name}
     */
    public static String woodName(final WoodType woodType) {
        return path(parse(lower(woodType.name())));
    }

    /**
     * Get the {@link MapColor Wood Color} based on the {@link WoodType Wood Type}
     *
     * @param woodType {@link WoodType The Wood Type}
     * @return {@link String The Wood name}
     */
    public static MapColor woodColor(final WoodType woodType) {
        if(woodType.equals(MWWoodTypes.APPLE.get())) {
            return MWColors.APPLE.toMapColor();
        }
        if(woodType.equals(MWWoodTypes.PALM.get())) {
            return MWColors.PALM.toMapColor();
        }
        if(woodType.equals(MWWoodTypes.DEAD.get())) {
            return MWColors.DEAD.toMapColor();
        }
        if(woodType.equals(MWWoodTypes.SCULK.get())) {
            return MWColors.SCULK.toMapColor();
        }
        return MapColor.WOOD;
    }

    /**
     * Get the {@link ChestType Chest Type} name
     *
     * @param chestType {@link ChestType The Chest Type}
     * @return  An empty string if the {@link ChestType Chest Type} is {@link ChestType#SINGLE Single},
     *          the {@link String Chest Type Name} otherwise
     */
    public static String chestTypeName(final ChestType chestType) {
        if(chestType.equals(ChestType.SINGLE)) {
            return "";
        }
        return "_" + lower(chestType.name());
    }

    /**
     * Get the {@link String Tier name}
     *
     * @param tier {@link Tier The Tier to get the name from}
     * @return {@link String The Tier name}
     */
    public static String tierName(final Tier tier) {
        if(tier.equals(Tiers.WOOD)) {
            return "wood";
        }
        else if(tier.equals(Tiers.STONE)) {
            return "stone";
        }
        else if(tier.equals(Tiers.GOLD)) {
            return "gold";
        }
        else if(tier.equals(Tiers.IRON)) {
            return "iron";
        }
        else if(tier.equals(Tiers.DIAMOND)) {
            return "diamond";
        }
        else if(tier.equals(Tiers.NETHERITE)) {
            return "netherite";
        }
        else if(tier.equals(MWItemTiers.EMERALD_ITEM_TIER)) {
            return "emerald";
        }
        else if(tier.equals(MWItemTiers.ALUMINUM_ITEM_TIER)) {
            return "aluminum";
        }
        else if(tier.equals(MWItemTiers.BRONZE_ITEM_TIER)) {
            return "bronze";
        }
        else if(tier.equals(MWItemTiers.COPPER_ITEM_TIER)) {
            return "copper";
        }
        else if(tier.equals(MWItemTiers.SILVER_ITEM_TIER)) {
            return "silver";
        }
        else if(tier.equals(MWItemTiers.RUBY_ITEM_TIER)) {
            return "ruby";
        }
        else if(tier.equals(MWItemTiers.SAPPHIRE_ITEM_TIER)) {
            return "sapphire";
        }
        throw new NotImplementedException("No name set for tier " + tier);
    }

    /**
     * Make a {@link String string} lower case
     *
     * @param text {@link String The string}
     * @return {@link String The lower case string}
     */
    public static String lower(final String text) {
        return text.toLowerCase(Locale.ROOT);
    }

}