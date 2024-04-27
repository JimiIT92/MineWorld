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
    public static final RegistryObject<SoundEvent> DARKNESS_CHARGE_SHOOT = registerSound("darkness_charge_shoot");
    public static final RegistryObject<SoundEvent> ANCIENT_GUARDIAN_SUMMON = registerSound("ancient_guardian_summon");
    public static final RegistryObject<SoundEvent> ANCIENT_GUARDIAN_CHARGING = registerSound("ancient_guardian_charging");
    public static final RegistryObject<SoundEvent> ANCIENT_GUARDIAN_HURT = registerSound("ancient_guardian_hurt");
    public static final RegistryObject<SoundEvent> ANCIENT_GUARDIAN_DEATH = registerSound("ancient_guardian_death");
    public static final RegistryObject<SoundEvent> ANCIENT_GUARDIAN_AMBIENT = registerSound("ancient_guardian_ambient");
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_COPPER = registerSound("equip_copper");
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_SILVER = registerSound("equip_silver");
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_BRONZE = registerSound("equip_bronze");
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_ALUMINUM = registerSound("equip_aluminum");
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_EMERALD = registerSound("equip_emerald");
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_RUBY = registerSound("equip_ruby");
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_SAPPHIRE = registerSound("equip_sapphire");
    public static final RegistryObject<SoundEvent> GOAT_HORN_FLY = registerSound("goat_horn_fly");
    public static final RegistryObject<SoundEvent> GOAT_HORN_RESIST = registerSound("goat_horn_resist");
    public static final RegistryObject<SoundEvent> COPPER_HORN_GREAT_SKY_FALLING = registerSound("copper_horn_great_sky_falling");
    public static final RegistryObject<SoundEvent> COPPER_HORN_GREAT_SKY_FALLING_BASS = registerSound("copper_horn_great_sky_falling_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_GREAT_SKY_FALLING_HARMONY = registerSound("copper_horn_great_sky_falling_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_OLD_HYMN_RESTING = registerSound("copper_horn_old_hymn_resting");
    public static final RegistryObject<SoundEvent> COPPER_HORN_OLD_HYMN_RESTING_BASS = registerSound("copper_horn_old_hymn_resting_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_OLD_HYMN_RESTING_HARMONY = registerSound("copper_horn_old_hymn_resting_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_PURE_WATER_DESIRE = registerSound("copper_horn_pure_water_desire");
    public static final RegistryObject<SoundEvent> COPPER_HORN_PURE_WATER_DESIRE_BASS = registerSound("copper_horn_pure_water_desire_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_PURE_WATER_DESIRE_HARMONY = registerSound("copper_horn_pure_water_desire_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_MUMBLE_FIRE_MEMORY = registerSound("copper_horn_mumble_fire_memory");
    public static final RegistryObject<SoundEvent> COPPER_HORN_MUMBLE_FIRE_MEMORY_BASS = registerSound("copper_horn_mumble_fire_memory_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_MUMBLE_FIRE_MEMORY_HARMONY = registerSound("copper_horn_mumble_fire_memory_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_DRY_URGE_ANGER = registerSound("copper_horn_dry_urge_anger");
    public static final RegistryObject<SoundEvent> COPPER_HORN_DRY_URGE_ANGER_BASS = registerSound("copper_horn_dry_urge_anger_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_DRY_URGE_ANGER_HARMONY = registerSound("copper_horn_dry_urge_anger_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_CLEAR_TEMPER_JOURNEY = registerSound("copper_horn_clear_temper_journey");
    public static final RegistryObject<SoundEvent> COPPER_HORN_CLEAR_TEMPER_JOURNEY_BASS = registerSound("copper_horn_clear_temper_journey_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_CLEAR_TEMPER_JOURNEY_HARMONY = registerSound("copper_horn_clear_temper_journey_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_FRESH_NEST_THOUGHT = registerSound("copper_horn_fresh_nest_thought");
    public static final RegistryObject<SoundEvent> COPPER_HORN_FRESH_NEST_THOUGHT_BASS = registerSound("copper_horn_fresh_nest_thought_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_FRESH_NEST_THOUGHT_HARMONY = registerSound("copper_horn_fresh_nest_thought_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_SECRET_LAKE_TEAR = registerSound("copper_horn_secret_lake_tear");
    public static final RegistryObject<SoundEvent> COPPER_HORN_SECRET_LAKE_TEAR_BASS = registerSound("copper_horn_secret_lake_tear_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_SECRET_LAKE_TEAR_HARMONY = registerSound("copper_horn_secret_lake_tear_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_FEARLESS_RIVER_GIFT = registerSound("copper_horn_fearless_river_gift");
    public static final RegistryObject<SoundEvent> COPPER_HORN_FEARLESS_RIVER_GIFT_BASS = registerSound("copper_horn_fearless_river_gift_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_FEARLESS_RIVER_GIFT_HARMONY = registerSound("copper_horn_fearless_river_gift_harmony");
    public static final RegistryObject<SoundEvent> COPPER_HORN_SWEET_MOON_LOVE = registerSound("copper_horn_sweet_moon_love");
    public static final RegistryObject<SoundEvent> COPPER_HORN_SWEET_MOON_LOVE_BASS = registerSound("copper_horn_sweet_moon_love_bass");
    public static final RegistryObject<SoundEvent> COPPER_HORN_SWEET_MOON_LOVE_HARMONY = registerSound("copper_horn_sweet_moon_love_harmony");

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