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
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} interface for an {@link IMWChangeOverTimeBlock waxable block}
 */
public interface IMWWaxableBlock extends IMWChangeOverTimeBlock {

    Supplier<BiMap<Block, Block>> UNWAXED_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(MWBlocks.WAXED_COPPER_STAIRS.get(), MWBlocks.COPPER_STAIRS.get())
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
    .build());

    Supplier<BiMap<Block, Block>> WAXED_BY_BLOCK = Suppliers.memoize(() -> UNWAXED_BY_BLOCK.get().inverse());

    /**
     * Get the unwaxed variant of the current {@link Block weathering state block}
     *
     * @param block {@link Block The current weathering state block}
     * @return {@link Block The unwaxed weathering state block}
     */
    static Optional<Block> getUnwaxed(final Block block) {
        return Optional.ofNullable(UNWAXED_BY_BLOCK.get().get(block));
    }

    /**
     * Get the unwaxed variant of the current {@link BlockState weathering state block state}
     *
     * @param blockState {@link Block The current weathering state block state}
     * @return {@link Block The unwaxed weathering state block}
     */
    static Optional<BlockState> getUnwaxed(final BlockState blockState) {
        final boolean hasPowerProperty = blockState.hasProperty(BlockStateProperties.POWER);
        return getUnwaxed(blockState.getBlock()).map(block -> block.withPropertiesOf(IMWChangeOverTimeBlock.getAdjustedBlockState(blockState)));
    }

    /**
     * Get the waxed variant for the current {@link Block weathering state block}
     *
     * @param block {@link Block The current weathering state block}
     * @return {@link Block The next weathering state block}
     */
    static Optional<Block> getWaxed(final Block block) {
        return Optional.ofNullable(WAXED_BY_BLOCK.get().get(block));
    }

    /**
     * Get the waxed variant for the current {@link BlockState weathering state block state}
     *
     * @param blockState {@link BlockState The current weathering state block state}
     * @return {@link Block The next weathering state block state}
     */
    static @NotNull Optional<BlockState> getWaxed(final BlockState blockState) {
        return getWaxed(blockState.getBlock()).map(block -> block.withPropertiesOf(IMWChangeOverTimeBlock.getAdjustedBlockState(blockState)));
    }

    /**
     * Remove the wax from the {@link BlockState current block state}
     *
     * @param state {@link BlockState The current block state}
     * @param context {@link UseOnContext The item use context}
     * @param toolAction {@link ToolAction The tool action}
     * @param isClient {@link Boolean If the code is executed client side}
     * @return {@link BlockState The modified block state}
     */
    @Nullable
    static BlockState scrapeWax(final BlockState state, final UseOnContext context, final ToolAction toolAction, final boolean isClient) {
        Optional<BlockState> previousBlockState = getUnwaxed(state);
        if(previousBlockState.isPresent()) {
            context.getLevel().levelEvent(context.getPlayer(), 3004, context.getClickedPos(), 0);
            return previousBlockState.get();
        }
        return state;
    }

    /**
     * Apply wax to the {@link BlockState current block state}
     *
     * @param state {@link BlockState The current block state}
     * @param itemStack {@link ItemStack The honeycomb item stack}
     * @param player {@link Player The player who right clicked the block}
     * @param blockPos {@link BlockPos The current block pos}
     * @param hand {@link InteractionHand The hand the player has interacted with}
     * @param level {@link Level The world reference}
     * @return {@link InteractionResult The interaction result}
     */
    @Nullable
    static InteractionResult applyWax(final BlockState state, final ItemStack itemStack, final Player player, final  BlockPos blockPos, final InteractionHand hand, final Level level) {
        Optional<BlockState> waxedBlockState = getWaxed(state);
        if(waxedBlockState.isPresent()) {
            final BlockState waxedState = waxedBlockState.get();
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockPos, itemStack);
            }
            if(!player.isCreative()) {
                itemStack.shrink(1);
            }
            level.setBlock(blockPos, waxedState, 11);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, waxedState));
            level.levelEvent(player, 3003, blockPos, 0);
            if(level.isClientSide()) {
                player.swing(hand);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }

    /**
     * Check if a {@link Block block} is waxed
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link Boolean True if the block is waxed}
     */
    static boolean isWaxed(final BlockState blockState) {
        return UNWAXED_BY_BLOCK.get().containsKey(blockState.getBlock());
    }

    /**
     * Sets the previous {@link WeatheringCopper.WeatherState weather state}
     * on right click with an {@link AxeItem axe}
     *
     * @param blockState {@link BlockState The current block state}
     * @param context {@link UseOnContext The item use context}
     * @param toolAction {@link ToolAction The tool action}
     * @param isClient {@link Boolean If the code is executed client side}
     * @return {@link BlockState The modified block state}
     */
    static BlockState getToolModifiedState(final BlockState blockState, final UseOnContext context, final ToolAction toolAction, final boolean isClient) {
        if(isWaxed(blockState) && context.getItemInHand().getItem() instanceof AxeItem && toolAction.equals(ToolActions.AXE_WAX_OFF)) {
            return scrapeWax(blockState, context, toolAction, isClient);
        }
        return null;
    }

}