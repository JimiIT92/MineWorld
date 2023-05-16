package org.mineworld.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.HorizontalPaneBlock;

/**
 * Place an {@link HorizontalPaneBlock horizontal pane block} if the placed block has a variant
 * and the {@link Player player} is sneaking or is placing the block against a non solid block
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class BlockPlaceEventListener {

    /**
     * Place an {@link HorizontalPaneBlock horizontal pane block} if the placed block has a variant
     * and the {@link Player player} is sneaking or is placing the block against a non solid block
     *
     * @param event {@link BlockEvent.EntityPlaceEvent Entity place block event}
     */
    @SubscribeEvent
    public static void onBlockPlaced(final BlockEvent.EntityPlaceEvent event) {
        if(!event.isCanceled()) {
            final Entity placer = event.getEntity();
            final Level level = placer.getLevel();
            final BlockPos blockPos = event.getPos();
            final BlockState placedBlock = event.getPlacedBlock();
            if(placer instanceof Player player && !event.getPlacedAgainst().isAir() && shouldPlaceHorizontalPane(player, placedBlock, event.getPlacedAgainst(), level, blockPos)) {
                level.setBlockAndUpdate(blockPos, HorizontalPaneBlock.getStateFromGlassPane(placedBlock, level, blockPos));
            }
        }
    }

    /**
     * Check if the {@link Player player} should place an {@link HorizontalPaneBlock horizontal pane}
     *
     * @param player {@link Player The player placing the block}
     * @param blockState {@link BlockState The placed block state}
     * @param neighborBlockState {@link BlockState The block state against the block has been placed}
     * @param level {@link LevelAccessor The level accessor reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link Boolean True if the placed block has an horizontal pane and the player is shifting or is placing the block against a non solid block}
     */
    private static boolean shouldPlaceHorizontalPane(final Player player, final BlockState blockState, final BlockState neighborBlockState, final LevelAccessor level, final BlockPos blockPos) {
        return HorizontalPaneBlock.hasHorizontalPane(blockState) &&
                (player.isShiftKeyDown() || (!neighborBlockState.is(blockState.getBlock()) && !neighborBlockState.isFaceSturdy(level, blockPos, player.getDirection().getOpposite())));
    }

}