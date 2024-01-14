package org.mineworld.block.weathering;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.helper.ItemHelper;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} interface for an {@link ChangeOverTimeBlock waxable Block}
 */
public interface IMWWaxableBlock extends IMWChangeOverTimeBlock {

    /**
     * {@link Supplier<BiMap> Unwaxed Blocks by Block}
     */
    Supplier<BiMap<Block, Block>> UNWAXED_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            /*.put(MWBlocks.WAXED_COPPER_STAIRS.get(), MWBlocks.COPPER_STAIRS.get())
            .put(MWBlocks.WAXED_EXPOSED_COPPER_STAIRS.get(), MWBlocks.EXPOSED_COPPER_STAIRS.get())
            .put(MWBlocks.WAXED_WEATHERED_COPPER_STAIRS.get(), MWBlocks.WEATHERED_COPPER_STAIRS.get())
            .put(MWBlocks.WAXED_OXIDIZED_COPPER_STAIRS.get(), MWBlocks.OXIDIZED_COPPER_STAIRS.get())
            .put(MWBlocks.WAXED_COPPER_SLAB.get(), MWBlocks.COPPER_SLAB.get())
            .put(MWBlocks.WAXED_EXPOSED_COPPER_SLAB.get(), MWBlocks.EXPOSED_COPPER_SLAB.get())
            .put(MWBlocks.WAXED_WEATHERED_COPPER_SLAB.get(), MWBlocks.WEATHERED_COPPER_SLAB.get())
            .put(MWBlocks.WAXED_OXIDIZED_COPPER_SLAB.get(), MWBlocks.OXIDIZED_COPPER_SLAB.get())
            .put(MWBlocks.WAXED_COPPER_PRESSURE_PLATE.get(), MWBlocks.COPPER_PRESSURE_PLATE.get())
            .put(MWBlocks.WAXED_EXPOSED_COPPER_PRESSURE_PLATE.get(), MWBlocks.EXPOSED_COPPER_PRESSURE_PLATE.get())
            .put(MWBlocks.WAXED_WEATHERED_COPPER_PRESSURE_PLATE.get(), MWBlocks.WEATHERED_COPPER_PRESSURE_PLATE.get())
            .put(MWBlocks.WAXED_OXIDIZED_COPPER_PRESSURE_PLATE.get(), MWBlocks.OXIDIZED_COPPER_PRESSURE_PLATE.get())
            .put(MWBlocks.WAXED_CUT_COPPER_PRESSURE_PLATE.get(), MWBlocks.CUT_COPPER_PRESSURE_PLATE.get())
            .put(MWBlocks.WAXED_EXPOSED_CUT_COPPER_PRESSURE_PLATE.get(), MWBlocks.EXPOSED_CUT_COPPER_PRESSURE_PLATE.get())
            .put(MWBlocks.WAXED_WEATHERED_CUT_COPPER_PRESSURE_PLATE.get(), MWBlocks.WEATHERED_CUT_COPPER_PRESSURE_PLATE.get())
            .put(MWBlocks.WAXED_OXIDIZED_CUT_COPPER_PRESSURE_PLATE.get(), MWBlocks.OXIDIZED_CUT_COPPER_PRESSURE_PLATE.get())
            .put(MWBlocks.WAXED_COPPER_TRAPDOOR.get(), MWBlocks.COPPER_TRAPDOOR.get())
            .put(MWBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR.get(), MWBlocks.EXPOSED_COPPER_TRAPDOOR.get())
            .put(MWBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR.get(), MWBlocks.WEATHERED_COPPER_TRAPDOOR.get())
            .put(MWBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR.get(), MWBlocks.OXIDIZED_COPPER_TRAPDOOR.get())
            .put(MWBlocks.WAXED_COPPER_CHAIN.get(), MWBlocks.COPPER_CHAIN.get())
            .put(MWBlocks.WAXED_EXPOSED_COPPER_CHAIN.get(), MWBlocks.EXPOSED_COPPER_CHAIN.get())
            .put(MWBlocks.WAXED_WEATHERED_COPPER_CHAIN.get(), MWBlocks.WEATHERED_COPPER_CHAIN.get())
            .put(MWBlocks.WAXED_OXIDIZED_COPPER_CHAIN.get(), MWBlocks.OXIDIZED_COPPER_CHAIN.get())
            .put(MWBlocks.WAXED_COPPER_LANTERN.get(), MWBlocks.COPPER_LANTERN.get())
            .put(MWBlocks.WAXED_EXPOSED_COPPER_LANTERN.get(), MWBlocks.EXPOSED_COPPER_LANTERN.get())
            .put(MWBlocks.WAXED_WEATHERED_COPPER_LANTERN.get(), MWBlocks.WEATHERED_COPPER_LANTERN.get())
            .put(MWBlocks.WAXED_OXIDIZED_COPPER_LANTERN.get(), MWBlocks.OXIDIZED_COPPER_LANTERN.get())
            .put(MWBlocks.WAXED_COPPER_SOUL_LANTERN.get(), MWBlocks.COPPER_SOUL_LANTERN.get())
            .put(MWBlocks.WAXED_EXPOSED_COPPER_SOUL_LANTERN.get(), MWBlocks.EXPOSED_COPPER_SOUL_LANTERN.get())
            .put(MWBlocks.WAXED_WEATHERED_COPPER_SOUL_LANTERN.get(), MWBlocks.WEATHERED_COPPER_SOUL_LANTERN.get())
            .put(MWBlocks.WAXED_OXIDIZED_COPPER_SOUL_LANTERN.get(), MWBlocks.OXIDIZED_COPPER_SOUL_LANTERN.get())
            .put(MWBlocks.WAXED_COPPER_BARS.get(), MWBlocks.COPPER_BARS.get())
            .put(MWBlocks.WAXED_EXPOSED_COPPER_BARS.get(), MWBlocks.EXPOSED_COPPER_BARS.get())
            .put(MWBlocks.WAXED_WEATHERED_COPPER_BARS.get(), MWBlocks.WEATHERED_COPPER_BARS.get())
            .put(MWBlocks.WAXED_OXIDIZED_COPPER_BARS.get(), MWBlocks.OXIDIZED_COPPER_BARS.get())
            .put(MWBlocks.WAXED_COPPER_CAGE.get(), MWBlocks.COPPER_CAGE.get())
            .put(MWBlocks.WAXED_EXPOSED_COPPER_CAGE.get(), MWBlocks.EXPOSED_COPPER_CAGE.get())
            .put(MWBlocks.WAXED_WEATHERED_COPPER_CAGE.get(), MWBlocks.WEATHERED_COPPER_CAGE.get())
            .put(MWBlocks.WAXED_OXIDIZED_COPPER_CAGE.get(), MWBlocks.OXIDIZED_COPPER_CAGE.get())
            .put(MWBlocks.WAXED_COPPER_GRATE.get(), MWBlocks.COPPER_GRATE.get())
            .put(MWBlocks.WAXED_EXPOSED_COPPER_GRATE.get(), MWBlocks.EXPOSED_COPPER_GRATE.get())
            .put(MWBlocks.WAXED_WEATHERED_COPPER_GRATE.get(), MWBlocks.WEATHERED_COPPER_GRATE.get())
            .put(MWBlocks.WAXED_OXIDIZED_COPPER_GRATE.get(), MWBlocks.OXIDIZED_COPPER_GRATE.get())
            .put(MWBlocks.WALL_HANGING_WAXED_COPPER_LANTERN.get(), MWBlocks.WALL_HANGING_COPPER_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_WAXED_EXPOSED_COPPER_LANTERN.get(), MWBlocks.WALL_HANGING_EXPOSED_COPPER_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_WAXED_WEATHERED_COPPER_LANTERN.get(), MWBlocks.WALL_HANGING_WEATHERED_COPPER_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_WAXED_OXIDIZED_COPPER_LANTERN.get(), MWBlocks.WALL_HANGING_OXIDIZED_COPPER_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_WAXED_COPPER_SOUL_LANTERN.get(), MWBlocks.WALL_HANGING_COPPER_SOUL_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_WAXED_EXPOSED_COPPER_SOUL_LANTERN.get(), MWBlocks.WALL_HANGING_EXPOSED_COPPER_SOUL_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_WAXED_WEATHERED_COPPER_SOUL_LANTERN.get(), MWBlocks.WALL_HANGING_WEATHERED_COPPER_SOUL_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_WAXED_OXIDIZED_COPPER_SOUL_LANTERN.get(), MWBlocks.WALL_HANGING_OXIDIZED_COPPER_SOUL_LANTERN.get())
            .put(MWBlocks.WAXED_COPPER_END_LANTERN.get(), MWBlocks.COPPER_END_LANTERN.get())
            .put(MWBlocks.WAXED_EXPOSED_COPPER_END_LANTERN.get(), MWBlocks.EXPOSED_COPPER_END_LANTERN.get())
            .put(MWBlocks.WAXED_WEATHERED_COPPER_END_LANTERN.get(), MWBlocks.WEATHERED_COPPER_END_LANTERN.get())
            .put(MWBlocks.WAXED_OXIDIZED_COPPER_END_LANTERN.get(), MWBlocks.OXIDIZED_COPPER_END_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_WAXED_COPPER_END_LANTERN.get(), MWBlocks.WALL_HANGING_COPPER_END_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_WAXED_EXPOSED_COPPER_END_LANTERN.get(), MWBlocks.WALL_HANGING_EXPOSED_COPPER_END_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_WAXED_WEATHERED_COPPER_END_LANTERN.get(), MWBlocks.WALL_HANGING_WEATHERED_COPPER_END_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_WAXED_OXIDIZED_COPPER_END_LANTERN.get(), MWBlocks.WALL_HANGING_OXIDIZED_COPPER_END_LANTERN.get())
            .put(MWBlocks.WAXED_COPPER_SCULK_LANTERN.get(), MWBlocks.COPPER_SCULK_LANTERN.get())
            .put(MWBlocks.WAXED_EXPOSED_COPPER_SCULK_LANTERN.get(), MWBlocks.EXPOSED_COPPER_SCULK_LANTERN.get())
            .put(MWBlocks.WAXED_WEATHERED_COPPER_SCULK_LANTERN.get(), MWBlocks.WEATHERED_COPPER_SCULK_LANTERN.get())
            .put(MWBlocks.WAXED_OXIDIZED_COPPER_SCULK_LANTERN.get(), MWBlocks.OXIDIZED_COPPER_SCULK_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_WAXED_COPPER_SCULK_LANTERN.get(), MWBlocks.WALL_HANGING_COPPER_SCULK_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_WAXED_EXPOSED_COPPER_SCULK_LANTERN.get(), MWBlocks.WALL_HANGING_EXPOSED_COPPER_SCULK_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_WAXED_WEATHERED_COPPER_SCULK_LANTERN.get(), MWBlocks.WALL_HANGING_WEATHERED_COPPER_SCULK_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_WAXED_OXIDIZED_COPPER_SCULK_LANTERN.get(), MWBlocks.WALL_HANGING_OXIDIZED_COPPER_SCULK_LANTERN.get())*/
    .build());

    /**
     * {@link Supplier<BiMap> Waxed Blocks by Block}
     */
    Supplier<BiMap<Block, Block>> WAXED_BY_BLOCK = Suppliers.memoize(() -> UNWAXED_BY_BLOCK.get().inverse());

    /**
     * Get the unwaxed variant of the current {@link Block Block}
     *
     * @param block {@link Block The current Block}
     * @return {@link Block The unwaxed Block}
     */
    static Optional<Block> getUnwaxed(final Block block) {
        return Optional.ofNullable(UNWAXED_BY_BLOCK.get().get(block));
    }

    /**
     * Get the unwaxed variant of the current {@link BlockState Block State}
     *
     * @param blockState {@link Block The current Block State}
     * @return {@link Block The unwaxed Block State}
     */
    static Optional<BlockState> getUnwaxed(final BlockState blockState) {
        final boolean hasPowerProperty = blockState.hasProperty(BlockStateProperties.POWER);
        return getUnwaxed(blockState.getBlock()).map(block -> block.withPropertiesOf(IMWChangeOverTimeBlock.getAdjustedBlockState(blockState)));
    }

    /**
     * Get the waxed variant for the current {@link Block Block}
     *
     * @param block {@link Block The current Block}
     * @return {@link Block The waxed Block}
     */
    static Optional<Block> getWaxed(final Block block) {
        return Optional.ofNullable(WAXED_BY_BLOCK.get().get(block));
    }

    /**
     * Get the waxed variant for the current {@link BlockState Block State}
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Block The waxed Block State}
     */
    static @NotNull Optional<BlockState> getWaxed(final BlockState blockState) {
        return getWaxed(blockState.getBlock()).map(block -> block.withPropertiesOf(IMWChangeOverTimeBlock.getAdjustedBlockState(blockState)));
    }

    /**
     * Remove the wax from the {@link BlockState current Block State}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param context {@link UseOnContext The Item Use Context}
     * @param toolAction {@link ToolAction The tool action}
     * @param isClient {@link Boolean If the scraping only happened on the Client}
     * @return {@link BlockState The scraped Block State}
     */
    @Nullable
    static BlockState scrapeWax(final BlockState blockState, final UseOnContext context, final ToolAction toolAction, final boolean isClient) {
        final Optional<BlockState> previousBlockState = getUnwaxed(blockState);
        if(previousBlockState.isPresent()) {
            context.getLevel().levelEvent(context.getPlayer(), 3004, context.getClickedPos(), 0);
            return previousBlockState.get();
        }
        return blockState;
    }

    /**
     * Apply wax to the {@link BlockState current Block State}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param itemStack {@link ItemStack The honeycomb Item Stack}
     * @param player {@link Player The player who right clicked the block}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param hand {@link InteractionHand The hand the player has interacted with}
     * @param level {@link Level The level reference}
     * @return {@link InteractionResult The interaction result}
     */
    @Nullable
    static InteractionResult applyWax(final BlockState blockState, final ItemStack itemStack, final Player player, final  BlockPos blockPos, final InteractionHand hand, final Level level) {
        final Optional<BlockState> waxedBlockState = getWaxed(blockState);
        if(waxedBlockState.isPresent()) {
            final BlockState waxedState = waxedBlockState.get();
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockPos, itemStack);
            }
            ItemHelper.hurt(itemStack, player, level, hand);
            level.setBlock(blockPos, waxedState, 11);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, waxedState));
            level.levelEvent(player, 3003, blockPos, 0);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }

    /**
     * Check if a {@link Block Block} is waxed
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if the Block is waxed}
     */
    static boolean isWaxed(final BlockState blockState) {
        return UNWAXED_BY_BLOCK.get().containsKey(blockState.getBlock());
    }

    /**
     * Get the {@link BlockState modified Block State} after interacting with a tool
     *
     * @param blockState {@link BlockState The current Block State}
     * @param context {@link UseOnContext The Item Use Context}
     * @param toolAction {@link ToolAction The tool action}
     * @param isClient {@link Boolean If the scraping only happened on the Client}
     * @return {@link BlockState The modified Block State}
     */
    static BlockState getToolModifiedState(final BlockState blockState, final UseOnContext context, final ToolAction toolAction, final boolean isClient) {
        if(isWaxed(blockState) && context.getItemInHand().getItem() instanceof AxeItem && toolAction.equals(ToolActions.AXE_WAX_OFF)) {
            return scrapeWax(blockState, context, toolAction, isClient);
        }
        return null;
    }

}