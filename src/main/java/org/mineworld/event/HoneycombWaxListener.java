package org.mineworld.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.weathering.IMWWaxableBlock;

/**
 * Apply wax to a {@link MineWorld MineWorld} {@link ChangeOverTimeBlock oxidizable block}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MODID)
public final class HoneycombWaxListener {

    /**
     * Wax a {@link Block block} if is waxable
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock Player right click block event}
     */
    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        ItemStack itemStack = event.getItemStack();
        if(itemStack.getItem() instanceof HoneycombItem honeyComb) {
            final Level level = event.getLevel();
            final BlockPos blockPos = event.getPos();
            final BlockState blockState = level.getBlockState(blockPos);
            if(blockState.getBlock() instanceof IMWWaxableBlock) {
                event.setCancellationResult(IMWWaxableBlock.applyWax(blockState, itemStack, event.getEntity(), blockPos, event.getHand(), level));
            }
        }
    }

}