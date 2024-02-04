package org.mineworld.event;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.core.MWEntityTypes;
import org.mineworld.entity.Reaper;

/**
 * Handle all events for {@link Attribute Entity Attributes}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class EntityAttributesEvents {

    /**
     * Register {@link MineWorld MineWorld} {@link Attribute Entity Attributes}
     *
     * @param event {@link EntityAttributeCreationEvent The Entity Attribute Creation Event}
     */
    @SubscribeEvent
    public static void onEntityAttributeCreation(final EntityAttributeCreationEvent event) {
        registerAttribute(event, MWEntityTypes.REAPER.get(), Reaper.createAttributes());
    }

    /**
     * Register an {@link Attribute Entity Attribute}
     *
     * @param event {@link EntityAttributeCreationEvent The Entity Attribute Creation Event}
     * @param entityType {@link EntityType The Entity Type}
     * @param attributeBuilder {@link AttributeSupplier.Builder The Entity Attribute Builder}
     */
    private static void registerAttribute(final EntityAttributeCreationEvent event, EntityType<? extends Mob> entityType, final AttributeSupplier.Builder attributeBuilder) {
        event.put(entityType, attributeBuilder.build());
    }

}