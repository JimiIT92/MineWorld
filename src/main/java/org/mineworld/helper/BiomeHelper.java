package org.mineworld.helper;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Music;
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
 * Helper class for {@link Biome Biomes}
 */
public final class BiomeHelper {

    /**
     * Configure the {@link MWBiomes#FROZEN_PLAINS Frozen Plains Biome}
     *
     * @param placedFeatureHolder {@link HolderGetter<PlacedFeature> The placed feature holder}
     * @param carver {@link HolderGetter<ConfiguredWorldCarver> The world carver holder}
     * @return {@link Biome The Frozen Plains biome}
     */
    public static Biome frozenPlains(final HolderGetter<PlacedFeature> placedFeatureHolder, final HolderGetter<ConfiguredWorldCarver<?>> carver) {
        final MobSpawnSettings.Builder mobSpawnSettings = new MobSpawnSettings.Builder().creatureGenerationProbability(0.07F);
        final BiomeGenerationSettings.Builder biomeGenerationSettings =
                new BiomeGenerationSettings.Builder(placedFeatureHolder, carver)
                        .addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, MiscOverworldPlacements.ICE_SPIKE)
                        .addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, MiscOverworldPlacements.ICE_PATCH)
                        .addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, MiscOverworldPlacements.BLUE_ICE);
        OverworldBiomes.globalOverworldGeneration(biomeGenerationSettings);
        BiomeDefaultFeatures.snowySpawns(mobSpawnSettings);
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
     * Configure the {@link MWBiomes#WASTELAND Wasteland Biome}
     *
     * @param placedFeatureHolder {@link HolderGetter<PlacedFeature> The placed feature holder}
     * @param carver {@link HolderGetter<ConfiguredWorldCarver> The world carver holder}
     * @return {@link Biome The Wasteland biome}
     */
    public static Biome wasteland(final HolderGetter<PlacedFeature> placedFeatureHolder, final HolderGetter<ConfiguredWorldCarver<?>> carver) {
        final MobSpawnSettings.Builder mobSpawnSettings = new MobSpawnSettings.Builder().creatureGenerationProbability(0);
        final BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder(placedFeatureHolder, carver)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_NORMAL)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultOres(biomeGenerationSettings);

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
     * Configure the {@link MWBiomes#VOLCANIC_PEAKS Volcanic Peak Biome}
     *
     * @param placedFeatureHolder {@link HolderGetter<PlacedFeature> The placed feature holder}
     * @param carver {@link HolderGetter<ConfiguredWorldCarver> The world carver holder}
     * @return {@link Biome The Volcanic Peak biome}
     */
    public static Biome volcanicPeak(final HolderGetter<PlacedFeature> placedFeatureHolder, final HolderGetter<ConfiguredWorldCarver<?>> carver) {
        final MobSpawnSettings.Builder mobSpawnSettings = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.MAGMA_CUBE, 5, 1, 3));
        final BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder(placedFeatureHolder, carver)
                .addFeature(GenerationStep.Decoration.LAKES, MWPlacedFeatures.LAKE_LAVA_VOLCANIC_PEAK)
                .addFeature(GenerationStep.Decoration.LAKES, MWPlacedFeatures.LAKE_LAVA_MAGMA)
                .addFeature(GenerationStep.Decoration.LAKES, MWPlacedFeatures.LAKE_LAVA_COAL)
                .addFeature(GenerationStep.Decoration.LAKES, MWPlacedFeatures.LAKE_LAVA_BLACKSTONE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, MWPlacedFeatures.MAGMA_STONE_BLOBS)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, MWPlacedFeatures.MAGMA_BLACKSTONE_BLOBS)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, MWPlacedFeatures.COAL_STONE_BLOBS)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, MWPlacedFeatures.COAL_BLACKSTONE_BLOBS)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, MWPlacedFeatures.LAVA_ROCK_STONE_BLOBS)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, MWPlacedFeatures.LAVA_ROCK_BLACKSTONE_BLOBS)
                .addFeature(GenerationStep.Decoration.FLUID_SPRINGS, MWPlacedFeatures.SPRING_LAVA_VOLCANIC_PEAK);
        BiomeDefaultFeatures.commonSpawns(mobSpawnSettings);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeGenerationSettings);

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

    /**
     * Configure the {@link MWBiomes#ETHEREAL_FIELDS Ethereal Fields Biome}
     *
     * @param placedFeatureHolder {@link HolderGetter<PlacedFeature> The placed feature holder}
     * @param carver {@link HolderGetter<ConfiguredWorldCarver> The world carver holder}
     * @return {@link Biome The Ethereal Fields biome}
     */
    public static Biome etherealFields(final HolderGetter<PlacedFeature> placedFeatureHolder, final HolderGetter<ConfiguredWorldCarver<?>> carver) {
        final MobSpawnSettings.Builder mobSpawnSettings = new MobSpawnSettings.Builder().creatureGenerationProbability(0.07F)
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.PHANTOM, 5, 1, 2))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 10, 1, 2));
        final BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder(placedFeatureHolder, carver)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CavePlacements.SCULK_PATCH_ANCIENT_CITY)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CavePlacements.SCULK_PATCH_DEEP_DARK);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(1.0F)
                .downfall(0F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0)
                        .waterFogColor(329011)
                        .fogColor(0)
                        .skyColor(329011)
                        .foliageColorOverride(329011)
                        .grassColorOverride(329011)
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DEEP_DARK))
                        .ambientParticle(new AmbientParticleSettings(ParticleTypes.SCULK_SOUL, 0.00118093334F))
                        .build()
                )
                .mobSpawnSettings(mobSpawnSettings.build())
                .generationSettings(biomeGenerationSettings.build())
                .build();
    }

    /**
     * Configure the {@link MWBiomes#ECHOING_WOODS Echoing Woods Biome}
     *
     * @param placedFeatureHolder {@link HolderGetter<PlacedFeature> The placed feature holder}
     * @param carver {@link HolderGetter<ConfiguredWorldCarver> The world carver holder}
     * @return {@link Biome The Echoing Woods biome}
     */
    public static Biome echoingWoods(final HolderGetter<PlacedFeature> placedFeatureHolder, final HolderGetter<ConfiguredWorldCarver<?>> carver) {
        final MobSpawnSettings.Builder mobSpawnSettings = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.VEX, 50, 1, 4))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 50, 1, 2));
        final BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder(placedFeatureHolder, carver);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(1.0F)
                .downfall(0F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0)
                        .waterFogColor(329011)
                        .fogColor(0)
                        .skyColor(329011)
                        .foliageColorOverride(329011)
                        .grassColorOverride(329011)
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DEEP_DARK))
                        .build()
                )
                .mobSpawnSettings(mobSpawnSettings.build())
                .generationSettings(biomeGenerationSettings.build())
                .build();
    }

    /**
     * Configure the {@link MWBiomes#ANCIENT_LANDS Ancient Lands Biome}
     *
     * @param placedFeatureHolder {@link HolderGetter<PlacedFeature> The placed feature holder}
     * @param carver {@link HolderGetter<ConfiguredWorldCarver> The world carver holder}
     * @return {@link Biome The Ancient Lands biome}
     */
    public static Biome ancientLands(final HolderGetter<PlacedFeature> placedFeatureHolder, final HolderGetter<ConfiguredWorldCarver<?>> carver) {
        final MobSpawnSettings.Builder mobSpawnSettings = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 25, 1, 2));
                //.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(MWEntityTypes.REAPER.get(), 50, 1, 2));
        final BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder(placedFeatureHolder, carver);
        final Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DEEP_DARK);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(1.0F)
                .downfall(0F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(329011)
                        .waterFogColor(329011)
                        .fogColor(0)
                        .skyColor(0)
                        .foliageColorOverride(329011)
                        .grassColorOverride(329011)
                        .backgroundMusic(music)
                        .ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.00118093334F))
                        .build()
                )
                .mobSpawnSettings(mobSpawnSettings.build())
                .generationSettings(biomeGenerationSettings.build())
                .build();
    }

}