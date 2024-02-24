package org.mineworld.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegistryHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link MineWorld MineWorld} {@link PlacedFeature Placed Features}
 */
public final class MWPlacedFeatures {

    //#region Placed Features

    public static final ResourceKey<PlacedFeature> ORE_SILVER_UPPER = registerPlacedFeatureKey("ore_silver_upper");
    public static final ResourceKey<PlacedFeature> ORE_SILVER_MIDDLE = registerPlacedFeatureKey("ore_silver_middle");
    public static final ResourceKey<PlacedFeature> ORE_SILVER_SMALL = registerPlacedFeatureKey("ore_silver_small");
    public static final ResourceKey<PlacedFeature> ORE_ALUMINUM_UPPER = registerPlacedFeatureKey("ore_aluminum_upper");
    public static final ResourceKey<PlacedFeature> ORE_ALUMINUM_MIDDLE = registerPlacedFeatureKey("ore_aluminum_middle");
    public static final ResourceKey<PlacedFeature> ORE_ALUMINUM_SMALL = registerPlacedFeatureKey("ore_aluminum_small");
    public static final ResourceKey<PlacedFeature> ORE_RUBY = registerPlacedFeatureKey("ore_ruby");
    public static final ResourceKey<PlacedFeature> ORE_SAPPHIRE = registerPlacedFeatureKey("ore_sapphire");
    public static final ResourceKey<PlacedFeature> ORE_PYRITE = registerPlacedFeatureKey("ore_pyrite");
    public static final ResourceKey<PlacedFeature> ORE_MARBLE = registerPlacedFeatureKey("ore_marble");
    public static final ResourceKey<PlacedFeature> PATCH_BLUEBERRY_BUSH_VILLAGE = registerPlacedFeatureKey("patch_blueberry_bush");
    public static final ResourceKey<PlacedFeature> PATCH_BLUEBERRY_COMMON = registerPlacedFeatureKey("patch_blueberry_common");
    public static final ResourceKey<PlacedFeature> PATCH_BLUEBERRY_RARE = registerPlacedFeatureKey("patch_blueberry_rare");
    public static final ResourceKey<PlacedFeature> SPRING_LAVA_VOLCANIC_PEAK = registerPlacedFeatureKey("spring_lava_volcanic_peak");
    public static final ResourceKey<PlacedFeature> MAGMA_STONE_BLOBS = registerPlacedFeatureKey("magma_stone_blobs");
    public static final ResourceKey<PlacedFeature> MAGMA_BLACKSTONE_BLOBS = registerPlacedFeatureKey("magma_blackstone_blobs");
    public static final ResourceKey<PlacedFeature> COAL_STONE_BLOBS = registerPlacedFeatureKey("coal_stone_blobs");
    public static final ResourceKey<PlacedFeature> COAL_BLACKSTONE_BLOBS = registerPlacedFeatureKey("coal_blackstone_blobs");
    public static final ResourceKey<PlacedFeature> LAVA_ROCK_STONE_BLOBS = registerPlacedFeatureKey("lava_rock_stone_blobs");
    public static final ResourceKey<PlacedFeature> LAVA_ROCK_BLACKSTONE_BLOBS = registerPlacedFeatureKey("lava_rock_blackstone_blobs");
    public static final ResourceKey<PlacedFeature> LAKE_LAVA_VOLCANIC_PEAK = registerPlacedFeatureKey("lake_lava_volcanic_peak");
    public static final ResourceKey<PlacedFeature> LAKE_LAVA_MAGMA = registerPlacedFeatureKey("lake_lava_magma");
    public static final ResourceKey<PlacedFeature> LAKE_LAVA_BLACKSTONE = registerPlacedFeatureKey("lake_lava_blackstone");
    public static final ResourceKey<PlacedFeature> LAKE_LAVA_COAL = registerPlacedFeatureKey("lake_lava_coal");
    public static final ResourceKey<PlacedFeature> APPLE_TREE = registerPlacedFeatureKey("apple_tree");
    public static final ResourceKey<PlacedFeature> APPLE_BEES_TREE = registerPlacedFeatureKey("apple_bees_tree");
    public static final ResourceKey<PlacedFeature> FANCY_APPLE_TREE = registerPlacedFeatureKey("fancy_apple_tree");
    public static final ResourceKey<PlacedFeature> FANCY_APPLE_BEES_TREE = registerPlacedFeatureKey("fancy_apple_bees_tree");
    public static final ResourceKey<PlacedFeature> DEAD_TREE = registerPlacedFeatureKey("dead_tree");
    public static final ResourceKey<PlacedFeature> FANCY_DEAD_TREE = registerPlacedFeatureKey("fancy_dead_tree");
    public static final ResourceKey<PlacedFeature> PALM_TREE = registerPlacedFeatureKey("palm_tree");
    public static final ResourceKey<PlacedFeature> SCULK_TREE = registerPlacedFeatureKey("sculk_tree");
    public static final ResourceKey<PlacedFeature> BONE_SPIKE = registerPlacedFeatureKey("bone_spike");
    public static final ResourceKey<PlacedFeature> FALLEN_OAK_TREE = registerPlacedFeatureKey("fallen_oak_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_OAK_TREE_SWAMP = registerPlacedFeatureKey("fallen_oak_tree_swamp");
    public static final ResourceKey<PlacedFeature> FALLEN_BIRCH_TREE = registerPlacedFeatureKey("fallen_birch_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_SPRUCE_TREE = registerPlacedFeatureKey("fallen_spruce_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_JUNGLE_TREE = registerPlacedFeatureKey("fallen_jungle_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_ACACIA_TREE = registerPlacedFeatureKey("fallen_acacia_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_DARK_OAK_TREE = registerPlacedFeatureKey("fallen_dark_oak_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_MANGROVE_TREE = registerPlacedFeatureKey("fallen_mangrove_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_CHERRY_TREE = registerPlacedFeatureKey("fallen_cherry_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_APPLE_TREE = registerPlacedFeatureKey("fallen_apple_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_PALM_TREE = registerPlacedFeatureKey("fallen_palm_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_DEAD_TREE = registerPlacedFeatureKey("fallen_dead_tree");
    public static final ResourceKey<PlacedFeature> CATTAIL = registerPlacedFeatureKey("cattail");
    public static final ResourceKey<PlacedFeature> PATCH_ETHEREAL_FIRE = registerPlacedFeatureKey("patch_ethereal_fire");
    public static final ResourceKey<PlacedFeature> SPRING_WATER_SCULK = registerPlacedFeatureKey("spring_water_sculk");
    public static final ResourceKey<PlacedFeature> PATCH_SCULK_ROOTS = registerPlacedFeatureKey("patch_sculk_roots");

    //#endregion

    //#region Methods

    /**
     * Register a {@link PlacedFeature Placed Feature} {@link ResourceKey<PlacedFeature> Resource Key}
     *
     * @param name {@link String The Placed Feature name}
     * @return {@link ResourceKey<PlacedFeature> The Placed Feature Resource Key}
     */
    private static ResourceKey<PlacedFeature> registerPlacedFeatureKey(final String name) {
        return RegistryHelper.register(Registries.PLACED_FEATURE, name + "_placed");
    }

    /**
     * Register a common ore using the {@link HeightRangePlacement#triangle triangle height range placement}
     * with the {@link VerticalAnchor#absolute absolute values set}
     *
     * @param context {@link BootstapContext<PlacedFeature> The Bootstrap Context}
     * @param key {@link ResourceKey<PlacedFeature> The Placed Feature Resource Key}
     * @param oreConfiguredFeature {@link Holder<ConfiguredFeature> The Ore Configured Feature that this Placed Feature will place}
     * @param count {@link Integer How many ores per chunk should be generated}
     * @param minHeight {@link Integer The minimum height that the ore can spawn}
     * @param maxHeight {@link Integer The maximum height that the ore can spawn}
     */
    private static void registerCommonOrePlacedFeature(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> oreConfiguredFeature, final int count, final int minHeight, final int maxHeight) {
        registerPlacedFeature(context, key, oreConfiguredFeature, commonOrePlacement(count, HeightRangePlacement.triangle(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight))));
    }

    /**
     * Register a common ore using the {@link HeightRangePlacement#uniform unfirom height range placement}
     * with the {@link VerticalAnchor#absolute absolute values set}
     *
     * @param context {@link BootstapContext<PlacedFeature> The Bootstrap Context}
     * @param key {@link ResourceKey<PlacedFeature> The Placed Feature Resource Key}
     * @param oreConfiguredFeature {@link Holder<ConfiguredFeature> The Ore Configured Feature that this Placed Feature will place}
     * @param count {@link Integer How many ores per chunk should be generated}
     * @param maxHeight {@link Integer The maximum height that the ore can spawn}
     */
    private static void registerCommonOrePlacedFeature(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> oreConfiguredFeature, final int count, final int maxHeight) {
        registerPlacedFeature(context, key, oreConfiguredFeature, commonOrePlacement(count, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(maxHeight))));
    }

    /**
     * Register a common ore using the {@link HeightRangePlacement#triangle triangle height range placement}
     * with the {@link VerticalAnchor#aboveBottom above bottom} and {@link VerticalAnchor#belowTop below top} values set
     *
     * @param context {@link BootstapContext<PlacedFeature> The Bootstrap Context}
     * @param key {@link ResourceKey<PlacedFeature> The Placed Feature Resource Key}
     * @param oreConfiguredFeature {@link Holder<ConfiguredFeature> The Ore Configured Feature that this Placed Feature will place}
     * @param count {@link Integer How many ores per chunk should be generated}
     * @param minHeight {@link Integer The minimum height that the ore can spawn}
     * @param maxHeight {@link Integer The maximum height that the ore can spawn}
     */
    private static void registerRareOrePlacedFeature(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> oreConfiguredFeature, final int count, final int minHeight, final int maxHeight) {
        registerPlacedFeature(context, key, oreConfiguredFeature, rareOrePlacement(count, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(minHeight), VerticalAnchor.aboveBottom(maxHeight))));
    }

    /**
     * Register a {@link PlacedFeature Patch Placed Feature}
     *
     * @param context {@link BootstapContext<PlacedFeature> The Bootstrap Context}
     * @param key {@link ResourceKey<PlacedFeature> The Placed Feature Resource Key}
     * @param configuredFeatureHolder {@link Holder<ConfiguredFeature> The Configured Feature that this Placed Feature will place}
     * @param averageCount {@link Integer The average count of blocks that will generate per Chunk}
     */
    private static void registerPatchPlacedFeature(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> configuredFeatureHolder, final int averageCount) {
        registerPlacedFeature(context, key, configuredFeatureHolder, RarityFilter.onAverageOnceEvery(averageCount), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE);
    }

    /**
     * Register a {@link PlacedFeature Spring Placed Feature}
     *
     * @param context {@link BootstapContext<PlacedFeature> The Bootstrap Context}
     * @param key {@link ResourceKey<PlacedFeature> The Placed Feature Resource Key}
     * @param configuredFeatureHolder {@link Holder<ConfiguredFeature> The Configured Feature that this Placed Feature will place}
     */
    private static void registerSpringPlacedFeature(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> configuredFeatureHolder) {
        registerBlobPlacedFeature(context, key, configuredFeatureHolder, 10);
    }

    /**
     * Register a {@link PlacedFeature Blob Placed Feature}
     *
     * @param context {@link BootstapContext<PlacedFeature> The Bootstrap Context}
     * @param key {@link ResourceKey<PlacedFeature> The Placed Feature Resource Key}
     * @param configuredFeatureHolder {@link Holder<ConfiguredFeature> The Configured Feature that this Placed Feature will place}
     * @param averageCount {@link Integer The average count of blocks that will generate per Chunk}
     */
    private static void registerBlobPlacedFeature(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> configuredFeatureHolder, final int averageCount) {
        registerPlacedFeature(context, key, configuredFeatureHolder, CountPlacement.of(averageCount), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE);
    }

    /**
     * Register a {@link PlacedFeature Lake Placed Feature}
     *
     * @param context {@link BootstapContext<PlacedFeature> The Bootstrap Context}
     * @param key {@link ResourceKey<PlacedFeature> The Placed Feature Resource Key}
     * @param configuredFeatureHolder {@link Holder<ConfiguredFeature> The Configured Feature that this Placed Feature will place}
     */
    private static void registerLakePlacedFeature(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> configuredFeatureHolder) {
        registerPlacedFeature(context, key, configuredFeatureHolder, InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE);
    }

    /**
     * Register a {@link PlacedFeature Tree Placed Feature}
     *
     * @param context {@link BootstapContext<PlacedFeature> The Bootstrap Context}
     * @param key {@link ResourceKey<PlacedFeature> The Placed Feature Resource Key}
     * @param configuredFeatureHolder {@link Holder<ConfiguredFeature> The Configured Feature that this Placed Feature will place}
     * @param count {@link Integer How many trees to generate}
     * @param saplingBlock {@link Block The Sapling Block this tree is referring to}
     */
    private static void registerTreePlacedFeature(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> configuredFeatureHolder, final int count, final Block saplingBlock) {
        registerPlacedFeature(context, key, configuredFeatureHolder, VegetationPlacements.treePlacement(CountPlacement.of(count), saplingBlock));
    }

    /**
     * Register a {@link PlacedFeature Rare Tree Placed Feature}
     *
     * @param context {@link BootstapContext<PlacedFeature> The Bootstrap Context}
     * @param key {@link ResourceKey<PlacedFeature> The Placed Feature Resource Key}
     * @param configuredFeatureHolder {@link Holder<ConfiguredFeature> The Configured Feature that this Placed Feature will place}
     * @param count {@link Integer How many trees to generate}
     * @param saplingBlock {@link Block The Sapling Block this tree is referring to}
     */
    private static void registerRareTreePlacedFeature(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> configuredFeatureHolder, final int count, final Block saplingBlock) {
        registerPlacedFeature(context, key, configuredFeatureHolder, VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(count), saplingBlock));
    }

    /**
     * Register a {@link PlacedFeature Fallen Tree Placed Feature}
     *
     * @param context {@link BootstapContext<PlacedFeature> The Bootstrap Context}
     * @param key {@link ResourceKey<PlacedFeature> The Placed Feature Resource Key}
     * @param configuredFeatureHolder {@link Holder<ConfiguredFeature> The Configured Feature that this Placed Feature will place}
     * @param count {@link Integer How many trees to generate}
     */
    private static void registerFallenTreePlacedFeature(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> configuredFeatureHolder, final int count) {
        registerPlacedFeature(context, key, configuredFeatureHolder, RarityFilter.onAverageOnceEvery(count), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR);
    }

    /**
     * Register a {@link PlacedFeature Placed Feature}
     *
     * @param context {@link BootstapContext<PlacedFeature> The Bootstrap Context}
     * @param key {@link ResourceKey<PlacedFeature> The Placed Feature Resource Key}
     * @param configuredFeatureHolder {@link Holder<ConfiguredFeature> The Configured Feature that this Placed Feature will place}
     * @param placementModifiers {@link PlacementModifier The Placement Modifiers}
     */
    private static void registerPlacedFeature(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> configuredFeatureHolder, final PlacementModifier... placementModifiers) {
        registerPlacedFeature(context, key, configuredFeatureHolder, List.of(placementModifiers));
    }

    /**
     * Register a {@link PlacedFeature Placed Feature}
     *
     * @param context {@link BootstapContext<PlacedFeature> The Bootstrap Context}
     * @param key {@link ResourceKey<PlacedFeature> The Placed Feature Resource Key}
     * @param configuredFeatureHolder {@link Holder<ConfiguredFeature> The Configured Feature that this Placed Feature will place}
     * @param placementModifiers {@link List<PlacementModifier> The Placement Modifiers}
     */
    private static void registerPlacedFeature(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> configuredFeatureHolder, List<PlacementModifier> placementModifiers) {
        if(placementModifiers == null) {
            placementModifiers = new ArrayList<>();
        }
        placementModifiers.add(BiomeFilter.biome());
        PlacementUtils.register(context, key, configuredFeatureHolder, placementModifiers);
    }

    //#region Ore Configurations

    /**
     * Get the {@link List<PlacementModifier> Ore Placement Modifier} for a Common Ore
     *
     * @param count {@link Integer How many ores per chunk should be generated}
     * @param heightPlacementModifier {@link PlacementModifier The height placement modifier. Affects the levels at which the ore can spawn}
     * @return {@link List<PlacementModifier> The Common Ore Placement Modifier}
     */
    private static List<PlacementModifier> commonOrePlacement(final int count, final PlacementModifier heightPlacementModifier) {
        return orePlacement(CountPlacement.of(count), heightPlacementModifier);
    }

    /**
     * Get the {@link List<PlacementModifier> Ore Placement Modifier} for a Rare Ore
     *
     * @param count {@link Integer How many ores per chunk should be generated}
     * @param heightPlacementModifier {@link PlacementModifier The height placement modifier. Affects the levels at which the ore can spawn}
     * @return {@link List<PlacementModifier> The Common Ore Placement Modifier}
     */
    private static List<PlacementModifier> rareOrePlacement(final int count, final PlacementModifier heightPlacementModifier) {
        return orePlacement(CountPlacement.of(count), heightPlacementModifier);
    }

    /**
     * Get an {@link List<PlacementModifier> Ore Placement Modifier}
     *
     * @param rarityPlacementModifier {@link PlacementModifier The rarity placement modifier}
     * @param heightPlacementModifier {@link PlacementModifier The height placement modifier. Affects the levels at which the ore can spawn}
     * @return {@link List<PlacementModifier> The Common Ore Placement Modifier}
     */
    private static List<PlacementModifier> orePlacement(final PlacementModifier rarityPlacementModifier, final PlacementModifier heightPlacementModifier) {
        return List.of(rarityPlacementModifier, InSquarePlacement.spread(), heightPlacementModifier, BiomeFilter.biome());
    }

    //#endregion

    //#endregion

    //#region Register

    /**
     * Register all {@link PlacedFeature Placed Features}
     *
     * @param context {@link BootstapContext<PlacedFeature> The Bootstrap Context}
     */
    public static void bootstrap(final BootstapContext<PlacedFeature> context) {
        final HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        registerCommonOrePlacedFeature(context, ORE_SILVER_UPPER, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_SILVER), 90, 80, 384);
        registerCommonOrePlacedFeature(context, ORE_SILVER_MIDDLE, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_SILVER), 10, -24, 56);
        registerCommonOrePlacedFeature(context, ORE_SILVER_SMALL, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_SILVER_SMALL), 10, 72);
        registerCommonOrePlacedFeature(context, ORE_ALUMINUM_UPPER, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_ALUMINUM), 90, 80, 384);
        registerCommonOrePlacedFeature(context, ORE_ALUMINUM_MIDDLE, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_ALUMINUM), 10, -24, 56);
        registerCommonOrePlacedFeature(context, ORE_ALUMINUM_SMALL, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_ALUMINUM_SMALL), 10, 72);
        registerRareOrePlacedFeature(context, ORE_RUBY, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_RUBY), 100, -80, 80);
        registerRareOrePlacedFeature(context, ORE_SAPPHIRE, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_SAPPHIRE), 100, -80, 80);
        registerCommonOrePlacedFeature(context, ORE_MARBLE, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_MARBLE), 2, -16);

        registerPatchPlacedFeature(context, PATCH_BLUEBERRY_COMMON, configuredFeatures.getOrThrow(MWConfiguredFeatures.PATCH_BLUEBERRY_BUSH), 32);
        registerPatchPlacedFeature(context, PATCH_BLUEBERRY_RARE, configuredFeatures.getOrThrow(MWConfiguredFeatures.PATCH_BLUEBERRY_BUSH), 384);

        registerSpringPlacedFeature(context, SPRING_LAVA_VOLCANIC_PEAK, configuredFeatures.getOrThrow(MWConfiguredFeatures.SPRING_LAVA_VOLCANIC_PEAK));
        registerSpringPlacedFeature(context, SPRING_WATER_SCULK, configuredFeatures.getOrThrow(MWConfiguredFeatures.SPRING_WATER_SCULK));

        registerBlobPlacedFeature(context, MAGMA_STONE_BLOBS, configuredFeatures.getOrThrow(MWConfiguredFeatures.MAGMA_STONE_BLOBS), 10);
        registerBlobPlacedFeature(context, MAGMA_BLACKSTONE_BLOBS, configuredFeatures.getOrThrow(MWConfiguredFeatures.MAGMA_BLACKSTONE_BLOBS), 10);
        registerBlobPlacedFeature(context, COAL_STONE_BLOBS, configuredFeatures.getOrThrow(MWConfiguredFeatures.COAL_STONE_BLOBS), 10);
        registerBlobPlacedFeature(context, COAL_BLACKSTONE_BLOBS, configuredFeatures.getOrThrow(MWConfiguredFeatures.COAL_BLACKSTONE_BLOBS), 10);
        registerBlobPlacedFeature(context, LAVA_ROCK_STONE_BLOBS, configuredFeatures.getOrThrow(MWConfiguredFeatures.LAVA_ROCK_STONE_BLOBS), 10);
        registerBlobPlacedFeature(context, LAVA_ROCK_BLACKSTONE_BLOBS, configuredFeatures.getOrThrow(MWConfiguredFeatures.LAVA_ROCK_BLACKSTONE_BLOBS), 10);

        registerLakePlacedFeature(context, LAKE_LAVA_VOLCANIC_PEAK, configuredFeatures.getOrThrow(MWConfiguredFeatures.LAKE_LAVA_VOLCANIC_PEAK));
        registerLakePlacedFeature(context, LAKE_LAVA_MAGMA, configuredFeatures.getOrThrow(MWConfiguredFeatures.LAKE_LAVA_MAGMA));
        registerLakePlacedFeature(context, LAKE_LAVA_BLACKSTONE, configuredFeatures.getOrThrow(MWConfiguredFeatures.LAKE_LAVA_BLACKSTONE));
        registerLakePlacedFeature(context, LAKE_LAVA_COAL, configuredFeatures.getOrThrow(MWConfiguredFeatures.LAKE_LAVA_COAL));

        registerTreePlacedFeature(context, APPLE_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.APPLE_TREE), 1, MWBlocks.APPLE_SAPLING.get());
        registerTreePlacedFeature(context, APPLE_BEES_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.APPLE_BEES_TREE), 1, MWBlocks.APPLE_SAPLING.get());
        registerTreePlacedFeature(context, FANCY_APPLE_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FANCY_APPLE_TREE), 1, MWBlocks.APPLE_SAPLING.get());
        registerTreePlacedFeature(context, FANCY_APPLE_BEES_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FANCY_APPLE_BEES_TREE), 1, MWBlocks.APPLE_SAPLING.get());
        registerRareTreePlacedFeature(context, PALM_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.PALM_TREE), 5, MWBlocks.PALM_SAPLING.get());
        registerRareTreePlacedFeature(context, DEAD_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.DEAD_TREE), 1, Blocks.OAK_SAPLING);
        registerRareTreePlacedFeature(context, FANCY_DEAD_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FANCY_DEAD_TREE), 1, Blocks.OAK_SAPLING);
        registerPlacedFeature(context, SCULK_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.SCULK_TREE), List.of(CountOnEveryLayerPlacement.of(4),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(MWBlocks.SCULK_SAPLING.get().defaultBlockState(), BlockPos.ZERO))));

        registerFallenTreePlacedFeature(context, FALLEN_OAK_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_OAK_TREE), 15);
        registerFallenTreePlacedFeature(context, FALLEN_OAK_TREE_SWAMP, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_OAK_TREE_SWAMP), 15);
        registerFallenTreePlacedFeature(context, FALLEN_BIRCH_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_BIRCH_TREE), 15);
        registerFallenTreePlacedFeature(context, FALLEN_SPRUCE_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_SPRUCE_TREE), 15);
        registerFallenTreePlacedFeature(context, FALLEN_JUNGLE_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_JUNGLE_TREE), 15);
        registerFallenTreePlacedFeature(context, FALLEN_ACACIA_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_ACACIA_TREE), 15);
        registerFallenTreePlacedFeature(context, FALLEN_DARK_OAK_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_DARK_OAK_TREE), 15);
        registerFallenTreePlacedFeature(context, FALLEN_MANGROVE_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_MANGROVE_TREE), 15);
        registerFallenTreePlacedFeature(context, FALLEN_CHERRY_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_CHERRY_TREE), 15);
        registerFallenTreePlacedFeature(context, FALLEN_APPLE_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_APPLE_TREE), 15);
        registerFallenTreePlacedFeature(context, FALLEN_PALM_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_PALM_TREE), 15);
        registerFallenTreePlacedFeature(context, FALLEN_DEAD_TREE, configuredFeatures.getOrThrow(MWConfiguredFeatures.FALLEN_DEAD_TREE), 15);

        registerPlacedFeature(context, ORE_PYRITE, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_PYRITE), commonOrePlacement(10, PlacementUtils.RANGE_10_10));
        registerPlacedFeature(context, PATCH_BLUEBERRY_BUSH_VILLAGE, configuredFeatures.getOrThrow(MWConfiguredFeatures.PATCH_BLUEBERRY_BUSH));
        registerPlacedFeature(context, CATTAIL, configuredFeatures.getOrThrow(MWConfiguredFeatures.CATTAIL), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, CountPlacement.of(100));
        registerPlacedFeature(context, BONE_SPIKE, configuredFeatures.getOrThrow(MWConfiguredFeatures.BONE_SPIKE), RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR);
        registerPlacedFeature(context, PATCH_ETHEREAL_FIRE, configuredFeatures.getOrThrow(MWConfiguredFeatures.PATCH_ETHEREAL_FIRE), CountPlacement.of(UniformInt.of(0, 5)), InSquarePlacement.spread(), PlacementUtils.RANGE_4_4);
        registerPlacedFeature(context, PATCH_SCULK_ROOTS, configuredFeatures.getOrThrow(MWConfiguredFeatures.PATCH_SCULK_ROOTS), CountOnEveryLayerPlacement.of(4));
    }

    //#endregion

}