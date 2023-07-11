package org.mineworld.core;

import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;

/**
 * {@link MineWorld MineWorld} {@link PoiType villager poi type}
 */
public class MWPoiTypes {

    public static RegistryObject<PoiType> CARPENTER = RegisterHelper.registerPOIType("carpenter", MWBlocks.WOODCUTTER);

    /**
     * Register the {@link MineWorld MineWorld} {@link PoiType villager poi types}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(IEventBus eventBus) {
        RegisterHelper.registerPOITypes(eventBus);
    }

}