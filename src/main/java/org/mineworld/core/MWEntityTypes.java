package org.mineworld.core;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.LeashKnotRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.client.renderer.MWPrimedTntRenderer;
import org.mineworld.client.renderer.vehicle.MWBoatRenderer;
import org.mineworld.client.renderer.vehicle.MWChestBoatRenderer;
import org.mineworld.client.renderer.vehicle.MWChestMinecartRenderer;
import org.mineworld.client.renderer.vehicle.MWTntMinecartRenderer;
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.entity.ThrownGrenade;
import org.mineworld.entity.ThrownPebble;
import org.mineworld.entity.vehicle.MWBoat;
import org.mineworld.entity.vehicle.MWChestBoat;
import org.mineworld.entity.vehicle.MWMinecartChest;
import org.mineworld.entity.vehicle.MWMinecartTNT;
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
    public static final RegistryObject<EntityType<ThrownPebble>> PEBBLE = RegisterHelper.registerEntityType("pebble",
            EntityType.Builder.<ThrownPebble>of(ThrownPebble::new, MobCategory.MISC)
                    .sized(0.15F, 0.15F)
                    .clientTrackingRange(4)
                    .updateInterval(10));
    public static final RegistryObject<EntityType<LeashFenceKnotEntity>> LEASH_KNOT = RegisterHelper.registerEntityType("leash_knot",
            EntityType.Builder.<LeashFenceKnotEntity>of(LeashFenceKnotEntity::new, MobCategory.MISC)
                    .sized(0.375F, 0.5F)
                    .clientTrackingRange(10)
                    .updateInterval(Integer.MAX_VALUE));
    public static final RegistryObject<EntityType<MWMinecartTNT>> TNT_MINECART = RegisterHelper.registerEntityType("tnt_minecart",
            EntityType.Builder.<MWMinecartTNT>of(MWMinecartTNT::new, MobCategory.MISC)
                    .sized(0.98F, 0.7F)
                    .clientTrackingRange(8));
    public static final RegistryObject<EntityType<MWMinecartChest>> CHEST_MINECART = RegisterHelper.registerEntityType("chest_minecart",
            EntityType.Builder.<MWMinecartChest>of(MWMinecartChest::new, MobCategory.MISC)
                    .sized(0.98F, 0.7F)
                    .clientTrackingRange(8));
    public static final RegistryObject<EntityType<MWBoat>> BOAT = RegisterHelper.registerEntityType("boat",
            EntityType.Builder.<MWBoat>of(MWBoat::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F)
                    .clientTrackingRange(10));
    public static final RegistryObject<EntityType<MWChestBoat>> CHEST_BOAT = RegisterHelper.registerEntityType("chest_boat",
            EntityType.Builder.<MWChestBoat>of(MWChestBoat::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F)
                    .clientTrackingRange(10));

    public static final RegistryObject<EntityType<ThrownGrenade>> GRENADE = RegisterHelper.registerEntityType("grenade",
            EntityType.Builder.<ThrownGrenade>of(ThrownGrenade::new, MobCategory.MISC)
                    .sized(0.15F, 0.15F)
                    .clientTrackingRange(4)
                    .updateInterval(10));

    /**
     * Register the entity renderings
     */
    public static void registerRenderers() {
        EntityRenderers.register(MW_PRIMED_TNT.get(), MWPrimedTntRenderer::new);
        EntityRenderers.register(PEBBLE.get(), ThrownItemRenderer::new);
        EntityRenderers.register(LEASH_KNOT.get(), LeashKnotRenderer::new);
        EntityRenderers.register(TNT_MINECART.get(), MWTntMinecartRenderer::new);
        EntityRenderers.register(CHEST_MINECART.get(), MWChestMinecartRenderer::new);
        EntityRenderers.register(BOAT.get(), MWBoatRenderer::new);
        EntityRenderers.register(CHEST_BOAT.get(), MWChestBoatRenderer::new);
        EntityRenderers.register(GRENADE.get(), ThrownItemRenderer::new);
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