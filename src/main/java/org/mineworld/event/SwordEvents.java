package org.mineworld.event;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.FireAspectEnchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
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
            if(isFireAspectSword(itemStack)) {
                final Player player = event.getEntity();
                final Level level = event.getLevel();
                final BlockPos clickedPos = event.getPos();
                final BlockState blockState = level.getBlockState(clickedPos);
                final Block block = blockState.getBlock();
                final InteractionHand hand = event.getHand();
                if(block instanceof CampfireBlock) {
                    litBlock(level, itemStack, player, hand, clickedPos, blockState, CampfireBlock.canLight(blockState));
                    return;
                }
                if(block instanceof CandleBlock) {
                    litBlock(level, itemStack, player, hand, clickedPos, blockState, player.isShiftKeyDown() && CandleBlock.canLight(blockState));
                    return;
                }
                if(block instanceof CandleCakeBlock) {
                    litBlock(level, itemStack, player, hand, clickedPos, blockState, player.isShiftKeyDown() && CandleCakeBlock.canLight(blockState));
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

    /**
     * Lit a {@link Block Block} if is not already lit
     *
     * @param level {@link Level The level reference}
     * @param itemStack {@link ItemStack The held Item Stack}
     * @param player {@link Player The Player interacting with the Block}
     * @param hand {@link InteractionHand The Hand the Player is interacting with}
     * @param clickedPos {@link BlockPos The clicked BlockPos}
     * @param blockState {@link BlockState The clicked BlockState}
     */
    private static void litBlock(final Level level, final ItemStack itemStack, final Player player, final InteractionHand hand, final BlockPos clickedPos, final BlockState blockState, final Boolean litCondition) {
        if(litCondition && !blockState.getValue(BlockStateProperties.LIT)) {
            if(!level.isClientSide()) {
                level.setBlock(clickedPos, blockState.setValue(BlockStateProperties.LIT, Boolean.TRUE), 11);
                level.gameEvent(player, GameEvent.BLOCK_CHANGE, clickedPos);
                ItemHelper.hurt(itemStack, player);
            } else {
                player.swing(hand);
                level.playSound(player, clickedPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
            }
        }
    }

    /**
     * Check if an {@link ItemStack Item Stack} is a {@link SwordItem Sword} enchanted with {@link FireAspectEnchantment Fire Aspect}
     *
     * @param itemStack {@link ItemStack The Item Stack to check}
     * @return {@link Boolean True if is a Fire Aspect enchanted Sword}
     */
    public static boolean isFireAspectSword(final ItemStack itemStack) {
        return itemStack.getItem() instanceof SwordItem && itemStack.isEnchanted() && itemStack.getEnchantmentLevel(Enchantments.FIRE_ASPECT) > 0;
    }

}