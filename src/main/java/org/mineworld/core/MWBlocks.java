package org.mineworld.core;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.block.*;
import org.mineworld.block.weathering.*;
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.helper.RegisterHelper;

/**
 * {@link MineWorld MineWorld} {@link Block blocks}
 */
public final class MWBlocks {

    public static final RegistryObject<Block> SILVER_ORE = RegisterHelper.registerOverworldOreBlock("silver_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = RegisterHelper.registerOverworldOreBlock("deepslate_silver_ore", true);
    public static final RegistryObject<Block> ALUMINUM_ORE = RegisterHelper.registerOverworldOreBlock("aluminum_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_ALUMINUM_ORE = RegisterHelper.registerOverworldOreBlock("deepslate_aluminum_ore", true);
    public static final RegistryObject<Block> RUBY_ORE = RegisterHelper.registerOverworldOreBlock("ruby_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_RUBY_ORE = RegisterHelper.registerOverworldOreBlock("deepslate_ruby_ore", true);
    public static final RegistryObject<Block> SAPPHIRE_ORE = RegisterHelper.registerOverworldOreBlock("sapphire_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = RegisterHelper.registerOverworldOreBlock("deepslate_sapphire_ore", true);
    public static final RegistryObject<Block> PYRITE_ORE = RegisterHelper.registerNetherOreBlock("pyrite_ore");
    public static final RegistryObject<Block> RAW_SILVER_BLOCK = RegisterHelper.registerRawOreBlock("raw_silver_block", MWColors.RAW_SILVER.toMaterialColor());
    public static final RegistryObject<Block> RAW_ALUMINUM_BLOCK = RegisterHelper.registerRawOreBlock("raw_aluminum_block", MWColors.RAW_ALUMINUM.toMaterialColor());
    public static final RegistryObject<Block> RAW_BRONZE_BLOCK = RegisterHelper.registerRawOreBlock("raw_bronze_block", MWColors.RAW_BRONZE.toMaterialColor());
    public static final RegistryObject<Block> SILVER_BLOCK = RegisterHelper.registerMetalOreStorageBlock("silver_block", MWColors.SILVER.toMaterialColor());
    public static final RegistryObject<Block> ALUMINUM_BLOCK = RegisterHelper.registerMetalOreStorageBlock("aluminum_block", MWColors.ALUMINUM.toMaterialColor());
    public static final RegistryObject<Block> BRONZE_BLOCK = RegisterHelper.registerBlock("bronze_block", () -> PropertyHelper.basicBlockProperties(Material.METAL, MWColors.BRONZE.toMaterialColor(),3.0F, 6.0F, true, SoundType.COPPER));
    public static final RegistryObject<Block> RUBY_BLOCK = RegisterHelper.registerMetalOreStorageBlock("ruby_block", MWColors.RUBY.toMaterialColor());
    public static final RegistryObject<Block> SAPPHIRE_BLOCK = RegisterHelper.registerMetalOreStorageBlock("sapphire_block", MWColors.SAPPHIRE.toMaterialColor());
    public static final RegistryObject<Block> PYRITE_BLOCK = RegisterHelper.registerFuelBlock("pyrite_block", MWColors.PYRITE.toMaterialColor(), 1200);
    public static final RegistryObject<Block> MARBLE = RegisterHelper.registerBlock("marble", () -> PropertyHelper.copyFromBlock(Blocks.TUFF).color(MWColors.MARBLE.toMaterialColor()));
    public static final RegistryObject<Block> WHITE_MARBLE = RegisterHelper.registerBlock("white_marble", () -> PropertyHelper.copyFromBlock(Blocks.TUFF).color(MaterialColor.TERRACOTTA_WHITE));
    public static final RegistryObject<Block> ORANGE_MARBLE = RegisterHelper.registerBlock("orange_marble", () -> PropertyHelper.copyFromBlock(Blocks.TUFF).color(MaterialColor.TERRACOTTA_ORANGE));
    public static final RegistryObject<Block> MAGENTA_MARBLE = RegisterHelper.registerBlock("magenta_marble", () -> PropertyHelper.copyFromBlock(Blocks.TUFF).color(MaterialColor.TERRACOTTA_MAGENTA));
    public static final RegistryObject<Block> LIGHT_BLUE_MARBLE = RegisterHelper.registerBlock("light_blue_marble", () -> PropertyHelper.copyFromBlock(Blocks.TUFF).color(MaterialColor.TERRACOTTA_LIGHT_BLUE));
    public static final RegistryObject<Block> YELLOW_MARBLE = RegisterHelper.registerBlock("yellow_marble", () -> PropertyHelper.copyFromBlock(Blocks.TUFF).color(MaterialColor.TERRACOTTA_YELLOW));
    public static final RegistryObject<Block> LIME_MARBLE = RegisterHelper.registerBlock("lime_marble", () -> PropertyHelper.copyFromBlock(Blocks.TUFF).color(MaterialColor.TERRACOTTA_LIGHT_GREEN));
    public static final RegistryObject<Block> PINK_MARBLE = RegisterHelper.registerBlock("pink_marble", () -> PropertyHelper.copyFromBlock(Blocks.TUFF).color(MaterialColor.TERRACOTTA_PINK));
    public static final RegistryObject<Block> GRAY_MARBLE = RegisterHelper.registerBlock("gray_marble", () -> PropertyHelper.copyFromBlock(Blocks.TUFF).color(MaterialColor.TERRACOTTA_GRAY));
    public static final RegistryObject<Block> LIGHT_GRAY_MARBLE = RegisterHelper.registerBlock("light_gray_marble", () -> PropertyHelper.copyFromBlock(Blocks.TUFF).color(MaterialColor.TERRACOTTA_LIGHT_GRAY));
    public static final RegistryObject<Block> CYAN_MARBLE = RegisterHelper.registerBlock("cyan_marble", () -> PropertyHelper.copyFromBlock(Blocks.TUFF).color(MaterialColor.TERRACOTTA_CYAN));
    public static final RegistryObject<Block> PURPLE_MARBLE = RegisterHelper.registerBlock("purple_marble", () -> PropertyHelper.copyFromBlock(Blocks.TUFF).color(MaterialColor.TERRACOTTA_PURPLE));
    public static final RegistryObject<Block> BLUE_MARBLE = RegisterHelper.registerBlock("blue_marble", () -> PropertyHelper.copyFromBlock(Blocks.TUFF).color(MaterialColor.TERRACOTTA_BLUE));
    public static final RegistryObject<Block> BROWN_MARBLE = RegisterHelper.registerBlock("brown_marble", () -> PropertyHelper.copyFromBlock(Blocks.TUFF).color(MaterialColor.TERRACOTTA_BROWN));
    public static final RegistryObject<Block> GREEN_MARBLE = RegisterHelper.registerBlock("green_marble", () -> PropertyHelper.copyFromBlock(Blocks.TUFF).color(MaterialColor.TERRACOTTA_GREEN));
    public static final RegistryObject<Block> RED_MARBLE = RegisterHelper.registerBlock("red_marble", () -> PropertyHelper.copyFromBlock(Blocks.TUFF).color(MaterialColor.TERRACOTTA_RED));
    public static final RegistryObject<Block> BLACK_MARBLE = RegisterHelper.registerBlock("black_marble", () -> PropertyHelper.copyFromBlock(Blocks.TUFF).color(MaterialColor.TERRACOTTA_BLACK));
    public static final RegistryObject<Block> BLUE_ROSE = RegisterHelper.registerFlower("blue_rose", () -> MobEffects.SATURATION);
    public static final RegistryObject<Block> POTTED_BLUE_ROSE = RegisterHelper.registerFlowerPot("potted_blue_rose", BLUE_ROSE);
    public static final RegistryObject<Block> BLUE_ROSE_BUSH = RegisterHelper.registerTallFlower("blue_rose_bush");
    public static final RegistryObject<Block> POTTED_BLUE_ROSE_BUSH = RegisterHelper.registerFlowerPot("potted_blue_rose_bush", BLUE_ROSE_BUSH);
    public static final RegistryObject<Block> WHITE_ROSE = RegisterHelper.registerFlower("white_rose", () -> MobEffects.HEAL);
    public static final RegistryObject<Block> POTTED_WHITE_ROSE = RegisterHelper.registerFlowerPot("potted_white_rose", WHITE_ROSE);
    public static final RegistryObject<Block> WHITE_ROSE_BUSH = RegisterHelper.registerTallFlower("white_rose_bush");
    public static final RegistryObject<Block> POTTED_WHITE_ROSE_BUSH = RegisterHelper.registerFlowerPot("potted_white_rose_bush", WHITE_ROSE_BUSH);
    public static final RegistryObject<Block> POTTED_MOSS_BLOCK = RegisterHelper.registerFlowerPot("potted_moss_block", () -> Blocks.MOSS_BLOCK);
    public static final RegistryObject<Block> POTTED_OAK_LEAVES = RegisterHelper.registerFlowerPot("potted_oak_leaves", () -> Blocks.OAK_LEAVES);
    public static final RegistryObject<Block> POTTED_SPRUCE_LEAVES = RegisterHelper.registerFlowerPot("potted_spruce_leaves", () -> Blocks.SPRUCE_LEAVES);
    public static final RegistryObject<Block> POTTED_BIRCH_LEAVES = RegisterHelper.registerFlowerPot("potted_birch_leaves", () -> Blocks.BIRCH_LEAVES);
    public static final RegistryObject<Block> POTTED_JUNGLE_LEAVES = RegisterHelper.registerFlowerPot("potted_jungle_leaves", () -> Blocks.JUNGLE_LEAVES);
    public static final RegistryObject<Block> POTTED_ACACIA_LEAVES = RegisterHelper.registerFlowerPot("potted_acacia_leaves", () -> Blocks.ACACIA_LEAVES);
    public static final RegistryObject<Block> POTTED_CHERRY_LEAVES = RegisterHelper.registerFlowerPot("potted_cherry_leaves", () -> Blocks.CHERRY_LEAVES, FeatureFlags.UPDATE_1_20);
    public static final RegistryObject<Block> POTTED_DARK_OAK_LEAVES = RegisterHelper.registerFlowerPot("potted_dark_oak_leaves", () -> Blocks.DARK_OAK_LEAVES);
    public static final RegistryObject<Block> POTTED_MANGROVE_LEAVES = RegisterHelper.registerFlowerPot("potted_mangrove_leaves", () -> Blocks.MANGROVE_LEAVES);
    public static final RegistryObject<Block> POTTED_AZALEA_LEAVES = RegisterHelper.registerFlowerPot("potted_azalea_leaves", () -> Blocks.AZALEA_LEAVES);
    public static final RegistryObject<Block> POTTED_FLOWERING_AZALEA_LEAVES = RegisterHelper.registerFlowerPot("potted_flowering_azalea_leaves", () -> Blocks.FLOWERING_AZALEA_LEAVES);
    public static final RegistryObject<Block> POTTED_MANGROVE_ROOTS = RegisterHelper.registerFlowerPot("potted_mangrove_roots", () -> Blocks.MANGROVE_ROOTS);
    public static final RegistryObject<Block> POTTED_MUDDY_MANGROVE_ROOTS = RegisterHelper.registerFlowerPot("potted_muddy_mangrove_roots", () -> Blocks.MUDDY_MANGROVE_ROOTS);
    public static final RegistryObject<Block> POTTED_BROWN_MUSHROOM_BLOCK = RegisterHelper.registerFlowerPot("potted_brown_mushroom_block", () -> Blocks.BROWN_MUSHROOM_BLOCK);
    public static final RegistryObject<Block> POTTED_RED_MUSHROOM_BLOCK = RegisterHelper.registerFlowerPot("potted_red_mushroom_block", () -> Blocks.RED_MUSHROOM_BLOCK);
    public static final RegistryObject<Block> POTTED_NETHER_WART_BLOCK = RegisterHelper.registerFlowerPot("potted_nether_wart_block", () -> Blocks.NETHER_WART_BLOCK);
    public static final RegistryObject<Block> POTTED_WARPED_WART_BLOCK = RegisterHelper.registerFlowerPot("potted_warped_wart_block", () -> Blocks.WARPED_WART_BLOCK);
    public static final RegistryObject<Block> POTTED_SHROOMLIGHT = RegisterHelper.registerLitFlowerPot("potted_shroomlight", () -> Blocks.SHROOMLIGHT, 5);
    public static final RegistryObject<Block> POTTED_GRASS = RegisterHelper.registerFlowerPot("potted_grass", () -> Blocks.GRASS);
    public static final RegistryObject<Block> POTTED_SUGAR_CANE = RegisterHelper.registerFlowerPot("potted_sugar_cane", () -> Blocks.SUGAR_CANE);
    public static final RegistryObject<Block> POTTED_SEAGRASS = RegisterHelper.registerFlowerPot("potted_seagrass", () -> Blocks.SEAGRASS);
    public static final RegistryObject<Block> POTTED_TALL_GRASS = RegisterHelper.registerFlowerPot("potted_tall_grass", () -> Blocks.TALL_GRASS);
    public static final RegistryObject<Block> POTTED_LARGE_FERN = RegisterHelper.registerFlowerPot("potted_large_fern", () -> Blocks.LARGE_FERN);
    public static final RegistryObject<Block> POTTED_SUNFLOWER = RegisterHelper.registerFlowerPot("potted_sunflower", () -> Blocks.SUNFLOWER);
    public static final RegistryObject<Block> POTTED_LILAC = RegisterHelper.registerFlowerPot("potted_lilac", () -> Blocks.LILAC);
    public static final RegistryObject<Block> POTTED_ROSE_BUSH = RegisterHelper.registerFlowerPot("potted_rose_bush", () -> Blocks.ROSE_BUSH);
    public static final RegistryObject<Block> POTTED_PEONY = RegisterHelper.registerFlowerPot("potted_peony", () -> Blocks.PEONY);
    public static final RegistryObject<Block> POTTED_KELP = RegisterHelper.registerFlowerPot("potted_kelp", () -> Blocks.KELP);
    public static final RegistryObject<Block> POTTED_BIG_DRIPLEAF = RegisterHelper.registerFlowerPot("potted_big_dripleaf", () -> Blocks.BIG_DRIPLEAF);
    public static final RegistryObject<Block> POTTED_SMALL_DRIPLEAF = RegisterHelper.registerFlowerPot("potted_small_dripleaf", () -> Blocks.SMALL_DRIPLEAF);
    public static final RegistryObject<Block> POTTED_CHORUS_PLANT = RegisterHelper.registerFlowerPot("potted_chorus_plant", () -> Blocks.CHORUS_PLANT);
    public static final RegistryObject<Block> POTTED_CHORUS_FLOWER = RegisterHelper.registerFlowerPot("potted_chorus_flower", () -> Blocks.CHORUS_FLOWER);
    public static final RegistryObject<Block> POTTED_SWEET_BERRY_BUSH = RegisterHelper.registerFlowerPot("potted_sweet_berry_bush", () -> Blocks.SWEET_BERRY_BUSH);
    public static final RegistryObject<Block> POTTED_CAVE_VINES = RegisterHelper.registerLitFlowerPot("potted_cave_vines", () -> Blocks.CAVE_VINES, 4);
    public static final RegistryObject<Block> POTTED_OCHRE_FROGLIGHT = RegisterHelper.registerLitFlowerPot("potted_ochre_froglight", () -> Blocks.OCHRE_FROGLIGHT, 5);
    public static final RegistryObject<Block> POTTED_VERDANT_FROGLIGHT = RegisterHelper.registerLitFlowerPot("potted_verdant_froglight", () -> Blocks.VERDANT_FROGLIGHT, 5);
    public static final RegistryObject<Block> POTTED_PEARLESCENT_FROGLIGHT = RegisterHelper.registerLitFlowerPot("potted_pearlescent_froglight", () -> Blocks.PEARLESCENT_FROGLIGHT, 5);
    public static final RegistryObject<Block> POTTED_DEAD_TUBE_CORAL_BLOCK = RegisterHelper.registerFlowerPot("potted_dead_tube_coral_block", () -> Blocks.DEAD_TUBE_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_TUBE_CORAL_BLOCK = RegisterHelper.registerCoralFlowerPot("potted_tube_coral_block", POTTED_DEAD_TUBE_CORAL_BLOCK, () -> Blocks.TUBE_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_DEAD_BRAIN_CORAL_BLOCK = RegisterHelper.registerFlowerPot("potted_dead_brain_coral_block", () -> Blocks.DEAD_BRAIN_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_BRAIN_CORAL_BLOCK = RegisterHelper.registerCoralFlowerPot("potted_brain_coral_block", POTTED_DEAD_BRAIN_CORAL_BLOCK, () -> Blocks.BRAIN_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_DEAD_BUBBLE_CORAL_BLOCK = RegisterHelper.registerFlowerPot("potted_dead_bubble_coral_block", () -> Blocks.DEAD_BUBBLE_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_BUBBLE_CORAL_BLOCK = RegisterHelper.registerCoralFlowerPot("potted_bubble_coral_block", POTTED_DEAD_BUBBLE_CORAL_BLOCK, () -> Blocks.BUBBLE_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_DEAD_FIRE_CORAL_BLOCK = RegisterHelper.registerFlowerPot("potted_dead_fire_coral_block", () -> Blocks.DEAD_FIRE_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_FIRE_CORAL_BLOCK = RegisterHelper.registerCoralFlowerPot("potted_fire_coral_block", POTTED_DEAD_FIRE_CORAL_BLOCK, () -> Blocks.FIRE_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_DEAD_HORN_CORAL_BLOCK = RegisterHelper.registerFlowerPot("potted_dead_horn_coral_block", () -> Blocks.DEAD_HORN_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_HORN_CORAL_BLOCK = RegisterHelper.registerCoralFlowerPot("potted_horn_coral_block", POTTED_DEAD_HORN_CORAL_BLOCK, () -> Blocks.HORN_CORAL_BLOCK);
    public static final RegistryObject<Block> POTTED_DEAD_TUBE_CORAL = RegisterHelper.registerFlowerPot("potted_dead_tube_coral", () -> Blocks.DEAD_TUBE_CORAL);
    public static final RegistryObject<Block> POTTED_TUBE_CORAL = RegisterHelper.registerCoralFlowerPot("potted_tube_coral", POTTED_DEAD_TUBE_CORAL, () -> Blocks.TUBE_CORAL);
    public static final RegistryObject<Block> POTTED_DEAD_BRAIN_CORAL = RegisterHelper.registerFlowerPot("potted_dead_brain_coral", () -> Blocks.DEAD_BRAIN_CORAL);
    public static final RegistryObject<Block> POTTED_BRAIN_CORAL = RegisterHelper.registerCoralFlowerPot("potted_brain_coral", POTTED_DEAD_BRAIN_CORAL, () -> Blocks.BRAIN_CORAL);
    public static final RegistryObject<Block> POTTED_DEAD_BUBBLE_CORAL = RegisterHelper.registerFlowerPot("potted_dead_bubble_coral", () -> Blocks.DEAD_BUBBLE_CORAL);
    public static final RegistryObject<Block> POTTED_BUBBLE_CORAL = RegisterHelper.registerCoralFlowerPot("potted_bubble_coral", POTTED_DEAD_BUBBLE_CORAL, () -> Blocks.BUBBLE_CORAL);
    public static final RegistryObject<Block> POTTED_DEAD_FIRE_CORAL = RegisterHelper.registerFlowerPot("potted_dead_fire_coral", () -> Blocks.DEAD_FIRE_CORAL);
    public static final RegistryObject<Block> POTTED_FIRE_CORAL = RegisterHelper.registerCoralFlowerPot("potted_fire_coral", POTTED_DEAD_FIRE_CORAL, () -> Blocks.FIRE_CORAL);
    public static final RegistryObject<Block> POTTED_DEAD_HORN_CORAL = RegisterHelper.registerFlowerPot("potted_dead_horn_coral", () -> Blocks.DEAD_HORN_CORAL);
    public static final RegistryObject<Block> POTTED_HORN_CORAL = RegisterHelper.registerCoralFlowerPot("potted_horn_coral", POTTED_DEAD_HORN_CORAL, () -> Blocks.HORN_CORAL);
    public static final RegistryObject<Block> POTTED_DEAD_TUBE_CORAL_FAN = RegisterHelper.registerFlowerPot("potted_dead_tube_coral_fan", () -> Blocks.DEAD_TUBE_CORAL_FAN);
    public static final RegistryObject<Block> POTTED_TUBE_CORAL_FAN = RegisterHelper.registerCoralFlowerPot("potted_tube_coral_fan", POTTED_DEAD_TUBE_CORAL_FAN, () -> Blocks.TUBE_CORAL_FAN);
    public static final RegistryObject<Block> POTTED_DEAD_BRAIN_CORAL_FAN = RegisterHelper.registerFlowerPot("potted_dead_brain_coral_fan", () -> Blocks.DEAD_BRAIN_CORAL_FAN);
    public static final RegistryObject<Block> POTTED_BRAIN_CORAL_FAN = RegisterHelper.registerCoralFlowerPot("potted_brain_coral_fan", POTTED_DEAD_BRAIN_CORAL_FAN, () -> Blocks.BRAIN_CORAL_FAN);
    public static final RegistryObject<Block> POTTED_DEAD_BUBBLE_CORAL_FAN = RegisterHelper.registerFlowerPot("potted_dead_bubble_coral_fan", () -> Blocks.DEAD_BUBBLE_CORAL_FAN);
    public static final RegistryObject<Block> POTTED_BUBBLE_CORAL_FAN = RegisterHelper.registerCoralFlowerPot("potted_bubble_coral_fan", POTTED_DEAD_BUBBLE_CORAL_FAN, () -> Blocks.BUBBLE_CORAL_FAN);
    public static final RegistryObject<Block> POTTED_DEAD_FIRE_CORAL_FAN = RegisterHelper.registerFlowerPot("potted_dead_fire_coral_fan", () -> Blocks.DEAD_FIRE_CORAL_FAN);
    public static final RegistryObject<Block> POTTED_FIRE_CORAL_FAN = RegisterHelper.registerCoralFlowerPot("potted_fire_coral_fan", POTTED_DEAD_FIRE_CORAL_FAN, () -> Blocks.FIRE_CORAL_FAN);
    public static final RegistryObject<Block> POTTED_DEAD_HORN_CORAL_FAN = RegisterHelper.registerFlowerPot("potted_dead_horn_coral_fan", () -> Blocks.DEAD_HORN_CORAL_FAN);
    public static final RegistryObject<Block> POTTED_HORN_CORAL_FAN = RegisterHelper.registerCoralFlowerPot("potted_horn_coral_fan", POTTED_DEAD_HORN_CORAL_FAN, () -> Blocks.HORN_CORAL_FAN);
    public static final RegistryObject<Block> CORN = RegisterHelper.registerBlockWithoutBlockItem("corn", CornBlock::new);
    public static final RegistryObject<Block> DISGUISED_GRASS_TNT = RegisterHelper.registerBlock("disguised_grass_tnt", () -> new MWTntBlock(MWPrimedTnt.Type.DISGUISED_GRASS));
    public static final RegistryObject<Block> DISGUISED_DIRT_TNT = RegisterHelper.registerBlock("disguised_dirt_tnt", () -> new MWTntBlock(MWPrimedTnt.Type.DISGUISED_DIRT));
    public static final RegistryObject<Block> DISGUISED_SAND_TNT = RegisterHelper.registerBlock("disguised_sand_tnt", () -> new MWFallableTntBlock(MWPrimedTnt.Type.DISGUISED_SAND, 14406560));
    public static final RegistryObject<Block> DISGUISED_RED_SAND_TNT = RegisterHelper.registerBlock("disguised_red_sand_tnt", () -> new MWFallableTntBlock(MWPrimedTnt.Type.DISGUISED_RED_SAND, 11098145));
    public static final RegistryObject<Block> DISGUISED_STONE_TNT = RegisterHelper.registerBlock("disguised_stone_tnt", () -> new MWTntBlock(MWPrimedTnt.Type.DISGUISED_STONE));
    public static final RegistryObject<Block> MEGA_TNT = RegisterHelper.registerBlock("mega_tnt", () -> new MWTntBlock(MWPrimedTnt.Type.MEGA));
    public static final RegistryObject<Block> SUPER_TNT = RegisterHelper.registerBlock("super_tnt", () -> new MWTntBlock(MWPrimedTnt.Type.SUPER));
    public static final RegistryObject<Block> HYPER_TNT = RegisterHelper.registerBlock("hyper_tnt", () -> new MWTntBlock(MWPrimedTnt.Type.HYPER));
    public static final RegistryObject<Block> DAYLIGHT_LAMP = RegisterHelper.registerBlock("daylight_lamp", () -> new DaylightLampBlock());
    public static final RegistryObject<Block> CHARCOAL_BLOCK = RegisterHelper.registerFuelBlock("charcoal_block", MWColors.CHARCOAL.toMaterialColor(), 800);
    public static final RegistryObject<Block> IRON_STAIRS = RegisterHelper.registerStair("iron_stairs", Blocks.IRON_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> IRON_SLAB = RegisterHelper.registerSlab("iron_slab", () -> Blocks.IRON_BLOCK);
    public static final RegistryObject<Block> IRON_CAGE = RegisterHelper.registerCage("iron_cage", () -> Blocks.IRON_BLOCK);
    public static final RegistryObject<Block> CUT_IRON = RegisterHelper.registerBlock("cut_iron", () -> PropertyHelper.copyFromBlock(Blocks.IRON_BLOCK));
    public static final RegistryObject<Block> CUT_IRON_STAIRS = RegisterHelper.registerStair("cut_iron_stairs", () -> CUT_IRON.get().defaultBlockState());
    public static final RegistryObject<Block> CUT_IRON_SLAB = RegisterHelper.registerSlab("cut_iron_slab", CUT_IRON);
    public static final RegistryObject<Block> CUT_IRON_PRESSURE_PLATE = RegisterHelper.registerWeightedPressurePlate("cut_iron_pressure_plate", 15, MaterialColor.METAL, BlockSetType.IRON);
    public static final RegistryObject<Block> GOLDEN_STAIRS = RegisterHelper.registerStair("golden_stairs", Blocks.GOLD_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> GOLDEN_SLAB = RegisterHelper.registerSlab("golden_slab", () -> Blocks.GOLD_BLOCK);
    public static final RegistryObject<Block> GOLDEN_DOOR = RegisterHelper.registerDoor("golden_door", true, BlockSetType.GOLD);
    public static final RegistryObject<Block> GOLDEN_TRAPDOOR = RegisterHelper.registerTrapdoor("golden_trapdoor", true, BlockSetType.GOLD);
    public static final RegistryObject<Block> GOLDEN_CHAIN = RegisterHelper.registerChain("golden_chain");
    public static final RegistryObject<Block> GOLDEN_LANTERN = RegisterHelper.registerLantern("golden_lantern", false);
    public static final RegistryObject<Block> GOLDEN_SOUL_LANTERN = RegisterHelper.registerLantern("golden_soul_lantern", true);
    public static final RegistryObject<Block> GOLD_BARS = RegisterHelper.registerBars("gold_bars");
    public static final RegistryObject<Block> GOLDEN_CAGE = RegisterHelper.registerCage("golden_cage", () -> Blocks.GOLD_BLOCK);
    public static final RegistryObject<Block> CUT_GOLD = RegisterHelper.registerBlock("cut_gold", () -> PropertyHelper.copyFromBlock(Blocks.GOLD_BLOCK));
    public static final RegistryObject<Block> CUT_GOLDEN_STAIRS = RegisterHelper.registerStair("cut_golden_stairs", () -> CUT_GOLD.get().defaultBlockState());
    public static final RegistryObject<Block> CUT_GOLDEN_SLAB = RegisterHelper.registerSlab("cut_golden_slab", CUT_GOLD);
    public static final RegistryObject<Block> CUT_GOLDEN_PRESSURE_PLATE = RegisterHelper.registerWeightedPressurePlate("cut_golden_pressure_plate", 15, MaterialColor.GOLD, BlockSetType.GOLD);
    public static final RegistryObject<Block> OXIDIZED_COPPER_STAIRS = RegisterHelper.registerBlock("oxidized_copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.OXIDIZED, Blocks.OXIDIZED_COPPER.defaultBlockState()));
    public static final RegistryObject<Block> WEATHERED_COPPER_STAIRS = RegisterHelper.registerBlock("weathered_copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.WEATHERED, Blocks.WEATHERED_COPPER.defaultBlockState()));
    public static final RegistryObject<Block> EXPOSED_COPPER_STAIRS = RegisterHelper.registerBlock("exposed_copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.EXPOSED, Blocks.EXPOSED_COPPER.defaultBlockState()));
    public static final RegistryObject<Block> COPPER_STAIRS = RegisterHelper.registerBlock("copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.UNAFFECTED, Blocks.COPPER_BLOCK.defaultBlockState()));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_STAIRS = RegisterHelper.registerBlock("waxed_oxidized_copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.OXIDIZED, Blocks.WAXED_OXIDIZED_COPPER.defaultBlockState()));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_STAIRS = RegisterHelper.registerBlock("waxed_weathered_copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.WEATHERED, Blocks.WAXED_WEATHERED_COPPER.defaultBlockState()));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_STAIRS = RegisterHelper.registerBlock("waxed_exposed_copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.EXPOSED, Blocks.WAXED_EXPOSED_COPPER.defaultBlockState()));
    public static final RegistryObject<Block> WAXED_COPPER_STAIRS = RegisterHelper.registerBlock("waxed_copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.UNAFFECTED, Blocks.WAXED_COPPER_BLOCK.defaultBlockState()));
    public static final RegistryObject<Block> OXIDIZED_COPPER_SLAB = RegisterHelper.registerBlock("oxidized_copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.OXIDIZED, PropertyHelper.copyFromBlock(Blocks.OXIDIZED_CUT_COPPER)));
    public static final RegistryObject<Block> WEATHERED_COPPER_SLAB = RegisterHelper.registerBlock("weathered_copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.WEATHERED, PropertyHelper.copyFromBlock(Blocks.WEATHERED_CUT_COPPER)));
    public static final RegistryObject<Block> EXPOSED_COPPER_SLAB = RegisterHelper.registerBlock("exposed_copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.EXPOSED, PropertyHelper.copyFromBlock(Blocks.EXPOSED_CUT_COPPER)));
    public static final RegistryObject<Block> COPPER_SLAB = RegisterHelper.registerBlock("copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.UNAFFECTED, PropertyHelper.copyFromBlock(Blocks.CUT_COPPER_SLAB)));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_SLAB = RegisterHelper.registerBlock("waxed_oxidized_copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.OXIDIZED, PropertyHelper.copyFromBlock(Blocks.WAXED_OXIDIZED_CUT_COPPER)));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_SLAB = RegisterHelper.registerBlock("waxed_weathered_copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.WEATHERED, PropertyHelper.copyFromBlock(Blocks.WAXED_WEATHERED_CUT_COPPER)));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_SLAB = RegisterHelper.registerBlock("waxed_exposed_copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.EXPOSED, PropertyHelper.copyFromBlock(Blocks.WAXED_EXPOSED_CUT_COPPER)));
    public static final RegistryObject<Block> WAXED_COPPER_SLAB = RegisterHelper.registerBlock("waxed_copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.UNAFFECTED, PropertyHelper.copyFromBlock(Blocks.WAXED_CUT_COPPER_SLAB)));
    public static final RegistryObject<Block> COPPER_DOOR = RegisterHelper.registerDoor("copper_door", true, MWBlockSetTypes.COPPER);
    public static final RegistryObject<Block> OXIDIZED_COPPER_TRAPDOOR = RegisterHelper.registerBlock("oxidized_copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WEATHERED_COPPER_TRAPDOOR = RegisterHelper.registerBlock("weathered_copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> EXPOSED_COPPER_TRAPDOOR = RegisterHelper.registerBlock("exposed_copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> COPPER_TRAPDOOR = RegisterHelper.registerBlock("copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_TRAPDOOR = RegisterHelper.registerBlock("waxed_oxidized_copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_TRAPDOOR = RegisterHelper.registerBlock("waxed_weathered_copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_TRAPDOOR = RegisterHelper.registerBlock("waxed_exposed_copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> WAXED_COPPER_TRAPDOOR = RegisterHelper.registerBlock("waxed_copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> OXIDIZED_COPPER_PRESSURE_PLATE = RegisterHelper.registerBlock("oxidized_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WEATHERED_COPPER_PRESSURE_PLATE = RegisterHelper.registerBlock("weathered_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> EXPOSED_COPPER_PRESSURE_PLATE = RegisterHelper.registerBlock("exposed_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> COPPER_PRESSURE_PLATE = RegisterHelper.registerBlock("copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_PRESSURE_PLATE = RegisterHelper.registerBlock("waxed_oxidized_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_PRESSURE_PLATE = RegisterHelper.registerBlock("waxed_weathered_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_PRESSURE_PLATE = RegisterHelper.registerBlock("waxed_exposed_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> WAXED_COPPER_PRESSURE_PLATE = RegisterHelper.registerBlock("waxed_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> OXIDIZED_COPPER_CHAIN = RegisterHelper.registerBlock("oxidized_copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WEATHERED_COPPER_CHAIN = RegisterHelper.registerBlock("weathered_copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> EXPOSED_COPPER_CHAIN = RegisterHelper.registerBlock("exposed_copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> COPPER_CHAIN = RegisterHelper.registerBlock("copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_CHAIN = RegisterHelper.registerBlock("waxed_oxidized_copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_CHAIN = RegisterHelper.registerBlock("waxed_weathered_copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_CHAIN = RegisterHelper.registerBlock("waxed_exposed_copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> WAXED_COPPER_CHAIN = RegisterHelper.registerBlock("waxed_copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> OXIDIZED_COPPER_LANTERN = RegisterHelper.registerBlock("oxidized_copper_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.OXIDIZED, false));
    public static final RegistryObject<Block> WEATHERED_COPPER_LANTERN = RegisterHelper.registerBlock("weathered_copper_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.WEATHERED, false));
    public static final RegistryObject<Block> EXPOSED_COPPER_LANTERN = RegisterHelper.registerBlock("exposed_copper_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.EXPOSED, false));
    public static final RegistryObject<Block> COPPER_LANTERN = RegisterHelper.registerBlock("copper_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.UNAFFECTED, false));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_LANTERN = RegisterHelper.registerBlock("waxed_oxidized_copper_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.OXIDIZED, false));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_LANTERN = RegisterHelper.registerBlock("waxed_weathered_copper_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.WEATHERED, false));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_LANTERN = RegisterHelper.registerBlock("waxed_exposed_copper_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.EXPOSED, false));
    public static final RegistryObject<Block> WAXED_COPPER_LANTERN = RegisterHelper.registerBlock("waxed_copper_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.UNAFFECTED, false));
    public static final RegistryObject<Block> OXIDIZED_COPPER_SOUL_LANTERN = RegisterHelper.registerBlock("oxidized_copper_soul_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.OXIDIZED, true));
    public static final RegistryObject<Block> WEATHERED_COPPER_SOUL_LANTERN = RegisterHelper.registerBlock("weathered_copper_soul_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.WEATHERED, true));
    public static final RegistryObject<Block> EXPOSED_COPPER_SOUL_LANTERN = RegisterHelper.registerBlock("exposed_copper_soul_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.EXPOSED, true));
    public static final RegistryObject<Block> COPPER_SOUL_LANTERN = RegisterHelper.registerBlock("copper_soul_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.UNAFFECTED, true));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_SOUL_LANTERN = RegisterHelper.registerBlock("waxed_oxidized_copper_soul_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.OXIDIZED, true));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_SOUL_LANTERN = RegisterHelper.registerBlock("waxed_weathered_copper_soul_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.WEATHERED, true));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_SOUL_LANTERN = RegisterHelper.registerBlock("waxed_exposed_copper_soul_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.EXPOSED, true));
    public static final RegistryObject<Block> WAXED_COPPER_SOUL_LANTERN = RegisterHelper.registerBlock("waxed_copper_soul_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.UNAFFECTED, true));
    public static final RegistryObject<Block> OXIDIZED_COPPER_BARS = RegisterHelper.registerBlock("oxidized_copper_bars",
            () -> new WeatheringCopperBarsBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WEATHERED_COPPER_BARS = RegisterHelper.registerBlock("weathered_copper_bars",
            () -> new WeatheringCopperBarsBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> EXPOSED_COPPER_BARS = RegisterHelper.registerBlock("exposed_copper_bars",
            () -> new WeatheringCopperBarsBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> COPPER_BARS = RegisterHelper.registerBlock("copper_bars",
            () -> new WeatheringCopperBarsBlock(WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_BARS = RegisterHelper.registerBlock("waxed_oxidized_copper_bars",
            () -> new WeatheringCopperBarsBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_BARS = RegisterHelper.registerBlock("waxed_weathered_copper_bars",
            () -> new WeatheringCopperBarsBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_BARS = RegisterHelper.registerBlock("waxed_exposed_copper_bars",
            () -> new WeatheringCopperBarsBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> WAXED_COPPER_BARS = RegisterHelper.registerBlock("waxed_copper_bars",
            () -> new WeatheringCopperBarsBlock(WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> OXIDIZED_COPPER_CAGE = RegisterHelper.registerBlock("oxidized_copper_cage",
            () -> new WeatheringCopperCageBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WEATHERED_COPPER_CAGE = RegisterHelper.registerBlock("weathered_copper_cage",
            () -> new WeatheringCopperCageBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> EXPOSED_COPPER_CAGE = RegisterHelper.registerBlock("exposed_copper_cage",
            () -> new WeatheringCopperCageBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> COPPER_CAGE = RegisterHelper.registerBlock("copper_cage",
            () -> new WeatheringCopperCageBlock(WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_CAGE = RegisterHelper.registerBlock("waxed_oxidized_copper_cage",
            () -> new WeatheringCopperCageBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_CAGE = RegisterHelper.registerBlock("waxed_weathered_copper_cage",
            () -> new WeatheringCopperCageBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_CAGE = RegisterHelper.registerBlock("waxed_exposed_copper_cage",
            () -> new WeatheringCopperCageBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> WAXED_COPPER_CAGE = RegisterHelper.registerBlock("waxed_copper_cage",
            () -> new WeatheringCopperCageBlock(WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> OXIDIZED_CUT_COPPER_PRESSURE_PLATE = RegisterHelper.registerBlock("oxidized_cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WEATHERED_CUT_COPPER_PRESSURE_PLATE = RegisterHelper.registerBlock("weathered_cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> EXPOSED_CUT_COPPER_PRESSURE_PLATE = RegisterHelper.registerBlock("exposed_cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> CUT_COPPER_PRESSURE_PLATE = RegisterHelper.registerBlock("cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> WAXED_OXIDIZED_CUT_COPPER_PRESSURE_PLATE = RegisterHelper.registerBlock("waxed_oxidized_cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.OXIDIZED));
    public static final RegistryObject<Block> WAXED_WEATHERED_CUT_COPPER_PRESSURE_PLATE = RegisterHelper.registerBlock("waxed_weathered_cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.WEATHERED));
    public static final RegistryObject<Block> WAXED_EXPOSED_CUT_COPPER_PRESSURE_PLATE = RegisterHelper.registerBlock("waxed_exposed_cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.EXPOSED));
    public static final RegistryObject<Block> WAXED_CUT_COPPER_PRESSURE_PLATE = RegisterHelper.registerBlock("waxed_cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.UNAFFECTED));
    public static final RegistryObject<Block> NETHERITE_STAIRS = RegisterHelper.registerStair("netherite_stairs", Blocks.NETHERITE_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> NETHERITE_SLAB = RegisterHelper.registerSlab("netherite_slab", () -> Blocks.NETHERITE_BLOCK);
    public static final RegistryObject<Block> NETHERITE_DOOR = RegisterHelper.registerDoor("netherite_door", true, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> NETHERITE_TRAPDOOR = RegisterHelper.registerTrapdoor("netherite_trapdoor", true, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> NETHERITE_PRESSURE_PLATE = RegisterHelper.registerWeightedPressurePlate("netherite_pressure_plate", 100, MaterialColor.COLOR_BLACK, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> NETHERITE_CHAIN = RegisterHelper.registerChain("netherite_chain");
    public static final RegistryObject<Block> NETHERITE_LANTERN = RegisterHelper.registerLantern("netherite_lantern", false);
    public static final RegistryObject<Block> NETHERITE_SOUL_LANTERN = RegisterHelper.registerLantern("netherite_soul_lantern", true);
    public static final RegistryObject<Block> NETHERITE_BARS = RegisterHelper.registerBars("netherite_bars");
    public static final RegistryObject<Block> NETHERITE_CAGE = RegisterHelper.registerCage("netherite_cage", () -> Blocks.NETHERITE_BLOCK);
    public static final RegistryObject<Block> CUT_NETHERITE = RegisterHelper.registerBlock("cut_netherite", () -> PropertyHelper.copyFromBlock(Blocks.NETHERITE_BLOCK));
    public static final RegistryObject<Block> CUT_NETHERITE_STAIRS = RegisterHelper.registerStair("cut_netherite_stairs", () -> CUT_NETHERITE.get().defaultBlockState());
    public static final RegistryObject<Block> CUT_NETHERITE_SLAB = RegisterHelper.registerSlab("cut_netherite_slab", CUT_NETHERITE);
    public static final RegistryObject<Block> CUT_NETHERITE_PRESSURE_PLATE = RegisterHelper.registerWeightedPressurePlate("cut_netherite_pressure_plate", 100, MaterialColor.COLOR_BLACK, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> ALUMINUM_STAIRS = RegisterHelper.registerStair("aluminum_stairs", () -> ALUMINUM_BLOCK.get().defaultBlockState());
    public static final RegistryObject<Block> ALUMINUM_SLAB = RegisterHelper.registerSlab("aluminum_slab", ALUMINUM_BLOCK);
    public static final RegistryObject<Block> ALUMINUM_DOOR = RegisterHelper.registerDoor("aluminum_door", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> ALUMINUM_TRAPDOOR = RegisterHelper.registerTrapdoor("aluminum_trapdoor", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> ALUMINUM_PRESSURE_PLATE = RegisterHelper.registerWeightedPressurePlate("aluminum_pressure_plate", 15, MWColors.ALUMINUM.toMaterialColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> ALUMINUM_CHAIN = RegisterHelper.registerChain("aluminum_chain");
    public static final RegistryObject<Block> ALUMINUM_LANTERN = RegisterHelper.registerLantern("aluminum_lantern", false);
    public static final RegistryObject<Block> ALUMINUM_SOUL_LANTERN = RegisterHelper.registerLantern("aluminum_soul_lantern", true);
    public static final RegistryObject<Block> ALUMINUM_BARS = RegisterHelper.registerBars("aluminum_bars");
    public static final RegistryObject<Block> ALUMINUM_CAGE = RegisterHelper.registerCage("aluminum_cage", ALUMINUM_BLOCK);
    public static final RegistryObject<Block> CUT_ALUMINUM = RegisterHelper.registerBlock("cut_aluminum", () -> PropertyHelper.copyFromBlock(ALUMINUM_BLOCK.get()));
    public static final RegistryObject<Block> CUT_ALUMINUM_STAIRS = RegisterHelper.registerStair("cut_aluminum_stairs", () -> CUT_ALUMINUM.get().defaultBlockState());
    public static final RegistryObject<Block> CUT_ALUMINUM_SLAB = RegisterHelper.registerSlab("cut_aluminum_slab", CUT_ALUMINUM);
    public static final RegistryObject<Block> CUT_ALUMINUM_PRESSURE_PLATE = RegisterHelper.registerWeightedPressurePlate("cut_aluminum_pressure_plate", 15, MWColors.ALUMINUM.toMaterialColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> BRONZE_STAIRS = RegisterHelper.registerStair("bronze_stairs", () -> BRONZE_BLOCK.get().defaultBlockState());
    public static final RegistryObject<Block> BRONZE_SLAB = RegisterHelper.registerSlab("bronze_slab", BRONZE_BLOCK);
    public static final RegistryObject<Block> BRONZE_DOOR = RegisterHelper.registerDoor("bronze_door", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> BRONZE_TRAPDOOR = RegisterHelper.registerTrapdoor("bronze_trapdoor", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> BRONZE_PRESSURE_PLATE = RegisterHelper.registerWeightedPressurePlate("bronze_pressure_plate", 15, MWColors.BRONZE.toMaterialColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> BRONZE_CHAIN = RegisterHelper.registerChain("bronze_chain");
    public static final RegistryObject<Block> BRONZE_LANTERN = RegisterHelper.registerLantern("bronze_lantern", false);
    public static final RegistryObject<Block> BRONZE_SOUL_LANTERN = RegisterHelper.registerLantern("bronze_soul_lantern", true);
    public static final RegistryObject<Block> BRONZE_BARS = RegisterHelper.registerBars("bronze_bars");
    public static final RegistryObject<Block> BRONZE_CAGE = RegisterHelper.registerCage("bronze_cage", BRONZE_BLOCK);
    public static final RegistryObject<Block> CUT_BRONZE = RegisterHelper.registerBlock("cut_bronze", () -> PropertyHelper.copyFromBlock(BRONZE_BLOCK.get()));
    public static final RegistryObject<Block> CUT_BRONZE_STAIRS = RegisterHelper.registerStair("cut_bronze_stairs", () -> CUT_BRONZE.get().defaultBlockState());
    public static final RegistryObject<Block> CUT_BRONZE_SLAB = RegisterHelper.registerSlab("cut_bronze_slab", CUT_BRONZE);
    public static final RegistryObject<Block> CUT_BRONZE_PRESSURE_PLATE = RegisterHelper.registerWeightedPressurePlate("cut_bronze_pressure_plate", 15, MWColors.BRONZE.toMaterialColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> SILVER_STAIRS = RegisterHelper.registerStair("silver_stairs", () -> SILVER_BLOCK.get().defaultBlockState());
    public static final RegistryObject<Block> SILVER_SLAB = RegisterHelper.registerSlab("silver_slab", SILVER_BLOCK);
    public static final RegistryObject<Block> SILVER_DOOR = RegisterHelper.registerDoor("silver_door", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> SILVER_TRAPDOOR = RegisterHelper.registerTrapdoor("silver_trapdoor", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> SILVER_PRESSURE_PLATE = RegisterHelper.registerWeightedPressurePlate("silver_pressure_plate", 50, MWColors.SILVER.toMaterialColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> SILVER_CHAIN = RegisterHelper.registerChain("silver_chain");
    public static final RegistryObject<Block> SILVER_LANTERN = RegisterHelper.registerLantern("silver_lantern", false);
    public static final RegistryObject<Block> SILVER_SOUL_LANTERN = RegisterHelper.registerLantern("silver_soul_lantern", true);
    public static final RegistryObject<Block> SILVER_BARS = RegisterHelper.registerBars("silver_bars");
    public static final RegistryObject<Block> SILVER_CAGE = RegisterHelper.registerCage("silver_cage", SILVER_BLOCK);
    public static final RegistryObject<Block> CUT_SILVER = RegisterHelper.registerBlock("cut_silver", () -> PropertyHelper.copyFromBlock(SILVER_BLOCK.get()));
    public static final RegistryObject<Block> CUT_SILVER_STAIRS = RegisterHelper.registerStair("cut_silver_stairs", () -> CUT_SILVER.get().defaultBlockState());
    public static final RegistryObject<Block> CUT_SILVER_SLAB = RegisterHelper.registerSlab("cut_silver_slab", CUT_SILVER);
    public static final RegistryObject<Block> CUT_SILVER_PRESSURE_PLATE = RegisterHelper.registerWeightedPressurePlate("cut_silver_pressure_plate", 50, MWColors.SILVER.toMaterialColor(), MWBlockSetTypes.METAL);

    /**
     * Register the {@link MineWorld MineWorld} {@link Block blocks}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(final IEventBus eventBus) {
        RegisterHelper.registerBlocks(eventBus);
    }

}