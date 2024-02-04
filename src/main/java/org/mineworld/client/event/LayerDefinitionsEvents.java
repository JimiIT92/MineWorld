package org.mineworld.client.event;

import com.google.common.base.Suppliers;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.client.model.StrawHatModel;
import org.mineworld.client.model.WitchHatModel;
import org.mineworld.client.model.entity.ReaperModel;

/**
 * Handle all events for {@link MineWorld MineWorld} {@link LayerDefinition Layer Definitions}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class LayerDefinitionsEvents {

    /**
     * Register all {@link MineWorld MineWorld} {@link LayerDefinition Layer Definitions}
     *
     * @param event {@link EntityRenderersEvent.RegisterLayerDefinitions The Register Layers Definition Event}
     */
    @SubscribeEvent
    public static void onRegisterLayers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WitchHatModel.LAYER_LOCATION, Suppliers.memoize(() -> WitchHatModel.createBodyLayer()));
        event.registerLayerDefinition(StrawHatModel.LAYER_LOCATION, Suppliers.memoize(() -> StrawHatModel.createBodyLayer()));
        event.registerLayerDefinition(ReaperModel.LAYER_LOCATION, Suppliers.memoize(() -> ReaperModel.createBodyLayer()));
    }

}