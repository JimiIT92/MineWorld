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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWConfiguredFeatures;
import org.mineworld.core.MWSoundTypes;

/**
 * Implementation class for a Sculk Soil block
 */
public class SculkSoilBlock extends MWSoilBlock implements BonemealableBlock {

    /**
     * Constructor. Set the block properties
     */
    public SculkSoilBlock() {
        super(MapColor.COLOR_LIGHT_BLUE, MWSoundTypes.SCULK_SOIL);
    }

    /**
     * Get the decayed block
     *
     * @return {@link Blocks#SCULK The sculk block}
     */
    @Override
    public Block getDecayedBlock() {
        return Blocks.SCULK;
    }

    /**
     * Check if the block can be bonemealed
     *
     * @param level {@link LevelReader The level reference}
     * @param blockPos {@link BlockPos The current BlockPos}
     * @param blockState {@link BlockState The current BlockState}
     * @return {@link Boolean True if there is no block above}
     */
    @Override
    public boolean isValidBonemealTarget(final LevelReader level, final BlockPos blockPos, final @NotNull BlockState blockState) {
        return level.getBlockState(blockPos.above()).isAir();
    }

    /**
     * Check if the block has been successfully bonemealed
     *
     * @param level {@link Level The level reference}
     * @param random {@link RandomSource The random reference}
     * @param blockPos {@link BlockPos The current BlockPos}
     * @param blockState {@link BlockState The current BlockState}
     * @return {@link Boolean True}
     */
    @Override
    public boolean isBonemealSuccess(final @NotNull Level level, final @NotNull RandomSource random, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return true;
    }

    /**
     * Place some {@link MWConfiguredFeatures#PATCH_SCULK_ROOTS sculk roots} when bonemealing the block
     *
     * @param level {@link ServerLevel The level reference}
     * @param random {@link RandomSource The random reference}
     * @param blockPos {@link BlockPos The current BlockPos}
     * @param blockState {@link BlockState The current BlockState}
     */
    @Override
    public void performBonemeal(final ServerLevel level, final @NotNull RandomSource random, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE)
                .getHolder(MWConfiguredFeatures.PATCH_SCULK_ROOTS)
                .ifPresent(feature -> feature.value().place(level, level.getChunkSource().getGenerator(), random, blockPos.above()));
    }
}
