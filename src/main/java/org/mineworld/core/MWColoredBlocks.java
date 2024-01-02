package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} {@link Block Colored Blocks}
 */
public final class MWColoredBlocks {

    //#region Colored Blocks

    //#region Marble

    public static final RegistryObject<Block> WHITE_MARBLE = MWBlocks.registerBlock("white_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_WHITE)));
    public static final RegistryObject<Block> WHITE_MARBLE_STAIRS = MWBlocks.registerStair("white_marble_stairs", Suppliers.memoize(() -> WHITE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> WHITE_MARBLE_SLAB = MWBlocks.registerSlab("white_marble_slab", Suppliers.memoize(() -> WHITE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> WHITE_MARBLE_WALL = MWBlocks.registerWall("white_marble_wall", Suppliers.memoize(() -> WHITE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> WHITE_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("white_marble_pressure_plate", false, MapColor.TERRACOTTA_WHITE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> WHITE_MARBLE_BUTTON = MWBlocks.registerButton("white_marble_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> ORANGE_MARBLE = MWBlocks.registerBlock("orange_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistryObject<Block> ORANGE_MARBLE_STAIRS = MWBlocks.registerStair("orange_marble_stairs", Suppliers.memoize(() -> ORANGE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> ORANGE_MARBLE_SLAB = MWBlocks.registerSlab("orange_marble_slab", Suppliers.memoize(() -> ORANGE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> ORANGE_MARBLE_WALL = MWBlocks.registerWall("orange_marble_wall", Suppliers.memoize(() -> ORANGE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> ORANGE_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("orange_marble_pressure_plate", false, MapColor.TERRACOTTA_ORANGE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> ORANGE_MARBLE_BUTTON = MWBlocks.registerButton("orange_marble_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MAGENTA_MARBLE = MWBlocks.registerBlock("magenta_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_MAGENTA)));
    public static final RegistryObject<Block> MAGENTA_MARBLE_STAIRS = MWBlocks.registerStair("magenta_marble_stairs", Suppliers.memoize(() -> MAGENTA_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> MAGENTA_MARBLE_SLAB = MWBlocks.registerSlab("magenta_marble_slab", Suppliers.memoize(() -> MAGENTA_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> MAGENTA_MARBLE_WALL = MWBlocks.registerWall("magenta_marble_wall", Suppliers.memoize(() -> MAGENTA_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> MAGENTA_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("magenta_marble_pressure_plate", false, MapColor.TERRACOTTA_MAGENTA, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MAGENTA_MARBLE_BUTTON = MWBlocks.registerButton("magenta_marble_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_BLUE_MARBLE = MWBlocks.registerBlock("light_blue_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)));
    public static final RegistryObject<Block> LIGHT_BLUE_MARBLE_STAIRS = MWBlocks.registerStair("light_blue_marble_stairs", Suppliers.memoize(() -> LIGHT_BLUE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> LIGHT_BLUE_MARBLE_SLAB = MWBlocks.registerSlab("light_blue_marble_slab", Suppliers.memoize(() -> LIGHT_BLUE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> LIGHT_BLUE_MARBLE_WALL = MWBlocks.registerWall("light_blue_marble_wall", Suppliers.memoize(() -> LIGHT_BLUE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> LIGHT_BLUE_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("light_blue_marble_pressure_plate", false, MapColor.TERRACOTTA_LIGHT_BLUE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_BLUE_MARBLE_BUTTON = MWBlocks.registerButton("light_blue_marble_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> YELLOW_MARBLE = MWBlocks.registerBlock("yellow_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_YELLOW)));
    public static final RegistryObject<Block> YELLOW_MARBLE_STAIRS = MWBlocks.registerStair("yellow_marble_stairs", Suppliers.memoize(() -> YELLOW_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> YELLOW_MARBLE_SLAB = MWBlocks.registerSlab("yellow_marble_slab", Suppliers.memoize(() -> YELLOW_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> YELLOW_MARBLE_WALL = MWBlocks.registerWall("yellow_marble_wall", Suppliers.memoize(() -> YELLOW_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> YELLOW_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("yellow_marble_pressure_plate", false, MapColor.TERRACOTTA_YELLOW, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> YELLOW_MARBLE_BUTTON = MWBlocks.registerButton("yellow_marble_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIME_MARBLE = MWBlocks.registerBlock("lime_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)));
    public static final RegistryObject<Block> LIME_MARBLE_STAIRS = MWBlocks.registerStair("lime_marble_stairs", Suppliers.memoize(() -> LIME_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> LIME_MARBLE_SLAB = MWBlocks.registerSlab("lime_marble_slab", Suppliers.memoize(() -> LIME_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> LIME_MARBLE_WALL = MWBlocks.registerWall("lime_marble_wall", Suppliers.memoize(() -> LIME_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> LIME_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("lime_marble_pressure_plate", false, MapColor.TERRACOTTA_LIGHT_GREEN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIME_MARBLE_BUTTON = MWBlocks.registerButton("lime_marble_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PINK_MARBLE = MWBlocks.registerBlock("pink_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_PINK)));
    public static final RegistryObject<Block> PINK_MARBLE_STAIRS = MWBlocks.registerStair("pink_marble_stairs", Suppliers.memoize(() -> PINK_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> PINK_MARBLE_SLAB = MWBlocks.registerSlab("pink_marble_slab", Suppliers.memoize(() -> PINK_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> PINK_MARBLE_WALL = MWBlocks.registerWall("pink_marble_wall", Suppliers.memoize(() -> PINK_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> PINK_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("pink_marble_pressure_plate", false, MapColor.TERRACOTTA_PINK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PINK_MARBLE_BUTTON = MWBlocks.registerButton("pink_marble_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GRAY_MARBLE = MWBlocks.registerBlock("gray_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_GRAY)));
    public static final RegistryObject<Block> GRAY_MARBLE_STAIRS = MWBlocks.registerStair("gray_marble_stairs", Suppliers.memoize(() -> GRAY_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> GRAY_MARBLE_SLAB = MWBlocks.registerSlab("gray_marble_slab", Suppliers.memoize(() -> GRAY_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> GRAY_MARBLE_WALL = MWBlocks.registerWall("gray_marble_wall", Suppliers.memoize(() -> GRAY_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> GRAY_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("gray_marble_pressure_plate", false, MapColor.TERRACOTTA_GRAY, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GRAY_MARBLE_BUTTON = MWBlocks.registerButton("gray_marble_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_GRAY_MARBLE = MWBlocks.registerBlock("light_gray_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)));
    public static final RegistryObject<Block> LIGHT_GRAY_MARBLE_STAIRS = MWBlocks.registerStair("light_gray_marble_stairs", Suppliers.memoize(() -> LIGHT_GRAY_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> LIGHT_GRAY_MARBLE_SLAB = MWBlocks.registerSlab("light_gray_marble_slab", Suppliers.memoize(() -> LIGHT_GRAY_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> LIGHT_GRAY_MARBLE_WALL = MWBlocks.registerWall("light_gray_marble_wall", Suppliers.memoize(() -> LIGHT_GRAY_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> LIGHT_GRAY_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("light_gray_marble_pressure_plate", false, MapColor.TERRACOTTA_LIGHT_GRAY, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_GRAY_MARBLE_BUTTON = MWBlocks.registerButton("light_gray_marble_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CYAN_MARBLE = MWBlocks.registerBlock("cyan_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_CYAN)));
    public static final RegistryObject<Block> CYAN_MARBLE_STAIRS = MWBlocks.registerStair("cyan_marble_stairs", Suppliers.memoize(() -> CYAN_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> CYAN_MARBLE_SLAB = MWBlocks.registerSlab("cyan_marble_slab", Suppliers.memoize(() -> CYAN_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> CYAN_MARBLE_WALL = MWBlocks.registerWall("cyan_marble_wall", Suppliers.memoize(() -> CYAN_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> CYAN_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("cyan_marble_pressure_plate", false, MapColor.TERRACOTTA_CYAN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CYAN_MARBLE_BUTTON = MWBlocks.registerButton("cyan_marble_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPLE_MARBLE = MWBlocks.registerBlock("purple_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_PURPLE)));
    public static final RegistryObject<Block> PURPLE_MARBLE_STAIRS = MWBlocks.registerStair("purple_marble_stairs", Suppliers.memoize(() -> PURPLE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> PURPLE_MARBLE_SLAB = MWBlocks.registerSlab("purple_marble_slab", Suppliers.memoize(() -> PURPLE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> PURPLE_MARBLE_WALL = MWBlocks.registerWall("purple_marble_wall", Suppliers.memoize(() -> PURPLE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> PURPLE_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("purple_marble_pressure_plate", false, MapColor.TERRACOTTA_PURPLE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPLE_MARBLE_BUTTON = MWBlocks.registerButton("purple_marble_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLUE_MARBLE = MWBlocks.registerBlock("blue_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_BLUE)));
    public static final RegistryObject<Block> BLUE_MARBLE_STAIRS = MWBlocks.registerStair("blue_marble_stairs", Suppliers.memoize(() -> BLUE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> BLUE_MARBLE_SLAB = MWBlocks.registerSlab("blue_marble_slab", Suppliers.memoize(() -> BLUE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> BLUE_MARBLE_WALL = MWBlocks.registerWall("blue_marble_wall", Suppliers.memoize(() -> BLUE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> BLUE_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("blue_marble_pressure_plate", false, MapColor.TERRACOTTA_BLUE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLUE_MARBLE_BUTTON = MWBlocks.registerButton("blue_marble_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BROWN_MARBLE = MWBlocks.registerBlock("brown_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final RegistryObject<Block> BROWN_MARBLE_STAIRS = MWBlocks.registerStair("brown_marble_stairs", Suppliers.memoize(() -> BROWN_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> BROWN_MARBLE_SLAB = MWBlocks.registerSlab("brown_marble_slab", Suppliers.memoize(() -> BROWN_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> BROWN_MARBLE_WALL = MWBlocks.registerWall("brown_marble_wall", Suppliers.memoize(() -> BROWN_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> BROWN_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("brown_marble_pressure_plate", false, MapColor.TERRACOTTA_BROWN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BROWN_MARBLE_BUTTON = MWBlocks.registerButton("brown_marble_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GREEN_MARBLE = MWBlocks.registerBlock("green_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_GREEN)));
    public static final RegistryObject<Block> GREEN_MARBLE_STAIRS = MWBlocks.registerStair("green_marble_stairs", Suppliers.memoize(() -> GREEN_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> GREEN_MARBLE_SLAB = MWBlocks.registerSlab("green_marble_slab", Suppliers.memoize(() -> GREEN_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> GREEN_MARBLE_WALL = MWBlocks.registerWall("green_marble_wall", Suppliers.memoize(() -> GREEN_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> GREEN_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("green_marble_pressure_plate", false, MapColor.TERRACOTTA_GREEN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GREEN_MARBLE_BUTTON = MWBlocks.registerButton("green_marble_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> RED_MARBLE = MWBlocks.registerBlock("red_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_RED)));
    public static final RegistryObject<Block> RED_MARBLE_STAIRS = MWBlocks.registerStair("red_marble_stairs", Suppliers.memoize(() -> RED_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> RED_MARBLE_SLAB = MWBlocks.registerSlab("red_marble_slab", Suppliers.memoize(() -> RED_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> RED_MARBLE_WALL = MWBlocks.registerWall("red_marble_wall", Suppliers.memoize(() -> RED_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> RED_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("red_marble_pressure_plate", false, MapColor.TERRACOTTA_RED, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> RED_MARBLE_BUTTON = MWBlocks.registerButton("red_marble_button", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLACK_MARBLE = MWBlocks.registerBlock("black_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_BLACK)));
    public static final RegistryObject<Block> BLACK_MARBLE_STAIRS = MWBlocks.registerStair("black_marble_stairs", Suppliers.memoize(() -> BLACK_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> BLACK_MARBLE_SLAB = MWBlocks.registerSlab("black_marble_slab", Suppliers.memoize(() -> BLACK_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> BLACK_MARBLE_WALL = MWBlocks.registerWall("black_marble_wall", Suppliers.memoize(() -> BLACK_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> BLACK_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("black_marble_pressure_plate", false, MapColor.TERRACOTTA_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLACK_MARBLE_BUTTON = MWBlocks.registerButton("black_marble_button", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#endregion

    //#region Bus register

    /**
     * Register all {@link Block Colored Blocks}
     */
    public static void register() { }

    //#endregion

}