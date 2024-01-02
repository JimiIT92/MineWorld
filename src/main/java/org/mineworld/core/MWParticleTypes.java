package org.mineworld.core;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegistryHelper;

/**
 * {@link MineWorld MineWorld} {@link ParticleType particle types}
 */
public final class MWParticleTypes {

    //#region Registry

    /**
     * The {@link DeferredRegister<ParticleType> Particle Types Registry}
     */
    private static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = RegistryHelper.registry(ForgeRegistries.PARTICLE_TYPES);

    //#endregion

    //#region Particles

    public static final RegistryObject<SimpleParticleType> END_FIRE_FLAME = registerSimpleParticle("end_fire_flame");
    public static final RegistryObject<SimpleParticleType> SCULK_FIRE_FLAME = registerSimpleParticle("sculk_fire_flame");

    //#endregion

    //#region Methods

    /**
     * Register a {@link SimpleParticleType Simple Particle}
     *
     * @param name {@link String The particle name}
     * @return {@link RegistryObject<SimpleParticleType> The registered particle}
     */
    private static RegistryObject<SimpleParticleType> registerSimpleParticle(final String name) {
        return PARTICLE_TYPES.register(name, () -> new SimpleParticleType(false));
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link ParticleType particles}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }

    //#endregion

}