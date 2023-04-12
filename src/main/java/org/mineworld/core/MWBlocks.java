package org.mineworld.core;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.block.*;
import org.mineworld.entity.MWPrimedTnt;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link Block blocks}
 */
public final class MWBlocks {

    /**
     * {@link DeferredRegister<Block> The block registry}
     */
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MineWorld.MODID);

    //#region Blocks

    public static final RegistryObject<Block> SILVER_ORE = registerOverworldOreBlock("silver_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = registerOverworldOreBlock("deepslate_silver_ore", true);
    public static final RegistryObject<Block> ALUMINUM_ORE = registerOverworldOreBlock("aluminum_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_ALUMINUM_ORE = registerOverworldOreBlock("deepslate_aluminum_ore", true);
    public static final RegistryObject<Block> RUBY_ORE = registerOverworldOreBlock("ruby_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_RUBY_ORE = registerOverworldOreBlock("deepslate_ruby_ore", true);
    public static final RegistryObject<Block> SAPPHIRE_ORE = registerOverworldOreBlock("sapphire_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = registerOverworldOreBlock("deepslate_sapphire_ore", true);
    public static final RegistryObject<Block> PYRITE_ORE = registerNetherOreBlock("pyrite_ore");
    public static final RegistryObject<Block> RAW_SILVER_BLOCK = registerRawOreBlock("raw_silver_block", MWColors.RAW_SILVER.toMaterialColor());
    public static final RegistryObject<Block> RAW_ALUMINUM_BLOCK = registerRawOreBlock("raw_aluminum_block", MWColors.RAW_ALUMINUM.toMaterialColor());
    public static final RegistryObject<Block> RAW_BRONZE_BLOCK = registerRawOreBlock("raw_bronze_block", MWColors.RAW_BRONZE.toMaterialColor());
    public static final RegistryObject<Block> SILVER_BLOCK = registerMetalOreStorageBlock("silver_block", MWColors.SILVER.toMaterialColor());
    public static final RegistryObject<Block> ALUMINUM_BLOCK = registerMetalOreStorageBlock("aluminum_block", MWColors.ALUMINUM.toMaterialColor());
    public static final RegistryObject<Block> BRONZE_BLOCK = registerSimpleBlock("bronze_block", basicBlockProperties(Material.METAL, MWColors.BRONZE.toMaterialColor(),3.0F, 6.0F, true, SoundType.COPPER));
    public static final RegistryObject<Block> RUBY_BLOCK = registerMetalOreStorageBlock("ruby_block", MWColors.RUBY.toMaterialColor());
    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerMetalOreStorageBlock("sapphire_block", MWColors.SAPPHIRE.toMaterialColor());
    public static final RegistryObject<Block> PYRITE_BLOCK = registerFuelBlock("pyrite_block", MWColors.PYRITE.toMaterialColor(), 1200);
    public static final RegistryObject<Block> CHARCOAL_BLOCK = registerFuelBlock("charcoal_block", MWColors.CHARCOAL.toMaterialColor(), 800);
    public static final RegistryObject<Block> MARBLE = registerSimpleBlock("marble", copyFrom(Blocks.TUFF).color(MWColors.MARBLE.toMaterialColor()));
    public static final RegistryObject<Block> WHITE_MARBLE = registerSimpleBlock("white_marble", copyFrom(Blocks.TUFF).color(MaterialColor.TERRACOTTA_WHITE));
    public static final RegistryObject<Block> ORANGE_MARBLE = registerSimpleBlock("orange_marble", copyFrom(Blocks.TUFF).color(MaterialColor.TERRACOTTA_ORANGE));
    public static final RegistryObject<Block> MAGENTA_MARBLE = registerSimpleBlock("magenta_marble", copyFrom(Blocks.TUFF).color(MaterialColor.TERRACOTTA_MAGENTA));
    public static final RegistryObject<Block> LIGHT_BLUE_MARBLE = registerSimpleBlock("light_blue_marble", copyFrom(Blocks.TUFF).color(MaterialColor.TERRACOTTA_LIGHT_BLUE));
    public static final RegistryObject<Block> YELLOW_MARBLE = registerSimpleBlock("yellow_marble", copyFrom(Blocks.TUFF).color(MaterialColor.TERRACOTTA_YELLOW));
    public static final RegistryObject<Block> LIME_MARBLE = registerSimpleBlock("lime_marble", copyFrom(Blocks.TUFF).color(MaterialColor.TERRACOTTA_LIGHT_GREEN));
    public static final RegistryObject<Block> PINK_MARBLE = registerSimpleBlock("pink_marble", copyFrom(Blocks.TUFF).color(MaterialColor.TERRACOTTA_PINK));
    public static final RegistryObject<Block> GRAY_MARBLE = registerSimpleBlock("gray_marble", copyFrom(Blocks.TUFF).color(MaterialColor.TERRACOTTA_GRAY));
    public static final RegistryObject<Block> LIGHT_GRAY_MARBLE = registerSimpleBlock("light_gray_marble", copyFrom(Blocks.TUFF).color(MaterialColor.TERRACOTTA_LIGHT_GRAY));
    public static final RegistryObject<Block> CYAN_MARBLE = registerSimpleBlock("cyan_marble", copyFrom(Blocks.TUFF).color(MaterialColor.TERRACOTTA_CYAN));
    public static final RegistryObject<Block> PURPLE_MARBLE = registerSimpleBlock("purple_marble", copyFrom(Blocks.TUFF).color(MaterialColor.TERRACOTTA_PURPLE));
    public static final RegistryObject<Block> BLUE_MARBLE = registerSimpleBlock("blue_marble", copyFrom(Blocks.TUFF).color(MaterialColor.TERRACOTTA_BLUE));
    public static final RegistryObject<Block> BROWN_MARBLE = registerSimpleBlock("brown_marble", copyFrom(Blocks.TUFF).color(MaterialColor.TERRACOTTA_BROWN));
    public static final RegistryObject<Block> GREEN_MARBLE = registerSimpleBlock("green_marble", copyFrom(Blocks.TUFF).color(MaterialColor.TERRACOTTA_GREEN));
    public static final RegistryObject<Block> RED_MARBLE = registerSimpleBlock("red_marble", copyFrom(Blocks.TUFF).color(MaterialColor.TERRACOTTA_RED));
    public static final RegistryObject<Block> BLACK_MARBLE = registerSimpleBlock("black_marble", copyFrom(Blocks.TUFF).color(MaterialColor.TERRACOTTA_BLACK));
    public static final RegistryObject<Block> BLUE_ROSE = registerFlower("blue_rose", () -> MobEffects.SATURATION);
    public static final RegistryObject<Block> BLUE_ROSE_BUSH = registerTallFlower("blue_rose_bush");
    public static final RegistryObject<Block> WHITE_ROSE = registerFlower("white_rose", () -> MobEffects.HEAL);
    public static final RegistryObject<Block> WHITE_ROSE_BUSH = registerTallFlower("white_rose_bush");
    public static final RegistryObject<Block> DISGUISED_GRASS_TNT = registerBlock("disguised_grass_tnt", () -> new MWTntBlock(MWPrimedTnt.Type.DISGUISED_GRASS));
    public static final RegistryObject<Block> DISGUISED_DIRT_TNT = registerBlock("disguised_dirt_tnt", () -> new MWTntBlock(MWPrimedTnt.Type.DISGUISED_DIRT));
    public static final RegistryObject<Block> DISGUISED_SAND_TNT = registerBlock("disguised_sand_tnt", () -> new MWFallableTntBlock(MWPrimedTnt.Type.DISGUISED_SAND, 14406560));
    public static final RegistryObject<Block> DISGUISED_RED_SAND_TNT = registerBlock("disguised_red_sand_tnt", () -> new MWFallableTntBlock(MWPrimedTnt.Type.DISGUISED_RED_SAND, 11098145));
    public static final RegistryObject<Block> DISGUISED_STONE_TNT = registerBlock("disguised_stone_tnt", () -> new MWTntBlock(MWPrimedTnt.Type.DISGUISED_STONE));
    public static final RegistryObject<Block> MEGA_TNT = registerBlock("mega_tnt", () -> new MWTntBlock(MWPrimedTnt.Type.MEGA));
    public static final RegistryObject<Block> SUPER_TNT = registerBlock("super_tnt", () -> new MWTntBlock(MWPrimedTnt.Type.SUPER));
    public static final RegistryObject<Block> HYPER_TNT = registerBlock("hyper_tnt", () -> new MWTntBlock(MWPrimedTnt.Type.HYPER));
    public static final RegistryObject<Block> DAYLIGHT_LAMP = registerBlock("daylight_lamp", DaylightLampBlock::new);
    public static final RegistryObject<Block> CUT_GOLD = registerSimpleBlock("cut_gold", copyFrom(Blocks.GOLD_BLOCK));
    public static final RegistryObject<Block> GOLDEN_STAIRS = registerStair("golden_stairs", Blocks.GOLD_BLOCK);
    public static final RegistryObject<Block> CUT_GOLDEN_STAIRS = registerStair("cut_golden_stairs", () -> CUT_GOLD.get().defaultBlockState());
    public static final RegistryObject<Block> GOLDEN_SLAB = registerSlab("golden_slab", Blocks.GOLD_BLOCK);
    public static final RegistryObject<Block> CUT_GOLDEN_SLAB = registerSlab("cut_golden_slab", () -> CUT_GOLD.get().defaultBlockState());
    public static final RegistryObject<Block> GOLDEN_DOOR = registerDoor("golden_door", true, BlockSetType.GOLD);
    public static final RegistryObject<Block> GOLDEN_TRAPDOOR = registerTrapdoor("golden_trapdoor", true, BlockSetType.GOLD);
    public static final RegistryObject<Block> CUT_GOLDEN_PRESSURE_PLATE = registerWeightedPressurePlate("cut_golden_pressure_plate", 15, MaterialColor.GOLD, BlockSetType.GOLD);
    public static final RegistryObject<Block> GOLDEN_CHAIN = registerChain("golden_chain");
    public static final RegistryObject<Block> GOLDEN_LANTERN = registerLantern("golden_lantern");
    public static final RegistryObject<Block> GOLDEN_SOUL_LANTERN = registerLantern("golden_soul_lantern", 10);
    public static final RegistryObject<Block> GOLD_BARS = registerBars("gold_bars");
    public static final RegistryObject<Block> GOLDEN_CAGE = registerSimpleTranslucentBlock("golden_cage", copyFrom(Blocks.GOLD_BLOCK));
    public static final RegistryObject<Block> CUT_NETHERITE = registerSimpleBlock("cut_netherite", copyFrom(Blocks.NETHERITE_BLOCK));
    public static final RegistryObject<Block> NETHERITE_STAIRS = registerStair("netherite_stairs", Blocks.NETHERITE_BLOCK);
    public static final RegistryObject<Block> CUT_NETHERITE_STAIRS = registerStair("cut_netherite_stairs", () -> CUT_NETHERITE.get().defaultBlockState());
    public static final RegistryObject<Block> NETHERITE_SLAB = registerSlab("netherite_slab", Blocks.NETHERITE_BLOCK);
    public static final RegistryObject<Block> CUT_NETHERITE_SLAB = registerSlab("cut_netherite_slab", () -> CUT_NETHERITE.get().defaultBlockState());
    public static final RegistryObject<Block> NETHERITE_DOOR = registerDoor("netherite_door", true, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> NETHERITE_TRAPDOOR = registerTrapdoor("netherite_trapdoor", true, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> NETHERITE_PRESSURE_PLATE = registerWeightedPressurePlate("netherite_pressure_plate", 100, MaterialColor.COLOR_BLACK, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> CUT_NETHERITE_PRESSURE_PLATE = registerWeightedPressurePlate("cut_netherite_pressure_plate", 100, MaterialColor.COLOR_BLACK, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> NETHERITE_CHAIN = registerChain("netherite_chain");
    public static final RegistryObject<Block> NETHERITE_LANTERN = registerLantern("netherite_lantern");
    public static final RegistryObject<Block> NETHERITE_SOUL_LANTERN = registerLantern("netherite_soul_lantern", 10);
    public static final RegistryObject<Block> NETHERITE_BARS = registerBars("netherite_bars");
    public static final RegistryObject<Block> NETHERITE_CAGE = registerSimpleTranslucentBlock("netherite_cage", copyFrom(Blocks.NETHERITE_BLOCK));
    public static final RegistryObject<Block> CUT_IRON = registerSimpleBlock("cut_iron", copyFrom(Blocks.IRON_BLOCK));
    public static final RegistryObject<Block> CUT_IRON_PRESSURE_PLATE = registerWeightedPressurePlate("cut_iron_pressure_plate", 15, MaterialColor.METAL, BlockSetType.IRON);
    public static final RegistryObject<Block> IRON_STAIRS = registerStair("iron_stairs", Blocks.IRON_BLOCK);
    public static final RegistryObject<Block> CUT_IRON_STAIRS = registerStair("cut_iron_stairs", () -> CUT_IRON.get().defaultBlockState());
    public static final RegistryObject<Block> IRON_SLAB = registerSlab("iron_slab", Blocks.IRON_BLOCK);
    public static final RegistryObject<Block> CUT_IRON_SLAB = registerSlab("cut_iron_slab", () -> CUT_IRON.get().defaultBlockState());
    public static final RegistryObject<Block> IRON_CAGE = registerSimpleTranslucentBlock("iron_cage", copyFrom(Blocks.IRON_BLOCK));
    public static final RegistryObject<Block> CUT_ALUMINUM = registerSimpleBlock("cut_aluminum", () -> copyFrom(ALUMINUM_BLOCK.get()));
    public static final RegistryObject<Block> ALUMINUM_STAIRS = registerStair("aluminum_stairs", () -> ALUMINUM_BLOCK.get().defaultBlockState());
    public static final RegistryObject<Block> CUT_ALUMINUM_STAIRS = registerStair("cut_aluminum_stairs", () -> CUT_ALUMINUM.get().defaultBlockState());
    public static final RegistryObject<Block> ALUMINUM_SLAB = registerSlab("aluminum_slab", () -> ALUMINUM_BLOCK.get().defaultBlockState());
    public static final RegistryObject<Block> CUT_ALUMINUM_SLAB = registerSlab("cut_aluminum_slab", () -> CUT_ALUMINUM.get().defaultBlockState());
    public static final RegistryObject<Block> ALUMINUM_DOOR = registerDoor("aluminum_door", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> ALUMINUM_TRAPDOOR = registerTrapdoor("aluminum_trapdoor", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> ALUMINUM_PRESSURE_PLATE = registerWeightedPressurePlate("aluminum_pressure_plate", 15, MWColors.ALUMINUM.toMaterialColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> CUT_ALUMINUM_PRESSURE_PLATE = registerWeightedPressurePlate("cut_aluminum_pressure_plate", 15, MWColors.ALUMINUM.toMaterialColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> ALUMINUM_CHAIN = registerChain("aluminum_chain");
    public static final RegistryObject<Block> ALUMINUM_LANTERN = registerLantern("aluminum_lantern");
    public static final RegistryObject<Block> ALUMINUM_SOUL_LANTERN = registerLantern("aluminum_soul_lantern", 10);
    public static final RegistryObject<Block> ALUMINUM_BARS = registerBars("aluminum_bars");
    public static final RegistryObject<Block> ALUMINUM_CAGE = registerSimpleTranslucentBlock("aluminum_cage", () -> copyFrom(ALUMINUM_BLOCK.get()));
    public static final RegistryObject<Block> CUT_BRONZE = registerSimpleBlock("cut_bronze", () -> copyFrom(BRONZE_BLOCK.get()));
    public static final RegistryObject<Block> BRONZE_STAIRS = registerStair("bronze_stairs", () -> BRONZE_BLOCK.get().defaultBlockState());
    public static final RegistryObject<Block> CUT_BRONZE_STAIRS = registerStair("cut_bronze_stairs", () -> CUT_BRONZE.get().defaultBlockState());
    public static final RegistryObject<Block> BRONZE_SLAB = registerSlab("bronze_slab", () -> BRONZE_BLOCK.get().defaultBlockState());
    public static final RegistryObject<Block> CUT_BRONZE_SLAB = registerSlab("cut_bronze_slab", () -> CUT_BRONZE.get().defaultBlockState());
    public static final RegistryObject<Block> BRONZE_DOOR = registerDoor("bronze_door", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> BRONZE_TRAPDOOR = registerTrapdoor("bronze_trapdoor", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> BRONZE_PRESSURE_PLATE = registerWeightedPressurePlate("bronze_pressure_plate", 15, MWColors.BRONZE.toMaterialColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> CUT_BRONZE_PRESSURE_PLATE = registerWeightedPressurePlate("cut_bronze_pressure_plate", 15, MWColors.BRONZE.toMaterialColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> BRONZE_CHAIN = registerChain("bronze_chain");
    public static final RegistryObject<Block> BRONZE_LANTERN = registerLantern("bronze_lantern");
    public static final RegistryObject<Block> BRONZE_SOUL_LANTERN = registerLantern("bronze_soul_lantern", 10);
    public static final RegistryObject<Block> BRONZE_BARS = registerBars("bronze_bars");
    public static final RegistryObject<Block> BRONZE_CAGE = registerSimpleTranslucentBlock("bronze_cage", () -> copyFrom(BRONZE_BLOCK.get()));
    public static final RegistryObject<Block> CUT_SILVER = registerSimpleBlock("cut_silver", () -> copyFrom(SILVER_BLOCK.get()));
    public static final RegistryObject<Block> SILVER_STAIRS = registerStair("silver_stairs", () -> SILVER_BLOCK.get().defaultBlockState());
    public static final RegistryObject<Block> CUT_SILVER_STAIRS = registerStair("cut_silver_stairs", () -> CUT_SILVER.get().defaultBlockState());
    public static final RegistryObject<Block> SILVER_SLAB = registerSlab("silver_slab", () -> SILVER_BLOCK.get().defaultBlockState());
    public static final RegistryObject<Block> CUT_SILVER_SLAB = registerSlab("cut_silver_slab", () -> CUT_SILVER.get().defaultBlockState());
    public static final RegistryObject<Block> SILVER_DOOR = registerDoor("silver_door", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> SILVER_TRAPDOOR = registerTrapdoor("silver_trapdoor", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> SILVER_PRESSURE_PLATE = registerWeightedPressurePlate("silver_pressure_plate", 50, MWColors.SILVER.toMaterialColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> CUT_SILVER_PRESSURE_PLATE = registerWeightedPressurePlate("cut_silver_pressure_plate", 50, MWColors.SILVER.toMaterialColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> SILVER_CHAIN = registerChain("silver_chain");
    public static final RegistryObject<Block> SILVER_LANTERN = registerLantern("silver_lantern");
    public static final RegistryObject<Block> SILVER_SOUL_LANTERN = registerLantern("silver_soul_lantern", 10);
    public static final RegistryObject<Block> SILVER_BARS = registerBars("silver_bars");
    public static final RegistryObject<Block> SILVER_CAGE = registerSimpleTranslucentBlock("silver_cage", () -> copyFrom(SILVER_BLOCK.get()));
    public static final RegistryObject<Block> OXIDIZED_COPPER_STAIRS = registerBlock("oxidized_copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.OXIDIZED, Blocks.OXIDIZED_COPPER.defaultBlockState()));
    public static final RegistryObject<Block> WEATHERED_COPPER_STAIRS = registerBlock("weathered_copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.WEATHERED, Blocks.WEATHERED_COPPER.defaultBlockState()));
    public static final RegistryObject<Block> EXPOSED_COPPER_STAIRS = registerBlock("exposed_copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.EXPOSED, Blocks.EXPOSED_COPPER.defaultBlockState()));
    public static final RegistryObject<Block> COPPER_STAIRS = registerBlock("copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.UNAFFECTED, Blocks.COPPER_BLOCK.defaultBlockState()));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_STAIRS = registerBlock("waxed_oxidized_copper_stairs",
            () -> new MWWaxedWeatheringCopperStairBlock(WeatheringCopper.WeatherState.OXIDIZED, Blocks.WAXED_OXIDIZED_COPPER.defaultBlockState()));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_STAIRS = registerBlock("waxed_weathered_copper_stairs",
            () -> new MWWaxedWeatheringCopperStairBlock(WeatheringCopper.WeatherState.WEATHERED, Blocks.WAXED_WEATHERED_COPPER.defaultBlockState()));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_STAIRS = registerBlock("waxed_exposed_copper_stairs",
            () -> new MWWaxedWeatheringCopperStairBlock(WeatheringCopper.WeatherState.EXPOSED, Blocks.WAXED_EXPOSED_COPPER.defaultBlockState()));
    public static final RegistryObject<Block> WAXED_COPPER_STAIRS = registerBlock("waxed_copper_stairs",
            () -> new MWWaxedWeatheringCopperStairBlock(WeatheringCopper.WeatherState.UNAFFECTED, Blocks.WAXED_COPPER_BLOCK.defaultBlockState()));
    public static final RegistryObject<Block> OXIDIZED_COPPER_SLAB = registerBlock("oxidized_copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.OXIDIZED, copyFrom(Blocks.OXIDIZED_CUT_COPPER)));
    public static final RegistryObject<Block> WEATHERED_COPPER_SLAB = registerBlock("weathered_copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.WEATHERED, copyFrom(Blocks.WEATHERED_CUT_COPPER)));
    public static final RegistryObject<Block> EXPOSED_COPPER_SLAB = registerBlock("exposed_copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.EXPOSED, copyFrom(Blocks.EXPOSED_CUT_COPPER)));
    public static final RegistryObject<Block> COPPER_SLAB = registerBlock("copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.UNAFFECTED, copyFrom(Blocks.CUT_COPPER_SLAB)));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_SLAB = registerBlock("waxed_oxidized_copper_slab",
            () -> new MWWaxedWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.OXIDIZED, copyFrom(Blocks.WAXED_OXIDIZED_CUT_COPPER)));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_SLAB = registerBlock("waxed_weathered_copper_slab",
            () -> new MWWaxedWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.WEATHERED, copyFrom(Blocks.WAXED_WEATHERED_CUT_COPPER)));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_SLAB = registerBlock("waxed_exposed_copper_slab",
            () -> new MWWaxedWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.EXPOSED, copyFrom(Blocks.WAXED_EXPOSED_CUT_COPPER)));
    public static final RegistryObject<Block> WAXED_COPPER_SLAB = registerBlock("waxed_copper_slab",
            () -> new MWWaxedWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.UNAFFECTED, copyFrom(Blocks.WAXED_CUT_COPPER_SLAB)));
    public static final RegistryObject<Block> OXIDIZED_COPPER_PRESSURE_PLATE = registerBlock("oxidized_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WEATHERED_COPPER_PRESSURE_PLATE = registerBlock("weathered_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> EXPOSED_COPPER_PRESSURE_PLATE = registerBlock("exposed_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> COPPER_PRESSURE_PLATE = registerBlock("copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_PRESSURE_PLATE = registerBlock("waxed_oxidized_copper_pressure_plate",
            () -> new WaxedWeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_PRESSURE_PLATE = registerBlock("waxed_weathered_copper_pressure_plate",
            () -> new WaxedWeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_PRESSURE_PLATE = registerBlock("waxed_exposed_copper_pressure_plate",
            () -> new WaxedWeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> WAXED_COPPER_PRESSURE_PLATE = registerBlock("waxed_copper_pressure_plate",
            () -> new WaxedWeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> OXIDIZED_CUT_COPPER_PRESSURE_PLATE = registerBlock("oxidized_cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WEATHERED_CUT_COPPER_PRESSURE_PLATE = registerBlock("weathered_cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> EXPOSED_CUT_COPPER_PRESSURE_PLATE = registerBlock("exposed_cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> CUT_COPPER_PRESSURE_PLATE = registerBlock("cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> WAXED_OXIDIZED_CUT_COPPER_PRESSURE_PLATE = registerBlock("waxed_oxidized_cut_copper_pressure_plate",
            () -> new WaxedWeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WAXED_WEATHERED_CUT_COPPER_PRESSURE_PLATE = registerBlock("waxed_weathered_cut_copper_pressure_plate",
            () -> new WaxedWeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> WAXED_EXPOSED_CUT_COPPER_PRESSURE_PLATE = registerBlock("waxed_exposed_cut_copper_pressure_plate",
            () -> new WaxedWeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> WAXED_CUT_COPPER_PRESSURE_PLATE = registerBlock("waxed_cut_copper_pressure_plate",
            () -> new WaxedWeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> OXIDIZED_COPPER_TRAPDOOR = registerBlock("oxidized_copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WEATHERED_COPPER_TRAPDOOR = registerBlock("weathered_copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> EXPOSED_COPPER_TRAPDOOR = registerBlock("exposed_copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> COPPER_TRAPDOOR = registerBlock("copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_TRAPDOOR = registerBlock("waxed_oxidized_copper_trapdoor",
            () -> new WaxedWeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_TRAPDOOR = registerBlock("waxed_weathered_copper_trapdoor",
            () -> new WaxedWeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_TRAPDOOR = registerBlock("waxed_exposed_copper_trapdoor",
            () -> new WaxedWeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> WAXED_COPPER_TRAPDOOR = registerBlock("waxed_copper_trapdoor",
            () -> new WaxedWeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> OXIDIZED_COPPER_CHAIN = registerBlock("oxidized_copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WEATHERED_COPPER_CHAIN = registerBlock("weathered_copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> EXPOSED_COPPER_CHAIN = registerBlock("exposed_copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> COPPER_CHAIN = registerBlock("copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_CHAIN = registerBlock("waxed_oxidized_copper_chain",
            () -> new WaxedWeatheringCopperChainBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_CHAIN = registerBlock("waxed_weathered_copper_chain",
            () -> new WaxedWeatheringCopperChainBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_CHAIN = registerBlock("waxed_exposed_copper_chain",
            () -> new WaxedWeatheringCopperChainBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> WAXED_COPPER_CHAIN = registerBlock("waxed_copper_chain",
            () -> new WaxedWeatheringCopperChainBlock(WeatheringCopper.WeatherState.UNAFFECTED));

    //#endregion

    /**
     * Create some basic {@link BlockBehaviour.Properties block properties}
     * using the {@link Material material color} and the base {@link SoundType block sound}
     *
     * @param material {@link Material The block material}
     * @param hardness {@link Float The block hardness}
     * @param blastResistance {@link Float The block blast restitance}
     * @param requiresTool {@link Boolean If the block requires the correct tool to drop}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    private static BlockBehaviour.Properties basicBlockProperties(final Material material, final float hardness, final float blastResistance, final boolean requiresTool, final FeatureFlag... featureFlags) {
        return basicBlockProperties(material, material.getColor(), hardness, blastResistance, requiresTool, featureFlags);
    }

    /**
     * Create some basic {@link BlockBehaviour.Properties block properties}
     * using the provided {@link MaterialColor color} and the base {@link SoundType block sound}
     *
     * @param material {@link Material The block material}
     * @param color {@link MaterialColor The block color on maps}
     * @param hardness {@link Float The block hardness}
     * @param blastResistance {@link Float The block blast restitance}
     * @param requiresTool {@link Boolean If the block requires the correct tool to drop}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    private static BlockBehaviour.Properties basicBlockProperties(final Material material, final MaterialColor color, final float hardness, final float blastResistance, final boolean requiresTool, final FeatureFlag... featureFlags) {
        return basicBlockProperties(material, color, hardness, blastResistance, requiresTool, null, featureFlags);
    }

    /**
     * Create some basic {@link BlockBehaviour.Properties block properties}
     *
     * @param material {@link Material The block material}
     * @param color {@link MaterialColor The block color on maps}
     * @param hardness {@link Float The block hardness}
     * @param blastResistance {@link Float The block blast restitance}
     * @param requiresTool {@link Boolean If the block requires the correct tool to drop}
     * @param sound {@link SoundType The block sound}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    private static BlockBehaviour.Properties basicBlockProperties(final Material material, final MaterialColor color, final float hardness, final float blastResistance, final boolean requiresTool, final SoundType sound, final FeatureFlag... featureFlags) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of(material, color).strength(hardness, blastResistance);
        if(requiresTool) {
            properties = properties.requiresCorrectToolForDrops();
        }
        if(sound != null) {
            properties = properties.sound(sound);
        }
        if(featureFlags != null) {
            properties = properties.requiredFeatures(featureFlags);
        }
        return properties;
    }

    /**
     * Create the {@link BlockBehaviour.Properties block properties} for an {@link Block ore block}
     *
     * @param isDeepslateOre {@link Boolean Whether the ore is a deepslate ore}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The ore block properties}
     */
    private static BlockBehaviour.Properties oreBlockProperties(final boolean isDeepslateOre, final FeatureFlag... featureFlags) {
        return basicBlockProperties(Material.STONE,
                isDeepslateOre ? MaterialColor.DEEPSLATE : Material.STONE.getColor(),
                isDeepslateOre ? 4.5F : 3.0F, 3.0F,
                true,
                isDeepslateOre ? SoundType.DEEPSLATE : null,
                featureFlags);
    }

    /**
     * Register an {@link Block ore block}
     *
     * @param name {@link String The block Name}
     * @param isDeepslateOre {@link Boolean Whether the ore is a deepslate ore}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerOverworldOreBlock(final String name, final boolean isDeepslateOre, final FeatureFlag... featureFlags) {
        return registerOreBlock(name, oreBlockProperties(isDeepslateOre, featureFlags), 3, 7);
    }

    /**
     * Register a Nether {@link Block ore block}
     *
     * @param name {@link String The block Name}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerNetherOreBlock(final String name, final FeatureFlag... featureFlags) {
        return registerOreBlock(name, oreBlockProperties(false, featureFlags).color(MaterialColor.NETHER).sound(SoundType.NETHER_ORE), 2, 5);
    }

    /**
     * Register an {@link Block ore block}
     *
     * @param name {@link String The block Name}
     * @param blockProperties {@link BlockBehaviour.Properties The block properties}
     * @param minXp {@link Integer The minimum amount of XP the ore will drop}
     * @param maxXp {@link Integer The maximum amount of XP the ore will drop}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerOreBlock(final String name, final BlockBehaviour.Properties blockProperties, final int minXp, final int maxXp) {
        return registerBlock(name, () -> new DropExperienceBlock(blockProperties, UniformInt.of(minXp, maxXp)));
    }

    /**
     * Register a {@link Block raw ore block}
     *
     * @param name {@link String The block name}
     * @param color {@link MaterialColor The block color on maps}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerRawOreBlock(final String name, final MaterialColor color, final FeatureFlag... featureFlags) {
        return registerOreStorageBlock(name, Material.STONE, color,null, featureFlags);
    }

    /**
     * Register a {@link Block raw ore block}
     *
     * @param name {@link String The block name}
     * @param color {@link MaterialColor The block color on maps}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerMetalOreStorageBlock(final String name, MaterialColor color, final FeatureFlag... featureFlags) {
        return registerOreStorageBlock(name, Material.METAL, color, SoundType.METAL, featureFlags);
    }

    /**
     * Register a {@link Block raw ore block}
     *
     * @param name {@link String The block name}
     * @param material {@link Material The block material}
     * @param color {@link MaterialColor The block color on maps}
     * @param sound {@link SoundType The block sound}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerOreStorageBlock(final String name, final Material material, final MaterialColor color, final SoundType sound, final FeatureFlag... featureFlags) {
        return registerSimpleBlock(name, oreStorageProperties(material, color,sound, featureFlags));
    }

    /**
     * Get the {@link BlockBehaviour.Properties block properties} for an ore storage block
     *
     * @param color {@link MaterialColor The block color on maps}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    private static BlockBehaviour.Properties oreStorageProperties(final MaterialColor color, final FeatureFlag... featureFlags) {
        return oreStorageProperties(Material.STONE, color, SoundType.STONE, featureFlags);
    }

    /**
     * Get the {@link BlockBehaviour.Properties block properties} for a metal ore storage block
     *
     * @param color {@link MaterialColor The block color on maps}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    private static BlockBehaviour.Properties metalOreStorageProperties(final MaterialColor color, final FeatureFlag... featureFlags) {
        return oreStorageProperties(Material.METAL, color, SoundType.METAL, featureFlags);
    }

    /**
     * Get the {@link BlockBehaviour.Properties block properties} for an ore storage block
     *
     * @param material {@link Material The block material}
     * @param color {@link MaterialColor The block color on maps}
     * @param sound {@link SoundType The block sound}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    private static BlockBehaviour.Properties oreStorageProperties(final Material material, final MaterialColor color, final SoundType sound, final FeatureFlag... featureFlags) {
        return basicBlockProperties(material, color,5.0F, 6.0F, true, sound, featureFlags);
    }

    /**
     * Register a {@link Block fuel block}
     *
     * @param name {@link String The block name}
     * @param color {@link MaterialColor The block color on maps}
     * @param burnTime {@link Integer The fuel burn time}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerFuelBlock(final String name, final MaterialColor color, final int burnTime, final FeatureFlag... featureFlags) {
        RegistryObject<Block> block = registerBlockWithoutBlockItem(name, () -> new Block(basicBlockProperties(Material.STONE, color, 5.0F, 6.0F, true, featureFlags)));
        MWItems.registerFuelBlockItem(name, block, burnTime, featureFlags);
        return block;
    }

    /**
     * Register a {@link FlowerBlock flower block}
     *
     * @param name {@link String The block name}
     * @param effectSupplier {@link Supplier<MobEffect> The flower effect when used in suspicious stews}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerFlower(final String name, final Supplier<MobEffect> effectSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new FlowerBlock(effectSupplier, 5, copyFrom(Blocks.POPPY, featureFlags)));
    }

    /**
     * Register a {@link TallFlowerBlock tall flower block}
     *
     * @param name {@link String The block name}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerTallFlower(final String name, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new TallFlowerBlock(copyFrom(Blocks.ROSE_BUSH, featureFlags)));
    }

    /**
     * Register a {@link DoorBlock door block}
     *
     * @param name {@link String The block name}
     * @param requiresPower {@link Boolean If the door needs redstone to be activated}
     * @param blockSetType {@link BlockSetType The door block set type}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerDoor(final String name, final boolean requiresPower, final BlockSetType blockSetType, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new DoorBlock(copyFrom(requiresPower ? Blocks.IRON_DOOR : Blocks.OAK_DOOR, featureFlags), blockSetType));
    }

    /**
     * Register a {@link TrapDoorBlock trap door block}
     *
     * @param name {@link String The block name}
     * @param requiresPower {@link Boolean If the door needs redstone to be activated}
     * @param blockSetType {@link BlockSetType The door block set type}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerTrapdoor(final String name, final boolean requiresPower, final BlockSetType blockSetType, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new TrapDoorBlock(copyFrom(requiresPower ? Blocks.IRON_TRAPDOOR : Blocks.OAK_TRAPDOOR, featureFlags), blockSetType));
    }

    /**
     * Register a {@link WeightedPressurePlateBlock weighted pressure plate block}
     *
     * @param name {@link String The block name}
     * @param maxWeight {@link Integer The max weight the pressure plate can detect}
     * @param materialColor {@link MaterialColor The block color on maps}
     * @param blockSetType {@link BlockSetType The door block set type}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerWeightedPressurePlate(final String name, final int maxWeight, final MaterialColor materialColor, final BlockSetType blockSetType, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new WeightedPressurePlateBlock(maxWeight, copyFrom(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, featureFlags).color(materialColor), blockSetType));
    }

    /**
     * Register a {@link StairBlock stair block}
     *
     * @param name {@link String The block name}
     * @param block {@link Block The block this stair is based on}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerStair(final String name, final Block block, final FeatureFlag... featureFlags) {
        return registerStair(name, block::defaultBlockState, featureFlags);
    }

    /**
     * Register a {@link StairBlock stair block}
     *
     * @param name {@link String The block name}
     * @param blockStateSupplier {@link Supplier<BlockState> The block state supplier this stair is based on}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerStair(final String name, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new StairBlock(blockStateSupplier, copyFrom(blockStateSupplier.get().getBlock(), featureFlags)));
    }

    /**
     * Register a {@link SlabBlock stair block}
     *
     * @param name {@link String The block name}
     * @param block {@link Block The block state this slab is based on}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerSlab(final String name, final Block block, final FeatureFlag... featureFlags) {
        return registerSlab(name, block::defaultBlockState, featureFlags);
    }

    /**
     * Register a {@link SlabBlock stair block}
     *
     * @param name {@link String The block name}
     * @param blockStateSupplier {@link Supplier<BlockState> The block state supplier this slab is based on}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerSlab(final String name, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new SlabBlock(copyFrom(blockStateSupplier.get().getBlock(), featureFlags)));
    }

    /**
     * Register a {@link ChainBlock chain block}
     *
     * @param name {@link String The block name}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerChain(final String name, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new ChainBlock(copyFrom(Blocks.CHAIN, featureFlags).sound(SoundType.CHAIN)));
    }

    /**
     * Register a {@link ChainBlock chain block}
     *
     * @param name {@link String The block name}
     * @param sound {@link SoundType The block sound}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerChain(final String name, final SoundType sound, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new ChainBlock(copyFrom(Blocks.CHAIN, featureFlags).sound(sound)));
    }

    /**
     * Register a {@link LanternBlock lantern block}
     *
     * @param name {@link String The block name}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerLantern(final String name, final FeatureFlag... featureFlags) {
        return registerLantern(name, 15, featureFlags);
    }

    /**
     * Register a {@link LanternBlock lantern block}
     *
     * @param name {@link String The block name}
     * @param lightLevel {@link Integer The lantern light level}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerLantern(final String name, final int lightLevel, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new LanternBlock(copyFrom(Blocks.LANTERN, featureFlags).lightLevel((state) -> lightLevel)));
    }

    /**
     * Register a {@link IronBarsBlock bar block}
     *
     * @param name {@link String The block name}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerBars(final String name, final FeatureFlag... featureFlags) {
        return registerBars(name, SoundType.METAL, featureFlags);
    }

    /**
     * Register a {@link IronBarsBlock bar block}
     *
     * @param name {@link String The block name}
     * @param sound {@link SoundType The block sound}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerBars(final String name, final SoundType sound, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new IronBarsBlock(copyFrom(Blocks.IRON_BARS, featureFlags).sound(sound)));
    }

    /**
     * Register a {@link Block block} using the provided {@link BlockBehaviour.Properties properties}
     * and adding the translucent properties as well
     *
     * @param name {@link String The block name}
     * @param properties {@link BlockBehaviour.Properties The block properties}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerSimpleTranslucentBlock(final String name, final BlockBehaviour.Properties properties) {
        return registerSimpleTranslucentBlock(name, () -> properties);
    }

    /**
     * Register a {@link Block block} using the provided {@link BlockBehaviour.Properties properties}
     * and adding the translucent properties as well
     *
     * @param name {@link String The block name}
     * @param properties {@link Supplier<BlockBehaviour.Properties> The block properties}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerSimpleTranslucentBlock(final String name, final Supplier<BlockBehaviour.Properties> properties) {
        return registerBlock(name, () -> new Block(properties.get().noOcclusion()
                .isValidSpawn((state, level, blockPos, entityType) -> false)
                .isRedstoneConductor((state, level, blockPos) -> false)
                .isSuffocating((state, level, blockPos) -> false)
                .isViewBlocking((state, level, blockPos) -> false)));
    }

    /**
     * Register a {@link Block block} using the provided {@link BlockBehaviour.Properties properties}
     *
     * @param name {@link String The block name}
     * @param properties {@link BlockBehaviour.Properties The block properties}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerSimpleBlock(final String name, final BlockBehaviour.Properties properties) {
        return registerSimpleBlock(name, () -> properties);
    }

    /**
     * Register a {@link Block block} using the provided {@link BlockBehaviour.Properties properties}
     *
     * @param name {@link String The block name}
     * @param properties {@link Supplier<BlockBehaviour.Properties> The block properties}
     * @return {@link RegistryObject<Block> The registered block}
     */
    private static RegistryObject<Block> registerSimpleBlock(final String name, final Supplier<BlockBehaviour.Properties> properties) {
        return registerBlock(name, () -> new Block(properties.get()));
    }

    /**
     * Register a {@link Block block}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<Block> The block supplier}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this block to be registered}
     * @param <T> The block type
     * @return {@link RegistryObject<T> The registered block}
     */
    private static <T extends Block> RegistryObject<T> registerBlock(final String name, final Supplier<T> blockSupplier, final FeatureFlag... featureFlags) {
        RegistryObject<T> block = registerBlockWithoutBlockItem(name, blockSupplier);
        registerBlockItem(name, block, featureFlags);
        return block;
    }

    /**
     * Register a {@link Block block} without also registering a {@link BlockItem block item}
     *
     * @param name {@link String The block name}
     * @param blockSupplier {@link Supplier<Block> The block supplier}
     * @param <T> The block type
     * @return {@link RegistryObject<Block> The registered block}
     */
    static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(final String name, final Supplier<T> blockSupplier) {
        return BLOCKS.register(name, blockSupplier);
    }

    /**
     * Register a {@link BlockItem block item}
     *
     * @param name  {@link String The block name}
     * @param block {@link RegistryObject<T> The block}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this block to be registered}
     * @param <T> The block type
     */
    private static <T extends Block> void registerBlockItem(final String name, final RegistryObject<T> block, final FeatureFlag... featureFlags) {
        MWItems.registerItem(name, () -> new BlockItem(block.get(), MWItems.basicProperties(featureFlags)));
    }

    /**
     * Copy the {@link BlockBehaviour.Properties block properties} of an existing block
     * and makes them required by the provided {@link FeatureFlag feature flags}
     *
     * @param block {@link Block The block to copy the properties from}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    public static BlockBehaviour.Properties copyFrom(final Block block, final FeatureFlag... featureFlags) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.copy(block);
        if(featureFlags != null && featureFlags.length > 0) {
            properties = properties.requiredFeatures(featureFlags);
        }
        return properties;
    }

    /**
     * Register the {@link MineWorld MineWorld} {@link Block blocks}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        MWFlowerPots.register(eventBus);
        BLOCKS.register(eventBus);
    }
}
