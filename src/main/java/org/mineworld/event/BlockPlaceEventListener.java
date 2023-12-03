package org.mineworld.event;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.block.HorizontalPaneBlock;
import org.mineworld.block.MWPointedDripstoneBlock;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWTags;
import org.mineworld.helper.ItemHelper;

import java.util.Objects;

/**
 * Place an {@link HorizontalPaneBlock horizontal pane block} if the placed block has a variant
 * and the {@link Player player} is sneaking or is placing the block against a non-solid block
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class BlockPlaceEventListener {

    /**
     * Place an {@link HorizontalPaneBlock horizontal pane block} if the placed block has a variant
     * and the {@link Player player} is sneaking or is placing the block against a non-solid block
     *
     * @param event {@link BlockEvent.EntityPlaceEvent Entity place block event}
     */
    @SubscribeEvent
    public static void onBlockPlaced(final BlockEvent.EntityPlaceEvent event) {
        if(!event.isCanceled()) {
            final Entity placer = event.getEntity();
            final Level level = Objects.requireNonNull(placer).level();
            final BlockPos blockPos = event.getPos();
            final BlockState placedBlock = event.getPlacedBlock();
            if(placer instanceof Player player && !event.getPlacedAgainst().isAir()) {
                final BlockState hitBlockState = event.getPlacedAgainst();
                if(shouldPlaceHorizontalPane(player, placedBlock, hitBlockState, level, blockPos)) {
                    level.setBlockAndUpdate(blockPos, HorizontalPaneBlock.getStateFromGlassPane(placedBlock, level, blockPos));
                }
                else if(placedBlock.is(Blocks.POINTED_DRIPSTONE) && !player.isShiftKeyDown()) {
                    level.setBlockAndUpdate(blockPos, MWPointedDripstoneBlock.getDripstoneFor(hitBlockState.getBlock()).withPropertiesOf(placedBlock));
                }
                else if(placedBlock.getBlock() instanceof BaseFireBlock && player.getMainHandItem().getItem() instanceof FlintAndSteelItem) {
                    handleFireBlockPlacement(event, player, level, blockPos, MWTags.Blocks.END_FIRE_BASE_BLOCKS, MWBlocks.END_FIRE);
                    handleFireBlockPlacement(event, player, level, blockPos, MWTags.Blocks.SCULK_FIRE_BASE_BLOCKS, MWBlocks.SCULK_FIRE);
                }
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

    /**
     * Check if a special kind of {@link BaseFireBlock fire} should be placed
     *
     * @param event {@link BlockEvent.EntityPlaceEvent Entity place block event}
     * @param player {@link Player The player placing the block}
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current BlockPos}
     * @param baseFireBlocksTag {@link TagKey<Block> The fire base blocks tag}
     * @param fireBlock {@link RegistryObject<Block> The fire block to place}
     */
    private static void handleFireBlockPlacement(final BlockEvent.EntityPlaceEvent event, final Player player, final Level level, final BlockPos pos, final TagKey<Block> baseFireBlocksTag, final RegistryObject<Block> fireBlock) {
        if(level.getBlockState(pos.below()).is(baseFireBlocksTag)) {
            //event.setCanceled(true);
            level.setBlockAndUpdate(pos, fireBlock.get().defaultBlockState());
            ItemHelper.hurt(player.getMainHandItem());
        }
    }

}