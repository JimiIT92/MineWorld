package org.mineworld.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.mineworld.core.MWBlocks;

/**
 * Implementation class for a {@link RopeHeadBlock rope head block}
 */
public class RopeBlock extends RopeHeadBlock {

    /**
     * {@link VoxelShape The rope shape}
     */
    protected static VoxelShape SHAPE = Block.box(6.0D, 4.0D, 6.0D, 10.0D, 16.0D, 10.0D);

    /**
     * Constructor. Set the block properties
     */
    public RopeBlock() {
        super(Direction.DOWN, SHAPE);
    }

    /**
     * Get the {@link Block rope body block}
     *
     * @return {@link Block The rope body block}
     */
    @Override
    protected Block getBodyBlock() {
        return MWBlocks.ROPE_TAIL.get();
    }

}