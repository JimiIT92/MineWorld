package org.mineworld.core;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.client.renderer.MWPrimedTntRenderer;
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.helper.RegisterHelper;

/**
 * {@link MineWorld MineWorld} {@link EntityType entity types}
 */
public final class MWEntityTypes {

    public static final RegistryObject<EntityType<MWPrimedTnt>> MW_PRIMED_TNT = RegisterHelper.registerEntityType("tnt",
            EntityType.Builder.<MWPrimedTnt>of(MWPrimedTnt::new, MobCategory.MISC)
                    .fireImmune()
                    .sized(0.98F, 0.98F)
                    .clientTrackingRange(10)
                    .updateInterval(10));

    /**
     * Register the entity renderings
     */
    public static void registerRenderers() {
        EntityRenderers.register(MW_PRIMED_TNT.get(), MWPrimedTntRenderer::new);
    }

    /**
     * Register the {@link MineWorld MineWorld} {@link EntityType entity types}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(final IEventBus eventBus) {
        RegisterHelper.registerEntityTypes(eventBus);
    }

}