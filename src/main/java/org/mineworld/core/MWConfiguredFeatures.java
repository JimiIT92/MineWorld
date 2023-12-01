package org.mineworld.core;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.ReplaceSphereConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SpringConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.material.Fluids;
import org.mineworld.MineWorld;
import org.mineworld.block.BlueberryBushBlock;
import org.mineworld.helper.KeyHelper;
import org.mineworld.helper.RegisterHelper;
import org.mineworld.world.worldgen.feature.configuration.FallenTreeConfiguration;
import org.mineworld.world.worldgen.tree.foliageplacers.PalmFoliagePlacer;
import org.mineworld.world.worldgen.tree.trunkplacers.PalmTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

/**
 * {@link MineWorld MineWorld} {@link ConfiguredFeature configured features}
 */
public final class MWConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SILVER = KeyHelper.registerConfiguredFeatureKey("ore_silver");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SILVER_SMALL = KeyHelper.registerConfiguredFeatureKey("ore_silver_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ALUMINUM = KeyHelper.registerConfiguredFeatureKey("ore_aluminum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ALUMINUM_SMALL = KeyHelper.registerConfiguredFeatureKey("ore_aluminum_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY = KeyHelper.registerConfiguredFeatureKey("ore_ruby");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE = KeyHelper.registerConfiguredFeatureKey("ore_sapphire");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PYRITE = KeyHelper.registerConfiguredFeatureKey("ore_pyrite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_MARBLE = KeyHelper.registerConfiguredFeatureKey("ore_marble");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_BLUEBERRY_BUSH = KeyHelper.registerConfiguredFeatureKey("patch_blueberry_bush.json");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRING_LAVA_VOLCANIC_PEAK = KeyHelper.registerConfiguredFeatureKey("spring_lava_volcanic_peak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAGMA_STONE_BLOBS = KeyHelper.registerConfiguredFeatureKey("magma_stone_blobs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAGMA_BLACKSTONE_BLOBS = KeyHelper.registerConfiguredFeatureKey("magma_blackstone_blobs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COAL_STONE_BLOBS = KeyHelper.registerConfiguredFeatureKey("coal_stone_blobs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COAL_BLACKSTONE_BLOBS = KeyHelper.registerConfiguredFeatureKey("coal_blackstone_blobs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVA_ROCK_STONE_BLOBS = KeyHelper.registerConfiguredFeatureKey("lava_rock_stone_blobs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVA_ROCK_BLACKSTONE_BLOBS = KeyHelper.registerConfiguredFeatureKey("lava_rock_blackstone_blobs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAKE_LAVA_VOLCANIC_PEAK = KeyHelper.registerConfiguredFeatureKey("lake_lava_volcanic_peak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAKE_LAVA_MAGMA = KeyHelper.registerConfiguredFeatureKey("lake_lava_magma");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAKE_LAVA_BLACKSTONE = KeyHelper.registerConfiguredFeatureKey("lake_lava_blackstone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAKE_LAVA_COAL = KeyHelper.registerConfiguredFeatureKey("lake_lava_coal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_TREE = KeyHelper.registerConfiguredFeatureKey("apple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_BEES_TREE = KeyHelper.registerConfiguredFeatureKey("apple_bees_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_APPLE_TREE = KeyHelper.registerConfiguredFeatureKey("fancy_apple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_APPLE_BEES_TREE = KeyHelper.registerConfiguredFeatureKey("fancy_apple_bees_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM_TREE = KeyHelper.registerConfiguredFeatureKey("palm_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_TREE = KeyHelper.registerConfiguredFeatureKey("dead_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_DEAD_TREE = KeyHelper.registerConfiguredFeatureKey("fancy_dead_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SCULK_TREE = KeyHelper.registerConfiguredFeatureKey("sculk_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BONE_SPIKE = KeyHelper.registerConfiguredFeatureKey("bone_spike");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_OAK_TREE = KeyHelper.registerConfiguredFeatureKey("fallen_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_OAK_TREE_SWAMP = KeyHelper.registerConfiguredFeatureKey("fallen_oak_tree_swamp");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_BIRCH_TREE = KeyHelper.registerConfiguredFeatureKey("fallen_birch_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_SPRUCE_TREE = KeyHelper.registerConfiguredFeatureKey("fallen_spruce_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_JUNGLE_TREE = KeyHelper.registerConfiguredFeatureKey("fallen_jungle_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_ACACIA_TREE = KeyHelper.registerConfiguredFeatureKey("fallen_acacia_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_DARK_OAK_TREE = KeyHelper.registerConfiguredFeatureKey("fallen_dark_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_MANGROVE_TREE = KeyHelper.registerConfiguredFeatureKey("fallen_mangrove_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_CHERRY_TREE = KeyHelper.registerConfiguredFeatureKey("fallen_cherry_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_APPLE_TREE = KeyHelper.registerConfiguredFeatureKey("fallen_apple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_PALM_TREE = KeyHelper.registerConfiguredFeatureKey("fallen_palm_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_DEAD_TREE = KeyHelper.registerConfiguredFeatureKey("fallen_dead_tree");

    /**
     * Register the {@link ConfiguredFeature configured features}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     */
    public static void bootstrap(final BootstapContext<ConfiguredFeature<?, ?>> context) {
        RegisterHelper.registerOverworldOreConfiguredFeature(context, ORE_SILVER, MWBlocks.SILVER_ORE, MWBlocks.DEEPSLATE_SILVER_ORE, 9);
        RegisterHelper.registerOverworldOreConfiguredFeature(context, ORE_SILVER_SMALL, MWBlocks.SILVER_ORE, MWBlocks.DEEPSLATE_SILVER_ORE, 4);
        RegisterHelper.registerOverworldOreConfiguredFeature(context, ORE_ALUMINUM, MWBlocks.ALUMINUM_ORE, MWBlocks.DEEPSLATE_ALUMINUM_ORE, 9);
        RegisterHelper.registerOverworldOreConfiguredFeature(context, ORE_ALUMINUM_SMALL, MWBlocks.ALUMINUM_ORE, MWBlocks.DEEPSLATE_ALUMINUM_ORE, 4);
        RegisterHelper.registerOverworldOreConfiguredFeature(context, ORE_RUBY, MWBlocks.RUBY_ORE, MWBlocks.DEEPSLATE_RUBY_ORE, 3);
        RegisterHelper.registerOverworldOreConfiguredFeature(context, ORE_SAPPHIRE, MWBlocks.SAPPHIRE_ORE, MWBlocks.DEEPSLATE_SAPPHIRE_ORE, 3);
        RegisterHelper.registerOverworldOreConfiguredFeature(context, ORE_MARBLE, MWBlocks.MARBLE, 16);
        RegisterHelper.registerNetherOreConfiguredFeature(context, ORE_PYRITE, MWBlocks.PYRITE_ORE, 17);
        RegisterHelper.registerRandomPatchConfiguredFeature(context, PATCH_BLUEBERRY_BUSH, () -> MWBlocks.BLUEBERRY_BUSH.get().defaultBlockState().setValue(BlueberryBushBlock.AGE, 3));
        FeatureUtils.register(context, SPRING_LAVA_VOLCANIC_PEAK, Feature.SPRING, new SpringConfiguration(Fluids.LAVA.defaultFluidState(), true, 4, 1,
                HolderSet.direct(Block::builtInRegistryHolder, Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DEEPSLATE, Blocks.TUFF, Blocks.CALCITE, Blocks.DIRT, Blocks.COAL_BLOCK, Blocks.BLACKSTONE, Blocks.MAGMA_BLOCK, MWBlocks.LAVA_ROCK.get())));
        FeatureUtils.register(context, MAGMA_STONE_BLOBS, Feature.REPLACE_BLOBS, new ReplaceSphereConfiguration(Blocks.STONE.defaultBlockState(), Blocks.MAGMA_BLOCK.defaultBlockState(), UniformInt.of(2, 5)));
        FeatureUtils.register(context, MAGMA_BLACKSTONE_BLOBS, Feature.REPLACE_BLOBS, new ReplaceSphereConfiguration(Blocks.BLACKSTONE.defaultBlockState(), Blocks.MAGMA_BLOCK.defaultBlockState(), UniformInt.of(2, 5)));
        FeatureUtils.register(context, COAL_STONE_BLOBS, Feature.REPLACE_BLOBS, new ReplaceSphereConfiguration(Blocks.STONE.defaultBlockState(), Blocks.COAL_BLOCK.defaultBlockState(), UniformInt.of(2, 5)));
        FeatureUtils.register(context, COAL_BLACKSTONE_BLOBS, Feature.REPLACE_BLOBS, new ReplaceSphereConfiguration(Blocks.BLACKSTONE.defaultBlockState(), Blocks.COAL_BLOCK.defaultBlockState(), UniformInt.of(2, 5)));
        FeatureUtils.register(context, LAVA_ROCK_STONE_BLOBS, Feature.REPLACE_BLOBS, new ReplaceSphereConfiguration(Blocks.STONE.defaultBlockState(), MWBlocks.LAVA_ROCK.get().defaultBlockState(), UniformInt.of(2, 5)));
        FeatureUtils.register(context, LAVA_ROCK_BLACKSTONE_BLOBS, Feature.REPLACE_BLOBS, new ReplaceSphereConfiguration(Blocks.BLACKSTONE.defaultBlockState(), MWBlocks.LAVA_ROCK.get().defaultBlockState(), UniformInt.of(2, 5)));
        FeatureUtils.register(context, LAKE_LAVA_VOLCANIC_PEAK, Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(Blocks.LAVA.defaultBlockState()), BlockStateProvider.simple(MWBlocks.LAVA_ROCK.get().defaultBlockState())));
        FeatureUtils.register(context, LAKE_LAVA_MAGMA, Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(Blocks.LAVA.defaultBlockState()), BlockStateProvider.simple(Blocks.MAGMA_BLOCK.defaultBlockState())));
        FeatureUtils.register(context, LAKE_LAVA_BLACKSTONE, Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(Blocks.LAVA.defaultBlockState()), BlockStateProvider.simple(Blocks.BLACKSTONE.defaultBlockState())));
        FeatureUtils.register(context, LAKE_LAVA_COAL, Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(Blocks.LAVA.defaultBlockState()), BlockStateProvider.simple(Blocks.COAL_BLOCK.defaultBlockState())));
        FeatureUtils.register(context, APPLE_TREE, Feature.TREE, appleTree(false, false));
        FeatureUtils.register(context, APPLE_BEES_TREE, Feature.TREE, appleTree(false, true));
        FeatureUtils.register(context, FANCY_APPLE_TREE, Feature.TREE, appleTree(true, false));
        FeatureUtils.register(context, FANCY_APPLE_BEES_TREE, Feature.TREE, appleTree(true, true));
        FeatureUtils.register(context, PALM_TREE, Feature.TREE, palmTree(context));
        FeatureUtils.register(context, DEAD_TREE, Feature.TREE, deadTree(false));
        FeatureUtils.register(context, FANCY_DEAD_TREE, Feature.TREE, deadTree(true));
        FeatureUtils.register(context, SCULK_TREE, Feature.TREE, sculkTree());
        FeatureUtils.register(context, BONE_SPIKE, MWFeatures.BONE_SPIKE.get());
        FeatureUtils.register(context, FALLEN_OAK_TREE, MWFeatures.FALLEN_TREE.get(), fallenTree(Blocks.OAK_LOG, MWBlocks.HOLLOW_OAK_LOG.get(), true));
        FeatureUtils.register(context, FALLEN_OAK_TREE_SWAMP, MWFeatures.FALLEN_TREE.get(), fallenTree(Blocks.OAK_LOG, MWBlocks.HOLLOW_OAK_LOG.get(), false));
        FeatureUtils.register(context, FALLEN_BIRCH_TREE, MWFeatures.FALLEN_TREE.get(), fallenTree(Blocks.BIRCH_LOG, MWBlocks.HOLLOW_BIRCH_LOG.get(), false));
        FeatureUtils.register(context, FALLEN_SPRUCE_TREE, MWFeatures.FALLEN_TREE.get(), fallenTree(Blocks.SPRUCE_LOG, MWBlocks.HOLLOW_SPRUCE_LOG.get(), true));
        FeatureUtils.register(context, FALLEN_JUNGLE_TREE, MWFeatures.FALLEN_TREE.get(), fallenTree(Blocks.JUNGLE_LOG, MWBlocks.HOLLOW_JUNGLE_LOG.get(), false));
        FeatureUtils.register(context, FALLEN_ACACIA_TREE, MWFeatures.FALLEN_TREE.get(), fallenTree(Blocks.ACACIA_LOG, MWBlocks.HOLLOW_ACACIA_LOG.get(), true));
        FeatureUtils.register(context, FALLEN_DARK_OAK_TREE, MWFeatures.FALLEN_TREE.get(), fallenTree(Blocks.DARK_OAK_LOG, MWBlocks.HOLLOW_DARK_OAK_LOG.get(), true));
        FeatureUtils.register(context, FALLEN_MANGROVE_TREE, MWFeatures.FALLEN_TREE.get(), fallenTree(Blocks.MANGROVE_LOG, MWBlocks.HOLLOW_MANGROVE_LOG.get(), false));
        FeatureUtils.register(context, FALLEN_CHERRY_TREE, MWFeatures.FALLEN_TREE.get(), fallenTree(Blocks.CHERRY_LOG, MWBlocks.HOLLOW_CHERRY_LOG.get(), true));
        FeatureUtils.register(context, FALLEN_APPLE_TREE, MWFeatures.FALLEN_TREE.get(), fallenTree(MWBlocks.APPLE_LOG.get(), MWBlocks.HOLLOW_APPLE_LOG.get(), true));
        FeatureUtils.register(context, FALLEN_PALM_TREE, MWFeatures.FALLEN_TREE.get(), fallenTree(MWBlocks.PALM_LOG.get(), MWBlocks.HOLLOW_PALM_LOG.get(), true));
        FeatureUtils.register(context, FALLEN_DEAD_TREE, MWFeatures.FALLEN_TREE.get(), fallenTree(MWBlocks.DEAD_LOG.get(), MWBlocks.HOLLOW_DEAD_LOG.get(), true));
    }

    /**
     * Get the {@link TreeConfiguration apple tree configuration}
     *
     * @param fancy {@link Boolean If the tree should be a large tree}
     * @param withBeeHive {@link Boolean If the tree should have a bee hive}
     * @return {@link TreeConfiguration The apple tree configuration}
     */
    private static TreeConfiguration appleTree(final boolean fancy, final boolean withBeeHive) {
        TreeConfiguration.TreeConfigurationBuilder builder = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(MWBlocks.APPLE_LOG.get()),
                fancy ? new FancyTrunkPlacer(3, 11, 0) : new StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.simple(MWBlocks.APPLE_LEAVES.get()),
                fancy ? new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4) : new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                fancy ? new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)) : new TwoLayersFeatureSize(1, 0, 1)
        ).ignoreVines();
        if(withBeeHive) {
            builder = builder.decorators(List.of(new BeehiveDecorator(0.05F)));
        }
        return builder.build();
    }

    /**
     * Get the {@link TreeConfiguration palm tree configuration}
     *
     * @return {@link TreeConfiguration The palm tree configuration}
     */
    private static TreeConfiguration palmTree(final BootstapContext<ConfiguredFeature<?, ?>> context) {
        final HolderGetter<Block> holderGetter = context.lookup(Registries.BLOCK);
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(MWBlocks.PALM_LOG.get()),
                new PalmTrunkPlacer(7, 4, 2),
                BlockStateProvider.simple(MWBlocks.PALM_LEAVES.get()),
                new PalmFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                new TwoLayersFeatureSize(1, 0, 1)
        ).ignoreVines().build();
    }

    /**
     * Get the {@link TreeConfiguration dead tree configuration}
     *
     * @param fancy {@link Boolean If the tree should be a large tree}
     * @return {@link TreeConfiguration The dead tree configuration}
     */
    private static TreeConfiguration deadTree(final boolean fancy) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(MWBlocks.DEAD_LOG.get()),
                fancy ? new FancyTrunkPlacer(3, 11, 0) : new StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.simple(Blocks.AIR),
                fancy ? new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4) : new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                fancy ? new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)) : new TwoLayersFeatureSize(1, 0, 1)
        ).ignoreVines().build();
    }

    /**
     * Get the {@link TreeConfiguration sculk tree configuration}
     *
     * @return {@link TreeConfiguration The sculk tree configuration}
     */
    private static TreeConfiguration sculkTree() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(MWBlocks.SCULK_LOG.get()),
                new CherryTrunkPlacer(7, 1, 0,
                        new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(ConstantInt.of(1), 1).add(ConstantInt.of(2), 1).add(ConstantInt.of(3), 1).build()),
                        UniformInt.of(2, 4), UniformInt.of(-4, -3), UniformInt.of(-1, 0)
                ),
                BlockStateProvider.simple(MWBlocks.SCULK_LEAVES.get()),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.25F, 0.5F, 0.16666667F, 0.33333334F),
                new TwoLayersFeatureSize(1, 0, 2)
        ).ignoreVines().build();
    }

    /**
     * Get a {@link FallenTreeConfiguration Fallen Tree Configuration}
     *
     * @param logBlock {@link Block The Log Block}
     * @param hollowLogBlock {@link Block The Hollow Log Block}
     * @param ignoreMoss {@link Boolean If the Fallen Tree should not generate moss}
     * @return {@link FallenTreeConfiguration The Fallen Tree Configuration}
     */
    private static FallenTreeConfiguration fallenTree(final Block logBlock, final Block hollowLogBlock, final boolean ignoreMoss) {
        return new FallenTreeConfiguration(BlockStateProvider.simple(logBlock), BlockStateProvider.simple(hollowLogBlock), ignoreMoss);
    }
}