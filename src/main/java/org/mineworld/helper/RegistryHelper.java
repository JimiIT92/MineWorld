package org.mineworld.helper;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;

import java.util.function.Supplier;

/**
 * Helper class for {@link DeferredRegister Deferred Registries}
 */
public final class RegistryHelper {

    /**
     * Create a {@link DeferredRegister<T> Deferred Register}
     *
     * @param registryType {@link IForgeRegistry<T> The registry type}
     * @return {@link DeferredRegister<T> The Deferred Register}
     * @param <T> {@link T The Deferred Register Type}
     */
    public static <T> DeferredRegister<T> registry(final IForgeRegistry<T> registryType) {
        return DeferredRegister.create(registryType, MineWorld.MOD_ID);
    }

    /**
     * Register an {@link T object} to the {@link DeferredRegister<T> Registry}
     *
     * @param registry {@link DeferredRegister<T> The object Registry}
     * @param name {@link String The object name}
     * @param objectSupplier {@link Supplier<T> The object supplier}
     * @return {@link RegistryObject<T> The registered object}
     * @param <T> {@link T The object type}
     */
    public static <T> RegistryObject<T> register(final DeferredRegister<T> registry, final String name, final Supplier<? extends T> objectSupplier) {
        return registry.register(name, objectSupplier);
    }

    /**
     * Register the {@link DeferredRegister Registry}
     * @param registry {@link DeferredRegister The Registry}
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final DeferredRegister<?> registry, final IEventBus eventBus) {
        registry.register(eventBus);
    }

}