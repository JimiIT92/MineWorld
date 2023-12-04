package org.mineworld.client.event;

import net.minecraft.client.particle.FlameParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.core.MWParticleTypes;

/**
 * Register the {@link MineWorld MineWorld} particles
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ParticleFactoryEventListener {

    /**
     * Register the {@link MineWorld MineWorld} particles
     *
     * @param event {@link RegisterParticleProvidersEvent Register particle providers event}
     */
    @SubscribeEvent
    public static void onParticleFactoryRegister(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(MWParticleTypes.END_FIRE_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(MWParticleTypes.SCULK_FIRE_FLAME.get(), FlameParticle.Provider::new);
    }

}