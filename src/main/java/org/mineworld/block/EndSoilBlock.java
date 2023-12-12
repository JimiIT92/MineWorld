package org.mineworld.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;
import org.mineworld.core.MWSoundTypes;

/**
 * Implementation class for an End Soil block
 */
public class EndSoilBlock extends MWSoilBlock  {

    /**
     * Constructor. Set the block properties
     */
    public EndSoilBlock() {
        super(MapColor.COLOR_GREEN, MWSoundTypes.END_SOIL);
    }

    /**
     * Get the decayed block
     *
     * @return {@link Blocks#END_STONE The end stone block}
     */
    @Override
    public Block getDecayedBlock() {
        return Blocks.END_STONE;
    }

}