package org.mineworld.block;

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
 * Implementation class for a {@link WeatheringCopperStairBlock waxed weathering copper stairs}
 */
public class MWWaxedWeatheringCopperStairBlock extends StairBlock implements IMWWeatheringBlock {

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
    public MWWaxedWeatheringCopperStairBlock(final WeatheringCopper.WeatherState weatherState, final BlockState blockState) {
        super(blockState, MWBlocks.copyFrom(blockState.getBlock()));
        this.weatherState = weatherState;
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
        final BlockState scrapedState = IMWWeatheringBlock.scrapeWax(state, context, toolAction, isClient);
        return scrapedState != null ? scrapedState : super.getToolModifiedState(state, context, toolAction, isClient);
    }
}
