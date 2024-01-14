package org.mineworld.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.mineworld.MineWorld;
import org.mineworld.core.MWItems;

/**
 * {@link MineWorld MineWorld} Corn Crop
 */
public class CornBlock extends TallCropBlock {

    /**
     * Constructor. Set the {@link MWItems#CORN_SEEDS Corn Seeds}
     */
    public CornBlock() {
        super(MWItems.CORN_SEEDS);
    }

    /**
     * Get the {@link VoxelShape Block Shapes}
     *
     * @return {@link VoxelShape The Block Shapes}
     */
    @Override
    public VoxelShape[] getShapes() {
        return new VoxelShape[]{
                Block.box(4.0D, 0.0D, 4.0D, 12.0D, 6.0D, 12.0D),
                Block.box(4.0D, 0.0D, 4.0D, 12.0D, 12.0D, 12.0D),
                Block.box(4.0D, 0.0D, 4.0D, 12.0D, 14.0D, 12.0D),
                Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D)
        };
    }

}