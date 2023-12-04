package org.mineworld.core;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;

/**
 * {@link MineWorld MineWorld} {@link BlockSetType block set types}
 */
public final class MWBlockSetTypes {

    public static final BlockSetType COPPER = RegisterHelper.registerBlockSetType("copper", false, SoundType.COPPER,
            SoundEvents.COPPER_FALL, SoundEvents.COPPER_STEP,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON);
    public static final BlockSetType NETHERITE = RegisterHelper.registerBlockSetType("netherite", false, SoundType.NETHERITE_BLOCK,
            SoundEvents.NETHERITE_BLOCK_FALL, SoundEvents.NETHERITE_BLOCK_STEP,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON);
    public static final BlockSetType METAL = RegisterHelper.registerBlockSetType("metal", false, SoundType.METAL,
            SoundEvents.METAL_FALL, SoundEvents.METAL_STEP,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON);
    public static final BlockSetType SCULK = RegisterHelper.registerBlockSetType("sculk", true, MWSoundTypes.SCULK_WOOD,
            MWSounds.SCULK_DOOR_CLOSE.get(), MWSounds.SCULK_DOOR_OPEN.get(),
            MWSounds.SCULK_CLICK_OFF.get(), MWSounds.SCULK_CLICK_ON.get(),
            MWSounds.SCULK_CLICK_OFF.get(), MWSounds.SCULK_CLICK_ON.get());

}