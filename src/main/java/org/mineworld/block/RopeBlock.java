package org.mineworld.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.mineworld.core.MWBlocks;

/**
 * Implementation class for a {@link RopeHeadBlock Rope Head Block}
 */
public class RopeBlock extends RopeHeadBlock {

    /**
     * {@link VoxelShape The Rope shape}
     */
    protected static final VoxelShape SHAPE = Block.box(6.0D, 4.0D, 6.0D, 10.0D, 16.0D, 10.0D);

    /**
     * Constructor. Set the Block properties
     */
    public RopeBlock() {
        super(Direction.DOWN, SHAPE);
    }

    /**
     * Get the {@link Block Rope Body Block}
     *
     * @return {@link Block The Rope Body Block}
     */
    @Override
    protected Block getBodyBlock() {
        return MWBlocks.ROPE_TAIL.get();
    }

}