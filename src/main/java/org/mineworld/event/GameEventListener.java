package org.mineworld.event;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.VanillaGameEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.weathering.IMWWeatheringBlock;
import org.mineworld.core.MWItems;
import org.mineworld.helper.PlayerHelper;
import org.mineworld.item.InvisibilityCloakItem;

import java.util.Objects;

/**
 * Listener for a {@link GameEvent game event}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class GameEventListener {

    /**
     * Listen for a {@link GameEvent vanilla game event} and apply some custom logic
     *
     * @param event {@link VanillaGameEvent The vanilla game event}
     */
    @SubscribeEvent
    public static void onGameEvent(final VanillaGameEvent event) {
        if(!event.isCanceled()) {
            final GameEvent gameEvent = event.getVanillaEvent();
            final Level level = event.getLevel();
            final Entity cause = event.getCause();
            if(gameEvent.equals(GameEvent.LIGHTNING_STRIKE)) {
                handleLightningStrike(Objects.requireNonNull(cause).getBlockStateOn(), level, cause.getOnPos());
                return;
            }
            if(gameEvent.equals(GameEvent.UNEQUIP) && cause instanceof ServerPlayer player) {
                handleInvisibilityCloakUnequip(player);
            }
        }
    }

    /**
     * Handle a {@link LightningBolt lightning strike} hitting a {@link Block block},
     * for example to make it deoxidize
     *
     * @param blockState {@link BlockState The lightning strike hitted block state}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The lightning strike hitted block pos}
     */
    private static void handleLightningStrike(final BlockState blockState, final Level level, final BlockPos blockPos) {
        if(blockState.getBlock() instanceof IMWWeatheringBlock) {
            IMWWeatheringBlock.lightningStrike(blockState, level, blockPos);
        }
    }

    /**
     * Handle the unequip of an {@link InvisibilityCloakItem invisiblity cloak}, making the {@link Player player}
     * visible again
     *
     * @param player {@link Player The player unequipping the cloak}
     */
    private static void handleInvisibilityCloakUnequip(final Player player) {
        if(PlayerHelper.getArmorSlotItem(player, EquipmentSlot.CHEST).is(MWItems.INVISIBILITY_CLOAK.get()) && !player.hasEffect(MobEffects.INVISIBILITY)) {
            player.setInvisible(false);
        }
    }

}