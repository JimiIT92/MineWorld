package org.mineworld.event;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.MissingMappingsEvent;
import org.mineworld.MineWorld;
import org.mineworld.helper.ResourceHelper;

/**
 * Handle events for missing mappings
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class MissingMappingEvents {

    /**
     * Handle the missing mappings and replace them with a valid one
     *
     * @param event {@link MissingMappingsEvent The Missing Mappings Event}
     */
    @SubscribeEvent
    public static void onMissingMapping(final MissingMappingsEvent event) {
        if(event.getKey().location().equals(ResourceHelper.resourceLocation("volcanic_peak"))) {
            System.out.println("MISSING");
        }
    }
}