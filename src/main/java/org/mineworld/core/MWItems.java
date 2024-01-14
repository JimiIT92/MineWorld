package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.entity.vehicle.MWMinecartTnt;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.helper.ResourceHelper;
import org.mineworld.helper.TextureHelper;
import org.mineworld.item.MWFuelItem;
import org.mineworld.item.MWMinecartItem;
import org.mineworld.item.MWTntMinecartItem;

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
     * Register a {@link BlockItem Block Item}
     *
     * @param name {@link String The Block name}
     * @param blockSupplier {@link Supplier The Block supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
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