package org.mineworld.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.HorizontalPaneBlock;

import java.util.Objects;

/**
 * Handle all events for the {@link HorizontalPaneBlock Horizontal Pane Block}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class HorizontalPaneEvents {

    /**
     * Place an {@link HorizontalPaneBlock Horizontal Pane Block} if the {@link Player Player} is sneaking
     *
     * @param event {@link BlockEvent.EntityPlaceEvent The Entity Place Block Event}
     */
    @SubscribeEvent
    public static void onEntityPlace(final BlockEvent.EntityPlaceEvent event) {
        if(!event.isCanceled()) {
            final Entity placer = event.getEntity();
            final Level level = Objects.requireNonNull(placer).level();
            final BlockPos blockPos = event.getPos();
            final BlockState placedBlock = event.getPlacedBlock();
            if(placer instanceof Player player && !event.getPlacedAgainst().isAir()) {
                final BlockState hitBlockState = event.getPlacedAgainst();
                if(HorizontalPaneBlock.hasHorizontalPane(placedBlock) && (player.isShiftKeyDown() || (!hitBlockState.is(placedBlock.getBlock()) && !hitBlockState.isFaceSturdy(level, blockPos, player.getDirection().getOpposite())))) {
                    level.setBlockAndUpdate(blockPos, HorizontalPaneBlock.getStateFromGlassPane(placedBlock, level, blockPos));
                }
            }
        }
    }

}