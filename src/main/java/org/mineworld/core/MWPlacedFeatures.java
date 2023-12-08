package org.mineworld.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import org.mineworld.MineWorld;
import org.mineworld.helper.KeyHelper;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.helper.RegisterHelper;

import java.util.List;

/**
 * {@link MineWorld MineWorld} {@link PlacedFeature placed features}
 */
public final class MWPlacedFeatures {

    public static final ResourceKey<PlacedFeature> ORE_SILVER_UPPER = KeyHelper.registerPlacedFeatureKey("ore_silver_upper");
    public static final ResourceKey<PlacedFeature> ORE_SILVER_MIDDLE = KeyHelper.registerPlacedFeatureKey("ore_silver_middle");
    public static final ResourceKey<PlacedFeature> ORE_SILVER_SMALL = KeyHelper.registerPlacedFeatureKey("ore_silver_small");
    public static final ResourceKey<PlacedFeature> ORE_ALUMINUM_UPPER = KeyHelper.registerPlacedFeatureKey("ore_aluminum_upper");
    public static final ResourceKey<PlacedFeature> ORE_ALUMINUM_MIDDLE = KeyHelper.registerPlacedFeatureKey("ore_aluminum_middle");
    public static final ResourceKey<PlacedFeature> ORE_ALUMINUM_SMALL = KeyHelper.registerPlacedFeatureKey("ore_aluminum_small");
    public static final ResourceKey<PlacedFeature> ORE_RUBY = KeyHelper.registerPlacedFeatureKey("ore_ruby");
    public static final ResourceKey<PlacedFeature> ORE_SAPPHIRE = KeyHelper.registerPlacedFeatureKey("ore_sapphire");
    public static final ResourceKey<PlacedFeature> ORE_PYRITE = KeyHelper.registerPlacedFeatureKey("ore_pyrite");
    public static final ResourceKey<PlacedFeature> ORE_MARBLE = KeyHelper.registerPlacedFeatureKey("ore_marble");
    public static final ResourceKey<PlacedFeature> PATCH_BLUEBERRY_BUSH_VILLAGE = KeyHelper.registerPlacedFeatureKey("patch_blueberry_bush");
    public static final ResourceKey<PlacedFeature> PATCH_BLUEBERRY_COMMON = KeyHelper.registerPlacedFeatureKey("patch_blueberry_common");
    public static final ResourceKey<PlacedFeature> PATCH_BLUEBERRY_RARE = KeyHelper.registerPlacedFeatureKey("patch_blueberry_rare");
    public static final ResourceKey<PlacedFeature> SPRING_LAVA_VOLCANIC_PEAK = KeyHelper.registerPlacedFeatureKey("spring_lava_volcanic_peak");
    public static final ResourceKey<PlacedFeature> MAGMA_STONE_BLOBS = KeyHelper.registerPlacedFeatureKey("magma_stone_blobs");
    public static final ResourceKey<PlacedFeature> MAGMA_BLACKSTONE_BLOBS = KeyHelper.registerPlacedFeatureKey("magma_blackstone_blobs");
    public static final ResourceKey<PlacedFeature> COAL_STONE_BLOBS = KeyHelper.registerPlacedFeatureKey("coal_stone_blobs");
    public static final ResourceKey<PlacedFeature> COAL_BLACKSTONE_BLOBS = KeyHelper.registerPlacedFeatureKey("coal_blackstone_blobs");
    public static final ResourceKey<PlacedFeature> LAVA_ROCK_STONE_BLOBS = KeyHelper.registerPlacedFeatureKey("lava_rock_stone_blobs");
    public static final ResourceKey<PlacedFeature> LAVA_ROCK_BLACKSTONE_BLOBS = KeyHelper.registerPlacedFeatureKey("lava_rock_blackstone_blobs");
    public static final ResourceKey<PlacedFeature> LAKE_LAVA_VOLCANIC_PEAK = KeyHelper.registerPlacedFeatureKey("lake_lava_volcanic_peak");
    public static final ResourceKey<PlacedFeature> LAKE_LAVA_MAGMA = KeyHelper.registerPlacedFeatureKey("lake_lava_magma");
    public static final ResourceKey<PlacedFeature> LAKE_LAVA_BLACKSTONE = KeyHelper.registerPlacedFeatureKey("lake_lava_blackstone");
    public static final ResourceKey<PlacedFeature> LAKE_LAVA_COAL = KeyHelper.registerPlacedFeatureKey("lake_lava_coal");
    public static final ResourceKey<PlacedFeature> APPLE_TREE = KeyHelper.registerPlacedFeatureKey("apple_tree");
    public static final ResourceKey<PlacedFeature> APPLE_BEES_TREE = KeyHelper.registerPlacedFeatureKey("apple_bees_tree");
    public static final ResourceKey<PlacedFeature> FANCY_APPLE_TREE = KeyHelper.registerPlacedFeatureKey("fancy_apple_tree");
    public static final ResourceKey<PlacedFeature> FANCY_APPLE_BEES_TREE = KeyHelper.registerPlacedFeatureKey("fancy_apple_bees_tree");
    public static final ResourceKey<PlacedFeature> DEAD_TREE = KeyHelper.registerPlacedFeatureKey("dead_tree");
    public static final ResourceKey<PlacedFeature> FANCY_DEAD_TREE = KeyHelper.registerPlacedFeatureKey("fancy_dead_tree");
    public static final ResourceKey<PlacedFeature> PALM_TREE = KeyHelper.registerPlacedFeatureKey("palm_tree");
    public static final ResourceKey<PlacedFeature> SCULK_TREE = KeyHelper.registerPlacedFeatureKey("sculk_tree");
    public static final ResourceKey<PlacedFeature> BONE_SPIKE = KeyHelper.registerPlacedFeatureKey("bone_spike");
    public static final ResourceKey<PlacedFeature> FALLEN_OAK_TREE = KeyHelper.registerPlacedFeatureKey("fallen_oak_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_OAK_TREE_SWAMP = KeyHelper.registerPlacedFeatureKey("fallen_oak_tree_swamp");
    public static final ResourceKey<PlacedFeature> FALLEN_BIRCH_TREE = KeyHelper.registerPlacedFeatureKey("fallen_birch_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_SPRUCE_TREE = KeyHelper.registerPlacedFeatureKey("fallen_spruce_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_JUNGLE_TREE = KeyHelper.registerPlacedFeatureKey("fallen_jungle_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_ACACIA_TREE = KeyHelper.registerPlacedFeatureKey("fallen_acacia_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_DARK_OAK_TREE = KeyHelper.registerPlacedFeatureKey("fallen_dark_oak_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_MANGROVE_TREE = KeyHelper.registerPlacedFeatureKey("fallen_mangrove_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_CHERRY_TREE = KeyHelper.registerPlacedFeatureKey("fallen_cherry_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_APPLE_TREE = KeyHelper.registerPlacedFeatureKey("fallen_apple_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_PALM_TREE = KeyHelper.registerPlacedFeatureKey("fallen_palm_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_DEAD_TREE = KeyHelper.registerPlacedFeatureKey("fallen_dead_tree");
    public static final ResourceKey<PlacedFeature> CATTAIL = KeyHelper.registerPlacedFeatureKey("cattail");
    public static final ResourceKey<PlacedFeature> PATCH_ETHEREAL_FIRE = KeyHelper.registerPlacedFeatureKey("patch_ethereal_fire");
    public static final ResourceKey<PlacedFeature> SPRING_WATER_SCULK = KeyHelper.registerPlacedFeatureKey("spring_water_sculk");
    public static final ResourceKey<PlacedFeature> PATCH_SCULK_ROOTS = KeyHelper.registerPlacedFeatureKey("patch_sculk_roots");

    /**
     * Register the {@link PlacedFeature placed features}
     *
     * @param context {@link BootstapContext<PlacedFeature> The bootstrap context}
     */
    public static void bootstrap(final BootstapContext<PlacedFeature> context) {
        final HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        RegisterHelper.registerCommonOrePlacedFeature(context, ORE_SILVER_UPPER, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_SILVER), 90, 80, 384);
        RegisterHelper.registerCommonOrePlacedFeature(context, ORE_SILVER_MIDDLE, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_SILVER), 10, -24, 56);
        RegisterHelper.registerCommonOrePlacedFeature(context, ORE_SILVER_SMALL, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_SILVER_SMALL), 10, 72);
        RegisterHelper.registerCommonOrePlacedFeature(context, ORE_ALUMINUM_UPPER, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_ALUMINUM), 90, 80, 384);
        RegisterHelper.registerCommonOrePlacedFeature(context, ORE_ALUMINUM_MIDDLE, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_ALUMINUM), 10, -24, 56);
        RegisterHelper.registerCommonOrePlacedFeature(context, ORE_ALUMINUM_SMALL, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_ALUMINUM_SMALL), 10, 72);
        RegisterHelper.registerCommonOrePlacedFeature(context, ORE_RUBY, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_RUBY), 100, -16, 480);
        RegisterHelper.registerCommonOrePlacedFeature(context, ORE_SAPPHIRE, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_SAPPHIRE), 100, -16, 480);
        RegisterHelper.registerCommonOrePlacedFeature(context, ORE_MARBLE, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_MARBLE), 2, -16);
        RegisterHelper.registerPlacedFeature(context, ORE_PYRITE, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_PYRITE), PropertyHelper.commonOrePlacement(10, PlacementUtils.RANGE_10_10));
        RegisterHelper.registerPatchPlacedFeature(context, PATCH_BLUEBERRY_COMMON, configuredFeatures.getOrThrow(MWConfiguredFeatures.PATCH_BLUEBERRY_BUSH), 32);
        RegisterHelper.registerPatchPlacedFeature(context, PATCH_BLUEBERRY_RARE, configuredFeatures.getOrThrow(MWConfiguredFeatures.PATCH_BLUEBERRY_BUSH), 384);
        RegisterHelper.registerPlacedFeature(context, PATCH_BLUEBERRY_BUSH_VILLAGE, configuredFeatures.getOrThrow(MWConfiguredFeatures.PATCH_BLUEBERRY_BUSH));
        RegisterHelper.registerPlacedFeature(context, SPRING_LAVA_VOLCANIC_PEAK, configuredFeatures.getOrThrow(MWConfiguredFeatures.SPRING_LAVA_VOLCANIC_PEAK),
                List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, MAGMA_STONE_BLOBS, configuredFeatures.getOrThrow(MWConfiguredFeatures.MAGMA_STONE_BLOBS),
                List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, MAGMA_BLACKSTONE_BLOBS, configuredFeatures.getOrThrow(MWConfiguredFeatures.MAGMA_BLACKSTONE_BLOBS),
                List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, COAL_STONE_BLOBS, configuredFeatures.getOrThrow(MWConfiguredFeatures.COAL_STONE_BLOBS),
                List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, COAL_BLACKSTONE_BLOBS, configuredFeatures.getOrThrow(MWConfiguredFeatures.COAL_BLACKSTONE_BLOBS),
                List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, LAVA_ROCK_STONE_BLOBS, configuredFeatures.getOrThrow(MWConfiguredFeatures.LAVA_ROCK_STONE_BLOBS),
                List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, LAVA_ROCK_BLACKSTONE_BLOBS, configuredFeatures.getOrThrow(MWConfiguredFeatures.LAVA_ROCK_BLACKSTONE_BLOBS),
                List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, LAKE_LAVA_VOLCANIC_PEAK, configuredFeatures.getOrThrow(MWConfiguredFeatures.LAKE_LAVA_VOLCANIC_PEAK),
                List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, LAKE_LAVA_MAGMA, configuredFeatures.getOrThrow(MWConfiguredFeatures.LAKE_LAVA_MAGMA),
                List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, LAKE_LAVA_BLACKSTONE, configuredFeatures.getOrThrow(MWConfiguredFeatures.LAKE_LAVA_BLACKSTONE),
                List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, LAKE_LAVA_COAL, configuredFeatures.getOrThrow(MWConfiguredFeatures.LAKE_LAVA_COAL),
                List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, APPLE_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.APPLE_TREE),
                VegetationPlacements.treePlacement(CountPlacement.of(1), MWBlocks.APPLE_SAPLING.get()));
        RegisterHelper.registerPlacedFeature(context, APPLE_BEES_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.APPLE_BEES_TREE),
                VegetationPlacements.treePlacement(CountPlacement.of(1), MWBlocks.APPLE_SAPLING.get()));
        RegisterHelper.registerPlacedFeature(context, FANCY_APPLE_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FANCY_APPLE_TREE),
                VegetationPlacements.treePlacement(CountPlacement.of(1), MWBlocks.APPLE_SAPLING.get()));
        RegisterHelper.registerPlacedFeature(context, FANCY_APPLE_BEES_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FANCY_APPLE_BEES_TREE),
                VegetationPlacements.treePlacement(CountPlacement.of(1), MWBlocks.APPLE_SAPLING.get()));
        RegisterHelper.registerPlacedFeature(context, PALM_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.PALM_TREE),
                VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(5), MWBlocks.PALM_SAPLING.get()));
        RegisterHelper.registerPlacedFeature(context, DEAD_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.DEAD_TREE),
                VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(1), Blocks.OAK_SAPLING));
        RegisterHelper.registerPlacedFeature(context, FANCY_DEAD_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FANCY_DEAD_TREE),
                VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(1), Blocks.OAK_SAPLING));
        RegisterHelper.registerPlacedFeature(context, SCULK_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.SCULK_TREE),
                List.of(CountOnEveryLayerPlacement.of(4), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive( MWBlocks.SCULK_SAPLING.get().defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, BONE_SPIKE, configuredFeatures.getOrThrow(MWConfiguredFeatures.BONE_SPIKE),
                List.of(RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, FALLEN_OAK_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_OAK_TREE),
                List.of(RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, FALLEN_OAK_TREE_SWAMP, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_OAK_TREE_SWAMP),
                List.of(RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, FALLEN_BIRCH_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_BIRCH_TREE),
                List.of(RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, FALLEN_SPRUCE_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_SPRUCE_TREE),
                List.of(RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, FALLEN_JUNGLE_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_JUNGLE_TREE),
                List.of(RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, FALLEN_ACACIA_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_ACACIA_TREE),
                List.of(RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, FALLEN_DARK_OAK_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_DARK_OAK_TREE),
                List.of(RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, FALLEN_MANGROVE_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_MANGROVE_TREE),
                List.of(RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, FALLEN_CHERRY_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_CHERRY_TREE),
                List.of(RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, FALLEN_APPLE_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_APPLE_TREE),
                List.of(RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, FALLEN_PALM_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_PALM_TREE),
                List.of(RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, FALLEN_DEAD_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_DEAD_TREE),
                List.of(RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, CATTAIL, configuredFeatures.getOrThrow(MWConfiguredFeatures.CATTAIL),
                List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, CountPlacement.of(100), BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, PATCH_ETHEREAL_FIRE, configuredFeatures.getOrThrow(MWConfiguredFeatures.PATCH_ETHEREAL_FIRE),
                List.of(CountPlacement.of(UniformInt.of(0, 5)), InSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, SPRING_WATER_SCULK, configuredFeatures.getOrThrow(MWConfiguredFeatures.SPRING_WATER_SCULK),
                List.of(CountPlacement.of(10), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        RegisterHelper.registerPlacedFeature(context, PATCH_SCULK_ROOTS, configuredFeatures.getOrThrow(MWConfiguredFeatures.PATCH_SCULK_ROOTS),
                List.of(CountOnEveryLayerPlacement.of(4), BiomeFilter.biome()));
    }

}