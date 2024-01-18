package org.mineworld.core;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
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

    public static final RegistryObject<StructureType<MWJigsawStructure>> ICE_CASTLE = registerStructureType("ice_castle", MWJigsawStructure.CODEC);
    public static final RegistryObject<StructureType<MWJigsawStructure>> ETHEREAL_RUIN = registerStructureType("ethereal_ruin", MWJigsawStructure.CODEC);
    public static final RegistryObject<StructureType<MWJigsawStructure>> RUINED_ETHEREAL_PORTAL = registerStructureType("ruined_ethereal_portal", MWJigsawStructure.CODEC);
    public static final RegistryObject<StructureType<MWJigsawStructure>> ANCIENT_TEMPLE = registerStructureType("ancient_temple", MWJigsawStructure.CODEC);

    //#endregion

    //#region Methods

    /**
     * Register a {@link StructureType Structure Type}
     *
     * @param name {@link String The Structure Type name}
     * @param codec {@link Codec<T> The Structure Type Codec}
     * @return {@link RegistryObject<StructureType> The registered Structure Type}
     * @param <T> {@link T The Structure Type}
     */
    private static <T extends Structure> RegistryObject<StructureType<T>> registerStructureType(final String name, final Codec<T> codec) {
        return STRUCTURE_TYPES.register(name, () -> () -> codec);
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