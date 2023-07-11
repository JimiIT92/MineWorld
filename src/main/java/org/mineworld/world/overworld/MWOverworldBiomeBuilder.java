package org.mineworld.world.overworld;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBiomes;
import terrablender.api.ParameterUtils;

import java.util.function.Consumer;

/**
 * {@link MineWorld MineWorld} biome builder.
 * Inspired by {@link OverworldBiomeBuilder vanilla overworld biome builder}
 * and <a href="https://github.com/Glitchfiend/BiomesOPlenty/blob/BOP-1.19.4-17.3.x/src/main/java/biomesoplenty/common/biome/BOPOverworldBiomeBuilder.java">Biomes O' Plenty</a>
 */
public class MWOverworldBiomeBuilder {

    /**
     * {@link Climate.Parameter The climate parameter full range value}
     */
    private Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);

    /**
     * {@link Climate.Parameter Biome temepratures}
     */
    private Climate.Parameter[] TEMPERATURES = new Climate.Parameter[]{
            ParameterUtils.Temperature.ICY.parameter(),
            ParameterUtils.Temperature.COOL.parameter(),
            ParameterUtils.Temperature.NEUTRAL.parameter(),
            ParameterUtils.Temperature.WARM.parameter(),
            ParameterUtils.Temperature.HOT.parameter()
    };

    /**
     * {@link Climate.Parameter Biome humidities}
     */
    private Climate.Parameter[] HUMIDITIES = new Climate.Parameter[]{
            ParameterUtils.Humidity.ARID.parameter(),
            ParameterUtils.Humidity.DRY.parameter(),
            ParameterUtils.Humidity.NEUTRAL.parameter(),
            ParameterUtils.Humidity.WET.parameter(),
            ParameterUtils.Humidity.HUMID.parameter()
    };

    /**
     * {@link Climate.Parameter Biome erosions}
     */
    private Climate.Parameter[] EROSIONS = new Climate.Parameter[]{
            ParameterUtils.Erosion.EROSION_0.parameter(),
            ParameterUtils.Erosion.EROSION_1.parameter(),
            ParameterUtils.Erosion.EROSION_2.parameter(),
            ParameterUtils.Erosion.EROSION_3.parameter(),
            ParameterUtils.Erosion.EROSION_4.parameter(),
            ParameterUtils.Erosion.EROSION_5.parameter(),
            ParameterUtils.Erosion.EROSION_6.parameter()
    };

    /**
     * {@link Climate.Parameter The range of temperatrues for frozen biomes}
     */
    private Climate.Parameter FROZEN_RANGE = this.TEMPERATURES[0];
    /**
     * {@link Climate.Parameter The range of temperatrues for non frozen biomes}
     */
    private Climate.Parameter UNFROZEN_RANGE = Climate.Parameter.span(this.TEMPERATURES[1], this.TEMPERATURES[4]);
    /**
     * {@link ParameterUtils.Continentalness The continentalness values for mushroom fields}
     */
    private Climate.Parameter MUSHROOM_FIELDS_CONTINENTALNESS = Climate.Parameter.span(-1.2F, -1.05F);
    /**
     * {@link ParameterUtils.Continentalness The continentalness values for deep ocean biomes}
     */
    private Climate.Parameter DEEP_OCEAN_CONTINENTALNESS = Climate.Parameter.span(-1.05F, -0.455F);
    /**
     * {@link ParameterUtils.Continentalness The continentalness values for ocean biomes}
     */
    private Climate.Parameter OCEAN_CONTINENTALNESS = Climate.Parameter.span(-0.455F, -0.19F);
    /**
     * {@link ParameterUtils.Continentalness The continentalness values for coast biomes}
     */
    private Climate.Parameter COAST_CONTINENTALNESS = Climate.Parameter.span(-0.19F, -0.11F);
    /**
     * {@link ParameterUtils.Continentalness The continentalness values for inland biomes}
     */
    private Climate.Parameter INLAND_CONTINENTALNESS = Climate.Parameter.span(-0.11F, 0.55F);
    /**
     * {@link ParameterUtils.Continentalness The continentalness values for near inland biomes}
     */
    private Climate.Parameter NEAR_INLAND_CONTINENTALNESS = Climate.Parameter.span(-0.11F, 0.03F);
    /**
     * {@link ParameterUtils.Continentalness The continentalness values for mid inland biomes}
     */
    private Climate.Parameter MID_INLAND_CONTINENTALNESS = Climate.Parameter.span(0.03F, 0.3F);
    /**
     * {@link ParameterUtils.Continentalness The continentalness values for far inland biomes}
     */
    private Climate.Parameter FAR_INLAND_CONTINENTALNESS = Climate.Parameter.span(0.3F, 1.0F);

    /**
     * {@link ResourceKey<Biome> Ocean biomes}
     */
    private ResourceKey<Biome>[][] OCEANS = new ResourceKey[][]{
            {Biomes.DEEP_FROZEN_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN},
            {Biomes.FROZEN_OCEAN,      Biomes.COLD_OCEAN,      Biomes.OCEAN,      Biomes.LUKEWARM_OCEAN,      Biomes.WARM_OCEAN}
    };

    /**
     * {@link ResourceKey<Biome> Middle biomes}
     */
    private ResourceKey<Biome>[][] MIDDLE_BIOMES = new ResourceKey[][]{
            {Biomes.SNOWY_PLAINS,  Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA,  Biomes.TAIGA},
            {Biomes.PLAINS,        Biomes.PLAINS,       Biomes.FOREST,       Biomes.TAIGA,        Biomes.OLD_GROWTH_SPRUCE_TAIGA},
            {Biomes.FLOWER_FOREST, Biomes.PLAINS,       Biomes.FOREST,       Biomes.BIRCH_FOREST, Biomes.DARK_FOREST},
            {Biomes.SAVANNA,       Biomes.SAVANNA,      Biomes.FOREST,       Biomes.JUNGLE,       Biomes.JUNGLE},
            {Biomes.DESERT,        Biomes.DESERT,       Biomes.DESERT,       Biomes.DESERT,       Biomes.DESERT}
    };

    /**
     * {@link ResourceKey<Biome> Middle biome variants}
     */
    private ResourceKey<Biome>[][] MIDDLE_BIOMES_VARIANT = new ResourceKey[][]{
            {Biomes.ICE_SPIKES,       null, Biomes.SNOWY_TAIGA, null,                           null},
            {null,                    null, null,               null,                           Biomes.OLD_GROWTH_PINE_TAIGA},
            {Biomes.SUNFLOWER_PLAINS, null, null,               Biomes.OLD_GROWTH_BIRCH_FOREST, null},
            {null,                    null, Biomes.PLAINS,      Biomes.SPARSE_JUNGLE,           Biomes.BAMBOO_JUNGLE},
            {null,                    null, null,               null,                           null}
    };

    /**
     * {@link ResourceKey<Biome> MineWorld middle biome variants}
     */
    private ResourceKey<Biome>[][] MW_MIDDLE_BIOMES_VARIANT = new ResourceKey[][]{
            {MWBiomes.FROZEN_PLAINS,    null,               null, null, null},
            {null,                      null,               null, null, null},
            {null,                      null,               null, null, null},
            {MWBiomes.WASTELAND,        MWBiomes.WASTELAND, null, null, null},
            {null,                      null,               null, null, null}
    };

    /**
     * {@link ResourceKey<Biome> Plateau biomes}
     */
    private ResourceKey<Biome>[][] PLATEAU_BIOMES = new ResourceKey[][]{
            {Biomes.SNOWY_PLAINS,    Biomes.SNOWY_PLAINS,    Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA,     Biomes.SNOWY_TAIGA},
            {Biomes.MEADOW,          Biomes.MEADOW,          Biomes.FOREST,       Biomes.TAIGA,           Biomes.OLD_GROWTH_SPRUCE_TAIGA},
            {Biomes.MEADOW,          Biomes.MEADOW,          Biomes.MEADOW,       Biomes.MEADOW,          Biomes.DARK_FOREST},
            {Biomes.SAVANNA_PLATEAU, Biomes.SAVANNA_PLATEAU, Biomes.FOREST,       Biomes.FOREST,          Biomes.JUNGLE},
            {Biomes.BADLANDS,        Biomes.BADLANDS,        Biomes.BADLANDS,     Biomes.WOODED_BADLANDS, Biomes.WOODED_BADLANDS}
    };

    /**
     * {@link ResourceKey<Biome> Plateau biome variants}
     */
    private ResourceKey<Biome>[][] PLATEAU_BIOMES_VARIANT = new ResourceKey[][]{
            {Biomes.ICE_SPIKES,      null,                   null,          null,                null},
            {null,                   null,                   Biomes.MEADOW, Biomes.MEADOW,       Biomes.OLD_GROWTH_PINE_TAIGA},
            {null,                   null,                   Biomes.FOREST, Biomes.BIRCH_FOREST, null},
            {MWBiomes.WASTELAND,     MWBiomes.WASTELAND,     null,          null,                null},
            {Biomes.ERODED_BADLANDS, Biomes.ERODED_BADLANDS, null,          null,                null}
    };

    /**
     * {@link ResourceKey<Biome> MineWorld Plateau biome variants}
     */
    private ResourceKey<Biome>[][] MW_PLATEAU_BIOMES_VARIANT = new ResourceKey[][]{
            {MWBiomes.FROZEN_PLAINS,    null, null, null, null},
            {null,                      null, null, null, null},
            {null,                      null, null, null, null},
            {null,                      null, null, null, null},
            {null,                      null, null, null, null}
    };

    /**
     * {@link ResourceKey<Biome> Extreme hills biomes}
     */
    private ResourceKey<Biome>[][] EXTREME_HILLS = new ResourceKey[][]{
            {Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST},
            {Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST},
            {Biomes.WINDSWEPT_HILLS,          Biomes.WINDSWEPT_HILLS,          Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST},
            {null,                            null,                            null,                   null,                    null},
            {null,                            null,                            null,                   null,                    null}
    };

    /**
     * Add biomes into the overworld
     *
     * @param mapper {@link Pair The biome mapper}
     */
    public void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addOffCoastBiomes(mapper);
        this.addInlandBiomes(mapper);
        this.addUndergroundBiomes(mapper);
    }

    /**
     * Add the off coast biomes into the overworld
     *
     * @param mapper {@link Pair The biome mapper}
     */
    private void addOffCoastBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addSurfaceBiome(mapper, this.FULL_RANGE, this.FULL_RANGE, this.MUSHROOM_FIELDS_CONTINENTALNESS, this.FULL_RANGE, this.FULL_RANGE, 0.0F, Biomes.MUSHROOM_FIELDS);

        for (int i = 0; i < this.TEMPERATURES.length; ++i) {
            Climate.Parameter temperature = this.TEMPERATURES[i];
            this.addSurfaceBiome(mapper, temperature, this.FULL_RANGE, this.DEEP_OCEAN_CONTINENTALNESS, this.FULL_RANGE, this.FULL_RANGE, 0.0F, this.OCEANS[0][i]);
            this.addSurfaceBiome(mapper, temperature, this.FULL_RANGE, this.OCEAN_CONTINENTALNESS, this.FULL_RANGE, this.FULL_RANGE, 0.0F, this.OCEANS[1][i]);
        }
    }

    /**
     * Add the inland biomes into the overworld
     *
     * @param mapper {@link Pair The biome mapper}
     */
    private void addInlandBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addMidSlice(mapper, Climate.Parameter.span(-1.0F, -0.93333334F));
        this.addHighSlice(mapper, Climate.Parameter.span(-0.93333334F, -0.7666667F));
        this.addPeaks(mapper, Climate.Parameter.span(-0.7666667F, -0.56666666F));
        this.addHighSlice(mapper, Climate.Parameter.span(-0.56666666F, -0.4F));
        this.addMidSlice(mapper, Climate.Parameter.span(-0.4F, -0.26666668F));
        this.addLowSlice(mapper, Climate.Parameter.span(-0.26666668F, -0.05F));
        this.addValleys(mapper, Climate.Parameter.span(-0.05F, 0.05F));
        this.addLowSlice(mapper, Climate.Parameter.span(0.05F, 0.26666668F));
        this.addMidSlice(mapper, Climate.Parameter.span(0.26666668F, 0.4F));
        this.addHighSlice(mapper, Climate.Parameter.span(0.4F, 0.56666666F));
        this.addPeaks(mapper, Climate.Parameter.span(0.56666666F, 0.7666667F));
        this.addHighSlice(mapper, Climate.Parameter.span(0.7666667F, 0.93333334F));
        this.addMidSlice(mapper, Climate.Parameter.span(0.93333334F, 1.0F));
    }

    /**
     * Add the peak biomes into the overworld
     *
     * @param mapper {@link Pair The biome mapper}
     * @param weirdness {@link Climate.Parameter The region weirdness}
     */
    private void addPeaks(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter weirdness) {
        for (int i = 0; i < this.TEMPERATURES.length; ++i) {
            Climate.Parameter temperature = this.TEMPERATURES[i];

            for (int j = 0; j < this.HUMIDITIES.length; ++j) {

                Climate.Parameter humidity = this.HUMIDITIES[j];
                ResourceKey<Biome> middleBiome = this.pickMiddleBiomeMW(i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiome = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, weirdness);
                ResourceKey<Biome> plateauBiome = this.pickPlateauBiomeMW(i, j, weirdness);
                ResourceKey<Biome> extremeHillsBiome = this.pickExtremeHillsBiome(i, j, weirdness);
                ResourceKey<Biome> shatteredBiome = this.pickShatteredBiome(i, j, weirdness, extremeHillsBiome);
                ResourceKey<Biome> peakBiome = this.pickPeakBiomeMW(i, j, weirdness);

                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.COAST_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[0], weirdness, 0.0F, peakBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.COAST_CONTINENTALNESS, this.NEAR_INLAND_CONTINENTALNESS), this.EROSIONS[1], weirdness, 0.0F, middleBadlandsOrSlopeBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[1], weirdness, 0.0F, peakBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.COAST_CONTINENTALNESS, this.NEAR_INLAND_CONTINENTALNESS), Climate.Parameter.span(this.EROSIONS[2], this.EROSIONS[3]), weirdness, 0.0F, middleBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[2], weirdness, 0.0F, plateauBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, this.MID_INLAND_CONTINENTALNESS, this.EROSIONS[3], weirdness, 0.0F, middleBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, this.FAR_INLAND_CONTINENTALNESS, this.EROSIONS[3], weirdness, 0.0F, plateauBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.COAST_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[4], weirdness, 0.0F, middleBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.COAST_CONTINENTALNESS, this.NEAR_INLAND_CONTINENTALNESS), this.EROSIONS[5], weirdness, 0.0F, shatteredBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[5], weirdness, 0.0F, extremeHillsBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.COAST_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[6], weirdness, 0.0F, middleBiome);
            }
        }
    }

    /**
     * Add the high slice biomes into the overworld
     *
     * @param mapper {@link Pair The biome mapper}
     * @param weirdness {@link Climate.Parameter The region weirdness}
     */
    private void addHighSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter weirdness) {
        for (int i = 0; i < this.TEMPERATURES.length; ++i) {

            Climate.Parameter temperature = this.TEMPERATURES[i];

            for (int j = 0; j < this.HUMIDITIES.length; ++j) {

                Climate.Parameter humidity = this.HUMIDITIES[j];

                ResourceKey<Biome> middleBiome = this.pickMiddleBiome(i, j, weirdness);
                ResourceKey<Biome> middleBiomeMW = this.pickMiddleBiomeMW(i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiome = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, weirdness);
                ResourceKey<Biome> plateauBiome = this.pickPlateauBiomeMW(i, j, weirdness);
                ResourceKey<Biome> extremeHillsBiome = this.pickExtremeHillsBiome(i, j, weirdness);
                ResourceKey<Biome> shatteredBiome = this.pickShatteredBiome(i, j, weirdness, middleBiome);
                ResourceKey<Biome> slopeBiome = this.pickPlateauBiomeMW(i, j, weirdness);
                ResourceKey<Biome> peakBiome = this.pickPeakBiomeMW(i, j, weirdness);

                this.addSurfaceBiome(mapper, temperature, humidity, this.COAST_CONTINENTALNESS, Climate.Parameter.span(this.EROSIONS[0], this.EROSIONS[1]), weirdness, 0.0F, middleBiomeMW);
                this.addSurfaceBiome(mapper, temperature, humidity, this.NEAR_INLAND_CONTINENTALNESS, this.EROSIONS[0], weirdness, 0.0F, slopeBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[0], weirdness, 0.0F, peakBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, this.NEAR_INLAND_CONTINENTALNESS, this.EROSIONS[1], weirdness, 0.0F, middleBadlandsOrSlopeBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[1], weirdness, 0.0F, slopeBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.COAST_CONTINENTALNESS, this.NEAR_INLAND_CONTINENTALNESS), Climate.Parameter.span(this.EROSIONS[2], this.EROSIONS[3]), weirdness, 0.0F, middleBiomeMW);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[2], weirdness, 0.0F, plateauBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, this.MID_INLAND_CONTINENTALNESS, this.EROSIONS[3], weirdness, 0.0F, middleBiomeMW);
                this.addSurfaceBiome(mapper, temperature, humidity, this.FAR_INLAND_CONTINENTALNESS, this.EROSIONS[3], weirdness, 0.0F, plateauBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.COAST_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[4], weirdness, 0.0F, middleBiomeMW);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.COAST_CONTINENTALNESS, this.NEAR_INLAND_CONTINENTALNESS), this.EROSIONS[5], weirdness, 0.0F, shatteredBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[5], weirdness, 0.0F, extremeHillsBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.COAST_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[6], weirdness, 0.0F, middleBiomeMW);
            }
        }
    }

    /**
     * Add the mid slice biomes into the overworld
     *
     * @param mapper {@link Pair The biome mapper}
     * @param weirdness {@link Climate.Parameter The region weirdness}
     */
    private void addMidSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter weirdness) {
        this.addSurfaceBiome(mapper, this.FULL_RANGE, this.FULL_RANGE, this.COAST_CONTINENTALNESS, Climate.Parameter.span(this.EROSIONS[0], this.EROSIONS[2]), weirdness, 0.0F, Biomes.STONY_SHORE);

        for (int i = 0; i < this.TEMPERATURES.length; ++i) {

            Climate.Parameter temperature = this.TEMPERATURES[i];

            for (int j = 0; j < this.HUMIDITIES.length; ++j) {

                Climate.Parameter humidity = this.HUMIDITIES[j];

                ResourceKey<Biome> middleBiome = this.pickMiddleBiome(i, j, weirdness);
                ResourceKey<Biome> middleBiomeMW = this.pickMiddleBiomeMW(i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiome = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, weirdness);
                ResourceKey<Biome> extremeHillsBiome = this.pickExtremeHillsBiome(i, j, weirdness);
                ResourceKey<Biome> plateauBiome = this.pickPlateauBiomeMW(i, j, weirdness);
                ResourceKey<Biome> beachBiome = this.pickBeachBiome(i, j);
                ResourceKey<Biome> shatteredBiome = this.pickShatteredBiome(i, j, weirdness, middleBiome);
                ResourceKey<Biome> shatteredCoastBiome = this.pickShatteredCoastBiome(i, j, weirdness);
                ResourceKey<Biome> slopeBiome = this.pickPlateauBiomeMW(i, j, weirdness);
                ResourceKey<Biome> swampBiome = this.pickSwampBiome(i, j, weirdness);

                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.NEAR_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[0], weirdness, 0.0F, slopeBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.NEAR_INLAND_CONTINENTALNESS, this.MID_INLAND_CONTINENTALNESS), this.EROSIONS[1], weirdness, 0.0F, middleBadlandsOrSlopeBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, this.FAR_INLAND_CONTINENTALNESS, this.EROSIONS[1], weirdness, 0.0F, i == 0 ? slopeBiome : plateauBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, this.NEAR_INLAND_CONTINENTALNESS, this.EROSIONS[2], weirdness, 0.0F, middleBiomeMW);
                this.addSurfaceBiome(mapper, temperature, humidity, this.MID_INLAND_CONTINENTALNESS, this.EROSIONS[2], weirdness, 0.0F, middleBiomeMW);
                this.addSurfaceBiome(mapper, temperature, humidity, this.FAR_INLAND_CONTINENTALNESS, this.EROSIONS[2], weirdness, 0.0F, plateauBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.COAST_CONTINENTALNESS, this.NEAR_INLAND_CONTINENTALNESS), this.EROSIONS[3], weirdness, 0.0F, middleBiomeMW);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[3], weirdness, 0.0F, middleBiomeMW);

                if (weirdness.max() < 0L) {
                    this.addSurfaceBiome(mapper, temperature, humidity, this.COAST_CONTINENTALNESS, this.EROSIONS[4], weirdness, 0.0F, beachBiome);
                }

                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(weirdness.max() < 0L ? this.NEAR_INLAND_CONTINENTALNESS : this.COAST_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[4], weirdness, 0.0F, middleBiomeMW);
                this.addSurfaceBiome(mapper, temperature, humidity, this.COAST_CONTINENTALNESS, this.EROSIONS[5], weirdness, 0.0F, shatteredCoastBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, this.NEAR_INLAND_CONTINENTALNESS, this.EROSIONS[5], weirdness, 0.0F, shatteredBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[5], weirdness, 0.0F, extremeHillsBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, this.COAST_CONTINENTALNESS, this.EROSIONS[6], weirdness, 0.0F, weirdness.max() < 0L ? beachBiome : middleBiomeMW);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.NEAR_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[6], weirdness, 0.0F, i == 0 ? middleBiomeMW : swampBiome);
            }
        }
    }

    /**
     * Add the low slice biomes into the overworld
     *
     * @param mapper {@link Pair The biome mapper}
     * @param weirdness {@link Climate.Parameter The region weirdness}
     */
    private void addLowSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter weirdness) {
        this.addSurfaceBiome(mapper, this.FULL_RANGE, this.FULL_RANGE, this.COAST_CONTINENTALNESS, Climate.Parameter.span(this.EROSIONS[0], this.EROSIONS[2]), weirdness, 0.0F, Biomes.STONY_SHORE);

        for (int i = 0; i < this.TEMPERATURES.length; ++i) {

            Climate.Parameter temperature = this.TEMPERATURES[i];

            for (int j = 0; j < this.HUMIDITIES.length; ++j) {

                Climate.Parameter humidity = this.HUMIDITIES[j];

                ResourceKey<Biome> middleBiome = this.pickMiddleBiome(i, j, weirdness);
                ResourceKey<Biome> middleBiomeMW = this.pickMiddleBiomeMW(i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiome = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, weirdness);
                ResourceKey<Biome> beachBiome = this.pickBeachBiome(i, j);
                ResourceKey<Biome> shatteredBiome = this.pickShatteredBiome(i, j, weirdness, middleBiome);
                ResourceKey<Biome> shatteredCoastBiome = this.pickShatteredCoastBiome(i, j, weirdness);
                ResourceKey<Biome> swampBiome = this.pickSwampBiome(i, j, weirdness);

                this.addSurfaceBiome(mapper, temperature, humidity, this.NEAR_INLAND_CONTINENTALNESS, Climate.Parameter.span(this.EROSIONS[0], this.EROSIONS[1]), weirdness, 0.0F, middleBiomeMW);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), Climate.Parameter.span(this.EROSIONS[0], this.EROSIONS[1]), weirdness, 0.0F, middleBadlandsOrSlopeBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, this.NEAR_INLAND_CONTINENTALNESS, Climate.Parameter.span(this.EROSIONS[2], this.EROSIONS[3]), weirdness, 0.0F, middleBiomeMW);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), Climate.Parameter.span(this.EROSIONS[2], this.EROSIONS[3]), weirdness, 0.0F, middleBiomeMW);
                this.addSurfaceBiome(mapper, temperature, humidity, this.COAST_CONTINENTALNESS, Climate.Parameter.span(this.EROSIONS[3], this.EROSIONS[4]), weirdness, 0.0F, beachBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.NEAR_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[4], weirdness, 0.0F, middleBiomeMW);
                this.addSurfaceBiome(mapper, temperature, humidity, this.COAST_CONTINENTALNESS, this.EROSIONS[5], weirdness, 0.0F, shatteredCoastBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, this.NEAR_INLAND_CONTINENTALNESS, this.EROSIONS[5], weirdness, 0.0F, shatteredBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[5], weirdness, 0.0F, middleBiomeMW);
                this.addSurfaceBiome(mapper, temperature, humidity, this.COAST_CONTINENTALNESS, this.EROSIONS[6], weirdness, 0.0F, beachBiome);

                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.NEAR_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[6], weirdness, 0.0F, i == 0 ? middleBiomeMW : swampBiome);
            }
        }
    }

    /**
     * Add the valley biomes into the overworld
     *
     * @param mapper {@link Pair The biome mapper}
     * @param weirdness {@link Climate.Parameter The region weirdness}
     */
    private void addValleys(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter weirdness) {
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, this.COAST_CONTINENTALNESS, Climate.Parameter.span(this.EROSIONS[0], this.EROSIONS[1]), weirdness, 0.0F, weirdness.max() < 0L ? Biomes.STONY_SHORE : Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(mapper, this.UNFROZEN_RANGE, this.FULL_RANGE, this.COAST_CONTINENTALNESS, Climate.Parameter.span(this.EROSIONS[0], this.EROSIONS[1]), weirdness, 0.0F, weirdness.max() < 0L ? Biomes.STONY_SHORE : Biomes.RIVER);
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, this.NEAR_INLAND_CONTINENTALNESS, Climate.Parameter.span(this.EROSIONS[0], this.EROSIONS[1]), weirdness, 0.0F, Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(mapper, this.UNFROZEN_RANGE, this.FULL_RANGE, this.NEAR_INLAND_CONTINENTALNESS, Climate.Parameter.span(this.EROSIONS[0], this.EROSIONS[1]), weirdness, 0.0F, Biomes.RIVER);
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.COAST_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), Climate.Parameter.span(this.EROSIONS[2], this.EROSIONS[5]), weirdness, 0.0F, Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(mapper, this.UNFROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.COAST_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), Climate.Parameter.span(this.EROSIONS[2], this.EROSIONS[5]), weirdness, 0.0F, Biomes.RIVER);
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, this.COAST_CONTINENTALNESS, this.EROSIONS[6], weirdness, 0.0F, Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[6], weirdness, 0.0F, Biomes.FROZEN_RIVER);

        for (int i = 0; i < this.TEMPERATURES.length; ++i) {

            Climate.Parameter temperature = this.TEMPERATURES[i];

            for (int j = 0; j < this.HUMIDITIES.length; ++j) {

                Climate.Parameter humidity = this.HUMIDITIES[j];
                ResourceKey<Biome> middleBiome = this.pickMiddleBiomeMW(i, j, weirdness);
                ResourceKey<Biome> swampBiome = this.pickPlateauBiomeMW(i, j, weirdness);

                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.MID_INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), Climate.Parameter.span(this.EROSIONS[0], this.EROSIONS[1]), weirdness, 0.0F, middleBiome);

                if (i != 0) {
                    this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[6], weirdness, 0.0F, swampBiome);
                }
            }
        }
    }

    /**
     * Add the underground biomes into the overworld
     *
     * @param mapper {@link Pair The biome mapper}
     */
    private void addUndergroundBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addUndergroundBiome(mapper, this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(0.8F, 1.0F), this.FULL_RANGE, this.FULL_RANGE, 0.0F, Biomes.DRIPSTONE_CAVES);
        this.addUndergroundBiome(mapper, this.FULL_RANGE, Climate.Parameter.span(0.7F, 1.0F), this.FULL_RANGE, this.FULL_RANGE, this.FULL_RANGE, 0.0F, Biomes.LUSH_CAVES);
        this.addBottomBiome(mapper, this.FULL_RANGE, this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.EROSIONS[0], this.EROSIONS[1]), this.FULL_RANGE, 0.0F, Biomes.DEEP_DARK);
    }

    /**
     * Get a {@link ResourceKey<Biome> middle biome}
     *
     * @param temperatureIndex {@link Integer The temperature index}
     * @param humidityIndex {@link Integer The humidity index}
     * @param weirdness {@link Climate.Parameter The biome weirdness}
     * @return {@link ResourceKey<Biome> The middle biome}
     */
    private ResourceKey<Biome> pickMiddleBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
        if (weirdness.max() < 0L) {
            return this.MIDDLE_BIOMES[temperatureIndex][humidityIndex];
        }
        ResourceKey<Biome> variantBiome = this.MIDDLE_BIOMES_VARIANT[temperatureIndex][humidityIndex];
        return variantBiome == null ? this.MIDDLE_BIOMES[temperatureIndex][humidityIndex] : variantBiome;
    }

    /**
     * Get a {@link ResourceKey<Biome> MineWorld middle biome}
     *
     * @param temperatureIndex {@link Integer The temperature index}
     * @param humidityIndex {@link Integer The humidity index}
     * @param weirdness {@link Climate.Parameter The biome weirdness}
     * @return {@link ResourceKey<Biome> The MineWorld middle biome}
     */
    private ResourceKey<Biome> pickMiddleBiomeMW(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
        ResourceKey<Biome> biome = weirdness.max() < 0 ? this.MIDDLE_BIOMES[temperatureIndex][humidityIndex] : this.MW_MIDDLE_BIOMES_VARIANT[temperatureIndex][humidityIndex];
        return biome == null ? this.MIDDLE_BIOMES[temperatureIndex][humidityIndex] : biome;
    }

    /**
     * Get a {@link ResourceKey<Biome> middle biome} or a balands biome if the region temperature is hot
     *
     * @param temperatureIndex {@link Integer The temperature index}
     * @param humidityIndex {@link Integer The humidity index}
     * @param weirdness {@link Climate.Parameter The biome weirdness}
     * @return {@link ResourceKey<Biome> The biome}
     */
    private ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
        return temperatureIndex == 0 ? this.pickPlateauBiomeMW(temperatureIndex, humidityIndex, weirdness) : this.pickMiddleBiomeMW(temperatureIndex, humidityIndex, weirdness);
    }

    /**
     * Get a {@link ResourceKey<Biome> swamp biome}
     *
     * @param temperatureIndex {@link Integer The temperature index}
     * @param humidityIndex {@link Integer The humidity index}
     * @param weirdness {@link Climate.Parameter The biome weirdness}
     * @return {@link ResourceKey<Biome> The swamp biome}
     */
    private ResourceKey<Biome> pickSwampBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
        ResourceKey<Biome> middleBiome = this.pickMiddleBiomeMW(temperatureIndex, humidityIndex, weirdness);
        return middleBiome == null ? Biomes.SWAMP : middleBiome;
    }

    /**
     * Get a {@link ResourceKey<Biome> shattered biome}
     *
     * @param temperatureIndex {@link Integer The temperature index}
     * @param humidityIndex {@link Integer The humidity index}
     * @param weirdness {@link Climate.Parameter The biome weirdness}
     * @param fallbackBiome {@link ResourceKey<Biome> The fallback biome}
     * @return {@link ResourceKey<Biome> The shattered biome}
     */
    private ResourceKey<Biome> pickShatteredBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness, ResourceKey<Biome> fallbackBiome) {
        return temperatureIndex > 1 && humidityIndex < 4 && weirdness.max() >= 0L ? Biomes.WINDSWEPT_SAVANNA : fallbackBiome;
    }

    /**
     * Get a {@link ResourceKey<Biome> shattered coast biome}
     *
     * @param temperatureIndex {@link Integer The temperature index}
     * @param humidityIndex {@link Integer The humidity index}
     * @param weirdness {@link Climate.Parameter The biome weirdness}
     * @return {@link ResourceKey<Biome> The shattered coast biome}
     */
    private ResourceKey<Biome> pickShatteredCoastBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
        ResourceKey<Biome> biome = weirdness.max() >= 0L ? this.pickMiddleBiome(temperatureIndex, humidityIndex, weirdness) : this.pickBeachBiome(temperatureIndex, humidityIndex);
        return this.pickShatteredBiome(temperatureIndex, humidityIndex, weirdness, biome);
    }

    /**
     * Get a {@link ResourceKey<Biome> beach biome}
     *
     * @param temperatureIndex {@link Integer The temperature index}
     * @param humidityIndex {@link Integer The humidity index}
     * @return {@link ResourceKey<Biome> The beach biome}
     */
    private ResourceKey<Biome> pickBeachBiome(int temperatureIndex, int humidityIndex) {
        return temperatureIndex == 0 ? Biomes.SNOWY_BEACH : temperatureIndex == 4 ? Biomes.DESERT : Biomes.BEACH;
    }

    /**
     * Get a {@link ResourceKey<Biome> badlands biome}
     *
     * @param humidityIndex {@link Integer The humidity index}
     * @param weirdness {@link Climate.Parameter The biome weirdness}
     * @return {@link ResourceKey<Biome> The badlands biome}
     */
    private ResourceKey<Biome> pickBadlandsBiome(int humidityIndex, Climate.Parameter weirdness) {
        return humidityIndex < 2 ? weirdness.max() < 0L ? Biomes.ERODED_BADLANDS : Biomes.BADLANDS
                : humidityIndex < 3 ? Biomes.BADLANDS : Biomes.WOODED_BADLANDS;
    }

    /**
     * Get a {@link ResourceKey<Biome> MineWorld plateau biome}
     *
     * @param temperatureIndex {@link Integer The temperature index}
     * @param humidityIndex {@link Integer The humidity index}
     * @param weirdness {@link Climate.Parameter The biome weirdness}
     * @return {@link ResourceKey<Biome> The MineWorld plateau biome}
     */
    private ResourceKey<Biome> pickPlateauBiomeMW(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
        ResourceKey<Biome> biome = weirdness.max() < 0L ? this.PLATEAU_BIOMES[temperatureIndex][humidityIndex] : this.MW_PLATEAU_BIOMES_VARIANT[temperatureIndex][humidityIndex];
        return biome == null ? this.PLATEAU_BIOMES[temperatureIndex][humidityIndex] : biome;
    }

    /**
     * Get a {@link ResourceKey<Biome> peak biome}
     *
     * @param temperatureIndex {@link Integer The temperature index}
     * @param humidityIndex {@link Integer The humidity index}
     * @param weirdness {@link Climate.Parameter The biome weirdness}
     * @return {@link ResourceKey<Biome> The peak biome}
     */
    private ResourceKey<Biome> pickPeakBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
        return temperatureIndex <= 2 ? weirdness.max() < 0L ? Biomes.JAGGED_PEAKS : Biomes.FROZEN_PEAKS
            : temperatureIndex == 3 ? Biomes.STONY_PEAKS : this.pickBadlandsBiome(humidityIndex, weirdness);
    }

    /**
     * Get a {@link ResourceKey<Biome> MineWorld peak biome}
     *
     * @param temperatureIndex {@link Integer The temperature index}
     * @param humidityIndex {@link Integer The humidity index}
     * @param weirdness {@link Climate.Parameter The biome weirdness}
     * @return {@link ResourceKey<Biome> The MineWorld peak biome}
     */
    private ResourceKey<Biome> pickPeakBiomeMW(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
        return temperatureIndex == 3 && humidityIndex >= 3 ? MWBiomes.VOLCANIC_PEAK : this.pickPeakBiome(temperatureIndex, humidityIndex, weirdness);
    }

    /**
     * Get an {@link ResourceKey<Biome> extreme hills biome}
     *
     * @param temperatureIndex {@link Integer The temperature index}
     * @param humidityIndex {@link Integer The humidity index}
     * @param weirdness {@link Climate.Parameter The biome weirdness}
     * @return {@link ResourceKey<Biome> The extreme hills biome}
     */
    private ResourceKey<Biome> pickExtremeHillsBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
        ResourceKey<Biome> biome = this.EXTREME_HILLS[temperatureIndex][humidityIndex];
        return biome == null ? this.pickMiddleBiome(temperatureIndex, humidityIndex, weirdness) : biome;
    }

    /**
     * Add a surface biome to the overworld
     *
     * @param mapper {@link Pair The biome mapper}
     * @param temperature {@link Climate.Parameter The region temperature}
     * @param humidity {@link Climate.Parameter The region humidity}
     * @param continentalness {@link Climate.Parameter The region continentalness}
     * @param erosion {@link Climate.Parameter The region erosion}
     * @param weirdness {@link Climate.Parameter The biome weirdness}
     * @param offset {@link Float The biome offset}
     * @param biome {@link ResourceKey<Biome> The biome to add}
     */
    private void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome) {
        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(0.0F), weirdness, offset), biome));
        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(1.0F), weirdness, offset), biome));
    }

    /**
     * Add a surface biome to the overworld
     *
     * @param mapper {@link Pair The biome mapper}
     * @param temperature {@link Climate.Parameter The region temperature}
     * @param humidity {@link Climate.Parameter The region humidity}
     * @param continentalness {@link Climate.Parameter The region continentalness}
     * @param erosion {@link Climate.Parameter The region erosion}
     * @param weirdness {@link Climate.Parameter The biome weirdness}
     * @param offset {@link Float The biome offset}
     * @param biome {@link ResourceKey<Biome> The biome to add}
     */
    private void addUndergroundBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome) {
        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.span(0.2F, 0.9F), weirdness, offset), biome));
    }

    /**
     * Add a bottom biome to the overworld
     *
     * @param mapper {@link Pair The biome mapper}
     * @param temperature {@link Climate.Parameter The region temperature}
     * @param humidity {@link Climate.Parameter The region humidity}
     * @param continentalness {@link Climate.Parameter The region continentalness}
     * @param erosion {@link Climate.Parameter The region erosion}
     * @param weirdness {@link Climate.Parameter The biome weirdness}
     * @param offset {@link Float The biome offset}
     * @param biome {@link ResourceKey<Biome> The biome to add}
     */
    private void addBottomBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome) {
        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(1.1F), weirdness, offset), biome));
    }

}