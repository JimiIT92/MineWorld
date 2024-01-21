package org.mineworld.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.world.structure.MWJigsawStructure;

/**
 * {@link MineWorld MineWorld} {@link StructureType Structure Types}
 */
public final class MWStructureTypes {

    //#region Registry

    /**
     * The {@link DeferredRegister<StructureType> Structure Types Registry}
     */
    private static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, MineWorld.MOD_ID);

    //#endregion

    //#region Structure Types

    public static final RegistryObject<StructureType<MWJigsawStructure>> ICE_CASTLE = registerStructureType("ice_castle");
    public static final RegistryObject<StructureType<MWJigsawStructure>> ETHEREAL_RUIN = registerStructureType("ethereal_ruin");
    public static final RegistryObject<StructureType<MWJigsawStructure>> RUINED_ETHEREAL_PORTAL = registerStructureType("ruined_ethereal_portal");
    public static final RegistryObject<StructureType<MWJigsawStructure>> ANCIENT_TEMPLE = registerStructureType("ancient_temple");

    //#endregion

    //#region Methods

    /**
     * Register a {@link StructureType Structure Type}
     *
     * @param name {@link String The Structure Type name}
     * @return {@link RegistryObject<StructureType> The registered Structure Type}
     */
    private static RegistryObject<StructureType<MWJigsawStructure>> registerStructureType(final String name) {
        return STRUCTURE_TYPES.register(name, () -> () -> MWJigsawStructure.CODEC);
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link StructureType Structure Types}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        STRUCTURE_TYPES.register(eventBus);
    }

    //#endregion

}