package org.mineworld.block.weathering;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlockSetTypes;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} {@link IMWWeatheringBlock Weathering} {@link TrapDoorBlock Trapdoor Block}
 */
public class WeatheringCopperTrapdoorBlock extends TrapDoorBlock implements IMWWeatheringBlock, IMWWaxableBlock {

    /**
     * {@link WeatheringCopper.WeatherState The Trapdoor Weather State}
     */
    private final WeatheringCopper.WeatherState weatherState;

    /**
     * Constructor. Set the Block properties
     *
     * @param weatherState {@link WeatheringCopper.WeatherState The Weather State}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public WeatheringCopperTrapdoorBlock(final WeatheringCopper.WeatherState weatherState, final FeatureFlag... featureFlags) {
        super(PropertyHelper.copy(Blocks.IRON_TRAPDOOR, featureFlags), MWBlockSetTypes.COPPER.get());
        this.weatherState = weatherState;
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
        IMWWeatheringBlock.randomTick(this, blockState, level, blockPos, randomSource);
    }

    /**
     * Check if the Block should randomly ticking
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if the Block is not waxed and fully oxidized}
     */
    @Override
    public boolean isRandomlyTicking(final @NotNull BlockState blockState) {
        return IMWWeatheringBlock.isRandomlyTicking(blockState);
    }

    /**
     * Get the Block {@link WeatheringCopper.WeatherState Weather State}
     *
     * @return {@link #weatherState The Block Weather State}
     */
    @Override
    public @NotNull WeatheringCopper.WeatherState getAge() {
        return this.weatherState;
    }

    /**
     * Get the {@link BlockState modified Block State} after interacting with a tool
     *
     * @param blockState {@link BlockState The current Block State}
     * @param context {@link UseOnContext The Item Use Context}
     * @param toolAction {@link ToolAction The tool action}
     * @param isClient {@link Boolean If the scraping only happened on the Client}
     * @return {@link BlockState The modified Block State}
     */
    @Override
    public @Nullable BlockState getToolModifiedState(final BlockState blockState, final UseOnContext context, final ToolAction toolAction, final boolean isClient) {
        final BlockState modifiedState = IMWWeatheringBlock.getToolModifiedState(blockState, context, toolAction, isClient);
        return modifiedState != null ? modifiedState : super.getToolModifiedState(blockState, context, toolAction, isClient);
    }

}