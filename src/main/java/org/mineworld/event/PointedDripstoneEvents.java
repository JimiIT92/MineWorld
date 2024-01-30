package org.mineworld.event;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.IcePointedDripstoneBlock;
import org.mineworld.block.MWPointedDripstoneBlock;

import java.util.Objects;

/**
 * Handle all events for {@link PointedDripstoneBlock Pointed Dripstones}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class PointedDripstoneEvents {

    /**
     * Play the {@link SoundEvents#GLASS_BREAK Glass Break sound} when an {@link IcePointedDripstoneBlock Ice Pointed Dripstone} lands
     *
     * @param event {@link EntityLeaveLevelEvent The Entity Leave Level Event}
     */
    @SubscribeEvent
    public static void onEntityLeaveLevel(final EntityLeaveLevelEvent event) {
        if(!event.isCanceled() && event.getEntity() instanceof FallingBlockEntity entity && entity.getBlockState().getBlock() instanceof IcePointedDripstoneBlock) {
            event.getLevel().playSound(null, entity.blockPosition(), SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }

    /**
     * Place a {@link MineWorld MineWorld} {@link MWPointedDripstoneBlock Pointed Dripstone} if the {@link Player Player} is not sneaking
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
                if(placedBlock.is(Blocks.POINTED_DRIPSTONE) && !player.isShiftKeyDown()) {
                    level.setBlockAndUpdate(blockPos, MWPointedDripstoneBlock.getDripstoneFor(hitBlockState.getBlock()).withPropertiesOf(placedBlock));
                }
            }
        }
    }

}