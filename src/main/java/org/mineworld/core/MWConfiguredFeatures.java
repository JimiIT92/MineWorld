package org.mineworld.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegistryHelper;

/**
 * {@link MineWorld MineWorld} {@link ConfiguredFeature Configured Features}
 */
public final class MWConfiguredFeatures {

    //#region Configured Features

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SILVER = registerConfiguredFeatureKey("ore_silver");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SILVER_SMALL = registerConfiguredFeatureKey("ore_silver_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ALUMINUM = registerConfiguredFeatureKey("ore_aluminum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ALUMINUM_SMALL = registerConfiguredFeatureKey("ore_aluminum_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY = registerConfiguredFeatureKey("ore_ruby");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE = registerConfiguredFeatureKey("ore_sapphire");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PYRITE = registerConfiguredFeatureKey("ore_pyrite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_MARBLE = registerConfiguredFeatureKey("ore_marble");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_BLUEBERRY_BUSH = registerConfiguredFeatureKey("patch_blueberry_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRING_LAVA_VOLCANIC_PEAK = registerConfiguredFeatureKey("spring_lava_volcanic_peak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAGMA_STONE_BLOBS = registerConfiguredFeatureKey("magma_stone_blobs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAGMA_BLACKSTONE_BLOBS = registerConfiguredFeatureKey("magma_blackstone_blobs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COAL_STONE_BLOBS = registerConfiguredFeatureKey("coal_stone_blobs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COAL_BLACKSTONE_BLOBS = registerConfiguredFeatureKey("coal_blackstone_blobs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVA_ROCK_STONE_BLOBS = registerConfiguredFeatureKey("lava_rock_stone_blobs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVA_ROCK_BLACKSTONE_BLOBS = registerConfiguredFeatureKey("lava_rock_blackstone_blobs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAKE_LAVA_VOLCANIC_PEAK = registerConfiguredFeatureKey("lake_lava_volcanic_peak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAKE_LAVA_MAGMA = registerConfiguredFeatureKey("lake_lava_magma");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAKE_LAVA_BLACKSTONE = registerConfiguredFeatureKey("lake_lava_blackstone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAKE_LAVA_COAL = registerConfiguredFeatureKey("lake_lava_coal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_TREE = registerConfiguredFeatureKey("apple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_BEES_TREE = registerConfiguredFeatureKey("apple_bees_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_APPLE_TREE = registerConfiguredFeatureKey("fancy_apple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_APPLE_BEES_TREE = registerConfiguredFeatureKey("fancy_apple_bees_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM_TREE = registerConfiguredFeatureKey("palm_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_TREE = registerConfiguredFeatureKey("dead_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_DEAD_TREE = registerConfiguredFeatureKey("fancy_dead_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SCULK_TREE = registerConfiguredFeatureKey("sculk_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BONE_SPIKE = registerConfiguredFeatureKey("bone_spike");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_OAK_TREE = registerConfiguredFeatureKey("fallen_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_OAK_TREE_SWAMP = registerConfiguredFeatureKey("fallen_oak_tree_swamp");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_BIRCH_TREE = registerConfiguredFeatureKey("fallen_birch_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_SPRUCE_TREE = registerConfiguredFeatureKey("fallen_spruce_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_JUNGLE_TREE = registerConfiguredFeatureKey("fallen_jungle_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_ACACIA_TREE = registerConfiguredFeatureKey("fallen_acacia_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_DARK_OAK_TREE = registerConfiguredFeatureKey("fallen_dark_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_MANGROVE_TREE = registerConfiguredFeatureKey("fallen_mangrove_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_CHERRY_TREE = registerConfiguredFeatureKey("fallen_cherry_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_APPLE_TREE = registerConfiguredFeatureKey("fallen_apple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_PALM_TREE = registerConfiguredFeatureKey("fallen_palm_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_DEAD_TREE = registerConfiguredFeatureKey("fallen_dead_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CATTAIL = registerConfiguredFeatureKey("cattail");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ETHEREAL_FIRE = registerConfiguredFeatureKey("patch_ethereal_fire");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRING_WATER_SCULK = registerConfiguredFeatureKey("spring_water_sculk");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_SCULK_ROOTS = registerConfiguredFeatureKey("patch_sculk_roots");

    //#endregion

    //#region Methods

    /**
     * Register a {@link ConfiguredFeature Configured Feature} {@link ResourceKey<ConfiguredFeature> Resource Key}
     *
     * @param name {@link String The Configured Feature name}
     * @return {@link ResourceKey<ConfiguredFeature> The Configured Feature Resource Key}
     */
    private static ResourceKey<ConfiguredFeature<?, ?>> registerConfiguredFeatureKey(final String name) {
        return RegistryHelper.register(Registries.CONFIGURED_FEATURE, name);
    }

    //#endregion

    //#region Register

    /**
     * Register all {@link ConfiguredFeature Configured Features}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The Bootstrap Context}
     */
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

    }

    //#endregion

}