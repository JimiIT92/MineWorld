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

        public static final RegistryObject<Block> STONE_PEBBLE = registerPebble("stone_pebble", () -> Blocks.STONE);
        public static final RegistryObject<Block> COBBLESTONE_PEBBLE = registerPebble("cobblestone_pebble", () -> Blocks.COBBLESTONE);
        public static final RegistryObject<Block> MOSSY_STONE_PEBBLE = registerPebble("mossy_stone_pebble", Suppliers.memoize(() -> MWBlocks.MOSSY_STONE.get()));
        public static final RegistryObject<Block> MOSSY_COBBLESTONE_PEBBLE = registerPebble("mossy_cobblestone_pebble", () -> Blocks.MOSSY_COBBLESTONE);
        public static final RegistryObject<Block> SMOOTH_STONE_PEBBLE = registerPebble("smooth_stone_pebble", () -> Blocks.SMOOTH_STONE);
        public static final RegistryObject<Block> STONE_BRICKS_PEBBLE = registerPebble("stone_bricks_pebble", () -> Blocks.STONE_BRICKS);
        public static final RegistryObject<Block> MOSSY_STONE_BRICKS_PEBBLE = registerPebble("mossy_stone_bricks_pebble", () -> Blocks.MOSSY_STONE_BRICKS);
        public static final RegistryObject<Block> GRANITE_PEBBLE = registerPebble("granite_pebble", () -> Blocks.GRANITE);
        public static final RegistryObject<Block> POLISHED_GRANITE_PEBBLE = registerPebble("polished_granite_pebble", () -> Blocks.POLISHED_GRANITE);
        public static final RegistryObject<Block> DIORITE_PEBBLE = registerPebble("diorite_pebble", () -> Blocks.DIORITE);
        public static final RegistryObject<Block> POLISHED_DIORITE_PEBBLE = registerPebble("polished_diorite_pebble", () -> Blocks.POLISHED_DIORITE);
        public static final RegistryObject<Block> ANDESITE_PEBBLE = registerPebble("andesite_pebble", () -> Blocks.ANDESITE);
        public static final RegistryObject<Block> POLISHED_ANDESITE_PEBBLE = registerPebble("polished_andesite_pebble", () -> Blocks.POLISHED_ANDESITE);
        public static final RegistryObject<Block> DEEPSLATE_PEBBLE = registerPebble("deepslate_pebble", () -> Blocks.DEEPSLATE);
        public static final RegistryObject<Block> COBBLED_DEEPSLATE_PEBBLE = registerPebble("cobbled_deepslate_pebble", () -> Blocks.COBBLED_DEEPSLATE);
        public static final RegistryObject<Block> POLISHED_DEEPSLATE_PEBBLE = registerPebble("polished_deepslate_pebble", () -> Blocks.POLISHED_DEEPSLATE);
        public static final RegistryObject<Block> DEEPSLATE_BRICKS_PEBBLE = registerPebble("deepslate_bricks_pebble", () -> Blocks.DEEPSLATE_BRICKS);
        public static final RegistryObject<Block> DEEPSLATE_TILES_PEBBLE = registerPebble("deepslate_tiles_pebble", () -> Blocks.DEEPSLATE_TILES);
        public static final RegistryObject<Block> REINFORCED_DEEPSLATE_PEBBLE = registerPebble("reinforced_deepslate_pebble", () -> Blocks.REINFORCED_DEEPSLATE);
        public static final RegistryObject<Block> BRICKS_PEBBLE = registerPebble("bricks_pebble", () -> Blocks.BRICKS);
        public static final RegistryObject<Block> MUD_BRICKS_PEBBLE = registerPebble("mud_bricks_pebble", () -> Blocks.MUD_BRICKS);
        public static final RegistryObject<Block> SANDSTONE_PEBBLE = registerPebble("sandstone_pebble", () -> Blocks.SANDSTONE);
        public static final RegistryObject<Block> SMOOTH_SANDSTONE_PEBBLE = registerPebble("smooth_sandstone_pebble", () -> Blocks.SMOOTH_SANDSTONE);
        public static final RegistryObject<Block> RED_SANDSTONE_PEBBLE = registerPebble("red_sandstone_pebble", () -> Blocks.RED_SANDSTONE);
        public static final RegistryObject<Block> SMOOTH_RED_SANDSTONE_PEBBLE = registerPebble("smooth_red_sandstone_pebble", () -> Blocks.SMOOTH_RED_SANDSTONE);
        public static final RegistryObject<Block> PRISMARINE_PEBBLE = registerPebble("prismarine_pebble", () -> Blocks.PRISMARINE);
        public static final RegistryObject<Block> PRISMARINE_BRICKS_PEBBLE = registerPebble("prismarine_bricks_pebble", () -> Blocks.PRISMARINE_BRICKS);
        public static final RegistryObject<Block> DARK_PRISMARINE_PEBBLE = registerPebble("dark_prismarine_pebble", () -> Blocks.DARK_PRISMARINE);
        public static final RegistryObject<Block> NETHERRACK_PEBBLE = registerPebble("netherrack_pebble", () -> Blocks.NETHERRACK);
        public static final RegistryObject<Block> NETHER_BRICKS_PEBBLE = registerPebble("nether_bricks_pebble", () -> Blocks.NETHER_BRICKS);
        public static final RegistryObject<Block> RED_NETHER_BRICKS_PEBBLE = registerPebble("red_nether_bricks_pebble", () -> Blocks.RED_NETHER_BRICKS);
        public static final RegistryObject<Block> BASALT_PEBBLE = registerPebble("basalt_pebble", () -> Blocks.BASALT);
        public static final RegistryObject<Block> SMOOTH_BASALT_PEBBLE = registerPebble("smooth_basalt_pebble", () -> Blocks.SMOOTH_BASALT);
        public static final RegistryObject<Block> POLISHED_BASALT_PEBBLE = registerPebble("polished_basalt_pebble", () -> Blocks.POLISHED_BASALT);
        public static final RegistryObject<Block> BLACKSTONE_PEBBLE = registerPebble("blackstone_pebble", () -> Blocks.BLACKSTONE);
        public static final RegistryObject<Block> POLISHED_BLACKSTONE_PEBBLE = registerPebble("polished_blackstone_pebble", () -> Blocks.POLISHED_BLACKSTONE);
        public static final RegistryObject<Block> POLISHED_BLACKSTONE_BRICKS_PEBBLE = registerPebble("polished_blackstone_bricks_pebble", () -> Blocks.POLISHED_BLACKSTONE_BRICKS);
        public static final RegistryObject<Block> GILDED_BLACKSTONE_PEBBLE = registerPebble("gilded_blackstone_pebble", () -> Blocks.GILDED_BLACKSTONE);
        public static final RegistryObject<Block> END_STONE_PEBBLE = registerPebble("end_stone_pebble", () -> Blocks.END_STONE);
        public static final RegistryObject<Block> END_STONE_BRICKS_PEBBLE = registerPebble("end_stone_bricks_pebble", () -> Blocks.END_STONE_BRICKS);
        public static final RegistryObject<Block> PURPUR_PEBBLE = registerPebble("purpur_pebble", () -> Blocks.PURPUR_BLOCK);
        public static final RegistryObject<Block> PURPUR_PILLAR_PEBBLE = registerPebble("purpur_pillar_pebble", () -> Blocks.PURPUR_PILLAR);
        public static final RegistryObject<Block> QUARTZ_PEBBLE = registerPebble("quartz_pebble", () -> Blocks.QUARTZ_BLOCK);
        public static final RegistryObject<Block> SMOOTH_QUARTZ_PEBBLE = registerPebble("smooth_quartz_pebble", () -> Blocks.SMOOTH_QUARTZ);
        public static final RegistryObject<Block> QUARTZ_BRICKS_PEBBLE = registerPebble("quartz_bricks_pebble", () -> Blocks.QUARTZ_BRICKS);
        public static final RegistryObject<Block> QUARTZ_PILLAR_PEBBLE = registerPebble("quartz_pillar_pebble", () -> Blocks.QUARTZ_PILLAR);
        public static final RegistryObject<Block> TERRACOTTA_PEBBLE = registerPebble("terracotta_pebble", () -> Blocks.TERRACOTTA);
        public static final RegistryObject<Block> WHITE_TERRACOTTA_PEBBLE = registerPebble("white_terracotta_pebble", () -> Blocks.WHITE_TERRACOTTA);
        public static final RegistryObject<Block> ORANGE_TERRACOTTA_PEBBLE = registerPebble("orange_terracotta_pebble", () -> Blocks.ORANGE_TERRACOTTA);
        public static final RegistryObject<Block> MAGENTA_TERRACOTTA_PEBBLE = registerPebble("magenta_terracotta_pebble", () -> Blocks.MAGENTA_TERRACOTTA);
        public static final RegistryObject<Block> LIGHT_BLUE_TERRACOTTA_PEBBLE = registerPebble("light_blue_terracotta_pebble", () -> Blocks.LIGHT_BLUE_TERRACOTTA);
        public static final RegistryObject<Block> YELLOW_TERRACOTTA_PEBBLE = registerPebble("yellow_terracotta_pebble", () -> Blocks.YELLOW_TERRACOTTA);
        public static final RegistryObject<Block> LIME_TERRACOTTA_PEBBLE = registerPebble("lime_terracotta_pebble", () -> Blocks.LIME_TERRACOTTA);
        public static final RegistryObject<Block> PINK_TERRACOTTA_PEBBLE = registerPebble("pink_terracotta_pebble", () -> Blocks.PINK_TERRACOTTA);
        public static final RegistryObject<Block> GRAY_TERRACOTTA_PEBBLE = registerPebble("gray_terracotta_pebble", () -> Blocks.GRAY_TERRACOTTA);
        public static final RegistryObject<Block> LIGHT_GRAY_TERRACOTTA_PEBBLE = registerPebble("light_gray_terracotta_pebble", () -> Blocks.LIGHT_GRAY_TERRACOTTA);
        public static final RegistryObject<Block> CYAN_TERRACOTTA_PEBBLE = registerPebble("cyan_terracotta_pebble", () -> Blocks.CYAN_TERRACOTTA);
        public static final RegistryObject<Block> PURPLE_TERRACOTTA_PEBBLE = registerPebble("purple_terracotta_pebble", () -> Blocks.PURPLE_TERRACOTTA);
        public static final RegistryObject<Block> BLUE_TERRACOTTA_PEBBLE = registerPebble("blue_terracotta_pebble", () -> Blocks.BLUE_TERRACOTTA);
        public static final RegistryObject<Block> BROWN_TERRACOTTA_PEBBLE = registerPebble("brown_terracotta_pebble", () -> Blocks.BROWN_TERRACOTTA);
        public static final RegistryObject<Block> GREEN_TERRACOTTA_PEBBLE = registerPebble("green_terracotta_pebble", () -> Blocks.GREEN_TERRACOTTA);
        public static final RegistryObject<Block> RED_TERRACOTTA_PEBBLE = registerPebble("red_terracotta_pebble", () -> Blocks.RED_TERRACOTTA);
        public static final RegistryObject<Block> BLACK_TERRACOTTA_PEBBLE = registerPebble("black_terracotta_pebble", () -> Blocks.BLACK_TERRACOTTA);
        public static final RegistryObject<Block> WHITE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("white_glazed_terracotta_pebble", () -> Blocks.WHITE_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> ORANGE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("orange_glazed_terracotta_pebble", () -> Blocks.ORANGE_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> MAGENTA_GLAZED_TERRACOTTA_PEBBLE = registerPebble("magenta_glazed_terracotta_pebble", () -> Blocks.MAGENTA_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> LIGHT_BLUE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("light_blue_glazed_terracotta_pebble", () -> Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> YELLOW_GLAZED_TERRACOTTA_PEBBLE = registerPebble("yellow_glazed_terracotta_pebble", () -> Blocks.YELLOW_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> LIME_GLAZED_TERRACOTTA_PEBBLE = registerPebble("lime_glazed_terracotta_pebble", () -> Blocks.LIME_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> PINK_GLAZED_TERRACOTTA_PEBBLE = registerPebble("pink_glazed_terracotta_pebble", () -> Blocks.PINK_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> GRAY_GLAZED_TERRACOTTA_PEBBLE = registerPebble("gray_glazed_terracotta_pebble", () -> Blocks.GRAY_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> LIGHT_GRAY_GLAZED_TERRACOTTA_PEBBLE = registerPebble("light_gray_glazed_terracotta_pebble", () -> Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> CYAN_GLAZED_TERRACOTTA_PEBBLE = registerPebble("cyan_glazed_terracotta_pebble", () -> Blocks.CYAN_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> PURPLE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("purple_glazed_terracotta_pebble", () -> Blocks.PURPLE_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> BLUE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("blue_glazed_terracotta_pebble", () -> Blocks.BLUE_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> BROWN_GLAZED_TERRACOTTA_PEBBLE = registerPebble("brown_glazed_terracotta_pebble", () -> Blocks.BROWN_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> GREEN_GLAZED_TERRACOTTA_PEBBLE = registerPebble("green_glazed_terracotta_pebble", () -> Blocks.GREEN_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> RED_GLAZED_TERRACOTTA_PEBBLE = registerPebble("red_glazed_terracotta_pebble", () -> Blocks.RED_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> BLACK_GLAZED_TERRACOTTA_PEBBLE = registerPebble("black_glazed_terracotta_pebble", () -> Blocks.BLACK_GLAZED_TERRACOTTA);
        public static final RegistryObject<Block> WHITE_CONCRETE_PEBBLE = registerPebble("white_concrete_pebble", () -> Blocks.WHITE_CONCRETE);
        public static final RegistryObject<Block> ORANGE_CONCRETE_PEBBLE = registerPebble("orange_concrete_pebble", () -> Blocks.ORANGE_CONCRETE);
        public static final RegistryObject<Block> MAGENTA_CONCRETE_PEBBLE = registerPebble("magenta_concrete_pebble", () -> Blocks.MAGENTA_CONCRETE);
        public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_PEBBLE = registerPebble("light_blue_concrete_pebble", () -> Blocks.LIGHT_BLUE_CONCRETE);
        public static final RegistryObject<Block> YELLOW_CONCRETE_PEBBLE = registerPebble("yellow_concrete_pebble", () -> Blocks.YELLOW_CONCRETE);
        public static final RegistryObject<Block> LIME_CONCRETE_PEBBLE = registerPebble("lime_concrete_pebble", () -> Blocks.LIME_CONCRETE);
        public static final RegistryObject<Block> PINK_CONCRETE_PEBBLE = registerPebble("pink_concrete_pebble", () -> Blocks.PINK_CONCRETE);
        public static final RegistryObject<Block> GRAY_CONCRETE_PEBBLE = registerPebble("gray_concrete_pebble", () -> Blocks.GRAY_CONCRETE);
        public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_PEBBLE = registerPebble("light_gray_concrete_pebble", () -> Blocks.LIGHT_GRAY_CONCRETE);
        public static final RegistryObject<Block> CYAN_CONCRETE_PEBBLE = registerPebble("cyan_concrete_pebble", () -> Blocks.CYAN_CONCRETE);
        public static final RegistryObject<Block> PURPLE_CONCRETE_PEBBLE = registerPebble("purple_concrete_pebble", () -> Blocks.PURPLE_CONCRETE);
        public static final RegistryObject<Block> BLUE_CONCRETE_PEBBLE = registerPebble("blue_concrete_pebble", () -> Blocks.BLUE_CONCRETE);
        public static final RegistryObject<Block> BROWN_CONCRETE_PEBBLE = registerPebble("brown_concrete_pebble", () -> Blocks.BROWN_CONCRETE);
        public static final RegistryObject<Block> GREEN_CONCRETE_PEBBLE = registerPebble("green_concrete_pebble", () -> Blocks.GREEN_CONCRETE);
        public static final RegistryObject<Block> RED_CONCRETE_PEBBLE = registerPebble("red_concrete_pebble", () -> Blocks.RED_CONCRETE);
        public static final RegistryObject<Block> BLACK_CONCRETE_PEBBLE = registerPebble("black_concrete_pebble", () -> Blocks.BLACK_CONCRETE);
        public static final RegistryObject<Block> CALCITE_PEBBLE = registerPebble("calcite_pebble", () -> Blocks.CALCITE);
        public static final RegistryObject<Block> TUFF_PEBBLE = registerPebble("tuff_pebble", () -> Blocks.TUFF);
        public static final RegistryObject<Block> DRIPSTONE_PEBBLE = registerPebble("dripstone_pebble", () -> Blocks.DRIPSTONE_BLOCK);
        public static final RegistryObject<Block> OBSIDIAN_PEBBLE = registerPebble("obsidian_pebble", () -> Blocks.OBSIDIAN);
        public static final RegistryObject<Block> CRYING_OBSIDIAN_PEBBLE = registerPebble("crying_obsidian_pebble", () -> Blocks.CRYING_OBSIDIAN);
        //public static final RegistryObject<Block> GLOWING_OBSIDIAN_PEBBLE = registerPebble("glowing_obsidian_pebble", GLOWING_OBSIDIAN);
        //public static final RegistryObject<Block> WARPED_NETHER_BRICKS_PEBBLE = registerPebble("warped_nether_bricks_pebble", WARPED_NETHER_BRICKS);
        public static final RegistryObject<Block> MARBLE_PEBBLE = registerPebble("marble_pebble", MWBlocks.MARBLE);
        public static final RegistryObject<Block> WHITE_MARBLE_PEBBLE = registerPebble("white_marble_pebble", MWColoredBlocks.WHITE_MARBLE);
        public static final RegistryObject<Block> ORANGE_MARBLE_PEBBLE = registerPebble("orange_marble_pebble", MWColoredBlocks.ORANGE_MARBLE);
        public static final RegistryObject<Block> MAGENTA_MARBLE_PEBBLE = registerPebble("magenta_marble_pebble", MWColoredBlocks.MAGENTA_MARBLE);
        public static final RegistryObject<Block> LIGHT_BLUE_MARBLE_PEBBLE = registerPebble("light_blue_marble_pebble", MWColoredBlocks.LIGHT_BLUE_MARBLE);
        public static final RegistryObject<Block> YELLOW_MARBLE_PEBBLE = registerPebble("yellow_marble_pebble", MWColoredBlocks.YELLOW_MARBLE);
        public static final RegistryObject<Block> LIME_MARBLE_PEBBLE = registerPebble("lime_marble_pebble", MWColoredBlocks.LIME_MARBLE);
        public static final RegistryObject<Block> PINK_MARBLE_PEBBLE = registerPebble("pink_marble_pebble", MWColoredBlocks.PINK_MARBLE);
        public static final RegistryObject<Block> GRAY_MARBLE_PEBBLE = registerPebble("gray_marble_pebble", MWColoredBlocks.GRAY_MARBLE);
        public static final RegistryObject<Block> LIGHT_GRAY_MARBLE_PEBBLE = registerPebble("light_gray_marble_pebble", MWColoredBlocks.LIGHT_GRAY_MARBLE);
        public static final RegistryObject<Block> CYAN_MARBLE_PEBBLE = registerPebble("cyan_marble_pebble", MWColoredBlocks.CYAN_MARBLE);
        public static final RegistryObject<Block> PURPLE_MARBLE_PEBBLE = registerPebble("purple_marble_pebble", MWColoredBlocks.PURPLE_MARBLE);
        public static final RegistryObject<Block> BLUE_MARBLE_PEBBLE = registerPebble("blue_marble_pebble", MWColoredBlocks.BLUE_MARBLE);
        public static final RegistryObject<Block> BROWN_MARBLE_PEBBLE = registerPebble("brown_marble_pebble", MWColoredBlocks.BROWN_MARBLE);
        public static final RegistryObject<Block> GREEN_MARBLE_PEBBLE = registerPebble("green_marble_pebble", MWColoredBlocks.GREEN_MARBLE);
        public static final RegistryObject<Block> RED_MARBLE_PEBBLE = registerPebble("red_marble_pebble", MWColoredBlocks.RED_MARBLE);
        public static final RegistryObject<Block> BLACK_MARBLE_PEBBLE = registerPebble("black_marble_pebble", MWColoredBlocks.BLACK_MARBLE);

        //#endregion

        //#region Methods

        /**
         * Register a {@link PebbleBlock Pebble}
         *
         * @param name {@link String The Pebble Block Name}
         * @param pebbleBlockSupplier {@link Supplier<Block> The supplier for the Block the Pebble is based on}
         * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
         * @return {@link RegistryObject<Block> The registered Pebble}
         */
        private static RegistryObject<Block> registerPebble(final String name, final Supplier<Block> pebbleBlockSupplier, final FeatureFlag... featureFlags) {
            return MWBlocks.registerBlockWithoutBlockItem(name, () -> new PebbleBlock(pebbleBlockSupplier, featureFlags));
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

        public static final RegistryObject<Item> STONE_PEBBLE = registerPebble("stone_pebble", PebbleBlocks.STONE_PEBBLE);
        public static final RegistryObject<Item> COBBLESTONE_PEBBLE = registerPebble("cobblestone_pebble", PebbleBlocks.COBBLESTONE_PEBBLE);
        public static final RegistryObject<Item> MOSSY_STONE_PEBBLE = registerPebble("mossy_stone_pebble", PebbleBlocks.MOSSY_STONE_PEBBLE);
        public static final RegistryObject<Item> MOSSY_COBBLESTONE_PEBBLE = registerPebble("mossy_cobblestone_pebble", PebbleBlocks.MOSSY_COBBLESTONE_PEBBLE);
        public static final RegistryObject<Item> SMOOTH_STONE_PEBBLE = registerPebble("smooth_stone_pebble", PebbleBlocks.SMOOTH_STONE_PEBBLE);
        public static final RegistryObject<Item> STONE_BRICKS_PEBBLE = registerPebble("stone_bricks_pebble", PebbleBlocks.STONE_BRICKS_PEBBLE);
        public static final RegistryObject<Item> MOSSY_STONE_BRICKS_PEBBLE = registerPebble("mossy_stone_bricks_pebble", PebbleBlocks.MOSSY_STONE_BRICKS_PEBBLE);
        public static final RegistryObject<Item> GRANITE_PEBBLE = registerPebble("granite_pebble", PebbleBlocks.GRANITE_PEBBLE);
        public static final RegistryObject<Item> POLISHED_GRANITE_PEBBLE = registerPebble("polished_granite_pebble", PebbleBlocks.POLISHED_GRANITE_PEBBLE);
        public static final RegistryObject<Item> DIORITE_PEBBLE = registerPebble("diorite_pebble", PebbleBlocks.DIORITE_PEBBLE);
        public static final RegistryObject<Item> POLISHED_DIORITE_PEBBLE = registerPebble("polished_diorite_pebble", PebbleBlocks.POLISHED_DIORITE_PEBBLE);
        public static final RegistryObject<Item> ANDESITE_PEBBLE = registerPebble("andesite_pebble", PebbleBlocks.ANDESITE_PEBBLE);
        public static final RegistryObject<Item> POLISHED_ANDESITE_PEBBLE = registerPebble("polished_andesite_pebble", PebbleBlocks.POLISHED_ANDESITE_PEBBLE);
        public static final RegistryObject<Item> DEEPSLATE_PEBBLE = registerPebble("deepslate_pebble", PebbleBlocks.DEEPSLATE_PEBBLE);
        public static final RegistryObject<Item> COBBLED_DEEPSLATE_PEBBLE = registerPebble("cobbled_deepslate_pebble", PebbleBlocks.COBBLED_DEEPSLATE_PEBBLE);
        public static final RegistryObject<Item> POLISHED_DEEPSLATE_PEBBLE = registerPebble("polished_deepslate_pebble", PebbleBlocks.POLISHED_DEEPSLATE_PEBBLE);
        public static final RegistryObject<Item> DEEPSLATE_BRICKS_PEBBLE = registerPebble("deepslate_bricks_pebble", PebbleBlocks.DEEPSLATE_BRICKS_PEBBLE);
        public static final RegistryObject<Item> DEEPSLATE_TILES_PEBBLE = registerPebble("deepslate_tiles_pebble", PebbleBlocks.DEEPSLATE_TILES_PEBBLE);
        public static final RegistryObject<Item> REINFORCED_DEEPSLATE_PEBBLE = registerPebble("reinforced_deepslate_pebble", PebbleBlocks.REINFORCED_DEEPSLATE_PEBBLE);
        public static final RegistryObject<Item> BRICKS_PEBBLE = registerPebble("bricks_pebble", PebbleBlocks.BRICKS_PEBBLE);
        public static final RegistryObject<Item> MUD_BRICKS_PEBBLE = registerPebble("mud_bricks_pebble", PebbleBlocks.MUD_BRICKS_PEBBLE);
        public static final RegistryObject<Item> SANDSTONE_PEBBLE = registerPebble("sandstone_pebble", PebbleBlocks.SANDSTONE_PEBBLE);
        public static final RegistryObject<Item> SMOOTH_SANDSTONE_PEBBLE = registerPebble("smooth_sandstone_pebble", PebbleBlocks.SMOOTH_SANDSTONE_PEBBLE);
        public static final RegistryObject<Item> RED_SANDSTONE_PEBBLE = registerPebble("red_sandstone_pebble", PebbleBlocks.RED_SANDSTONE_PEBBLE);
        public static final RegistryObject<Item> SMOOTH_RED_SANDSTONE_PEBBLE = registerPebble("smooth_red_sandstone_pebble", PebbleBlocks.SMOOTH_RED_SANDSTONE_PEBBLE);
        public static final RegistryObject<Item> PRISMARINE_PEBBLE = registerPebble("prismarine_pebble", PebbleBlocks.PRISMARINE_PEBBLE);
        public static final RegistryObject<Item> PRISMARINE_BRICKS_PEBBLE = registerPebble("prismarine_bricks_pebble", PebbleBlocks.PRISMARINE_BRICKS_PEBBLE);
        public static final RegistryObject<Item> DARK_PRISMARINE_PEBBLE = registerPebble("dark_prismarine_pebble", PebbleBlocks.DARK_PRISMARINE_PEBBLE);
        public static final RegistryObject<Item> NETHERRACK_PEBBLE = registerPebble("netherrack_pebble", PebbleBlocks.NETHERRACK_PEBBLE);
        public static final RegistryObject<Item> NETHER_BRICKS_PEBBLE = registerPebble("nether_bricks_pebble", PebbleBlocks.NETHER_BRICKS_PEBBLE);
        public static final RegistryObject<Item> RED_NETHER_BRICKS_PEBBLE = registerPebble("red_nether_bricks_pebble", PebbleBlocks.RED_NETHER_BRICKS_PEBBLE);
        public static final RegistryObject<Item> BASALT_PEBBLE = registerPebble("basalt_pebble", PebbleBlocks.BASALT_PEBBLE);
        public static final RegistryObject<Item> SMOOTH_BASALT_PEBBLE = registerPebble("smooth_basalt_pebble", PebbleBlocks.SMOOTH_BASALT_PEBBLE);
        public static final RegistryObject<Item> POLISHED_BASALT_PEBBLE = registerPebble("polished_basalt_pebble", PebbleBlocks.POLISHED_BASALT_PEBBLE);
        public static final RegistryObject<Item> BLACKSTONE_PEBBLE = registerPebble("blackstone_pebble", PebbleBlocks.BLACKSTONE_PEBBLE);
        public static final RegistryObject<Item> POLISHED_BLACKSTONE_PEBBLE = registerPebble("polished_blackstone_pebble", PebbleBlocks.POLISHED_BLACKSTONE_PEBBLE);
        public static final RegistryObject<Item> POLISHED_BLACKSTONE_BRICKS_PEBBLE = registerPebble("polished_blackstone_bricks_pebble", PebbleBlocks.POLISHED_BLACKSTONE_BRICKS_PEBBLE);
        public static final RegistryObject<Item> GILDED_BLACKSTONE_PEBBLE = registerPebble("gilded_blackstone_pebble", PebbleBlocks.GILDED_BLACKSTONE_PEBBLE);
        public static final RegistryObject<Item> END_STONE_PEBBLE = registerPebble("end_stone_pebble", PebbleBlocks.END_STONE_PEBBLE);
        public static final RegistryObject<Item> END_STONE_BRICKS_PEBBLE = registerPebble("end_stone_bricks_pebble", PebbleBlocks.END_STONE_BRICKS_PEBBLE);
        public static final RegistryObject<Item> PURPUR_PEBBLE = registerPebble("purpur_pebble", PebbleBlocks.PURPUR_PEBBLE);
        public static final RegistryObject<Item> PURPUR_PILLAR_PEBBLE = registerPebble("purpur_pillar_pebble", PebbleBlocks.PURPUR_PILLAR_PEBBLE);
        public static final RegistryObject<Item> QUARTZ_PEBBLE = registerPebble("quartz_pebble", PebbleBlocks.QUARTZ_PEBBLE);
        public static final RegistryObject<Item> SMOOTH_QUARTZ_PEBBLE = registerPebble("smooth_quartz_pebble", PebbleBlocks.SMOOTH_QUARTZ_PEBBLE);
        public static final RegistryObject<Item> QUARTZ_BRICKS_PEBBLE = registerPebble("quartz_bricks_pebble", PebbleBlocks.QUARTZ_BRICKS_PEBBLE);
        public static final RegistryObject<Item> QUARTZ_PILLAR_PEBBLE = registerPebble("quartz_pillar_pebble", PebbleBlocks.QUARTZ_PILLAR_PEBBLE);
        public static final RegistryObject<Item> TERRACOTTA_PEBBLE = registerPebble("terracotta_pebble", PebbleBlocks.TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> WHITE_TERRACOTTA_PEBBLE = registerPebble("white_terracotta_pebble", PebbleBlocks.WHITE_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> ORANGE_TERRACOTTA_PEBBLE = registerPebble("orange_terracotta_pebble", PebbleBlocks.ORANGE_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> MAGENTA_TERRACOTTA_PEBBLE = registerPebble("magenta_terracotta_pebble", PebbleBlocks.MAGENTA_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> LIGHT_BLUE_TERRACOTTA_PEBBLE = registerPebble("light_blue_terracotta_pebble", PebbleBlocks.LIGHT_BLUE_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> YELLOW_TERRACOTTA_PEBBLE = registerPebble("yellow_terracotta_pebble", PebbleBlocks.YELLOW_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> LIME_TERRACOTTA_PEBBLE = registerPebble("lime_terracotta_pebble", PebbleBlocks.LIME_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> PINK_TERRACOTTA_PEBBLE = registerPebble("pink_terracotta_pebble", PebbleBlocks.PINK_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> GRAY_TERRACOTTA_PEBBLE = registerPebble("gray_terracotta_pebble", PebbleBlocks.GRAY_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> LIGHT_GRAY_TERRACOTTA_PEBBLE = registerPebble("light_gray_terracotta_pebble", PebbleBlocks.LIGHT_GRAY_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> CYAN_TERRACOTTA_PEBBLE = registerPebble("cyan_terracotta_pebble", PebbleBlocks.CYAN_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> PURPLE_TERRACOTTA_PEBBLE = registerPebble("purple_terracotta_pebble", PebbleBlocks.PURPLE_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> BLUE_TERRACOTTA_PEBBLE = registerPebble("blue_terracotta_pebble", PebbleBlocks.BLUE_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> BROWN_TERRACOTTA_PEBBLE = registerPebble("brown_terracotta_pebble", PebbleBlocks.BROWN_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> GREEN_TERRACOTTA_PEBBLE = registerPebble("green_terracotta_pebble", PebbleBlocks.GREEN_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> RED_TERRACOTTA_PEBBLE = registerPebble("red_terracotta_pebble", PebbleBlocks.RED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> BLACK_TERRACOTTA_PEBBLE = registerPebble("black_terracotta_pebble", PebbleBlocks.BLACK_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> WHITE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("white_glazed_terracotta_pebble", PebbleBlocks.WHITE_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> ORANGE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("orange_glazed_terracotta_pebble", PebbleBlocks.ORANGE_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> MAGENTA_GLAZED_TERRACOTTA_PEBBLE = registerPebble("magenta_glazed_terracotta_pebble", PebbleBlocks.MAGENTA_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> LIGHT_BLUE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("light_blue_glazed_terracotta_pebble", PebbleBlocks.LIGHT_BLUE_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> YELLOW_GLAZED_TERRACOTTA_PEBBLE = registerPebble("yellow_glazed_terracotta_pebble", PebbleBlocks.YELLOW_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> LIME_GLAZED_TERRACOTTA_PEBBLE = registerPebble("lime_glazed_terracotta_pebble", PebbleBlocks.LIME_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> PINK_GLAZED_TERRACOTTA_PEBBLE = registerPebble("pink_glazed_terracotta_pebble", PebbleBlocks.PINK_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> GRAY_GLAZED_TERRACOTTA_PEBBLE = registerPebble("gray_glazed_terracotta_pebble", PebbleBlocks.GRAY_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> LIGHT_GRAY_GLAZED_TERRACOTTA_PEBBLE = registerPebble("light_gray_glazed_terracotta_pebble", PebbleBlocks.LIGHT_GRAY_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> CYAN_GLAZED_TERRACOTTA_PEBBLE = registerPebble("cyan_glazed_terracotta_pebble", PebbleBlocks.CYAN_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> PURPLE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("purple_glazed_terracotta_pebble", PebbleBlocks.PURPLE_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> BLUE_GLAZED_TERRACOTTA_PEBBLE = registerPebble("blue_glazed_terracotta_pebble", PebbleBlocks.BLUE_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> BROWN_GLAZED_TERRACOTTA_PEBBLE = registerPebble("brown_glazed_terracotta_pebble", PebbleBlocks.BROWN_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> GREEN_GLAZED_TERRACOTTA_PEBBLE = registerPebble("green_glazed_terracotta_pebble", PebbleBlocks.GREEN_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> RED_GLAZED_TERRACOTTA_PEBBLE = registerPebble("red_glazed_terracotta_pebble", PebbleBlocks.RED_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> BLACK_GLAZED_TERRACOTTA_PEBBLE = registerPebble("black_glazed_terracotta_pebble", PebbleBlocks.BLACK_GLAZED_TERRACOTTA_PEBBLE);
        public static final RegistryObject<Item> WHITE_CONCRETE_PEBBLE = registerPebble("white_concrete_pebble", PebbleBlocks.WHITE_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> ORANGE_CONCRETE_PEBBLE = registerPebble("orange_concrete_pebble", PebbleBlocks.ORANGE_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> MAGENTA_CONCRETE_PEBBLE = registerPebble("magenta_concrete_pebble", PebbleBlocks.MAGENTA_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> LIGHT_BLUE_CONCRETE_PEBBLE = registerPebble("light_blue_concrete_pebble", PebbleBlocks.LIGHT_BLUE_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> YELLOW_CONCRETE_PEBBLE = registerPebble("yellow_concrete_pebble", PebbleBlocks.YELLOW_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> LIME_CONCRETE_PEBBLE = registerPebble("lime_concrete_pebble", PebbleBlocks.LIME_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> PINK_CONCRETE_PEBBLE = registerPebble("pink_concrete_pebble", PebbleBlocks.PINK_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> GRAY_CONCRETE_PEBBLE = registerPebble("gray_concrete_pebble", PebbleBlocks.GRAY_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> LIGHT_GRAY_CONCRETE_PEBBLE = registerPebble("light_gray_concrete_pebble", PebbleBlocks.LIGHT_GRAY_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> CYAN_CONCRETE_PEBBLE = registerPebble("cyan_concrete_pebble", PebbleBlocks.CYAN_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> PURPLE_CONCRETE_PEBBLE = registerPebble("purple_concrete_pebble", PebbleBlocks.PURPLE_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> BLUE_CONCRETE_PEBBLE = registerPebble("blue_concrete_pebble", PebbleBlocks.BLUE_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> BROWN_CONCRETE_PEBBLE = registerPebble("brown_concrete_pebble", PebbleBlocks.BROWN_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> GREEN_CONCRETE_PEBBLE = registerPebble("green_concrete_pebble", PebbleBlocks.GREEN_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> RED_CONCRETE_PEBBLE = registerPebble("red_concrete_pebble", PebbleBlocks.RED_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> BLACK_CONCRETE_PEBBLE = registerPebble("black_concrete_pebble", PebbleBlocks.BLACK_CONCRETE_PEBBLE);
        public static final RegistryObject<Item> CALCITE_PEBBLE = registerPebble("calcite_pebble", PebbleBlocks.CALCITE_PEBBLE);
        public static final RegistryObject<Item> TUFF_PEBBLE = registerPebble("tuff_pebble", PebbleBlocks.TUFF_PEBBLE);
        public static final RegistryObject<Item> DRIPSTONE_PEBBLE = registerPebble("dripstone_pebble", PebbleBlocks.DRIPSTONE_PEBBLE);
        public static final RegistryObject<Item> OBSIDIAN_PEBBLE = registerPebble("obsidian_pebble", PebbleBlocks.OBSIDIAN_PEBBLE);
        public static final RegistryObject<Item> CRYING_OBSIDIAN_PEBBLE = registerPebble("crying_obsidian_pebble", PebbleBlocks.CRYING_OBSIDIAN_PEBBLE);
        //public static final RegistryObject<Item> GLOWING_OBSIDIAN_PEBBLE = registerPebble("glowing_obsidian_pebble", PebbleBlocks.GLOWING_OBSIDIAN_PEBBLE);
        //public static final RegistryObject<Item> WARPED_NETHER_BRICKS_PEBBLE = registerPebble("warped_nether_bricks_pebble", PebbleBlocks.WARPED_NETHER_BRICKS_PEBBLE);
        public static final RegistryObject<Item> MARBLE_PEBBLE = registerPebble("marble_pebble", PebbleBlocks.MARBLE_PEBBLE);
        public static final RegistryObject<Item> WHITE_MARBLE_PEBBLE = registerPebble("white_marble_pebble", PebbleBlocks.WHITE_MARBLE_PEBBLE);
        public static final RegistryObject<Item> ORANGE_MARBLE_PEBBLE = registerPebble("orange_marble_pebble", PebbleBlocks.ORANGE_MARBLE_PEBBLE);
        public static final RegistryObject<Item> MAGENTA_MARBLE_PEBBLE = registerPebble("magenta_marble_pebble", PebbleBlocks.MAGENTA_MARBLE_PEBBLE);
        public static final RegistryObject<Item> LIGHT_BLUE_MARBLE_PEBBLE = registerPebble("light_blue_marble_pebble", PebbleBlocks.LIGHT_BLUE_MARBLE_PEBBLE);
        public static final RegistryObject<Item> YELLOW_MARBLE_PEBBLE = registerPebble("yellow_marble_pebble", PebbleBlocks.YELLOW_MARBLE_PEBBLE);
        public static final RegistryObject<Item> LIME_MARBLE_PEBBLE = registerPebble("lime_marble_pebble", PebbleBlocks.LIME_MARBLE_PEBBLE);
        public static final RegistryObject<Item> PINK_MARBLE_PEBBLE = registerPebble("pink_marble_pebble", PebbleBlocks.PINK_MARBLE_PEBBLE);
        public static final RegistryObject<Item> GRAY_MARBLE_PEBBLE = registerPebble("gray_marble_pebble", PebbleBlocks.GRAY_MARBLE_PEBBLE);
        public static final RegistryObject<Item> LIGHT_GRAY_MARBLE_PEBBLE = registerPebble("light_gray_marble_pebble", PebbleBlocks.LIGHT_GRAY_MARBLE_PEBBLE);
        public static final RegistryObject<Item> CYAN_MARBLE_PEBBLE = registerPebble("cyan_marble_pebble", PebbleBlocks.CYAN_MARBLE_PEBBLE);
        public static final RegistryObject<Item> PURPLE_MARBLE_PEBBLE = registerPebble("purple_marble_pebble", PebbleBlocks.PURPLE_MARBLE_PEBBLE);
        public static final RegistryObject<Item> BLUE_MARBLE_PEBBLE = registerPebble("blue_marble_pebble", PebbleBlocks.BLUE_MARBLE_PEBBLE);
        public static final RegistryObject<Item> BROWN_MARBLE_PEBBLE = registerPebble("brown_marble_pebble", PebbleBlocks.BROWN_MARBLE_PEBBLE);
        public static final RegistryObject<Item> GREEN_MARBLE_PEBBLE = registerPebble("green_marble_pebble", PebbleBlocks.GREEN_MARBLE_PEBBLE);
        public static final RegistryObject<Item> RED_MARBLE_PEBBLE = registerPebble("red_marble_pebble", PebbleBlocks.RED_MARBLE_PEBBLE);
        public static final RegistryObject<Item> BLACK_MARBLE_PEBBLE = registerPebble("black_marble_pebble", PebbleBlocks.BLACK_MARBLE_PEBBLE);

        //#endregion

        //#region Methods

        /**
         * Register a {@link PebbleItem Pebble}
         *
         * @param name {@link String The Pebble Item Name}
         * @param pebbleBlockSupplier {@link Supplier < Block > The Pebble Block supplier}
         * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
         * @return {@link RegistryObject<Item> The registered Pebble}
         */
        private static RegistryObject<Item> registerPebble(final String name, final Supplier<Block> pebbleBlockSupplier, final FeatureFlag... featureFlags) {
            return MWItems.registerItem(name, () -> new PebbleItem(pebbleBlockSupplier, featureFlags));
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