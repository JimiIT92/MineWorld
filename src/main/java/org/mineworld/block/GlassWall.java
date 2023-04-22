package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.mineworld.helper.PropertyHelper;

import java.util.function.Supplier;

public class GlassWall extends WallBlock {

    /**
     * Constructor. Set up the block properties
     *
     * @param blockSupplier {@link Supplier<Block> The supplier for the block this wall is based on}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     */
    public GlassWall(final Supplier<? extends Block> blockSupplier, final FeatureFlag... featureFlags) {
        super(PropertyHelper.copyFromBlock(blockSupplier.get(), featureFlags).requiresCorrectToolForDrops());
    }

    /**
     * Get the {@link VoxelShape block visual shape}
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param collisionContext {@link CollisionContext The collision context}
     * @return {@link Shapes#empty() Empty shape}
     */
    public @NotNull VoxelShape getVisualShape(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull CollisionContext collisionContext) {
        return Shapes.empty();
    }

    /**
     * Get the {@link Float block shade brightness}
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link Float 1.0}
     */
    public float getShadeBrightness(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos) {
        return 1.0F;
    }

    /**
     * Check if the block can propagate the skylight
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link Boolean True}
     */
    public boolean propagatesSkylightDown(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos) {
        return true;
    }

    /**
     * Determine if the face should be rendered based on the neighbor state
     *
     * @param blockState {@link BlockState The current block state}
     * @param neighborState {@link BlockState The neighbor block state}
     * @param direction {@link Direction The direction of the face to be rendered}
     * @return {@link Boolean True if the face should be rendered}
     */
    public boolean skipRendering(final @NotNull BlockState blockState, final BlockState neighborState, final @NotNull Direction direction) {
        return neighborState.is(this) || super.skipRendering(blockState, neighborState, direction);
    }
}
