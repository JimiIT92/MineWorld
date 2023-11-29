package org.mineworld.core;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import org.mineworld.MineWorld;
import org.mineworld.helper.BiomeHelper;
import org.mineworld.helper.KeyHelper;
import org.mineworld.helper.RegisterHelper;
import org.mineworld.world.MWSurfaceRuleData;
import org.mineworld.world.overworld.MWOverworldRegion;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

/**
 * {@link MineWorld MineWorld} {@link Biome biomes}
 */
public final class MWBiomes {

    public static final ResourceKey<Biome> FROZEN_PLAINS = KeyHelper.registerBiome("frozen_plains");
    public static final ResourceKey<Biome> WASTELAND = KeyHelper.registerBiome("wasteland");
    public static final ResourceKey<Biome> VOLCANIC_PEAK = KeyHelper.registerBiome("volcanic_peak");
    public static final ResourceKey<Biome> FOSSILS_WASTELAND = KeyHelper.registerBiome("fossils_wasteland");

    /**
     * Register the {@link MineWorld MineWorld} {@link Biome biomes}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(final IEventBus eventBus) {
        RegisterHelper.registerBiomes(eventBus);
    }

    /**
     * Register the {@link Biome biomes}
     *
     * @param context {@link BootstapContext<Biome> The bootstrap context}
     */
    public static void bootstrapBiomes(final BootstapContext<Biome> context) {
        final HolderGetter<PlacedFeature> placedFeatureHolder = context.lookup(Registries.PLACED_FEATURE);
        final HolderGetter<ConfiguredWorldCarver<?>> carver = context.lookup(Registries.CONFIGURED_CARVER);
        context.register(FROZEN_PLAINS, BiomeHelper.frozenPlains(placedFeatureHolder, carver));
        context.register(WASTELAND, BiomeHelper.wasteland(placedFeatureHolder, carver));
        context.register(VOLCANIC_PEAK, BiomeHelper.volcanicPeak(placedFeatureHolder, carver));
        context.register(FOSSILS_WASTELAND, BiomeHelper.wasteland(placedFeatureHolder, carver));
    }

    /**
     * Register the {@link MineWorld MineWorld} overworld biomes
     */
    public static void registerOverworldBiomes() {
        Regions.register(new MWOverworldRegion());
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MineWorld.MOD_ID, MWSurfaceRuleData.makeRules());
    }

}