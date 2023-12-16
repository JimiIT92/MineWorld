package org.mineworld.client.event;

import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.client.model.ReaperModel;
import org.mineworld.client.model.StrawHatModel;
import org.mineworld.client.model.WitchHatModel;

/**
 * Register the {@link MineWorld MineWorld} {@link LayerDefinition Layer Definitions}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class LayerDefinitionsEvent {

    /**
     * Register the {@link LayerDefinition Layer Definitions}
     *
     * @param event {@link EntityRenderersEvent.RegisterLayerDefinitions The register layer definitions event}
     */
    @SubscribeEvent
    public static void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WitchHatModel.LAYER_LOCATION, () -> WitchHatModel.createBodyLayer());
        event.registerLayerDefinition(StrawHatModel.LAYER_LOCATION, () -> StrawHatModel.createBodyLayer());
        event.registerLayerDefinition(ReaperModel.LAYER_LOCATION, () -> ReaperModel.createBodyLayer());
    }

}