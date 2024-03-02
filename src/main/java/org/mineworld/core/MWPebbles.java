package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.block.PebbleBlock;
import org.mineworld.item.PebbleItem;

import java.util.function.Supplier;

public final class MWPebbles {

    public static class PebbleBlocks {

        //#region Pebbles

        public static final RegistryObject<Block> STONE_PEBBLE = registerPebble("stone", () -> Blocks.STONE);
        public static final RegistryObject<Block> COBBLESTONE_PEBBLE = registerPebble("cobblestone", () -> Blocks.COBBLESTONE);
        public static final RegistryObject<Block> MOSSY_STONE_PEBBLE = registerPebble("mossy_stone", Suppliers.memoize(() -> MWBlocks.MOSSY_STONE.get()));
        public static final RegistryObject<Block> MOSSY_COBBLESTONE_PEBBLE = registerPebble("mossy_cobblestone", () -> Blocks.MOSSY_COBBLESTONE);
        public static final RegistryObject<Block> SMOOTH_STONE_PEBBLE = registerPebble("smooth_stone", () -> Blocks.SMOOTH_STONE);
        public static final RegistryObject<Block> STONE_BRICKS_PEBBLE = registerPebble("stone_bricks", () -> Blocks.STONE_BRICKS);
        public static final RegistryObject<Block> MOSSY_STONE_BRICKS_PEBBLE = registerPebble("mossy_stone_bricks", () -> Blocks.MOSSY_STONE_BRICKS);
        public static final RegistryObject<Block> GRANITE_PEBBLE = registerPebble("granite", () -> Blocks.GRANITE);
        public static final RegistryObject<Block> POLISHED_GRANITE_PEBBLE = registerPebble("polished_granite", () -> Blocks.POLISHED_GRANITE);
        public static final RegistryObject<Block> DIORITE_PEBBLE = registerPebble("diorite", () -> Blocks.DIORITE);
        public static final RegistryObject<Block> POLISHED_DIORITE_PEBBLE = registerPebble("polished_diorite", () -> Blocks.POLISHED_DIORITE);
        public static final RegistryObject<Block> ANDESITE_PEBBLE = registerPebble("andesite", () -> Blocks.ANDESITE);
        public static final RegistryObject<Block> POLISHED_ANDESITE_PEBBLE = registerPebble("polished_andesite", () -> Blocks.POLISHED_ANDESITE);
        public static final RegistryObject<Block> DEEPSLATE_PEBBLE = registerPebble("deepslate", () -> Blocks.DEEPSLATE);
        public static final RegistryObject<Block> COBBLED_DEEPSLATE_PEBBLE = registerPebble("cobbled_deepslate", () -> Blocks.COBBLED_DEEPSLATE);
        public static final RegistryObject<Block> POLISHED_DEEPSLATE_PEBBLE = registerPebble("polished_deepslate", () -> Blocks.POLISHED_DEEPSLATE);
        public static final RegistryObject<Block> DEEPSLATE_BRICKS_PEBBLE = registerPebble("deepslate_bricks", () -> Blocks.DEEPSLATE_BRICKS);
        public static final RegistryObject<Block> DEEPSLATE_TILES_PEBBLE = registerPebble("deepslate_tiles", () -> Blocks.DEEPSLATE_TILES);
        public static final RegistryObject<Block> REINFORCED_DEEPSLATE_PEBBLE = registerPebble("reinforced_deepslate", () -> Blocks.REINFORCED_DEEPSLATE);
        public static final RegistryObject<Block> BRICKS_PEBBLE = registerPebble("bricks", () -> Blocks.BRICKS);
        public static final RegistryObject<Block> MUD_BRICKS_PEBBLE = registerPebble("mud_bricks", () -> Blocks.MUD_BRICKS);
        public static final RegistryObject<Block> SANDSTONE_PEBBLE = registerPebble("sandstone", () -> Blocks.SANDSTONE);
        public static final RegistryObject<Block> SMOOTH_SANDSTONE_PEBBLE = registerPebble("smooth_sandstone", () -> Blocks.SMOOTH_SANDSTONE);
        public static final RegistryObject<Block> RED_SANDSTONE_PEBBLE = registerPebble("red_sandstone", () -> Blocks.RED_SANDSTONE);
        public static final RegistryObject<Block> SMOOTH_RED_SANDSTONE_PEBBLE = registerPebble("smooth_red_sandstone", () -> Blocks.SMOOTH_RED_SANDSTONE);
        public static final RegistryObject<Block> PRISMARINE_PEBBLE = registerPebble("prismarine", () -> Blocks.PRISMARINE);
        public static final RegistryObject<Block> PRISMARINE_BRICKS_PEBBLE = registerPebble("prismarine_bricks", () -> Blocks.PRISMARINE_BRICKS);
        public static final RegistryObject<Block> DARK_PRISMARINE_PEBBLE = registerPebble("dark_prismarine", () -> Blocks.DARK_PRISMARINE);
        public static final RegistryObject<Block> NETHERRACK_PEBBLE = registerPebble("netherrack", () -> Blocks.NETHERRACK);
        public static final RegistryObject<Block> NETHER_BRICKS_PEBBLE = registerPebble("nether_bricks", () -> Blocks.NETHER_BRICKS);
        public static final RegistryObject<Block> RED_NETHER_BRICKS_PEBBLE = registerPebble("red_nether_bricks", () -> Blocks.RED_NETHER_BRICKS);
        public static final RegistryObject<Block> BASALT_PEBBLE = registerPebble("basalt", () -> Blocks.BASALT);
        public static final RegistryObject<Block> SMOOTH_BASALT_PEBBLE = registerPebble("smooth_basalt", () -> Blocks.SMOOTH_BASALT);
        public static final RegistryObject<Block> POLISHED_BASALT_PEBBLE = registerPebble("polished_basalt", () -> Blocks.POLISHED_BASALT);
        public static final RegistryObject<Block> BLACKSTONE_PEBBLE = registerPebble("blackstone", () -> Blocks.BLACKSTONE);
        public static final RegistryObject<Block> POLISHED_BLACKSTONE_PEBBLE = registerPebble("polished_blackstone", () -> Blocks.POLISHED_BLACKSTONE);
        public static final RegistryObject<Block> POLISHED_BLACKSTONE_BRICKS_PEBBLE = registerPebble("polished_blackstone_bricks", () -> Blocks.POLISHED_BLACKSTONE_BRICKS);
        public static final RegistryObject<Block> GILDED_BLACKSTONE_PEBBLE = registerPebble("gilded_blackstone", () -> Blocks.GILDED_BLACKSTONE);
        public static final RegistryObject<Block> END_STONE_PEBBLE = registerPebble("end_stone", () -> Blocks.END_STONE);
        public static final RegistryObject<Block> END_STONE_BRICKS_PEBBLE = registerPebble("end_stone_bricks", () -> Blocks.END_STONE_BRICKS);
        public static final RegistryObject<Block> PURPUR_PEBBLE = registerPebble("purpur", () -> Blocks.PURPUR_BLOCK);
        public static final RegistryObject<Block> PURPUR_PILLAR_PEBBLE = registerPebble("purpur_pillar", () -> Blocks.PURPUR_PILLAR);
        public static final RegistryObject<Block> QUARTZ_PEBBLE = registerPebble("quartz", () -> Blocks.QUARTZ_BLOCK);
        public static final RegistryObject<Block> SMOOTH_QUARTZ_PEBBLE = registerPebble("smooth_quartz", () -> Blocks.SMOOTH_QUARTZ);
        public static final RegistryObject<Block> QUARTZ_BRICKS_PEBBLE = registerPebble("quartz_bricks", () -> Blocks.QUARTZ_BRICKS);
        public static final RegistryObject<Block> QUARTZ_PILLAR_PEBBLE = registerPebble("quartz_pillar", () -> Blocks.QUARTZ_PILLAR);
        public static final RegistryObject<Block> TERRACOTTA_PEBBLE = registerPebble("terracotta", () -> Blocks.TERRACOTTA);
        public static final RegistryObject<Block> WHITE_TERRACOTTA_PEBBLE = registerPebble("white_terracotta", () -> Blocks.WHITE_TERRACOTTA);
        public static final RegistryObject<Block> ORANGE_TERRACOTTA_PEBBLE = registerPebble("orange_terracotta", () -> Blocks.ORANGE_TERRACOTTA);
        public static final RegistryObject<Block> MAGENTA_TERRACOTTA_PEBBLE = registerPebble("magenta_terracotta", () -> Blocks.MAGENTA_TERRACOTTA);
        public static final RegistryObject<Block> LIGHT_BLUE_TERRACOTTA_PEBBLE = registerPebble("light_blue_terracotta", () -> Blocks.LIGHT_BLUE_TERRACOTTA);
        public static final RegistryObject<Block> YELLOW_TERRACOTTA_PEBBLE = registerPebble("yellow_terracotta", () -> Blocks.YELLOW_TERRACOTTA);
        public static final RegistryObject<Block> LIME_TERRACOTTA_PEBBLE = registerPebble("lime_terracotta", () -> Blocks.LIME_TERRACOTTA);
        public static final RegistryObject<Block> PINK_TERRACOTTA_PEBBLE = registerPebble("pink_terracotta", () -> Blocks.PINK_TERRACOTTA);
        public static final RegistryObject<Block> GRAY_TERRACOTTA_PEBBLE = registerPebble("gray_terracotta", () -> Blocks.GRAY_TERRACOTTA);
        public static final RegistryObject<Block> LIGHT_GRAY_TERRACOTTA_PEBBLE = registerPebble("light_gray_terracotta", () -> Blocks.LIGHT_GRAY_TERRACOTTA);
        public static final RegistryObject<Block> CYAN_TERRACOTTA_PEBBLE = registerPebble("cyan_terracotta", () -> Blocks.CYAN_TERRACOTTA);
        public static final RegistryObject<Block> PURPLE_TERRACOTTA_PEBBLE = registerPebble("purple_terracotta", () -> Blocks.PURPLE_TERRACOTTA);
        public static final RegistryObject<Block> BLUE_TERRACOTTA_PEBBLE = registerPebble("blue_terracotta", () -> Blocks.BLUE_TERRACOTTA);
        public static final RegistryObject<Block> BROWN_TERRACOTTA_PEBBLE = registerPebble("brown_terracotta", () -> Blocks.BROWN_TERRACOTTA);
        public static final RegistryObject<Block> GREEN_TERRACOTTA_PEBBLE = registerPebble("green_terracotta", () -> Blocks.GREEN_TERRACOTTA);
        public static final RegistryObject<Block> RED_TERRACOTTA_PEBBLE = registerPebble("red_terracotta", () -> Blocks.RED_TERRACOTTA);
        public static final RegistryObject<Block> BLACK_TERRACOTTA_PEBBLE = registerPebble("black_terracotta", () -> Blocks.BLACK_TERRACOTTA);
        public static final RegistryObject<Block> WHITE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("white_glazed_terracotta", () -> Blocks.WHITE_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> ORANGE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("orange_glazed_terracotta", () -> Blocks.ORANGE_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> MAGENTA_GLAZED_TERRACOTTA_PEBBLE = registerPebble("magenta_glazed_terracotta", () -> Blocks.MAGENTA_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> LIGHT_BLUE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("light_blue_glazed_terracotta", () -> Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> YELLOW_GLAZED_TERRACOTTA_PEBBLE = registerPebble("yellow_glazed_terracotta", () -> Blocks.YELLOW_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> LIME_GLAZED_TERRACOTTA_PEBBLE = registerPebble("lime_glazed_terracotta", () -> Blocks.LIME_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> PINK_GLAZED_TERRACOTTA_PEBBLE = registerPebble("pink_glazed_terracotta", () -> Blocks.PINK_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> GRAY_GLAZED_TERRACOTTA_PEBBLE = registerPebble("gray_glazed_terracotta", () -> Blocks.GRAY_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> LIGHT_GRAY_GLAZED_TERRACOTTA_PEBBLE = registerPebble("light_gray_glazed_terracotta", () -> Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> CYAN_GLAZED_TERRACOTTA_PEBBLE = registerPebble("cyan_glazed_terracotta", () -> Blocks.CYAN_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> PURPLE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("purple_glazed_terracotta", () -> Blocks.PURPLE_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> BLUE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("blue_glazed_terracotta", () -> Blocks.BLUE_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> BROWN_GLAZED_TERRACOTTA_PEBBLE = registerPebble("brown_glazed_terracotta", () -> Blocks.BROWN_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> GREEN_GLAZED_TERRACOTTA_PEBBLE = registerPebble("green_glazed_terracotta", () -> Blocks.GREEN_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> RED_GLAZED_TERRACOTTA_PEBBLE = registerPebble("red_glazed_terracotta", () -> Blocks.RED_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> BLACK_GLAZED_TERRACOTTA_PEBBLE = registerPebble("black_glazed_terracotta", () -> Blocks.BLACK_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> WHITE_CONCRETE_PEBBLE = registerPebble("white_concrete", () -> Blocks.WHITE_CONCRETE);
        public static final RegistryObject<Block> ORANGE_CONCRETE_PEBBLE = registerPebble("orange_concrete", () -> Blocks.ORANGE_CONCRETE);
        public static final RegistryObject<Block> MAGENTA_CONCRETE_PEBBLE = registerPebble("magenta_concrete", () -> Blocks.MAGENTA_CONCRETE);
        public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_PEBBLE = registerPebble("light_blue_concrete", () -> Blocks.LIGHT_BLUE_CONCRETE);
        public static final RegistryObject<Block> YELLOW_CONCRETE_PEBBLE = registerPebble("yellow_concrete", () -> Blocks.YELLOW_CONCRETE);
        public static final RegistryObject<Block> LIME_CONCRETE_PEBBLE = registerPebble("lime_concrete", () -> Blocks.LIME_CONCRETE);
        public static final RegistryObject<Block> PINK_CONCRETE_PEBBLE = registerPebble("pink_concrete", () -> Blocks.PINK_CONCRETE);
        public static final RegistryObject<Block> GRAY_CONCRETE_PEBBLE = registerPebble("gray_concrete", () -> Blocks.GRAY_CONCRETE);
        public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_PEBBLE = registerPebble("light_gray_concrete", () -> Blocks.LIGHT_GRAY_CONCRETE);
        public static final RegistryObject<Block> CYAN_CONCRETE_PEBBLE = registerPebble("cyan_concrete", () -> Blocks.CYAN_CONCRETE);
        public static final RegistryObject<Block> PURPLE_CONCRETE_PEBBLE = registerPebble("purple_concrete", () -> Blocks.PURPLE_CONCRETE);
        public static final RegistryObject<Block> BLUE_CONCRETE_PEBBLE = registerPebble("blue_concrete", () -> Blocks.BLUE_CONCRETE);
        public static final RegistryObject<Block> BROWN_CONCRETE_PEBBLE = registerPebble("brown_concrete", () -> Blocks.BROWN_CONCRETE);
        public static final RegistryObject<Block> GREEN_CONCRETE_PEBBLE = registerPebble("green_concrete", () -> Blocks.GREEN_CONCRETE);
        public static final RegistryObject<Block> RED_CONCRETE_PEBBLE = registerPebble("red_concrete", () -> Blocks.RED_CONCRETE);
        public static final RegistryObject<Block> BLACK_CONCRETE_PEBBLE = registerPebble("black_concrete", () -> Blocks.BLACK_CONCRETE);
        public static final RegistryObject<Block> CALCITE_PEBBLE = registerPebble("calcite", () -> Blocks.CALCITE);
        public static final RegistryObject<Block> TUFF_PEBBLE = registerPebble("tuff", () -> Blocks.TUFF);
        public static final RegistryObject<Block> DRIPSTONE_PEBBLE = registerPebble("dripstone", () -> Blocks.DRIPSTONE_BLOCK);
        public static final RegistryObject<Block> OBSIDIAN_PEBBLE = registerPebble("obsidian", () -> Blocks.OBSIDIAN);
        public static final RegistryObject<Block> CRYING_OBSIDIAN_PEBBLE = registerPebble("crying_obsidian", () -> Blocks.CRYING_OBSIDIAN);
        public static final RegistryObject<Block> GLOWING_OBSIDIAN_PEBBLE = registerPebble("glowing_obsidian", MWBlocks.GLOWING_OBSIDIAN);
        public static final RegistryObject<Block> WARPED_NETHER_BRICKS_PEBBLE = registerPebble("warped_nether_bricks", MWBlocks.WARPED_NETHER_BRICKS);
        public static final RegistryObject<Block> MARBLE_PEBBLE = registerPebble("marble", MWBlocks.MARBLE);
        public static final RegistryObject<Block> WHITE_MARBLE_PEBBLE = registerPebble("white_marble", MWColoredBlocks.WHITE_MARBLE);
        public static final RegistryObject<Block> ORANGE_MARBLE_PEBBLE = registerPebble("orange_marble", MWColoredBlocks.ORANGE_MARBLE);
        public static final RegistryObject<Block> MAGENTA_MARBLE_PEBBLE = registerPebble("magenta_marble", MWColoredBlocks.MAGENTA_MARBLE);
        public static final RegistryObject<Block> LIGHT_BLUE_MARBLE_PEBBLE = registerPebble("light_blue_marble", MWColoredBlocks.LIGHT_BLUE_MARBLE);
        public static final RegistryObject<Block> YELLOW_MARBLE_PEBBLE = registerPebble("yellow_marble", MWColoredBlocks.YELLOW_MARBLE);
        public static final RegistryObject<Block> LIME_MARBLE_PEBBLE = registerPebble("lime_marble", MWColoredBlocks.LIME_MARBLE);
        public static final RegistryObject<Block> PINK_MARBLE_PEBBLE = registerPebble("pink_marble", MWColoredBlocks.PINK_MARBLE);
        public static final RegistryObject<Block> GRAY_MARBLE_PEBBLE = registerPebble("gray_marble", MWColoredBlocks.GRAY_MARBLE);
        public static final RegistryObject<Block> LIGHT_GRAY_MARBLE_PEBBLE = registerPebble("light_gray_marble", MWColoredBlocks.LIGHT_GRAY_MARBLE);
        public static final RegistryObject<Block> CYAN_MARBLE_PEBBLE = registerPebble("cyan_marble", MWColoredBlocks.CYAN_MARBLE);
        public static final RegistryObject<Block> PURPLE_MARBLE_PEBBLE = registerPebble("purple_marble", MWColoredBlocks.PURPLE_MARBLE);
        public static final RegistryObject<Block> BLUE_MARBLE_PEBBLE = registerPebble("blue_marble", MWColoredBlocks.BLUE_MARBLE);
        public static final RegistryObject<Block> BROWN_MARBLE_PEBBLE = registerPebble("brown_marble", MWColoredBlocks.BROWN_MARBLE);
        public static final RegistryObject<Block> GREEN_MARBLE_PEBBLE = registerPebble("green_marble", MWColoredBlocks.GREEN_MARBLE);
        public static final RegistryObject<Block> RED_MARBLE_PEBBLE = registerPebble("red_marble", MWColoredBlocks.RED_MARBLE);
        public static final RegistryObject<Block> BLACK_MARBLE_PEBBLE = registerPebble("black_marble", MWColoredBlocks.BLACK_MARBLE);

        //#endregion

        //#region Methods

        /**
         * Register a {@link PebbleBlock Pebble}
         *
         * @param materialName {@link String The Pebble Block material Name}
         * @param pebbleBlockSupplier {@link Supplier<Block> The supplier for the Block the Pebble is based on}
         * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
         * @return {@link RegistryObject<Block> The registered Pebble}
         */
        private static RegistryObject<Block> registerPebble(final String materialName, final Supplier<Block> pebbleBlockSupplier, final FeatureFlag... featureFlags) {
            return MWBlocks.registerBlockWithoutBlockItem(materialName + "_pebble", () -> new PebbleBlock(pebbleBlockSupplier, featureFlags));
        }

        //#endregion

        //#region Bus register

        /**
         * Register all {@link PebbleBlock Pebbles}
         */
        public static void register() { }

        //#endregion
    }

    public static class Items {

        //#region Pebbles

        public static final RegistryObject<Item> STONE_PEBBLE = registerPebble("stone", PebbleBlocks.STONE_PEBBLE);
        public static final RegistryObject<Item> COBBLESTONE_PEBBLE = registerPebble("cobblestone", PebbleBlocks.COBBLESTONE_PEBBLE);
        public static final RegistryObject<Item> MOSSY_STONE_PEBBLE = registerPebble("mossy_stone", PebbleBlocks.MOSSY_STONE_PEBBLE);
        public static final RegistryObject<Item> MOSSY_COBBLESTONE_PEBBLE = registerPebble("mossy_cobblestone", PebbleBlocks.MOSSY_COBBLESTONE_PEBBLE);
        public static final RegistryObject<Item> SMOOTH_STONE_PEBBLE = registerPebble("smooth_stone", PebbleBlocks.SMOOTH_STONE_PEBBLE);
        public static final RegistryObject<Item> STONE_BRICKS_PEBBLE = registerPebble("stone_bricks", PebbleBlocks.STONE_BRICKS_PEBBLE);
        public static final RegistryObject<Item> MOSSY_STONE_BRICKS_PEBBLE = registerPebble("mossy_stone_bricks", PebbleBlocks.MOSSY_STONE_BRICKS_PEBBLE);
        public static final RegistryObject<Item> GRANITE_PEBBLE = registerPebble("granite", PebbleBlocks.GRANITE_PEBBLE);
        public static final RegistryObject<Item> POLISHED_GRANITE_PEBBLE = registerPebble("polished_granite", PebbleBlocks.POLISHED_GRANITE_PEBBLE);
        public static final RegistryObject<Item> DIORITE_PEBBLE = registerPebble("diorite", PebbleBlocks.DIORITE_PEBBLE);
        public static final RegistryObject<Item> POLISHED_DIORITE_PEBBLE = registerPebble("polished_diorite", PebbleBlocks.POLISHED_DIORITE_PEBBLE);
        public static final RegistryObject<Item> ANDESITE_PEBBLE = registerPebble("andesite", PebbleBlocks.ANDESITE_PEBBLE);
        public static final RegistryObject<Item> POLISHED_ANDESITE_PEBBLE = registerPebble("polished_andesite", PebbleBlocks.POLISHED_ANDESITE_PEBBLE);
        public static final RegistryObject<Item> DEEPSLATE_PEBBLE = registerPebble("deepslate", PebbleBlocks.DEEPSLATE_PEBBLE);
        public static final RegistryObject<Item> COBBLED_DEEPSLATE_PEBBLE = registerPebble("cobbled_deepslate", PebbleBlocks.COBBLED_DEEPSLATE_PEBBLE);
        public static final RegistryObject<Item> POLISHED_DEEPSLATE_PEBBLE = registerPebble("polished_deepslate", PebbleBlocks.POLISHED_DEEPSLATE_PEBBLE);
        public static final RegistryObject<Item> DEEPSLATE_BRICKS_PEBBLE = registerPebble("deepslate_bricks", PebbleBlocks.DEEPSLATE_BRICKS_PEBBLE);
        public static final RegistryObject<Item> DEEPSLATE_TILES_PEBBLE = registerPebble("deepslate_tiles", PebbleBlocks.DEEPSLATE_TILES_PEBBLE);
        public static final RegistryObject<Item> REINFORCED_DEEPSLATE_PEBBLE = registerPebble("reinforced_deepslate", PebbleBlocks.REINFORCED_DEEPSLATE_PEBBLE);
        public static final RegistryObject<Item> BRICKS_PEBBLE = registerPebble("bricks", PebbleBlocks.BRICKS_PEBBLE);
        public static final RegistryObject<Item> MUD_BRICKS_PEBBLE = registerPebble("mud_bricks", PebbleBlocks.MUD_BRICKS_PEBBLE);
        public static final RegistryObject<Item> SANDSTONE_PEBBLE = registerPebble("sandstone", PebbleBlocks.SANDSTONE_PEBBLE);
        public static final RegistryObject<Item> SMOOTH_SANDSTONE_PEBBLE = registerPebble("smooth_sandstone", PebbleBlocks.SMOOTH_SANDSTONE_PEBBLE);
        public static final RegistryObject<Item> RED_SANDSTONE_PEBBLE = registerPebble("red_sandstone", PebbleBlocks.RED_SANDSTONE_PEBBLE);
        public static final RegistryObject<Item> SMOOTH_RED_SANDSTONE_PEBBLE = registerPebble("smooth_red_sandstone", PebbleBlocks.SMOOTH_RED_SANDSTONE_PEBBLE);
        public static final RegistryObject<Item> PRISMARINE_PEBBLE = registerPebble("prismarine", PebbleBlocks.PRISMARINE_PEBBLE);
        public static final RegistryObject<Item> PRISMARINE_BRICKS_PEBBLE = registerPebble("prismarine_bricks", PebbleBlocks.PRISMARINE_BRICKS_PEBBLE);
        public static final RegistryObject<Item> DARK_PRISMARINE_PEBBLE = registerPebble("dark_prismarine", PebbleBlocks.DARK_PRISMARINE_PEBBLE);
        public static final RegistryObject<Item> NETHERRACK_PEBBLE = registerPebble("netherrack", PebbleBlocks.NETHERRACK_PEBBLE);
        public static final RegistryObject<Item> NETHER_BRICKS_PEBBLE = registerPebble("nether_bricks", PebbleBlocks.NETHER_BRICKS_PEBBLE);
        public static final RegistryObject<Item> RED_NETHER_BRICKS_PEBBLE = registerPebble("red_nether_bricks", PebbleBlocks.RED_NETHER_BRICKS_PEBBLE);
        public static final RegistryObject<Item> BASALT_PEBBLE = registerPebble("basalt", PebbleBlocks.BASALT_PEBBLE);
        public static final RegistryObject<Item> SMOOTH_BASALT_PEBBLE = registerPebble("smooth_basalt", PebbleBlocks.SMOOTH_BASALT_PEBBLE);
        public static final RegistryObject<Item> POLISHED_BASALT_PEBBLE = registerPebble("polished_basalt", PebbleBlocks.POLISHED_BASALT_PEBBLE);
        public static final RegistryObject<Item> BLACKSTONE_PEBBLE = registerPebble("blackstone", PebbleBlocks.BLACKSTONE_PEBBLE);
        public static final RegistryObject<Item> POLISHED_BLACKSTONE_PEBBLE = registerPebble("polished_blackstone", PebbleBlocks.POLISHED_BLACKSTONE_PEBBLE);
        public static final RegistryObject<Item> POLISHED_BLACKSTONE_BRICKS_PEBBLE = registerPebble("polished_blackstone_bricks", PebbleBlocks.POLISHED_BLACKSTONE_BRICKS_PEBBLE);
        public static final RegistryObject<Item> GILDED_BLACKSTONE_PEBBLE = registerPebble("gilded_blackstone", PebbleBlocks.GILDED_BLACKSTONE_PEBBLE);
        public static final RegistryObject<Item> END_STONE_PEBBLE = registerPebble("end_stone", PebbleBlocks.END_STONE_PEBBLE);
        public static final RegistryObject<Item> END_STONE_BRICKS_PEBBLE = registerPebble("end_stone_bricks", PebbleBlocks.END_STONE_BRICKS_PEBBLE);
        public static final RegistryObject<Item> PURPUR_PEBBLE = registerPebble("purpur", PebbleBlocks.PURPUR_PEBBLE);
        public static final RegistryObject<Item> PURPUR_PILLAR_PEBBLE = registerPebble("purpur_pillar", PebbleBlocks.PURPUR_PILLAR_PEBBLE);
        public static final RegistryObject<Item> QUARTZ_PEBBLE = registerPebble("quartz", PebbleBlocks.QUARTZ_PEBBLE);
        public static final RegistryObject<Item> SMOOTH_QUARTZ_PEBBLE = registerPebble("smooth_quartz", PebbleBlocks.SMOOTH_QUARTZ_PEBBLE);
        public static final RegistryObject<Item> QUARTZ_BRICKS_PEBBLE = registerPebble("quartz_bricks", PebbleBlocks.QUARTZ_BRICKS_PEBBLE);
        public static final RegistryObject<Item> QUARTZ_PILLAR_PEBBLE = registerPebble("quartz_pillar", PebbleBlocks.QUARTZ_PILLAR_PEBBLE);
        public static final RegistryObject<Item> TERRACOTTA_PEBBLE = registerPebble("terracotta", PebbleBlocks.TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> WHITE_TERRACOTTA_PEBBLE = registerPebble("white_terracotta", PebbleBlocks.WHITE_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> ORANGE_TERRACOTTA_PEBBLE = registerPebble("orange_terracotta", PebbleBlocks.ORANGE_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> MAGENTA_TERRACOTTA_PEBBLE = registerPebble("magenta_terracotta", PebbleBlocks.MAGENTA_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> LIGHT_BLUE_TERRACOTTA_PEBBLE = registerPebble("light_blue_terracotta", PebbleBlocks.LIGHT_BLUE_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> YELLOW_TERRACOTTA_PEBBLE = registerPebble("yellow_terracotta", PebbleBlocks.YELLOW_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> LIME_TERRACOTTA_PEBBLE = registerPebble("lime_terracotta", PebbleBlocks.LIME_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> PINK_TERRACOTTA_PEBBLE = registerPebble("pink_terracotta", PebbleBlocks.PINK_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> GRAY_TERRACOTTA_PEBBLE = registerPebble("gray_terracotta", PebbleBlocks.GRAY_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> LIGHT_GRAY_TERRACOTTA_PEBBLE = registerPebble("light_gray_terracotta", PebbleBlocks.LIGHT_GRAY_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> CYAN_TERRACOTTA_PEBBLE = registerPebble("cyan_terracotta", PebbleBlocks.CYAN_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> PURPLE_TERRACOTTA_PEBBLE = registerPebble("purple_terracotta", PebbleBlocks.PURPLE_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> BLUE_TERRACOTTA_PEBBLE = registerPebble("blue_terracotta", PebbleBlocks.BLUE_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> BROWN_TERRACOTTA_PEBBLE = registerPebble("brown_terracotta", PebbleBlocks.BROWN_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> GREEN_TERRACOTTA_PEBBLE = registerPebble("green_terracotta", PebbleBlocks.GREEN_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> RED_TERRACOTTA_PEBBLE = registerPebble("red_terracotta", PebbleBlocks.RED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> BLACK_TERRACOTTA_PEBBLE = registerPebble("black_terracotta", PebbleBlocks.BLACK_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> WHITE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("white_glazed_terracotta", PebbleBlocks.WHITE_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> ORANGE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("orange_glazed_terracotta", PebbleBlocks.ORANGE_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> MAGENTA_GLAZED_TERRACOTTA_PEBBLE = registerPebble("magenta_glazed_terracotta", PebbleBlocks.MAGENTA_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> LIGHT_BLUE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("light_blue_glazed_terracotta", PebbleBlocks.LIGHT_BLUE_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> YELLOW_GLAZED_TERRACOTTA_PEBBLE = registerPebble("yellow_glazed_terracotta", PebbleBlocks.YELLOW_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> LIME_GLAZED_TERRACOTTA_PEBBLE = registerPebble("lime_glazed_terracotta", PebbleBlocks.LIME_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> PINK_GLAZED_TERRACOTTA_PEBBLE = registerPebble("pink_glazed_terracotta", PebbleBlocks.PINK_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> GRAY_GLAZED_TERRACOTTA_PEBBLE = registerPebble("gray_glazed_terracotta", PebbleBlocks.GRAY_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> LIGHT_GRAY_GLAZED_TERRACOTTA_PEBBLE = registerPebble("light_gray_glazed_terracotta", PebbleBlocks.LIGHT_GRAY_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> CYAN_GLAZED_TERRACOTTA_PEBBLE = registerPebble("cyan_glazed_terracotta", PebbleBlocks.CYAN_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> PURPLE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("purple_glazed_terracotta", PebbleBlocks.PURPLE_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> BLUE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("blue_glazed_terracotta", PebbleBlocks.BLUE_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> BROWN_GLAZED_TERRACOTTA_PEBBLE = registerPebble("brown_glazed_terracotta", PebbleBlocks.BROWN_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> GREEN_GLAZED_TERRACOTTA_PEBBLE = registerPebble("green_glazed_terracotta", PebbleBlocks.GREEN_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> RED_GLAZED_TERRACOTTA_PEBBLE = registerPebble("red_glazed_terracotta", PebbleBlocks.RED_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> BLACK_GLAZED_TERRACOTTA_PEBBLE = registerPebble("black_glazed_terracotta", PebbleBlocks.BLACK_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> WHITE_CONCRETE_PEBBLE = registerPebble("white_concrete", PebbleBlocks.WHITE_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> ORANGE_CONCRETE_PEBBLE = registerPebble("orange_concrete", PebbleBlocks.ORANGE_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> MAGENTA_CONCRETE_PEBBLE = registerPebble("magenta_concrete", PebbleBlocks.MAGENTA_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> LIGHT_BLUE_CONCRETE_PEBBLE = registerPebble("light_blue_concrete", PebbleBlocks.LIGHT_BLUE_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> YELLOW_CONCRETE_PEBBLE = registerPebble("yellow_concrete", PebbleBlocks.YELLOW_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> LIME_CONCRETE_PEBBLE = registerPebble("lime_concrete", PebbleBlocks.LIME_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> PINK_CONCRETE_PEBBLE = registerPebble("pink_concrete", PebbleBlocks.PINK_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> GRAY_CONCRETE_PEBBLE = registerPebble("gray_concrete", PebbleBlocks.GRAY_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> LIGHT_GRAY_CONCRETE_PEBBLE = registerPebble("light_gray_concrete", PebbleBlocks.LIGHT_GRAY_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> CYAN_CONCRETE_PEBBLE = registerPebble("cyan_concrete", PebbleBlocks.CYAN_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> PURPLE_CONCRETE_PEBBLE = registerPebble("purple_concrete", PebbleBlocks.PURPLE_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> BLUE_CONCRETE_PEBBLE = registerPebble("blue_concrete", PebbleBlocks.BLUE_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> BROWN_CONCRETE_PEBBLE = registerPebble("brown_concrete", PebbleBlocks.BROWN_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> GREEN_CONCRETE_PEBBLE = registerPebble("green_concrete", PebbleBlocks.GREEN_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> RED_CONCRETE_PEBBLE = registerPebble("red_concrete", PebbleBlocks.RED_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> BLACK_CONCRETE_PEBBLE = registerPebble("black_concrete", PebbleBlocks.BLACK_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> CALCITE_PEBBLE = registerPebble("calcite", PebbleBlocks.CALCITE_PEBBLE);
        public static final RegistryObject<Item> TUFF_PEBBLE = registerPebble("tuff", PebbleBlocks.TUFF_PEBBLE);
        public static final RegistryObject<Item> DRIPSTONE_PEBBLE = registerPebble("dripstone", PebbleBlocks.DRIPSTONE_PEBBLE);
        public static final RegistryObject<Item> OBSIDIAN_PEBBLE = registerPebble("obsidian", PebbleBlocks.OBSIDIAN_PEBBLE);
        public static final RegistryObject<Item> CRYING_OBSIDIAN_PEBBLE = registerPebble("crying_obsidian", PebbleBlocks.CRYING_OBSIDIAN_PEBBLE);
        public static final RegistryObject<Item> GLOWING_OBSIDIAN_PEBBLE = registerPebble("glowing_obsidian", PebbleBlocks.GLOWING_OBSIDIAN_PEBBLE);
        public static final RegistryObject<Item> WARPED_NETHER_BRICKS_PEBBLE = registerPebble("warped_nether_bricks", PebbleBlocks.WARPED_NETHER_BRICKS_PEBBLE);
        public static final RegistryObject<Item> MARBLE_PEBBLE = registerPebble("marble", PebbleBlocks.MARBLE_PEBBLE);
        public static final RegistryObject<Item> WHITE_MARBLE_PEBBLE = registerPebble("white_marble", PebbleBlocks.WHITE_MARBLE_PEBBLE);
        public static final RegistryObject<Item> ORANGE_MARBLE_PEBBLE = registerPebble("orange_marble", PebbleBlocks.ORANGE_MARBLE_PEBBLE);
        public static final RegistryObject<Item> MAGENTA_MARBLE_PEBBLE = registerPebble("magenta_marble", PebbleBlocks.MAGENTA_MARBLE_PEBBLE);
        public static final RegistryObject<Item> LIGHT_BLUE_MARBLE_PEBBLE = registerPebble("light_blue_marble", PebbleBlocks.LIGHT_BLUE_MARBLE_PEBBLE);
        public static final RegistryObject<Item> YELLOW_MARBLE_PEBBLE = registerPebble("yellow_marble", PebbleBlocks.YELLOW_MARBLE_PEBBLE);
        public static final RegistryObject<Item> LIME_MARBLE_PEBBLE = registerPebble("lime_marble", PebbleBlocks.LIME_MARBLE_PEBBLE);
        public static final RegistryObject<Item> PINK_MARBLE_PEBBLE = registerPebble("pink_marble", PebbleBlocks.PINK_MARBLE_PEBBLE);
        public static final RegistryObject<Item> GRAY_MARBLE_PEBBLE = registerPebble("gray_marble", PebbleBlocks.GRAY_MARBLE_PEBBLE);
        public static final RegistryObject<Item> LIGHT_GRAY_MARBLE_PEBBLE = registerPebble("light_gray_marble", PebbleBlocks.LIGHT_GRAY_MARBLE_PEBBLE);
        public static final RegistryObject<Item> CYAN_MARBLE_PEBBLE = registerPebble("cyan_marble", PebbleBlocks.CYAN_MARBLE_PEBBLE);
        public static final RegistryObject<Item> PURPLE_MARBLE_PEBBLE = registerPebble("purple_marble", PebbleBlocks.PURPLE_MARBLE_PEBBLE);
        public static final RegistryObject<Item> BLUE_MARBLE_PEBBLE = registerPebble("blue_marble", PebbleBlocks.BLUE_MARBLE_PEBBLE);
        public static final RegistryObject<Item> BROWN_MARBLE_PEBBLE = registerPebble("brown_marble", PebbleBlocks.BROWN_MARBLE_PEBBLE);
        public static final RegistryObject<Item> GREEN_MARBLE_PEBBLE = registerPebble("green_marble", PebbleBlocks.GREEN_MARBLE_PEBBLE);
        public static final RegistryObject<Item> RED_MARBLE_PEBBLE = registerPebble("red_marble", PebbleBlocks.RED_MARBLE_PEBBLE);
        public static final RegistryObject<Item> BLACK_MARBLE_PEBBLE = registerPebble("black_marble", PebbleBlocks.BLACK_MARBLE_PEBBLE);

        //#endregion

        //#region Methods

        /**
         * Register a {@link PebbleItem Pebble}
         *
         * @param materialName {@link String The Pebble Item material Name}
         * @param pebbleBlockSupplier {@link Supplier<Block> The Pebble Block supplier}
         * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
         * @return {@link RegistryObject<Item> The registered Pebble}
         */
        private static RegistryObject<Item> registerPebble(final String materialName, final Supplier<Block> pebbleBlockSupplier, final FeatureFlag... featureFlags) {
            return MWItems.registerItem(materialName + "_pebble", () -> new PebbleItem(pebbleBlockSupplier, featureFlags));
        }

        //#endregion

        //#region Bus register

        /**
         * Register all {@link PebbleItem Pebbles}
         */
        public static void register() { }

        //#endregion
    }

}