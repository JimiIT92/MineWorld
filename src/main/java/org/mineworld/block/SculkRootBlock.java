package org.mineworld.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
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
     * {@link MapCodec The Block Codec}
     */
    public static final MapCodec<SculkRootBlock> CODEC = simpleCodec(SculkRootBlock::new);

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     */
    public SculkRootBlock() {
        this(PropertyHelper.block(MapColor.COLOR_CYAN, 0F, 0F, false, SoundType.SCULK).replaceable().noCollission().ignitedByLava().offsetType(OffsetType.XZ).pushReaction(PushReaction.DESTROY));
    }

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param properties The {@link BlockBehaviour.Properties Block Properties}
     */
    public SculkRootBlock(final BlockBehaviour.Properties properties) {
        super(properties);
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

    /**
     * Get the {@link MapCodec Block Codec}
     *
     * @return {@link MapCodec The Block Codec}
     */
    @Override
    protected @NotNull MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

}