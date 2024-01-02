package org.mineworld.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} client initializer
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class MineWorldClient {

    /**
     * Set up the {@link MineWorld MineWorld} client stuffs, like entity renderers
     *
     * @param event {@link FMLClientSetupEvent FML client setup event}
     */
    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {

    }

}