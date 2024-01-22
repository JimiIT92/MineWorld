package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.mineworld.MineWorld;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.helper.ResourceHelper;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link FenceGateBlock Fence Gate Block}
 */
public class MWFenceGateBlock extends FenceGateBlock {

    /**
     * Constructor. Set the {@link Properties Block Properties}
     *
     * @param woodTypeSupplier {@link Supplier<WoodType> The Supplier for the Wood Type this Fence Gate is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public MWFenceGateBlock(final Supplier<WoodType> woodTypeSupplier, final FeatureFlag... featureFlags) {
        super(PropertyHelper.copy(Blocks.OAK_FENCE, featureFlags)
                .mapColor(ResourceHelper.woodColor(woodTypeSupplier.get()))
                .sound(woodTypeSupplier.get().soundType())
                .strength(2.0F, 3.0F), woodTypeSupplier.get()
        );
    }

    /**
     * Check if the Block can catch fire
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
     * Get the Block {@link Integer flammability value}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 5}
     */
    @Override
    public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return 5;
    }

    /**
     * Get the Block {@link Integer fire spread speed value}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 20}
     */
    @Override
    public int getFireSpreadSpeed(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return 20;
    }

}