package org.mineworld.block.weathering;

import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} interface for an {@link ChangeOverTimeBlock oxidizable and waxable Block}
 */
public interface IMWChangeOverTimeBlock extends ChangeOverTimeBlock<WeatheringCopper.WeatherState> {

    /**
     * Get the {@link BlockState Block State} for the next state with the adjusted properties
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link BlockState The adjusted Block State}
     */
    static BlockState getAdjustedBlockState(BlockState blockState) {
        if(blockState.hasProperty(BlockStateProperties.POWER)) {
            blockState = blockState.setValue(BlockStateProperties.POWER, 0);
        }
        return blockState;
    }

    /**
     * Get the {@link Float oxidization chance}
     * based on the current {@link WeatheringCopper.WeatherState Weather State}
     *
     * @return {@link Float The Oxidization Chance}
     */
    default float getChanceModifier() {
        return this.getAge().equals(WeatheringCopper.WeatherState.UNAFFECTED) ? 0.75F : 1.0F;
    }

}