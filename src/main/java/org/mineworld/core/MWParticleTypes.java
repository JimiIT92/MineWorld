package org.mineworld.core;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;

/**
 * {@link MineWorld MineWorld} {@link ParticleType particle types}
 */
public final class MWParticleTypes {

    public static final RegistryObject<SimpleParticleType> END_FIRE_FLAME = RegisterHelper.registerParticleType("end_fire_flame");
    public static final RegistryObject<SimpleParticleType> SCULK_FIRE_FLAME = RegisterHelper.registerParticleType("sculk_fire_flame");

    /**
     * Register the {@link MineWorld MineWorld} {@link ParticleType particle types}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(final IEventBus eventBus) {
        RegisterHelper.registerParticleTypes(eventBus);
    }

}