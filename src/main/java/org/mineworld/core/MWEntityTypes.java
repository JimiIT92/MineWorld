package org.mineworld.core;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.client.renderer.MWPrimedTntRenderer;
import org.mineworld.entity.MWPrimedTnt;

/**
 * {@link MineWorld MineWorld} {@link EntityType entity types}
 */
public final class MWEntityTypes {

    /**
     * {@link DeferredRegister<EntityType> The entity types registry}
     */
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MineWorld.MODID);

    //#region Entity types

    public static final RegistryObject<EntityType<MWPrimedTnt>> MW_PRIMED_TNT = registerEntityType("tnt",
            EntityType.Builder.<MWPrimedTnt>of(MWPrimedTnt::new, MobCategory.MISC)
                    .fireImmune()
                    .sized(0.98F, 0.98F)
                    .clientTrackingRange(10)
                    .updateInterval(10));

    //#endregion

    /**
     * Register an {@link EntityType entity type}
     *
     * @param name {@link String The entity type name}
     * @param entityBuilder {@link EntityType.Builder The entity type builder}
     * @return {@link RegistryObject<EntityType> The registered entity type}
     */
    private static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(final String name, final EntityType.Builder<T> entityBuilder) {
        return ENTITY_TYPES.register(name, () -> entityBuilder.build(new ResourceLocation(MineWorld.MODID, name).toString()));
    }

    /**
     * Register the entity renderings
     */
    public static void registerRenderers() {
        EntityRenderers.register(MW_PRIMED_TNT.get(), MWPrimedTntRenderer::new);
    }

    /**
     * Register the {@link MineWorld MineWorld} {@link EntityType entity types}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
