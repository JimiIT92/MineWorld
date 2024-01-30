package org.mineworld.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RodBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.helper.ItemHelper;

import java.util.Objects;
import java.util.Optional;

/**
 * Handle all events for a {@link RodBlock Rod Block}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class RodBlockEvents {

    /**
     * Place a {@link RodBlock Rod Block} when the {@link Player Player} is sneaking
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
            Optional<Block> optionalRodBlock = Optional.empty();
            if(itemStack.is(Items.BONE)) {
                optionalRodBlock = Optional.of(MWBlocks.BONE_ROD_BLOCK.get());
            }
            else if(itemStack.is(Items.STICK)) {
                optionalRodBlock = Optional.of(MWBlocks.STICK_ROD_BLOCK.get());
            }
            else if(itemStack.is(Items.BLAZE_ROD)) {
                optionalRodBlock = Optional.of(MWBlocks.BLAZE_ROD_BLOCK.get());
            }
            optionalRodBlock.ifPresent(rodBlock -> {
                if(player.isShiftKeyDown()) {
                    final Direction face = event.getFace();
                    final BlockPos rodPos = clickedPos.offset(Objects.requireNonNull(event.getFace()).getNormal());
                    if(level.getBlockState(rodPos).isAir()) {
                        final BlockState rodState = rodBlock.defaultBlockState().setValue(RodBlock.FACING, Objects.requireNonNull(face).getOpposite());
                        level.setBlock(rodPos, rodState, 2);
                        player.playSound(rodBlock.getSoundType(rodState, level, rodPos, player).getPlaceSound());
                        event.setCanceled(true);
                        ItemHelper.hurt(event.getItemStack(), player, level, event.getHand());
                    }
                }
            });
        }
    }

}