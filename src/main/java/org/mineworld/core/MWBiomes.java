package org.mineworld.core;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.mineworld.MineWorld;
import org.mineworld.helper.BiomeHelper;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.world.biome.MWOverworldRegion;
import org.mineworld.world.biome.MWSurfaceRuleData;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

/**
 * {@link MineWorld MineWorld} {@link Biome Biomes}
 */
public final class MWBiomes {

    //#region Biomes

    public static final ResourceKey<Biome> FROZEN_PLAINS = registerBiomeKey("frozen_plains");
    public static final ResourceKey<Biome> WASTELAND = registerBiomeKey("wasteland");
    public static final ResourceKey<Biome> VOLCANIC_PEAKS = registerBiomeKey("volcanic_peaks");
    public static final ResourceKey<Biome> FOSSILS_WASTELAND = registerBiomeKey("fossils_wasteland");
    public static final ResourceKey<Biome> ETHEREAL_FIELDS = registerBiomeKey("ethereal_fields");
    public static final ResourceKey<Biome> ETHEREAL_DARK = registerBiomeKey("ethereal_dark");
    public static final ResourceKey<Biome> ECHOING_WOODS = registerBiomeKey("echoing_woods");
    public static final ResourceKey<Biome> ANCIENT_LANDS = registerBiomeKey("ancient_lands");

    //#endregion

    //#region Methods

    /**
     * Register a {@link Biome Biome} {@link ResourceKey<Biome> Resource Key}
     *
     * @param name {@link String The Biome name}
     * @return {@link ResourceKey<Biome> The Biome Resource Key}
     */
    private static ResourceKey<Biome> registerBiomeKey(final String name) {
        return RegistryHelper.register(Registries.BIOME, name);
    }

    /**
     * Register a {@link Biome Biome}
     *
     * @param context {@link BootstapContext<Biome> The Bootstrap Context}
     * @param biomeKey {@link ResourceKey<Biome> The Biome Resource Key}
     * @param biome {@link Biome The Biome to register}
     */
    private static void registerBiome(final BootstapContext<Biome> context, final ResourceKey<Biome> biomeKey, final Biome biome) {
        context.register(biomeKey, biome);
    }

    //#endregion

    //#region Register

    /**
     * Register all {@link Biome Biomes}
     *
     * @param context {@link BootstapContext<Biome> The Bootstrap Context}
     */
    public static void bootstrap(final BootstapContext<Biome> context) {
        final HolderGetter<PlacedFeature> placedFeatureHolder = context.lookup(Registries.PLACED_FEATURE);
        final HolderGetter<ConfiguredWorldCarver<?>> carver = context.lookup(Registries.CONFIGURED_CARVER);
        registerBiome(context, FROZEN_PLAINS, BiomeHelper.frozenPlains(placedFeatureHolder, carver));
        registerBiome(context, WASTELAND, BiomeHelper.wasteland(placedFeatureHolder, carver));
        registerBiome(context, VOLCANIC_PEAKS, BiomeHelper.volcanicPeak(placedFeatureHolder, carver));
        registerBiome(context, FOSSILS_WASTELAND, BiomeHelper.wasteland(placedFeatureHolder, carver));
        registerBiome(context, ETHEREAL_FIELDS, BiomeHelper.etherealFields(placedFeatureHolder, carver));
        registerBiome(context, ETHEREAL_DARK, OverworldBiomes.deepDark(placedFeatureHolder, carver));
        registerBiome(context, ECHOING_WOODS, BiomeHelper.echoingWoods(placedFeatureHolder, carver));
        registerBiome(context, ANCIENT_LANDS, BiomeHelper.ancientLands(placedFeatureHolder, carver));
    }

    /**
     * Register all the {@link MineWorld MineWorld} Overworld Biomes
     */
    public static void registerOverworldBiomes() {
        Regions.register(new MWOverworldRegion());
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MineWorld.MOD_ID, MWSurfaceRuleData.makeRules());
    }

    //#endregion

}