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
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} {@link BushBlock Sculk Root Block}
 */
public class SculkRootBlock extends BushBlock {

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     */
    public SculkRootBlock() {
        super(PropertyHelper.block(MapColor.COLOR_CYAN, 0F, 0F, false, SoundType.SCULK).replaceable().noCollission().ignitedByLava().offsetType(OffsetType.XZ).pushReaction(PushReaction.DESTROY));
    }

    /**
     * Get the {@link VoxelShape Block Shape}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param collisionContext {@link CollisionContext The collision context}
     * @return {@link VoxelShape The Block Shape}
     */
    @Override
    public @NotNull VoxelShape getShape(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull CollisionContext collisionContext) {
        return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D);
    }

    /**
     * Check if the Block can be placed at the given {@link BlockPos location}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Boolean True if the Block can be placed}
     */
    @Override
    protected boolean mayPlaceOn(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos) {
        return blockState.is(Blocks.SCULK) || blockState.is(MWBlocks.SCULK_SOIL.get()) || super.mayPlaceOn(blockState, blockGetter, blockPos);
    }

}