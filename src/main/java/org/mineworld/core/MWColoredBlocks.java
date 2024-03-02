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
    public static final RegistryObject<Block> WHITE_MARBLE_STAIRS = MWBlocks.registerStair("white_marble", Suppliers.memoize(() -> WHITE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> WHITE_MARBLE_SLAB = MWBlocks.registerSlab("white_marble", Suppliers.memoize(() -> WHITE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> WHITE_MARBLE_WALL = MWBlocks.registerWall("white_marble", Suppliers.memoize(() -> WHITE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> WHITE_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("white_marble", false, MapColor.TERRACOTTA_WHITE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> WHITE_MARBLE_BUTTON = MWBlocks.registerButton("white_marble", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> ORANGE_MARBLE = MWBlocks.registerBlock("orange_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistryObject<Block> ORANGE_MARBLE_STAIRS = MWBlocks.registerStair("orange_marble", Suppliers.memoize(() -> ORANGE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> ORANGE_MARBLE_SLAB = MWBlocks.registerSlab("orange_marble", Suppliers.memoize(() -> ORANGE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> ORANGE_MARBLE_WALL = MWBlocks.registerWall("orange_marble", Suppliers.memoize(() -> ORANGE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> ORANGE_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("orange_marble", false, MapColor.TERRACOTTA_ORANGE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> ORANGE_MARBLE_BUTTON = MWBlocks.registerButton("orange_marble", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MAGENTA_MARBLE = MWBlocks.registerBlock("magenta_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_MAGENTA)));
    public static final RegistryObject<Block> MAGENTA_MARBLE_STAIRS = MWBlocks.registerStair("magenta_marble", Suppliers.memoize(() -> MAGENTA_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> MAGENTA_MARBLE_SLAB = MWBlocks.registerSlab("magenta_marble", Suppliers.memoize(() -> MAGENTA_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> MAGENTA_MARBLE_WALL = MWBlocks.registerWall("magenta_marble", Suppliers.memoize(() -> MAGENTA_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> MAGENTA_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("magenta_marble", false, MapColor.TERRACOTTA_MAGENTA, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MAGENTA_MARBLE_BUTTON = MWBlocks.registerButton("magenta_marble", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_BLUE_MARBLE = MWBlocks.registerBlock("light_blue_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)));
    public static final RegistryObject<Block> LIGHT_BLUE_MARBLE_STAIRS = MWBlocks.registerStair("light_blue_marble", Suppliers.memoize(() -> LIGHT_BLUE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> LIGHT_BLUE_MARBLE_SLAB = MWBlocks.registerSlab("light_blue_marble", Suppliers.memoize(() -> LIGHT_BLUE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> LIGHT_BLUE_MARBLE_WALL = MWBlocks.registerWall("light_blue_marble", Suppliers.memoize(() -> LIGHT_BLUE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> LIGHT_BLUE_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("light_blue_marble", false, MapColor.TERRACOTTA_LIGHT_BLUE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_BLUE_MARBLE_BUTTON = MWBlocks.registerButton("light_blue_marble", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> YELLOW_MARBLE = MWBlocks.registerBlock("yellow_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_YELLOW)));
    public static final RegistryObject<Block> YELLOW_MARBLE_STAIRS = MWBlocks.registerStair("yellow_marble", Suppliers.memoize(() -> YELLOW_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> YELLOW_MARBLE_SLAB = MWBlocks.registerSlab("yellow_marble", Suppliers.memoize(() -> YELLOW_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> YELLOW_MARBLE_WALL = MWBlocks.registerWall("yellow_marble", Suppliers.memoize(() -> YELLOW_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> YELLOW_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("yellow_marble", false, MapColor.TERRACOTTA_YELLOW, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> YELLOW_MARBLE_BUTTON = MWBlocks.registerButton("yellow_marble", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIME_MARBLE = MWBlocks.registerBlock("lime_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)));
    public static final RegistryObject<Block> LIME_MARBLE_STAIRS = MWBlocks.registerStair("lime_marble", Suppliers.memoize(() -> LIME_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> LIME_MARBLE_SLAB = MWBlocks.registerSlab("lime_marble", Suppliers.memoize(() -> LIME_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> LIME_MARBLE_WALL = MWBlocks.registerWall("lime_marble", Suppliers.memoize(() -> LIME_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> LIME_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("lime_marble", false, MapColor.TERRACOTTA_LIGHT_GREEN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIME_MARBLE_BUTTON = MWBlocks.registerButton("lime_marble", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PINK_MARBLE = MWBlocks.registerBlock("pink_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_PINK)));
    public static final RegistryObject<Block> PINK_MARBLE_STAIRS = MWBlocks.registerStair("pink_marble", Suppliers.memoize(() -> PINK_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> PINK_MARBLE_SLAB = MWBlocks.registerSlab("pink_marble", Suppliers.memoize(() -> PINK_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> PINK_MARBLE_WALL = MWBlocks.registerWall("pink_marble", Suppliers.memoize(() -> PINK_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> PINK_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("pink_marble", false, MapColor.TERRACOTTA_PINK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PINK_MARBLE_BUTTON = MWBlocks.registerButton("pink_marble", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GRAY_MARBLE = MWBlocks.registerBlock("gray_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_GRAY)));
    public static final RegistryObject<Block> GRAY_MARBLE_STAIRS = MWBlocks.registerStair("gray_marble", Suppliers.memoize(() -> GRAY_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> GRAY_MARBLE_SLAB = MWBlocks.registerSlab("gray_marble", Suppliers.memoize(() -> GRAY_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> GRAY_MARBLE_WALL = MWBlocks.registerWall("gray_marble", Suppliers.memoize(() -> GRAY_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> GRAY_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("gray_marble", false, MapColor.TERRACOTTA_GRAY, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GRAY_MARBLE_BUTTON = MWBlocks.registerButton("gray_marble", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_GRAY_MARBLE = MWBlocks.registerBlock("light_gray_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)));
    public static final RegistryObject<Block> LIGHT_GRAY_MARBLE_STAIRS = MWBlocks.registerStair("light_gray_marble", Suppliers.memoize(() -> LIGHT_GRAY_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> LIGHT_GRAY_MARBLE_SLAB = MWBlocks.registerSlab("light_gray_marble", Suppliers.memoize(() -> LIGHT_GRAY_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> LIGHT_GRAY_MARBLE_WALL = MWBlocks.registerWall("light_gray_marble", Suppliers.memoize(() -> LIGHT_GRAY_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> LIGHT_GRAY_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("light_gray_marble", false, MapColor.TERRACOTTA_LIGHT_GRAY, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_GRAY_MARBLE_BUTTON = MWBlocks.registerButton("light_gray_marble", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CYAN_MARBLE = MWBlocks.registerBlock("cyan_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_CYAN)));
    public static final RegistryObject<Block> CYAN_MARBLE_STAIRS = MWBlocks.registerStair("cyan_marble", Suppliers.memoize(() -> CYAN_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> CYAN_MARBLE_SLAB = MWBlocks.registerSlab("cyan_marble", Suppliers.memoize(() -> CYAN_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> CYAN_MARBLE_WALL = MWBlocks.registerWall("cyan_marble", Suppliers.memoize(() -> CYAN_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> CYAN_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("cyan_marble", false, MapColor.TERRACOTTA_CYAN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CYAN_MARBLE_BUTTON = MWBlocks.registerButton("cyan_marble", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPLE_MARBLE = MWBlocks.registerBlock("purple_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_PURPLE)));
    public static final RegistryObject<Block> PURPLE_MARBLE_STAIRS = MWBlocks.registerStair("purple_marble", Suppliers.memoize(() -> PURPLE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> PURPLE_MARBLE_SLAB = MWBlocks.registerSlab("purple_marble", Suppliers.memoize(() -> PURPLE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> PURPLE_MARBLE_WALL = MWBlocks.registerWall("purple_marble", Suppliers.memoize(() -> PURPLE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> PURPLE_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("purple_marble", false, MapColor.TERRACOTTA_PURPLE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPLE_MARBLE_BUTTON = MWBlocks.registerButton("purple_marble", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLUE_MARBLE = MWBlocks.registerBlock("blue_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_BLUE)));
    public static final RegistryObject<Block> BLUE_MARBLE_STAIRS = MWBlocks.registerStair("blue_marble", Suppliers.memoize(() -> BLUE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> BLUE_MARBLE_SLAB = MWBlocks.registerSlab("blue_marble", Suppliers.memoize(() -> BLUE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> BLUE_MARBLE_WALL = MWBlocks.registerWall("blue_marble", Suppliers.memoize(() -> BLUE_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> BLUE_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("blue_marble", false, MapColor.TERRACOTTA_BLUE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLUE_MARBLE_BUTTON = MWBlocks.registerButton("blue_marble", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BROWN_MARBLE = MWBlocks.registerBlock("brown_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final RegistryObject<Block> BROWN_MARBLE_STAIRS = MWBlocks.registerStair("brown_marble", Suppliers.memoize(() -> BROWN_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> BROWN_MARBLE_SLAB = MWBlocks.registerSlab("brown_marble", Suppliers.memoize(() -> BROWN_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> BROWN_MARBLE_WALL = MWBlocks.registerWall("brown_marble", Suppliers.memoize(() -> BROWN_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> BROWN_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("brown_marble", false, MapColor.TERRACOTTA_BROWN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BROWN_MARBLE_BUTTON = MWBlocks.registerButton("brown_marble", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GREEN_MARBLE = MWBlocks.registerBlock("green_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_GREEN)));
    public static final RegistryObject<Block> GREEN_MARBLE_STAIRS = MWBlocks.registerStair("green_marble", Suppliers.memoize(() -> GREEN_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> GREEN_MARBLE_SLAB = MWBlocks.registerSlab("green_marble", Suppliers.memoize(() -> GREEN_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> GREEN_MARBLE_WALL = MWBlocks.registerWall("green_marble", Suppliers.memoize(() -> GREEN_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> GREEN_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("green_marble", false, MapColor.TERRACOTTA_GREEN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GREEN_MARBLE_BUTTON = MWBlocks.registerButton("green_marble", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> RED_MARBLE = MWBlocks.registerBlock("red_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_RED)));
    public static final RegistryObject<Block> RED_MARBLE_STAIRS = MWBlocks.registerStair("red_marble", Suppliers.memoize(() -> RED_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> RED_MARBLE_SLAB = MWBlocks.registerSlab("red_marble", Suppliers.memoize(() -> RED_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> RED_MARBLE_WALL = MWBlocks.registerWall("red_marble", Suppliers.memoize(() -> RED_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> RED_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("red_marble", false, MapColor.TERRACOTTA_RED, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> RED_MARBLE_BUTTON = MWBlocks.registerButton("red_marble", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLACK_MARBLE = MWBlocks.registerBlock("black_marble", Suppliers.memoize(() -> PropertyHelper.copy(Blocks.TUFF).mapColor(MapColor.TERRACOTTA_BLACK)));
    public static final RegistryObject<Block> BLACK_MARBLE_STAIRS = MWBlocks.registerStair("black_marble", Suppliers.memoize(() -> BLACK_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> BLACK_MARBLE_SLAB = MWBlocks.registerSlab("black_marble", Suppliers.memoize(() -> BLACK_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> BLACK_MARBLE_WALL = MWBlocks.registerWall("black_marble", Suppliers.memoize(() -> BLACK_MARBLE.get().defaultBlockState()));
    public static final RegistryObject<Block> BLACK_MARBLE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("black_marble", false, MapColor.TERRACOTTA_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLACK_MARBLE_BUTTON = MWBlocks.registerButton("black_marble", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Terracotta

    public static final RegistryObject<Block> WHITE_TERRACOTTA_STAIRS = MWBlocks.registerStair("white_terracotta", Blocks.WHITE_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> WHITE_TERRACOTTA_SLAB = MWBlocks.registerSlab("white_terracotta", Blocks.WHITE_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> WHITE_TERRACOTTA_WALL = MWBlocks.registerWall("white_terracotta", Blocks.WHITE_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> WHITE_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("white_terracotta", false, MapColor.TERRACOTTA_WHITE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> WHITE_TERRACOTTA_BUTTON = MWBlocks.registerButton("white_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> ORANGE_TERRACOTTA_STAIRS = MWBlocks.registerStair("orange_terracotta", Blocks.ORANGE_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> ORANGE_TERRACOTTA_SLAB = MWBlocks.registerSlab("orange_terracotta", Blocks.ORANGE_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> ORANGE_TERRACOTTA_WALL = MWBlocks.registerWall("orange_terracotta", Blocks.ORANGE_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> ORANGE_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("orange_terracotta", false, MapColor.TERRACOTTA_ORANGE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> ORANGE_TERRACOTTA_BUTTON = MWBlocks.registerButton("orange_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MAGENTA_TERRACOTTA_STAIRS = MWBlocks.registerStair("magenta_terracotta", Blocks.MAGENTA_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> MAGENTA_TERRACOTTA_SLAB = MWBlocks.registerSlab("magenta_terracotta", Blocks.MAGENTA_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> MAGENTA_TERRACOTTA_WALL = MWBlocks.registerWall("magenta_terracotta", Blocks.MAGENTA_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> MAGENTA_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("magenta_terracotta", false, MapColor.TERRACOTTA_MAGENTA, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MAGENTA_TERRACOTTA_BUTTON = MWBlocks.registerButton("magenta_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_BLUE_TERRACOTTA_STAIRS = MWBlocks.registerStair("light_blue_terracotta", Blocks.LIGHT_BLUE_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_BLUE_TERRACOTTA_SLAB = MWBlocks.registerSlab("light_blue_terracotta", Blocks.LIGHT_BLUE_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_BLUE_TERRACOTTA_WALL = MWBlocks.registerWall("light_blue_terracotta", Blocks.LIGHT_BLUE_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_BLUE_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("light_blue_terracotta", false, MapColor.TERRACOTTA_LIGHT_BLUE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_BLUE_TERRACOTTA_BUTTON = MWBlocks.registerButton("light_blue_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> YELLOW_TERRACOTTA_STAIRS = MWBlocks.registerStair("yellow_terracotta", Blocks.YELLOW_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> YELLOW_TERRACOTTA_SLAB = MWBlocks.registerSlab("yellow_terracotta", Blocks.YELLOW_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> YELLOW_TERRACOTTA_WALL = MWBlocks.registerWall("yellow_terracotta", Blocks.YELLOW_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> YELLOW_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("yellow_terracotta", false, MapColor.TERRACOTTA_YELLOW, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> YELLOW_TERRACOTTA_BUTTON = MWBlocks.registerButton("yellow_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIME_TERRACOTTA_STAIRS = MWBlocks.registerStair("lime_terracotta", Blocks.LIME_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> LIME_TERRACOTTA_SLAB = MWBlocks.registerSlab("lime_terracotta", Blocks.LIME_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> LIME_TERRACOTTA_WALL = MWBlocks.registerWall("lime_terracotta", Blocks.LIME_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> LIME_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("lime_terracotta", false, MapColor.TERRACOTTA_LIGHT_GREEN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIME_TERRACOTTA_BUTTON = MWBlocks.registerButton("lime_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PINK_TERRACOTTA_STAIRS = MWBlocks.registerStair("pink_terracotta", Blocks.PINK_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> PINK_TERRACOTTA_SLAB = MWBlocks.registerSlab("pink_terracotta", Blocks.PINK_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> PINK_TERRACOTTA_WALL = MWBlocks.registerWall("pink_terracotta", Blocks.PINK_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> PINK_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("pink_terracotta", false, MapColor.TERRACOTTA_PINK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PINK_TERRACOTTA_BUTTON = MWBlocks.registerButton("pink_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GRAY_TERRACOTTA_STAIRS = MWBlocks.registerStair("gray_terracotta", Blocks.GRAY_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> GRAY_TERRACOTTA_SLAB = MWBlocks.registerSlab("gray_terracotta", Blocks.GRAY_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> GRAY_TERRACOTTA_WALL = MWBlocks.registerWall("gray_terracotta", Blocks.GRAY_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> GRAY_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("gray_terracotta", false, MapColor.TERRACOTTA_GRAY, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GRAY_TERRACOTTA_BUTTON = MWBlocks.registerButton("gray_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_GRAY_TERRACOTTA_STAIRS = MWBlocks.registerStair("light_gray_terracotta", Blocks.LIGHT_GRAY_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_GRAY_TERRACOTTA_SLAB = MWBlocks.registerSlab("light_gray_terracotta", Blocks.LIGHT_GRAY_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_GRAY_TERRACOTTA_WALL = MWBlocks.registerWall("light_gray_terracotta", Blocks.LIGHT_GRAY_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_GRAY_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("light_gray_terracotta", false, MapColor.TERRACOTTA_LIGHT_GRAY, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_GRAY_TERRACOTTA_BUTTON = MWBlocks.registerButton("light_gray_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CYAN_TERRACOTTA_STAIRS = MWBlocks.registerStair("cyan_terracotta", Blocks.CYAN_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> CYAN_TERRACOTTA_SLAB = MWBlocks.registerSlab("cyan_terracotta", Blocks.CYAN_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> CYAN_TERRACOTTA_WALL = MWBlocks.registerWall("cyan_terracotta", Blocks.CYAN_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> CYAN_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("cyan_terracotta", false, MapColor.TERRACOTTA_CYAN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CYAN_TERRACOTTA_BUTTON = MWBlocks.registerButton("cyan_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPLE_TERRACOTTA_STAIRS = MWBlocks.registerStair("purple_terracotta", Blocks.PURPLE_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> PURPLE_TERRACOTTA_SLAB = MWBlocks.registerSlab("purple_terracotta", Blocks.PURPLE_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> PURPLE_TERRACOTTA_WALL = MWBlocks.registerWall("purple_terracotta", Blocks.PURPLE_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> PURPLE_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("purple_terracotta", false, MapColor.TERRACOTTA_PURPLE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPLE_TERRACOTTA_BUTTON = MWBlocks.registerButton("purple_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLUE_TERRACOTTA_STAIRS = MWBlocks.registerStair("blue_terracotta", Blocks.BLUE_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> BLUE_TERRACOTTA_SLAB = MWBlocks.registerSlab("blue_terracotta", Blocks.BLUE_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> BLUE_TERRACOTTA_WALL = MWBlocks.registerWall("blue_terracotta", Blocks.BLUE_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> BLUE_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("blue_terracotta", false, MapColor.TERRACOTTA_BLUE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLUE_TERRACOTTA_BUTTON = MWBlocks.registerButton("blue_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BROWN_TERRACOTTA_STAIRS = MWBlocks.registerStair("brown_terracotta", Blocks.BROWN_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> BROWN_TERRACOTTA_SLAB = MWBlocks.registerSlab("brown_terracotta", Blocks.BROWN_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> BROWN_TERRACOTTA_WALL = MWBlocks.registerWall("brown_terracotta", Blocks.BROWN_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> BROWN_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("brown_terracotta", false, MapColor.TERRACOTTA_BROWN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BROWN_TERRACOTTA_BUTTON = MWBlocks.registerButton("brown_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GREEN_TERRACOTTA_STAIRS = MWBlocks.registerStair("green_terracotta", Blocks.GREEN_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> GREEN_TERRACOTTA_SLAB = MWBlocks.registerSlab("green_terracotta", Blocks.GREEN_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> GREEN_TERRACOTTA_WALL = MWBlocks.registerWall("green_terracotta", Blocks.GREEN_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> GREEN_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("green_terracotta", false, MapColor.TERRACOTTA_GREEN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GREEN_TERRACOTTA_BUTTON = MWBlocks.registerButton("green_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> RED_TERRACOTTA_STAIRS = MWBlocks.registerStair("red_terracotta", Blocks.RED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> RED_TERRACOTTA_SLAB = MWBlocks.registerSlab("red_terracotta", Blocks.RED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> RED_TERRACOTTA_WALL = MWBlocks.registerWall("red_terracotta", Blocks.RED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> RED_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("red_terracotta", false, MapColor.TERRACOTTA_RED, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> RED_TERRACOTTA_BUTTON = MWBlocks.registerButton("red_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLACK_TERRACOTTA_STAIRS = MWBlocks.registerStair("black_terracotta", Blocks.BLACK_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> BLACK_TERRACOTTA_SLAB = MWBlocks.registerSlab("black_terracotta", Blocks.BLACK_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> BLACK_TERRACOTTA_WALL = MWBlocks.registerWall("black_terracotta", Blocks.BLACK_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> BLACK_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("black_terracotta", false, MapColor.TERRACOTTA_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLACK_TERRACOTTA_BUTTON = MWBlocks.registerButton("black_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Glazed Terracotta

    public static final RegistryObject<Block> WHITE_GLAZED_TERRACOTTA_STAIRS = MWBlocks.registerStair("white_glazed_terracotta", Blocks.WHITE_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> WHITE_GLAZED_TERRACOTTA_SLAB = MWBlocks.registerSlab("white_glazed_terracotta", Blocks.WHITE_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> WHITE_GLAZED_TERRACOTTA_WALL = MWBlocks.registerWall("white_glazed_terracotta", Blocks.WHITE_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> WHITE_GLAZED_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("white_glazed_terracotta", false, MapColor.SNOW, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> WHITE_GLAZED_TERRACOTTA_BUTTON = MWBlocks.registerButton("white_glazed_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> ORANGE_GLAZED_TERRACOTTA_STAIRS = MWBlocks.registerStair("orange_glazed_terracotta", Blocks.ORANGE_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> ORANGE_GLAZED_TERRACOTTA_SLAB = MWBlocks.registerSlab("orange_glazed_terracotta", Blocks.ORANGE_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> ORANGE_GLAZED_TERRACOTTA_WALL = MWBlocks.registerWall("orange_glazed_terracotta", Blocks.ORANGE_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> ORANGE_GLAZED_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("orange_glazed_terracotta", false, MapColor.COLOR_ORANGE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> ORANGE_GLAZED_TERRACOTTA_BUTTON = MWBlocks.registerButton("orange_glazed_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MAGENTA_GLAZED_TERRACOTTA_STAIRS = MWBlocks.registerStair("magenta_glazed_terracotta", Blocks.MAGENTA_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> MAGENTA_GLAZED_TERRACOTTA_SLAB = MWBlocks.registerSlab("magenta_glazed_terracotta", Blocks.MAGENTA_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> MAGENTA_GLAZED_TERRACOTTA_WALL = MWBlocks.registerWall("magenta_glazed_terracotta", Blocks.MAGENTA_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> MAGENTA_GLAZED_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("magenta_glazed_terracotta", false, MapColor.COLOR_MAGENTA, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MAGENTA_GLAZED_TERRACOTTA_BUTTON = MWBlocks.registerButton("magenta_glazed_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_BLUE_GLAZED_TERRACOTTA_STAIRS = MWBlocks.registerStair("light_blue_glazed_terracotta", Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_BLUE_GLAZED_TERRACOTTA_SLAB = MWBlocks.registerSlab("light_blue_glazed_terracotta", Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_BLUE_GLAZED_TERRACOTTA_WALL = MWBlocks.registerWall("light_blue_glazed_terracotta", Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_BLUE_GLAZED_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("light_blue_glazed_terracotta", false, MapColor.COLOR_LIGHT_BLUE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_BLUE_GLAZED_TERRACOTTA_BUTTON = MWBlocks.registerButton("light_blue_glazed_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> YELLOW_GLAZED_TERRACOTTA_STAIRS = MWBlocks.registerStair("yellow_glazed_terracotta", Blocks.YELLOW_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> YELLOW_GLAZED_TERRACOTTA_SLAB = MWBlocks.registerSlab("yellow_glazed_terracotta", Blocks.YELLOW_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> YELLOW_GLAZED_TERRACOTTA_WALL = MWBlocks.registerWall("yellow_glazed_terracotta", Blocks.YELLOW_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> YELLOW_GLAZED_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("yellow_glazed_terracotta", false, MapColor.COLOR_YELLOW, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> YELLOW_GLAZED_TERRACOTTA_BUTTON = MWBlocks.registerButton("yellow_glazed_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIME_GLAZED_TERRACOTTA_STAIRS = MWBlocks.registerStair("lime_glazed_terracotta", Blocks.LIME_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> LIME_GLAZED_TERRACOTTA_SLAB = MWBlocks.registerSlab("lime_glazed_terracotta", Blocks.LIME_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> LIME_GLAZED_TERRACOTTA_WALL = MWBlocks.registerWall("lime_glazed_terracotta", Blocks.LIME_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> LIME_GLAZED_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("lime_glazed_terracotta", false, MapColor.COLOR_LIGHT_GREEN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIME_GLAZED_TERRACOTTA_BUTTON = MWBlocks.registerButton("lime_glazed_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PINK_GLAZED_TERRACOTTA_STAIRS = MWBlocks.registerStair("pink_glazed_terracotta", Blocks.PINK_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> PINK_GLAZED_TERRACOTTA_SLAB = MWBlocks.registerSlab("pink_glazed_terracotta", Blocks.PINK_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> PINK_GLAZED_TERRACOTTA_WALL = MWBlocks.registerWall("pink_glazed_terracotta", Blocks.PINK_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> PINK_GLAZED_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("pink_glazed_terracotta", false, MapColor.COLOR_PINK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PINK_GLAZED_TERRACOTTA_BUTTON = MWBlocks.registerButton("pink_glazed_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GRAY_GLAZED_TERRACOTTA_STAIRS = MWBlocks.registerStair("gray_glazed_terracotta", Blocks.GRAY_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> GRAY_GLAZED_TERRACOTTA_SLAB = MWBlocks.registerSlab("gray_glazed_terracotta", Blocks.GRAY_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> GRAY_GLAZED_TERRACOTTA_WALL = MWBlocks.registerWall("gray_glazed_terracotta", Blocks.GRAY_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> GRAY_GLAZED_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("gray_glazed_terracotta", false, MapColor.COLOR_GRAY, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GRAY_GLAZED_TERRACOTTA_BUTTON = MWBlocks.registerButton("gray_glazed_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_GRAY_GLAZED_TERRACOTTA_STAIRS = MWBlocks.registerStair("light_gray_glazed_terracotta", Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_GRAY_GLAZED_TERRACOTTA_SLAB = MWBlocks.registerSlab("light_gray_glazed_terracotta", Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_GRAY_GLAZED_TERRACOTTA_WALL = MWBlocks.registerWall("light_gray_glazed_terracotta", Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_GRAY_GLAZED_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("light_gray_glazed_terracotta", false, MapColor.COLOR_LIGHT_GRAY, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_GRAY_GLAZED_TERRACOTTA_BUTTON = MWBlocks.registerButton("light_gray_glazed_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CYAN_GLAZED_TERRACOTTA_STAIRS = MWBlocks.registerStair("cyan_glazed_terracotta", Blocks.CYAN_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> CYAN_GLAZED_TERRACOTTA_SLAB = MWBlocks.registerSlab("cyan_glazed_terracotta", Blocks.CYAN_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> CYAN_GLAZED_TERRACOTTA_WALL = MWBlocks.registerWall("cyan_glazed_terracotta", Blocks.CYAN_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> CYAN_GLAZED_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("cyan_glazed_terracotta", false, MapColor.COLOR_CYAN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CYAN_GLAZED_TERRACOTTA_BUTTON = MWBlocks.registerButton("cyan_glazed_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPLE_GLAZED_TERRACOTTA_STAIRS = MWBlocks.registerStair("purple_glazed_terracotta", Blocks.PURPLE_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> PURPLE_GLAZED_TERRACOTTA_SLAB = MWBlocks.registerSlab("purple_glazed_terracotta", Blocks.PURPLE_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> PURPLE_GLAZED_TERRACOTTA_WALL = MWBlocks.registerWall("purple_glazed_terracotta", Blocks.PURPLE_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> PURPLE_GLAZED_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("purple_glazed_terracotta", false, MapColor.COLOR_PURPLE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPLE_GLAZED_TERRACOTTA_BUTTON = MWBlocks.registerButton("purple_glazed_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLUE_GLAZED_TERRACOTTA_STAIRS = MWBlocks.registerStair("blue_glazed_terracotta", Blocks.BLUE_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> BLUE_GLAZED_TERRACOTTA_SLAB = MWBlocks.registerSlab("blue_glazed_terracotta", Blocks.BLUE_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> BLUE_GLAZED_TERRACOTTA_WALL = MWBlocks.registerWall("blue_glazed_terracotta", Blocks.BLUE_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> BLUE_GLAZED_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("blue_glazed_terracotta", false, MapColor.COLOR_BLUE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLUE_GLAZED_TERRACOTTA_BUTTON = MWBlocks.registerButton("blue_glazed_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BROWN_GLAZED_TERRACOTTA_STAIRS = MWBlocks.registerStair("brown_glazed_terracotta", Blocks.BROWN_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> BROWN_GLAZED_TERRACOTTA_SLAB = MWBlocks.registerSlab("brown_glazed_terracotta", Blocks.BROWN_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> BROWN_GLAZED_TERRACOTTA_WALL = MWBlocks.registerWall("brown_glazed_terracotta", Blocks.BROWN_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> BROWN_GLAZED_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("brown_glazed_terracotta", false, MapColor.COLOR_BROWN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BROWN_GLAZED_TERRACOTTA_BUTTON = MWBlocks.registerButton("brown_glazed_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GREEN_GLAZED_TERRACOTTA_STAIRS = MWBlocks.registerStair("green_glazed_terracotta", Blocks.GREEN_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> GREEN_GLAZED_TERRACOTTA_SLAB = MWBlocks.registerSlab("green_glazed_terracotta", Blocks.GREEN_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> GREEN_GLAZED_TERRACOTTA_WALL = MWBlocks.registerWall("green_glazed_terracotta", Blocks.GREEN_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> GREEN_GLAZED_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("green_glazed_terracotta", false, MapColor.COLOR_GREEN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GREEN_GLAZED_TERRACOTTA_BUTTON = MWBlocks.registerButton("green_glazed_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> RED_GLAZED_TERRACOTTA_STAIRS = MWBlocks.registerStair("red_glazed_terracotta", Blocks.RED_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> RED_GLAZED_TERRACOTTA_SLAB = MWBlocks.registerSlab("red_glazed_terracotta", Blocks.RED_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> RED_GLAZED_TERRACOTTA_WALL = MWBlocks.registerWall("red_glazed_terracotta", Blocks.RED_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> RED_GLAZED_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("red_glazed_terracotta", false, MapColor.COLOR_RED, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> RED_GLAZED_TERRACOTTA_BUTTON = MWBlocks.registerButton("red_glazed_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLACK_GLAZED_TERRACOTTA_STAIRS = MWBlocks.registerStair("black_glazed_terracotta", Blocks.BLACK_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> BLACK_GLAZED_TERRACOTTA_SLAB = MWBlocks.registerSlab("black_glazed_terracotta", Blocks.BLACK_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> BLACK_GLAZED_TERRACOTTA_WALL = MWBlocks.registerWall("black_glazed_terracotta", Blocks.BLACK_GLAZED_TERRACOTTA::defaultBlockState);
    public static final RegistryObject<Block> BLACK_GLAZED_TERRACOTTA_PRESSURE_PLATE = MWBlocks.registerPressurePlate("black_glazed_terracotta", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLACK_GLAZED_TERRACOTTA_BUTTON = MWBlocks.registerButton("black_glazed_terracotta", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Concrete

    public static final RegistryObject<Block> WHITE_CONCRETE_STAIRS = MWBlocks.registerStair("white_concrete", Blocks.WHITE_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> WHITE_CONCRETE_SLAB = MWBlocks.registerSlab("white_concrete", Blocks.WHITE_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> WHITE_CONCRETE_WALL = MWBlocks.registerWall("white_concrete", Blocks.WHITE_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> WHITE_CONCRETE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("white_concrete", false, MapColor.SNOW, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> WHITE_CONCRETE_BUTTON = MWBlocks.registerButton("white_concrete", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> ORANGE_CONCRETE_STAIRS = MWBlocks.registerStair("orange_concrete", Blocks.ORANGE_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> ORANGE_CONCRETE_SLAB = MWBlocks.registerSlab("orange_concrete", Blocks.ORANGE_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> ORANGE_CONCRETE_WALL = MWBlocks.registerWall("orange_concrete", Blocks.ORANGE_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> ORANGE_CONCRETE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("orange_concrete", false, MapColor.COLOR_ORANGE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> ORANGE_CONCRETE_BUTTON = MWBlocks.registerButton("orange_concrete", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MAGENTA_CONCRETE_STAIRS = MWBlocks.registerStair("magenta_concrete", Blocks.MAGENTA_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> MAGENTA_CONCRETE_SLAB = MWBlocks.registerSlab("magenta_concrete", Blocks.MAGENTA_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> MAGENTA_CONCRETE_WALL = MWBlocks.registerWall("magenta_concrete", Blocks.MAGENTA_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> MAGENTA_CONCRETE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("magenta_concrete", false, MapColor.COLOR_MAGENTA, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> MAGENTA_CONCRETE_BUTTON = MWBlocks.registerButton("magenta_concrete", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_STAIRS = MWBlocks.registerStair("light_blue_concrete", Blocks.LIGHT_BLUE_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_SLAB = MWBlocks.registerSlab("light_blue_concrete", Blocks.LIGHT_BLUE_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_WALL = MWBlocks.registerWall("light_blue_concrete", Blocks.LIGHT_BLUE_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("light_blue_concrete", false, MapColor.COLOR_LIGHT_BLUE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_BUTTON = MWBlocks.registerButton("light_blue_concrete", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> YELLOW_CONCRETE_STAIRS = MWBlocks.registerStair("yellow_concrete", Blocks.YELLOW_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> YELLOW_CONCRETE_SLAB = MWBlocks.registerSlab("yellow_concrete", Blocks.YELLOW_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> YELLOW_CONCRETE_WALL = MWBlocks.registerWall("yellow_concrete", Blocks.YELLOW_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> YELLOW_CONCRETE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("yellow_concrete", false, MapColor.COLOR_YELLOW, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> YELLOW_CONCRETE_BUTTON = MWBlocks.registerButton("yellow_concrete", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIME_CONCRETE_STAIRS = MWBlocks.registerStair("lime_concrete", Blocks.LIME_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> LIME_CONCRETE_SLAB = MWBlocks.registerSlab("lime_concrete", Blocks.LIME_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> LIME_CONCRETE_WALL = MWBlocks.registerWall("lime_concrete", Blocks.LIME_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> LIME_CONCRETE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("lime_concrete", false, MapColor.COLOR_LIGHT_GREEN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIME_CONCRETE_BUTTON = MWBlocks.registerButton("lime_concrete", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PINK_CONCRETE_STAIRS = MWBlocks.registerStair("pink_concrete", Blocks.PINK_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> PINK_CONCRETE_SLAB = MWBlocks.registerSlab("pink_concrete", Blocks.PINK_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> PINK_CONCRETE_WALL = MWBlocks.registerWall("pink_concrete", Blocks.PINK_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> PINK_CONCRETE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("pink_concrete", false, MapColor.COLOR_PINK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PINK_CONCRETE_BUTTON = MWBlocks.registerButton("pink_concrete", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GRAY_CONCRETE_STAIRS = MWBlocks.registerStair("gray_concrete", Blocks.GRAY_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> GRAY_CONCRETE_SLAB = MWBlocks.registerSlab("gray_concrete", Blocks.GRAY_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> GRAY_CONCRETE_WALL = MWBlocks.registerWall("gray_concrete", Blocks.GRAY_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> GRAY_CONCRETE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("gray_concrete", false, MapColor.COLOR_GRAY, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GRAY_CONCRETE_BUTTON = MWBlocks.registerButton("gray_concrete", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_STAIRS = MWBlocks.registerStair("light_gray_concrete", Blocks.LIGHT_GRAY_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_SLAB = MWBlocks.registerSlab("light_gray_concrete", Blocks.LIGHT_GRAY_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_WALL = MWBlocks.registerWall("light_gray_concrete", Blocks.LIGHT_GRAY_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("light_gray_concrete", false, MapColor.COLOR_LIGHT_GRAY, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_BUTTON = MWBlocks.registerButton("light_gray_concrete", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CYAN_CONCRETE_STAIRS = MWBlocks.registerStair("cyan_concrete", Blocks.CYAN_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> CYAN_CONCRETE_SLAB = MWBlocks.registerSlab("cyan_concrete", Blocks.CYAN_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> CYAN_CONCRETE_WALL = MWBlocks.registerWall("cyan_concrete", Blocks.CYAN_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> CYAN_CONCRETE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("cyan_concrete", false, MapColor.COLOR_CYAN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> CYAN_CONCRETE_BUTTON = MWBlocks.registerButton("cyan_concrete", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPLE_CONCRETE_STAIRS = MWBlocks.registerStair("purple_concrete", Blocks.PURPLE_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> PURPLE_CONCRETE_SLAB = MWBlocks.registerSlab("purple_concrete", Blocks.PURPLE_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> PURPLE_CONCRETE_WALL = MWBlocks.registerWall("purple_concrete", Blocks.PURPLE_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> PURPLE_CONCRETE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("purple_concrete", false, MapColor.COLOR_PURPLE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> PURPLE_CONCRETE_BUTTON = MWBlocks.registerButton("purple_concrete", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLUE_CONCRETE_STAIRS = MWBlocks.registerStair("blue_concrete", Blocks.BLUE_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> BLUE_CONCRETE_SLAB = MWBlocks.registerSlab("blue_concrete", Blocks.BLUE_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> BLUE_CONCRETE_WALL = MWBlocks.registerWall("blue_concrete", Blocks.BLUE_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> BLUE_CONCRETE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("blue_concrete", false, MapColor.COLOR_BLUE, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLUE_CONCRETE_BUTTON = MWBlocks.registerButton("blue_concrete", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BROWN_CONCRETE_STAIRS = MWBlocks.registerStair("brown_concrete", Blocks.BROWN_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> BROWN_CONCRETE_SLAB = MWBlocks.registerSlab("brown_concrete", Blocks.BROWN_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> BROWN_CONCRETE_WALL = MWBlocks.registerWall("brown_concrete", Blocks.BROWN_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> BROWN_CONCRETE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("brown_concrete", false, MapColor.COLOR_BROWN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BROWN_CONCRETE_BUTTON = MWBlocks.registerButton("brown_concrete", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GREEN_CONCRETE_STAIRS = MWBlocks.registerStair("green_concrete", Blocks.GREEN_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> GREEN_CONCRETE_SLAB = MWBlocks.registerSlab("green_concrete", Blocks.GREEN_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> GREEN_CONCRETE_WALL = MWBlocks.registerWall("green_concrete", Blocks.GREEN_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> GREEN_CONCRETE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("green_concrete", false, MapColor.COLOR_GREEN, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> GREEN_CONCRETE_BUTTON = MWBlocks.registerButton("green_concrete", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> RED_CONCRETE_STAIRS = MWBlocks.registerStair("red_concrete", Blocks.RED_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> RED_CONCRETE_SLAB = MWBlocks.registerSlab("red_concrete", Blocks.RED_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> RED_CONCRETE_WALL = MWBlocks.registerWall("red_concrete", Blocks.RED_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> RED_CONCRETE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("red_concrete", false, MapColor.COLOR_RED, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> RED_CONCRETE_BUTTON = MWBlocks.registerButton("red_concrete", false, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLACK_CONCRETE_STAIRS = MWBlocks.registerStair("black_concrete", Blocks.BLACK_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> BLACK_CONCRETE_SLAB = MWBlocks.registerSlab("black_concrete", Blocks.BLACK_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> BLACK_CONCRETE_WALL = MWBlocks.registerWall("black_concrete", Blocks.BLACK_CONCRETE::defaultBlockState);
    public static final RegistryObject<Block> BLACK_CONCRETE_PRESSURE_PLATE = MWBlocks.registerPressurePlate("black_concrete", false, MapColor.COLOR_BLACK, Suppliers.memoize(() -> BlockSetType.STONE));
    public static final RegistryObject<Block> BLACK_CONCRETE_BUTTON = MWBlocks.registerButton("black_concrete", false, Suppliers.memoize(() -> BlockSetType.STONE));

    //#endregion

    //#region Stained Glass

    public static final RegistryObject<Block> WHITE_STAINED_GLASS_STAIRS = MWBlocks.registerStair("white_stained_glass", Blocks.WHITE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> WHITE_STAINED_GLASS_SLAB = MWBlocks.registerSlab("white_stained_glass", Blocks.WHITE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> WHITE_STAINED_GLASS_WALL = MWBlocks.registerGlassWall("white_stained_glass", Blocks.WHITE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> ORANGE_STAINED_GLASS_STAIRS = MWBlocks.registerStair("orange_stained_glass", Blocks.ORANGE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> ORANGE_STAINED_GLASS_SLAB = MWBlocks.registerSlab("orange_stained_glass", Blocks.ORANGE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> ORANGE_STAINED_GLASS_WALL = MWBlocks.registerGlassWall("orange_stained_glass", Blocks.ORANGE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> MAGENTA_STAINED_GLASS_STAIRS = MWBlocks.registerStair("magenta_stained_glass", Blocks.MAGENTA_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> MAGENTA_STAINED_GLASS_SLAB = MWBlocks.registerSlab("magenta_stained_glass", Blocks.MAGENTA_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> MAGENTA_STAINED_GLASS_WALL = MWBlocks.registerGlassWall("magenta_stained_glass", Blocks.MAGENTA_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_BLUE_STAINED_GLASS_STAIRS = MWBlocks.registerStair("light_blue_stained_glass", Blocks.LIGHT_BLUE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_BLUE_STAINED_GLASS_SLAB = MWBlocks.registerSlab("light_blue_stained_glass", Blocks.LIGHT_BLUE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_BLUE_STAINED_GLASS_WALL = MWBlocks.registerGlassWall("light_blue_stained_glass", Blocks.LIGHT_BLUE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> YELLOW_STAINED_GLASS_STAIRS = MWBlocks.registerStair("yellow_stained_glass", Blocks.YELLOW_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> YELLOW_STAINED_GLASS_SLAB = MWBlocks.registerSlab("yellow_stained_glass", Blocks.YELLOW_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> YELLOW_STAINED_GLASS_WALL = MWBlocks.registerGlassWall("yellow_stained_glass", Blocks.YELLOW_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> LIME_STAINED_GLASS_STAIRS = MWBlocks.registerStair("lime_stained_glass", Blocks.LIME_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> LIME_STAINED_GLASS_SLAB = MWBlocks.registerSlab("lime_stained_glass", Blocks.LIME_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> LIME_STAINED_GLASS_WALL = MWBlocks.registerGlassWall("lime_stained_glass", Blocks.LIME_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> PINK_STAINED_GLASS_STAIRS = MWBlocks.registerStair("pink_stained_glass", Blocks.PINK_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> PINK_STAINED_GLASS_SLAB = MWBlocks.registerSlab("pink_stained_glass", Blocks.PINK_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> PINK_STAINED_GLASS_WALL = MWBlocks.registerGlassWall("pink_stained_glass", Blocks.PINK_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> GRAY_STAINED_GLASS_STAIRS = MWBlocks.registerStair("gray_stained_glass", Blocks.GRAY_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> GRAY_STAINED_GLASS_SLAB = MWBlocks.registerSlab("gray_stained_glass", Blocks.GRAY_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> GRAY_STAINED_GLASS_WALL = MWBlocks.registerGlassWall("gray_stained_glass", Blocks.GRAY_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_GRAY_STAINED_GLASS_STAIRS = MWBlocks.registerStair("light_gray_stained_glass", Blocks.LIGHT_GRAY_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_GRAY_STAINED_GLASS_SLAB = MWBlocks.registerSlab("light_gray_stained_glass", Blocks.LIGHT_GRAY_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_GRAY_STAINED_GLASS_WALL = MWBlocks.registerGlassWall("light_gray_stained_glass", Blocks.LIGHT_GRAY_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> CYAN_STAINED_GLASS_STAIRS = MWBlocks.registerStair("cyan_stained_glass", Blocks.CYAN_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> CYAN_STAINED_GLASS_SLAB = MWBlocks.registerSlab("cyan_stained_glass", Blocks.CYAN_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> CYAN_STAINED_GLASS_WALL = MWBlocks.registerGlassWall("cyan_stained_glass", Blocks.CYAN_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> PURPLE_STAINED_GLASS_STAIRS = MWBlocks.registerStair("purple_stained_glass", Blocks.PURPLE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> PURPLE_STAINED_GLASS_SLAB = MWBlocks.registerSlab("purple_stained_glass", Blocks.PURPLE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> PURPLE_STAINED_GLASS_WALL = MWBlocks.registerGlassWall("purple_stained_glass", Blocks.PURPLE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> BLUE_STAINED_GLASS_STAIRS = MWBlocks.registerStair("blue_stained_glass", Blocks.BLUE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> BLUE_STAINED_GLASS_SLAB = MWBlocks.registerSlab("blue_stained_glass", Blocks.BLUE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> BLUE_STAINED_GLASS_WALL = MWBlocks.registerGlassWall("blue_stained_glass", Blocks.BLUE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> BROWN_STAINED_GLASS_STAIRS = MWBlocks.registerStair("brown_stained_glass", Blocks.BROWN_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> BROWN_STAINED_GLASS_SLAB = MWBlocks.registerSlab("brown_stained_glass", Blocks.BROWN_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> BROWN_STAINED_GLASS_WALL = MWBlocks.registerGlassWall("brown_stained_glass", Blocks.BROWN_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> GREEN_STAINED_GLASS_STAIRS = MWBlocks.registerStair("green_stained_glass", Blocks.GREEN_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> GREEN_STAINED_GLASS_SLAB = MWBlocks.registerSlab("green_stained_glass", Blocks.GREEN_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> GREEN_STAINED_GLASS_WALL = MWBlocks.registerGlassWall("green_stained_glass", Blocks.GREEN_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> RED_STAINED_GLASS_STAIRS = MWBlocks.registerStair("red_stained_glass", Blocks.RED_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> RED_STAINED_GLASS_SLAB = MWBlocks.registerSlab("red_stained_glass", Blocks.RED_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> RED_STAINED_GLASS_WALL = MWBlocks.registerGlassWall("red_stained_glass", Blocks.RED_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> BLACK_STAINED_GLASS_STAIRS = MWBlocks.registerStair("black_stained_glass", Blocks.BLACK_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> BLACK_STAINED_GLASS_SLAB = MWBlocks.registerSlab("black_stained_glass", Blocks.BLACK_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> BLACK_STAINED_GLASS_WALL = MWBlocks.registerGlassWall("black_stained_glass", Blocks.BLACK_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> WHITE_STAINED_GLASS_HORIZONTAL_PANE = MWBlocks.registerHorizontalPane("white_stained_glass_horizontal_pane", Blocks.WHITE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> ORANGE_STAINED_GLASS_HORIZONTAL_PANE = MWBlocks.registerHorizontalPane("orange_stained_glass_horizontal_pane", Blocks.ORANGE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> MAGENTA_STAINED_GLASS_HORIZONTAL_PANE = MWBlocks.registerHorizontalPane("magenta_stained_glass_horizontal_pane", Blocks.MAGENTA_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_BLUE_STAINED_GLASS_HORIZONTAL_PANE = MWBlocks.registerHorizontalPane("light_blue_stained_glass_horizontal_pane", Blocks.LIGHT_BLUE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> YELLOW_STAINED_GLASS_HORIZONTAL_PANE = MWBlocks.registerHorizontalPane("yellow_stained_glass_horizontal_pane", Blocks.YELLOW_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> LIME_STAINED_GLASS_HORIZONTAL_PANE = MWBlocks.registerHorizontalPane("lime_stained_glass_horizontal_pane", Blocks.LIME_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> PINK_STAINED_GLASS_HORIZONTAL_PANE = MWBlocks.registerHorizontalPane("pink_stained_glass_horizontal_pane", Blocks.PINK_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> GRAY_STAINED_GLASS_HORIZONTAL_PANE = MWBlocks.registerHorizontalPane("gray_stained_glass_horizontal_pane", Blocks.GRAY_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_GRAY_STAINED_GLASS_HORIZONTAL_PANE = MWBlocks.registerHorizontalPane("light_gray_stained_glass_horizontal_pane", Blocks.LIGHT_GRAY_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> CYAN_STAINED_GLASS_HORIZONTAL_PANE = MWBlocks.registerHorizontalPane("cyan_stained_glass_horizontal_pane", Blocks.CYAN_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> PURPLE_STAINED_GLASS_HORIZONTAL_PANE = MWBlocks.registerHorizontalPane("purple_stained_glass_horizontal_pane", Blocks.PURPLE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> BLUE_STAINED_GLASS_HORIZONTAL_PANE = MWBlocks.registerHorizontalPane("blue_stained_glass_horizontal_pane", Blocks.BLUE_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> BROWN_STAINED_GLASS_HORIZONTAL_PANE = MWBlocks.registerHorizontalPane("brown_stained_glass_horizontal_pane", Blocks.BROWN_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> GREEN_STAINED_GLASS_HORIZONTAL_PANE = MWBlocks.registerHorizontalPane("green_stained_glass_horizontal_pane", Blocks.GREEN_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> RED_STAINED_GLASS_HORIZONTAL_PANE = MWBlocks.registerHorizontalPane("red_stained_glass_horizontal_pane", Blocks.RED_STAINED_GLASS::defaultBlockState);
    public static final RegistryObject<Block> BLACK_STAINED_GLASS_HORIZONTAL_PANE = MWBlocks.registerHorizontalPane("black_stained_glass_horizontal_pane", Blocks.BLACK_STAINED_GLASS::defaultBlockState);

    //#endregion

    //#region Wool

    public static final RegistryObject<Block> WHITE_WOOL_STAIRS = MWBlocks.registerStair("white_wool", Blocks.WHITE_WOOL::defaultBlockState);
    public static final RegistryObject<Block> WHITE_WOOL_SLAB = MWBlocks.registerSlab("white_wool", Blocks.WHITE_WOOL::defaultBlockState);
    public static final RegistryObject<Block> WHITE_WOOL_WALL = MWBlocks.registerWall("white_wool", Blocks.WHITE_WOOL::defaultBlockState);
    public static final RegistryObject<Block> ORANGE_WOOL_STAIRS = MWBlocks.registerStair("orange_wool", Blocks.ORANGE_WOOL::defaultBlockState);
    public static final RegistryObject<Block> ORANGE_WOOL_SLAB = MWBlocks.registerSlab("orange_wool", Blocks.ORANGE_WOOL::defaultBlockState);
    public static final RegistryObject<Block> ORANGE_WOOL_WALL = MWBlocks.registerWall("orange_wool", Blocks.ORANGE_WOOL::defaultBlockState);
    public static final RegistryObject<Block> MAGENTA_WOOL_STAIRS = MWBlocks.registerStair("magenta_wool", Blocks.MAGENTA_WOOL::defaultBlockState);
    public static final RegistryObject<Block> MAGENTA_WOOL_SLAB = MWBlocks.registerSlab("magenta_wool", Blocks.MAGENTA_WOOL::defaultBlockState);
    public static final RegistryObject<Block> MAGENTA_WOOL_WALL = MWBlocks.registerWall("magenta_wool", Blocks.MAGENTA_WOOL::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_BLUE_WOOL_STAIRS = MWBlocks.registerStair("light_blue_wool", Blocks.LIGHT_BLUE_WOOL::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_BLUE_WOOL_SLAB = MWBlocks.registerSlab("light_blue_wool", Blocks.LIGHT_BLUE_WOOL::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_BLUE_WOOL_WALL = MWBlocks.registerWall("light_blue_wool", Blocks.LIGHT_BLUE_WOOL::defaultBlockState);
    public static final RegistryObject<Block> YELLOW_WOOL_STAIRS = MWBlocks.registerStair("yellow_wool", Blocks.YELLOW_WOOL::defaultBlockState);
    public static final RegistryObject<Block> YELLOW_WOOL_SLAB = MWBlocks.registerSlab("yellow_wool", Blocks.YELLOW_WOOL::defaultBlockState);
    public static final RegistryObject<Block> YELLOW_WOOL_WALL = MWBlocks.registerWall("yellow_wool", Blocks.YELLOW_WOOL::defaultBlockState);
    public static final RegistryObject<Block> LIME_WOOL_STAIRS = MWBlocks.registerStair("lime_wool", Blocks.LIME_WOOL::defaultBlockState);
    public static final RegistryObject<Block> LIME_WOOL_SLAB = MWBlocks.registerSlab("lime_wool", Blocks.LIME_WOOL::defaultBlockState);
    public static final RegistryObject<Block> LIME_WOOL_WALL = MWBlocks.registerWall("lime_wool", Blocks.LIME_WOOL::defaultBlockState);
    public static final RegistryObject<Block> PINK_WOOL_STAIRS = MWBlocks.registerStair("pink_wool", Blocks.PINK_WOOL::defaultBlockState);
    public static final RegistryObject<Block> PINK_WOOL_SLAB = MWBlocks.registerSlab("pink_wool", Blocks.PINK_WOOL::defaultBlockState);
    public static final RegistryObject<Block> PINK_WOOL_WALL = MWBlocks.registerWall("pink_wool", Blocks.PINK_WOOL::defaultBlockState);
    public static final RegistryObject<Block> GRAY_WOOL_STAIRS = MWBlocks.registerStair("gray_wool", Blocks.GRAY_WOOL::defaultBlockState);
    public static final RegistryObject<Block> GRAY_WOOL_SLAB = MWBlocks.registerSlab("gray_wool", Blocks.GRAY_WOOL::defaultBlockState);
    public static final RegistryObject<Block> GRAY_WOOL_WALL = MWBlocks.registerWall("gray_wool", Blocks.GRAY_WOOL::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_GRAY_WOOL_STAIRS = MWBlocks.registerStair("light_gray_wool", Blocks.LIGHT_GRAY_WOOL::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_GRAY_WOOL_SLAB = MWBlocks.registerSlab("light_gray_wool", Blocks.LIGHT_GRAY_WOOL::defaultBlockState);
    public static final RegistryObject<Block> LIGHT_GRAY_WOOL_WALL = MWBlocks.registerWall("light_gray_wool", Blocks.LIGHT_GRAY_WOOL::defaultBlockState);
    public static final RegistryObject<Block> CYAN_WOOL_STAIRS = MWBlocks.registerStair("cyan_wool", Blocks.CYAN_WOOL::defaultBlockState);
    public static final RegistryObject<Block> CYAN_WOOL_SLAB = MWBlocks.registerSlab("cyan_wool", Blocks.CYAN_WOOL::defaultBlockState);
    public static final RegistryObject<Block> CYAN_WOOL_WALL = MWBlocks.registerWall("cyan_wool", Blocks.CYAN_WOOL::defaultBlockState);
    public static final RegistryObject<Block> PURPLE_WOOL_STAIRS = MWBlocks.registerStair("purple_wool", Blocks.PURPLE_WOOL::defaultBlockState);
    public static final RegistryObject<Block> PURPLE_WOOL_SLAB = MWBlocks.registerSlab("purple_wool", Blocks.PURPLE_WOOL::defaultBlockState);
    public static final RegistryObject<Block> PURPLE_WOOL_WALL = MWBlocks.registerWall("purple_wool", Blocks.PURPLE_WOOL::defaultBlockState);
    public static final RegistryObject<Block> BLUE_WOOL_STAIRS = MWBlocks.registerStair("blue_wool", Blocks.BLUE_WOOL::defaultBlockState);
    public static final RegistryObject<Block> BLUE_WOOL_SLAB = MWBlocks.registerSlab("blue_wool", Blocks.BLUE_WOOL::defaultBlockState);
    public static final RegistryObject<Block> BLUE_WOOL_WALL = MWBlocks.registerWall("blue_wool", Blocks.BLUE_WOOL::defaultBlockState);
    public static final RegistryObject<Block> BROWN_WOOL_STAIRS = MWBlocks.registerStair("brown_wool", Blocks.BROWN_WOOL::defaultBlockState);
    public static final RegistryObject<Block> BROWN_WOOL_SLAB = MWBlocks.registerSlab("brown_wool", Blocks.BROWN_WOOL::defaultBlockState);
    public static final RegistryObject<Block> BROWN_WOOL_WALL = MWBlocks.registerWall("brown_wool", Blocks.BROWN_WOOL::defaultBlockState);
    public static final RegistryObject<Block> GREEN_WOOL_STAIRS = MWBlocks.registerStair("green_wool", Blocks.GREEN_WOOL::defaultBlockState);
    public static final RegistryObject<Block> GREEN_WOOL_SLAB = MWBlocks.registerSlab("green_wool", Blocks.GREEN_WOOL::defaultBlockState);
    public static final RegistryObject<Block> GREEN_WOOL_WALL = MWBlocks.registerWall("green_wool", Blocks.GREEN_WOOL::defaultBlockState);
    public static final RegistryObject<Block> RED_WOOL_STAIRS = MWBlocks.registerStair("red_wool", Blocks.RED_WOOL::defaultBlockState);
    public static final RegistryObject<Block> RED_WOOL_SLAB = MWBlocks.registerSlab("red_wool", Blocks.RED_WOOL::defaultBlockState);
    public static final RegistryObject<Block> RED_WOOL_WALL = MWBlocks.registerWall("red_wool", Blocks.RED_WOOL::defaultBlockState);
    public static final RegistryObject<Block> BLACK_WOOL_STAIRS = MWBlocks.registerStair("black_wool", Blocks.BLACK_WOOL::defaultBlockState);
    public static final RegistryObject<Block> BLACK_WOOL_SLAB = MWBlocks.registerSlab("black_wool", Blocks.BLACK_WOOL::defaultBlockState);
    public static final RegistryObject<Block> BLACK_WOOL_WALL = MWBlocks.registerWall("black_wool", Blocks.BLACK_WOOL::defaultBlockState);

    //#endregion

    //#endregion

    //#region Bus register

    /**
     * Register all {@link Block Colored Blocks}
     */
    public static void register() { }

    //#endregion

}