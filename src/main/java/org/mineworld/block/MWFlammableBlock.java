package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.mineworld.MineWorld;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link Block Flammable Block}
 */
public class MWFlammableBlock extends Block {

    /**
     * The {@link Integer Block Flammability} value
     */
    private final int flammability;
    /**
     * The {@link Integer Block Fire Spread} value
     */
    private final int fireSpread;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param flammability The {@link Integer Block Flammability} value
     * @param fireSpread The {@link Integer Block Fire Spread} value
     * @param properties The {@link BlockBehaviour.Properties Block Properties}
     */
    public MWFlammableBlock(final int flammability, final int fireSpread, final Properties properties) {
        super(properties);
        this.flammability = flammability;
        this.fireSpread = fireSpread;
    }

    /**
     * Check if the Block can catch fire
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Boolean True if the source Block is flammable}
     */
    @Override
    public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return true;
    }

    /**
     * Get the Block {@link Integer flammability value}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link #flammability The Block flammability value}
     */
    @Override
    public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return flammability;
    }

    /**
     * Get the Block {@link Integer fire spread speed value}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link #fireSpread The Block fire spread value}
     */
    @Override
    public int getFireSpreadSpeed(final BlockState blockState,final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return fireSpread;
    }

}