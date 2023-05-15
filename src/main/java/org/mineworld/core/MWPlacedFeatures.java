package org.mineworld.core;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
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
    public static final ResourceKey<PlacedFeature> PATCH_BLUEBERRY_BUSH_VILLAGE = KeyHelper.registerPlacedFeatureKey("patch_blueberry_bush.json");
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
    }

}