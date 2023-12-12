package org.mineworld.world.overworld;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBiomes;
import org.mineworld.helper.RandomHelper;
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
    private final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);

    /**
     * {@link Climate.Parameter Biome temepratures}
     */
    private final Climate.Parameter[] TEMPERATURES = new Climate.Parameter[]{
            ParameterUtils.Temperature.ICY.parameter(),
            ParameterUtils.Temperature.COOL.parameter(),
            ParameterUtils.Temperature.NEUTRAL.parameter(),
            ParameterUtils.Temperature.WARM.parameter(),
            ParameterUtils.Temperature.HOT.parameter()
    };

    /**
     * {@link Climate.Parameter Biome humidities}
     */
    private final Climate.Parameter[] HUMIDITIES = new Climate.Parameter[]{
            ParameterUtils.Humidity.ARID.parameter(),
            ParameterUtils.Humidity.DRY.parameter(),
            ParameterUtils.Humidity.NEUTRAL.parameter(),
            ParameterUtils.Humidity.WET.parameter(),
            ParameterUtils.Humidity.HUMID.parameter()
    };

    /**
     * {@link Climate.Parameter Biome erosions}
     */
    private final Climate.Parameter[] EROSIONS = new Climate.Parameter[]{
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
    private final Climate.Parameter FROZEN_RANGE = this.TEMPERATURES[0];
    /**
     * {@link Climate.Parameter The range of temperatrues for non frozen biomes}
     */
    private final Climate.Parameter UNFROZEN_RANGE = Climate.Parameter.span(this.TEMPERATURES[1], this.TEMPERATURES[4]);
    /**
     * {@link ParameterUtils.Continentalness The continentalness values for mushroom fields}
     */
    private final Climate.Parameter MUSHROOM_FIELDS_CONTINENTALNESS = Climate.Parameter.span(-1.2F, -1.05F);
    /**
     * {@link ParameterUtils.Continentalness The continentalness values for deep ocean biomes}
     */
    private final Climate.Parameter DEEP_OCEAN_CONTINENTALNESS = Climate.Parameter.span(-1.05F, -0.455F);
    /**
     * {@link ParameterUtils.Continentalness The continentalness values for ocean biomes}
     */
    private final Climate.Parameter OCEAN_CONTINENTALNESS = Climate.Parameter.span(-0.455F, -0.19F);
    /**
     * {@link ParameterUtils.Continentalness The continentalness values for coast biomes}
     */
    private final Climate.Parameter COAST_CONTINENTALNESS = Climate.Parameter.span(-0.19F, -0.11F);
    /**
     * {@link ParameterUtils.Continentalness The continentalness values for inland biomes}
     */
    private final Climate.Parameter INLAND_CONTINENTALNESS = Climate.Parameter.span(-0.11F, 0.55F);
    /**
     * {@link ParameterUtils.Continentalness The continentalness values for near inland biomes}
     */
    private final Climate.Parameter NEAR_INLAND_CONTINENTALNESS = Climate.Parameter.span(-0.11F, 0.03F);
    /**
     * {@link ParameterUtils.Continentalness The continentalness values for mid inland biomes}
     */
    private final Climate.Parameter MID_INLAND_CONTINENTALNESS = Climate.Parameter.span(0.03F, 0.3F);
    /**
     * {@link ParameterUtils.Continentalness The continentalness values for far inland biomes}
     */
    private final Climate.Parameter FAR_INLAND_CONTINENTALNESS = Climate.Parameter.span(0.3F, 1.0F);

    /**
     * {@link ResourceKey<Biome> Ocean biomes}
     */
    private final ResourceKey<Biome>[][] OCEANS = new ResourceKey[][]{
            {Biomes.DEEP_FROZEN_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN},
            {Biomes.FROZEN_OCEAN,      Biomes.COLD_OCEAN,      Biomes.OCEAN,      Biomes.LUKEWARM_OCEAN,      Biomes.WARM_OCEAN}
    };

    /**
     * {@link ResourceKey<Biome> Middle biomes}
     */
    private final ResourceKey<Biome>[][] MIDDLE_BIOMES = new ResourceKey[][]{
            {Biomes.SNOWY_PLAINS,  Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA,  Biomes.TAIGA},
            {Biomes.PLAINS,        Biomes.PLAINS,       Biomes.FOREST,       Biomes.TAIGA,        Biomes.OLD_GROWTH_SPRUCE_TAIGA},
            {Biomes.FLOWER_FOREST, Biomes.PLAINS,       Biomes.FOREST,       Biomes.BIRCH_FOREST, Biomes.DARK_FOREST},
            {Biomes.SAVANNA,       Biomes.SAVANNA,      Biomes.FOREST,       Biomes.JUNGLE,       Biomes.JUNGLE},
            {Biomes.DESERT,        Biomes.DESERT,       Biomes.DESERT,       Biomes.DESERT,       Biomes.DESERT}
    };

    /**
     * {@link ResourceKey<Biome> Middle biome variants}
     */
    private final ResourceKey<Biome>[][] MIDDLE_BIOMES_VARIANT = new ResourceKey[][]{
            {Biomes.ICE_SPIKES,       null, Biomes.SNOWY_TAIGA, null,                           null},
            {null,                    null, null,               null,                           Biomes.OLD_GROWTH_PINE_TAIGA},
            {Biomes.SUNFLOWER_PLAINS, null, null,               Biomes.OLD_GROWTH_BIRCH_FOREST, null},
            {null,                    null, Biomes.PLAINS,      Biomes.SPARSE_JUNGLE,           Biomes.BAMBOO_JUNGLE},
            {null,                    null, null,               null,                           null}
    };

    /**
     * {@link ResourceKey<Biome> MineWorld middle biome variants}
     */
    private final ResourceKey<Biome>[][] MW_MIDDLE_BIOMES_VARIANT = new ResourceKey[][]{
            {MWBiomes.FROZEN_PLAINS,    null,               null, null, null},
            {null,                      null,               null, null, null},
            {null,                      null,               null, null, null},
            {MWBiomes.WASTELAND,        MWBiomes.WASTELAND, null, null, null},
            {null,                      null,               null, null, null}
    };

    /**
     * {@link ResourceKey<Biome> Plateau biomes}
     */
    private final ResourceKey<Biome>[][] PLATEAU_BIOMES = new ResourceKey[][]{
            {Biomes.SNOWY_PLAINS,    Biomes.SNOWY_PLAINS,    Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA,     Biomes.SNOWY_TAIGA},
            {Biomes.MEADOW,          Biomes.MEADOW,          Biomes.FOREST,       Biomes.TAIGA,           Biomes.OLD_GROWTH_SPRUCE_TAIGA},
            {Biomes.MEADOW,          Biomes.MEADOW,          Biomes.MEADOW,       Biomes.MEADOW,          Biomes.DARK_FOREST},
            {Biomes.SAVANNA_PLATEAU, Biomes.SAVANNA_PLATEAU, Biomes.FOREST,       Biomes.FOREST,          Biomes.JUNGLE},
            {Biomes.BADLANDS,        Biomes.BADLANDS,        Biomes.BADLANDS,     Biomes.WOODED_BADLANDS, Biomes.WOODED_BADLANDS}
    };

    /**
     * {@link ResourceKey<Biome> MineWorld Plateau biome variants}
     */
    private final ResourceKey<Biome>[][] MW_PLATEAU_BIOMES_VARIANT = new ResourceKey[][]{
            {MWBiomes.FROZEN_PLAINS,    null, null, null, null},
            {null,                      null, null, null, null},
            {null,                      null, null, null, null},
            {null,                      null, null, null, null},
            {null,                      null, null, null, null}
    };

    /**
     * {@link ResourceKey<Biome> Extreme hills biomes}
     */
    private final ResourceKey<Biome>[][] EXTREME_HILLS = new ResourceKey[][]{
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
    public void addBiomes(final Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addOffCoastBiomes(mapper);
        this.addInlandBiomes(mapper);
        this.addUndergroundBiomes(mapper);
    }

    /**
     * Add the off coast biomes into the overworld
     *
     * @param mapper {@link Pair The biome mapper}
     */
    private void addOffCoastBiomes(final Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addSurfaceBiome(mapper, this.FULL_RANGE, this.FULL_RANGE, this.MUSHROOM_FIELDS_CONTINENTALNESS, this.FULL_RANGE, this.FULL_RANGE, 0.0F, Biomes.MUSHROOM_FIELDS);

        for (int i = 0; i < this.TEMPERATURES.length; ++i) {
            final Climate.Parameter temperature = this.TEMPERATURES[i];
            this.addSurfaceBiome(mapper, temperature, this.FULL_RANGE, this.DEEP_OCEAN_CONTINENTALNESS, this.FULL_RANGE, this.FULL_RANGE, 0.0F, this.OCEANS[0][i]);
            this.addSurfaceBiome(mapper, temperature, this.FULL_RANGE, this.OCEAN_CONTINENTALNESS, this.FULL_RANGE, this.FULL_RANGE, 0.0F, this.OCEANS[1][i]);
        }
    }

    /**
     * Add the inland biomes into the overworld
     *
     * @param mapper {@link Pair The biome mapper}
     */
    private void addInlandBiomes(final Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
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
    private void addPeaks(final Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, final Climate.Parameter weirdness) {
        for (int i = 0; i < this.TEMPERATURES.length; ++i) {
            final Climate.Parameter temperature = this.TEMPERATURES[i];

            for (int j = 0; j < this.HUMIDITIES.length; ++j) {

                final Climate.Parameter humidity = this.HUMIDITIES[j];
                final ResourceKey<Biome> middleBiome = this.pickMiddleBiomeMW(i, j, weirdness);
                final ResourceKey<Biome> middleBadlandsOrSlopeBiome = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, weirdness);
                final ResourceKey<Biome> plateauBiome = this.pickPlateauBiomeMW(i, j, weirdness);
                final ResourceKey<Biome> extremeHillsBiome = this.pickExtremeHillsBiome(i, j, weirdness);
                final ResourceKey<Biome> shatteredBiome = this.pickShatteredBiome(i, j, weirdness, extremeHillsBiome);
                final ResourceKey<Biome> peakBiome = this.pickPeakBiomeMW(i, j, weirdness);

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
    private void addHighSlice(final Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, final Climate.Parameter weirdness) {
        for (int i = 0; i < this.TEMPERATURES.length; ++i) {

            final Climate.Parameter temperature = this.TEMPERATURES[i];

            for (int j = 0; j < this.HUMIDITIES.length; ++j) {

                final Climate.Parameter humidity = this.HUMIDITIES[j];

                final ResourceKey<Biome> middleBiome = this.pickMiddleBiome(i, j, weirdness);
                final ResourceKey<Biome> middleBiomeMW = this.pickMiddleBiomeMW(i, j, weirdness);
                final ResourceKey<Biome> middleBadlandsOrSlopeBiome = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, weirdness);
                final ResourceKey<Biome> plateauBiome = this.pickPlateauBiomeMW(i, j, weirdness);
                final ResourceKey<Biome> extremeHillsBiome = this.pickExtremeHillsBiome(i, j, weirdness);
                final ResourceKey<Biome> shatteredBiome = this.pickShatteredBiome(i, j, weirdness, middleBiome);
                final ResourceKey<Biome> slopeBiome = this.pickPlateauBiomeMW(i, j, weirdness);
                final ResourceKey<Biome> peakBiome = this.pickPeakBiomeMW(i, j, weirdness);

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
     * Add the mid-slice biomes into the overworld
     *
     * @param mapper {@link Pair The biome mapper}
     * @param weirdness {@link Climate.Parameter The region weirdness}
     */
    private void addMidSlice(final Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, final Climate.Parameter weirdness) {
        this.addSurfaceBiome(mapper, this.FULL_RANGE, this.FULL_RANGE, this.COAST_CONTINENTALNESS, Climate.Parameter.span(this.EROSIONS[0], this.EROSIONS[2]), weirdness, 0.0F, Biomes.STONY_SHORE);

        for (int i = 0; i < this.TEMPERATURES.length; ++i) {

            final Climate.Parameter temperature = this.TEMPERATURES[i];

            for (int j = 0; j < this.HUMIDITIES.length; ++j) {

                final Climate.Parameter humidity = this.HUMIDITIES[j];

                final ResourceKey<Biome> middleBiome = this.pickMiddleBiome(i, j, weirdness);
                final ResourceKey<Biome> middleBiomeMW = this.pickMiddleBiomeMW(i, j, weirdness);
                final ResourceKey<Biome> middleBadlandsOrSlopeBiome = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, weirdness);
                final ResourceKey<Biome> extremeHillsBiome = this.pickExtremeHillsBiome(i, j, weirdness);
                final ResourceKey<Biome> plateauBiome = this.pickPlateauBiomeMW(i, j, weirdness);
                final ResourceKey<Biome> beachBiome = this.pickBeachBiome(i, j);
                final ResourceKey<Biome> shatteredBiome = this.pickShatteredBiome(i, j, weirdness, middleBiome);
                final ResourceKey<Biome> shatteredCoastBiome = this.pickShatteredCoastBiome(i, j, weirdness);
                final ResourceKey<Biome> slopeBiome = this.pickPlateauBiomeMW(i, j, weirdness);
                final ResourceKey<Biome> swampBiome = this.pickSwampBiome(i, j, weirdness);

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
    private void addLowSlice(final Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, final Climate.Parameter weirdness) {
        this.addSurfaceBiome(mapper, this.FULL_RANGE, this.FULL_RANGE, this.COAST_CONTINENTALNESS, Climate.Parameter.span(this.EROSIONS[0], this.EROSIONS[2]), weirdness, 0.0F, Biomes.STONY_SHORE);

        for (int i = 0; i < this.TEMPERATURES.length; ++i) {

            final Climate.Parameter temperature = this.TEMPERATURES[i];

            for (int j = 0; j < this.HUMIDITIES.length; ++j) {

                final Climate.Parameter humidity = this.HUMIDITIES[j];

                final ResourceKey<Biome> middleBiome = this.pickMiddleBiome(i, j, weirdness);
                final ResourceKey<Biome> middleBiomeMW = this.pickMiddleBiomeMW(i, j, weirdness);
                final ResourceKey<Biome> middleBadlandsOrSlopeBiome = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, weirdness);
                final ResourceKey<Biome> beachBiome = this.pickBeachBiome(i, j);
                final ResourceKey<Biome> shatteredBiome = this.pickShatteredBiome(i, j, weirdness, middleBiome);
                final ResourceKey<Biome> shatteredCoastBiome = this.pickShatteredCoastBiome(i, j, weirdness);
                final ResourceKey<Biome> swampBiome = this.pickSwampBiome(i, j, weirdness);

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
    private void addValleys(final Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, final Climate.Parameter weirdness) {
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, this.COAST_CONTINENTALNESS, Climate.Parameter.span(this.EROSIONS[0], this.EROSIONS[1]), weirdness, 0.0F, weirdness.max() < 0L ? Biomes.STONY_SHORE : Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(mapper, this.UNFROZEN_RANGE, this.FULL_RANGE, this.COAST_CONTINENTALNESS, Climate.Parameter.span(this.EROSIONS[0], this.EROSIONS[1]), weirdness, 0.0F, weirdness.max() < 0L ? Biomes.STONY_SHORE : Biomes.RIVER);
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, this.NEAR_INLAND_CONTINENTALNESS, Climate.Parameter.span(this.EROSIONS[0], this.EROSIONS[1]), weirdness, 0.0F, Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(mapper, this.UNFROZEN_RANGE, this.FULL_RANGE, this.NEAR_INLAND_CONTINENTALNESS, Climate.Parameter.span(this.EROSIONS[0], this.EROSIONS[1]), weirdness, 0.0F, Biomes.RIVER);
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.COAST_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), Climate.Parameter.span(this.EROSIONS[2], this.EROSIONS[5]), weirdness, 0.0F, Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(mapper, this.UNFROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.COAST_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), Climate.Parameter.span(this.EROSIONS[2], this.EROSIONS[5]), weirdness, 0.0F, Biomes.RIVER);
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, this.COAST_CONTINENTALNESS, this.EROSIONS[6], weirdness, 0.0F, Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.INLAND_CONTINENTALNESS, this.FAR_INLAND_CONTINENTALNESS), this.EROSIONS[6], weirdness, 0.0F, Biomes.FROZEN_RIVER);

        for (int i = 0; i < this.TEMPERATURES.length; ++i) {

            final Climate.Parameter temperature = this.TEMPERATURES[i];

            for (int j = 0; j < this.HUMIDITIES.length; ++j) {

                final Climate.Parameter humidity = this.HUMIDITIES[j];
                final ResourceKey<Biome> middleBiome = this.pickMiddleBiomeMW(i, j, weirdness);
                final ResourceKey<Biome> swampBiome = this.pickPlateauBiomeMW(i, j, weirdness);

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
    private void addUndergroundBiomes(final Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
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
    private ResourceKey<Biome> pickMiddleBiome(final int temperatureIndex, final int humidityIndex, final Climate.Parameter weirdness) {
        if (weirdness.max() < 0L) {
            return this.MIDDLE_BIOMES[temperatureIndex][humidityIndex];
        }
        final ResourceKey<Biome> variantBiome = this.MIDDLE_BIOMES_VARIANT[temperatureIndex][humidityIndex];
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
    private ResourceKey<Biome> pickMiddleBiomeMW(final int temperatureIndex, final int humidityIndex, final Climate.Parameter weirdness) {
        ResourceKey<Biome> biome = weirdness.max() < 0 ? this.MIDDLE_BIOMES[temperatureIndex][humidityIndex] : this.MW_MIDDLE_BIOMES_VARIANT[temperatureIndex][humidityIndex];
        if(biome != null && biome.equals(MWBiomes.WASTELAND) && RandomHelper.choose()) {
            biome = MWBiomes.FOSSILS_WASTELAND;
        }
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
    private ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(final int temperatureIndex, final int humidityIndex, final Climate.Parameter weirdness) {
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
    private ResourceKey<Biome> pickSwampBiome(final int temperatureIndex, final int humidityIndex, final Climate.Parameter weirdness) {
        final ResourceKey<Biome> middleBiome = this.pickMiddleBiomeMW(temperatureIndex, humidityIndex, weirdness);
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
    private ResourceKey<Biome> pickShatteredBiome(final int temperatureIndex, final int humidityIndex, final Climate.Parameter weirdness, final ResourceKey<Biome> fallbackBiome) {
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
    private ResourceKey<Biome> pickShatteredCoastBiome(final int temperatureIndex, final int humidityIndex, final Climate.Parameter weirdness) {
        final ResourceKey<Biome> biome = weirdness.max() >= 0L ? this.pickMiddleBiome(temperatureIndex, humidityIndex, weirdness) : this.pickBeachBiome(temperatureIndex, humidityIndex);
        return this.pickShatteredBiome(temperatureIndex, humidityIndex, weirdness, biome);
    }

    /**
     * Get a {@link ResourceKey<Biome> beach biome}
     *
     * @param temperatureIndex {@link Integer The temperature index}
     * @param humidityIndex {@link Integer The humidity index}
     * @return {@link ResourceKey<Biome> The beach biome}
     */
    private ResourceKey<Biome> pickBeachBiome(final int temperatureIndex, final int humidityIndex) {
        return temperatureIndex == 0 ? Biomes.SNOWY_BEACH : temperatureIndex == 4 ? Biomes.DESERT : Biomes.BEACH;
    }

    /**
     * Get a {@link ResourceKey<Biome> badlands biome}
     *
     * @param humidityIndex {@link Integer The humidity index}
     * @param weirdness {@link Climate.Parameter The biome weirdness}
     * @return {@link ResourceKey<Biome> The badlands biome}
     */
    private ResourceKey<Biome> pickBadlandsBiome(final int humidityIndex, final Climate.Parameter weirdness) {
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
    private ResourceKey<Biome> pickPlateauBiomeMW(final int temperatureIndex, final int humidityIndex, final Climate.Parameter weirdness) {
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
    private ResourceKey<Biome> pickPeakBiome(final int temperatureIndex, final int humidityIndex, final Climate.Parameter weirdness) {
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
    private ResourceKey<Biome> pickPeakBiomeMW(final int temperatureIndex, final int humidityIndex, final Climate.Parameter weirdness) {
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
    private ResourceKey<Biome> pickExtremeHillsBiome(final int temperatureIndex, final int humidityIndex, final Climate.Parameter weirdness) {
        final ResourceKey<Biome> biome = this.EXTREME_HILLS[temperatureIndex][humidityIndex];
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
    private void addSurfaceBiome(final Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, final Climate.Parameter temperature, final Climate.Parameter humidity, final Climate.Parameter continentalness, final Climate.Parameter erosion, final Climate.Parameter weirdness, final float offset, final ResourceKey<Biome> biome) {
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
    private void addUndergroundBiome(final Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, final Climate.Parameter temperature, final Climate.Parameter humidity, final Climate.Parameter continentalness, final Climate.Parameter erosion, final Climate.Parameter weirdness, final float offset, final ResourceKey<Biome> biome) {
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
    private void addBottomBiome(final Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, final Climate.Parameter temperature, final Climate.Parameter humidity, final Climate.Parameter continentalness, final Climate.Parameter erosion, final Climate.Parameter weirdness, final float offset, final ResourceKey<Biome> biome) {
        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(1.1F), weirdness, offset), biome));
    }

}