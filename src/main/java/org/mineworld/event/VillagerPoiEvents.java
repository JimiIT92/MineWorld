package org.mineworld.event;

import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.mineworld.MineWorld;
import org.mineworld.core.MWPoiTypes;

/**
 * Handle the Villager {@link PoiType POI types} register
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class VillagerPoiEvents {

    /**
     * Override vanilla {@link PoiType Villager POI Types}
     *
     * @param event {@link RegisterEvent The Register Event}
     */
    @SubscribeEvent
    public static void onRegister(final RegisterEvent event) {
        event.register(ForgeRegistries.Keys.POI_TYPES, helper -> {
            helper.register(PoiTypes.FISHERMAN, MWPoiTypes.FISHERMAN.get());
            helper.register(PoiTypes.LIBRARIAN, MWPoiTypes.LIBRARIAN.get());
            helper.register(PoiTypes.CARTOGRAPHER, MWPoiTypes.CARTOGRAPHER.get());
            helper.register(PoiTypes.FLETCHER, MWPoiTypes.FLETCHER.get());
            helper.register(PoiTypes.TOOLSMITH, MWPoiTypes.TOOLSMITH.get());
            helper.register(PoiTypes.SHEPHERD, MWPoiTypes.SHEPHERD.get());
            helper.register(PoiTypes.FARMER, MWPoiTypes.FARMER.get());
            helper.register(PoiTypes.BEEHIVE, MWPoiTypes.BEEHIVE.get());
        });
    }

}