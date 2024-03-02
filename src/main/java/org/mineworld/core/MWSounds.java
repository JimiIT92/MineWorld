package org.mineworld.core;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.helper.ResourceHelper;

/**
 * {@link MineWorld MineWorld} {@link SoundEvent Sounds}
 */
public final class MWSounds {

    //#region Registry

    /**
     * The {@link DeferredRegister<SoundEvent> Sounds Registry}
     */
    private static final DeferredRegister<SoundEvent> SOUNDS = RegistryHelper.registry(ForgeRegistries.SOUND_EVENTS);

    //#endregion

    //#region Sounds

    public static final RegistryObject<SoundEvent> MAGIC_MIRROR = registerSound("magic_mirror");
    public static final RegistryObject<SoundEvent> INVISIBILITY_CLOAK_EQUIP = registerSound("invisibility_cloak_equip");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_BREAK = registerSound("sculk_wood_break");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_STEP = registerSound("sculk_wood_step");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_PLACE = registerSound("sculk_wood_place");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_HIT = registerSound("sculk_wood_hit");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_FALL = registerSound("sculk_wood_fall");
    public static final RegistryObject<SoundEvent> SCULK_DOOR_CLOSE = registerSound("sculk_door_close");
    public static final RegistryObject<SoundEvent> SCULK_DOOR_OPEN = registerSound("sculk_door_open");
    public static final RegistryObject<SoundEvent> SCULK_TRAPDOOR_CLOSE = registerSound("sculk_trapdoor_close");
    public static final RegistryObject<SoundEvent> SCULK_TRAPDOOR_OPEN = registerSound("sculk_trapdoor_open");
    public static final RegistryObject<SoundEvent> SCULK_BUTTON_ON = registerSound("sculk_button_on");
    public static final RegistryObject<SoundEvent> SCULK_BUTTON_OFF = registerSound("sculk_button_off");
    public static final RegistryObject<SoundEvent> SCULK_PRESSURE_PLATE_ON = registerSound("sculk_pressure_plate_on");
    public static final RegistryObject<SoundEvent> SCULK_PRESSURE_PLATE_OFF = registerSound("sculk_pressure_plate_off");
    public static final RegistryObject<SoundEvent> SCULK_FENCE_GATE_CLOSE = registerSound("sculk_fence_gate_close");
    public static final RegistryObject<SoundEvent> SCULK_FENCE_GATE_OPEN = registerSound("sculk_fence_gate_open");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_HANGING_SIGN_BREAK = registerSound("sculk_wood_hanging_sign_break");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_HANGING_SIGN_STEP = registerSound("sculk_wood_hanging_sign_step");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_HANGING_SIGN_PLACE = registerSound("sculk_wood_hanging_sign_place");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_HANGING_SIGN_HIT = registerSound("sculk_wood_hanging_sign_hit");
    public static final RegistryObject<SoundEvent> SCULK_WOOD_HANGING_SIGN_FALL = registerSound("sculk_wood_hanging_sign_fall");
    public static final RegistryObject<SoundEvent> SCULK_SOIL_BREAK = registerSound("sculk_soil_break");
    public static final RegistryObject<SoundEvent> SCULK_SOIL_STEP = registerSound("sculk_soil_step");
    public static final RegistryObject<SoundEvent> SCULK_SOIL_PLACE = registerSound("sculk_soil_place");
    public static final RegistryObject<SoundEvent> SCULK_SOIL_HIT = registerSound("sculk_soil_hit");
    public static final RegistryObject<SoundEvent> SCULK_SOIL_FALL = registerSound("sculk_soil_fall");
    public static final RegistryObject<SoundEvent> END_SOIL_BREAK = registerSound("end_soil_break");
    public static final RegistryObject<SoundEvent> END_SOIL_STEP = registerSound("end_soil_step");
    public static final RegistryObject<SoundEvent> END_SOIL_PLACE = registerSound("end_soil_place");
    public static final RegistryObject<SoundEvent> END_SOIL_HIT = registerSound("end_soil_hit");
    public static final RegistryObject<SoundEvent> END_SOIL_FALL = registerSound("end_soil_fall");
    public static final RegistryObject<SoundEvent> ETHEREAL_PORTAL_OPEN = registerSound("ethereal_portal_open");
    public static final RegistryObject<SoundEvent> REAPER_CHARGE = registerSound("reaper_charge");
    public static final RegistryObject<SoundEvent> REAPER_HURT = registerSound("reaper_hurt");
    public static final RegistryObject<SoundEvent> REAPER_DEATH = registerSound("reaper_death");
    public static final RegistryObject<SoundEvent> REAPER_IDLE = registerSound("reaper_idle");
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_COPPER = registerSound("equip_copper");
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_SILVER = registerSound("equip_silver");
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_BRONZE = registerSound("equip_bronze");
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_ALUMINUM = registerSound("equip_aluminum");
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_EMERALD = registerSound("equip_emerald");
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_RUBY = registerSound("equip_ruby");
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_SAPPHIRE = registerSound("equip_sapphire");

    //#endregion

    //#region Methods

    /**
     * Register a {@link SoundEvent Sound}
     *
     * @param name {@link String The sound name}
     * @return {@link RegistryObject<SoundEvent> The registered sound}
     */
    private static RegistryObject<SoundEvent> registerSound(final String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceHelper.resourceLocation(name)));
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link SoundEvent sounds}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }

    //#endregion

}