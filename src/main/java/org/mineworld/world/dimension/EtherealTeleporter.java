package org.mineworld.world.dimension;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWDimensions;
import org.mineworld.core.MWPoiTypes;

/**
 * Implementation class for the Ethereal Dimension {@link MWTeleporter teleporter}
 */
public class EtherealTeleporter extends MWTeleporter {

    /**
     * Constructor. Set the {@link ServerLevel level} reference and
     * the default portal frame block state
     *
     * @param level {@link ServerLevel The server level reference}
     */
    public EtherealTeleporter(final ServerLevel level) {
        super(level, Blocks.REINFORCED_DEEPSLATE.defaultBlockState());
    }

    /**
     * Get the {@link ResourceKey <Level> dimension key} this teleporter is referring to
     *
     * @return {@link ResourceKey<Level> The dimension key}
     */
    @Override
    public ResourceKey<Level> getDimension() {
        return MWDimensions.ETHEREAL;
    }

    /**
     * Get the {@link ResourceKey<PoiType> PoiType key} this teleporter is referring to
     *
     * @return {@link ResourceKey<PoiType> The PoiType key}
     */
    @Override
    public ResourceKey<PoiType> getPoiType() {
        return MWPoiTypes.ETHEREAL_PORTAL.getKey();
    }

    /**
     * Get the {@link BlockState portal block state}
     *
     * @return {@link BlockState The portal block state}
     */
    @Override
    public BlockState getPortalState() {
        return MWBlocks.ETHEREAL_PORTAL.get().defaultBlockState();
    }

}