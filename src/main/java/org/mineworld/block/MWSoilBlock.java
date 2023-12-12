package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.lighting.LightEngine;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.PropertyHelper;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link Block soil block}
 */
public abstract class MWSoilBlock extends Block {

    /**
     * Constructor. Set the block properties
     *
     * @param color {@link MapColor The block map color}
     * @param sound {@link SoundType The block sound}
     */
    public MWSoilBlock(final MapColor color, final SoundType sound) {
        super(PropertyHelper.basicBlockProperties(color, 0.4F, 0.4F, true, sound).instrument(NoteBlockInstrument.BASEDRUM).randomTicks());
    }

    /**
     * Check if a block can be soil
     *
     * @param blockState {@link BlockState The current BlockState}
     * @param level {@link LevelReader The level reference}
     * @param blockPos {@link BlockPos The current BlockPos}
     * @return {@link Boolean True if the block can be a soil block}
     */
    private static boolean canBeSoil(final BlockState blockState, final LevelReader level, final BlockPos blockPos) {
        final BlockPos aboveBlockPos = blockPos.above();
        final BlockState aboveBlockState = level.getBlockState(aboveBlockPos);
        return LightEngine.getLightBlockInto(level, blockState, blockPos, aboveBlockState, aboveBlockPos, Direction.UP, aboveBlockState.getLightBlock(level, aboveBlockPos)) < level.getMaxLightLevel();
    }

    /**
     * Randomly ticks the block
     *
     * @param blockState {@link BlockState The current BlockState}
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current BlockPos}
     * @param random {@link RandomSource The random reference}
     */
    @Override
    public void randomTick(final @NotNull BlockState blockState, final @NotNull ServerLevel level, final @NotNull BlockPos blockPos, final @NotNull RandomSource random) {
        if (!canBeSoil(blockState, level, blockPos)) {
            level.setBlockAndUpdate(blockPos, getDecayedBlock().defaultBlockState());
        }
    }

    /**
     * Get the {@link Block decayed block}
     *
     * @return {@link Block The decayed block}
     */
    public abstract Block getDecayedBlock();

}