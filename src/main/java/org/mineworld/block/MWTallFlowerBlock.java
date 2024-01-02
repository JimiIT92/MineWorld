package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.mineworld.MineWorld;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} {@link TallFlowerBlock Tall Flower Block}
 */
public class MWTallFlowerBlock extends TallFlowerBlock {

    /**
     * Constructor. Set the Block properties
     *
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public MWTallFlowerBlock(final FeatureFlag... featureFlags) {
        super(PropertyHelper.copy(Blocks.ROSE_BUSH, featureFlags));
    }

    /**
     * Makes the block able to catch fire
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Boolean#TRUE True}
     */
    @Override
    public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return true;
    }

    /**
     * Get the block {@link Integer flammability value}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 100}
     */
    @Override
    public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return 100;
    }

    /**
     * Get the block {@link Integer fire spread speed value}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 60}
     */
    @Override
    public int getFireSpreadSpeed(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return 60;
    }

}