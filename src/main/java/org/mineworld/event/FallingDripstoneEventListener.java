package org.mineworld.event;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.IcePointedDripstoneBlock;

/**
 * Play the break sound when the dripstone falls and break
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class FallingDripstoneEventListener {

    /**
     * Play the break sound when the {@link FallingBlockEntity falling dripstone} lands
     *
     * @param event {@link EntityLeaveLevelEvent The entity leave level event}
     */
    @SubscribeEvent
    public static void onFallingIcreDripstone(final EntityLeaveLevelEvent event) {
        if(event.getEntity() instanceof FallingBlockEntity entity && entity.getBlockState().getBlock() instanceof IcePointedDripstoneBlock) {
            event.getLevel().playSound(null, entity.blockPosition(), SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }

}