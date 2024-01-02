package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.PropertyHelper;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link WallBlock Glass Wall Block}
 */
public class GlassWallBlock extends WallBlock {

    public GlassWallBlock(final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        super(PropertyHelper.makeTranslucent(blockStateSupplier.get().getBlock(), featureFlags).requiresCorrectToolForDrops());
    }

    /**
     * Get the {@link VoxelShape Block visual shape}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param collisionContext {@link CollisionContext The collision context}
     * @return {@link Shapes#empty Empty shape}
     */
    public @NotNull VoxelShape getVisualShape(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull CollisionContext collisionContext) {
        return Shapes.empty();
    }

    /**
     * Get the {@link Float block shade brightness}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Float 1.0}
     */
    public float getShadeBrightness(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos) {
        return 1.0F;
    }

    /**
     * Check if the block can propagate the skylight
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Boolean#TRUE True}
     */
    public boolean propagatesSkylightDown(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos) {
        return true;
    }

    /**
     * Determine if the face should be rendered based on the neighbor state
     *
     * @param blockState {@link BlockState The current Block State}
     * @param neighborState {@link BlockState The neighbor Block State}
     * @param direction {@link Direction The direction of the face to be rendered}
     * @return {@link Boolean True if the face should be rendered}
     */
    public boolean skipRendering(final @NotNull BlockState blockState, final @NotNull BlockState neighborState, final @NotNull Direction direction) {
        return neighborState.is(this) || super.skipRendering(blockState, neighborState, direction);
    }

}