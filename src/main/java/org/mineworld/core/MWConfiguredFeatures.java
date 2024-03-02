package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluids;
import org.mineworld.MineWorld;
import org.mineworld.block.BlueberryBushBlock;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.world.worldgen.feature.configurations.FallenTreeConfiguration;
import org.mineworld.world.worldgen.tree.foliageplacers.PalmFoliagePlacer;
import org.mineworld.world.worldgen.tree.trunkplacers.PalmTrunkPlacer;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRING_LAVA_VOLCANIC_PEAKS = registerConfiguredFeatureKey("spring_lava_volcanic_peaks");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAGMA_STONE_BLOBS = registerConfiguredFeatureKey("magma_stone_blobs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAGMA_BLACKSTONE_BLOBS = registerConfiguredFeatureKey("magma_blackstone_blobs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COAL_STONE_BLOBS = registerConfiguredFeatureKey("coal_stone_blobs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COAL_BLACKSTONE_BLOBS = registerConfiguredFeatureKey("coal_blackstone_blobs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVA_ROCK_STONE_BLOBS = registerConfiguredFeatureKey("lava_rock_stone_blobs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVA_ROCK_BLACKSTONE_BLOBS = registerConfiguredFeatureKey("lava_rock_blackstone_blobs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAKE_LAVA_VOLCANIC_PEAKS = registerConfiguredFeatureKey("lake_lava_volcanic_peaks");
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

    /**
     * Get the Overworld {@link OreConfiguration.TargetBlockState Target Block States}
     *
     * @param stoneOreSupplier {@link Supplier<Block> The Stone Ore Block Supplier}
     * @param deepslateOreSupplier {@link Supplier<Block> The Deepslate Ore Block Supplier}
     * @return {@link OreConfiguration.TargetBlockState Overworld Target Block states}
     */
    private static Supplier<List<OreConfiguration.TargetBlockState>> overworldTargetStates(final Supplier<Block> stoneOreSupplier, final Supplier<Block> deepslateOreSupplier) {
        return Suppliers.memoize(() -> List.of(
                oreConfiguration(BlockTags.STONE_ORE_REPLACEABLES, stoneOreSupplier),
                oreConfiguration(BlockTags.DEEPSLATE_ORE_REPLACEABLES, deepslateOreSupplier)));
    }

    /**
     * Get the Overworld {@link OreConfiguration.TargetBlockState Target Block States}
     * for an ore that doesn't have a variant
     *
     * @param oreSupplier {@link Supplier<Block> The Ore Block Supplier}
     * @return {@link OreConfiguration.TargetBlockState Overworld Target Block states}
     */
    private static Supplier<List<OreConfiguration.TargetBlockState>> overworldTargetStates(final Supplier<Block> oreSupplier) {
        return targetStates(BlockTags.BASE_STONE_OVERWORLD, oreSupplier);
    }

    /**
     * Get the Overworld {@link OreConfiguration.TargetBlockState Target Block States}
     * for an ore that doesn't have a variant
     *
     * @param oreSupplier {@link Supplier<Block> The Ore Block Supplier}
     * @return {@link OreConfiguration.TargetBlockState Overworld Target Block states}
     */
    private static Supplier<List<OreConfiguration.TargetBlockState>> netherTargetStates(final Supplier<Block> oreSupplier) {
        return targetStates(BlockTags.BASE_STONE_NETHER, oreSupplier);
    }

    /**
     * Get the Overworld {@link OreConfiguration.TargetBlockState Target Block States}
     * for an ore that doesn't have a variant
     *
     * @param oreTargetTag {@link TagKey<Block> The Block Tags targeted by this ore}
     * @param oreSupplier {@link Supplier<Block> The Ore Block Supplier}
     * @return {@link OreConfiguration.TargetBlockState Overworld Target Block states}
     */
    private static Supplier<List<OreConfiguration.TargetBlockState>> targetStates(final TagKey<Block> oreTargetTag, final Supplier<Block> oreSupplier) {
        return Suppliers.memoize(() -> List.of(OreConfiguration.target(new TagMatchTest(oreTargetTag), oreSupplier.get().defaultBlockState())));
    }

    /**
     * Get an {@link OreConfiguration.TargetBlockState Ore Configuration}
     *
     * @param oreTargetTag {@link TagKey<Block> The Block Tags targeted by this ore}
     * @param oreSupplier {@link Supplier<Block> The Ore Block Supplier}
     * @return {@link OreConfiguration.TargetBlockState The Ore Configuration}
     */
    private static OreConfiguration.TargetBlockState oreConfiguration(final TagKey<Block> oreTargetTag, final Supplier<Block> oreSupplier) {
        return OreConfiguration.target(new TagMatchTest(oreTargetTag), oreSupplier.get().defaultBlockState());
    }

    /**
     * Register an Overworld {@link ConfiguredFeature Ore Configured Feature}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey<ConfiguredFeature> The Configured Feature Resource Key}
     * @param stoneOreSupplier {@link Supplier<Block> The Stone ore Block Supplier}
     * @param deepslateOreSupplier {@link Supplier<Block> The Deepslate Ore Block Supplier}
     * @param maxVeinSize {@link Integer The maximum number of blocks per vein}
     */
    private static void registerOverworldOreConfiguredFeature(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final Supplier<Block> stoneOreSupplier, final Supplier<Block> deepslateOreSupplier, final int maxVeinSize) {
        registerOreConfiguredFeature(context, key, overworldTargetStates(stoneOreSupplier, deepslateOreSupplier), maxVeinSize);
    }

    /**
     * Register an Overworld {@link ConfiguredFeature ore configured feature}
     * that doesn't have a variant
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey<ConfiguredFeature> The Configured Feature Resource Key}
     * @param oreSupplier {@link Supplier<Block> The stone ore block supplier}
     * @param size {@link Integer The number of blocks per vein}
     */
    private static void registerOverworldOreConfiguredFeature(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final Supplier<Block> oreSupplier, final int size) {
        registerOreConfiguredFeature(context, key, overworldTargetStates(oreSupplier), size);
    }

    /**
     * Register a Nether {@link ConfiguredFeature ore configured feature}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey<ConfiguredFeature> The Configured Feature Resource Key}
     * @param oreSupplier {@link Supplier<Block> The ore block supplier}
     * @param size {@link Integer The number of blocks per vein}
     */
    private static void registerNetherOreConfiguredFeature(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final Supplier<Block> oreSupplier, final int size) {
        registerOreConfiguredFeature(context, key, netherTargetStates(oreSupplier), size);
    }

    /**
     * Register an {@link ConfiguredFeature ore configured feature}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey<ConfiguredFeature> The Configured Feature Resource Key}
     * @param oreTargetStates {@link List<OreConfiguration.TargetBlockState> The ore block target states}
     * @param size {@link Integer The number of blocks per vein}
     */
    private static void registerOreConfiguredFeature(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final Supplier<List<OreConfiguration.TargetBlockState>> oreTargetStates, final int size) {
        FeatureUtils.register(context, key, Feature.ORE, new OreConfiguration(oreTargetStates.get(), size));
    }

    /**
     * Register a {@link Feature#RANDOM_PATCH Flower Random Patch Feature}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey<ConfiguredFeature> The Configured Feature Resource Key}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the random patch should place}
     */
    public static void registerFlowerRandomPatchConfiguredFeature(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final Supplier<BlockState> blockStateSupplier) {
        registerRandomPatchConfiguredFeature(context, key, blockStateSupplier, List.of(Blocks.GRASS_BLOCK));
    }

    /**
     * Register a {@link Feature#RANDOM_PATCH Random Patch Feature}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey<ConfiguredFeature> The Configured Feature Resource Key}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the random patch should place}
     * @param soilBlocks {@link List<Block> The list of Soil Blocks}
     */
    public static void registerRandomPatchConfiguredFeature(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final Supplier<BlockState> blockStateSupplier, final List<Block> soilBlocks) {
        FeatureUtils.register(context, key, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(blockStateSupplier.get())), soilBlocks));
    }

    /**
     * Register a {@link Feature#SIMPLE_RANDOM_SELECTOR Random Selector Feature}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey<ConfiguredFeature> The Configured Feature Resource Key}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State the random patch should place}
     */
    public static void registerRandomSelectorConfiguredFeature(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final Supplier<BlockState> blockStateSupplier) {
        FeatureUtils.register(context, key, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(PlacementUtils.inlinePlaced(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(blockStateSupplier.get(), 1)))))));
    }

    /**
     * Register a {@link Feature#SPRING Spring Feature}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey<ConfiguredFeature> The Configured Feature Resource Key}
     * @param fluid {@link FlowingFluid The Fluid this Spring should place}
     * @param validBlockSuppliers {@link Supplier<Block> The Supplier for the Blocks where the Spring can be placed}
     */
    @SafeVarargs
    public static void registerSpringFeature(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final FlowingFluid fluid, final Supplier<Block>... validBlockSuppliers) {
        FeatureUtils.register(context, key, Feature.SPRING, new SpringConfiguration(fluid.defaultFluidState(), true, 4, 1,
                HolderSet.direct(Block::builtInRegistryHolder, Arrays.stream(validBlockSuppliers).map(blockSupplier -> blockSupplier.get()).collect(Collectors.toSet()))));
    }

    /**
     * Register a {@link Feature#REPLACE_BLOBS Blob Feature}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey<ConfiguredFeature> The Configured Feature Resource Key}
     * @param targetBlockStateSupplier {@link Supplier<BlockState> The Supplier for the Block to replace}
     * @param blobBlockStateSupplier {@link Supplier<BlockState> The Supplier for the Block to place}
     */
    public static void registerBlobFeature(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final Supplier<BlockState> targetBlockStateSupplier, final Supplier<BlockState> blobBlockStateSupplier) {
        FeatureUtils.register(context, key, Feature.REPLACE_BLOBS, new ReplaceSphereConfiguration(targetBlockStateSupplier.get(), blobBlockStateSupplier.get(), UniformInt.of(2, 5)));
    }

    /**
     * Register a {@link Feature#LAKE Lake Feature}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey<ConfiguredFeature> The Configured Feature Resource Key}
     * @param lakeBlockStateSupplier {@link Supplier<BlockState> The Supplier for the Block to place inside the Lake}
     * @param barrierBlockStateSupplier {@link Supplier<BlockState> The Supplier for the Block to place outside the Lake}
     */
    public static void registerLakeFeature(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final Supplier<BlockState> lakeBlockStateSupplier, final Supplier<BlockState> barrierBlockStateSupplier) {
        FeatureUtils.register(context, key, Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(lakeBlockStateSupplier.get()), BlockStateProvider.simple(barrierBlockStateSupplier.get())));
    }

    /**
     * Register a {@link Feature#TREE Tree Feature}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey<ConfiguredFeature> The Configured Feature Resource Key}
     * @param treeConfiguration {@link TreeConfiguration The Tree configuration}
     */
    public static void registerTreeFeature(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final TreeConfiguration treeConfiguration) {
        FeatureUtils.register(context, key, Feature.TREE, treeConfiguration);
    }

    /**
     * Register a {@link MWFeatures#FALLEN_TREE Fallen Tree Feature}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey<ConfiguredFeature> The Configured Feature Resource Key}
     * @param logBlock {@link Block The Log Block}
     * @param hollowLogBlock {@link Block The Hollow Log Block}
     * @param ignoreMoss {@link Boolean If the Fallen Tree should not generate Moss}
     */
    public static void registerFallenTreeFeature(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final Block logBlock, final Block hollowLogBlock, final boolean ignoreMoss) {
        FeatureUtils.register(context, key, MWFeatures.FALLEN_TREE.get(), fallenTree(logBlock, hollowLogBlock, ignoreMoss));
    }

    //#region Tree Configurations

    /**
     * Get the {@link TreeConfiguration Apple Tree Configuration}
     *
     * @param isFancy {@link Boolean If the Tree should be a Large Tree}
     * @param withBeeHive {@link Boolean If the Tree should have a Bee Hive}
     * @return {@link TreeConfiguration The Apple Tree Configuration}
     */
    private static TreeConfiguration appleTree(final boolean isFancy, final boolean withBeeHive) {
        TreeConfiguration.TreeConfigurationBuilder builder = treeBuilder(
                Suppliers.memoize(() -> MWBlocks.APPLE_LOG.get()),
                isFancy ? new FancyTrunkPlacer(3, 11, 0) : new StraightTrunkPlacer(4, 2, 0),
                Suppliers.memoize(() -> MWBlocks.APPLE_LEAVES.get()),
                isFancy ? new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4) : new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                isFancy ? new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)) : new TwoLayersFeatureSize(1, 0, 1)
        ).ignoreVines();
        if(withBeeHive) {
            builder = builder.decorators(List.of(new BeehiveDecorator(0.05F)));
        }
        return builder.build();
    }

    /**
     * Get the {@link TreeConfiguration Palm Tree Configuration}
     *
     * @return {@link TreeConfiguration The Palm Tree Configuration}
     */
    private static TreeConfiguration palmTree() {
        return tree(
                Suppliers.memoize(() -> MWBlocks.PALM_LOG.get()),
                new PalmTrunkPlacer(7, 4, 2),
                Suppliers.memoize(() -> MWBlocks.PALM_LEAVES.get()),
                new PalmFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                new TwoLayersFeatureSize(1, 0, 1)
        );
    }

    /**
     * Get the {@link TreeConfiguration Dead Tree Configuration}
     *
     * @param isFancy {@link Boolean If the Tree should be a Large Tree}
     * @return {@link TreeConfiguration The Dead Tree Configuration}
     */
    private static TreeConfiguration deadTree(final boolean isFancy) {
        return tree(
                Suppliers.memoize(() -> MWBlocks.DEAD_LOG.get()),
                isFancy ? new FancyTrunkPlacer(3, 11, 0) : new StraightTrunkPlacer(4, 2, 0),
                () -> Blocks.AIR,
                isFancy ? new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4) : new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                isFancy ? new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)) : new TwoLayersFeatureSize(1, 0, 1)
        );
    }

    /**
     * Get the {@link TreeConfiguration Sculk Tree Configuration}
     *
     * @return {@link TreeConfiguration The Sculk Tree Configuration}
     */
    private static TreeConfiguration sculkTree() {
        return tree(
                Suppliers.memoize(() -> MWBlocks.SCULK_LOG.get()),
                new CherryTrunkPlacer(7, 1, 0,
                        new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(ConstantInt.of(1), 1).add(ConstantInt.of(2), 1).add(ConstantInt.of(3), 1).build()),
                        UniformInt.of(2, 4), UniformInt.of(-4, -3), UniformInt.of(-1, 0)
                ),
                Suppliers.memoize(() -> MWBlocks.SCULK_LEAVES.get()),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.25F, 0.5F, 0.16666667F, 0.33333334F),
                new TwoLayersFeatureSize(1, 0, 2)
        );
    }

    /**
     * Get a {@link TreeConfiguration Tree Configuration}
     *
     * @param trunkBlockProvider {@link Supplier<Block> The Supplier for the Tree Trunk Block}
     * @param trunkPlacer {@link TrunkPlacer The Tree Trunk Placer}
     * @param leavesBlockSupplier {@link Supplier<Block> The Supplier for the Tree Leaves Block}
     * @param foliagePlacer {@link FoliagePlacer The Trunk Foliage Placer}
     * @param twoLayersFeatureSize {@link TwoLayersFeatureSize The Tree Two Layers Feature Size}
     * @return {@link TreeConfiguration The Tree Configuration}
     */
    private static TreeConfiguration tree(final Supplier<Block> trunkBlockProvider, final TrunkPlacer trunkPlacer, final Supplier<Block> leavesBlockSupplier, final FoliagePlacer foliagePlacer, final TwoLayersFeatureSize twoLayersFeatureSize) {
        return treeBuilder(
                trunkBlockProvider,
                trunkPlacer,
                leavesBlockSupplier,
                foliagePlacer,
                twoLayersFeatureSize
        ).ignoreVines().build();
    }

    /**
     * Get a {@link TreeConfiguration Tree Configuration}
     *
     * @param trunkBlockProvider {@link Supplier<Block> The Supplier for the Tree Trunk Block}
     * @param trunkPlacer {@link TrunkPlacer The Tree Trunk Placer}
     * @param leavesBlockSupplier {@link Supplier<Block> The Supplier for the Tree Leaves Block}
     * @param foliagePlacer {@link FoliagePlacer The Trunk Foliage Placer}
     * @param twoLayersFeatureSize {@link TwoLayersFeatureSize The Tree Two Layers Feature Size}
     * @return {@link TreeConfiguration The Tree Configuration}
     */
    private static TreeConfiguration.TreeConfigurationBuilder treeBuilder(final Supplier<Block> trunkBlockProvider, final TrunkPlacer trunkPlacer, final Supplier<Block> leavesBlockSupplier, final FoliagePlacer foliagePlacer, final TwoLayersFeatureSize twoLayersFeatureSize) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(trunkBlockProvider.get()),
                trunkPlacer,
                BlockStateProvider.simple(leavesBlockSupplier.get()),
                foliagePlacer,
                twoLayersFeatureSize
        );
    }

    //#endregion

    //#region Fallen Tree Configurations

    //#endregion

    /**
     * Get a {@link FallenTreeConfiguration Fallen Tree Configuration}
     *
     * @param logBlock {@link Block The Log Block}
     * @param hollowLogBlock {@link Block The Hollow Log Block}
     * @param ignoreMoss {@link Boolean If the Fallen Tree should not generate Moss}
     * @return {@link FallenTreeConfiguration The Fallen Tree Configuration}
     */
    private static FallenTreeConfiguration fallenTree(final Block logBlock, final Block hollowLogBlock, final boolean ignoreMoss) {
        return new FallenTreeConfiguration(BlockStateProvider.simple(logBlock), BlockStateProvider.simple(hollowLogBlock), ignoreMoss);
    }

    //#endregion

    //#region Register

    /**
     * Register all {@link ConfiguredFeature Configured Features}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The Bootstrap Context}
     */
    public static void bootstrap(final BootstapContext<ConfiguredFeature<?, ?>> context) {
        registerOverworldOreConfiguredFeature(context, ORE_SILVER, MWBlocks.SILVER_ORE, MWBlocks.DEEPSLATE_SILVER_ORE, 9);
        registerOverworldOreConfiguredFeature(context, ORE_SILVER_SMALL, MWBlocks.SILVER_ORE, MWBlocks.DEEPSLATE_SILVER_ORE, 4);
        registerOverworldOreConfiguredFeature(context, ORE_ALUMINUM, MWBlocks.ALUMINUM_ORE, MWBlocks.DEEPSLATE_ALUMINUM_ORE, 9);
        registerOverworldOreConfiguredFeature(context, ORE_ALUMINUM_SMALL, MWBlocks.ALUMINUM_ORE, MWBlocks.DEEPSLATE_ALUMINUM_ORE, 4);
        registerOverworldOreConfiguredFeature(context, ORE_RUBY, MWBlocks.RUBY_ORE, MWBlocks.DEEPSLATE_RUBY_ORE, 3);
        registerOverworldOreConfiguredFeature(context, ORE_SAPPHIRE, MWBlocks.SAPPHIRE_ORE, MWBlocks.DEEPSLATE_SAPPHIRE_ORE, 3);
        registerOverworldOreConfiguredFeature(context, ORE_MARBLE, MWBlocks.MARBLE, 16);
        registerNetherOreConfiguredFeature(context, ORE_PYRITE, MWBlocks.PYRITE_ORE, 17);

        registerFlowerRandomPatchConfiguredFeature(context, PATCH_BLUEBERRY_BUSH, Suppliers.memoize(() -> MWBlocks.BLUEBERRY_BUSH.get().defaultBlockState().setValue(BlueberryBushBlock.AGE, 3)));
        registerRandomPatchConfiguredFeature(context, PATCH_BLUEBERRY_BUSH, Suppliers.memoize(() -> MWBlocks.BLUEBERRY_BUSH.get().defaultBlockState().setValue(BlueberryBushBlock.AGE, 3)), List.of(MWBlocks.SCULK_SOIL.get()));

        registerBlobFeature(context, MAGMA_STONE_BLOBS, Blocks.STONE::defaultBlockState, Blocks.MAGMA_BLOCK::defaultBlockState);
        registerBlobFeature(context, MAGMA_BLACKSTONE_BLOBS, Blocks.BLACKSTONE::defaultBlockState, Blocks.MAGMA_BLOCK::defaultBlockState);
        registerBlobFeature(context, COAL_STONE_BLOBS, Blocks.STONE::defaultBlockState, Blocks.COAL_BLOCK::defaultBlockState);
        registerBlobFeature(context, COAL_BLACKSTONE_BLOBS, Blocks.BLACKSTONE::defaultBlockState, Blocks.COAL_BLOCK::defaultBlockState);
        registerBlobFeature(context, LAVA_ROCK_STONE_BLOBS, Blocks.STONE::defaultBlockState, Suppliers.memoize(() -> MWBlocks.LAVA_ROCK.get().defaultBlockState()));
        registerBlobFeature(context, LAVA_ROCK_BLACKSTONE_BLOBS, Blocks.BLACKSTONE::defaultBlockState, Suppliers.memoize(() -> MWBlocks.LAVA_ROCK.get().defaultBlockState()));

        registerLakeFeature(context, LAKE_LAVA_VOLCANIC_PEAKS, Blocks.LAVA::defaultBlockState, Suppliers.memoize(() -> MWBlocks.LAVA_ROCK.get().defaultBlockState()));
        registerLakeFeature(context, LAKE_LAVA_MAGMA, Blocks.LAVA::defaultBlockState, Blocks.MAGMA_BLOCK::defaultBlockState);
        registerLakeFeature(context, LAKE_LAVA_BLACKSTONE, Blocks.LAVA::defaultBlockState, Blocks.BLACKSTONE::defaultBlockState);
        registerLakeFeature(context, LAKE_LAVA_COAL, Blocks.LAVA::defaultBlockState, Blocks.COAL_BLOCK::defaultBlockState);

        registerSpringFeature(context, SPRING_LAVA_VOLCANIC_PEAKS, Fluids.LAVA,
                () -> Blocks.STONE,
                () -> Blocks.GRANITE,
                () -> Blocks.DIORITE,
                () -> Blocks.ANDESITE,
                () -> Blocks.DEEPSLATE,
                () -> Blocks.TUFF,
                () -> Blocks.CALCITE,
                () -> Blocks.DIRT,
                () -> Blocks.COAL_BLOCK,
                () -> Blocks.BLACKSTONE,
                () -> Blocks.MAGMA_BLOCK,
                Suppliers.memoize(() -> MWBlocks.LAVA_ROCK.get()));
        registerSpringFeature(context, SPRING_WATER_SCULK, Fluids.WATER,
                () -> Blocks.SCULK,
                Suppliers.memoize(() -> MWBlocks.SCULK_SOIL.get()));

        registerTreeFeature(context, APPLE_TREE, appleTree(false, false));
        registerTreeFeature(context, APPLE_BEES_TREE, appleTree(false, true));
        registerTreeFeature(context, FANCY_APPLE_TREE, appleTree(true, false));
        registerTreeFeature(context, FANCY_APPLE_BEES_TREE, appleTree(true, true));
        registerTreeFeature(context, PALM_TREE, palmTree());
        registerTreeFeature(context, DEAD_TREE, deadTree(false));
        registerTreeFeature(context, FANCY_DEAD_TREE, deadTree(true));
        registerTreeFeature(context, SCULK_TREE, sculkTree());

        registerFallenTreeFeature(context, FALLEN_OAK_TREE, Blocks.OAK_LOG, MWBlocks.HOLLOW_OAK_LOG.get(), true);
        registerFallenTreeFeature(context, FALLEN_OAK_TREE_SWAMP, Blocks.OAK_LOG, MWBlocks.HOLLOW_OAK_LOG.get(), false);
        registerFallenTreeFeature(context, FALLEN_BIRCH_TREE, Blocks.BIRCH_LOG, MWBlocks.HOLLOW_BIRCH_LOG.get(), false);
        registerFallenTreeFeature(context, FALLEN_SPRUCE_TREE, Blocks.SPRUCE_LOG, MWBlocks.HOLLOW_SPRUCE_LOG.get(), true);
        registerFallenTreeFeature(context, FALLEN_JUNGLE_TREE, Blocks.JUNGLE_LOG, MWBlocks.HOLLOW_JUNGLE_LOG.get(), false);
        registerFallenTreeFeature(context, FALLEN_ACACIA_TREE, Blocks.ACACIA_LOG, MWBlocks.HOLLOW_ACACIA_LOG.get(), true);
        registerFallenTreeFeature(context, FALLEN_DARK_OAK_TREE, Blocks.DARK_OAK_LOG, MWBlocks.HOLLOW_DARK_OAK_LOG.get(), true);
        registerFallenTreeFeature(context, FALLEN_MANGROVE_TREE, Blocks.MANGROVE_LOG, MWBlocks.HOLLOW_MANGROVE_LOG.get(), false);
        registerFallenTreeFeature(context, FALLEN_CHERRY_TREE, Blocks.CHERRY_LOG, MWBlocks.HOLLOW_CHERRY_LOG.get(), true);
        registerFallenTreeFeature(context, FALLEN_APPLE_TREE, MWBlocks.APPLE_LOG.get(), MWBlocks.HOLLOW_APPLE_LOG.get(), true);
        registerFallenTreeFeature(context, FALLEN_PALM_TREE, MWBlocks.PALM_LOG.get(), MWBlocks.HOLLOW_PALM_LOG.get(), true);
        registerFallenTreeFeature(context, FALLEN_DEAD_TREE, MWBlocks.DEAD_LOG.get(), MWBlocks.HOLLOW_DEAD_LOG.get(), true);

        FeatureUtils.register(context, BONE_SPIKE, MWFeatures.BONE_SPIKE.get());
        FeatureUtils.register(context, PATCH_SCULK_ROOTS, MWFeatures.ETHEREAL_FOREST_VEGETATION.get(), new NetherForestVegetationConfig(BlockStateProvider.simple(MWBlocks.SCULK_ROOTS.get()), 8, 4));
    }
    
    //#endregion

}