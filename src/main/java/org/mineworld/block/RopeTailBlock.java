package org.mineworld.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.mineworld.core.MWBlocks;

/**
 * Implementation class for a {@link RopeBodyBlock Rope Tail Block}
 */
public class RopeTailBlock extends RopeBodyBlock {

    /**
     * {@link VoxelShape The Rope shape}
     */
    protected static final VoxelShape SHAPE = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     */
    public RopeTailBlock() {
        super(Direction.DOWN, SHAPE);
    }

    /**
     * Get the {@link RopeHeadBlock Rope Head Block}
     *
     * @return {@link RopeHeadBlock The Rope Head Block}
     */
    @Override
    protected RopeHeadBlock getHeadBlock() {
        return (RopeHeadBlock) MWBlocks.ROPE.get();
    }

}