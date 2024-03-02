package org.mineworld.world.dimension;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.ITeleporter;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWDimensions;
import org.mineworld.core.MWPoiTypes;

/**
 * {@link MineWorld MineWorld} {@link MWTeleporter Ethereal Teleporter}
 */
public class EtherealTeleporter extends MWTeleporter {

    /**
     * Constructor. Set the {@link ITeleporter Teleporter} properties
     *
     * @param level {@link ServerLevel The level reference}
     */
    public EtherealTeleporter(final ServerLevel level) {
        super(level, Blocks.REINFORCED_DEEPSLATE.defaultBlockState());
    }

    /**
     * Get the {@link ResourceKey<Level> dimension key} this portal is referring to
     *
     * @return {@link ResourceKey<Level> The dimension key}
     */
    @Override
    public ResourceKey<Level> getDimension() {
        return MWDimensions.ETHEREAL;
    }

    /**
     * Get the {@link ResourceKey<PoiType> POI Type key} this portal is referring to
     *
     * @return {@link ResourceKey<PoiType> The POI Type key}
     */
    @Override
    public ResourceKey<PoiType> getPoiType() {
        return MWPoiTypes.ETHEREAL_PORTAL.getKey();
    }

    /**
     * Get the {@link BlockState portal frame block state}
     *
     * @return {@link BlockState The portal frame block state}
     */
    @Override
    public BlockState getPortalFrameState() {
        return MWBlocks.ETHEREAL_PORTAL.get().defaultBlockState();
    }

}