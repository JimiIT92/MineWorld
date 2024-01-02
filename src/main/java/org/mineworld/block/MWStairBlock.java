package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.BlockHelper;
import org.mineworld.helper.PropertyHelper;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link StairBlock Stair Block}
 */
public class MWStairBlock extends StairBlock {

    /**
     * {@link Supplier<BlockState> The Stair Block State Supplier}
     */
    private final Supplier<BlockState> blockStateSupplier;

    /**
     * Constructor. Set the Block properties
     *
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State this Stair is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public MWStairBlock(final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        super(blockStateSupplier, PropertyHelper.copy(blockStateSupplier.get().getBlock(), featureFlags).requiresCorrectToolForDrops());
        this.blockStateSupplier = blockStateSupplier;
    }

    /**
     * Get the {@link PushReaction push reaction} when this block is pushed by pistons
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link PushReaction The appropriate Push Reaction based on the Stair material}
     */
    public @NotNull PushReaction getPistonPushReaction(final @NotNull BlockState blockState) {
        return BlockHelper.getPushReaction(blockStateSupplier.get());
    }

    /**
     * Determine if the {@link StairBlock Stair Block} is flammable
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The update direction}
     * @return {@link Boolean True if the block is flammable}
     */
    @Override
    public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return blockStateSupplier.get().isFlammable(blockGetter, blockPos, direction);
    }

    /**
     * Get the {@link Integer block flammability value}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The update direction}
     * @return {@link Integer The block flammability value}
     */
    @Override
    public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return 5;
    }

    /**
     * Get the {@link Integer block fire spread chance value}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The update direction}
     * @return {@link Integer The block fire spread chance value}
     */
    @Override
    public int getFireSpreadSpeed(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return 20;
    }

}