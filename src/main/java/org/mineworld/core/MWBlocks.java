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
    public static final RegistryObject<Block> MARBLE_STAIRS = registerStair("marble_stairs", Suppliers.memoize(MARBLE.get()::defaultBlockState));
    public static final RegistryObject<Block> MARBLE_SLAB = registerSlab("marble_slab", Suppliers.memoize(MARBLE.get()::defaultBlockState));
    public static final RegistryObject<Block> MARBLE_WALL = registerWall("marble_wall", Suppliers.memoize(MARBLE.get()::defaultBlockState));
    public static final RegistryObject<Block> MARBLE_PRESSURE_PLATE = registerPressurePlate("marble_pressure_plate", false, MWColors.MARBLE.toMapColor(), Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MARBLE_BUTTON = registerButton("marble_button", false, Suppliers.memoize(() -> BlockSetType.STONE));

    public static final RegistryObject<Block> STONE_WALL = registerWall("stone_wall", Blocks.STONE::defaultBlockState);
    public static final RegistryObject<Block> COBBLESTONE_PRESSURE_PLATE = registerPressurePlate("cobblestone_pressure_plate", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> COBBLESTONE_BUTTON = registerButton("cobblestone_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_STONE = registerBlock("mossy_stone", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.STONE)));
    public static final RegistryObject<Block> MOSSY_STONE_STAIRS = registerStair("mossy_stone_stairs", Suppliers.memoize(MOSSY_STONE.get()::defaultBlockState));
    public static final RegistryObject<Block> MOSSY_STONE_SLAB = registerSlab("mossy_stone_slab", Suppliers.memoize(MOSSY_STONE.get()::defaultBlockState));
    public static final RegistryObject<Block> MOSSY_STONE_WALL = registerWall("mossy_stone_wall", Suppliers.memoize(MOSSY_STONE.get()::defaultBlockState));
    public static final RegistryObject<Block> MOSSY_STONE_PRESSURE_PLATE = registerPressurePlate("mossy_stone_pressure_plate", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_STONE_BUTTON = registerButton("mossy_stone_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_COBBLESTONE_PRESSURE_PLATE = registerPressurePlate("mossy_cobblestone_pressure_plate", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_COBBLESTONE_BUTTON = registerButton("mossy_cobblestone_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_STONE_STAIRS = registerStair("smooth_stone_stairs", Blocks.SMOOTH_STONE::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_STONE_WALL = registerWall("smooth_stone_wall", Blocks.SMOOTH_STONE::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_STONE_PRESSURE_PLATE = registerPressurePlate("smooth_stone_pressure_plate", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_STONE_BUTTON = registerButton("smooth_stone_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> STONE_BRICKS_PRESSURE_PLATE = registerPressurePlate("stone_bricks_pressure_plate", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> STONE_BRICKS_BUTTON = registerButton("stone_bricks_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_STONE_BRICKS_PRESSURE_PLATE = registerPressurePlate("mossy_stone_bricks_pressure_plate", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_STONE_BRICKS_BUTTON = registerButton("mossy_stone_bricks_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MOSSY_CHISELED_STONE_BRICKS = registerBlock("mossy_chiseled_stone_bricks", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.CHISELED_STONE_BRICKS)));
    public static final RegistryObject<Block> MOSSY_CRACKED_STONE_BRICKS = registerBlock("mossy_cracked_stone_bricks", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.CRACKED_STONE_BRICKS)));
    public static final RegistryObject<Block> GRANITE_PRESSURE_PLATE = registerPressurePlate("granite_pressure_plate", false, MapColor.DIRT, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GRANITE_BUTTON = registerButton("granite_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_GRANITE_WALL = registerWall("polished_granite_wall", Blocks.POLISHED_GRANITE::defaultBlockState);
    public static final RegistryObject<Block> POLISHED_GRANITE_PRESSURE_PLATE = registerPressurePlate("polished_granite_pressure_plate", false, MapColor.DIRT, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_GRANITE_BUTTON = registerButton("polished_granite_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DIORITE_PRESSURE_PLATE = registerPressurePlate("diorite_pressure_plate", false, MapColor.QUARTZ, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DIORITE_BUTTON = registerButton("diorite_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_DIORITE_WALL = registerWall("polished_diorite_wall", Blocks.POLISHED_DIORITE::defaultBlockState);
    public static final RegistryObject<Block> POLISHED_DIORITE_PRESSURE_PLATE = registerPressurePlate("polished_diorite_pressure_plate", false, MapColor.QUARTZ, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_DIORITE_BUTTON = registerButton("polished_diorite_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> ANDESITE_PRESSURE_PLATE = registerPressurePlate("andesite_pressure_plate", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> ANDESITE_BUTTON = registerButton("andesite_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_ANDESITE_WALL = registerWall("polished_andesite_wall", Blocks.POLISHED_ANDESITE::defaultBlockState);
    public static final RegistryObject<Block> POLISHED_ANDESITE_PRESSURE_PLATE = registerPressurePlate("polished_andesite_pressure_plate", false, MapColor.STONE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_ANDESITE_BUTTON = registerButton("polished_andesite_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_STAIRS = registerStair("deepslate_stairs", Blocks.DEEPSLATE::defaultBlockState);
    public static final RegistryObject<Block> DEEPSLATE_SLAB = registerSlab("deepslate_slab", Blocks.DEEPSLATE::defaultBlockState);
    public static final RegistryObject<Block> DEEPSLATE_WALL = registerWall("deepslate_wall", Blocks.DEEPSLATE::defaultBlockState);
    public static final RegistryObject<Block> DEEPSLATE_PRESSURE_PLATE = registerPressurePlate("deepslate_pressure_plate", false, MapColor.DEEPSLATE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_BUTTON = registerButton("deepslate_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> COBBLED_DEEPSLATE_PRESSURE_PLATE = registerPressurePlate("cobbled_deepslate_pressure_plate", false, MapColor.DEEPSLATE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> COBBLED_DEEPSLATE_BUTTON = registerButton("cobbled_deepslate_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_DEEPSLATE_PRESSURE_PLATE = registerPressurePlate("polished_deepslate_pressure_plate", false, MapColor.DEEPSLATE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_DEEPSLATE_BUTTON = registerButton("polished_deepslate_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_BRICKS_PRESSURE_PLATE = registerPressurePlate("deepslate_bricks_pressure_plate", false, MapColor.DEEPSLATE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_BRICKS_BUTTON = registerButton("deepslate_bricks_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_TILES_PRESSURE_PLATE = registerPressurePlate("deepslate_tiles_pressure_plate", false, MapColor.DEEPSLATE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DEEPSLATE_TILES_BUTTON = registerButton("deepslate_tiles_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> REINFORCED_DEEPSLATE_STAIRS = registerStair("reinforced_deepslate_stairs", Blocks.REINFORCED_DEEPSLATE::defaultBlockState);
    public static final RegistryObject<Block> REINFORCED_DEEPSLATE_SLAB = registerSlab("reinforced_deepslate_slab", Blocks.REINFORCED_DEEPSLATE::defaultBlockState);
    public static final RegistryObject<Block> REINFORCED_DEEPSLATE_WALL = registerWall("reinforced_deepslate_wall", Blocks.REINFORCED_DEEPSLATE::defaultBlockState);
    public static final RegistryObject<Block> REINFORCED_DEEPSLATE_PRESSURE_PLATE = registerPressurePlate("reinforced_deepslate_pressure_plate", false, MapColor.DEEPSLATE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> REINFORCED_DEEPSLATE_BUTTON = registerButton("reinforced_deepslate_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BRICKS_PRESSURE_PLATE = registerPressurePlate("bricks_pressure_plate", false, MapColor.COLOR_RED, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BRICKS_BUTTON = registerButton("bricks_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MUD_BRICKS_PRESSURE_PLATE = registerPressurePlate("mud_bricks_pressure_plate", false, MapColor.TERRACOTTA_LIGHT_GRAY, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MUD_BRICKS_BUTTON = registerButton("mud_bricks_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SANDSTONE_PRESSURE_PLATE = registerPressurePlate("sandstone_pressure_plate", false, MapColor.SAND, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SANDSTONE_BUTTON = registerButton("sandstone_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_SANDSTONE_WALL = registerWall("smooth_sandstone_wall", Blocks.SANDSTONE::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_SANDSTONE_PRESSURE_PLATE = registerPressurePlate("smooth_sandstone_pressure_plate", false, MapColor.SAND, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_SANDSTONE_BUTTON = registerButton("smooth_sandstone_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CUT_SANDSTONE_STAIRS = registerStair("cut_sandstone_stairs", Blocks.CUT_SANDSTONE::defaultBlockState);
    public static final RegistryObject<Block> CUT_SANDSTONE_WALL = registerWall("cut_sandstone_wall", Blocks.CUT_SANDSTONE::defaultBlockState);
    public static final RegistryObject<Block> RED_SANDSTONE_PRESSURE_PLATE = registerPressurePlate("red_sandstone_pressure_plate", false, MapColor.COLOR_ORANGE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> RED_SANDSTONE_BUTTON = registerButton("red_sandstone_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_RED_SANDSTONE_WALL = registerWall("smooth_red_sandstone_wall", Blocks.RED_SANDSTONE::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_RED_SANDSTONE_PRESSURE_PLATE = registerPressurePlate("smooth_red_sandstone_pressure_plate", false, MapColor.COLOR_ORANGE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_RED_SANDSTONE_BUTTON = registerButton("smooth_red_sandstone_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CUT_RED_SANDSTONE_STAIRS = registerStair("cut_red_sandstone_stairs", Blocks.CUT_RED_SANDSTONE::defaultBlockState);
    public static final RegistryObject<Block> CUT_RED_SANDSTONE_WALL = registerWall("cut_red_sandstone_wall", Blocks.CUT_RED_SANDSTONE::defaultBlockState);
    public static final RegistryObject<Block> PRISMARINE_PRESSURE_PLATE = registerPressurePlate("prismarine_pressure_plate", false, MapColor.COLOR_CYAN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PRISMARINE_BUTTON = registerButton("prismarine_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PRISMARINE_BRICKS_WALL = registerWall("prismarine_bricks_wall", Blocks.PRISMARINE_BRICKS::defaultBlockState);
    public static final RegistryObject<Block> PRISMARINE_BRICKS_PRESSURE_PLATE = registerPressurePlate("prismarine_bricks_pressure_plate", false, MapColor.DIAMOND, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PRISMARINE_BRICKS_BUTTON = registerButton("prismarine_bricks_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DARK_PRISMARINE_WALL = registerWall("dark_prismarine_wall", Blocks.DARK_PRISMARINE::defaultBlockState);
    public static final RegistryObject<Block> DARK_PRISMARINE_PRESSURE_PLATE = registerPressurePlate("dark_prismarine_pressure_plate", false, MapColor.DIAMOND, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DARK_PRISMARINE_BUTTON = registerButton("dark_prismarine_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> NETHERRACK_STAIRS = registerStair("netherrack_stairs", Blocks.NETHERRACK::defaultBlockState);
    public static final RegistryObject<Block> NETHERRACK_SLAB = registerSlab("netherrack_slab", Blocks.NETHERRACK::defaultBlockState);
    public static final RegistryObject<Block> NETHERRACK_WALL = registerWall("netherrack_wall", Blocks.NETHERRACK::defaultBlockState);
    public static final RegistryObject<Block> NETHERRACK_PRESSURE_PLATE = registerPressurePlate("netherrack_pressure_plate", false, MapColor.NETHER, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> NETHERRACK_BUTTON = registerButton("netherrack_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> NETHER_BRICKS_PRESSURE_PLATE = registerPressurePlate("nether_bricks_pressure_plate", false, MapColor.NETHER, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> NETHER_BRICKS_BUTTON = registerButton("nether_bricks_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> RED_NETHER_BRICKS_PRESSURE_PLATE = registerPressurePlate("red_nether_bricks_pressure_plate", false, MapColor.NETHER, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> RED_NETHER_BRICKS_BUTTON = registerButton("red_nether_bricks_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CRACKED_RED_NETHER_BRICKS = registerBlock("cracked_red_nether_bricks", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.RED_NETHER_BRICKS)));
    public static final RegistryObject<Block> CHISELED_RED_NETHER_BRICKS = registerBlock("chiseled_red_nether_bricks", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.RED_NETHER_BRICKS)));
    public static final RegistryObject<Block> BASALT_STAIRS = registerStair("basalt_stairs", Blocks.BASALT::defaultBlockState);
    public static final RegistryObject<Block> BASALT_SLAB = registerSlab("basalt_slab", Blocks.BASALT::defaultBlockState);
    public static final RegistryObject<Block> BASALT_WALL = registerWall("basalt_wall", Blocks.BASALT::defaultBlockState);
    public static final RegistryObject<Block> BASALT_PRESSURE_PLATE = registerPressurePlate("basalt_pressure_plate", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BASALT_BUTTON = registerButton("basalt_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_BASALT_STAIRS = registerStair("smooth_basalt_stairs", Blocks.SMOOTH_BASALT::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_BASALT_SLAB = registerSlab("smooth_basalt_slab", Blocks.SMOOTH_BASALT::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_BASALT_WALL = registerWall("smooth_basalt_wall", Blocks.SMOOTH_BASALT::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_BASALT_PRESSURE_PLATE = registerPressurePlate("smooth_basalt_pressure_plate", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_BASALT_BUTTON = registerButton("smooth_basalt_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_BASALT_STAIRS = registerStair("polished_basalt_stairs", Blocks.POLISHED_BASALT::defaultBlockState);
    public static final RegistryObject<Block> POLISHED_BASALT_SLAB = registerSlab("polished_basalt_slab", Blocks.POLISHED_BASALT::defaultBlockState);
    public static final RegistryObject<Block> POLISHED_BASALT_WALL = registerWall("polished_basalt_wall", Blocks.POLISHED_BASALT::defaultBlockState);
    public static final RegistryObject<Block> POLISHED_BASALT_PRESSURE_PLATE = registerPressurePlate("polished_basalt_pressure_plate", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_BASALT_BUTTON = registerButton("polished_basalt_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLACKSTONE_PRESSURE_PLATE = registerPressurePlate("blackstone_pressure_plate", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLACKSTONE_BUTTON = registerButton("blackstone_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_BLACKSTONE_BRICKS_PRESSURE_PLATE = registerPressurePlate("polished_blackstone_bricks_pressure_plate", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> POLISHED_BLACKSTONE_BRICKS_BUTTON = registerButton("polished_blackstone_bricks_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GILDED_BLACKSTONE_STAIRS = registerStair("gilded_blackstone_stairs", Blocks.GILDED_BLACKSTONE::defaultBlockState);
    public static final RegistryObject<Block> GILDED_BLACKSTONE_SLAB = registerSlab("gilded_blackstone_slab", Blocks.GILDED_BLACKSTONE::defaultBlockState);
    public static final RegistryObject<Block> GILDED_BLACKSTONE_WALL = registerWall("gilded_blackstone_wall", Blocks.GILDED_BLACKSTONE::defaultBlockState);
    public static final RegistryObject<Block> GILDED_BLACKSTONE_PRESSURE_PLATE = registerPressurePlate("gilded_blackstone_pressure_plate", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GILDED_BLACKSTONE_BUTTON = registerButton("gilded_blackstone_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> END_STONE_STAIRS = registerStair("end_stone_stairs", Blocks.END_STONE::defaultBlockState);
    public static final RegistryObject<Block> END_STONE_SLAB = registerSlab("end_stone_slab", Blocks.END_STONE::defaultBlockState);
    public static final RegistryObject<Block> END_STONE_WALL = registerWall("end_stone_wall", Blocks.END_STONE::defaultBlockState);
    public static final RegistryObject<Block> END_STONE_PRESSURE_PLATE = registerPressurePlate("end_stone_pressure_plate", false, MapColor.SAND, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> END_STONE_BUTTON = registerButton("end_stone_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> END_STONE_BRICKS_PRESSURE_PLATE = registerPressurePlate("end_stone_bricks_pressure_plate", false, MapColor.SAND, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> END_STONE_BRICKS_BUTTON = registerButton("end_stone_bricks_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPUR_WALL = registerWall("purpur_wall", Blocks.PURPUR_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> PURPUR_PRESSURE_PLATE = registerPressurePlate("purpur_pressure_plate", false, MapColor.COLOR_MAGENTA, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPUR_BUTTON = registerButton("purpur_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPUR_PILLAR_STAIRS = registerStair("purpur_pillar_stairs", Blocks.PURPUR_PILLAR::defaultBlockState);
    public static final RegistryObject<Block> PURPUR_PILLAR_SLAB = registerSlab("purpur_pillar_slab", Blocks.PURPUR_PILLAR::defaultBlockState);
    public static final RegistryObject<Block> PURPUR_PILLAR_WALL = registerWall("purpur_pillar_wall", Blocks.PURPUR_PILLAR::defaultBlockState);
    public static final RegistryObject<Block> PURPUR_PILLAR_PRESSURE_PLATE = registerPressurePlate("purpur_pillar_pressure_plate", false, MapColor.COLOR_MAGENTA, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPUR_PILLAR_BUTTON = registerButton("purpur_pillar_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> QUARTZ_WALL = registerWall("quartz_wall", Blocks.QUARTZ_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_PRESSURE_PLATE = registerPressurePlate("quartz_pressure_plate", false, MapColor.QUARTZ, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> QUARTZ_BUTTON = registerButton("quartz_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_QUARTZ_WALL = registerWall("smooth_quartz_wall", Blocks.SMOOTH_QUARTZ::defaultBlockState);
    public static final RegistryObject<Block> SMOOTH_QUARTZ_PRESSURE_PLATE = registerPressurePlate("smooth_quartz_pressure_plate", false, MapColor.QUARTZ, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> SMOOTH_QUARTZ_BUTTON = registerButton("smooth_quartz_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> QUARTZ_BRICKS_STAIRS = registerStair("quartz_bricks_stairs", Blocks.QUARTZ_BRICKS::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_BRICKS_SLAB = registerSlab("quartz_bricks_slab", Blocks.QUARTZ_BRICKS::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_BRICKS_WALL = registerWall("quartz_bricks_wall", Blocks.QUARTZ_BRICKS::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_BRICKS_PRESSURE_PLATE = registerPressurePlate("quartz_bricks_pressure_plate", false, MapColor.QUARTZ, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> QUARTZ_BRICKS_BUTTON = registerButton("quartz_bricks_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> QUARTZ_PILLAR_STAIRS = registerStair("quartz_pillar_stairs", Blocks.QUARTZ_PILLAR::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_PILLAR_SLAB = registerSlab("quartz_pillar_slab", Blocks.QUARTZ_PILLAR::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_PILLAR_WALL = registerWall("quartz_pillar_wall", Blocks.QUARTZ_PILLAR::defaultBlockState);
    public static final RegistryObject<Block> QUARTZ_PILLAR_PRESSURE_PLATE = registerPressurePlate("quartz_pillar_pressure_plate", false, MapColor.QUARTZ, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> QUARTZ_PILLAR_BUTTON = registerButton("quartz_pillar_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> TERRACOTTA_STAIRS = registerStair("terracotta_stairs", Blocks.TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> TERRACOTTA_SLAB = registerSlab("terracotta_slab", Blocks.TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> TERRACOTTA_WALL = registerWall("terracotta_wall", Blocks.TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> TERRACOTTA_PRESSURE_PLATE = registerPressurePlate("terracotta_pressure_plate", false, MapColor.COLOR_ORANGE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> TERRACOTTA_BUTTON = registerButton("terracotta_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CALCITE_STAIRS = registerStair("calcite_stairs", Blocks.CALCITE::defaultBlockState);
    public static final RegistryObject<Block> CALCITE_SLAB = registerSlab("calcite_slab", Blocks.CALCITE::defaultBlockState);
    public static final RegistryObject<Block> CALCITE_WALL = registerWall("calcite_wall", Blocks.CALCITE::defaultBlockState);
    public static final RegistryObject<Block> CALCITE_PRESSURE_PLATE = registerPressurePlate("calcite_pressure_plate", false, MapColor.TERRACOTTA_WHITE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CALCITE_BUTTON = registerButton("calcite_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> TUFF_STAIRS = registerStair("tuff_stairs", Blocks.TUFF::defaultBlockState);
    public static final RegistryObject<Block> TUFF_SLAB = registerSlab("tuff_slab", Blocks.TUFF::defaultBlockState);
    public static final RegistryObject<Block> TUFF_WALL = registerWall("tuff_wall", Blocks.TUFF::defaultBlockState);
    public static final RegistryObject<Block> TUFF_PRESSURE_PLATE = registerPressurePlate("tuff_pressure_plate", false, MapColor.TERRACOTTA_GRAY, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> TUFF_BUTTON = registerButton("tuff_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DRIPSTONE_STAIRS = registerStair("dripstone_stairs", Blocks.DRIPSTONE_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> DRIPSTONE_SLAB = registerSlab("dripstone_slab", Blocks.DRIPSTONE_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> DRIPSTONE_WALL = registerWall("dripstone_wall", Blocks.DRIPSTONE_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> DRIPSTONE_PRESSURE_PLATE = registerPressurePlate("dripstone_pressure_plate", false, MapColor.TERRACOTTA_BROWN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> DRIPSTONE_BUTTON = registerButton("dripstone_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> OBSIDIAN_STAIRS = registerStair("obsidian_stairs", Blocks.OBSIDIAN::defaultBlockState);
    public static final RegistryObject<Block> OBSIDIAN_SLAB = registerSlab("obsidian_slab", Blocks.OBSIDIAN::defaultBlockState);
    public static final RegistryObject<Block> OBSIDIAN_WALL = registerWall("obsidian_wall", Blocks.OBSIDIAN::defaultBlockState);
    public static final RegistryObject<Block> OBSIDIAN_PRESSURE_PLATE = registerPressurePlate("obsidian_pressure_plate", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> OBSIDIAN_BUTTON = registerButton("obsidian_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CRYING_OBSIDIAN_STAIRS = registerBlock("crying_obsidian_stairs", CryingObsidianStairs::new);
    public static final RegistryObject<Block> CRYING_OBSIDIAN_SLAB = registerBlock("crying_obsidian_slab", CryingObsidianSlab::new);
    public static final RegistryObject<Block> CRYING_OBSIDIAN_WALL = registerBlock("crying_obsidian_wall", CryingObsidianWall::new);
    public static final RegistryObject<Block> CRYING_OBSIDIAN_PRESSURE_PLATE = registerPressurePlate("crying_obsidian_pressure_plate", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CRYING_OBSIDIAN_BUTTON = registerButton("crying_obsidian_button", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Metal Block Sets

    public static final RegistryObject<Block> IRON_STAIRS = registerStair("iron_stairs", Blocks.IRON_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> IRON_SLAB = registerSlab("iron_slab", Blocks.IRON_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> IRON_CAGE = registerCage("iron_cage", Blocks.IRON_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> CUT_IRON = registerBlock("cut_iron", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> CUT_IRON_STAIRS = registerStair("cut_iron_stairs", Suppliers.memoize(() -> CUT_IRON.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_IRON_SLAB = registerSlab("cut_iron_slab", Suppliers.memoize(() -> CUT_IRON.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_IRON_PRESSURE_PLATE = registerWeightedPressurePlate("cut_iron_pressure_plate", 15, MapColor.METAL, Suppliers.memoize(() -> BlockSetType.IRON));
    public static final RegistryObject<Block> GOLDEN_STAIRS = registerStair("golden_stairs", Blocks.GOLD_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> GOLDEN_SLAB = registerSlab("golden_slab", Blocks.GOLD_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> GOLDEN_DOOR = registerDoor("golden_door", true, Suppliers.memoize(() -> BlockSetType.GOLD));
    public static final RegistryObject<Block> GOLDEN_TRAPDOOR = registerTrapdoor("golden_trapdoor", true, Suppliers.memoize(() -> BlockSetType.GOLD));
    public static final RegistryObject<Block> GOLDEN_CHAIN = registerChain("golden_chain");
    public static final RegistryObject<Block> GOLDEN_LANTERN = registerLantern("golden_lantern", false);
    public static final RegistryObject<Block> GOLDEN_SOUL_LANTERN = registerLantern("golden_soul_lantern", true);
    public static final RegistryObject<Block> GOLD_BARS = registerBars("gold_bars");
    public static final RegistryObject<Block> GOLDEN_CAGE = registerCage("golden_cage", Blocks.GOLD_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> CUT_GOLD = registerBlock("cut_gold", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.GOLD_BLOCK)));
    public static final RegistryObject<Block> CUT_GOLDEN_STAIRS = registerStair("cut_golden_stairs", Suppliers.memoize(() -> CUT_GOLD.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_GOLDEN_SLAB = registerSlab("cut_golden_slab", Suppliers.memoize(() -> CUT_GOLD.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_GOLDEN_PRESSURE_PLATE = registerWeightedPressurePlate("cut_golden_pressure_plate", 15, MapColor.GOLD, Suppliers.memoize(() -> BlockSetType.GOLD));
    //copper
    public static final RegistryObject<Block> NETHERITE_STAIRS = registerStair("netherite_stairs", Blocks.NETHERITE_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> NETHERITE_SLAB = registerSlab("netherite_slab", Blocks.NETHERITE_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> NETHERITE_DOOR = registerDoor("netherite_door", true, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> NETHERITE_TRAPDOOR = registerTrapdoor("netherite_trapdoor", true, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> NETHERITE_PRESSURE_PLATE = registerWeightedPressurePlate("netherite_pressure_plate", 100, MapColor.COLOR_BLACK, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> NETHERITE_CHAIN = registerChain("netherite_chain");
    public static final RegistryObject<Block> NETHERITE_LANTERN = registerLantern("netherite_lantern", false);
    public static final RegistryObject<Block> NETHERITE_SOUL_LANTERN = registerLantern("netherite_soul_lantern", true);
    public static final RegistryObject<Block> NETHERITE_BARS = registerBars("netherite_bars");
    public static final RegistryObject<Block> NETHERITE_CAGE = registerCage("netherite_cage", Blocks.NETHERITE_BLOCK::defaultBlockState);
    public static final RegistryObject<Block> CUT_NETHERITE = registerBlock("cut_netherite", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> CUT_NETHERITE_STAIRS = registerStair("cut_netherite_stairs", Suppliers.memoize(() -> CUT_NETHERITE.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_NETHERITE_SLAB = registerSlab("cut_netherite_slab", Suppliers.memoize(() -> CUT_NETHERITE.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_NETHERITE_PRESSURE_PLATE = registerWeightedPressurePlate("cut_netherite_pressure_plate", 100, MapColor.COLOR_BLACK, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> ALUMINUM_STAIRS = registerStair("aluminum_stairs", Suppliers.memoize(() -> ALUMINUM_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> ALUMINUM_SLAB = registerSlab("aluminum_slab", Suppliers.memoize(() -> ALUMINUM_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> ALUMINUM_DOOR = registerDoor("aluminum_door", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> ALUMINUM_TRAPDOOR = registerTrapdoor("aluminum_trapdoor", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> ALUMINUM_PRESSURE_PLATE = registerWeightedPressurePlate("aluminum_pressure_plate", 15, MWColors.ALUMINUM.toMapColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> ALUMINUM_CHAIN = registerChain("aluminum_chain");
    public static final RegistryObject<Block> ALUMINUM_LANTERN = registerLantern("aluminum_lantern", false);
    public static final RegistryObject<Block> ALUMINUM_SOUL_LANTERN = registerLantern("aluminum_soul_lantern", true);
    public static final RegistryObject<Block> ALUMINUM_BARS = registerBars("aluminum_bars");
    public static final RegistryObject<Block> ALUMINUM_CAGE = registerCage("aluminum_cage", Suppliers.memoize(() -> ALUMINUM_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_ALUMINUM = registerBlock("cut_aluminum", Suppliers.memoize(() -> PropertyHelper.copy(ALUMINUM_BLOCK.get())));
    public static final RegistryObject<Block> CUT_ALUMINUM_STAIRS = registerStair("cut_aluminum_stairs", Suppliers.memoize(() -> CUT_ALUMINUM.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_ALUMINUM_SLAB = registerSlab("cut_aluminum_slab", Suppliers.memoize(() -> CUT_ALUMINUM.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_ALUMINUM_PRESSURE_PLATE = registerWeightedPressurePlate("cut_aluminum_pressure_plate", 15, MWColors.ALUMINUM.toMapColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> BRONZE_STAIRS = registerStair("bronze_stairs", Suppliers.memoize(() -> BRONZE_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> BRONZE_SLAB = registerSlab("bronze_slab", Suppliers.memoize(() -> BRONZE_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> BRONZE_DOOR = registerDoor("bronze_door", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> BRONZE_TRAPDOOR = registerTrapdoor("bronze_trapdoor", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> BRONZE_PRESSURE_PLATE = registerWeightedPressurePlate("bronze_pressure_plate", 15, MWColors.BRONZE.toMapColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> BRONZE_CHAIN = registerChain("bronze_chain");
    public static final RegistryObject<Block> BRONZE_LANTERN = registerLantern("bronze_lantern", false);
    public static final RegistryObject<Block> BRONZE_SOUL_LANTERN = registerLantern("bronze_soul_lantern", true);
    public static final RegistryObject<Block> BRONZE_BARS = registerBars("bronze_bars");
    public static final RegistryObject<Block> BRONZE_CAGE = registerCage("bronze_cage", Suppliers.memoize(() -> BRONZE_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_BRONZE = registerBlock("cut_bronze", Suppliers.memoize(() -> PropertyHelper.copy(BRONZE_BLOCK.get())));
    public static final RegistryObject<Block> CUT_BRONZE_STAIRS = registerStair("cut_bronze_stairs", Suppliers.memoize(() -> CUT_BRONZE.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_BRONZE_SLAB = registerSlab("cut_bronze_slab", Suppliers.memoize(() -> CUT_BRONZE.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_BRONZE_PRESSURE_PLATE = registerWeightedPressurePlate("cut_bronze_pressure_plate", 15, MWColors.BRONZE.toMapColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> SILVER_STAIRS = registerStair("silver_stairs", Suppliers.memoize(() -> SILVER_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> SILVER_SLAB = registerSlab("silver_slab", Suppliers.memoize(() -> SILVER_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> SILVER_DOOR = registerDoor("silver_door", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> SILVER_TRAPDOOR = registerTrapdoor("silver_trapdoor", true, MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> SILVER_PRESSURE_PLATE = registerWeightedPressurePlate("silver_pressure_plate", 50, MWColors.SILVER.toMapColor(), MWBlockSetTypes.METAL);
    public static final RegistryObject<Block> SILVER_CHAIN = registerChain("silver_chain");
    public static final RegistryObject<Block> SILVER_LANTERN = registerLantern("silver_lantern", false);
    public static final RegistryObject<Block> SILVER_SOUL_LANTERN = registerLantern("silver_soul_lantern", true);
    public static final RegistryObject<Block> SILVER_BARS = registerBars("silver_bars");
    public static final RegistryObject<Block> SILVER_CAGE = registerCage("silver_cage", Suppliers.memoize(() -> SILVER_BLOCK.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_SILVER = registerBlock("cut_silver", Suppliers.memoize(() -> PropertyHelper.copy(SILVER_BLOCK.get())));
    public static final RegistryObject<Block> CUT_SILVER_STAIRS = registerStair("cut_silver_stairs", Suppliers.memoize(() -> CUT_SILVER.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_SILVER_SLAB = registerSlab("cut_silver_slab", Suppliers.memoize(() -> CUT_SILVER.get().defaultBlockState()));
    public static final RegistryObject<Block> CUT_SILVER_PRESSURE_PLATE = registerWeightedPressurePlate("cut_silver_pressure_plate", 50, MWColors.SILVER.toMapColor(), MWBlockSetTypes.METAL);

    //#endregion

    //#region Glass Block Sets

    public static final RegistryObject<Block> GLASS_STAIRS = registerStair("glass_stairs", Blocks.GLASS::defaultBlockState);
    public static final RegistryObject<Block> GLASS_SLAB = registerSlab("glass_slab", Blocks.GLASS::defaultBlockState);
    public static final RegistryObject<Block> GLASS_WALL = registerGlassWall("glass_wall", Blocks.GLASS::defaultBlockState);

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
     * @param name {@link String The Block name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the Stair is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerStair(final String name, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new MWStairBlock(blockStateSupplier, featureFlags));
    }

    /**
     * Register a {@link MWSlabBlock Slab Block}
     *
     * @param name {@link String The Block name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the Slab is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerSlab(final String name, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new MWSlabBlock(blockStateSupplier, featureFlags));
    }

    /**
     * Register a {@link MWWallBlock Wall Block}
     *
     * @param name {@link String The Block name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the Wall is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerWall(final String name, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new MWWallBlock(blockStateSupplier, featureFlags));
    }

    /**
     * Register a {@link GlassWallBlock Glass Wall Block}
     *
     * @param name {@link String The Block name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the Wall is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerGlassWall(final String name, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new GlassWallBlock(blockStateSupplier, featureFlags));
    }

    /**
     * Register a {@link PressurePlateBlock Pressure Plate Block}
     *
     * @param name {@link String The Block name}
     * @param isWooden {@link Boolean If the Pressure Plate is a Wooden Pressure Plate}
     * @param mapColor {@link MapColor The Block Map color}
     * @param blockSetTypeSupplier {@link Supplier<BlockSetType> The Block Set Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerPressurePlate(final String name, final boolean isWooden, final MapColor mapColor, final Supplier<BlockSetType> blockSetTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new PressurePlateBlock(isWooden ? PressurePlateBlock.Sensitivity.EVERYTHING : PressurePlateBlock.Sensitivity.MOBS,
                PropertyHelper.copy(isWooden ? Blocks.OAK_PRESSURE_PLATE : Blocks.STONE_PRESSURE_PLATE, featureFlags).mapColor(mapColor),
                blockSetTypeSupplier.get())
        );
    }

    /**
     * Register a {@link WeightedPressurePlateBlock Weighted Pressure Plate Block}
     *
     * @param name {@link String The Block name}
     * @param maxWeight {@link Integer The Weighted Pressure Plate max weight}
     * @param mapColor {@link MapColor The Block Map color}
     * @param blockSetTypeSupplier {@link Supplier<BlockSetType> The Block Set Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerWeightedPressurePlate(final String name, final int maxWeight, final MapColor mapColor, final Supplier<BlockSetType> blockSetTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new WeightedPressurePlateBlock(maxWeight, PropertyHelper.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, featureFlags).mapColor(mapColor), blockSetTypeSupplier.get()));
    }

    /**
     * Register a {@link ButtonBlock Button Block}
     *
     * @param name {@link String The Block name}
     * @param isWooden {@link Boolean If the Button is a Wooden Button}
     * @param blockSetTypeSupplier {@link Supplier<BlockSetType> The Block Set Type Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    static RegistryObject<Block> registerButton(final String name, final boolean isWooden, final Supplier<BlockSetType> blockSetTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new ButtonBlock(
                PropertyHelper.copy(isWooden ? Blocks.OAK_PRESSURE_PLATE : Blocks.STONE_PRESSURE_PLATE, featureFlags),
                blockSetTypeSupplier.get(),
                isWooden ? 30 : 20,
                isWooden)
        );
    }

    /**
     * Register a {@link DoorBlock Door Block}
     *
     * @param name {@link String The Block name}
     * @param requiresPower {@link Boolean If the Door needs redstone to be activated}
     * @param blockSetTypeSupplier {@link BlockSetType The Door Block set type supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    public static RegistryObject<Block> registerDoor(final String name, final boolean requiresPower, final Supplier<BlockSetType> blockSetTypeSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new DoorBlock(PropertyHelper.copy(requiresPower ? Blocks.IRON_DOOR : Blocks.OAK_DOOR, featureFlags), blockSetTypeSupplier.get()));
    }

    /**
     * Register a {@link TrapDoorBlock Trapdoor Block}
     *
     * @param name {@link String The Block name}
     * @param requiresPower {@link Boolean If the Trapdoor needs redstone to be activated}
     * @param blockSetType {@link BlockSetType The Trapdoor Block Set Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    public static RegistryObject<Block> registerTrapdoor(final String name, final boolean requiresPower, final Supplier<BlockSetType> blockSetType, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new TrapDoorBlock(PropertyHelper.copy(requiresPower ? Blocks.IRON_TRAPDOOR : Blocks.OAK_TRAPDOOR, featureFlags), blockSetType.get()));
    }

    /**
     * Register a {@link ChainBlock Chain block}
     *
     * @param name {@link String The Block name}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    public static RegistryObject<Block> registerChain(final String name, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new ChainBlock(PropertyHelper.copy(Blocks.CHAIN, featureFlags)));
    }

    /**
     * Register a {@link LanternBlock Lantern Block}
     *
     * @param name {@link String The Block name}
     * @param isSoulLantern {@link Boolean If the Lantern is a soul lantern}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    public static RegistryObject<Block> registerLantern(final String name, final boolean isSoulLantern, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new LanternBlock(PropertyHelper.copy(isSoulLantern ? Blocks.SOUL_LANTERN : Blocks.LANTERN, featureFlags)));
    }

    /**
     * Register a {@link IronBarsBlock Bar Block}
     *
     * @param name {@link String The Block name}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    public static RegistryObject<Block> registerBars(final String name, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new IronBarsBlock(PropertyHelper.copy(Blocks.IRON_BARS, featureFlags)));
    }

    /**
     * Register a {@link Block Cage Block}
     *
     * @param name {@link String The Block name}
     * @param blockStateSupplier {@link Supplier<BlockState> The Block State Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerCage(final String name, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return registerBlock(name, () -> new Block(PropertyHelper.makeTranslucent(blockStateSupplier.get().getBlock(), featureFlags).requiresCorrectToolForDrops()));
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
    private static RegistryObject<Block> registerBlock(final String name, final Supplier<? extends Block> blockSupplier, final FeatureFlag... featureFlags) {
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
        MWPebbles.PebbleBlocks.register();
        MWFlowerPots.register();
        BLOCKS.register(eventBus);
    }

    //#endregion

}