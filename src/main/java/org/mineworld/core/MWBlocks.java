package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.block.*;
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.item.MWFuelBlockItem;

import java.util.Locale;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link Block Blocks}
 */
public final class MWBlocks {

    //#region Registry

    /**
     * The {@link DeferredRegister<Block> Block Registry}
     */
    private static final DeferredRegister<Block> BLOCKS = RegistryHelper.registry(ForgeRegistries.BLOCKS);

    //#endregion

    //#region Blocks

    //#region Ores, Raw Ore Blocks and Ore Blocks

    public static final RegistryObject<Block> SILVER_ORE = registerOverworldOre("silver_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = registerOverworldOre("deepslate_silver_ore", true);
    public static final RegistryObject<Block> ALUMINUM_ORE = registerOverworldOre("aluminum_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_ALUMINUM_ORE = registerOverworldOre("deepslate_aluminum_ore", true);
    public static final RegistryObject<Block> RUBY_ORE = registerOverworldOre("ruby_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_RUBY_ORE = registerOverworldOre("deepslate_ruby_ore", true);
    public static final RegistryObject<Block> SAPPHIRE_ORE = registerOverworldOre("sapphire_ore", false);
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = registerOverworldOre("deepslate_sapphire_ore", true);
    public static final RegistryObject<Block> PYRITE_ORE = registerNetherOre("pyrite_ore");
    public static final RegistryObject<Block> RAW_SILVER_BLOCK = registerOreStorageBlock("raw_silver_block", MWColors.RAW_SILVER.toMapColor(), false);
    public static final RegistryObject<Block> RAW_ALUMINUM_BLOCK = registerOreStorageBlock("raw_aluminum_block", MWColors.RAW_ALUMINUM.toMapColor(), false);
    public static final RegistryObject<Block> RAW_BRONZE_BLOCK = registerOreStorageBlock("raw_bronze_block", MWColors.RAW_BRONZE.toMapColor(), false);
    public static final RegistryObject<Block> SILVER_BLOCK = registerOreStorageBlock("silver_block", MWColors.SILVER.toMapColor(), true);
    public static final RegistryObject<Block> ALUMINUM_BLOCK = registerOreStorageBlock("aluminum_block", MWColors.ALUMINUM.toMapColor(), true);
    public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block", () -> PropertyHelper.block(MWColors.BRONZE.toMapColor(),3.0F, 6.0F, true, SoundType.COPPER));
    public static final RegistryObject<Block> RUBY_BLOCK = registerOreStorageBlock("ruby_block", MWColors.RUBY.toMapColor(), true);
    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerOreStorageBlock("sapphire_block", MWColors.SAPPHIRE.toMapColor(), true);
    public static final RegistryObject<Block> PYRITE_BLOCK = registerFuel("pyrite_block", MWColors.PYRITE.toMapColor(), 1200);
    public static final RegistryObject<Block> CHARCOAL_BLOCK = registerFuel("charcoal_block", MWColors.CHARCOAL.toMapColor(), 800);

    //#endregion

    //#region Stone Block Sets

    public static final RegistryObject<Block> MARBLE = registerBlock("marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MWColors.MARBLE.toMapColor())));
    public static final RegistryObject<Block> MARBLE_STAIRS = registerStair("marble", Suppliers.memoize(() -> MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> MARBLE_SLAB = registerSlab("marble", Suppliers.memoize(() -> MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> MARBLE_WALL = registerWall("marble", Suppliers.memoize(() -> MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> MARBLE_PRESSURE_PLATE = registerPressurePlate("marble", false, MWColors.MARBLE.toMapColor(), Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MARBLE_BUTTON = registerButton("marble", false, Suppliers.memoize(() -> BlockSetType.STONE));

    public static final RegistryObject<Block> STONE_WALL = registerWall("stone", Blocks.STONE::defaultBlockState);
    public static final RegistryObject<Block> COBBLESTONE_PRESSURE_PLATE = registerPressurePlate("cobblestone", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> COBBLESTONE_BUTTON = registerButton("cobblestone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_STONE = registerBlock("mossy_stone", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.STONE)));
    public static final RegistryObject<Block> MOSSY_STONE_STAIRS = registerStair("mossy_stone", Suppliers.memoize(() -> MOSSY_STONE.get().defaultBlockState()));
    public static final RegistryObject<Block> MOSSY_STONE_SLAB = registerSlab("mossy_stone", Suppliers.memoize(() -> MOSSY_STONE.get().defaultBlockState()));
    public static final RegistryObject<Block> MOSSY_STONE_WALL = registerWall("mossy_stone", Suppliers.memoize(() -> MOSSY_STONE.get().defaultBlockState()));
    public static final RegistryObject<Block> MOSSY_STONE_PRESSURE_PLATE = registerPressurePlate("mossy_stone", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_STONE_BUTTON = registerButton("mossy_stone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_COBBLESTONE_PRESSURE_PLATE = registerPressurePlate("mossy_cobblestone", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_COBBLESTONE_BUTTON = registerButton("mossy_cobblestone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_STONE_STAIRS = registerStair("smooth_stone", Blocks.SMOOTH_STONE::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_STONE_WALL = registerWall("smooth_stone", Blocks.SMOOTH_STONE::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_STONE_PRESSURE_PLATE = registerPressurePlate("smooth_stone", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_STONE_BUTTON = registerButton("smooth_stone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> STONE_BRICKS_PRESSURE_PLATE = registerPressurePlate("stone_bricks", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> STONE_BRICKS_BUTTON = registerButton("stone_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_STONE_BRICKS_PRESSURE_PLATE = registerPressurePlate("mossy_stone_bricks", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_STONE_BRICKS_BUTTON = registerButton("mossy_stone_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_CHISELED_STONE_BRICKS = registerBlock("mossy_chiseled_stone_bricks", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.CHISELED_STONE_BRICKS)));
    public static final RegistryObject<Block> MOSSY_CRACKED_STONE_BRICKS = registerBlock("mossy_cracked_stone_bricks", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.CRACKED_STONE_BRICKS)));
    public static final RegistryObject<Block> GRANITE_PRESSURE_PLATE = registerPressurePlate("granite", false, MapColor.DIRT, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GRANITE_BUTTON = registerButton("granite", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_GRANITE_WALL = registerWall("polished_granite", Blocks.POLISHED_GRANITE::defaultBlockState);
    public static final RegistryObject<Block> POLISHED_GRANITE_PRESSURE_PLATE = registerPressurePlate("polished_granite", false, MapColor.DIRT, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_GRANITE_BUTTON = registerButton("polished_granite", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DIORITE_PRESSURE_PLATE = registerPressurePlate("diorite", false, MapColor.QUARTZ, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DIORITE_BUTTON = registerButton("diorite", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_DIORITE_WALL = registerWall("polished_diorite", Blocks.POLISHED_DIORITE::defaultBlockState);
    public static final RegistryObject<Block> POLISHED_DIORITE_PRESSURE_PLATE = registerPressurePlate("polished_diorite", false, MapColor.QUARTZ, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_DIORITE_BUTTON = registerButton("polished_diorite", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> ANDESITE_PRESSURE_PLATE = registerPressurePlate("andesite", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> ANDESITE_BUTTON = registerButton("andesite", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_ANDESITE_WALL = registerWall("polished_andesite", Blocks.POLISHED_ANDESITE::defaultBlockState);
    public static final RegistryObject<Block> POLISHED_ANDESITE_PRESSURE_PLATE = registerPressurePlate("polished_andesite", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_ANDESITE_BUTTON = registerButton("polished_andesite", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_STAIRS = registerStair("deepslate", Blocks.DEEPSLATE::defaultBlockState);
    public static final RegistryObject<Block> DEEPSLATE_SLAB = registerSlab("deepslate", Blocks.DEEPSLATE::defaultBlockState);
    public static final RegistryObject<Block> DEEPSLATE_WALL = registerWall("deepslate", Blocks.DEEPSLATE::defaultBlockState);
    public static final RegistryObject<Block> DEEPSLATE_PRESSURE_PLATE = registerPressurePlate("deepslate", false, MapColor.DEEPSLATE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_BUTTON = registerButton("deepslate", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> COBBLED_DEEPSLATE_PRESSURE_PLATE = registerPressurePlate("cobbled_deepslate", false, MapColor.DEEPSLATE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> COBBLED_DEEPSLATE_BUTTON = registerButton("cobbled_deepslate", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_DEEPSLATE_PRESSURE_PLATE = registerPressurePlate("polished_deepslate", false, MapColor.DEEPSLATE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_DEEPSLATE_BUTTON = registerButton("polished_deepslate", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_BRICKS_PRESSURE_PLATE = registerPressurePlate("deepslate_bricks", false, MapColor.DEEPSLATE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_BRICKS_BUTTON = registerButton("deepslate_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_TILES_PRESSURE_PLATE = registerPressurePlate("deepslate_tiles", false, MapColor.DEEPSLATE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_TILES_BUTTON = registerButton("deepslate_tiles", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> REINFORCED_DEEPSLATE_STAIRS = registerStair("reinforced_deepslate", Blocks.REINFORCED_DEEPSLATE::defaultBlockState);
    public static final RegistryObject<Block> REINFORCED_DEEPSLATE_SLAB = registerSlab("reinforced_deepslate", Blocks.REINFORCED_DEEPSLATE::defaultBlockState);
    public static final RegistryObject<Block> REINFORCED_DEEPSLATE_WALL = registerWall("reinforced_deepslate", Blocks.REINFORCED_DEEPSLATE::defaultBlockState);
    public static final RegistryObject<Block> REINFORCED_DEEPSLATE_PRESSURE_PLATE = registerPressurePlate("reinforced_deepslate", false, MapColor.DEEPSLATE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> REINFORCED_DEEPSLATE_BUTTON = registerButton("reinforced_deepslate", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BRICKS_PRESSURE_PLATE = registerPressurePlate("bricks", false, MapColor.COLOR_RED, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BRICKS_BUTTON = registerButton("bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MUD_BRICKS_PRESSURE_PLATE = registerPressurePlate("mud_bricks", false, MapColor.TERRACOTTA_LIGHT_GRAY, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MUD_BRICKS_BUTTON = registerButton("mud_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SANDSTONE_PRESSURE_PLATE = registerPressurePlate("sandstone", false, MapColor.SAND, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SANDSTONE_BUTTON = registerButton("sandstone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_SANDSTONE_WALL = registerWall("smooth_sandstone", Blocks.SANDSTONE::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_SANDSTONE_PRESSURE_PLATE = registerPressurePlate("smooth_sandstone", false, MapColor.SAND, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_SANDSTONE_BUTTON = registerButton("smooth_sandstone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CUT_SANDSTONE_STAIRS = registerStair("cut_sandstone", Blocks.CUT_SANDSTONE::defaultBlockState);
    public static final RegistryObject<Block> CUT_SANDSTONE_WALL = registerWall("cut_sandstone", Blocks.CUT_SANDSTONE::defaultBlockState);
    public static final RegistryObject<Block> RED_SANDSTONE_PRESSURE_PLATE = registerPressurePlate("red_sandstone", false, MapColor.COLOR_ORANGE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> RED_SANDSTONE_BUTTON = registerButton("red_sandstone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_RED_SANDSTONE_WALL = registerWall("smooth_red_sandstone", Blocks.RED_SANDSTONE::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_RED_SANDSTONE_PRESSURE_PLATE = registerPressurePlate("smooth_red_sandstone", false, MapColor.COLOR_ORANGE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_RED_SANDSTONE_BUTTON = registerButton("smooth_red_sandstone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CUT_RED_SANDSTONE_STAIRS = registerStair("cut_red_sandstone", Blocks.CUT_RED_SANDSTONE::defaultBlockState);
    public static final RegistryObject<Block> CUT_RED_SANDSTONE_WALL = registerWall("cut_red_sandstone", Blocks.CUT_RED_SANDSTONE::defaultBlockState);
    public static final RegistryObject<Block> PRISMARINE_PRESSURE_PLATE = registerPressurePlate("prismarine", false, MapColor.COLOR_CYAN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PRISMARINE_BUTTON = registerButton("prismarine", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PRISMARINE_BRICKS_WALL = registerWall("prismarine_bricks", Blocks.PRISMARINE_BRICKS::defaultBlockState);
    public static final RegistryObject<Block> PRISMARINE_BRICKS_PRESSURE_PLATE = registerPressurePlate("prismarine_bricks", false, MapColor.DIAMOND, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PRISMARINE_BRICKS_BUTTON = registerButton("prismarine_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DARK_PRISMARINE_WALL = registerWall("dark_prismarine", Blocks.DARK_PRISMARINE::defaultBlockState);
    public static final RegistryObject<Block> DARK_PRISMARINE_PRESSURE_PLATE = registerPressurePlate("dark_prismarine", false, MapColor.DIAMOND, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DARK_PRISMARINE_BUTTON = registerButton("dark_prismarine", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> NETHERRACK_STAIRS = registerStair("netherrack", Blocks.NETHERRACK::defaultBlockState);
    public static final RegistryObject<Block> NETHERRACK_SLAB = registerSlab("netherrack", Blocks.NETHERRACK::defaultBlockState);
    public static final RegistryObject<Block> NETHERRACK_WALL = registerWall("netherrack", Blocks.NETHERRACK::defaultBlockState);
    public static final RegistryObject<Block> NETHERRACK_PRESSURE_PLATE = registerPressurePlate("netherrack", false, MapColor.NETHER, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> NETHERRACK_BUTTON = registerButton("netherrack", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> NETHER_BRICKS_PRESSURE_PLATE = registerPressurePlate("nether_bricks", false, MapColor.NETHER, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> NETHER_BRICKS_BUTTON = registerButton("nether_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> RED_NETHER_BRICKS_PRESSURE_PLATE = registerPressurePlate("red_nether_bricks", false, MapColor.NETHER, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> RED_NETHER_BRICKS_BUTTON = registerButton("red_nether_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CRACKED_RED_NETHER_BRICKS = registerBlock("cracked_red_nether_bricks", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.RED_NETHER_BRICKS)));
    public static final RegistryObject<Block> CHISELED_RED_NETHER_BRICKS = registerBlock("chiseled_red_nether_bricks", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.RED_NETHER_BRICKS)));
    public static final RegistryObject<Block> BASALT_STAIRS = registerStair("basalt", Blocks.BASALT::defaultBlockState);
    public static final RegistryObject<Block> BASALT_SLAB = registerSlab("basalt", Blocks.BASALT::defaultBlockState);
    public static final RegistryObject<Block> BASALT_WALL = registerWall("basalt", Blocks.BASALT::defaultBlockState);
    public static final RegistryObject<Block> BASALT_PRESSURE_PLATE = registerPressurePlate("basalt", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BASALT_BUTTON = registerButton("basalt", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_BASALT_STAIRS = registerStair("smooth_basalt", Blocks.SMOOTH_BASALT::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_BASALT_SLAB = registerSlab("smooth_basalt", Blocks.SMOOTH_BASALT::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_BASALT_WALL = registerWall("smooth_basalt", Blocks.SMOOTH_BASALT::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_BASALT_PRESSURE_PLATE = registerPressurePlate("smooth_basalt", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_BASALT_BUTTON = registerButton("smooth_basalt", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_BASALT_STAIRS = registerStair("polished_basalt", Blocks.POLISHED_BASALT::defaultBlockState);
    public static final RegistryObject<Block> POLISHED_BASALT_SLAB = registerSlab("polished_basalt", Blocks.POLISHED_BASALT::defaultBlockState);
    public static final RegistryObject<Block> POLISHED_BASALT_WALL = registerWall("polished_basalt", Blocks.POLISHED_BASALT::defaultBlockState);
    public static final RegistryObject<Block> POLISHED_BASALT_PRESSURE_PLATE = registerPressurePlate("polished_basalt", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_BASALT_BUTTON = registerButton("polished_basalt", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLACKSTONE_PRESSURE_PLATE = registerPressurePlate("blackstone", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLACKSTONE_BUTTON = registerButton("blackstone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_BLACKSTONE_BRICKS_PRESSURE_PLATE = registerPressurePlate("polished_blackstone_bricks", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_BLACKSTONE_BRICKS_BUTTON = registerButton("polished_blackstone_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GILDED_BLACKSTONE_STAIRS = registerStair("gilded_blackstone", Blocks.GILDED_BLACKSTONE::defaultBlockState);
    public static final RegistryObject<Block> GILDED_BLACKSTONE_SLAB = registerSlab("gilded_blackstone", Blocks.GILDED_BLACKSTONE::defaultBlockState);
    public static final RegistryObject<Block> GILDED_BLACKSTONE_WALL = registerWall("gilded_blackstone", Blocks.GILDED_BLACKSTONE::defaultBlockState);
    public static final RegistryObject<Block> GILDED_BLACKSTONE_PRESSURE_PLATE = registerPressurePlate("gilded_blackstone", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GILDED_BLACKSTONE_BUTTON = registerButton("gilded_blackstone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> END_STONE_STAIRS = registerStair("end_stone", Blocks.END_STONE::defaultBlockState);
    public static final RegistryObject<Block> END_STONE_SLAB = registerSlab("end_stone", Blocks.END_STONE::defaultBlockState);
    public static final RegistryObject<Block> END_STONE_WALL = registerWall("end_stone", Blocks.END_STONE::defaultBlockState);
    public static final RegistryObject<Block> END_STONE_PRESSURE_PLATE = registerPressurePlate("end_stone", false, MapColor.SAND, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> END_STONE_BUTTON = registerButton("end_stone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> END_STONE_BRICKS_PRESSURE_PLATE = registerPressurePlate("end_stone_bricks", false, MapColor.SAND, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> END_STONE_BRICKS_BUTTON = registerButton("end_stone_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPUR_WALL = registerWall("purpur", Blocks.PURPUR_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> PURPUR_PRESSURE_PLATE = registerPressurePlate("purpur", false, MapColor.COLOR_MAGENTA, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPUR_BUTTON = registerButton("purpur", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPUR_PILLAR_STAIRS = registerStair("purpur_pillar", Blocks.PURPUR_PILLAR::defaultBlockState);
    public static final RegistryObject<Block> PURPUR_PILLAR_SLAB = registerSlab("purpur_pillar", Blocks.PURPUR_PILLAR::defaultBlockState);
    public static final RegistryObject<Block> PURPUR_PILLAR_WALL = registerWall("purpur_pillar", Blocks.PURPUR_PILLAR::defaultBlockState);
    public static final RegistryObject<Block> PURPUR_PILLAR_PRESSURE_PLATE = registerPressurePlate("purpur_pillar", false, MapColor.COLOR_MAGENTA, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPUR_PILLAR_BUTTON = registerButton("purpur_pillar", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> QUARTZ_WALL = registerWall("quartz", Blocks.QUARTZ_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_PRESSURE_PLATE = registerPressurePlate("quartz", false, MapColor.QUARTZ, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> QUARTZ_BUTTON = registerButton("quartz", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_QUARTZ_WALL = registerWall("smooth_quartz", Blocks.SMOOTH_QUARTZ::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_QUARTZ_PRESSURE_PLATE = registerPressurePlate("smooth_quartz", false, MapColor.QUARTZ, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_QUARTZ_BUTTON = registerButton("smooth_quartz", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> QUARTZ_BRICKS_STAIRS = registerStair("quartz_bricks", Blocks.QUARTZ_BRICKS::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_BRICKS_SLAB = registerSlab("quartz_bricks", Blocks.QUARTZ_BRICKS::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_BRICKS_WALL = registerWall("quartz_bricks", Blocks.QUARTZ_BRICKS::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_BRICKS_PRESSURE_PLATE = registerPressurePlate("quartz_bricks", false, MapColor.QUARTZ, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> QUARTZ_BRICKS_BUTTON = registerButton("quartz_bricks", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> QUARTZ_PILLAR_STAIRS = registerStair("quartz_pillar", Blocks.QUARTZ_PILLAR::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_PILLAR_SLAB = registerSlab("quartz_pillar", Blocks.QUARTZ_PILLAR::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_PILLAR_WALL = registerWall("quartz_pillar", Blocks.QUARTZ_PILLAR::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_PILLAR_PRESSURE_PLATE = registerPressurePlate("quartz_pillar", false, MapColor.QUARTZ, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> QUARTZ_PILLAR_BUTTON = registerButton("quartz_pillar", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> TERRACOTTA_STAIRS = registerStair("terracotta", Blocks.TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> TERRACOTTA_SLAB = registerSlab("terracotta", Blocks.TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> TERRACOTTA_WALL = registerWall("terracotta", Blocks.TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> TERRACOTTA_PRESSURE_PLATE = registerPressurePlate("terracotta", false, MapColor.COLOR_ORANGE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> TERRACOTTA_BUTTON = registerButton("terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CALCITE_STAIRS = registerStair("calcite", Blocks.CALCITE::defaultBlockState);
    public static final RegistryObject<Block> CALCITE_SLAB = registerSlab("calcite", Blocks.CALCITE::defaultBlockState);
    public static final RegistryObject<Block> CALCITE_WALL = registerWall("calcite", Blocks.CALCITE::defaultBlockState);
    public static final RegistryObject<Block> CALCITE_PRESSURE_PLATE = registerPressurePlate("calcite", false, MapColor.TERRACOTTA_WHITE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CALCITE_BUTTON = registerButton("calcite", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> TUFF_STAIRS = registerStair("tuff", Blocks.TUFF::defaultBlockState);
    public static final RegistryObject<Block> TUFF_SLAB = registerSlab("tuff", Blocks.TUFF::defaultBlockState);
    public static final RegistryObject<Block> TUFF_WALL = registerWall("tuff", Blocks.TUFF::defaultBlockState);
    public static final RegistryObject<Block> TUFF_PRESSURE_PLATE = registerPressurePlate("tuff", false, MapColor.TERRACOTTA_GRAY, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> TUFF_BUTTON = registerButton("tuff", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DRIPSTONE_STAIRS = registerStair("dripstone", Blocks.DRIPSTONE_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> DRIPSTONE_SLAB = registerSlab("dripstone", Blocks.DRIPSTONE_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> DRIPSTONE_WALL = registerWall("dripstone", Blocks.DRIPSTONE_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> DRIPSTONE_PRESSURE_PLATE = registerPressurePlate("dripstone", false, MapColor.TERRACOTTA_BROWN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DRIPSTONE_BUTTON = registerButton("dripstone", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> OBSIDIAN_STAIRS = registerStair("obsidian", Blocks.OBSIDIAN::defaultBlockState);
    public static final RegistryObject<Block> OBSIDIAN_SLAB = registerSlab("obsidian", Blocks.OBSIDIAN::defaultBlockState);
    public static final RegistryObject<Block> OBSIDIAN_WALL = registerWall("obsidian", Blocks.OBSIDIAN::defaultBlockState);
    public static final RegistryObject<Block> OBSIDIAN_PRESSURE_PLATE = registerPressurePlate("obsidian", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> OBSIDIAN_BUTTON = registerButton("obsidian", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CRYING_OBSIDIAN_STAIRS = registerBlock("crying_obsidian_stairs", CryingObsidianStairs::new);
    public static final RegistryObject<Block> CRYING_OBSIDIAN_SLAB = registerBlock("crying_obsidian_slab", CryingObsidianSlab::new);
    public static final RegistryObject<Block> CRYING_OBSIDIAN_WALL = registerBlock("crying_obsidian_wall", CryingObsidianWall::new);
    public static final RegistryObject<Block> CRYING_OBSIDIAN_PRESSURE_PLATE = registerPressurePlate("crying_obsidian", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CRYING_OBSIDIAN_BUTTON = registerButton("crying_obsidian", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GLOWING_OBSIDIAN = registerBlock("glowing_obsidian", Suppliers.memoize(() -> new UnmovableBlock(PropertyHelper.copy(Blocks.OBSIDIAN).lightLevel(state -> 15))));
    public static final RegistryObject<Block> GLOWING_OBSIDIAN_STAIRS = registerStair("glowing_obsidian", Suppliers.memoize(() -> GLOWING_OBSIDIAN.get().defaultBlockState()));
    public static final RegistryObject<Block> GLOWING_OBSIDIAN_SLAB = registerSlab("glowing_obsidian", Suppliers.memoize(() -> GLOWING_OBSIDIAN.get().defaultBlockState()));
    public static final RegistryObject<Block> GLOWING_OBSIDIAN_WALL = registerWall("glowing_obsidian", Suppliers.memoize(() -> GLOWING_OBSIDIAN.get().defaultBlockState()));
    public static final RegistryObject<Block> GLOWING_OBSIDIAN_PRESSURE_PLATE = registerPressurePlate("glowing_obsidian", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GLOWING_OBSIDIAN_BUTTON = registerButton("glowing_obsidian", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Metal Block Sets

    public static final RegistryObject<Block> IRON_STAIRS = registerStair("iron", Blocks.IRON_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> IRON_SLAB = registerSlab("iron", Blocks.IRON_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> IRON_CAGE = registerCage("iron", Blocks.IRON_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> IRON_GRATE = registerHorizontalPane("iron_grate", Suppliers.memoize(() -> Blocks.IRON_BARS.defaultBlockState()));
    public static final RegistryObject<Block> CUT_IRON = registerBlock("cut_iron", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> CUT_IRON_STAIRS = registerStair("cut_iron", Suppliers.memoize(() -> CUT_IRON.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_IRON_SLAB = registerSlab("cut_iron", Suppliers.memoize(() -> CUT_IRON.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_IRON_PRESSURE_PLATE = registerWeightedPressurePlate("cut_iron", 15, MapColor.METAL, Suppliers.memoize(() -> BlockSetType.IRON));
    public static final RegistryObject<Block> GOLDEN_STAIRS = registerStair("golden", Blocks.GOLD_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> GOLDEN_SLAB = registerSlab("golden", Blocks.GOLD_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> GOLDEN_DOOR = registerDoor("golden", true, Suppliers.memoize(() -> BlockSetType.GOLD));
    public static final RegistryObject<Block> GOLDEN_TRAPDOOR = registerTrapdoor("golden", true, Suppliers.memoize(() -> BlockSetType.GOLD));
    public static final RegistryObject<Block> GOLDEN_CHAIN = registerChain("golden");
    public static final RegistryObject<Block> GOLDEN_LANTERN = registerLantern("golden", false);
    public static final RegistryObject<Block> GOLDEN_SOUL_LANTERN = registerLantern("golden", true);
    public static final RegistryObject<Block> GOLD_BARS = registerBars("gold");
    public static final RegistryObject<Block> GOLDEN_CAGE = registerCage("golden", Blocks.GOLD_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> GOLD_GRATE = registerHorizontalPane("gold_grate", Suppliers.memoize(() -> GOLD_BARS.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_GOLD = registerBlock("cut_gold", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.GOLD_BLOCK)));
    public static final RegistryObject<Block> CUT_GOLDEN_STAIRS = registerStair("cut_golden", Suppliers.memoize(() -> CUT_GOLD.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_GOLDEN_SLAB = registerSlab("cut_golden", Suppliers.memoize(() -> CUT_GOLD.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_GOLDEN_PRESSURE_PLATE = registerWeightedPressurePlate("cut_golden", 15, MapColor.GOLD, Suppliers.memoize(() -> BlockSetType.GOLD));
    public static final RegistryObject<Block> NETHERITE_STAIRS = registerStair("netherite", Blocks.NETHERITE_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> NETHERITE_SLAB = registerSlab("netherite", Blocks.NETHERITE_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> NETHERITE_DOOR = registerDoor("netherite", true, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> NETHERITE_TRAPDOOR = registerTrapdoor("netherite", true, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> NETHERITE_PRESSURE_PLATE = registerWeightedPressurePlate("netherite", 100, MapColor.COLOR_BLACK, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> NETHERITE_CHAIN = registerChain("netherite");
    public static final RegistryObject<Block> NETHERITE_LANTERN = registerLantern("netherite", false);
    public static final RegistryObject<Block> NETHERITE_SOUL_LANTERN = registerLantern("netherite", true);
    public static final RegistryObject<Block> NETHERITE_BARS = registerBars("netherite");
    public static final RegistryObject<Block> NETHERITE_CAGE = registerCage("netherite", Blocks.NETHERITE_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> NETHERITE_GRATE = registerHorizontalPane("netherite_grate", Suppliers.memoize(() -> NETHERITE_BARS.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_NETHERITE = registerBlock("cut_netherite", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> CUT_NETHERITE_STAIRS = registerStair("cut_netherite", Suppliers.memoize(() -> CUT_NETHERITE.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_NETHERITE_SLAB = registerSlab("cut_netherite", Suppliers.memoize(() -> CUT_NETHERITE.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_NETHERITE_PRESSURE_PLATE = registerWeightedPressurePlate("cut_netherite", 100, MapColor.COLOR_BLACK, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> ALUMINUM_STAIRS = registerStair("aluminum", Suppliers.memoize(() -> ALUMINUM_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> ALUMINUM_SLAB = registerSlab("aluminum", Suppliers.memoize(() -> ALUMINUM_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> ALUMINUM_DOOR = registerDoor("aluminum", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> ALUMINUM_TRAPDOOR = registerTrapdoor("aluminum", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> ALUMINUM_PRESSURE_PLATE = registerWeightedPressurePlate("aluminum", 15, MWColors.ALUMINUM.toMapColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> ALUMINUM_CHAIN = registerChain("aluminum");
    public static final RegistryObject<Block> ALUMINUM_LANTERN = registerLantern("aluminum", false);
    public static final RegistryObject<Block> ALUMINUM_SOUL_LANTERN = registerLantern("aluminum", true);
    public static final RegistryObject<Block> ALUMINUM_BARS = registerBars("aluminum");
    public static final RegistryObject<Block> ALUMINUM_CAGE = registerCage("aluminum", Suppliers.memoize(() -> ALUMINUM_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> ALUMINUM_GRATE = registerHorizontalPane("aluminum_grate", Suppliers.memoize(() -> ALUMINUM_BARS.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_ALUMINUM = registerBlock("cut_aluminum", Suppliers.memoize(() -> PropertyHelper.copy(ALUMINUM_BLOCK.get())));
    public static final RegistryObject<Block> CUT_ALUMINUM_STAIRS = registerStair("cut_aluminum", Suppliers.memoize(() -> CUT_ALUMINUM.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_ALUMINUM_SLAB = registerSlab("cut_aluminum", Suppliers.memoize(() -> CUT_ALUMINUM.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_ALUMINUM_PRESSURE_PLATE = registerWeightedPressurePlate("cut_aluminum", 15, MWColors.ALUMINUM.toMapColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> BRONZE_STAIRS = registerStair("bronze", Suppliers.memoize(() -> BRONZE_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> BRONZE_SLAB = registerSlab("bronze", Suppliers.memoize(() -> BRONZE_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> BRONZE_DOOR = registerDoor("bronze", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> BRONZE_TRAPDOOR = registerTrapdoor("bronze", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> BRONZE_PRESSURE_PLATE = registerWeightedPressurePlate("bronze", 15, MWColors.BRONZE.toMapColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> BRONZE_CHAIN = registerChain("bronze");
    public static final RegistryObject<Block> BRONZE_LANTERN = registerLantern("bronze", false);
    public static final RegistryObject<Block> BRONZE_SOUL_LANTERN = registerLantern("bronze", true);
    public static final RegistryObject<Block> BRONZE_BARS = registerBars("bronze");
    public static final RegistryObject<Block> BRONZE_CAGE = registerCage("bronze", Suppliers.memoize(() -> BRONZE_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> BRONZE_GRATE = registerHorizontalPane("bronze_grate", Suppliers.memoize(() -> BRONZE_BARS.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_BRONZE = registerBlock("cut_bronze", Suppliers.memoize(() -> PropertyHelper.copy(BRONZE_BLOCK.get())));
    public static final RegistryObject<Block> CUT_BRONZE_STAIRS = registerStair("cut_bronze", Suppliers.memoize(() -> CUT_BRONZE.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_BRONZE_SLAB = registerSlab("cut_bronze", Suppliers.memoize(() -> CUT_BRONZE.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_BRONZE_PRESSURE_PLATE = registerWeightedPressurePlate("cut_bronze", 15, MWColors.BRONZE.toMapColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> SILVER_STAIRS = registerStair("silver", Suppliers.memoize(() -> SILVER_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> SILVER_SLAB = registerSlab("silver", Suppliers.memoize(() -> SILVER_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> SILVER_DOOR = registerDoor("silver", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> SILVER_TRAPDOOR = registerTrapdoor("silver", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> SILVER_PRESSURE_PLATE = registerWeightedPressurePlate("silver", 50, MWColors.SILVER.toMapColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> SILVER_CHAIN = registerChain("silver");
    public static final RegistryObject<Block> SILVER_LANTERN = registerLantern("silver", false);
    public static final RegistryObject<Block> SILVER_SOUL_LANTERN = registerLantern("silver", true);
    public static final RegistryObject<Block> SILVER_BARS = registerBars("silver");
    public static final RegistryObject<Block> SILVER_CAGE = registerCage("silver", Suppliers.memoize(() -> SILVER_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> SILVER_GRATE = registerHorizontalPane("silver_grate", Suppliers.memoize(() -> SILVER_BARS.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_SILVER = registerBlock("cut_silver", Suppliers.memoize(() -> PropertyHelper.copy(SILVER_BLOCK.get())));
    public static final RegistryObject<Block> CUT_SILVER_STAIRS = registerStair("cut_silver", Suppliers.memoize(() -> CUT_SILVER.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_SILVER_SLAB = registerSlab("cut_silver", Suppliers.memoize(() -> CUT_SILVER.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_SILVER_PRESSURE_PLATE = registerWeightedPressurePlate("cut_silver", 50, MWColors.SILVER.toMapColor(), MWBlockSetTypes.METAL);

    //#endregion

    //#region Glass Block Sets

    public static final RegistryObject<Block> GLASS_STAIRS = registerStair("glass", Blocks.GLASS::defaultBlockState);
    public static final RegistryObject<Block> GLASS_SLAB = registerSlab("glass", Blocks.GLASS::defaultBlockState);
    public static final RegistryObject<Block> GLASS_WALL = registerGlassWall("glass", Blocks.GLASS::defaultBlockState);

    //#endregion

    //#region Flowers, Crops and Plants

    public static final RegistryObject<Block> BLUE_ROSE = registerFlower("blue_rose", Suppliers.memoize(() -> MobEffects.SATURATION));
    public static final RegistryObject<Block> BLUE_ROSE_BUSH = registerTallFlower("blue_rose_bush");
    public static final RegistryObject<Block> WHITE_ROSE = registerFlower("white_rose", () -> MobEffects.HEAL);
    public static final RegistryObject<Block> WHITE_ROSE_BUSH = registerTallFlower("white_rose_bush");

    public static final RegistryObject<Block> CORN = registerBlockWithoutBlockItem("corn", CornBlock::new);

    //#endregion

    //#region TNT

    public static final RegistryObject<Block> DISGUISED_GRASS_TNT = registerTnt(MWPrimedTnt.Type.DISGUISED_GRASS);
    public static final RegistryObject<Block> DISGUISED_DIRT_TNT = registerTnt(MWPrimedTnt.Type.DISGUISED_DIRT);
    public static final RegistryObject<Block> DISGUISED_SAND_TNT = registerFallableTnt(MWPrimedTnt.Type.DISGUISED_SAND, 14406560);
    public static final RegistryObject<Block> DISGUISED_RED_SAND_TNT = registerFallableTnt(MWPrimedTnt.Type.DISGUISED_RED_SAND, 11098145);
    public static final RegistryObject<Block> DISGUISED_STONE_TNT = registerTnt(MWPrimedTnt.Type.DISGUISED_STONE);
    public static final RegistryObject<Block> DISGUISED_CAKE_TNT = registerTnt(MWPrimedTnt.Type.DISGUISED_CAKE);
    public static final RegistryObject<Block> MEGA_TNT = registerTnt(MWPrimedTnt.Type.MEGA);
    public static final RegistryObject<Block> SUPER_TNT = registerTnt(MWPrimedTnt.Type.SUPER);
    public static final RegistryObject<Block> HYPER_TNT = registerTnt(MWPrimedTnt.Type.HYPER);

    //#endregion

    //#region Misc

    public static final RegistryObject<Block> DAYLIGHT_LAMP = registerBlock("daylight_lamp", Suppliers.memoize(() -> new DaylightLampBlock()));
    public static final RegistryObject<Block> HORIZONTAL_GLASS_PANE = registerHorizontalPane("horizontal_glass_pane", Blocks.GLASS::defaultBlockState);

    //#endregion

    //#endregion

    //#region Methods

    /**
     * Register an {@link Block Overworld Ore Block}
     *
     * @param name {@link String The Block name}
     * @param isDeepslateOre {@link Boolean If the Ore is a Deepslate Ore}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerOverworldOre(final String name, final boolean isDeepslateOre, final FeatureFlag... featureFlags) {
        return registerOreBlock(name, PropertyHelper.ore(isDeepslateOre, featureFlags), 3, 7);
    }

    /**
     * Register a {@link Block Nether Ore Block}
     *
     * @param name {@link String The Block name}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerNetherOre(final String name, final FeatureFlag... featureFlags) {
        return registerOreBlock(name, PropertyHelper.ore(false, featureFlags).mapColor(MapColor.NETHER).sound(SoundType.NETHER_ORE), 2, 5);
    }

    /**
     * Register an {@link DropExperienceBlock Ore Block}
     *
     * @param name {@link String The Block name}
     * @param properties {@link BlockBehaviour.Properties The Block Properties}
     * @param minXp {@link Integer The minimum amount of XP dropped by the block}
     * @param maxXp {@link Integer The maximum amount of XP dropped by the block}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerOreBlock(final String name, final BlockBehaviour.Properties properties, final int minXp, final int maxXp) {
        return registerBlock(name, () -> new DropExperienceBlock(properties, UniformInt.of(minXp, maxXp)));
    }

    /**
     * Register an {@link Block Ore Storage Block}
     *
     * @param name {@link String The Block name}
     * @param mapColor {@link MapColor The Block Map color}
     * @param isMetallic {@link Boolean If the Ore Storage Block is metallic}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerOreStorageBlock(final String name, final MapColor mapColor, final boolean isMetallic, final FeatureFlag... featureFlags) {
        return registerBlock(name, Suppliers.memoize(() -> PropertyHelper.oreStorage(mapColor, isMetallic ? SoundType.METAL : SoundType.STONE, featureFlags)));
    }

    /**
     * Register a {@link Block Fuel Block}
     *
     * @param name {@link String The Block name}
     * @param mapColor {@link MapColor The Block Map color}
     * @param burnTime {@link Integer The fuel burn time}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerFuel(final String name, final MapColor mapColor, final int burnTime, final FeatureFlag...featureFlags) {
        final RegistryObject<Block> block = registerBlockWithoutBlockItem(name, () -> new Block(PropertyHelper.oreStorage(mapColor, SoundType.STONE, featureFlags)));
        MWItems.registerItem(name, () -> new MWFuelBlockItem(block, burnTime, featureFlags));
        return block;
    }

    /**
     * Register a {@link MWStairBlock Stair Block}
     *
     * @param materialName {@link String The Block material name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the Stair is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerStair(final String materialName, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_stairs", () -> new MWStairBlock(blockStateSupplier, featureFlags));
    }

    /**
     * Register a {@link MWSlabBlock Slab Block}
     *
     * @param materialName {@link String The Block material name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the Slab is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerSlab(final String materialName, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_slab", () -> new MWSlabBlock(blockStateSupplier, featureFlags));
    }

    /**
     * Register a {@link MWWallBlock Wall Block}
     *
     * @param materialName {@link String The Block material name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the Wall is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerWall(final String materialName, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_wall", () -> new MWWallBlock(blockStateSupplier, featureFlags));
    }

    /**
     * Register a {@link GlassWallBlock Glass Wall Block}
     *
     * @param materialName {@link String The Block material name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the Wall is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerGlassWall(final String materialName, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_wall", () -> new GlassWallBlock(blockStateSupplier, featureFlags));
    }

    /**
     * Register a {@link PressurePlateBlock Pressure Plate Block}
     *
     * @param materialName {@link String The Block material name}
     * @param isWooden {@link Boolean If the Pressure Plate is a Wooden Pressure Plate}
     * @param mapColor {@link MapColor The Block Map color}
     * @param blockSetTypeSupplier {@link Supplier<BlockSetType> The Block Set Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerPressurePlate(final String materialName, final boolean isWooden, final MapColor mapColor, final Supplier<BlockSetType> blockSetTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_pressure_plate", () -> new PressurePlateBlock(isWooden ? PressurePlateBlock.Sensitivity.EVERYTHING : PressurePlateBlock.Sensitivity.MOBS,
                PropertyHelper.copy(isWooden ? Blocks.OAK_PRESSURE_PLATE : Blocks.STONE_PRESSURE_PLATE, featureFlags).mapColor(mapColor),
                blockSetTypeSupplier.get())
        );
    }

    /**
     * Register a {@link WeightedPressurePlateBlock Weighted Pressure Plate Block}
     *
     * @param materialName {@link String The Block material name}
     * @param maxWeight {@link Integer The Weighted Pressure Plate max weight}
     * @param mapColor {@link MapColor The Block Map color}
     * @param blockSetTypeSupplier {@link Supplier<BlockSetType> The Block Set Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerWeightedPressurePlate(final String materialName, final int maxWeight, final MapColor mapColor, final Supplier<BlockSetType> blockSetTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_pressure_plate", () -> new WeightedPressurePlateBlock(maxWeight, PropertyHelper.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, featureFlags).mapColor(mapColor), blockSetTypeSupplier.get()));
    }

    /**
     * Register a {@link ButtonBlock Button Block}
     *
     * @param materialName {@link String The Block material name}
     * @param isWooden {@link Boolean If the Button is a Wooden Button}
     * @param blockSetTypeSupplier {@link Supplier<BlockSetType> The Block Set Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerButton(final String materialName, final boolean isWooden, final Supplier<BlockSetType> blockSetTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_button", () -> new ButtonBlock(
                PropertyHelper.copy(isWooden ? Blocks.OAK_PRESSURE_PLATE : Blocks.STONE_PRESSURE_PLATE, featureFlags),
                blockSetTypeSupplier.get(),
                isWooden ? 30 : 20,
                isWooden)
        );
    }

    /**
     * Register a {@link DoorBlock Door Block}
     *
     * @param materialName {@link String The Block material name}
     * @param requiresPower {@link Boolean If the Door needs redstone to be activated}
     * @param blockSetTypeSupplier {@link BlockSetType The Door Block set type supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    public static RegistryObject<Block> registerDoor(final String materialName, final boolean requiresPower, final Supplier<BlockSetType> blockSetTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_door", () -> new DoorBlock(PropertyHelper.copy(requiresPower ? Blocks.IRON_DOOR : Blocks.OAK_DOOR, featureFlags), blockSetTypeSupplier.get()));
    }

    /**
     * Register a {@link TrapDoorBlock Trapdoor Block}
     *
     * @param materialName {@link String The Block material name}
     * @param requiresPower {@link Boolean If the Trapdoor needs redstone to be activated}
     * @param blockSetType {@link BlockSetType The Trapdoor Block Set Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    public static RegistryObject<Block> registerTrapdoor(final String materialName, final boolean requiresPower, final Supplier<BlockSetType> blockSetType, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_trapdoor", () -> new TrapDoorBlock(PropertyHelper.copy(requiresPower ? Blocks.IRON_TRAPDOOR : Blocks.OAK_TRAPDOOR, featureFlags), blockSetType.get()));
    }

    /**
     * Register a {@link ChainBlock Chain block}
     *
     * @param materialName {@link String The Block material name}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    public static RegistryObject<Block> registerChain(final String materialName, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_chain", () -> new ChainBlock(PropertyHelper.copy(Blocks.CHAIN, featureFlags)));
    }

    /**
     * Register a {@link LanternBlock Lantern Block}
     *
     * @param materialName {@link String The Block material name}
     * @param isSoulLantern {@link Boolean If the Lantern is a soul lantern}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    public static RegistryObject<Block> registerLantern(final String materialName, final boolean isSoulLantern, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + (isSoulLantern ? "_soul_lantern" : "_lantern"), () -> new LanternBlock(PropertyHelper.copy(isSoulLantern ? Blocks.SOUL_LANTERN : Blocks.LANTERN, featureFlags)));
    }

    /**
     * Register a {@link IronBarsBlock Bar Block}
     *
     * @param materialName {@link String The Block material name}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    public static RegistryObject<Block> registerBars(final String materialName, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_bars", () -> new IronBarsBlock(PropertyHelper.copy(Blocks.IRON_BARS, featureFlags)));
    }

    /**
     * Register a {@link Block Cage Block}
     *
     * @param materialName {@link String The Block material name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Block State Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerCage(final String materialName, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(materialName + "_cage", () -> new Block(PropertyHelper.makeTranslucent(blockStateSupplier.get().getBlock(), featureFlags).requiresCorrectToolForDrops()));
    }

    /**
     * Register a {@link MWFlowerBlock Flower Block}
     *
     * @param name {@link String The Block name}
     * @param effectSupplier {@link Supplier<MobEffect> The Supplier for the flower effect when used in suspicious stews}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerFlower(final String name, final Supplier<MobEffect> effectSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new MWFlowerBlock(effectSupplier, featureFlags));
    }

    /**
     * Register a {@link MWTallFlowerBlock Tall Flower Block}
     *
     * @param name {@link String The Block name}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerTallFlower(final String name, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new MWTallFlowerBlock(featureFlags));
    }

    /**
     * Register a {@link MWTntBlock TNT Block}
     *
     * @param type {@link MWPrimedTnt.Type The TNT Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerTnt(final MWPrimedTnt.Type type, final FeatureFlag... featureFlags) {
        return registerBlock(type.name().toLowerCase(Locale.ROOT) + "_tnt", () -> new MWTntBlock(type, featureFlags));
    }

    /**
     * Register a {@link FallableTntBlock Fallable TNT Block}
     *
     * @param type {@link MWPrimedTnt.Type The TNT Type}
     * @param dustColor {@link Integer The falling block dust color}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerFallableTnt(final MWPrimedTnt.Type type, final int dustColor, final FeatureFlag... featureFlags) {
        return registerBlock(type.name().toLowerCase(Locale.ROOT) + "_tnt", () -> new FallableTntBlock(type, dustColor, featureFlags));
    }

    /**
     * Register an {@link HorizontalPaneBlock horizontal pane Block}
     *
     * @param name {@link String The Block name}
     * @param blockSupplier {@link Supplier<BlockState> The Supplier for the Block State this pane is based on}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerHorizontalPane(final String name, final Supplier<BlockState> blockSupplier) {
        return registerBlockWithoutBlockItem(name, Suppliers.memoize(() -> new HorizontalPaneBlock(PropertyHelper.copy(blockSupplier.get().getBlock()))));
    }

    /**
     * Register a {@link Block Block} given its {@link BlockBehaviour.Properties Properties}
     *
     * @param name {@link String The Block name}
     * @param propertiesSupplier {@link Supplier<BlockBehaviour.Properties> The Block Properties Supplier}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerBlock(final String name, final Supplier<BlockBehaviour.Properties> propertiesSupplier) {
        return registerBlock(name, () -> new Block(propertiesSupplier.get()));
    }

    /**
     * Register a {@link Block Block} without registering its {@link BlockItem Block Item}
     *
     * @param name {@link String The Block name}
     * @param blockSupplier {@link Supplier The Block supplier}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerBlockWithoutBlockItem(final String name, final Supplier<? extends Block> blockSupplier) {
        return BLOCKS.register(name, blockSupplier);
    }

    /**
     * Register a {@link Block Block}
     *
     * @param name {@link String The Block name}
     * @param blockSupplier {@link Supplier The Block supplier}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerBlock(final String name, final Supplier<? extends Block> blockSupplier, final FeatureFlag... featureFlags) {
        final RegistryObject<Block> block = registerBlockWithoutBlockItem(name, blockSupplier);
        MWItems.registerBlockItem(name, block, featureFlags);
        return block;
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link Block Blocks}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        MWColoredBlocks.register();
        MWCopperBlocks.register();
        MWPebbles.PebbleBlocks.register();
        MWFlowerPots.register();
        BLOCKS.register(eventBus);
    }

    //#endregion

}