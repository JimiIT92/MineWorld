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
     * Constructor. Set the corn seeds
     */
    public CornBlock() {
        super(MWItems.CORN_SEEDS);
    }

    /**
     * Get the {@link VoxelShape corn shapes} by age
     *
     * @return {@link VoxelShape The corn shapes} by age
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