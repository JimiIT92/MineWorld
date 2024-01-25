package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.core.Direction;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.block.MWFireBlock;
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.entity.vehicle.MWBoat;
import org.mineworld.entity.vehicle.MWMinecartChest;
import org.mineworld.entity.vehicle.MWMinecartTnt;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.helper.ResourceHelper;
import org.mineworld.helper.TextureHelper;
import org.mineworld.item.*;

import java.util.Locale;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link Item Items}
 */
public final class MWItems {

    //#region Registry

    /**
     * The {@link DeferredRegister<Item> Item Registry}
     */
    private static final DeferredRegister<Item> ITEMS = RegistryHelper.registry(ForgeRegistries.ITEMS);

    //#endregion

    //#region Items

    //#region Nuggets

    public static final RegistryObject<Item> COPPER_NUGGET = registerSimpleItem("copper_nugget");
    public static final RegistryObject<Item> NETHERITE_NUGGET = registerSimpleItem("netherite_nugget");
    public static final RegistryObject<Item> SILVER_NUGGET = registerSimpleItem("silver_nugget");
    public static final RegistryObject<Item> ALUMINUM_NUGGET = registerSimpleItem("aluminum_nugget");
    public static final RegistryObject<Item> BRONZE_NUGGET = registerSimpleItem("bronze_nugget");

    //#endregion

    //#region Gems, Raw materials and Ingots

    public static final RegistryObject<Item> RUBY = registerSimpleItem("ruby");
    public static final RegistryObject<Item> SAPPHIRE = registerSimpleItem("sapphire");
    public static final RegistryObject<Item> PYRITE = registerFuel("pyrite", 120);
    public static final RegistryObject<Item> RAW_SILVER = registerSimpleItem("raw_silver");
    public static final RegistryObject<Item> RAW_ALUMINUM = registerSimpleItem("raw_aluminum");
    public static final RegistryObject<Item> RAW_BRONZE = registerSimpleItem("raw_bronze");
    public static final RegistryObject<Item> SILVER_INGOT = registerSimpleItem("silver_ingot");
    public static final RegistryObject<Item> ALUMINUM_INGOT = registerSimpleItem("aluminum_ingot");
    public static final RegistryObject<Item> BRONZE_INGOT = registerSimpleItem("bronze_ingot");

    //#endregion

    //#region Food, Plants and Seeds

    public static final RegistryObject<Item> COB = registerFood("cob", 2, 0.1F);
    public static final RegistryObject<Item> BAKED_COB = registerFood("baked_cob", 4, 0.2F);
    public static final RegistryObject<Item> CHERRY = registerFood("cherry", 2, 0.1F);
    public static final RegistryObject<Item> CANDY_CANE = registerFood("candy_cane", 6, 0.25F);
    public static final RegistryObject<Item> BLUEBERRIES = registerFoodBlockItem("blueberries", Suppliers.memoize(() -> MWBlocks.BLUEBERRY_BUSH.get()), 2, 0.1F);
    public static final RegistryObject<Item> CORN_SEEDS = registerBlockItem("corn_seeds", Suppliers.memoize(() -> MWBlocks.CORN.get()));
    public static final RegistryObject<Item> WARPED_WART = registerBlockItem("warped_wart", Suppliers.memoize(() -> MWBlocks.WARPED_WART.get()));

    //#endregion

    //#region Armor, Tools and Horse Armors

    public static final RegistryObject<Item> CHAINMAIL_HORSE_ARMOR = registerHorseArmorItem(ArmorMaterials.CHAIN, 5);
    public static final RegistryObject<Item> EMERALD_SWORD = registerSword(MWItemTiers.EMERALD_ITEM_TIER);
    public static final RegistryObject<Item> EMERALD_SHOVEL = registerShovel(MWItemTiers.EMERALD_ITEM_TIER);
    public static final RegistryObject<Item> EMERALD_PICKAXE = registerPickaxe(MWItemTiers.EMERALD_ITEM_TIER);
    public static final RegistryObject<Item> EMERALD_AXE = registerAxe(MWItemTiers.EMERALD_ITEM_TIER, 5.0F, -3.0F);
    public static final RegistryObject<Item> EMERALD_HOE = registerHoe(MWItemTiers.EMERALD_ITEM_TIER, 0.0F);
    public static final RegistryObject<Item> EMERALD_HELMET = registerArmorItem(MWArmorMaterials.EMERALD, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> EMERALD_CHESTPLATE = registerArmorItem(MWArmorMaterials.EMERALD, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> EMERALD_LEGGINGS = registerArmorItem(MWArmorMaterials.EMERALD, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> EMERALD_BOOTS = registerArmorItem(MWArmorMaterials.EMERALD, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> EMERALD_HORSE_ARMOR = registerHorseArmorItem(MWArmorMaterials.EMERALD, 13);
    public static final RegistryObject<Item> NETHERITE_HORSE_ARMOR = registerHorseArmorItem(ArmorMaterials.NETHERITE, 15);
    public static final RegistryObject<Item> ALUMINUM_SWORD = registerSword(MWItemTiers.ALUMINUM_ITEM_TIER);
    public static final RegistryObject<Item> ALUMINUM_SHOVEL = registerShovel(MWItemTiers.ALUMINUM_ITEM_TIER);
    public static final RegistryObject<Item> ALUMINUM_PICKAXE = registerPickaxe(MWItemTiers.ALUMINUM_ITEM_TIER);
    public static final RegistryObject<Item> ALUMINUM_AXE = registerAxe(MWItemTiers.ALUMINUM_ITEM_TIER, 6.0F, -3.1F);
    public static final RegistryObject<Item> ALUMINUM_HOE = registerHoe(MWItemTiers.ALUMINUM_ITEM_TIER, -3.0F);
    public static final RegistryObject<Item> ALUMINUM_HELMET = registerArmorItem(MWArmorMaterials.ALUMINUM, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> ALUMINUM_CHESTPLATE = registerArmorItem(MWArmorMaterials.ALUMINUM, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> ALUMINUM_LEGGINGS = registerArmorItem(MWArmorMaterials.ALUMINUM, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> ALUMINUM_BOOTS = registerArmorItem(MWArmorMaterials.ALUMINUM, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> ALUMINUM_HORSE_ARMOR = registerHorseArmorItem(MWArmorMaterials.ALUMINUM, 4);
    public static final RegistryObject<Item> BRONZE_SWORD = registerSword(MWItemTiers.BRONZE_ITEM_TIER);
    public static final RegistryObject<Item> BRONZE_SHOVEL = registerShovel(MWItemTiers.BRONZE_ITEM_TIER);
    public static final RegistryObject<Item> BRONZE_PICKAXE = registerPickaxe(MWItemTiers.BRONZE_ITEM_TIER);
    public static final RegistryObject<Item> BRONZE_AXE = registerAxe(MWItemTiers.BRONZE_ITEM_TIER, 6.5F, -3.1F);
    public static final RegistryObject<Item> BRONZE_HOE = registerHoe(MWItemTiers.BRONZE_ITEM_TIER, -2.5F);
    public static final RegistryObject<Item> BRONZE_HELMET = registerArmorItem(MWArmorMaterials.BRONZE, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> BRONZE_CHESTPLATE = registerArmorItem(MWArmorMaterials.BRONZE, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> BRONZE_LEGGINGS = registerArmorItem(MWArmorMaterials.BRONZE, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> BRONZE_BOOTS = registerArmorItem(MWArmorMaterials.BRONZE, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> BRONZE_HORSE_ARMOR = registerHorseArmorItem(MWArmorMaterials.BRONZE, 6);
    public static final RegistryObject<Item> COPPER_SWORD = registerSword(MWItemTiers.COPPER_ITEM_TIER);
    public static final RegistryObject<Item> COPPER_SHOVEL = registerShovel(MWItemTiers.COPPER_ITEM_TIER);
    public static final RegistryObject<Item> COPPER_PICKAXE = registerPickaxe(MWItemTiers.COPPER_ITEM_TIER);
    public static final RegistryObject<Item> COPPER_AXE = registerAxe(MWItemTiers.COPPER_ITEM_TIER, 5.5F, -3.0F);
    public static final RegistryObject<Item> COPPER_HOE = registerHoe(MWItemTiers.COPPER_ITEM_TIER, -0.5F);
    public static final RegistryObject<Item> COPPER_HELMET = registerArmorItem(MWArmorMaterials.COPPER, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> COPPER_CHESTPLATE = registerArmorItem(MWArmorMaterials.COPPER, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> COPPER_LEGGINGS = registerArmorItem(MWArmorMaterials.COPPER, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> COPPER_BOOTS = registerArmorItem(MWArmorMaterials.COPPER, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> COPPER_HORSE_ARMOR = registerHorseArmorItem(MWArmorMaterials.COPPER, 8);
    public static final RegistryObject<Item> SILVER_SWORD = registerSword(MWItemTiers.SILVER_ITEM_TIER);
    public static final RegistryObject<Item> SILVER_SHOVEL = registerShovel(MWItemTiers.SILVER_ITEM_TIER);
    public static final RegistryObject<Item> SILVER_PICKAXE = registerPickaxe(MWItemTiers.SILVER_ITEM_TIER);
    public static final RegistryObject<Item> SILVER_AXE = registerAxe(MWItemTiers.SILVER_ITEM_TIER, 5.5F, -3.0F);
    public static final RegistryObject<Item> SILVER_HOE = registerHoe(MWItemTiers.SILVER_ITEM_TIER, -0.5F);
    public static final RegistryObject<Item> SILVER_HELMET = registerArmorItem(MWArmorMaterials.SILVER, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> SILVER_CHESTPLATE = registerArmorItem(MWArmorMaterials.SILVER, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> SILVER_LEGGINGS = registerArmorItem(MWArmorMaterials.SILVER, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> SILVER_BOOTS = registerArmorItem(MWArmorMaterials.SILVER, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> SILVER_HORSE_ARMOR = registerHorseArmorItem(MWArmorMaterials.SILVER, 8);
    public static final RegistryObject<Item> RUBY_SWORD = registerSword(MWItemTiers.RUBY_ITEM_TIER);
    public static final RegistryObject<Item> RUBY_SHOVEL = registerShovel(MWItemTiers.RUBY_ITEM_TIER);
    public static final RegistryObject<Item> RUBY_PICKAXE = registerPickaxe(MWItemTiers.RUBY_ITEM_TIER);
    public static final RegistryObject<Item> RUBY_AXE = registerAxe(MWItemTiers.RUBY_ITEM_TIER, 5.0F, -3.0F);
    public static final RegistryObject<Item> RUBY_HOE = registerHoe(MWItemTiers.RUBY_ITEM_TIER, 0.0F);
    public static final RegistryObject<Item> RUBY_HELMET = registerArmorItem(MWArmorMaterials.RUBY, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> RUBY_CHESTPLATE = registerArmorItem(MWArmorMaterials.RUBY, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> RUBY_LEGGINGS = registerArmorItem(MWArmorMaterials.RUBY, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> RUBY_BOOTS = registerArmorItem(MWArmorMaterials.RUBY, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> RUBY_HORSE_ARMOR = registerHorseArmorItem(MWArmorMaterials.RUBY, 13);
    public static final RegistryObject<Item> SAPPHIRE_SWORD = registerSword(MWItemTiers.SAPPHIRE_ITEM_TIER);
    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = registerShovel(MWItemTiers.SAPPHIRE_ITEM_TIER);
    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = registerPickaxe(MWItemTiers.SAPPHIRE_ITEM_TIER);
    public static final RegistryObject<Item> SAPPHIRE_AXE = registerAxe(MWItemTiers.SAPPHIRE_ITEM_TIER, 5.0F, -3.0F);
    public static final RegistryObject<Item> SAPPHIRE_HOE = registerHoe(MWItemTiers.SAPPHIRE_ITEM_TIER, 0.0F);
    public static final RegistryObject<Item> SAPPHIRE_HELMET = registerArmorItem(MWArmorMaterials.SAPPHIRE, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> SAPPHIRE_CHESTPLATE = registerArmorItem(MWArmorMaterials.SAPPHIRE, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> SAPPHIRE_LEGGINGS = registerArmorItem(MWArmorMaterials.SAPPHIRE, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> SAPPHIRE_BOOTS = registerArmorItem(MWArmorMaterials.SAPPHIRE, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> SAPPHIRE_HORSE_ARMOR = registerHorseArmorItem(MWArmorMaterials.SAPPHIRE, 13);
    public static final RegistryObject<Item> SKELETON_CHESTPLATE = registerCosmeticArmorItem(MWArmorMaterials.SKELETON, ArmorItem.Type.CHESTPLATE, Rarity.UNCOMMON);
    public static final RegistryObject<Item> SKELETON_LEGGINGS = registerCosmeticArmorItem(MWArmorMaterials.SKELETON, ArmorItem.Type.LEGGINGS, Rarity.UNCOMMON);
    public static final RegistryObject<Item> SKELETON_BOOTS = registerCosmeticArmorItem(MWArmorMaterials.SKELETON, ArmorItem.Type.BOOTS, Rarity.UNCOMMON);
    public static final RegistryObject<Item> WITHER_SKELETON_CHESTPLATE = registerCosmeticArmorItem(MWArmorMaterials.WITHER_SKELETON, ArmorItem.Type.CHESTPLATE, Rarity.UNCOMMON);
    public static final RegistryObject<Item> WITHER_SKELETON_LEGGINGS = registerCosmeticArmorItem(MWArmorMaterials.WITHER_SKELETON, ArmorItem.Type.LEGGINGS, Rarity.UNCOMMON);
    public static final RegistryObject<Item> WITHER_SKELETON_BOOTS = registerCosmeticArmorItem(MWArmorMaterials.WITHER_SKELETON, ArmorItem.Type.BOOTS, Rarity.UNCOMMON);
    public static final RegistryObject<Item> STRAY_SKULL = registerSkull(MWArmorMaterials.STRAY, false, Suppliers.memoize(() -> MWBlocks.STRAY_SKULL.get()), Suppliers.memoize(() -> MWBlocks.STRAY_WALL_SKULL.get()));
    public static final RegistryObject<Item> STRAY_CHESTPLATE = registerCosmeticArmorItem(MWArmorMaterials.STRAY, ArmorItem.Type.CHESTPLATE, Rarity.UNCOMMON);
    public static final RegistryObject<Item> STRAY_LEGGINGS = registerCosmeticArmorItem(MWArmorMaterials.STRAY, ArmorItem.Type.LEGGINGS, Rarity.UNCOMMON);
    public static final RegistryObject<Item> STRAY_BOOTS = registerCosmeticArmorItem(MWArmorMaterials.STRAY, ArmorItem.Type.BOOTS, Rarity.UNCOMMON);
    public static final RegistryObject<Item> ZOMBIE_CHESTPLATE = registerCosmeticArmorItem(MWArmorMaterials.ZOMBIE, ArmorItem.Type.CHESTPLATE, Rarity.UNCOMMON);
    public static final RegistryObject<Item> ZOMBIE_LEGGINGS = registerCosmeticArmorItem(MWArmorMaterials.ZOMBIE, ArmorItem.Type.LEGGINGS, Rarity.UNCOMMON);
    public static final RegistryObject<Item> ZOMBIE_BOOTS = registerCosmeticArmorItem(MWArmorMaterials.ZOMBIE, ArmorItem.Type.BOOTS, Rarity.UNCOMMON);
    public static final RegistryObject<Item> HUSK_HEAD = registerSkull(MWArmorMaterials.HUSK, true, Suppliers.memoize(() -> MWBlocks.HUSK_HEAD.get()), Suppliers.memoize(() -> MWBlocks.HUSK_WALL_HEAD.get()));
    public static final RegistryObject<Item> HUSK_CHESTPLATE = registerCosmeticArmorItem(MWArmorMaterials.HUSK, ArmorItem.Type.CHESTPLATE, Rarity.UNCOMMON);
    public static final RegistryObject<Item> HUSK_LEGGINGS = registerCosmeticArmorItem(MWArmorMaterials.HUSK, ArmorItem.Type.LEGGINGS, Rarity.UNCOMMON);
    public static final RegistryObject<Item> HUSK_BOOTS = registerCosmeticArmorItem(MWArmorMaterials.HUSK, ArmorItem.Type.BOOTS, Rarity.UNCOMMON);
    public static final RegistryObject<Item> DROWNED_HEAD = registerSkull(MWArmorMaterials.DROWNED, true, Suppliers.memoize(() -> MWBlocks.DROWNED_HEAD.get()), Suppliers.memoize(() -> MWBlocks.DROWNED_WALL_HEAD.get()));
    public static final RegistryObject<Item> DROWNED_CHESTPLATE = registerCosmeticArmorItem(MWArmorMaterials.DROWNED, ArmorItem.Type.CHESTPLATE, Rarity.UNCOMMON);
    public static final RegistryObject<Item> DROWNED_LEGGINGS = registerCosmeticArmorItem(MWArmorMaterials.DROWNED, ArmorItem.Type.LEGGINGS, Rarity.UNCOMMON);
    public static final RegistryObject<Item> DROWNED_BOOTS = registerCosmeticArmorItem(MWArmorMaterials.DROWNED, ArmorItem.Type.BOOTS, Rarity.UNCOMMON);
    public static final RegistryObject<Item> CREEPER_CHESTPLATE = registerCosmeticArmorItem(MWArmorMaterials.CREEPER, ArmorItem.Type.CHESTPLATE, Rarity.UNCOMMON);
    public static final RegistryObject<Item> CREEPER_LEGGINGS = registerCosmeticArmorItem(MWArmorMaterials.CREEPER, ArmorItem.Type.LEGGINGS, Rarity.UNCOMMON);
    public static final RegistryObject<Item> CREEPER_BOOTS = registerCosmeticArmorItem(MWArmorMaterials.CREEPER, ArmorItem.Type.BOOTS, Rarity.UNCOMMON);
    public static final RegistryObject<Item> PIGLIN_CHESTPLATE = registerCosmeticArmorItem(MWArmorMaterials.PIGLIN, ArmorItem.Type.CHESTPLATE, Rarity.UNCOMMON);
    public static final RegistryObject<Item> PIGLIN_LEGGINGS = registerCosmeticArmorItem(MWArmorMaterials.PIGLIN, ArmorItem.Type.LEGGINGS, Rarity.UNCOMMON);
    public static final RegistryObject<Item> PIGLIN_BOOTS = registerCosmeticArmorItem(MWArmorMaterials.PIGLIN, ArmorItem.Type.BOOTS, Rarity.UNCOMMON);
    //public static final RegistryObject<Item> WITCH_HAT = RegisterHelper.registerItem("witch_hat", WitchHatItem::new);
    public static final RegistryObject<Item> WITCH_CHESTPLATE = registerCosmeticArmorItem(MWArmorMaterials.WITCH, ArmorItem.Type.CHESTPLATE, Rarity.UNCOMMON);
    public static final RegistryObject<Item> WITCH_LEGGINGS = registerCosmeticArmorItem(MWArmorMaterials.WITCH, ArmorItem.Type.LEGGINGS, Rarity.UNCOMMON);
    public static final RegistryObject<Item> WITCH_BOOTS = registerCosmeticArmorItem(MWArmorMaterials.WITCH, ArmorItem.Type.BOOTS, Rarity.UNCOMMON);

    //#endregion

    //#region TNT Minecarts

    public static final RegistryObject<Item> DISGUISED_GRASS_TNT_MINECART = registerTntMinecart(MWMinecartItem.Type.DISGUISED_GRASS_TNT, MWPrimedTnt.Type.DISGUISED_GRASS);
    public static final RegistryObject<Item> DISGUISED_DIRT_TNT_MINECART = registerTntMinecart(MWMinecartItem.Type.DISGUISED_DIRT_TNT, MWPrimedTnt.Type.DISGUISED_DIRT);
    public static final RegistryObject<Item> DISGUISED_SAND_TNT_MINECART = registerTntMinecart(MWMinecartItem.Type.DISGUISED_SAND_TNT, MWPrimedTnt.Type.DISGUISED_SAND);
    public static final RegistryObject<Item> DISGUISED_RED_SAND_TNT_MINECART = registerTntMinecart(MWMinecartItem.Type.DISGUISED_RED_SAND_TNT, MWPrimedTnt.Type.DISGUISED_RED_SAND);
    public static final RegistryObject<Item> DISGUISED_STONE_TNT_MINECART = registerTntMinecart(MWMinecartItem.Type.DISGUISED_STONE_TNT, MWPrimedTnt.Type.DISGUISED_STONE);
    public static final RegistryObject<Item> DISGUISED_CAKE_TNT_MINECART = registerTntMinecart(MWMinecartItem.Type.DISGUISED_CAKE_TNT, MWPrimedTnt.Type.DISGUISED_CAKE);
    public static final RegistryObject<Item> MEGA_TNT_MINECART = registerTntMinecart(MWMinecartItem.Type.MEGA_TNT, MWPrimedTnt.Type.MEGA);
    public static final RegistryObject<Item> SUPER_TNT_MINECART = registerTntMinecart(MWMinecartItem.Type.SUPER_TNT, MWPrimedTnt.Type.SUPER);
    public static final RegistryObject<Item> HYPER_TNT_MINECART = registerTntMinecart(MWMinecartItem.Type.HYPER_TNT, MWPrimedTnt.Type.HYPER);

    //#endregion

    //#region Chest Minecarts

    public static final RegistryObject<Item> SPRUCE_CHEST_MINECART = registerChestMinecart(MWMinecartItem.Type.SPRUCE_CHEST, WoodType.SPRUCE);
    public static final RegistryObject<Item> BIRCH_CHEST_MINECART = registerChestMinecart(MWMinecartItem.Type.BIRCH_CHEST, WoodType.BIRCH);
    public static final RegistryObject<Item> JUNGLE_CHEST_MINECART = registerChestMinecart(MWMinecartItem.Type.JUNGLE_CHEST, WoodType.JUNGLE);
    public static final RegistryObject<Item> ACACIA_CHEST_MINECART = registerChestMinecart(MWMinecartItem.Type.ACACIA_CHEST, WoodType.ACACIA);
    public static final RegistryObject<Item> DARK_OAK_CHEST_MINECART = registerChestMinecart(MWMinecartItem.Type.DARK_OAK_CHEST, WoodType.DARK_OAK);
    public static final RegistryObject<Item> MANGROVE_CHEST_MINECART = registerChestMinecart(MWMinecartItem.Type.MANGROVE_CHEST, WoodType.MANGROVE);
    public static final RegistryObject<Item> CHERRY_CHEST_MINECART = registerChestMinecart(MWMinecartItem.Type.CHERRY_CHEST, WoodType.CHERRY);
    public static final RegistryObject<Item> BAMBOO_CHEST_MINECART = registerChestMinecart(MWMinecartItem.Type.BAMBOO_CHEST, WoodType.BAMBOO);
    public static final RegistryObject<Item> CRIMSON_CHEST_MINECART = registerChestMinecart(MWMinecartItem.Type.CRIMSON_CHEST, WoodType.CRIMSON);
    public static final RegistryObject<Item> WARPED_CHEST_MINECART = registerChestMinecart(MWMinecartItem.Type.WARPED_CHEST, WoodType.WARPED);
    public static final RegistryObject<Item> APPLE_CHEST_MINECART = registerChestMinecart(MWMinecartItem.Type.APPLE_CHEST, MWWoodTypes.MWWoodTypeNames.APPLE);
    public static final RegistryObject<Item> PALM_CHEST_MINECART = registerChestMinecart(MWMinecartItem.Type.PALM_CHEST, MWWoodTypes.MWWoodTypeNames.PALM);
    public static final RegistryObject<Item> DEAD_CHEST_MINECART = registerChestMinecart(MWMinecartItem.Type.DEAD_CHEST, MWWoodTypes.MWWoodTypeNames.DEAD);
    public static final RegistryObject<Item> SCULK_CHEST_MINECART = registerChestMinecart(MWMinecartItem.Type.SCULK_CHEST, MWWoodTypes.MWWoodTypeNames.SCULK);
    public static final RegistryObject<Item> ICE_CHEST_MINECART = registerChestMinecart(MWMinecartItem.Type.ICE_CHEST, MWWoodTypes.MWWoodTypeNames.ICE);

    //#endregion

    //#region Chests and Trapped Chests

    public static final RegistryObject<Item> SPRUCE_CHEST = registerChest(WoodType.SPRUCE, false, Suppliers.memoize(() -> MWBlocks.SPRUCE_CHEST.get()));
    public static final RegistryObject<Item> SPRUCE_TRAPPED_CHEST = registerChest(WoodType.SPRUCE, true, Suppliers.memoize(() -> MWBlocks.SPRUCE_TRAPPED_CHEST.get()));
    public static final RegistryObject<Item> BIRCH_CHEST = registerChest(WoodType.BIRCH, false, Suppliers.memoize(() -> MWBlocks.BIRCH_CHEST.get()));
    public static final RegistryObject<Item> BIRCH_TRAPPED_CHEST = registerChest(WoodType.BIRCH, true, Suppliers.memoize(() -> MWBlocks.BIRCH_TRAPPED_CHEST.get()));
    public static final RegistryObject<Item> JUNGLE_CHEST = registerChest(WoodType.JUNGLE, false, Suppliers.memoize(() -> MWBlocks.JUNGLE_CHEST.get()));
    public static final RegistryObject<Item> JUNGLE_TRAPPED_CHEST = registerChest(WoodType.JUNGLE, true, Suppliers.memoize(() -> MWBlocks.JUNGLE_TRAPPED_CHEST.get()));
    public static final RegistryObject<Item> ACACIA_CHEST = registerChest(WoodType.ACACIA, false, Suppliers.memoize(() -> MWBlocks.ACACIA_CHEST.get()));
    public static final RegistryObject<Item> ACACIA_TRAPPED_CHEST = registerChest(WoodType.ACACIA, true, Suppliers.memoize(() -> MWBlocks.ACACIA_TRAPPED_CHEST.get()));
    public static final RegistryObject<Item> DARK_OAK_CHEST = registerChest(WoodType.DARK_OAK, false, Suppliers.memoize(() -> MWBlocks.DARK_OAK_CHEST.get()));
    public static final RegistryObject<Item> DARK_OAK_TRAPPED_CHEST = registerChest(WoodType.DARK_OAK, true, Suppliers.memoize(() -> MWBlocks.DARK_OAK_TRAPPED_CHEST.get()));
    public static final RegistryObject<Item> MANGROVE_CHEST = registerChest(WoodType.MANGROVE, false, Suppliers.memoize(() -> MWBlocks.MANGROVE_CHEST.get()));
    public static final RegistryObject<Item> MANGROVE_TRAPPED_CHEST = registerChest(WoodType.MANGROVE, true, Suppliers.memoize(() -> MWBlocks.MANGROVE_TRAPPED_CHEST.get()));
    public static final RegistryObject<Item> CHERRY_CHEST = registerChest(WoodType.CHERRY, false, Suppliers.memoize(() -> MWBlocks.CHERRY_CHEST.get()));
    public static final RegistryObject<Item> CHERRY_TRAPPED_CHEST = registerChest(WoodType.CHERRY, true, Suppliers.memoize(() -> MWBlocks.CHERRY_TRAPPED_CHEST.get()));
    public static final RegistryObject<Item> BAMBOO_CHEST = registerChest(WoodType.BAMBOO, false, Suppliers.memoize(() -> MWBlocks.BAMBOO_CHEST.get()));
    public static final RegistryObject<Item> BAMBOO_TRAPPED_CHEST = registerChest(WoodType.BAMBOO, true, Suppliers.memoize(() -> MWBlocks.BAMBOO_TRAPPED_CHEST.get()));
    public static final RegistryObject<Item> CRIMSON_CHEST = registerChest(WoodType.CRIMSON, false, Suppliers.memoize(() -> MWBlocks.CRIMSON_CHEST.get()));
    public static final RegistryObject<Item> CRIMSON_TRAPPED_CHEST = registerChest(WoodType.CRIMSON, true, Suppliers.memoize(() -> MWBlocks.CRIMSON_TRAPPED_CHEST.get()));
    public static final RegistryObject<Item> WARPED_CHEST = registerChest(WoodType.WARPED, false, Suppliers.memoize(() -> MWBlocks.WARPED_CHEST.get()));
    public static final RegistryObject<Item> WARPED_TRAPPED_CHEST = registerChest(WoodType.WARPED, true, Suppliers.memoize(() -> MWBlocks.WARPED_TRAPPED_CHEST.get()));
    public static final RegistryObject<Item> APPLE_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.APPLE, false, Suppliers.memoize(() -> MWBlocks.APPLE_CHEST.get()));
    public static final RegistryObject<Item> APPLE_TRAPPED_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.APPLE, true, Suppliers.memoize(() -> MWBlocks.APPLE_TRAPPED_CHEST.get()));
    public static final RegistryObject<Item> PALM_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.PALM, false, Suppliers.memoize(() -> MWBlocks.PALM_CHEST.get()));
    public static final RegistryObject<Item> PALM_TRAPPED_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.PALM, true, Suppliers.memoize(() -> MWBlocks.PALM_TRAPPED_CHEST.get()));
    public static final RegistryObject<Item> DEAD_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.DEAD, false, Suppliers.memoize(() -> MWBlocks.DEAD_CHEST.get()));
    public static final RegistryObject<Item> DEAD_TRAPPED_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.DEAD, true, Suppliers.memoize(() -> MWBlocks.DEAD_TRAPPED_CHEST.get()));
    public static final RegistryObject<Item> SCULK_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.SCULK, false, Suppliers.memoize(() -> MWBlocks.SCULK_CHEST.get()));
    public static final RegistryObject<Item> SCULK_TRAPPED_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.SCULK, true, Suppliers.memoize(() -> MWBlocks.SCULK_TRAPPED_CHEST.get()));
    public static final RegistryObject<Item> ICE_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.ICE, false, Suppliers.memoize(() -> MWBlocks.ICE_CHEST.get()));
    public static final RegistryObject<Item> ICE_TRAPPED_CHEST = registerChest(MWWoodTypes.MWWoodTypeNames.ICE, true, Suppliers.memoize(() -> MWBlocks.ICE_TRAPPED_CHEST.get()));

    //#endregion

    //#region Signs and Hanging Signs

    public static final RegistryObject<Item> APPLE_SIGN = registerSign(MWWoodTypes.MWWoodTypeNames.APPLE, Suppliers.memoize(() -> MWBlocks.APPLE_SIGN.get()), Suppliers.memoize(() -> MWBlocks.APPLE_WALL_SIGN.get()));
    public static final RegistryObject<Item> APPLE_HANGING_SIGN = registerHangingSign(MWWoodTypes.MWWoodTypeNames.APPLE, Suppliers.memoize(() -> MWBlocks.APPLE_HANGING_SIGN.get()), Suppliers.memoize(() -> MWBlocks.APPLE_WALL_HANGING_SIGN.get()));
    public static final RegistryObject<Item> PALM_SIGN = registerSign(MWWoodTypes.MWWoodTypeNames.PALM, Suppliers.memoize(() -> MWBlocks.PALM_SIGN.get()), Suppliers.memoize(() -> MWBlocks.PALM_WALL_SIGN.get()));
    public static final RegistryObject<Item> PALM_HANGING_SIGN = registerHangingSign(MWWoodTypes.MWWoodTypeNames.PALM, Suppliers.memoize(() -> MWBlocks.PALM_HANGING_SIGN.get()), Suppliers.memoize(() -> MWBlocks.PALM_WALL_HANGING_SIGN.get()));
    public static final RegistryObject<Item> DEAD_SIGN = registerSign(MWWoodTypes.MWWoodTypeNames.DEAD, Suppliers.memoize(() -> MWBlocks.DEAD_SIGN.get()), Suppliers.memoize(() -> MWBlocks.DEAD_WALL_SIGN.get()));
    public static final RegistryObject<Item> DEAD_HANGING_SIGN = registerHangingSign(MWWoodTypes.MWWoodTypeNames.DEAD, Suppliers.memoize(() -> MWBlocks.DEAD_HANGING_SIGN.get()), Suppliers.memoize(() -> MWBlocks.DEAD_WALL_HANGING_SIGN.get()));
    public static final RegistryObject<Item> SCULK_SIGN = registerSign(MWWoodTypes.MWWoodTypeNames.SCULK, Suppliers.memoize(() -> MWBlocks.SCULK_SIGN.get()), Suppliers.memoize(() -> MWBlocks.SCULK_WALL_SIGN.get()));
    public static final RegistryObject<Item> SCULK_HANGING_SIGN = registerHangingSign(MWWoodTypes.MWWoodTypeNames.SCULK, Suppliers.memoize(() -> MWBlocks.SCULK_HANGING_SIGN.get()), Suppliers.memoize(() -> MWBlocks.SCULK_WALL_HANGING_SIGN.get()));

    //#endregion

    //#region Boats and Chest Boats

    public static final RegistryObject<Item> CRIMSON_BOAT = registerBoat(WoodType.CRIMSON, false, false, MWBoat.Type.CRIMSON);
    public static final RegistryObject<Item> CRIMSON_CHEST_BOAT = registerBoat(WoodType.CRIMSON, true, false, MWBoat.Type.CRIMSON);
    public static final RegistryObject<Item> WARPED_BOAT = registerBoat(WoodType.WARPED, false, false, MWBoat.Type.WARPED);
    public static final RegistryObject<Item> WARPED_CHEST_BOAT = registerBoat(WoodType.WARPED, true, false, MWBoat.Type.WARPED);
    public static final RegistryObject<Item> APPLE_BOAT = registerBoat(MWWoodTypes.MWWoodTypeNames.APPLE, false, false, MWBoat.Type.APPLE);
    public static final RegistryObject<Item> APPLE_CHEST_BOAT = registerBoat(MWWoodTypes.MWWoodTypeNames.APPLE, true, false, MWBoat.Type.APPLE);
    public static final RegistryObject<Item> PALM_RAFT = registerBoat(MWWoodTypes.MWWoodTypeNames.PALM, false, true, MWBoat.Type.PALM);
    public static final RegistryObject<Item> PALM_CHEST_RAFT = registerBoat(MWWoodTypes.MWWoodTypeNames.PALM, true, true, MWBoat.Type.PALM);
    public static final RegistryObject<Item> DEAD_BOAT = registerBoat(MWWoodTypes.MWWoodTypeNames.DEAD, false, false, MWBoat.Type.DEAD);
    public static final RegistryObject<Item> DEAD_CHEST_BOAT = registerBoat(MWWoodTypes.MWWoodTypeNames.DEAD, true, false, MWBoat.Type.DEAD);
    public static final RegistryObject<Item> SCULK_BOAT = registerBoat(MWWoodTypes.MWWoodTypeNames.SCULK, false, false, MWBoat.Type.SCULK);
    public static final RegistryObject<Item> SCULK_CHEST_BOAT = registerBoat(MWWoodTypes.MWWoodTypeNames.SCULK, true, false, MWBoat.Type.SCULK);

    //#endregion

    //#region Torches

    public static final RegistryObject<Item> UNLIT_TORCH = registerTorch("", true, Suppliers.memoize(() -> MWBlocks.UNLIT_TORCH.get()), Suppliers.memoize(() -> MWBlocks.UNLIT_WALL_TORCH.get()));
    public static final RegistryObject<Item> UNLIT_SOUL_TORCH = registerTorch("soul", true, Suppliers.memoize(() -> MWBlocks.UNLIT_SOUL_TORCH.get()), Suppliers.memoize(() -> MWBlocks.UNLIT_SOUL_WALL_TORCH.get()));
    public static final RegistryObject<Item> END_TORCH = registerTorch(MWFireBlock.MWFireType.END, false, Suppliers.memoize(() -> MWBlocks.END_TORCH.get()), Suppliers.memoize(() -> MWBlocks.END_WALL_TORCH.get()));
    public static final RegistryObject<Item> UNLIT_END_TORCH = registerTorch(MWFireBlock.MWFireType.END, true, Suppliers.memoize(() -> MWBlocks.UNLIT_END_TORCH.get()), Suppliers.memoize(() -> MWBlocks.UNLIT_END_WALL_TORCH.get()));
    public static final RegistryObject<Item> SCULK_TORCH = registerTorch(MWFireBlock.MWFireType.SCULK, false, Suppliers.memoize(() -> MWBlocks.SCULK_TORCH.get()), Suppliers.memoize(() -> MWBlocks.SCULK_WALL_TORCH.get()));
    public static final RegistryObject<Item> UNLIT_SCULK_TORCH = registerTorch(MWFireBlock.MWFireType.SCULK, true, Suppliers.memoize(() -> MWBlocks.UNLIT_SCULK_TORCH.get()), Suppliers.memoize(() -> MWBlocks.UNLIT_SCULK_WALL_TORCH.get()));

    //#endregion

    //#region Misc

    public static final RegistryObject<Item> GIFT = registerSpecialRendererBlockItem("gift", Suppliers.memoize(() -> MWBlocks.GIFT.get()));
    public static final RegistryObject<Item> ECHOING_CHARGE_FRAGMENT = registerRareItem("echoing_charge_fragment", Rarity.RARE);
    public static final RegistryObject<Item> SCULK_HEART = registerRareItem("sculk_heart", Rarity.EPIC);
    public static final RegistryObject<Item> DARK_SOUL = registerRareItem("dark_soul", Rarity.EPIC);
    public static final RegistryObject<Item> SOUL = registerSimpleItem("soul");

    //#endregion

    //#endregion

    //#region Methods

    /**
     * Register a simple {@link Item Item}
     *
     * @param name {@link String The Item name}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerSimpleItem(final String name, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new Item(PropertyHelper.item(featureFlags)));
    }

    /**
     * Register a simple {@link Item Item}
     *
     * @param name {@link String The Item name}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerRareItem(final String name, final Rarity rarity, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new Item(PropertyHelper.item(featureFlags).rarity(rarity)));
    }

    /**
     * Register a {@link MWFuelItem Fuel Item}
     *
     * @param name {@link String The Item name}
     * @param burnTime {@link Integer The fuel burn time}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerFuel(final String name, final int burnTime, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new MWFuelItem(burnTime, featureFlags));
    }

    /**
     * Register a {@link Item Food Item}
     *
     * @param name {@link String The Item name}
     * @param nutrition {@link Integer The Food nutrition value}
     * @param saturation {@link Float The Food saturation value}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerFood(final String name, final int nutrition, final float saturation, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new Item(PropertyHelper.item(featureFlags).food(PropertyHelper.food(nutrition, saturation))));
    }

    /**
     * Register a {@link Item Food Item}
     *
     * @param name {@link String The Item name}
     * @param blockSupplier {@link Supplier<Block> The Supplier for the Block this food is referring to}
     * @param nutrition {@link Integer The Food nutrition value}
     * @param saturation {@link Float The Food saturation value}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerFoodBlockItem(final String name, final Supplier<Block> blockSupplier, final int nutrition, final float saturation, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new ItemNameBlockItem(blockSupplier.get(), PropertyHelper.item(featureFlags).food(PropertyHelper.food(nutrition, saturation))));
    }

    /**
     * Register a {@link HorseArmorItem Horse Armor Item}
     *
     * @param armorMaterial {@link ArmorMaterial The Armor Material}
     * @param protection {@link Integer The armor protection amount}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerHorseArmorItem(final ArmorMaterial armorMaterial, final int protection, final FeatureFlag... featureFlags) {
        return registerItem(ResourceHelper.armorMaterialName(armorMaterial) + "_horse_armor", () -> new HorseArmorItem(protection, TextureHelper.horseArmor(armorMaterial), PropertyHelper.item(featureFlags).stacksTo(1)));
    }

    /**
     * Register a {@link SwordItem Sword}
     *
     * @param tier {@link Tier The Item tier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerSword(final Tier tier, final FeatureFlag... featureFlags) {
        return registerItem(ResourceHelper.tierName(tier) + "_sword", () -> new SwordItem(tier, 3, -2.4F, PropertyHelper.item(featureFlags)));
    }

    /**
     * Register a {@link ShovelItem Shovel}
     *
     * @param tier {@link Tier The Item Tier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerShovel(final Tier tier, final FeatureFlag... featureFlags) {
        return registerItem(ResourceHelper.tierName(tier) + "_shovel", () -> new ShovelItem(tier, 1.5F, -3.0F, PropertyHelper.item(featureFlags)));
    }

    /**
     * Register a {@link PickaxeItem pickaxe}
     *
     * @param tier {@link Tier The Item Tier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerPickaxe(final Tier tier, final FeatureFlag... featureFlags) {
        return registerItem(ResourceHelper.tierName(tier) + "_pickaxe", () -> new PickaxeItem(tier, 1, -2.8F, PropertyHelper.item(featureFlags)));
    }

    /**
     * Register a {@link AxeItem axe}
     *
     * @param tier {@link Tier The Item Tier}
     * @param attackDamageBonus {@link Float The axe attack damage bonus}
     * @param attackSpeed {@link Float The axe attack speed}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerAxe(final Tier tier, final float attackDamageBonus, final float attackSpeed, final FeatureFlag... featureFlags) {
        return registerItem(ResourceHelper.tierName(tier) + "_axe", () -> new AxeItem(tier, attackDamageBonus, attackSpeed, PropertyHelper.item(featureFlags)));
    }

    /**
     * Register a {@link HoeItem hoe}
     *
     * @param tier {@link Tier The Item Tier}
     * @param attackSpeed {@link Float The hoe attack speed}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerHoe(final Tier tier, final float attackSpeed, final FeatureFlag... featureFlags) {
        final ForgeTier hoeTier = new ForgeTier(tier.getLevel(), tier.getUses(), tier.getSpeed(), 0, tier.getEnchantmentValue(), Objects.requireNonNull(tier.getTag()), tier::getRepairIngredient);
        return registerItem(ResourceHelper.tierName(tier) + "_hoe", () -> new HoeItem(hoeTier, 0, attackSpeed, PropertyHelper.item(featureFlags)));
    }

    /**
     * Register an {@link ArmorItem Armor Item}
     *
     * @param armorMaterial {@link ArmorMaterial The Armor Material}
     * @param slot {@link ArmorItem.Type The Armor Item slot}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerArmorItem(final ArmorMaterial armorMaterial, final ArmorItem.Type slot, final FeatureFlag... featureFlags) {
        return registerItem(ResourceHelper.armorMaterialName(armorMaterial) + "_" + slot.getName().toLowerCase(Locale.ROOT), () -> new ArmorItem(armorMaterial, slot, PropertyHelper.item(featureFlags)));
    }

    /**
     * Register a Cosmetic {@link ArmorItem Armor Item}
     *
     * @param armorMaterial {@link ArmorMaterial The Armor Material}
     * @param slot {@link ArmorItem.Type The Armor Item slot}
     * @param rarity {@link Rarity The Armor Item rarity value}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerCosmeticArmorItem(final ArmorMaterial armorMaterial, final ArmorItem.Type slot, final Rarity rarity, final FeatureFlag... featureFlags) {
        return registerItem(ResourceHelper.armorMaterialName(armorMaterial) + "_" + slot.getName().toLowerCase(Locale.ROOT), () -> new ArmorItem(armorMaterial, slot, PropertyHelper.item(featureFlags).rarity(rarity)));
    }

    /**
     * Register a {@link MWMinecartTnt TNT Minecart}
     *
     * @param minecartType {@link MWMinecartItem.Type The Minecart Type}
     * @param tntType {@link MWPrimedTnt.Type The TNT Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerTntMinecart(final MWMinecartItem.Type minecartType, final MWPrimedTnt.Type tntType, final FeatureFlag... featureFlags) {
        return registerItem(tntType.name().toLowerCase(Locale.ROOT) + "_tnt_minecart", () -> new MWTntMinecartItem(minecartType, tntType, featureFlags));
    }

    /**
     * Register a {@link MWMinecartChest Chest Minecart}
     *
     * @param minecartType {@link MWMinecartItem.Type The Minecart Type}
     * @param woodType {@link WoodType The Chest Wood Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerChestMinecart(final MWMinecartItem.Type minecartType, final WoodType woodType, final FeatureFlag... featureFlags) {
        return registerChestMinecart(minecartType, ResourceHelper.woodName(woodType), featureFlags);
    }

    /**
     * Register a {@link MWMinecartChest Chest Minecart}
     *
     * @param minecartType {@link MWMinecartItem.Type The Minecart Type}
     * @param woodName {@link String The Chest Wood name}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerChestMinecart(final MWMinecartItem.Type minecartType, final String woodName, final FeatureFlag... featureFlags) {
        return registerItem(woodName + "_chest_minecart", () -> new MWMinecartChestItem(minecartType, featureFlags));
    }

    /**
     * Register a {@link SignItem Sign}
     *
     * @param woodName {@link String The wood name}
     * @param signSupplier {@link Supplier<Block> The Supplier for the Standing Sign this Sign will place}
     * @param wallSignSupplier {@link Supplier<Block> The Supplier for the Wall Sign this Sign will place}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerSign(final String woodName, final Supplier<Block> signSupplier, final Supplier<Block> wallSignSupplier, final FeatureFlag... featureFlags) {
        return registerItem(woodName + "_sign", Suppliers.memoize(() -> new SignItem(PropertyHelper.item(featureFlags).stacksTo(16), signSupplier.get(), wallSignSupplier.get())));
    }

    /**
     * Register an {@link HangingSignItem Hanging Sign}
     *
     * @param woodName {@link String The wood name}
     * @param signSupplier {@link Supplier<Block> The Supplier for the Ceiling Hanging Sign this Hanging Sign will place}
     * @param wallSignSupplier {@link Supplier<Block> The Supplier for the Wall Hanging Sign this Hanging Sign will place}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerHangingSign(final String woodName, final Supplier<Block> signSupplier, final Supplier<Block> wallSignSupplier, final FeatureFlag... featureFlags) {
        return registerItem(woodName + "_hanging_sign", Suppliers.memoize(() -> new HangingSignItem(signSupplier.get(), wallSignSupplier.get(), PropertyHelper.item(featureFlags).stacksTo(16))));
    }

    /**
     * Register a {@link Item Skull Item}
     *
     * @param armorMaterial {@link ArmorMaterial The Armor Material}
     * @param isHead {@link Boolean If the Skull represents a mob head}
     * @param standingBlockSupplier {@link Supplier<Block> The Supplier for the Standing Block}
     * @param wallBlockSupplier {@link Supplier<Block> The Supplier for the Wall Block}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerSkull(final MWArmorMaterials armorMaterial, final boolean isHead, final Supplier<Block> standingBlockSupplier, final Supplier<Block> wallBlockSupplier, final FeatureFlag... featureFlags) {
        return registerStandingAndWallBlockItem(ResourceHelper.armorMaterialName(armorMaterial) + "_" + (isHead ? "head" : "skull"), standingBlockSupplier, wallBlockSupplier, PropertyHelper.item(featureFlags).rarity(Rarity.UNCOMMON));
    }

    /**
     * Register a {@link Item Torch Item}
     *
     * @param fireType {@link MWFireBlock.MWFireType The Fire Type}
     * @param isUnlit {@link Boolean If the Torch is unlit}
     * @param standingBlockSupplier {@link Supplier<Block> The Supplier for the Standing Block}
     * @param wallBlockSupplier {@link Supplier<Block> The Supplier for the Wall Block}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerTorch(final MWFireBlock.MWFireType fireType, final boolean isUnlit, final Supplier<Block> standingBlockSupplier, final Supplier<Block> wallBlockSupplier, final FeatureFlag... featureFlags) {
        return registerTorch(ResourceHelper.fireName(fireType), isUnlit, standingBlockSupplier, wallBlockSupplier, featureFlags);
    }

    /**
     * Register a {@link Item Torch Item}
     *
     * @param materialName {@link String The Item material name}
     * @param isUnlit {@link Boolean If the Torch is unlit}
     * @param standingBlockSupplier {@link Supplier<Block> The Supplier for the Standing Block}
     * @param wallBlockSupplier {@link Supplier<Block> The Supplier for the Wall Block}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerTorch(final String materialName, final boolean isUnlit, final Supplier<Block> standingBlockSupplier, final Supplier<Block> wallBlockSupplier, final FeatureFlag... featureFlags) {
        return registerStandingAndWallBlockItem( (isUnlit ? "unlit_" : "") + materialName + (materialName.isEmpty() ? "" : "_") + "torch", standingBlockSupplier, wallBlockSupplier, PropertyHelper.item(featureFlags));
    }

    /**
     * Register a {@link Item Boat Item}
     *
     * @param woodType {@link WoodType The Chest Boat Wood Type}
     * @param isChestBoat {@link Boolean If the Boat is a Chest Boat}
     * @param isRaft {@link Boolean If the Boat is a Raft}
     * @param type {@link MWBoat.Type The Boat Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerBoat(final WoodType woodType, final boolean isChestBoat, final boolean isRaft,final MWBoat.Type type, final FeatureFlag... featureFlags) {
        return registerBoat( ResourceHelper.woodName (woodType), isChestBoat, isRaft, type, featureFlags);
    }

    /**
     * Register a {@link Item Boat Item}
     *
     * @param materialName {@link String The Item material name}
     * @param isChestBoat {@link Boolean If the Boat is a Chest Boat}
     * @param isRaft {@link Boolean If the Boat is a Raft}
     * @param type {@link MWBoat.Type The Boat Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerBoat(final String materialName, final boolean isChestBoat, final boolean isRaft, final MWBoat.Type type, final FeatureFlag... featureFlags) {
        return registerItem( materialName + (isChestBoat ? "_chest" : "") + "_" + (isRaft ? "raft" : "boat"), Suppliers.memoize(() -> new MWBoatItem(isChestBoat, type, featureFlags)));
    }

    /**
     * Register a {@link StandingAndWallBlockItem Block Item} that can be placed both on the ground
     * and on wall (like Signs, Torches, Skulls...)
     *
     * @param name {@link String The Item name}
     * @param standingBlockSupplier {@link Supplier<Block> The Supplier for the Standing Block}
     * @param wallBlockSupplier {@link Supplier<Block> The Supplier for the Wall Block}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerStandingAndWallBlockItem(final String name, final Supplier<Block> standingBlockSupplier, final Supplier<Block> wallBlockSupplier, final FeatureFlag... featureFlags) {
        return registerStandingAndWallBlockItem(name, standingBlockSupplier, wallBlockSupplier, PropertyHelper.item(featureFlags));
    }

    /**
     * Register a {@link StandingAndWallBlockItem Block Item} that can be placed both on the ground
     * and on wall (like Signs, Torches, Skulls...)
     *
     * @param name {@link String The Item name}
     * @param standingBlockSupplier {@link Supplier<Block> The Supplier for the Standing Block}
     * @param wallBlockSupplier {@link Supplier<Block> The Supplier for the Wall Block}
     * @param properties {@link Item.Properties The Item Properties}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerStandingAndWallBlockItem(final String name, final Supplier<Block> standingBlockSupplier, final Supplier<Block> wallBlockSupplier, final Item.Properties properties) {
        return registerItem(name, Suppliers.memoize(() -> new StandingAndWallBlockItem(standingBlockSupplier.get(), wallBlockSupplier.get(), properties, Direction.DOWN)));
    }

    /**
     * Register a {@link MWItemNameBlockItem Block Item with a special rendering}
     *
     * @param woodType {@link WoodType The Chest Wood Type}
     * @param isTrappedChest {@link Boolean If the Chest is a Trapped Chest}
     * @param blockSupplier {@link Supplier<Block> The Supplier for the Block that will be placed by this Item}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerChest(final WoodType woodType, final boolean isTrappedChest, final Supplier<? extends Block> blockSupplier, final FeatureFlag...featureFlags) {
        return registerChest(ResourceHelper.woodName(woodType), isTrappedChest, blockSupplier, featureFlags);
    }

    /**
     * Register a {@link MWItemNameBlockItem Block Item with a special rendering}
     *
     * @param woodName {@link String The Chest Wood Name}
     * @param isTrappedChest {@link Boolean If the Chest is a Trapped Chest}
     * @param blockSupplier {@link Supplier<Block> The Supplier for the Block that will be placed by this Item}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerChest(final String woodName, final boolean isTrappedChest, final Supplier<? extends Block> blockSupplier, final FeatureFlag...featureFlags) {
        return registerSpecialRendererBlockItem(woodName + "_" + (isTrappedChest ? "trapped_": "") + "chest", blockSupplier, featureFlags);
    }

    /**
     * Register a {@link MWItemNameBlockItem Block Item with a special rendering}
     *
     * @param name {@link String The Item Name}
     * @param blockSupplier {@link Supplier<Block> The Supplier for the Block that will be placed by this Item}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    private static RegistryObject<Item> registerSpecialRendererBlockItem(final String name, final Supplier<? extends Block> blockSupplier, final FeatureFlag...featureFlags) {
        return registerItem(name, () -> new MWItemNameBlockItem(blockSupplier, featureFlags));
    }

    /**
     * Register a {@link BlockItem Block Item}
     *
     * @param name {@link String The Item name}
     * @param blockSupplier {@link Supplier<Block> The Supplier for the Block that will be placed by this Item}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    static RegistryObject<Item> registerBlockItem(final String name, final Supplier<? extends Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerItem(name, () -> new BlockItem(blockSupplier.get(), PropertyHelper.item(featureFlags)));
    }

    /**
     * Register an {@link Item Item}
     *
     * @param name {@link String The Item name}
     * @param itemSupplier {@link Supplier The Item supplier}
     * @return {@link RegistryObject<Item> The registered Item}
     */
    static RegistryObject<Item> registerItem(final String name, final Supplier<Item> itemSupplier) {
        return ITEMS.register(name, itemSupplier);
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link Item Items}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        MWPebbles.Items.register();
        ITEMS.register(eventBus);
    }

    //#endregion

}