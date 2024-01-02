package org.mineworld.core;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.entity.projectile.ThrownPebble;
import org.mineworld.entity.vehicle.MWMinecartTnt;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.helper.ResourceHelper;

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
                    .updateInterval(10));
    public static final RegistryObject<EntityType<ThrownPebble>> PEBBLE = registerEntityType("pebble",
            EntityType.Builder.<ThrownPebble>of(ThrownPebble::new, MobCategory.MISC)
                    .sized(0.15F, 0.15F)
                    .clientTrackingRange(4)
                    .updateInterval(10));
    public static final RegistryObject<EntityType<LeashFenceKnotEntity>> LEASH_KNOT = registerEntityType("leash_knot",
            EntityType.Builder.<LeashFenceKnotEntity>of(LeashFenceKnotEntity::new, MobCategory.MISC)
                    .sized(0.375F, 0.5F)
                    .clientTrackingRange(10)
                    .updateInterval(Integer.MAX_VALUE));
    public static final RegistryObject<EntityType<MWMinecartTnt>> TNT_MINECART = registerEntityType("tnt_minecart",
            EntityType.Builder.<MWMinecartTnt>of(MWMinecartTnt::new, MobCategory.MISC)
                    .sized(0.98F, 0.7F)
                    .clientTrackingRange(8));

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

    //#endregion

}