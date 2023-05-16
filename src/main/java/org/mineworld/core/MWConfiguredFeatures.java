package org.mineworld.core;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
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
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.material.Fluids;
import org.mineworld.MineWorld;
import org.mineworld.block.BlueberryBushBlock;
import org.mineworld.helper.KeyHelper;
import org.mineworld.helper.RegisterHelper;
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
     * @return {@link TreeConfiguration The apple tree configuration}
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
     * Get the {@link TreeConfiguration palm tree configuration}
     *
     * @param fancy {@link Boolean If the tree should be a large tree}
     * @return {@link TreeConfiguration The apple tree configuration}
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

}