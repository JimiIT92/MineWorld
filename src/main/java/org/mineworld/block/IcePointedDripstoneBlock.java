package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link MWPointedDripstoneBlock Ice Pointed Dripstone Block}
 */
public class IcePointedDripstoneBlock extends MWPointedDripstoneBlock {

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param blockSupplier {@link Supplier < Block > The Supplier for the Block this Pointed Dripstone is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public IcePointedDripstoneBlock(final Supplier<Block> blockSupplier, final FeatureFlag... featureFlags) {
        super(blockSupplier, featureFlags);
    }

    /**
     * Drop the {@link Block Falling Dripstones} after it falls
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param fallingBlockEntity {@link FallingBlockEntity The falling Pointed Dripstone Entity}
     */
    @Override
    public void onBrokenAfterFall(final @NotNull Level level, final @NotNull BlockPos blockPos, final FallingBlockEntity fallingBlockEntity) {

    }

    /**
     * Get the {@link FallingBlockEntity falling Pointed Dripstone Entity}
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link FallingBlockEntity The Pointed Dripstone Entity}
     */
    @Override
    public FallingBlockEntity getFallingBlockEntity(final Level level, final BlockPos blockPos, final BlockState blockState) {
        final FallingBlockEntity fallingDripstone = super.getFallingBlockEntity(level, blockPos, blockState);
        fallingDripstone.dropItem = false;
        return fallingDripstone;
    }

}