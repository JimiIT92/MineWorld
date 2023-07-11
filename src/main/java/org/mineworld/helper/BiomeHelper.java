package org.mineworld.helper;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.mineworld.core.MWBiomes;
import org.mineworld.core.MWPlacedFeatures;

/**
 * Helper methods for {@link Biome biomes}
 */
public class BiomeHelper {

    /**
     * Generate the {@link MWBiomes#FROZEN_PLAINS frozen plains biome}
     *
     * @param placedFeatureHolder {@link HolderGetter<PlacedFeature> The placed feature holder}
     * @param carver {@link HolderGetter<ConfiguredWorldCarver> The wolrd carver holder}
     * @return {@link Biome The frozen plains biome}
     */
    public static Biome frozenPlains(HolderGetter<PlacedFeature> placedFeatureHolder, HolderGetter<ConfiguredWorldCarver<?>> carver) {
        MobSpawnSettings.Builder mobSpawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder(placedFeatureHolder, carver);
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

    /**
     * Generate the {@link MWBiomes#WASTELAND wasteland biome}
     *
     * @param placedFeatureHolder {@link HolderGetter<PlacedFeature> The placed feature holder}
     * @param carver {@link HolderGetter<ConfiguredWorldCarver> The wolrd carver holder}
     * @return {@link Biome The wasteland biome}
     */
    public static Biome wasteland(HolderGetter<PlacedFeature> placedFeatureHolder, HolderGetter<ConfiguredWorldCarver<?>> carver) {
        MobSpawnSettings.Builder mobSpawnSettings = new MobSpawnSettings.Builder();
        mobSpawnSettings.creatureGenerationProbability(0);

        BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder(placedFeatureHolder, carver);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultOres(biomeGenerationSettings);

        biomeGenerationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_NORMAL);
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(2.0F)
                .downfall(0.5F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(9868950)
                        .waterFogColor(11908533)
                        .fogColor(9868950)
                        .skyColor(9868950)
                        .foliageColorOverride(9868950)
                        .grassColorOverride(9868950)
                        .ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.00118093334F))
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()
                )
                .generationSettings(biomeGenerationSettings.build())
                .build();
    }

    /**
     * Generate the {@link MWBiomes#VOLCANIC_PEAK volcanic island biome}
     *
     * @param placedFeatureHolder {@link HolderGetter<PlacedFeature> The placed feature holder}
     * @param carver {@link HolderGetter<ConfiguredWorldCarver> The wolrd carver holder}
     * @return {@link Biome The volcanic island biome}
     */
    public static Biome volcanicPeak(HolderGetter<PlacedFeature> placedFeatureHolder, HolderGetter<ConfiguredWorldCarver<?>> carver) {
        MobSpawnSettings.Builder mobSpawnSettings = new MobSpawnSettings.Builder();
        mobSpawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.MAGMA_CUBE, 5, 1, 3));
        BiomeDefaultFeatures.commonSpawns(mobSpawnSettings);
        BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder(placedFeatureHolder, carver);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeGenerationSettings);
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.LAKES, MWPlacedFeatures.LAKE_LAVA_VOLCANIC_PEAK);
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.LAKES, MWPlacedFeatures.LAKE_LAVA_MAGMA);
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.LAKES, MWPlacedFeatures.LAKE_LAVA_COAL);
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.LAKES, MWPlacedFeatures.LAKE_LAVA_BLACKSTONE);
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, MWPlacedFeatures.MAGMA_STONE_BLOBS);
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, MWPlacedFeatures.MAGMA_BLACKSTONE_BLOBS);
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, MWPlacedFeatures.COAL_STONE_BLOBS);
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, MWPlacedFeatures.COAL_BLACKSTONE_BLOBS);
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, MWPlacedFeatures.LAVA_ROCK_STONE_BLOBS);
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, MWPlacedFeatures.LAVA_ROCK_BLACKSTONE_BLOBS);
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.FLUID_SPRINGS, MWPlacedFeatures.SPRING_LAVA_VOLCANIC_PEAK);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(2.0F)
                .downfall(0.5F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(2171169)
                        .waterFogColor(852224)
                        .fogColor(852224)
                        .skyColor(852224)
                        .foliageColorOverride(2171169)
                        .grassColorOverride(2171169)
                        .ambientParticle(new AmbientParticleSettings(ParticleTypes.ASH, 0.00236186668F))
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_BASALT_DELTAS))
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()
                )
                .generationSettings(biomeGenerationSettings.build())
                .build();
    }
}