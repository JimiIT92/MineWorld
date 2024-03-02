package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWConfiguredFeatures;
import org.mineworld.core.MWSoundTypes;

/**
 * {@link MineWorld MineWorld} {@link MWSoilBlock Sculk Soil Block}
 */
public class SculkSoilBlock extends MWSoilBlock implements BonemealableBlock {

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     */
    public SculkSoilBlock() {
        super(MapColor.COLOR_LIGHT_BLUE, MWSoundTypes.SCULK_SOIL, () -> Blocks.SCULK.defaultBlockState());
    }

    /**
     * Check if the Block can be bonemealed
     *
     * @param levelReader {@link LevelReader The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if the Block can be bonemealed}
     */
    @Override
    public boolean isValidBonemealTarget(final LevelReader levelReader, final BlockPos blockPos, final @NotNull BlockState blockState) {
        return levelReader.getBlockState(blockPos.above()).isAir();
    }

    /**
     * Check if the {@link Block Block} has been successfully bonemealed
     *
     * @param level {@link Level The level reference}
     * @param randomSource {@link RandomSource The random reference}
     * @param blockPos {@link BlockPos The current BlockPos}
     * @param blockState {@link BlockState The current BlockState}
     * @return {@link Boolean#TRUE True}
     */
    @Override
    public boolean isBonemealSuccess(final @NotNull Level level, final @NotNull RandomSource randomSource, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return true;
    }

    /**
     * Grow some vegetation when the {@link Block Block} is bonemealed
     *
     * @param level {@link ServerLevel The Level reference}
     * @param random {@link RandomSource The Random reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     */
    @Override
    public void performBonemeal(final ServerLevel level, final @NotNull RandomSource random, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE)
                .getHolder(MWConfiguredFeatures.PATCH_SCULK_ROOTS)
                .ifPresent(feature -> feature.value().place(level, level.getChunkSource().getGenerator(), random, blockPos.above()));
    }

}