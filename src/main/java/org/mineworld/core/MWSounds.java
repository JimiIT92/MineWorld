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
    public static final RegistryObject<SoundEvent> SCULK_CLICK_ON = RegisterHelper.registerSound("sculk_click_on");
    public static final RegistryObject<SoundEvent> SCULK_CLICK_OFF = RegisterHelper.registerSound("sculk_click_off");

    /**
     * Register the {@link MineWorld MineWorld} {@link SoundEvent sounds}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(final IEventBus eventBus) {
        RegisterHelper.registerSounds(eventBus);
    }

}