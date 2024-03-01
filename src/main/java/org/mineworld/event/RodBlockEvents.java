package org.mineworld.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RodBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.MWRodBlock;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.LevelHelper;

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
            final Level level = event.getLevel();
            final BlockPos clickedPos = event.getPos();
            final Block rodBlock = MWRodBlock.getRodBlockFor(event.getItemStack().getItem());
            if(rodBlock != null) {
                if(player.isShiftKeyDown()) {
                    final Direction direction = event.getFace();
                    getRodPos(level, clickedPos, direction).ifPresent(rodPos -> {
                        final BlockState rodState = rodBlock.defaultBlockState().setValue(RodBlock.FACING, Objects.requireNonNull(direction).getOpposite());
                        level.setBlock(rodPos, rodState, 2);
                        player.playSound(rodBlock.getSoundType(rodState, level, rodPos, player).getPlaceSound());
                        event.setCanceled(true);
                        ItemHelper.hurt(event.getItemStack(), player, level, event.getHand());
                    });
                }
            }
        }
    }

    /**
     * Get the {@link BlockPos Block Pos} for a {@link MWRodBlock Rod Block}
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction to check}
     * @return The {@link MWRodBlock Rod Block} placing {@link BlockPos Block Pos}
     */
    private static Optional<BlockPos> getRodPos(final Level level, final BlockPos blockPos, final Direction direction) {
        return Optional.ofNullable(LevelHelper.getReplacingBlockPos(level, blockPos, direction).orElseGet(() -> {
            final BlockPos offsetPos = LevelHelper.offset(blockPos, direction);
            final BlockState clickedBlockState = level.getBlockState(blockPos);
            if (clickedBlockState.getBlock() instanceof MWRodBlock rodBlock && clickedBlockState.getValue(RodBlock.FACING).equals(direction.getOpposite())) {
                return LevelHelper.getReplacingBlockPos(level, offsetPos, direction).orElse(null);
            }
            return null;
        }));
    }

}