package org.mineworld.core;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;
import org.mineworld.item.MWArmorMaterials;
import org.mineworld.item.MWFoods;
import org.mineworld.item.MWItemTiers;

/**
 * {@link MineWorld MineWorld} {@link Item items}
 */
public final class MWItems {

    public static final RegistryObject<Item> COPPER_NUGGET = RegisterHelper.registerItem("copper_nugget");
    public static final RegistryObject<Item> NETHERITE_NUGGET = RegisterHelper.registerItem("netherite_nugget");
    public static final RegistryObject<Item> RUBY = RegisterHelper.registerItem("ruby");
    public static final RegistryObject<Item> SAPPHIRE = RegisterHelper.registerItem("sapphire");
    public static final RegistryObject<Item> PYRITE = RegisterHelper.registerFuelItem("pyrite", 120);
    public static final RegistryObject<Item> RAW_SILVER = RegisterHelper.registerItem("raw_silver");
    public static final RegistryObject<Item> SILVER_INGOT = RegisterHelper.registerItem("silver_ingot");
    public static final RegistryObject<Item> SILVER_NUGGET = RegisterHelper.registerItem("silver_nugget");
    public static final RegistryObject<Item> RAW_ALUMINUM = RegisterHelper.registerItem("raw_aluminum");
    public static final RegistryObject<Item> ALUMINUM_INGOT = RegisterHelper.registerItem("aluminum_ingot");
    public static final RegistryObject<Item> ALUMINUM_NUGGET = RegisterHelper.registerItem("aluminum_nugget");
    public static final RegistryObject<Item> RAW_BRONZE = RegisterHelper.registerItem("raw_bronze");
    public static final RegistryObject<Item> BRONZE_INGOT = RegisterHelper.registerItem("bronze_ingot");
    public static final RegistryObject<Item> BRONZE_NUGGET = RegisterHelper.registerItem("bronze_nugget");
    public static final RegistryObject<Item> RAW_BRONZE_SMITHING_TEMPLATE = RegisterHelper.registerSmithingTemplate("raw_bronze_smithing_template", "raw_bronze", "raw_copper", "raw_aluminum");
    public static final RegistryObject<Item> CORN_SEEDS = RegisterHelper.registerSeedItem("corn_seeds", MWBlocks.CORN);
    public static final RegistryObject<Item> COB = RegisterHelper.registerFoodItem("cob", MWFoods.COB);
    public static final RegistryObject<Item> BAKED_COB = RegisterHelper.registerFoodItem("baked_cob", MWFoods.BAKED_COB);
    public static final RegistryObject<Item> CHAINMAIL_HORSE_ARMOR = RegisterHelper.registerHorseArmorItem("chainmail", 5);
    public static final RegistryObject<Item> EMERALD_SWORD = RegisterHelper.registerSword("emerald_sword", MWItemTiers.EMERALD_ITEM_TIER);
    public static final RegistryObject<Item> EMERALD_SHOVEL = RegisterHelper.registerShovel("emerald_shovel", MWItemTiers.EMERALD_ITEM_TIER);
    public static final RegistryObject<Item> EMERALD_PICKAXE = RegisterHelper.registerPickaxe("emerald_pickaxe", MWItemTiers.EMERALD_ITEM_TIER);
    public static final RegistryObject<Item> EMERALD_AXE = RegisterHelper.registerAxe("emerald_axe", MWItemTiers.EMERALD_ITEM_TIER, 5.0F, -3.0F);
    public static final RegistryObject<Item> EMERALD_HOE = RegisterHelper.registerHoe("emerald_hoe", MWItemTiers.EMERALD_ITEM_TIER, 0.0F);
    public static final RegistryObject<Item> EMERALD_HELMET = RegisterHelper.registerArmorItem("emerald_helmet", MWArmorMaterials.EMERALD, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> EMERALD_CHESTPLATE = RegisterHelper.registerArmorItem("emerald_chestplate", MWArmorMaterials.EMERALD, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> EMERALD_LEGGINGS = RegisterHelper.registerArmorItem("emerald_leggings", MWArmorMaterials.EMERALD, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> EMERALD_BOOTS = RegisterHelper.registerArmorItem("emerald_boots", MWArmorMaterials.EMERALD, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> EMERALD_HORSE_ARMOR = RegisterHelper.registerHorseArmorItem("emerald", 13);
    public static final RegistryObject<Item> NETHERITE_HORSE_ARMOR = RegisterHelper.registerHorseArmorItem("netherite", 15);
    public static final RegistryObject<Item> ALUMINUM_SWORD = RegisterHelper.registerSword("aluminum_sword", MWItemTiers.ALUMINUM_ITEM_TIER);
    public static final RegistryObject<Item> ALUMINUM_SHOVEL = RegisterHelper.registerShovel("aluminum_shovel", MWItemTiers.ALUMINUM_ITEM_TIER);
    public static final RegistryObject<Item> ALUMINUM_PICKAXE = RegisterHelper.registerPickaxe("aluminum_pickaxe", MWItemTiers.ALUMINUM_ITEM_TIER);
    public static final RegistryObject<Item> ALUMINUM_AXE = RegisterHelper.registerAxe("aluminum_axe", MWItemTiers.ALUMINUM_ITEM_TIER, 6.0F, -3.1F);
    public static final RegistryObject<Item> ALUMINUM_HOE = RegisterHelper.registerHoe("aluminum_hoe", MWItemTiers.ALUMINUM_ITEM_TIER, -3.0F);
    public static final RegistryObject<Item> ALUMINUM_HELMET = RegisterHelper.registerArmorItem("aluminum_helmet", MWArmorMaterials.ALUMINUM, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> ALUMINUM_CHESTPLATE = RegisterHelper.registerArmorItem("aluminum_chestplate", MWArmorMaterials.ALUMINUM, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> ALUMINUM_LEGGINGS = RegisterHelper.registerArmorItem("aluminum_leggings", MWArmorMaterials.ALUMINUM, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> ALUMINUM_BOOTS = RegisterHelper.registerArmorItem("aluminum_boots", MWArmorMaterials.ALUMINUM, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> ALUMINUM_HORSE_ARMOR = RegisterHelper.registerHorseArmorItem("aluminum", 4);
    public static final RegistryObject<Item> BRONZE_SWORD = RegisterHelper.registerSword("bronze_sword", MWItemTiers.BRONZE_ITEM_TIER);
    public static final RegistryObject<Item> BRONZE_SHOVEL = RegisterHelper.registerShovel("bronze_shovel", MWItemTiers.BRONZE_ITEM_TIER);
    public static final RegistryObject<Item> BRONZE_PICKAXE = RegisterHelper.registerPickaxe("bronze_pickaxe", MWItemTiers.BRONZE_ITEM_TIER);
    public static final RegistryObject<Item> BRONZE_AXE = RegisterHelper.registerAxe("bronze_axe", MWItemTiers.BRONZE_ITEM_TIER, 6.5F, -3.1F);
    public static final RegistryObject<Item> BRONZE_HOE = RegisterHelper.registerHoe("bronze_hoe", MWItemTiers.BRONZE_ITEM_TIER, -2.5F);
    public static final RegistryObject<Item> BRONZE_HELMET = RegisterHelper.registerArmorItem("bronze_helmet", MWArmorMaterials.BRONZE, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> BRONZE_CHESTPLATE = RegisterHelper.registerArmorItem("bronze_chestplate", MWArmorMaterials.BRONZE, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> BRONZE_LEGGINGS = RegisterHelper.registerArmorItem("bronze_leggings", MWArmorMaterials.BRONZE, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> BRONZE_BOOTS = RegisterHelper.registerArmorItem("bronze_boots", MWArmorMaterials.BRONZE, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> BRONZE_HORSE_ARMOR = RegisterHelper.registerHorseArmorItem("bronze", 6);
    public static final RegistryObject<Item> COPPER_SWORD = RegisterHelper.registerSword("copper_sword", MWItemTiers.COPPER_ITEM_TIER);
    public static final RegistryObject<Item> COPPER_SHOVEL = RegisterHelper.registerShovel("copper_shovel", MWItemTiers.COPPER_ITEM_TIER);
    public static final RegistryObject<Item> COPPER_PICKAXE = RegisterHelper.registerPickaxe("copper_pickaxe", MWItemTiers.COPPER_ITEM_TIER);
    public static final RegistryObject<Item> COPPER_AXE = RegisterHelper.registerAxe("copper_axe", MWItemTiers.COPPER_ITEM_TIER, 5.5F, -3.0F);
    public static final RegistryObject<Item> COPPER_HOE = RegisterHelper.registerHoe("copper_hoe", MWItemTiers.COPPER_ITEM_TIER, -0.5F);
    public static final RegistryObject<Item> COPPER_HELMET = RegisterHelper.registerArmorItem("copper_helmet", MWArmorMaterials.COPPER, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> COPPER_CHESTPLATE = RegisterHelper.registerArmorItem("copper_chestplate", MWArmorMaterials.COPPER, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> COPPER_LEGGINGS = RegisterHelper.registerArmorItem("copper_leggings", MWArmorMaterials.COPPER, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> COPPER_BOOTS = RegisterHelper.registerArmorItem("copper_boots", MWArmorMaterials.COPPER, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> COPPER_HORSE_ARMOR = RegisterHelper.registerHorseArmorItem("copper", 8);
    public static final RegistryObject<Item> SILVER_SWORD = RegisterHelper.registerSword("silver_sword", MWItemTiers.SILVER_ITEM_TIER);
    public static final RegistryObject<Item> SILVER_SHOVEL = RegisterHelper.registerShovel("silver_shovel", MWItemTiers.SILVER_ITEM_TIER);
    public static final RegistryObject<Item> SILVER_PICKAXE = RegisterHelper.registerPickaxe("silver_pickaxe", MWItemTiers.SILVER_ITEM_TIER);
    public static final RegistryObject<Item> SILVER_AXE = RegisterHelper.registerAxe("silver_axe", MWItemTiers.SILVER_ITEM_TIER, 5.5F, -3.0F);
    public static final RegistryObject<Item> SILVER_HOE = RegisterHelper.registerHoe("silver_hoe", MWItemTiers.SILVER_ITEM_TIER, -0.5F);
    public static final RegistryObject<Item> SILVER_HELMET = RegisterHelper.registerArmorItem("silver_helmet", MWArmorMaterials.SILVER, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> SILVER_CHESTPLATE = RegisterHelper.registerArmorItem("silver_chestplate", MWArmorMaterials.SILVER, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> SILVER_LEGGINGS = RegisterHelper.registerArmorItem("silver_leggings", MWArmorMaterials.SILVER, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> SILVER_BOOTS = RegisterHelper.registerArmorItem("silver_boots", MWArmorMaterials.SILVER, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> SILVER_HORSE_ARMOR = RegisterHelper.registerHorseArmorItem("silver", 8);
    public static final RegistryObject<Item> RUBY_SWORD = RegisterHelper.registerSword("ruby_sword", MWItemTiers.RUBY_ITEM_TIER);
    public static final RegistryObject<Item> RUBY_SHOVEL = RegisterHelper.registerShovel("ruby_shovel", MWItemTiers.RUBY_ITEM_TIER);
    public static final RegistryObject<Item> RUBY_PICKAXE = RegisterHelper.registerPickaxe("ruby_pickaxe", MWItemTiers.RUBY_ITEM_TIER);
    public static final RegistryObject<Item> RUBY_AXE = RegisterHelper.registerAxe("ruby_axe", MWItemTiers.RUBY_ITEM_TIER, 5.0F, -3.0F);
    public static final RegistryObject<Item> RUBY_HOE = RegisterHelper.registerHoe("ruby_hoe", MWItemTiers.RUBY_ITEM_TIER, 0.0F);
    public static final RegistryObject<Item> RUBY_HELMET = RegisterHelper.registerArmorItem("ruby_helmet", MWArmorMaterials.RUBY, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> RUBY_CHESTPLATE = RegisterHelper.registerArmorItem("ruby_chestplate", MWArmorMaterials.RUBY, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> RUBY_LEGGINGS = RegisterHelper.registerArmorItem("ruby_leggings", MWArmorMaterials.RUBY, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> RUBY_BOOTS = RegisterHelper.registerArmorItem("ruby_boots", MWArmorMaterials.RUBY, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> RUBY_HORSE_ARMOR = RegisterHelper.registerHorseArmorItem("ruby", 13);
    public static final RegistryObject<Item> SAPPHIRE_SWORD = RegisterHelper.registerSword("sapphire_sword", MWItemTiers.SAPPHIRE_ITEM_TIER);
    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = RegisterHelper.registerShovel("sapphire_shovel", MWItemTiers.SAPPHIRE_ITEM_TIER);
    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = RegisterHelper.registerPickaxe("sapphire_pickaxe", MWItemTiers.SAPPHIRE_ITEM_TIER);
    public static final RegistryObject<Item> SAPPHIRE_AXE = RegisterHelper.registerAxe("sapphire_axe", MWItemTiers.SAPPHIRE_ITEM_TIER, 5.0F, -3.0F);
    public static final RegistryObject<Item> SAPPHIRE_HOE = RegisterHelper.registerHoe("sapphire_hoe", MWItemTiers.SAPPHIRE_ITEM_TIER, 0.0F);
    public static final RegistryObject<Item> SAPPHIRE_HELMET = RegisterHelper.registerArmorItem("sapphire_helmet", MWArmorMaterials.SAPPHIRE, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> SAPPHIRE_CHESTPLATE = RegisterHelper.registerArmorItem("sapphire_chestplate", MWArmorMaterials.SAPPHIRE, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> SAPPHIRE_LEGGINGS = RegisterHelper.registerArmorItem("sapphire_leggings", MWArmorMaterials.SAPPHIRE, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> SAPPHIRE_BOOTS = RegisterHelper.registerArmorItem("sapphire_boots", MWArmorMaterials.SAPPHIRE, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> SAPPHIRE_HORSE_ARMOR = RegisterHelper.registerHorseArmorItem("sapphire", 13);

    /**
     * Register the {@link MineWorld MineWorld} {@link Item items}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(final IEventBus eventBus) {
        RegisterHelper.registerItems(eventBus);
    }

}
