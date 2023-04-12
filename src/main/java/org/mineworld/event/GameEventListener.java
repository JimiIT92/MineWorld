package org.mineworld.event;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.VanillaGameEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.IMWWeatheringBlock;

/**
 * Listener for a {@link GameEvent game event}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MODID)
public final class GameEventListener {

    /**
     * Listen for a {@link GameEvent vanilla game event} and apply some custom logic
     *
     * @param event {@link VanillaGameEvent The vanilla game event}
     */
    @SubscribeEvent
    public static void onGameEvent(final net.minecraftforge.event.VanillaGameEvent event) {
        final GameEvent gameEvent = event.getVanillaEvent();
        if(gameEvent.equals(GameEvent.LIGHTNING_STRIKE)) {
            final BlockState blockState = event.getCause().getBlockStateOn();
            if(blockState.getBlock() instanceof IMWWeatheringBlock) {
                IMWWeatheringBlock.lightningStrike(blockState, event.getLevel(), event.getCause().getOnPos());
            }
        }
    }
}