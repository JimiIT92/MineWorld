package org.mineworld.item;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWDimensions;

/**
 * Implementation class for the {@link MWFlintAndSteelItem corrupted dimension flitn and steel}
 */
public class CorruptedFlintAndSteelItem extends MWFlintAndSteelItem {

    /**
     * Get the {@link ResourceKey<Level> portal dimension key} that can be lit by this flint and steel
     *
     * @return {@link ResourceKey<Level> The portal dimension key}
     */
    @Override
    public ResourceKey<Level> getDimension() {
        return MWDimensions.CORRUPTED_DIMENSION_KEY;
    }

    /**
     * Get the {@link RegistryObject<Block> portal block}
     *
     * @return {@link RegistryObject<Block> The portal block}
     */
    @Override
    public RegistryObject<Block> getPortalBlock() {
        return MWBlocks.CORRUPTED_PORTAL;
    }

}