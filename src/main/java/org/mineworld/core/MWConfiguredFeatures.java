package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import org.mineworld.MineWorld;
import org.mineworld.util.KeyHelper;

import java.util.List;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link ConfiguredFeature configured features}
 */
public final class MWConfiguredFeatures {

    //#region Configured Features

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SILVER = registerKey("ore_silver");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SILVER_SMALL = registerKey("ore_silver_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ALUMINUM = registerKey("ore_aluminum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ALUMINUM_SMALL = registerKey("ore_aluminum_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY = registerKey("ore_ruby");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE = registerKey("ore_sapphire");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PYRITE = registerKey("ore_pyrite");

    //#endregion

    /**
     * Register the {@link ConfiguredFeature configured features}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     */
    public static void bootstrap(final BootstapContext<ConfiguredFeature<?, ?>> context) {
        registerOverworldOre(context, ORE_SILVER, MWBlocks.SILVER_ORE, MWBlocks.DEEPSLATE_SILVER_ORE, 9);
        registerOverworldOre(context, ORE_SILVER_SMALL, MWBlocks.SILVER_ORE, MWBlocks.DEEPSLATE_SILVER_ORE, 4);
        registerOverworldOre(context, ORE_ALUMINUM, MWBlocks.ALUMINUM_ORE, MWBlocks.DEEPSLATE_ALUMINUM_ORE, 9);
        registerOverworldOre(context, ORE_ALUMINUM_SMALL, MWBlocks.ALUMINUM_ORE, MWBlocks.DEEPSLATE_ALUMINUM_ORE, 4);
        registerOverworldOre(context, ORE_RUBY, MWBlocks.RUBY_ORE, MWBlocks.DEEPSLATE_RUBY_ORE, 3);
        registerOverworldOre(context, ORE_SAPPHIRE, MWBlocks.SAPPHIRE_ORE, MWBlocks.DEEPSLATE_SAPPHIRE_ORE, 3);
        registerNetherOre(context, ORE_PYRITE, MWBlocks.PYRITE_ORE, 17);
    }

    /**
     * Get the Overworld {@link OreConfiguration.TargetBlockState target block states}
     *
     * @param stoneOreSupplier {@link Supplier<Block> The stone ore block supplier}
     * @param deepslateOreSupplier {@link Supplier<Block> The deepslate ore block supplier}
     * @return {@link OreConfiguration.TargetBlockState Overworld target block states}
     */
    private static Supplier<List<OreConfiguration.TargetBlockState>> createOverworldTargetStates(final Supplier<Block> stoneOreSupplier, final Supplier<Block> deepslateOreSupplier) {
        return Suppliers.memoize(() -> List.of(
                OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), stoneOreSupplier.get().defaultBlockState()),
                OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), deepslateOreSupplier.get().defaultBlockState())));
    }

    /**
     * Get the Nether {@link OreConfiguration.TargetBlockState target block states}
     *
     * @param oreSupplier {@link Supplier<Block> The ore block supplier}
     * @return {@link OreConfiguration.TargetBlockState Nether target block states}
     */
    private static Supplier<List<OreConfiguration.TargetBlockState>> createNetherTargetState(final Supplier<Block> oreSupplier) {
        return Suppliers.memoize(() -> List.of(OreConfiguration.target(new TagMatchTest(BlockTags.BASE_STONE_NETHER), oreSupplier.get().defaultBlockState())));
    }

    /**
     * Register an Overworld {@link ConfiguredFeature ore configured feature}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey The configured feature resource key}
     * @param stoneOre {@link Supplier<Block> The stone ore block supplier}
     * @param deepslateOre {@link Supplier<Block> The deepslate ore block supplier}
     * @param size {@link Integer The number of blocks per vein}
     */
    private static void registerOverworldOre(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final Supplier<Block> stoneOre, final Supplier<Block> deepslateOre, final int size) {
        registerOre(context, key, createOverworldTargetStates(stoneOre, deepslateOre), size);
    }

    /**
     * Register a Nether {@link ConfiguredFeature ore configured feature}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey The configured feature resource key}
     * @param oreSupplier {@link Supplier<Block> The ore block supplier}
     * @param size {@link Integer The number of blocks per vein}
     */
    private static void registerNetherOre(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final Supplier<Block> oreSupplier, final int size) {
        registerOre(context, key, createNetherTargetState(oreSupplier), size);
    }

    /**
     * Register an {@link ConfiguredFeature ore configured feature}
     *
     * @param context {@link BootstapContext<ConfiguredFeature> The bootstrap context}
     * @param key {@link ResourceKey The configured feature resource key}
     * @param oreTargetStates {@link List<OreConfiguration.TargetBlockState> The ore block target states}
     * @param size {@link Integer The number of blocks per vein}
     */
    private static void registerOre(final BootstapContext<ConfiguredFeature<?, ?>> context, final ResourceKey<ConfiguredFeature<?, ?>> key, final Supplier<List<OreConfiguration.TargetBlockState>> oreTargetStates, final int size) {
        FeatureUtils.register(context, key, Feature.ORE, new OreConfiguration(oreTargetStates.get(), size));
    }

    /**
     * Register a {@link ResourceKey resource key} for a {@link ConfiguredFeature configured feature}
     *
     * @param name {@link String The configured feature name}
     * @return {@link ResourceKey<ConfiguredFeature> Configured feature resource key}
     */
    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(final String name) {
        return KeyHelper.register(Registries.CONFIGURED_FEATURE, name);
    }
}
