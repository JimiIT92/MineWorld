package org.mineworld.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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
            final Level level = entity.level;
            final Vec3 pos = entity.position();
            if(entity instanceof Husk) {
                final ItemEntity huskHead = new ItemEntity(level, pos.x, pos.y, pos.z, ItemHelper.getDefaultStack(MWItems.HUSK_HEAD));
                if(!drops.contains(huskHead)) {
                    drops.add(huskHead);
                }
            }
            else if(entity instanceof Drowned) {
                final ItemEntity drownedHead = new ItemEntity(level, pos.x, pos.y, pos.z, ItemHelper.getDefaultStack(MWItems.DROWNED_HEAD));
                if(!drops.contains(drownedHead)) {
                    drops.add(drownedHead);
                }
            }
            else if(entity instanceof Stray) {
                final ItemEntity straySkull = new ItemEntity(level, pos.x, pos.y, pos.z, ItemHelper.getDefaultStack(MWItems.STRAY_SKULL));
                if(!drops.contains(straySkull)) {
                    drops.add(straySkull);
                }
            }
        }
    }

}