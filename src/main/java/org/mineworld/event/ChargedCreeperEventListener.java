package org.mineworld.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.core.MWItems;
import org.mineworld.helper.ItemHelper;

import java.util.Collection;

/**
 * Handle events for when a charged creeper kills an entity
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class ChargedCreeperEventListener {

    /**
     * Add a custom mob head when the mob is killed by a charged creeper
     *
     * @param event {@link LivingDropsEvent The living drops event}
     */
    @SubscribeEvent
    public static void onLivingDrops(final LivingDropsEvent event) {
        final Entity entity = event.getEntity();
        if(event.getSource().getEntity() instanceof Creeper creeper && creeper.isPowered() && !(entity instanceof Player)) {
            final Collection<ItemEntity> drops = event.getDrops();
            final Vec3 pos = entity.position();
            if(entity instanceof Husk) {
                tryDropSkull(creeper, pos, MWItems.HUSK_HEAD, drops);
            }
            else if(entity instanceof Drowned) {
                tryDropSkull(creeper, pos, MWItems.DROWNED_HEAD, drops);
            }
            else if(entity instanceof Stray) {
                tryDropSkull(creeper, pos, MWItems.STRAY_SKULL, drops);
            }
        }
    }

    /**
     * Try to drop a mob skull
     *
     * @param creeper {@link Creeper The creeper that killed the mob}
     * @param pos {@link Vec3 The entity position}
     * @param skullSupplier {@link RegistryObject The skull supplier}
     * @param drops {@link Collection<ItemEntity> The entity drops}
     */
    private static void tryDropSkull(final Creeper creeper, final Vec3 pos, final RegistryObject<? extends ItemLike> skullSupplier, Collection<ItemEntity> drops) {
        final ItemEntity skull = new ItemEntity(creeper.level, pos.x, pos.y, pos.z, ItemHelper.getDefaultStack(skullSupplier));
        if(!drops.contains(skull) && creeper.canDropMobsSkull()) {
            drops.add(skull);
            creeper.increaseDroppedSkulls();
        }
    }

}