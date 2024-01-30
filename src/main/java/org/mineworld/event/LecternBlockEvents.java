package org.mineworld.event;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.MWLecternBlock;

/**
 * Handle all events for {@link MineWorld MineWorld} {@link MWLecternBlock Lectern Blocks}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class LecternBlockEvents {

    /**
     * Try placing a {@link BookItem Book} when right-clicking a {@link LecternBlock Lectern}
     *
     * @param event {@link PlayerInteractEvent.EntityInteractSpecific.RightClickBlock The Player Right Click Block Event}
     */
    @SubscribeEvent
    public static void onLecternRightClick(final PlayerInteractEvent.EntityInteractSpecific.RightClickBlock event) {
        final Level level = event.getLevel();
        final BlockPos clickedPos = event.getPos();
        final ItemStack itemStack = event.getItemStack();
        final BlockState clickedBlockState = level.getBlockState(clickedPos);
        if(!event.isCanceled() && clickedBlockState.getBlock() instanceof MWLecternBlock && itemStack.is(ItemTags.LECTERN_BOOKS)) {
            event.setCanceled(true);
            final boolean bookPlaced = LecternBlock.tryPlaceBook(event.getEntity(), level, clickedPos, clickedBlockState, itemStack);
            event.setCancellationResult(bookPlaced ? InteractionResult.sidedSuccess(level.isClientSide()) : InteractionResult.PASS);
        }
    }

}