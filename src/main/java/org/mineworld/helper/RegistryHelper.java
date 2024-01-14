package org.mineworld.helper;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
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
        return registry(registryType.getRegistryKey());
    }

    /**
     * Create a {@link DeferredRegister<T> Deferred Register}
     *
     * @param registryKey {@link ResourceKey The registry resource key}
     * @return {@link DeferredRegister<T> The Deferred Register}
     * @param <T> {@link T The Deferred Register Type}
     */
    public static <T> DeferredRegister<T> registry(final ResourceKey<? extends Registry<T>> registryKey) {
        return DeferredRegister.create(registryKey, MineWorld.MOD_ID);
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
     * Register a {@link ResourceKey Resource Key}
     *
     * @param registry {@link Registry<T> The Resource Key Registry}
     * @param name {@link String The Resource Key Name}
     * @return {@link ResourceKey<T> The Resource Key to register}
     * @param <T> The Resource Key Type
     */
    public static <T> ResourceKey<T> register(final ResourceKey<Registry<T>> registry, final String name) {
        return ResourceKey.create(registry, ResourceHelper.resourceLocation(name));
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