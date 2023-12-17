package org.mineworld.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.core.MWEntityTypes;
import org.mineworld.entity.AncientGuardian;
import org.mineworld.entity.Reaper;

/**
 * Register entity attributes
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class EntityAttributesEvent {

    /**
     * Register entity attributes
     *
     * @param event {@link EntityAttributeCreationEvent The entity attribute creation event}
     */
    @SubscribeEvent
    public static void registerAttributes(final EntityAttributeCreationEvent event) {
        event.put(MWEntityTypes.REAPER.get(), Reaper.createAttributes().build());
        event.put(MWEntityTypes.ANCIENT_GUARDIAN.get(), AncientGuardian.createAttributes().build());
    }

}