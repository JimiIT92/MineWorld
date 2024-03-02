package org.mineworld.event;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.MissingMappingsEvent;
import org.mineworld.MineWorld;
import org.mineworld.helper.ResourceHelper;

import java.util.List;

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

    }

    /**
     * Get all {@link MissingMappingsEvent.Mapping<T> Missing  Mappings } for a {@link IForgeRegistry<T> Registry}
     *
     * @param event The {@link MissingMappingsEvent Missing  Mappings  Event}
     * @param registry The {@link IForgeRegistry<T> Registry}
     * @return The {@link MissingMappingsEvent.Mapping<T> Missing Mappings}
     * @param <T> The Missing Mappings Type
     */
    private static <T> List<MissingMappingsEvent.Mapping<T>> getMappings(final MissingMappingsEvent event, final IForgeRegistry<T> registry) {
        return getMappings(event, registry.getRegistryKey());
    }

    /**
     * Get all {@link MissingMappingsEvent.Mapping<T> Missing  Mappings } for a {@link ResourceKey<Registry> Registry Key}
     *
     * @param event The {@link MissingMappingsEvent Missing  Mappings  Event}
     * @param registryKey The {@link ResourceKey<Registry> Registry Key}
     * @return The {@link MissingMappingsEvent.Mapping<T> Missing Mappings}
     * @param <T> The Missing Mappings Type
     */
    private static <T> List<MissingMappingsEvent.Mapping<T>> getMappings(final MissingMappingsEvent event, final ResourceKey<? extends Registry<T>> registryKey) {
        return event.getMappings(registryKey, MineWorld.MOD_ID);
    }

    /**
     * Check if the provided {@link MissingMappingsEvent.Mapping<T> Missing Mapping} corresponds to the provided {@link String entry name}
     *
     * @param mapping The {@link MissingMappingsEvent.Mapping<T> Missing Mapping}
     * @param name The {@link String entry name}
     * @return {@link Boolean True if the Missing Mapping is for the provided entry name}
     * @param <T> The Missing Mapping Type
     */
    private static <T> boolean checkMapping(final MissingMappingsEvent.Mapping<T> mapping, final String name) {
        return ResourceHelper.path(mapping.getKey()).equals(ResourceHelper.lower(name));
    }

}