package org.mineworld.world;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBiomes;
import org.mineworld.core.MWBlocks;

/**
 * Implementation class for a {@link MineWorld MineWorld} terrablender surface data
 */
public class MWSurfaceRuleData {

    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource PERENNIAL_ICE = makeStateRule(MWBlocks.PERENNIAL_ICE.get());
    private static final SurfaceRules.RuleSource BLUE_ICE = makeStateRule(Blocks.BLUE_ICE);

    /**
     * Make the surface rules
     *
     * @return {@link SurfaceRules.RuleSource The surface rules}
     */
    public static SurfaceRules.RuleSource makeRules() {
        final SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        final SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(MWBiomes.FROZEN_PLAINS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, BLUE_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, PERENNIAL_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, PERENNIAL_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, PERENNIAL_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, PERENNIAL_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, PERENNIAL_ICE)
                        )),
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
        );
    }

    /**
     * Get a {@link SurfaceRules.RuleSource state rule}
     *
     * @param block {@link Block The block to make the state rule from}
     * @return {@link SurfaceRules.RuleSource The surface state rule}
     */
    private static SurfaceRules.RuleSource makeStateRule(final Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}