package org.mineworld.block.weathering;

import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} interface for an {@link ChangeOverTimeBlock oxidizable and waxable block}
 */
public interface IMWChangeOverTimeBlock extends ChangeOverTimeBlock<WeatheringCopper.WeatherState> {

    /**
     * Get the {@link BlockState block state} for the next state with
     * the adjusted properties
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link BlockState The adjusted block state}
     */
    static BlockState getAdjustedBlockState(BlockState blockState) {
        if(blockState.hasProperty(BlockStateProperties.POWER)) {
            blockState = blockState.setValue(BlockStateProperties.POWER, 0);
        }
        return blockState;
    }

    /**
     * Get the {@link Float oxidization chance}
     * based on the current {@link WeatheringCopper.WeatherState weather state}
     *
     * @return {@link Float The oxidization chance}
     */
    default float getChanceModifier() {
        return this.getAge().equals(WeatheringCopper.WeatherState.UNAFFECTED) ? 0.75F : 1.0F;
    }

}