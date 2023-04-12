package org.mineworld.core;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} {@link BlockSetType block set types}
 */
public final class MWBlockSetTypes {

    //#region Block set types

    public static final BlockSetType COPPER = BlockSetType.register(
            new BlockSetType("copper", SoundType.COPPER,
                    SoundEvents.COPPER_FALL,
                    SoundEvents.COPPER_STEP,
                    SoundEvents.COPPER_FALL,
                    SoundEvents.COPPER_STEP,
                    SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
                    SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
                    SoundEvents.STONE_BUTTON_CLICK_OFF,
                    SoundEvents.STONE_BUTTON_CLICK_ON));

    public static final BlockSetType NETHERITE = BlockSetType.register(
            new BlockSetType("netherite", SoundType.NETHERITE_BLOCK,
                    SoundEvents.NETHERITE_BLOCK_FALL,
                    SoundEvents.NETHERITE_BLOCK_STEP,
                    SoundEvents.NETHERITE_BLOCK_FALL,
                    SoundEvents.NETHERITE_BLOCK_STEP,
                    SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
                    SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
                    SoundEvents.STONE_BUTTON_CLICK_OFF,
                    SoundEvents.STONE_BUTTON_CLICK_ON));

    public static final BlockSetType METAL = BlockSetType.register(
            new BlockSetType("metal", SoundType.METAL,
                    SoundEvents.METAL_FALL,
                    SoundEvents.METAL_STEP,
                    SoundEvents.METAL_FALL,
                    SoundEvents.METAL_STEP,
                    SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
                    SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
                    SoundEvents.STONE_BUTTON_CLICK_OFF,
                    SoundEvents.STONE_BUTTON_CLICK_ON));

    //#endregion
}
