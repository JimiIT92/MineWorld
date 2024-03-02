package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.BlockGetter;
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
 * Implementation class for a {@link MineWorld MineWorld} Bush Block
 */
public class TreeBushBlock extends AzaleaBlock {

    /**
     * {@link Supplier<AbstractTreeGrower> The Supplier for the Tree Grower for this Bush}
     */
    private final Supplier<AbstractTreeGrower> treeGrowerSupplier;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param treeGrowerSupplier {@link Supplier<AbstractTreeGrower> The Supplier for the Tree Grower for this Bush}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public TreeBushBlock(final Supplier<AbstractTreeGrower> treeGrowerSupplier, final FeatureFlag... featureFlags) {
        super(PropertyHelper.copy(Blocks.AZALEA, featureFlags));
        this.treeGrowerSupplier = treeGrowerSupplier;
    }

    /**
     * Grow a Tree when the Bush is bonemealed
     *
     * @param level {@link ServerLevel The Level reference}
     * @param random {@link RandomSource The Random reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     */
    @Override
    public void performBonemeal(final @NotNull ServerLevel level, final @NotNull RandomSource random, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        this.treeGrowerSupplier.get().growTree(level, level.getChunkSource().getGenerator(), blockPos, blockState, random);
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
     * @return {@link Integer 60}
     */
    @Override
    public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return 60;
    }

    /**
     * Get the Block {@link Integer fire spread speed value}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 30}
     */
    @Override
    public int getFireSpreadSpeed(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return 30;
    }

}