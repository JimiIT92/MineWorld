package org.mineworld.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.VanillaGameEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.weathering.IMWWeatheringBlock;

import java.util.Objects;

/**
 * Handle all events for the {@link LightningBolt Lightning Bolt}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class LightningBoltEvents {

    /**
     * Oxidize some {@link MineWorld MineWorld} {@link IMWWeatheringBlock Copper Blocks} when struck by a {@link LightningBolt Lightning Bolt}
     *
     * @param event {@link VanillaGameEvent The Vanilla Game Event}
     */
    @SubscribeEvent
    public static void onVanillaGameEvent(final VanillaGameEvent event) {
        if(!event.isCanceled() && event.getVanillaEvent().equals(GameEvent.LIGHTNING_STRIKE)) {
            final Entity cause = event.getCause();
            final BlockState blockState = Objects.requireNonNull(cause).getBlockStateOn();
            if(blockState.getBlock() instanceof IMWWeatheringBlock) {
                IMWWeatheringBlock.lightningStrike(blockState, event.getLevel(), cause.getOnPos());
            }
        }
    }

}