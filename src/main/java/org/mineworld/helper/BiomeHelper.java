package org.mineworld.helper;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.resources.ResourceKey;
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
     * Get a {@link Biome} from the registry only if exists or is a vanilla biome
     *
     * @param biomeRegistry {@link Registry<Biome> The biome registry}
     * @param biomes {@link ResourceKey<Biome> The biomes}
     * @return {@link ResourceKey<Biome> The biome}
     */
    @SafeVarargs
    public static ResourceKey<Biome> getBiome(final Registry<Biome> biomeRegistry, final ResourceKey<Biome>... biomes)
    {
        for (ResourceKey<Biome> key : biomes) {
            if (key == null) {
                continue;
            }
            if (key.location().getNamespace().equals("minecraft")) {
                return key;
            }
        }

        throw new RuntimeException("Failed to find fallback for biome!");
    }

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
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, MiscOverworldPlacements.BLUE_ICE);
        BiomeDefaultFeatures.addDefaultOres(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeGenerationSettings);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(-1.0F)
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