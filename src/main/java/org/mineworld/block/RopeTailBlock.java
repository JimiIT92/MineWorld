package org.mineworld.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.mineworld.core.MWBlocks;

/**
 * Implementation class for a {@link RopeBodyBlock rope tail block}
 */
public class RopeTailBlock extends RopeBodyBlock {

    /**
     * {@link VoxelShape The rope shape}
     */
    protected static final VoxelShape SHAPE = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);

    /**
     * Constructor. Set the block properties
     */
    public RopeTailBlock() {
        super(Direction.DOWN, SHAPE);
    }

    /**
     * Get the {@link Block rope head block}
     *
     * @return {@link Block The rope head block}
     */
    @Override
    protected RopeHeadBlock getHeadBlock() {
        return (RopeHeadBlock)MWBlocks.ROPE.get();
    }

}