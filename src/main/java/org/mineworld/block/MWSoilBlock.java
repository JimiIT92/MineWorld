package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.lighting.LightEngine;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.PropertyHelper;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link Block Soil Block}
 */
public class MWSoilBlock extends Block {

    /**
     * {@link Supplier<BlockState> The Supplier for the Decayed Block State}
     */
    private final Supplier<BlockState> blockStateSupplier;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param color {@link MapColor The Block Color on maps}
     * @param sound {@link SoundType The Block Sound}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Decayed Block State}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public MWSoilBlock(final MapColor color, final SoundType sound, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        super(PropertyHelper.block(color, 0.4F, 0.4F, true, sound, featureFlags).instrument(NoteBlockInstrument.BASEDRUM).randomTicks());
        this.blockStateSupplier = blockStateSupplier;
    }

    /**
     * Check if a {@link Block Block} can be {@link Block Soil Block}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param levelReader {@link LevelReader The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Boolean True if the Block can be a Soil Block}
     */
    private static boolean canBeSoil(final BlockState blockState, final LevelReader levelReader, final BlockPos blockPos) {
        final BlockPos aboveBlockPos = blockPos.above();
        final BlockState aboveBlockState = levelReader.getBlockState(aboveBlockPos);
        return LightEngine.getLightBlockInto(levelReader, blockState, blockPos, aboveBlockState, aboveBlockPos, Direction.UP, aboveBlockState.getLightBlock(levelReader, aboveBlockPos)) < levelReader.getMaxLightLevel();
    }

    /**
     * Randomly ticks the Block
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    @Override
    public void randomTick(final @NotNull BlockState blockState, final @NotNull ServerLevel level, final @NotNull BlockPos blockPos, final @NotNull RandomSource randomSource) {
        if (!canBeSoil(blockState, level, blockPos)) {
            level.setBlockAndUpdate(blockPos, this.blockStateSupplier.get());
        }
    }

}