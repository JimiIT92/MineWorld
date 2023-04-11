package org.mineworld.event;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.IMWWeatheringBlock;
import org.mineworld.core.MWBlocks;

import java.util.Optional;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = MineWorld.MODID)
public final class HoneycombWaxEvent {

    private static final Supplier<BiMap<Block, Block>> WAXABLES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(MWBlocks.COPPER_STAIRS.get(), MWBlocks.WAXED_COPPER_STAIRS.get())
            .put(MWBlocks.EXPOSED_COPPER_STAIRS.get(), MWBlocks.WAXED_EXPOSED_COPPER_STAIRS.get())
            .put(MWBlocks.WEATHERED_COPPER_STAIRS.get(), MWBlocks.WAXED_WEATHERED_COPPER_STAIRS.get())
            .put(MWBlocks.OXIDIZED_COPPER_STAIRS.get(), MWBlocks.WAXED_OXIDIZED_COPPER_STAIRS.get())
    .build());

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
            if(blockState.getBlock() instanceof IMWWeatheringBlock) {
                event.setCancellationResult(IMWWeatheringBlock.applyWax(blockState, itemStack, event.getEntity(), blockPos, event.getHand(), level));
            }
        }
    }

    /**
     * Get the {@link BlockState waxed block state}
     * based on the {@link BlockState current block state}
     *
     * @param state {@link BlockState The current block state}
     * @return {@link BlockState The waxed block state}
     */
    private static Optional<BlockState> getWaxed(final BlockState state) {
        return Optional.ofNullable(WAXABLES.get().get(state.getBlock())).map(block -> block.withPropertiesOf(state));
    }
}