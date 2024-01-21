package org.mineworld.core;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.mineworld.MineWorld;
import org.mineworld.helper.ResourceHelper;

/**
 * {@link MineWorld MineWorld} {@link BlockSetType Block Set Types}
 */
public final class MWBlockSetTypes {

    //#region Block Set Types

    public static final Supplier<BlockSetType> COPPER = Suppliers.memoize(() -> registerBlockSetType("copper", false,
            SoundType.COPPER,
            SoundEvents.COPPER_FALL,
            SoundEvents.COPPER_STEP,
            SoundEvents.COPPER_FALL,
            SoundEvents.COPPER_STEP,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON)
    );
    public static final Supplier<BlockSetType> NETHERITE = Suppliers.memoize(() -> registerBlockSetType("netherite", false,
            SoundType.NETHERITE_BLOCK,
            SoundEvents.NETHERITE_BLOCK_FALL,
            SoundEvents.NETHERITE_BLOCK_STEP,
            SoundEvents.NETHERITE_BLOCK_FALL,
            SoundEvents.NETHERITE_BLOCK_STEP,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON)
    );
    public static final Supplier<BlockSetType> METAL = Suppliers.memoize(() -> registerBlockSetType("metal", false,
            SoundType.METAL,
            SoundEvents.METAL_FALL,
            SoundEvents.METAL_STEP,
            SoundEvents.METAL_FALL,
            SoundEvents.METAL_STEP,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON)
    );
    public static final Supplier<BlockSetType> ICE = Suppliers.memoize(() -> registerBlockSetType(MWWoodTypes.MWWoodTypeNames.ICE, false,
            SoundType.GLASS,
            SoundEvents.GLASS_FALL,
            SoundEvents.GLASS_STEP,
            SoundEvents.GLASS_FALL,
            SoundEvents.GLASS_STEP,
            SoundEvents.GLASS_HIT,
            SoundEvents.GLASS_PLACE,
            SoundEvents.GLASS_HIT,
            SoundEvents.GLASS_PLACE)
    );
    public static final Supplier<BlockSetType> SCULK = Suppliers.memoize(() -> registerBlockSetType(MWWoodTypes.MWWoodTypeNames.SCULK, true,
            MWSoundTypes.SCULK_WOOD,
            MWSounds.SCULK_DOOR_CLOSE.get(),
            MWSounds.SCULK_DOOR_OPEN.get(),
            MWSounds.SCULK_TRAPDOOR_CLOSE.get(),
            MWSounds.SCULK_TRAPDOOR_OPEN.get(),
            MWSounds.SCULK_PRESSURE_PLATE_OFF.get(),
            MWSounds.SCULK_PRESSURE_PLATE_ON.get(),
            MWSounds.SCULK_BUTTON_ON.get(),
            MWSounds.SCULK_BUTTON_OFF.get())
    );

    //#endregion

    //#region Methods

    /**
     * Register a {@link BlockSetType Block Set Type}
     *
     * @param name {@link String The Block Set Type name}
     * @param canOpenByHand {@link Boolean If the block set Doors/Trapdoors can be open using hands}
     * @param defaultSound {@link SoundType The Block Set Type default sound}
     * @param doorCloseSound {@link SoundEvent The sound to play when a Door is closed}
     * @param doorOpenSound {@link SoundEvent The sound to play when a Door is opened}
     * @param trapdoorCloseSound {@link SoundEvent The sound to play when a Trapdoor is closed}
     * @param trapdoorOpenSound {@link SoundEvent The sound to play when a Trapdoor is closed}
     * @param pressurePlateClickOffSound {@link SoundEvent The sound to play when a Pressure Plate looses pressure}
     * @param pressurePlateClickOnSound {@link SoundEvent The sound to play when a Pressure Plate is pressed}
     * @param buttonClickOffSound {@link SoundEvent The sound to play when a Button looses pressure}
     * @param buttonClickOnSound {@link SoundEvent The sound to play when a Button is pressed}
     * @return {@link BlockSetType The registered Block Set Type}
     */
    public static BlockSetType registerBlockSetType(final String name, final boolean canOpenByHand, final SoundType defaultSound, final SoundEvent doorCloseSound, final SoundEvent doorOpenSound, final SoundEvent trapdoorCloseSound, final SoundEvent trapdoorOpenSound, final SoundEvent pressurePlateClickOffSound, final SoundEvent pressurePlateClickOnSound, final SoundEvent buttonClickOffSound, final SoundEvent buttonClickOnSound) {
        return BlockSetType.register(new BlockSetType(ResourceHelper.stringLocation(name),
                canOpenByHand,
                defaultSound,
                doorCloseSound,
                doorOpenSound,
                trapdoorCloseSound,
                trapdoorOpenSound,
                pressurePlateClickOffSound,
                pressurePlateClickOnSound,
                buttonClickOffSound,
                buttonClickOnSound
                )
        );
    }

    //#endregion

}