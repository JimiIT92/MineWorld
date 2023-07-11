package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AzaleaBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.PropertyHelper;

import java.util.function.Supplier;

/**
 * Implementation class for a {@link MineWorld MineWorld} bush block
 */
public class TreeBushBlock extends AzaleaBlock {

    /**
     * {@link Supplier<AbstractTreeGrower> The supplier for the tree grower for this bush}
     */
    private Supplier<AbstractTreeGrower> treeGrowerSupplier;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties block properties}
     *
     * @param treeGrowerSupplier {@link Supplier<AbstractTreeGrower> The supplier for the tree grower}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     */
    public TreeBushBlock(Supplier<AbstractTreeGrower> treeGrowerSupplier, FeatureFlag... featureFlags) {
        super(PropertyHelper.copyFromBlock(Blocks.AZALEA, featureFlags));
        this.treeGrowerSupplier = treeGrowerSupplier;
    }

    /**
     * Grow a tree when the block is bonemealed
     *
     * @param level {@link ServerLevel The level reference}
     * @param random {@link RandomSource The random reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     */
    @Override
    public void performBonemeal(@NotNull ServerLevel level, @NotNull RandomSource random, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        this.treeGrowerSupplier.get().growTree(level, level.getChunkSource().getGenerator(), blockPos, blockState, random);
    }

    /**
     * Makes the block able to catch fire
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link Level The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Boolean True}
     */
    @Override
    public boolean isFlammable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return true;
    }

    /**
     * Get the block {@link Integer flammability value}
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link Level The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 60}
     */
    @Override
    public int getFlammability(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return 60;
    }

    /**
     * Get the block {@link Integer fire spread speed value}
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link Level The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 30}
     */
    @Override
    public int getFireSpreadSpeed(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction) {
        return 30;
    }

}