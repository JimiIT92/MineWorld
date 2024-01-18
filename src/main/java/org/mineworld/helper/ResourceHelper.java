package org.mineworld.helper;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import org.apache.commons.lang3.NotImplementedException;
import org.mineworld.MineWorld;
import org.mineworld.core.MWColors;
import org.mineworld.core.MWItemTiers;
import org.mineworld.core.MWWoodTypes;

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
     * Get the {@link String Armor Material name}
     *
     * @param armorMaterial {@link ArmorMaterial The Armor Material}
     * @return {@link String The Armor Material name}
     */
    public static String armorMaterialName(final ArmorMaterial armorMaterial) {
        final ResourceLocation resourceLocation = ResourceLocation.tryParse(armorMaterial.getName());
        return resourceLocation.getPath().toLowerCase(Locale.ROOT);
    }

    /**
     * Get the {@link String Wood name} based on the {@link WoodType Wood Type}
     *
     * @param woodType {@link WoodType The Wood Type}
     * @return {@link String The Wood name}
     */
    public static String woodName(final WoodType woodType) {
        return woodType.name().toLowerCase(Locale.ROOT);
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

}