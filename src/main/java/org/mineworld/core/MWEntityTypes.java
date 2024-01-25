package org.mineworld.core;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.client.renderer.block.MWPrimedTntRenderer;
import org.mineworld.client.renderer.vehicle.MWBoatRenderer;
import org.mineworld.client.renderer.vehicle.MWChestBoatRenderer;
import org.mineworld.client.renderer.vehicle.MWChestMinecartRenderer;
import org.mineworld.client.renderer.vehicle.MWTntMinecartRenderer;
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.entity.projectile.ThrownPebble;
import org.mineworld.entity.vehicle.MWBoat;
import org.mineworld.entity.vehicle.MWChestBoat;
import org.mineworld.entity.vehicle.MWMinecartChest;
import org.mineworld.entity.vehicle.MWMinecartTnt;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.helper.ResourceHelper;

import java.util.Arrays;

/**
 * {@link MineWorld MineWorld} {@link EntityType Entity Types}
 */
public final class MWEntityTypes {

    //#region Registry

    /**
     * The {@link DeferredRegister<EntityType> EntityTypes Registry}
     */
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = RegistryHelper.registry(ForgeRegistries.ENTITY_TYPES);

    //#endregion

    //#region Entity Types

    public static final RegistryObject<EntityType<MWPrimedTnt>> MW_PRIMED_TNT = registerEntityType("tnt",
            EntityType.Builder.<MWPrimedTnt>of(MWPrimedTnt::new, MobCategory.MISC)
                    .fireImmune()
                    .sized(0.98F, 0.98F)
                    .clientTrackingRange(10)
                    .updateInterval(10)
    );
    public static final RegistryObject<EntityType<ThrownPebble>> PEBBLE = registerEntityType("pebble",
            EntityType.Builder.<ThrownPebble>of(ThrownPebble::new, MobCategory.MISC)
                    .sized(0.15F, 0.15F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
    );
    public static final RegistryObject<EntityType<LeashFenceKnotEntity>> LEASH_KNOT = registerEntityType("leash_knot",
            EntityType.Builder.<LeashFenceKnotEntity>of(LeashFenceKnotEntity::new, MobCategory.MISC)
                    .sized(0.375F, 0.5F)
                    .clientTrackingRange(10)
                    .updateInterval(Integer.MAX_VALUE)
    );
    public static final RegistryObject<EntityType<MWMinecartTnt>> TNT_MINECART = registerEntityType("tnt_minecart",
            EntityType.Builder.<MWMinecartTnt>of(MWMinecartTnt::new, MobCategory.MISC)
                    .sized(0.98F, 0.7F)
                    .clientTrackingRange(8)
    );
    public static final RegistryObject<EntityType<MWMinecartChest>> CHEST_MINECART = registerEntityType("chest_minecart",
            EntityType.Builder.<MWMinecartChest>of(MWMinecartChest::new, MobCategory.MISC)
                    .sized(0.98F, 0.7F)
                    .clientTrackingRange(8)
    );
    public static final RegistryObject<EntityType<MWBoat>> BOAT = registerEntityType("boat",
            EntityType.Builder.<MWBoat>of(MWBoat::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F)
                    .clientTrackingRange(10)
    );
    public static final RegistryObject<EntityType<MWBoat>> CHEST_BOAT = registerEntityType("chest_boat",
            EntityType.Builder.<MWBoat>of(MWChestBoat::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F)
                    .clientTrackingRange(10)
    );

    //#endregion

    //#region Methods

    /**
     * Register an {@link EntityType Entity Type}
     *
     * @param name {@link String The Entity Type name}
     * @param entityTypeBuilder {@link EntityType.Builder The Entity Type Builder}
     * @return {@link RegistryObject<EntityType> The registered Entity Type}
     * @param <T> {@link T The Entity type}
     */
    private static  <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(final String name, final EntityType.Builder<T> entityTypeBuilder) {
        return ENTITY_TYPES.register(name, () -> entityTypeBuilder.build(ResourceHelper.stringLocation(name)));
    }

    /**
     * Register an {@link EntityRendererProvider Entity Renderer} for an {@link EntityType Entities}
     *
     * @param rendererProvider {@link EntityRendererProvider The Entity Renderer}
     * @param entity {@link Entity The Entity}
     * @param <T> {@link T The Entity type}
     */
    private static <T extends Entity> void registerRenderer(final RegistryObject<EntityType<T>> entity, final EntityRendererProvider<T> rendererProvider) {
        EntityRenderers.register(entity.get(), rendererProvider);
    }

    /**
     * Register an {@link EntityRendererProvider Entity Renderer} for some {@link EntityType Entities}
     *
     * @param rendererProvider {@link EntityRendererProvider The Entity Renderer}
     * @param entities {@link Entity The Entities}
     * @param <T> {@link T The Entity type}
     */
    @SafeVarargs
    private static <T extends Entity> void registerRenderer(final EntityRendererProvider<T> rendererProvider, final RegistryObject<EntityType<T>>... entities) {
        Arrays.stream(entities).forEach(entity -> registerRenderer(entity, rendererProvider));
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link EntityType Entity Types}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    /**
     * Register all {@link EntityRenderer Entity Renderers}
     */
    public static void registerRenderers() {
        registerRenderer(MW_PRIMED_TNT, MWPrimedTntRenderer::new);
        registerRenderer(LEASH_KNOT, LeashKnotRenderer::new);
        registerRenderer(TNT_MINECART, MWTntMinecartRenderer::new);
        registerRenderer(CHEST_MINECART, MWChestMinecartRenderer::new);
        registerRenderer(BOAT, MWBoatRenderer::new);
        registerRenderer(CHEST_BOAT, MWChestBoatRenderer::new);
        registerRenderer(ThrownItemRenderer::new,
                PEBBLE
        );
    }

    //#endregion

}