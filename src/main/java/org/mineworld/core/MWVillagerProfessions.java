package org.mineworld.core;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;

/**
 * {@link MineWorld MineWorld} {@link VillagerProfession villager professions}
 */
public class MWVillagerProfessions {

    public static RegistryObject<VillagerProfession> CARPENTER = RegisterHelper.registerVillagerProfession("carpenter", MWPoiTypes.CARPENTER, SoundEvents.VILLAGER_WORK_MASON);

    /**
     * Register the {@link MineWorld MineWorld} {@link VillagerProfession villager professions}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(IEventBus eventBus) {
        RegisterHelper.registerVillagerProfessions(eventBus);
    }

}