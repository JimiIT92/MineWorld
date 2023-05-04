package org.mineworld.helper;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.mineworld.core.MWBiomes;

/**
 * Helper methods for {@link Biome biomes}
 */
public final class BiomeHelper {

    /**
     * Generate the {@link MWBiomes#FROZEN_PLAINS frozen plains biome}
     *
     * @param placedFeatureHolder {@link HolderGetter<PlacedFeature> The placed feature holder}
     * @param carver {@link HolderGetter<ConfiguredWorldCarver> The wolrd carver holder}
     * @return {@link Biome The frozen plains biome}
     */
    public static Biome frozenPlains(final HolderGetter<PlacedFeature> placedFeatureHolder, final HolderGetter<ConfiguredWorldCarver<?>> carver) {
        final MobSpawnSettings.Builder mobSpawnSettings = new MobSpawnSettings.Builder();
        final BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder(placedFeatureHolder, carver);
        OverworldBiomes.globalOverworldGeneration(biomeGenerationSettings);
        mobSpawnSettings.creatureGenerationProbability(0.07F);
        BiomeDefaultFeatures.snowySpawns(mobSpawnSettings);
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, MiscOverworldPlacements.ICE_SPIKE);
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, MiscOverworldPlacements.ICE_PATCH);
        BiomeDefaultFeatures.addDefaultOres(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeGenerationSettings);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(-5.0F)
                .temperatureAdjustment(Biome.TemperatureModifier.FROZEN)
                .downfall(0.5F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(10468857)
                        .waterFogColor(14079743)
                        .fogColor(14408667)
                        .skyColor(13092807)
                        .foliageColorOverride(16777215)
                        .grassColorOverride(16777215)
                        .ambientParticle(new AmbientParticleSettings(ParticleTypes.SNOWFLAKE, 0.00118093334F))
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()
                )
                .mobSpawnSettings(mobSpawnSettings.build())
                .generationSettings(biomeGenerationSettings.build())
        .build();
    }
}