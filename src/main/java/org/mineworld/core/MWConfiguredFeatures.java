package org.mineworld.core;

import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.mineworld.MineWorld;
import org.mineworld.helper.KeyHelper;
import org.mineworld.helper.RegisterHelper;

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
    }

}