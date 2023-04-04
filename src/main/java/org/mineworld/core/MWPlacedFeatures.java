package org.mineworld.core;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import org.mineworld.MineWorld;
import org.mineworld.util.KeyHelper;

import java.util.List;

/**
 * {@link MineWorld MineWorld} {@link PlacedFeature placed features}
 */
public final class MWPlacedFeatures {

    //#region Placed Features

    public static final ResourceKey<PlacedFeature> ORE_SILVER_UPPER = registerKey("ore_silver_upper");
    public static final ResourceKey<PlacedFeature> ORE_SILVER_MIDDLE = registerKey("ore_silver_middle");
    public static final ResourceKey<PlacedFeature> ORE_SILVER_SMALL = registerKey("ore_silver_small");
    public static final ResourceKey<PlacedFeature> ORE_ALUMINUM_UPPER = registerKey("ore_aluminum_upper");
    public static final ResourceKey<PlacedFeature> ORE_ALUMINUM_MIDDLE = registerKey("ore_aluminum_middle");
    public static final ResourceKey<PlacedFeature> ORE_ALUMINUM_SMALL = registerKey("ore_aluminum_small");
    public static final ResourceKey<PlacedFeature> ORE_RUBY = registerKey("ore_ruby");
    public static final ResourceKey<PlacedFeature> ORE_SAPPHIRE = registerKey("ore_sapphire");
    public static final ResourceKey<PlacedFeature> ORE_PYRITE = registerKey("ore_pyrite");

    //#endregion

    /**
     * Register the {@link PlacedFeature placed features}
     *
     * @param context {@link BootstapContext<PlacedFeature> The bootstrap context}
     */
    public static void bootstrap(final BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        registerCommonOre(context, ORE_SILVER_UPPER, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_SILVER), 90, 80, 384);
        registerCommonOre(context, ORE_SILVER_MIDDLE, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_SILVER), 10, -24, 56);
        register(context, ORE_SILVER_SMALL, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_SILVER_SMALL), commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72))));
        registerCommonOre(context, ORE_ALUMINUM_UPPER, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_ALUMINUM), 90, 80, 384);
        registerCommonOre(context, ORE_ALUMINUM_MIDDLE, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_ALUMINUM), 10, -24, 56);
        register(context, ORE_ALUMINUM_SMALL, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_ALUMINUM_SMALL), commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72))));
        registerCommonOre(context, ORE_RUBY, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_RUBY), 100, -16, 480);
        registerCommonOre(context, ORE_SAPPHIRE, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_SAPPHIRE), 100, -16, 480);
        register(context, ORE_PYRITE, configuredFeatures.getOrThrow(MWConfiguredFeatures.ORE_PYRITE), commonOrePlacement(10, PlacementUtils.RANGE_10_10));
    }

    /**
     * Get the {@link List<PlacementModifier> ore placement modifiers}
     *
     * @param rarityPlacementModifier {@link PlacementModifier The rarity placement modifier. Affects the frequency of the ore}
     * @param heightPlacementModifier {@link PlacementModifier The height placement modifier. Affects the levels at which the ore can spawn}
     * @return {@link List<PlacementModifier> The ore placement modifiers}
     */
    private static List<PlacementModifier> orePlacement(final PlacementModifier rarityPlacementModifier, final PlacementModifier heightPlacementModifier) {
        return List.of(rarityPlacementModifier, InSquarePlacement.spread(), heightPlacementModifier, BiomeFilter.biome());
    }

    /**
     * Get the {@link List<PlacementModifier> ore placement modifiers} for a common ore
     *
     * @param count {@link Integer How many ores per chunk should be generated}
     * @param heightPlacementModifier {@link PlacementModifier The height placement modifier. Affects the levels at which the ore can spawn}
     * @return {@link List<PlacementModifier> The common ore placement modifiers}
     */
    private static List<PlacementModifier> commonOrePlacement(final int count, final PlacementModifier heightPlacementModifier) {
        return orePlacement(CountPlacement.of(count), heightPlacementModifier);
    }

    /**
     * Get the {@link List<PlacementModifier> ore placement modifiers} for a rare ore
     *
     * @param chance {@link Integer The rarity, in chunks, for the ore to be placed (eg: 1 in X chunks)}
     * @param heightPlacementModifier {@link PlacementModifier The height placement modifier. Affects the levels at which the ore can spawn}
     * @return {@link List<PlacementModifier> The rare ore placement modifiers}
     */
    private static List<PlacementModifier> rareOrePlacement(final int chance, final PlacementModifier heightPlacementModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), heightPlacementModifier);
    }

    /**
     * Register a common ore using the {@link HeightRangePlacement#triangle triangle height range placement}
     * with the {@link VerticalAnchor#absolute absolute values set}
     *
     * @param context {@link BootstapContext<PlacedFeature> The bootstrap context}
     * @param key {@link ResourceKey<PlacedFeature> The placed feature resource key}
     * @param oreConfiguredFeature {@link Holder<ConfiguredFeature> The ore feature that this placed feature will place}
     * @param count {@link Integer How many ores per chunk should be generated}
     * @param minHeight {@link Integer The minimum height that the ore can spawn}
     * @param maxHeight {@link Integer The maximum height that the ore can spawn}
     */
    private static void registerCommonOre(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> oreConfiguredFeature, final int count, final int minHeight, final int maxHeight) {
        register(context, key, oreConfiguredFeature, commonOrePlacement(count, HeightRangePlacement.triangle(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight))));
    }

    /**
     * Register a rare ore using the {@link HeightRangePlacement#triangle triangle height range placement}
     * with the {@link VerticalAnchor#aboveBottom above bottom} and {@link VerticalAnchor#belowTop below top} values set
     *
     * @param context {@link BootstapContext<PlacedFeature> The bootstrap context}
     * @param key {@link ResourceKey<PlacedFeature> The placed feature resource key}
     * @param oreConfiguredFeature {@link Holder<ConfiguredFeature> The ore feature that this placed feature will place}
     * @param chance {@link Integer The rarity, in chunks, for the ore to be placed (eg: 1 in X chunks)}
     * @param minHeight {@link Integer The minimum height that the ore can spawn}
     * @param maxHeight {@link Integer The maximum height that the ore can spawn}
     */
    private static void registerRareOre(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> oreConfiguredFeature, final int chance, final int minHeight, final int maxHeight) {
        register(context, key, oreConfiguredFeature, rareOrePlacement(chance, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(minHeight), VerticalAnchor.belowTop(maxHeight))));
    }

    /**
     * Register a {@link PlacedFeature placed feature}
     *
     * @param context {@link BootstapContext<PlacedFeature> The bootstrap context}
     * @param key {@link ResourceKey<PlacedFeature> The placed feature resource key}
     * @param configuredFeatureHolder {@link Holder<ConfiguredFeature> The configured feature that this placed feature will place}
     * @param placementModifiers {@link List<PlacementModifier> The placement modifiers}
     */
    private static void register(final BootstapContext<PlacedFeature> context, final ResourceKey<PlacedFeature> key, final Holder<ConfiguredFeature<?, ?>> configuredFeatureHolder, final List<PlacementModifier> placementModifiers) {
        PlacementUtils.register(context, key, configuredFeatureHolder, placementModifiers);
    }

    /**
     * Register a {@link ResourceKey resource key} for a {@link PlacedFeature placed feature}
     *
     * @param name {@link String The placed feature name}
     * @return {@link ResourceKey<PlacedFeature> Configured feature resource key}
     */
    private static ResourceKey<PlacedFeature> registerKey(final String name) {
        return KeyHelper.register(Registries.PLACED_FEATURE, name + "_placed");
    }
}
