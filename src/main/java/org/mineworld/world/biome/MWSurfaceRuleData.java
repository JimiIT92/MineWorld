package org.mineworld.world.biome;

import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBiomes;
import org.mineworld.core.MWBlocks;

/**
 * {@link MineWorld MineWorld} {@link SurfaceRuleData Surface Rule Data}
 */
public final class MWSurfaceRuleData {

    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);
    private static final SurfaceRules.RuleSource BLACKSTONE = makeStateRule(Blocks.BLACKSTONE);
    private static final SurfaceRules.RuleSource PERENNIAL_ICE = makeStateRule(MWBlocks.PERENNIAL_ICE.get());
    private static final SurfaceRules.RuleSource BLUE_ICE = makeStateRule(Blocks.BLUE_ICE);
    private static final SurfaceRules.RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource LAVA_ROCK = makeStateRule(MWBlocks.LAVA_ROCK.get());
    private static final SurfaceRules.RuleSource MAGMA = makeStateRule(Blocks.MAGMA_BLOCK);
    private static final SurfaceRules.RuleSource DEEPSLATE = makeStateRule(Blocks.DEEPSLATE);

    /**
     * Make the {@link SurfaceRules.RuleSource Surface Rules}
     *
     * @return {@link SurfaceRules.RuleSource The Surface Rules}
     */
    public static SurfaceRules.RuleSource makeRules() {
        final SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        final SurfaceRules.ConditionSource sixBelowWater = SurfaceRules.waterStartCheck(-6, -1);
        final SurfaceRules.RuleSource blueIceSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, BLUE_ICE),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, PERENNIAL_ICE)
        );
        final SurfaceRules.RuleSource coarseDirtSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, COARSE_DIRT),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, STONE)
        );

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        sixBelowWater,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(SurfaceRules.isBiome(MWBiomes.FROZEN_PLAINS),
                                                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, PERENNIAL_ICE), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoise(), blueIceSurface), blueIceSurface))),
                                                SurfaceRules.ifTrue(SurfaceRules.isBiome(MWBiomes.WASTELAND),
                                                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, STONE), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoise(), coarseDirtSurface), STONE))),
                                                SurfaceRules.ifTrue(SurfaceRules.isBiome(MWBiomes.FOSSILS_WASTELAND), SurfaceRuleData.overworld()),
                                                SurfaceRules.ifTrue(SurfaceRules.isBiome(MWBiomes.VOLCANIC_PEAK),
                                                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, DEEPSLATE), SurfaceRules.ifTrue(surfaceNoise(), BLACKSTONE)))
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.DEEP_UNDER_FLOOR,
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(SurfaceRules.isBiome(MWBiomes.FROZEN_PLAINS), PERENNIAL_ICE),
                                                SurfaceRules.ifTrue(SurfaceRules.isBiome(MWBiomes.WASTELAND, MWBiomes.FOSSILS_WASTELAND), STONE)
                                        )
                                )
                        )
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(MWBiomes.FROZEN_PLAINS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(surfaceNoise(), blueIceSurface),
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, PERENNIAL_ICE),
                                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, STONE)
                                )
                        )
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(MWBiomes.WASTELAND, MWBiomes.FOSSILS_WASTELAND),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(surfaceNoise(), STONE),
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, STONE),
                                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, STONE)
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(MWBiomes.VOLCANIC_PEAK),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(surfaceNoiseAbove(2.8D), DEEPSLATE),
                                SurfaceRules.ifTrue(surfaceNoise(), BLACKSTONE),
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, BLACKSTONE),
                                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, BLACKSTONE)
                                )
                        )
                )
        );
    }

    /**
     * Get a {@link SurfaceRules.RuleSource Block State Surface Rule}
     *
     * @param block {@link Block The Block this Surface Rule is referring to}
     * @return {@link SurfaceRules.RuleSource The Block State Surface Rule}
     */
    private static SurfaceRules.RuleSource makeStateRule(final Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    /**
     * Get a {@link SurfaceRules.ConditionSource Surface Rule Condition} for generating the
     * terrain only above the Surface Threshold
     *
     * @return {@link SurfaceRules.ConditionSource The Surface Rule Condition}
     */
    private static SurfaceRules.ConditionSource surfaceNoise() {
        return surfaceNoiseAbove(2.6D);
    }

    /**
     * Get a {@link SurfaceRules.ConditionSource Surface Rule Condition} for generating the
     * terrain only above a certain Surface Threshold
     *
     * @param threshold {@link Double The Surface Threshold Value}
     * @return {@link SurfaceRules.ConditionSource The Surface Rule Condition}
     */
    private static SurfaceRules.ConditionSource surfaceNoiseAbove(final double threshold) {
        return SurfaceRules.noiseCondition(Noises.SURFACE, threshold / 8.25D, Double.MAX_VALUE);
    }

}