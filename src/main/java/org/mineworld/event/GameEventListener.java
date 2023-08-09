package org.mineworld.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.VanillaGameEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.weathering.IMWWeatheringBlock;

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

}