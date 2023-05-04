package org.mineworld.core;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.IEventBus;
import org.mineworld.MineWorld;
import org.mineworld.helper.BiomeHelper;
import org.mineworld.helper.KeyHelper;
import org.mineworld.helper.RegisterHelper;

/**
 * {@link MineWorld MineWorld} {@link Biome biomes}
 */
public final class MWBiomes {

    public static final ResourceKey<Biome> FROZEN_PLAINS = KeyHelper.registerBiome("frozen_plains");

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
    public static void bootstrap(final BootstapContext<Biome> context) {
        final HolderGetter<PlacedFeature> placedFeatureHolder = context.lookup(Registries.PLACED_FEATURE);
        final HolderGetter<ConfiguredWorldCarver<?>> carver = context.lookup(Registries.CONFIGURED_CARVER);
        context.register(FROZEN_PLAINS, BiomeHelper.frozenPlains(placedFeatureHolder, carver));
    }

    /**
     * Register the {@link MineWorld MineWorld} overworld biomes
     */
    public static void registerOverworldBiomes() {
        BiomeManager.addBiome(BiomeManager.BiomeType.ICY, new BiomeManager.BiomeEntry(FROZEN_PLAINS, 3000));
    }

}