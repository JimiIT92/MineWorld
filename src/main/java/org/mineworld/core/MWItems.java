package org.mineworld.core;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.helper.RegisterHelper;
import org.mineworld.item.*;

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
    public static final RegistryObject<Item> CORN_SEEDS = RegisterHelper.registerBlockItem("corn_seeds", MWBlocks.CORN);
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
    public static final RegistryObject<Item> STONE_PEBBLE = RegisterHelper.registerItem("stone_pebble", () -> new PebbleItem(MWBlocks.STONE_PEBBLE.get()));
    public static final RegistryObject<Item> COBBLESTONE_PEBBLE = RegisterHelper.registerItem("cobblestone_pebble", () -> new PebbleItem(MWBlocks.COBBLESTONE_PEBBLE.get()));
    public static final RegistryObject<Item> MOSSY_STONE_PEBBLE = RegisterHelper.registerItem("mossy_stone_pebble", () -> new PebbleItem(MWBlocks.MOSSY_STONE_PEBBLE.get()));
    public static final RegistryObject<Item> MOSSY_COBBLESTONE_PEBBLE = RegisterHelper.registerItem("mossy_cobblestone_pebble", () -> new PebbleItem(MWBlocks.MOSSY_COBBLESTONE_PEBBLE.get()));
    public static final RegistryObject<Item> SMOOTH_STONE_PEBBLE = RegisterHelper.registerItem("smooth_stone_pebble", () -> new PebbleItem(MWBlocks.SMOOTH_STONE_PEBBLE.get()));
    public static final RegistryObject<Item> STONE_BRICKS_PEBBLE = RegisterHelper.registerItem("stone_bricks_pebble", () -> new PebbleItem(MWBlocks.STONE_BRICKS_PEBBLE.get()));
    public static final RegistryObject<Item> MOSSY_STONE_BRICKS_PEBBLE = RegisterHelper.registerItem("mossy_stone_bricks_pebble", () -> new PebbleItem(MWBlocks.MOSSY_STONE_BRICKS_PEBBLE.get()));
    public static final RegistryObject<Item> GRANITE_PEBBLE = RegisterHelper.registerItem("granite_pebble", () -> new PebbleItem(MWBlocks.GRANITE_PEBBLE.get()));
    public static final RegistryObject<Item> POLISHED_GRANITE_PEBBLE = RegisterHelper.registerItem("polished_granite_pebble", () -> new PebbleItem(MWBlocks.POLISHED_GRANITE_PEBBLE.get()));
    public static final RegistryObject<Item> DIORITE_PEBBLE = RegisterHelper.registerItem("diorite_pebble", () -> new PebbleItem(MWBlocks.DIORITE_PEBBLE.get()));
    public static final RegistryObject<Item> POLISHED_DIORITE_PEBBLE = RegisterHelper.registerItem("polished_diorite_pebble", () -> new PebbleItem(MWBlocks.POLISHED_DIORITE_PEBBLE.get()));
    public static final RegistryObject<Item> ANDESITE_PEBBLE = RegisterHelper.registerItem("andesite_pebble", () -> new PebbleItem(MWBlocks.ANDESITE_PEBBLE.get()));
    public static final RegistryObject<Item> POLISHED_ANDESITE_PEBBLE = RegisterHelper.registerItem("polished_andesite_pebble", () -> new PebbleItem(MWBlocks.POLISHED_ANDESITE_PEBBLE.get()));
    public static final RegistryObject<Item> DEEPSLATE_PEBBLE = RegisterHelper.registerItem("deepslate_pebble", () -> new PebbleItem(MWBlocks.DEEPSLATE_PEBBLE.get()));
    public static final RegistryObject<Item> COBBLED_DEEPSLATE_PEBBLE = RegisterHelper.registerItem("cobbled_deepslate_pebble", () -> new PebbleItem(MWBlocks.COBBLED_DEEPSLATE_PEBBLE.get()));
    public static final RegistryObject<Item> POLISHED_DEEPSLATE_PEBBLE = RegisterHelper.registerItem("polished_deepslate_pebble", () -> new PebbleItem(MWBlocks.POLISHED_DEEPSLATE_PEBBLE.get()));
    public static final RegistryObject<Item> DEEPSLATE_BRICKS_PEBBLE = RegisterHelper.registerItem("deepslate_bricks_pebble", () -> new PebbleItem(MWBlocks.DEEPSLATE_BRICKS_PEBBLE.get()));
    public static final RegistryObject<Item> DEEPSLATE_TILES_PEBBLE = RegisterHelper.registerItem("deepslate_tiles_pebble", () -> new PebbleItem(MWBlocks.DEEPSLATE_TILES_PEBBLE.get()));
    public static final RegistryObject<Item> REINFORCED_DEEPSLATE_PEBBLE = RegisterHelper.registerItem("reinforced_deepslate_pebble", () -> new PebbleItem(MWBlocks.REINFORCED_DEEPSLATE_PEBBLE.get()));
    public static final RegistryObject<Item> BRICKS_PEBBLE = RegisterHelper.registerItem("bricks_pebble", () -> new PebbleItem(MWBlocks.BRICKS_PEBBLE.get()));
    public static final RegistryObject<Item> MUD_BRICKS_PEBBLE = RegisterHelper.registerItem("mud_bricks_pebble", () -> new PebbleItem(MWBlocks.MUD_BRICKS_PEBBLE.get()));
    public static final RegistryObject<Item> SANDSTONE_PEBBLE = RegisterHelper.registerItem("sandstone_pebble", () -> new PebbleItem(MWBlocks.SANDSTONE_PEBBLE.get()));
    public static final RegistryObject<Item> SMOOTH_SANDSTONE_PEBBLE = RegisterHelper.registerItem("smooth_sandstone_pebble", () -> new PebbleItem(MWBlocks.SMOOTH_SANDSTONE_PEBBLE.get()));
    public static final RegistryObject<Item> RED_SANDSTONE_PEBBLE = RegisterHelper.registerItem("red_sandstone_pebble", () -> new PebbleItem(MWBlocks.RED_SANDSTONE_PEBBLE.get()));
    public static final RegistryObject<Item> SMOOTH_RED_SANDSTONE_PEBBLE = RegisterHelper.registerItem("smooth_red_sandstone_pebble", () -> new PebbleItem(MWBlocks.SMOOTH_RED_SANDSTONE_PEBBLE.get()));
    public static final RegistryObject<Item> PRISMARINE_PEBBLE = RegisterHelper.registerItem("prismarine_pebble", () -> new PebbleItem(MWBlocks.PRISMARINE_PEBBLE.get()));
    public static final RegistryObject<Item> PRISMARINE_BRICKS_PEBBLE = RegisterHelper.registerItem("prismarine_bricks_pebble", () -> new PebbleItem(MWBlocks.PRISMARINE_BRICKS_PEBBLE.get()));
    public static final RegistryObject<Item> DARK_PRISMARINE_PEBBLE = RegisterHelper.registerItem("dark_prismarine_pebble", () -> new PebbleItem(MWBlocks.DARK_PRISMARINE_PEBBLE.get()));
    public static final RegistryObject<Item> NETHERRACK_PEBBLE = RegisterHelper.registerItem("netherrack_pebble", () -> new PebbleItem(MWBlocks.NETHERRACK_PEBBLE.get()));
    public static final RegistryObject<Item> NETHER_BRICKS_PEBBLE = RegisterHelper.registerItem("nether_bricks_pebble", () -> new PebbleItem(MWBlocks.NETHER_BRICKS_PEBBLE.get()));
    public static final RegistryObject<Item> RED_NETHER_BRICKS_PEBBLE = RegisterHelper.registerItem("red_nether_bricks_pebble", () -> new PebbleItem(MWBlocks.RED_NETHER_BRICKS_PEBBLE.get()));
    public static final RegistryObject<Item> BASALT_PEBBLE = RegisterHelper.registerItem("basalt_pebble", () -> new PebbleItem(MWBlocks.BASALT_PEBBLE.get()));
    public static final RegistryObject<Item> SMOOTH_BASALT_PEBBLE = RegisterHelper.registerItem("smooth_basalt_pebble", () -> new PebbleItem(MWBlocks.SMOOTH_BASALT_PEBBLE.get()));
    public static final RegistryObject<Item> POLISHED_BASALT_PEBBLE = RegisterHelper.registerItem("polished_basalt_pebble", () -> new PebbleItem(MWBlocks.POLISHED_BASALT_PEBBLE.get()));
    public static final RegistryObject<Item> BLACKSTONE_PEBBLE = RegisterHelper.registerItem("blackstone_pebble", () -> new PebbleItem(MWBlocks.BLACKSTONE_PEBBLE.get()));
    public static final RegistryObject<Item> POLISHED_BLACKSTONE_PEBBLE = RegisterHelper.registerItem("polished_blackstone_pebble", () -> new PebbleItem(MWBlocks.POLISHED_BLACKSTONE_PEBBLE.get()));
    public static final RegistryObject<Item> POLISHED_BLACKSTONE_BRICKS_PEBBLE = RegisterHelper.registerItem("polished_blackstone_bricks_pebble", () -> new PebbleItem(MWBlocks.POLISHED_BLACKSTONE_BRICKS_PEBBLE.get()));
    public static final RegistryObject<Item> GILDED_BLACKSTONE_PEBBLE = RegisterHelper.registerItem("gilded_blackstone_pebble", () -> new PebbleItem(MWBlocks.GILDED_BLACKSTONE_PEBBLE.get()));
    public static final RegistryObject<Item> END_STONE_PEBBLE = RegisterHelper.registerItem("end_stone_pebble", () -> new PebbleItem(MWBlocks.END_STONE_PEBBLE.get()));
    public static final RegistryObject<Item> END_STONE_BRICKS_PEBBLE = RegisterHelper.registerItem("end_stone_bricks_pebble", () -> new PebbleItem(MWBlocks.END_STONE_BRICKS_PEBBLE.get()));
    public static final RegistryObject<Item> PURPUR_PEBBLE = RegisterHelper.registerItem("purpur_pebble", () -> new PebbleItem(MWBlocks.PURPUR_PEBBLE.get()));
    public static final RegistryObject<Item> PURPUR_PILLAR_PEBBLE = RegisterHelper.registerItem("purpur_pillar_pebble", () -> new PebbleItem(MWBlocks.PURPUR_PILLAR_PEBBLE.get()));
    public static final RegistryObject<Item> QUARTZ_PEBBLE = RegisterHelper.registerItem("quartz_pebble", () -> new PebbleItem(MWBlocks.QUARTZ_PEBBLE.get()));
    public static final RegistryObject<Item> SMOOTH_QUARTZ_PEBBLE = RegisterHelper.registerItem("smooth_quartz_pebble", () -> new PebbleItem(MWBlocks.SMOOTH_QUARTZ_PEBBLE.get()));
    public static final RegistryObject<Item> QUARTZ_BRICKS_PEBBLE = RegisterHelper.registerItem("quartz_bricks_pebble", () -> new PebbleItem(MWBlocks.QUARTZ_BRICKS_PEBBLE.get()));
    public static final RegistryObject<Item> QUARTZ_PILLAR_PEBBLE = RegisterHelper.registerItem("quartz_pillar_pebble", () -> new PebbleItem(MWBlocks.QUARTZ_PILLAR_PEBBLE.get()));
    public static final RegistryObject<Item> TERRACOTTA_PEBBLE = RegisterHelper.registerItem("terracotta_pebble", () -> new PebbleItem(MWBlocks.TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> WHITE_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("white_terracotta_pebble", () -> new PebbleItem(MWBlocks.WHITE_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> ORANGE_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("orange_terracotta_pebble", () -> new PebbleItem(MWBlocks.ORANGE_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> MAGENTA_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("magenta_terracotta_pebble", () -> new PebbleItem(MWBlocks.MAGENTA_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> LIGHT_BLUE_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("light_blue_terracotta_pebble", () -> new PebbleItem(MWBlocks.LIGHT_BLUE_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> YELLOW_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("yellow_terracotta_pebble", () -> new PebbleItem(MWBlocks.YELLOW_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> LIME_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("lime_terracotta_pebble", () -> new PebbleItem(MWBlocks.LIME_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> PINK_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("pink_terracotta_pebble", () -> new PebbleItem(MWBlocks.PINK_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> GRAY_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("gray_terracotta_pebble", () -> new PebbleItem(MWBlocks.GRAY_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> LIGHT_GRAY_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("light_gray_terracotta_pebble", () -> new PebbleItem(MWBlocks.LIGHT_GRAY_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> CYAN_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("cyan_terracotta_pebble", () -> new PebbleItem(MWBlocks.CYAN_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> PURPLE_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("purple_terracotta_pebble", () -> new PebbleItem(MWBlocks.PURPLE_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> BLUE_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("blue_terracotta_pebble", () -> new PebbleItem(MWBlocks.BLUE_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> BROWN_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("brown_terracotta_pebble", () -> new PebbleItem(MWBlocks.BROWN_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> GREEN_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("green_terracotta_pebble", () -> new PebbleItem(MWBlocks.GREEN_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> RED_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("red_terracotta_pebble", () -> new PebbleItem(MWBlocks.RED_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> BLACK_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("black_terracotta_pebble", () -> new PebbleItem(MWBlocks.BLACK_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> WHITE_GLAZED_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("white_glazed_terracotta_pebble", () -> new PebbleItem(MWBlocks.WHITE_GLAZED_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> ORANGE_GLAZED_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("orange_glazed_terracotta_pebble", () -> new PebbleItem(MWBlocks.ORANGE_GLAZED_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> MAGENTA_GLAZED_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("magenta_glazed_terracotta_pebble", () -> new PebbleItem(MWBlocks.MAGENTA_GLAZED_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> LIGHT_BLUE_GLAZED_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("light_blue_glazed_terracotta_pebble", () -> new PebbleItem(MWBlocks.LIGHT_BLUE_GLAZED_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> YELLOW_GLAZED_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("yellow_glazed_terracotta_pebble", () -> new PebbleItem(MWBlocks.YELLOW_GLAZED_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> LIME_GLAZED_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("lime_glazed_terracotta_pebble", () -> new PebbleItem(MWBlocks.LIME_GLAZED_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> PINK_GLAZED_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("pink_glazed_terracotta_pebble", () -> new PebbleItem(MWBlocks.PINK_GLAZED_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> GRAY_GLAZED_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("gray_glazed_terracotta_pebble", () -> new PebbleItem(MWBlocks.GRAY_GLAZED_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> LIGHT_GRAY_GLAZED_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("light_gray_glazed_terracotta_pebble", () -> new PebbleItem(MWBlocks.LIGHT_GRAY_GLAZED_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> CYAN_GLAZED_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("cyan_glazed_terracotta_pebble", () -> new PebbleItem(MWBlocks.CYAN_GLAZED_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> PURPLE_GLAZED_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("purple_glazed_terracotta_pebble", () -> new PebbleItem(MWBlocks.PURPLE_GLAZED_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> BLUE_GLAZED_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("blue_glazed_terracotta_pebble", () -> new PebbleItem(MWBlocks.BLUE_GLAZED_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> BROWN_GLAZED_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("brown_glazed_terracotta_pebble", () -> new PebbleItem(MWBlocks.BROWN_GLAZED_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> GREEN_GLAZED_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("green_glazed_terracotta_pebble", () -> new PebbleItem(MWBlocks.GREEN_GLAZED_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> RED_GLAZED_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("red_glazed_terracotta_pebble", () -> new PebbleItem(MWBlocks.RED_GLAZED_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> BLACK_GLAZED_TERRACOTTA_PEBBLE = RegisterHelper.registerItem("black_glazed_terracotta_pebble", () -> new PebbleItem(MWBlocks.BLACK_GLAZED_TERRACOTTA_PEBBLE.get()));
    public static final RegistryObject<Item> WHITE_CONCRETE_PEBBLE = RegisterHelper.registerItem("white_concrete_pebble", () -> new PebbleItem(MWBlocks.WHITE_CONCRETE_PEBBLE.get()));
    public static final RegistryObject<Item> ORANGE_CONCRETE_PEBBLE = RegisterHelper.registerItem("orange_concrete_pebble", () -> new PebbleItem(MWBlocks.ORANGE_CONCRETE_PEBBLE.get()));
    public static final RegistryObject<Item> MAGENTA_CONCRETE_PEBBLE = RegisterHelper.registerItem("magenta_concrete_pebble", () -> new PebbleItem(MWBlocks.MAGENTA_CONCRETE_PEBBLE.get()));
    public static final RegistryObject<Item> LIGHT_BLUE_CONCRETE_PEBBLE = RegisterHelper.registerItem("light_blue_concrete_pebble", () -> new PebbleItem(MWBlocks.LIGHT_BLUE_CONCRETE_PEBBLE.get()));
    public static final RegistryObject<Item> YELLOW_CONCRETE_PEBBLE = RegisterHelper.registerItem("yellow_concrete_pebble", () -> new PebbleItem(MWBlocks.YELLOW_CONCRETE_PEBBLE.get()));
    public static final RegistryObject<Item> LIME_CONCRETE_PEBBLE = RegisterHelper.registerItem("lime_concrete_pebble", () -> new PebbleItem(MWBlocks.LIME_CONCRETE_PEBBLE.get()));
    public static final RegistryObject<Item> PINK_CONCRETE_PEBBLE = RegisterHelper.registerItem("pink_concrete_pebble", () -> new PebbleItem(MWBlocks.PINK_CONCRETE_PEBBLE.get()));
    public static final RegistryObject<Item> GRAY_CONCRETE_PEBBLE = RegisterHelper.registerItem("gray_concrete_pebble", () -> new PebbleItem(MWBlocks.GRAY_CONCRETE_PEBBLE.get()));
    public static final RegistryObject<Item> LIGHT_GRAY_CONCRETE_PEBBLE = RegisterHelper.registerItem("light_gray_concrete_pebble", () -> new PebbleItem(MWBlocks.LIGHT_GRAY_CONCRETE_PEBBLE.get()));
    public static final RegistryObject<Item> CYAN_CONCRETE_PEBBLE = RegisterHelper.registerItem("cyan_concrete_pebble", () -> new PebbleItem(MWBlocks.CYAN_CONCRETE_PEBBLE.get()));
    public static final RegistryObject<Item> PURPLE_CONCRETE_PEBBLE = RegisterHelper.registerItem("purple_concrete_pebble", () -> new PebbleItem(MWBlocks.PURPLE_CONCRETE_PEBBLE.get()));
    public static final RegistryObject<Item> BLUE_CONCRETE_PEBBLE = RegisterHelper.registerItem("blue_concrete_pebble", () -> new PebbleItem(MWBlocks.BLUE_CONCRETE_PEBBLE.get()));
    public static final RegistryObject<Item> BROWN_CONCRETE_PEBBLE = RegisterHelper.registerItem("brown_concrete_pebble", () -> new PebbleItem(MWBlocks.BROWN_CONCRETE_PEBBLE.get()));
    public static final RegistryObject<Item> GREEN_CONCRETE_PEBBLE = RegisterHelper.registerItem("green_concrete_pebble", () -> new PebbleItem(MWBlocks.GREEN_CONCRETE_PEBBLE.get()));
    public static final RegistryObject<Item> RED_CONCRETE_PEBBLE = RegisterHelper.registerItem("red_concrete_pebble", () -> new PebbleItem(MWBlocks.RED_CONCRETE_PEBBLE.get()));
    public static final RegistryObject<Item> BLACK_CONCRETE_PEBBLE = RegisterHelper.registerItem("black_concrete_pebble", () -> new PebbleItem(MWBlocks.BLACK_CONCRETE_PEBBLE.get()));
    public static final RegistryObject<Item> CALCITE_PEBBLE = RegisterHelper.registerItem("calcite_pebble", () -> new PebbleItem(MWBlocks.CALCITE_PEBBLE.get()));
    public static final RegistryObject<Item> TUFF_PEBBLE = RegisterHelper.registerItem("tuff_pebble", () -> new PebbleItem(MWBlocks.TUFF_PEBBLE.get()));
    public static final RegistryObject<Item> DRIPSTONE_PEBBLE = RegisterHelper.registerItem("dripstone_pebble", () -> new PebbleItem(MWBlocks.DRIPSTONE_PEBBLE.get()));
    public static final RegistryObject<Item> OBSIDIAN_PEBBLE = RegisterHelper.registerItem("obsidian_pebble", () -> new PebbleItem(MWBlocks.OBSIDIAN_PEBBLE.get()));
    public static final RegistryObject<Item> CRYING_OBSIDIAN_PEBBLE = RegisterHelper.registerItem("crying_obsidian_pebble", () -> new PebbleItem(MWBlocks.CRYING_OBSIDIAN_PEBBLE.get()));
    public static final RegistryObject<Item> GLOWING_OBSIDIAN_PEBBLE = RegisterHelper.registerItem("glowing_obsidian_pebble", () -> new PebbleItem(MWBlocks.GLOWING_OBSIDIAN_PEBBLE.get()));
    public static final RegistryObject<Item> MARBLE_PEBBLE = RegisterHelper.registerItem("marble_pebble", () -> new PebbleItem(MWBlocks.MARBLE_PEBBLE.get()));
    public static final RegistryObject<Item> WHITE_MARBLE_PEBBLE = RegisterHelper.registerItem("white_marble_pebble", () -> new PebbleItem(MWBlocks.WHITE_MARBLE_PEBBLE.get()));
    public static final RegistryObject<Item> ORANGE_MARBLE_PEBBLE = RegisterHelper.registerItem("orange_marble_pebble", () -> new PebbleItem(MWBlocks.ORANGE_MARBLE_PEBBLE.get()));
    public static final RegistryObject<Item> MAGENTA_MARBLE_PEBBLE = RegisterHelper.registerItem("magenta_marble_pebble", () -> new PebbleItem(MWBlocks.MAGENTA_MARBLE_PEBBLE.get()));
    public static final RegistryObject<Item> LIGHT_BLUE_MARBLE_PEBBLE = RegisterHelper.registerItem("light_blue_marble_pebble", () -> new PebbleItem(MWBlocks.LIGHT_BLUE_MARBLE_PEBBLE.get()));
    public static final RegistryObject<Item> YELLOW_MARBLE_PEBBLE = RegisterHelper.registerItem("yellow_marble_pebble", () -> new PebbleItem(MWBlocks.YELLOW_MARBLE_PEBBLE.get()));
    public static final RegistryObject<Item> LIME_MARBLE_PEBBLE = RegisterHelper.registerItem("lime_marble_pebble", () -> new PebbleItem(MWBlocks.LIME_MARBLE_PEBBLE.get()));
    public static final RegistryObject<Item> PINK_MARBLE_PEBBLE = RegisterHelper.registerItem("pink_marble_pebble", () -> new PebbleItem(MWBlocks.PINK_MARBLE_PEBBLE.get()));
    public static final RegistryObject<Item> GRAY_MARBLE_PEBBLE = RegisterHelper.registerItem("gray_marble_pebble", () -> new PebbleItem(MWBlocks.GRAY_MARBLE_PEBBLE.get()));
    public static final RegistryObject<Item> LIGHT_GRAY_MARBLE_PEBBLE = RegisterHelper.registerItem("light_gray_marble_pebble", () -> new PebbleItem(MWBlocks.LIGHT_GRAY_MARBLE_PEBBLE.get()));
    public static final RegistryObject<Item> CYAN_MARBLE_PEBBLE = RegisterHelper.registerItem("cyan_marble_pebble", () -> new PebbleItem(MWBlocks.CYAN_MARBLE_PEBBLE.get()));
    public static final RegistryObject<Item> PURPLE_MARBLE_PEBBLE = RegisterHelper.registerItem("purple_marble_pebble", () -> new PebbleItem(MWBlocks.PURPLE_MARBLE_PEBBLE.get()));
    public static final RegistryObject<Item> BLUE_MARBLE_PEBBLE = RegisterHelper.registerItem("blue_marble_pebble", () -> new PebbleItem(MWBlocks.BLUE_MARBLE_PEBBLE.get()));
    public static final RegistryObject<Item> BROWN_MARBLE_PEBBLE = RegisterHelper.registerItem("brown_marble_pebble", () -> new PebbleItem(MWBlocks.BROWN_MARBLE_PEBBLE.get()));
    public static final RegistryObject<Item> GREEN_MARBLE_PEBBLE = RegisterHelper.registerItem("green_marble_pebble", () -> new PebbleItem(MWBlocks.GREEN_MARBLE_PEBBLE.get()));
    public static final RegistryObject<Item> RED_MARBLE_PEBBLE = RegisterHelper.registerItem("red_marble_pebble", () -> new PebbleItem(MWBlocks.RED_MARBLE_PEBBLE.get()));
    public static final RegistryObject<Item> BLACK_MARBLE_PEBBLE = RegisterHelper.registerItem("black_marble_pebble", () -> new PebbleItem(MWBlocks.BLACK_MARBLE_PEBBLE.get()));
    public static final RegistryObject<Item> DISGUISED_GRASS_TNT_MINECART = RegisterHelper.registerItem("disguised_grass_tnt_minecart", () -> new MWTNTMinecartItem(MWMinecartItem.Type.DISGUISED_GRASS_TNT, MWPrimedTnt.Type.DISGUISED_GRASS));
    public static final RegistryObject<Item> DISGUISED_DIRT_TNT_MINECART = RegisterHelper.registerItem("disguised_dirt_tnt_minecart", () -> new MWTNTMinecartItem(MWMinecartItem.Type.DISGUISED_DIRT_TNT, MWPrimedTnt.Type.DISGUISED_DIRT));
    public static final RegistryObject<Item> DISGUISED_SAND_TNT_MINECART = RegisterHelper.registerItem("disguised_sand_tnt_minecart", () -> new MWTNTMinecartItem(MWMinecartItem.Type.DISGUISED_SAND_TNT, MWPrimedTnt.Type.DISGUISED_SAND));
    public static final RegistryObject<Item> DISGUISED_RED_SAND_TNT_MINECART = RegisterHelper.registerItem("disguised_red_sand_tnt_minecart", () -> new MWTNTMinecartItem(MWMinecartItem.Type.DISGUISED_RED_SAND_TNT, MWPrimedTnt.Type.DISGUISED_RED_SAND));
    public static final RegistryObject<Item> DISGUISED_STONE_TNT_MINECART = RegisterHelper.registerItem("disguised_stone_tnt_minecart", () -> new MWTNTMinecartItem(MWMinecartItem.Type.DISGUISED_STONE_TNT, MWPrimedTnt.Type.DISGUISED_STONE));
    public static final RegistryObject<Item> DISGUISED_CAKE_TNT_MINECART = RegisterHelper.registerItem("disguised_cake_tnt_minecart", () -> new MWTNTMinecartItem(MWMinecartItem.Type.DISGUISED_CAKE_TNT, MWPrimedTnt.Type.DISGUISED_CAKE));
    public static final RegistryObject<Item> MEGA_TNT_MINECART = RegisterHelper.registerItem("mega_tnt_minecart", () -> new MWTNTMinecartItem(MWMinecartItem.Type.MEGA_TNT, MWPrimedTnt.Type.MEGA));
    public static final RegistryObject<Item> SUPER_TNT_MINECART = RegisterHelper.registerItem("super_tnt_minecart", () -> new MWTNTMinecartItem(MWMinecartItem.Type.SUPER_TNT, MWPrimedTnt.Type.SUPER));
    public static final RegistryObject<Item> HYPER_TNT_MINECART = RegisterHelper.registerItem("hyper_tnt_minecart", () -> new MWTNTMinecartItem(MWMinecartItem.Type.HYPER_TNT, MWPrimedTnt.Type.HYPER));
    public static final RegistryObject<Item> WARPED_WART = RegisterHelper.registerBlockItem("warped_wart", MWBlocks.WARPED_WART);
    public static final RegistryObject<Item> BLUE_NETHER_BRICKS_PEBBLE = RegisterHelper.registerItem("blue_nether_bricks_pebble", () -> new PebbleItem(MWBlocks.BLUE_NETHER_BRICKS_PEBBLE.get()));
    public static final RegistryObject<Item> SPRUCE_CHEST = RegisterHelper.registerSpecialRendererBlockItem("spruce_chest", MWBlocks.SPRUCE_CHEST);
    public static final RegistryObject<Item> SPRUCE_TRAPPED_CHEST = RegisterHelper.registerSpecialRendererBlockItem("spruce_trapped_chest", MWBlocks.SPRUCE_TRAPPED_CHEST);
    public static final RegistryObject<Item> SPRUCE_CHEST_MINECART = RegisterHelper.registerItem("spruce_chest_minecart", () -> new MWMinecartItem(MWMinecartItem.Type.SPRUCE_CHEST));

    public static final RegistryObject<Item> BIRCH_CHEST = RegisterHelper.registerSpecialRendererBlockItem("birch_chest", MWBlocks.BIRCH_CHEST);
    public static final RegistryObject<Item> BIRCH_TRAPPED_CHEST = RegisterHelper.registerSpecialRendererBlockItem("birch_trapped_chest", MWBlocks.BIRCH_TRAPPED_CHEST);
    public static final RegistryObject<Item> BIRCH_CHEST_MINECART = RegisterHelper.registerItem("birch_chest_minecart", () -> new MWMinecartItem(MWMinecartItem.Type.BIRCH_CHEST));

    /**
     * Register the {@link MineWorld MineWorld} {@link Item items}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(final IEventBus eventBus) {
        RegisterHelper.registerItems(eventBus);
    }

}