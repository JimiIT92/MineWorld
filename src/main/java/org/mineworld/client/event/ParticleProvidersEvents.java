package org.mineworld.client.event;

import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.core.MWParticleTypes;

import java.util.Arrays;

/**
 * Handle all events for {@link MineWorld MineWorld} {@link ParticleProvider Particle Providers}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ParticleProvidersEvents {

    /**
     * Register all {@link MineWorld MineWorld} {@link ParticleProvider Particle Providers}
     *
     * @param event {@link RegisterParticleProvidersEvent The Register Particle Providers Event}
     */
    @SubscribeEvent
    public static void onRegisterLayers(final RegisterParticleProvidersEvent event) {
        registerFlameParticles(event,
                MWParticleTypes.END_FIRE_FLAME.get(),
                MWParticleTypes.SCULK_FIRE_FLAME.get()
        );
    }

    /**
     * Register some {@link SimpleParticleType Flame particles}
     *
     * @param event {@link RegisterParticleProvidersEvent The Register Particle Providers Event}
     * @param particleTypes {@link ParticleType<SimpleParticleType> The Particles to register}
     */
    @SafeVarargs
    private static void registerFlameParticles(final RegisterParticleProvidersEvent event, final ParticleType<SimpleParticleType>... particleTypes) {
        Arrays.stream(particleTypes).forEach(particleType -> event.registerSpriteSet(particleType, FlameParticle.Provider::new));
    }

}