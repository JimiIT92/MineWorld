package org.mineworld.core;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;

/**
 * {@link MineWorld MineWorld} {@link SoundEvent sounds}
 */
public final class MWSounds {

    public static final RegistryObject<SoundEvent> MAGIC_MIRROR = RegisterHelper.registerSound("magic_mirror");
    public static final RegistryObject<SoundEvent> INVISIBILITY_CLOAK_EQUIP = RegisterHelper.registerSound("invisibility_cloak_equip");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_BREAK = RegisterHelper.registerSound("sculk_wood_break");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_STEP = RegisterHelper.registerSound("sculk_wood_step");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_PLACE = RegisterHelper.registerSound("sculk_wood_place");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_HIT = RegisterHelper.registerSound("sculk_wood_hit");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_FALL = RegisterHelper.registerSound("sculk_wood_fall");
    public static final RegistryObject<SoundEvent> SCULK_DOOR_CLOSE = RegisterHelper.registerSound("sculk_door_close");
    public static final RegistryObject<SoundEvent> SCULK_DOOR_OPEN = RegisterHelper.registerSound("sculk_door_open");
    public static final RegistryObject<SoundEvent> SCULK_TRAPDOOR_CLOSE = RegisterHelper.registerSound("sculk_trapdoor_close");
    public static final RegistryObject<SoundEvent> SCULK_TRAPDOOR_OPEN = RegisterHelper.registerSound("sculk_trapdoor_open");
    public static final RegistryObject<SoundEvent> SCULK_BUTTON_ON = RegisterHelper.registerSound("sculk_button_on");
    public static final RegistryObject<SoundEvent> SCULK_BUTTON_OFF = RegisterHelper.registerSound("sculk_button_off");
    public static final RegistryObject<SoundEvent> SCULK_PRESSURE_PLATE_ON = RegisterHelper.registerSound("sculk_pressure_plate_on");
    public static final RegistryObject<SoundEvent> SCULK_PRESSURE_PLATE_OFF = RegisterHelper.registerSound("sculk_pressure_plate_off");
    public static final RegistryObject<SoundEvent> SCULK_FENCE_GATE_CLOSE = RegisterHelper.registerSound("sculk_fence_gate_close");
    public static final RegistryObject<SoundEvent> SCULK_FENCE_GATE_OPEN = RegisterHelper.registerSound("sculk_fence_gate_open");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_HANGING_SIGN_BREAK = RegisterHelper.registerSound("sculk_wood_hanging_sign_break");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_HANGING_SIGN_STEP = RegisterHelper.registerSound("sculk_wood_hanging_sign_step");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_HANGING_SIGN_PLACE = RegisterHelper.registerSound("sculk_wood_hanging_sign_place");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_HANGING_SIGN_HIT = RegisterHelper.registerSound("sculk_wood_hanging_sign_hit");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_HANGING_SIGN_FALL = RegisterHelper.registerSound("sculk_wood_hanging_sign_fall");
    public static final RegistryObject<SoundEvent> SCULK_SOIL_BREAK = RegisterHelper.registerSound("sculk_soil_break");
    public static final RegistryObject<SoundEvent> SCULK_SOIL_STEP = RegisterHelper.registerSound("sculk_soil_step");
    public static final RegistryObject<SoundEvent> SCULK_SOIL_PLACE = RegisterHelper.registerSound("sculk_soil_place");
    public static final RegistryObject<SoundEvent> SCULK_SOIL_HIT = RegisterHelper.registerSound("sculk_soil_hit");
    public static final RegistryObject<SoundEvent> SCULK_SOIL_FALL = RegisterHelper.registerSound("sculk_soil_fall");
    public static final RegistryObject<SoundEvent> END_SOIL_BREAK = RegisterHelper.registerSound("end_soil_break");
    public static final RegistryObject<SoundEvent> END_SOIL_STEP = RegisterHelper.registerSound("end_soil_step");
    public static final RegistryObject<SoundEvent> END_SOIL_PLACE = RegisterHelper.registerSound("end_soil_place");
    public static final RegistryObject<SoundEvent> END_SOIL_HIT = RegisterHelper.registerSound("end_soil_hit");
    public static final RegistryObject<SoundEvent> END_SOIL_FALL = RegisterHelper.registerSound("end_soil_fall");
    public static final RegistryObject<SoundEvent> ETHEREAL_PORTAL_OPEN = RegisterHelper.registerSound("ethereal_portal_open");
    public static final RegistryObject<SoundEvent> REAPER_CHARGE = RegisterHelper.registerSound("reaper_charge");
    public static final RegistryObject<SoundEvent> REAPER_HURT = RegisterHelper.registerSound("reaper_hurt");
    public static final RegistryObject<SoundEvent> REAPER_DEATH = RegisterHelper.registerSound("reaper_death");
    public static final RegistryObject<SoundEvent> REAPER_IDLE = RegisterHelper.registerSound("reaper_idle");

    /**
     * Register the {@link MineWorld MineWorld} {@link SoundEvent sounds}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(final IEventBus eventBus) {
        RegisterHelper.registerSounds(eventBus);
    }

}