package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.mineworld.MineWorld;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link WoodType Wood Types}
 */
public final class MWWoodTypes {

    //#region Wood Types

    public static final Supplier<WoodType> APPLE = Suppliers.memoize(() -> registerWoodType(MWWoodTypeNames.APPLE));
    public static final Supplier<WoodType> PALM = Suppliers.memoize(() -> registerWoodType(MWWoodTypeNames.PALM,
            BlockSetType.BAMBOO,
            SoundType.BAMBOO_WOOD,
            SoundType.BAMBOO_WOOD_HANGING_SIGN,
            SoundEvents.BAMBOO_WOOD_FENCE_GATE_CLOSE,
            SoundEvents.BAMBOO_WOOD_FENCE_GATE_OPEN
    ));
    public static final Supplier<WoodType> DEAD = Suppliers.memoize(() -> registerWoodType(MWWoodTypeNames.DEAD));
    public static final Supplier<WoodType> SCULK = Suppliers.memoize(() -> registerWoodType(MWWoodTypeNames.SCULK,
            MWBlockSetTypes.SCULK.get(),
            MWSoundTypes.SCULK_WOOD,
            MWSoundTypes.SCULK_WOOD_HANGING_SIGN,
            MWSounds.SCULK_FENCE_GATE_CLOSE.get(),
            MWSounds.SCULK_FENCE_GATE_OPEN.get()
    ));
    public static final Supplier<WoodType> ICE = Suppliers.memoize(() -> registerWoodType(MWWoodTypeNames.ICE,
            MWBlockSetTypes.ICE.get(),
            SoundType.GLASS,
            SoundType.HANGING_SIGN,
            SoundEvents.GLASS_HIT,
            SoundEvents.GLASS_PLACE
    ));

    //#endregion

    //#region Methods

    /**
     * Register a {@link WoodType Wood Type}
     *
     * @param name {@link String The Wood Type name}
     * @return {@link WoodType The registered Wood Type}
     */
    private static WoodType registerWoodType(final String name) {
        return registerWoodType(name, BlockSetType.OAK, SoundType.WOOD, SoundType.HANGING_SIGN, SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN);
    }

    /**
     * Register a {@link WoodType Wood Type}
     *
     * @param name {@link String The Wood Type name}
     * @param blockSetType {@link BlockSetType The Wood sounds}
     * @param sound {@link SoundType The Block sound}
     * @param hangingSignSound {@link SoundType The Hanging Sign sound}
     * @param fenceGateCloseSound {@link SoundType The Fence Gate close sound}
     * @param fenceGateOpenSound {@link SoundType The Fence Gate open sound}
     * @return {@link WoodType The registered Wood Type}
     */
    private static WoodType registerWoodType(final String name, final BlockSetType blockSetType, final SoundType sound, final SoundType hangingSignSound, final SoundEvent fenceGateCloseSound, final SoundEvent fenceGateOpenSound) {
        return WoodType.register(new WoodType(MineWorld.MOD_ID + ":" + name, blockSetType, sound, hangingSignSound, fenceGateCloseSound, fenceGateOpenSound));
    }

    //#endregion

    //#region Names

    /**
     * The {@link MineWorld MineWorld} {@link WoodType Wood Type} {@link String names}
     */
    public static class MWWoodTypeNames {
        public static final String APPLE = "apple";
        public static final String PALM = "palm";
        public static final String DEAD = "dead";
        public static final String SCULK = "sculk";
        public static final String ICE = "ice";
    }

    //#endregion

}