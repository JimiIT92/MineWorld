package org.mineworld.block.weathering;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.helper.PropertyHelper;

/**
 * Implementation class for a {@link ChangeOverTimeBlock weathering bars}
 */
public class WeatheringCopperCageBlock extends Block implements IMWWeatheringBlock, IMWWaxableBlock {

    /**
     * {@link WeatheringCopper.WeatherState The stair weather state}
     */
    private final WeatheringCopper.WeatherState weatherState;

    /**
     * Constructor. Set the block properties
     *
     * @param weatherState {@link WeatheringCopper.WeatherState The weather state}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     */
    public WeatheringCopperCageBlock(final WeatheringCopper.WeatherState weatherState, final FeatureFlag... featureFlags) {
        super(PropertyHelper.makeTranslucentBlock(Blocks.COPPER_BLOCK, featureFlags));
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
        IMWWeatheringBlock.randomTick(this, blockState, level, blockPos, random);
    }

    /**
     * Check if the block should randomly ticking
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link Boolean True if there is another state}
     */
    @Override
    public boolean isRandomlyTicking(final @NotNull BlockState blockState) {
        return IMWWeatheringBlock.isRandomlyTicking(blockState);
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
     * @param context {@link UseOnContext The id use context}
     * @param toolAction {@link ToolAction The tool action}
     * @param isClient {@link Boolean If the code is executed client side}
     * @return {@link BlockState The modified block state}
     */
    @Override
    public @Nullable BlockState getToolModifiedState(final BlockState state, final UseOnContext context, final ToolAction toolAction, final boolean isClient) {
        final BlockState modifiedState = IMWWeatheringBlock.getToolModifiedState(state, context, toolAction, isClient);
        return modifiedState != null ? modifiedState : super.getToolModifiedState(state, context, toolAction, isClient);
    }

}