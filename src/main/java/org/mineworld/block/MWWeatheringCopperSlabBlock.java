package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.WeatheringCopperSlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Implementation class for a {@link WeatheringCopperSlabBlock weathering copper slab}
 */
public class MWWeatheringCopperSlabBlock extends SlabBlock implements IMWWeatheringBlock {

    /**
     * {@link WeatheringCopper.WeatherState The stair weather state}
     */
    private final WeatheringCopper.WeatherState weatherState;
    /**
     * {@link Boolean If this block is waxed}. Used to determine the right click action
     * and to make the block not ticking
     */
    private final boolean isWaxed;

    /**
     * Constructor. Set the block properties
     *
     * @param weatherState {@link WeatheringCopper.WeatherState The weather state}
     * @param properties {@link BlockState The block properties}
     * @param isWaxed {@link Boolean If the block is waxed}
     */
    public MWWeatheringCopperSlabBlock(final WeatheringCopper.WeatherState weatherState, final BlockBehaviour.Properties properties, final boolean isWaxed) {
        super(properties);
        this.weatherState = weatherState;
        this.isWaxed = isWaxed;
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
        if(!this.isWaxed) {
            this.onRandomTick(blockState, level, blockPos, random);
        }
    }

    /**
     * Check if the block should randomly ticking
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link Boolean True if there is another state}
     */
    @Override
    public boolean isRandomlyTicking(final @NotNull BlockState blockState) {
        return !this.isWaxed && IMWWeatheringBlock.getNext(blockState.getBlock()).isPresent();
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
        if(!isWaxed) {
            final BlockState scrapedState = IMWWeatheringBlock.scrapeWeatherState(state, context, toolAction, isClient);
            return scrapedState != null ? scrapedState : super.getToolModifiedState(state, context, toolAction, isClient);
        }
        if(context.getItemInHand().getItem() instanceof AxeItem && toolAction.equals(ToolActions.AXE_WAX_OFF)) {
            return IMWWeatheringBlock.scrapeWax(state, context, toolAction, isClient);
        }
        return super.getToolModifiedState(state, context, toolAction, isClient);
    }
}
