package org.mineworld.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.VanillaGameEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.core.MWItems;
import org.mineworld.helper.PlayerHelper;
import org.mineworld.item.InvisibilityCloakItem;

/**
 * Handle all events for the {@link InvisibilityCloakItem Invisibility Cloak}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class InvisibilityCloakEvents {

    /**
     * Make the {@link Player Player} visible when removing the {@link InvisibilityCloakItem Invisibility Cloak}
     *
     * @param event {@link VanillaGameEvent The Vanilla Game Event}
     */
    @SubscribeEvent
    public static void onVanillaGameEvent(final VanillaGameEvent event) {
        if(!event.isCanceled() && event.getVanillaEvent().equals(GameEvent.UNEQUIP) && event.getCause() instanceof ServerPlayer player) {
            if(PlayerHelper.getArmorSlotItem(player, EquipmentSlot.CHEST).is(MWItems.INVISIBILITY_CLOAK.get()) && !player.hasEffect(MobEffects.INVISIBILITY)) {
                player.setInvisible(false);
            }
        }
    }

}