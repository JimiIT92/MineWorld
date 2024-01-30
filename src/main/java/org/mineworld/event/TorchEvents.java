package org.mineworld.event;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.UnlitTorchBlock;

/**
 * Handle all events for {@link TorchBlock Torches}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class TorchEvents {

    /**
     * Extinguish a {@link TorchBlock Torch} when right clicking on it with an empty {@link InteractionHand hand}
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock The Player Right Click Block Event}
     */
    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        if(!event.isCanceled()) {
            final Player player = event.getEntity();
            final ItemStack itemStack = event.getItemStack();
            final Level level = event.getLevel();
            final BlockPos clickedPos = event.getPos();
            final BlockState blockState = level.getBlockState(clickedPos);
            if(itemStack.isEmpty() && blockState.getBlock() instanceof TorchBlock) {
                final Block unlitTorchBlock = UnlitTorchBlock.UNLIT_TORCHES.get().get(blockState.getBlock());
                if(unlitTorchBlock != null) {
                    level.setBlock(clickedPos, unlitTorchBlock.withPropertiesOf(blockState), 2);
                    player.playSound(SoundEvents.CANDLE_EXTINGUISH);
                    player.swing(event.getHand());
                    event.setCanceled(true);
                }
            }
        }
    }

}