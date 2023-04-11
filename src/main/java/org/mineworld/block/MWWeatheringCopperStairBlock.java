package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.WeatheringCopperStairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.core.MWBlocks;

/**
 * Implementation class for a {@link WeatheringCopperStairBlock weathering copper stairs}
 */
public class MWWeatheringCopperStairBlock extends StairBlock implements IMWWeatheringBlock {

    /**
     * {@link WeatheringCopper.WeatherState The stair weather state}
     */
    private final WeatheringCopper.WeatherState weatherState;

    /**
     * Constructor. Set the block properties
     *
     * @param weatherState {@link WeatheringCopper.WeatherState The weather state}
     * @param blockState {@link BlockState The block state this stair is based on}
     */
    public MWWeatheringCopperStairBlock(final WeatheringCopper.WeatherState weatherState, final BlockState blockState) {
        super(blockState, MWBlocks.copyFrom(blockState.getBlock()));
        this.weatherState = weatherState;
    }

    /**
     * Change the {@link WeatheringCopper.WeatherState weather state}
     * on random tick
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link ServerLevel The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param random {@link RandomSource The random reference}
     */
    @Override
    public void randomTick(final @NotNull BlockState blockState, final @NotNull ServerLevel level, final @NotNull BlockPos blockPos, final @NotNull RandomSource random) {
        this.onRandomTick(blockState, level, blockPos, random);
    }

    /**
     * Check if the block should randomly ticking
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link Boolean True if there is another state}
     */
    @Override
    public boolean isRandomlyTicking(final @NotNull BlockState blockState) {
        return IMWWeatheringBlock.getNext(blockState.getBlock()).isPresent();
    }

    /**
     * Get the block {@link WeatheringCopper.WeatherState weather state}
     *
     * @return {@link WeatheringCopper.WeatherState The block weather state}
     */
    @Override
    public @NotNull WeatheringCopper.WeatherState getAge() {
        return this.weatherState;
    }

    /**
     * Sets the previous {@link WeatheringCopper.WeatherState weather state}
     * on right click with an {@link AxeItem axe}
     *
     * @param state {@link BlockState The current block state}
     * @param context {@link UseOnContext The item use context}
     * @param toolAction {@link ToolAction The tool action}
     * @param isClient {@link Boolean If the code is executed client side}
     * @return {@link BlockState The modified block state}
     */
    @Override
    public @Nullable BlockState getToolModifiedState(final BlockState state, final UseOnContext context, final ToolAction toolAction, final boolean isClient) {
        final BlockState scrapedState = IMWWeatheringBlock.scrapeWeatherState(state, context, toolAction, isClient);
        return scrapedState != null ? scrapedState : super.getToolModifiedState(state, context, toolAction, isClient);
    }
}
