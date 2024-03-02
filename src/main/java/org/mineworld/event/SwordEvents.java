package org.mineworld.event;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.FireAspectEnchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.helper.ItemHelper;

/**
 * Handle all events for a {@link SwordItem Sword}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class SwordEvents {

    /**
     * Lit a {@link CampfireBlock Campfire Block} or ignite a {@link TntBlock Tnt Block}
     * when right clicking on it with a {@link FireAspectEnchantment Fire Aspect Sword}
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock The Player Right Click Block Event}
     */
    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        if(!event.isCanceled()) {
            final ItemStack itemStack = event.getItemStack();
            if(itemStack.getItem() instanceof SwordItem && itemStack.isEnchanted() && itemStack.getEnchantmentLevel(Enchantments.FIRE_ASPECT) > 0) {
                final Player player = event.getEntity();
                final Level level = event.getLevel();
                final BlockPos clickedPos = event.getPos();
                final BlockState blockState = level.getBlockState(clickedPos);
                final Block block = blockState.getBlock();
                if(block instanceof CampfireBlock && !blockState.getValue(CampfireBlock.LIT)) {
                    if(!level.isClientSide()) {
                        level.setBlockAndUpdate(clickedPos, blockState.setValue(CampfireBlock.LIT, true));
                        ItemHelper.hurt(itemStack, player);
                    } else {
                        player.swing(event.getHand());
                        player.playSound(SoundEvents.FLINTANDSTEEL_USE);
                    }
                    return;
                }
                if(block instanceof TntBlock) {
                    if(!level.isClientSide()) {
                        level.setBlockAndUpdate(clickedPos, Blocks.AIR.defaultBlockState());
                        block.onCaughtFire(blockState, level, clickedPos, event.getFace(), player);
                        ItemHelper.hurt(itemStack, player);
                    } else {
                        player.swing(event.getHand());
                        player.playSound(SoundEvents.FLINTANDSTEEL_USE);
                    }
                }
            }
        }
    }

}