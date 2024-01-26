package org.mineworld.core;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegistryHelper;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link VillagerProfession Villager Professions}
 */
public final class MWVillagerProfessions {

    //#region Registry

    /**
     * The {@link DeferredRegister<VillagerProfession> Villager Profession Registry}
     */
    private static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = RegistryHelper.registry(ForgeRegistries.VILLAGER_PROFESSIONS);

    //#endregion

    //#region Poi Types

    public static final RegistryObject<VillagerProfession> CARPENTER = registerVillagerProfession("carpenter", MWPoiTypes.CARPENTER, () -> SoundEvents.VILLAGER_WORK_MASON);

    //#endregion

    //#region Methods

    /**
     * Register a {@link VillagerProfession Villager Profession}
     *
     * @param name {@link String The Villager Profession name}
     * @param poiTypeSupplier {@link Supplier<PoiType> The Supplier for the POI Type this Villager Profession is referring to}
     * @param soundSupplier {@link Supplier<SoundEvent> The Supplier for the Sound Event used by this Villager Profession when it starts working}
     * @return {@link RegistryObject<VillagerProfession> The registered Villager Profession}
     */
    private static RegistryObject<VillagerProfession> registerVillagerProfession(final String name, final Supplier<PoiType> poiTypeSupplier, final Supplier<SoundEvent> soundSupplier) {
        return VILLAGER_PROFESSIONS.register(name, Suppliers.memoize(() -> new VillagerProfession(
                        name,
                        x -> x.get().equals(poiTypeSupplier.get()),
                        x -> x.get().equals(poiTypeSupplier.get()),
                        ImmutableSet.of(),
                        ImmutableSet.of(),
                        soundSupplier.get()
                ))
        );
    }

    //#endregion

    //#region Bus Register

    /**
     * Register all {@link VillagerProfession Villager Professions}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        VILLAGER_PROFESSIONS.register(eventBus);
    }

    //#endregion

}