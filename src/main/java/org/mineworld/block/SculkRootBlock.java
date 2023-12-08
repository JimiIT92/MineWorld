package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWBlocks;

/**
 * Implementation class for a sculk root block
 */
public class SculkRootBlock extends BushBlock {

    /**
     * Constructor. Set the block properties
     */
    public SculkRootBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_CYAN)
                .replaceable()
                .noCollission()
                .instabreak()
                .sound(SoundType.SCULK)
                .offsetType(BlockBehaviour.OffsetType.XZ)
                .pushReaction(PushReaction.DESTROY)
        );
    }

    /**
     * Get the {@link VoxelShape block shape}
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param collisionContext {@link CollisionContext The collision context}
     * @return {@link VoxelShape The block shape}
     */
    @Override
    public @NotNull VoxelShape getShape(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull CollisionContext collisionContext) {
        return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D);
    }

    /**
     * Check if the bush can be placed on a certain block
     *
     * @param state {@link BlockState The block state where the bush is being placed on}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link Boolean True if the bush can be placed on top of the provided block}
     */
    @Override
    protected boolean mayPlaceOn(final @NotNull BlockState state, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos) {
        return state.is(Blocks.SCULK) || state.is(MWBlocks.SCULK_SOIL.get()) || super.mayPlaceOn(state, blockGetter, blockPos);
    }
}
