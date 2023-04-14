package org.mineworld.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.core.vanillaexpansion.MWVanillaExpansionCopperBlocks;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} interface for an oxidizable block
 */
public interface IMWWeatheringBlock extends ChangeOverTimeBlock<WeatheringCopper.WeatherState> {

    Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(MWVanillaExpansionCopperBlocks.COPPER_STAIRS.get(), MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_STAIRS.get())
            .put(MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_STAIRS.get(), MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_STAIRS.get())
            .put(MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_STAIRS.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_COPPER_STAIRS.get())
            .put(MWVanillaExpansionCopperBlocks.COPPER_SLAB.get(), MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_SLAB.get())
            .put(MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_SLAB.get(), MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_SLAB.get())
            .put(MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_SLAB.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_COPPER_SLAB.get())
            .put(MWVanillaExpansionCopperBlocks.COPPER_PRESSURE_PLATE.get(), MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_PRESSURE_PLATE.get())
            .put(MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_PRESSURE_PLATE.get(), MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_PRESSURE_PLATE.get())
            .put(MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_PRESSURE_PLATE.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_COPPER_PRESSURE_PLATE.get())
            .put(MWVanillaExpansionCopperBlocks.CUT_COPPER_PRESSURE_PLATE.get(), MWVanillaExpansionCopperBlocks.EXPOSED_CUT_COPPER_PRESSURE_PLATE.get())
            .put(MWVanillaExpansionCopperBlocks.EXPOSED_CUT_COPPER_PRESSURE_PLATE.get(), MWVanillaExpansionCopperBlocks.WEATHERED_CUT_COPPER_PRESSURE_PLATE.get())
            .put(MWVanillaExpansionCopperBlocks.WEATHERED_CUT_COPPER_PRESSURE_PLATE.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_CUT_COPPER_PRESSURE_PLATE.get())
            .put(MWVanillaExpansionCopperBlocks.COPPER_TRAPDOOR.get(), MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_TRAPDOOR.get())
            .put(MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_TRAPDOOR.get(), MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_TRAPDOOR.get())
            .put(MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_TRAPDOOR.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_COPPER_TRAPDOOR.get())
            .put(MWVanillaExpansionCopperBlocks.COPPER_CHAIN.get(), MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_CHAIN.get())
            .put(MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_CHAIN.get(), MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_CHAIN.get())
            .put(MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_CHAIN.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_COPPER_CHAIN.get())
            .put(MWVanillaExpansionCopperBlocks.COPPER_LANTERN.get(), MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_LANTERN.get())
            .put(MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_LANTERN.get(), MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_LANTERN.get())
            .put(MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_LANTERN.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_COPPER_LANTERN.get())
            .put(MWVanillaExpansionCopperBlocks.COPPER_SOUL_LANTERN.get(), MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_SOUL_LANTERN.get())
            .put(MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_SOUL_LANTERN.get(), MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_SOUL_LANTERN.get())
            .put(MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_SOUL_LANTERN.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_COPPER_SOUL_LANTERN.get())
            .put(MWVanillaExpansionCopperBlocks.COPPER_BARS.get(), MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_BARS.get())
            .put(MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_BARS.get(), MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_BARS.get())
            .put(MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_BARS.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_COPPER_BARS.get())
            .put(MWVanillaExpansionCopperBlocks.COPPER_CAGE.get(), MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_CAGE.get())
            .put(MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_CAGE.get(), MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_CAGE.get())
            .put(MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_CAGE.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_COPPER_CAGE.get())
    .build());

    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> NEXT_BY_BLOCK.get().inverse());

    Supplier<BiMap<Block, Block>> UNWAXED_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(MWVanillaExpansionCopperBlocks.WAXED_COPPER_STAIRS.get(), MWVanillaExpansionCopperBlocks.COPPER_STAIRS.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_EXPOSED_COPPER_STAIRS.get(), MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_STAIRS.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_WEATHERED_COPPER_STAIRS.get(), MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_STAIRS.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_OXIDIZED_COPPER_STAIRS.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_COPPER_STAIRS.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_COPPER_SLAB.get(), MWVanillaExpansionCopperBlocks.COPPER_SLAB.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_EXPOSED_COPPER_SLAB.get(), MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_SLAB.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_WEATHERED_COPPER_SLAB.get(), MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_SLAB.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_OXIDIZED_COPPER_SLAB.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_COPPER_SLAB.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_COPPER_PRESSURE_PLATE.get(), MWVanillaExpansionCopperBlocks.COPPER_PRESSURE_PLATE.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_EXPOSED_COPPER_PRESSURE_PLATE.get(), MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_PRESSURE_PLATE.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_WEATHERED_COPPER_PRESSURE_PLATE.get(), MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_PRESSURE_PLATE.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_OXIDIZED_COPPER_PRESSURE_PLATE.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_COPPER_PRESSURE_PLATE.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_CUT_COPPER_PRESSURE_PLATE.get(), MWVanillaExpansionCopperBlocks.CUT_COPPER_PRESSURE_PLATE.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_EXPOSED_CUT_COPPER_PRESSURE_PLATE.get(), MWVanillaExpansionCopperBlocks.EXPOSED_CUT_COPPER_PRESSURE_PLATE.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_WEATHERED_CUT_COPPER_PRESSURE_PLATE.get(), MWVanillaExpansionCopperBlocks.WEATHERED_CUT_COPPER_PRESSURE_PLATE.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_OXIDIZED_CUT_COPPER_PRESSURE_PLATE.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_CUT_COPPER_PRESSURE_PLATE.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_COPPER_TRAPDOOR.get(), MWVanillaExpansionCopperBlocks.COPPER_TRAPDOOR.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR.get(), MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_TRAPDOOR.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR.get(), MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_TRAPDOOR.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_COPPER_TRAPDOOR.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_COPPER_CHAIN.get(), MWVanillaExpansionCopperBlocks.COPPER_CHAIN.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_EXPOSED_COPPER_CHAIN.get(), MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_CHAIN.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_WEATHERED_COPPER_CHAIN.get(), MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_CHAIN.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_OXIDIZED_COPPER_CHAIN.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_COPPER_CHAIN.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_COPPER_LANTERN.get(), MWVanillaExpansionCopperBlocks.COPPER_LANTERN.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_EXPOSED_COPPER_LANTERN.get(), MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_LANTERN.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_WEATHERED_COPPER_LANTERN.get(), MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_LANTERN.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_OXIDIZED_COPPER_LANTERN.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_COPPER_LANTERN.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_COPPER_SOUL_LANTERN.get(), MWVanillaExpansionCopperBlocks.COPPER_SOUL_LANTERN.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_EXPOSED_COPPER_SOUL_LANTERN.get(), MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_SOUL_LANTERN.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_WEATHERED_COPPER_SOUL_LANTERN.get(), MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_SOUL_LANTERN.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_OXIDIZED_COPPER_SOUL_LANTERN.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_COPPER_SOUL_LANTERN.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_COPPER_BARS.get(), MWVanillaExpansionCopperBlocks.COPPER_BARS.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_EXPOSED_COPPER_BARS.get(), MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_BARS.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_WEATHERED_COPPER_BARS.get(), MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_BARS.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_OXIDIZED_COPPER_BARS.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_COPPER_BARS.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_COPPER_CAGE.get(), MWVanillaExpansionCopperBlocks.COPPER_CAGE.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_EXPOSED_COPPER_CAGE.get(), MWVanillaExpansionCopperBlocks.EXPOSED_COPPER_CAGE.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_WEATHERED_COPPER_CAGE.get(), MWVanillaExpansionCopperBlocks.WEATHERED_COPPER_CAGE.get())
            .put(MWVanillaExpansionCopperBlocks.WAXED_OXIDIZED_COPPER_CAGE.get(), MWVanillaExpansionCopperBlocks.OXIDIZED_COPPER_CAGE.get())
    .build());

    Supplier<BiMap<Block, Block>> WAXED_BY_BLOCK = Suppliers.memoize(() -> UNWAXED_BY_BLOCK.get().inverse());

    /**
     * Get the {@link BlockState block state} for the next state with
     * the adjusted properties
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link BlockState The adjusted block state}
     */
    static BlockState getAdjustedBlockState(BlockState blockState) {
        if(blockState.hasProperty(BlockStateProperties.POWER)) {
            blockState = blockState.setValue(BlockStateProperties.POWER, 0);
        }
        return blockState;
    }

    /**
     * Get the previous {@link Block weathering state block}
     * based on the current block
     *
     * @param block {@link Block The current weathering state block}
     * @return {@link Block The previous weathering state block}
     */
    static Optional<Block> getPrevious(final Block block) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(block));
    }

    /**
     * Get the previous {@link BlockState weathering state block state}
     * based on the current block state
     *
     * @param blockState {@link BlockState The current weathering state block state}
     * @return {@link Block The previous weathering state block state}
     */
    static Optional<BlockState> getPrevious(final BlockState blockState) {
        return getPrevious(blockState.getBlock()).map(block -> block.withPropertiesOf(getAdjustedBlockState(blockState)));
    }

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
        return getUnwaxed(blockState.getBlock()).map(block -> block.withPropertiesOf(getAdjustedBlockState(blockState)));
    }

    /**
     * Get the first {@link Block weathering state block}
     * based on the current block
     *
     * @param currentBlock {@link Block The current weathering state block}
     * @return {@link Block The first weathering state block}
     */
    static Block getFirst(final Block currentBlock) {
        Block previousBlock = currentBlock;

        for(Block block = PREVIOUS_BY_BLOCK.get().get(currentBlock); block != null; block = PREVIOUS_BY_BLOCK.get().get(block)) {
            previousBlock = block;
        }

        return previousBlock;
    }

    /**
     * Get the first {@link Block weathering state block state}
     * based on the current {@link BlockState block state}
     *
     * @param blockState {@link Block The current weathering state block state}
     * @return {@link Block The first weathering state block}
     */
    static BlockState getFirst(final BlockState blockState) {
        final boolean hasPowerProperty = blockState.hasProperty(BlockStateProperties.POWER);
        return getFirst(blockState.getBlock()).withPropertiesOf(getAdjustedBlockState(blockState));
    }

    /**
     * Get the next {@link Block weathering state block}
     * based on the current block
     *
     * @param block {@link Block The current weathering state block}
     * @return {@link Block The next weathering state block}
     */
    static Optional<Block> getNext(final Block block) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(block));
    }

    /**
     * Get the next {@link BlockState weathering state block state}
     * based on the current block state
     *
     * @param blockState {@link BlockState The current weathering state block state}
     * @return {@link Block The next weathering state block state}
     */
    default @NotNull Optional<BlockState> getNext(final BlockState blockState) {
        final boolean hasPowerProperty = blockState.hasProperty(BlockStateProperties.POWER);
        return getNext(blockState.getBlock()).map(block -> block.withPropertiesOf(getAdjustedBlockState(blockState)));
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
        final boolean hasPowerProperty = blockState.hasProperty(BlockStateProperties.POWER);
        return getWaxed(blockState.getBlock()).map(block -> block.withPropertiesOf(getAdjustedBlockState(blockState)));
    }

    /**
     * Get the {@link Float oxidization chance}
     * based on the current {@link WeatheringCopper.WeatherState weather state}
     *
     * @return {@link Float The oxidization chance}
     */
    default float getChanceModifier() {
        return this.getAge().equals(WeatheringCopper.WeatherState.UNAFFECTED) ? 0.75F : 1.0F;
    }

    /**
     * Sets the previous {@link WeatheringCopper.WeatherState weather state}
     * on right click with an {@link AxeItem axe}
     *
     * @param state {@link BlockState The current block state}
     * @param context {@link UseOnContext The item use context}
     * @param toolAction {@link ToolAction The tool action}
     * @param isClient {@link Boolean If the code is executed client side}
     * @return {@link BlockState The modified block state}
     */
    @Nullable
    static BlockState scrapeWeatherState(final BlockState state, final UseOnContext context, final ToolAction toolAction, final boolean isClient) {
        ItemStack stack = context.getItemInHand();
        if(stack.getItem() instanceof AxeItem && toolAction.equals(ToolActions.AXE_SCRAPE)) {
            Optional<BlockState> previousBlockState = getPrevious(state);
            if(previousBlockState.isPresent()) {
                context.getLevel().levelEvent(context.getPlayer(), 3005, context.getClickedPos(), 0);
                return previousBlockState.get();
            }
        }
        return null;
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
     * Clear some oxidization on struck by {@link LightningBolt lightning}
     *
     * @param blockState {@link Block The current block state}
     * @param level {@link Level The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     */
    static void lightningStrike(final BlockState blockState, final Level level, final BlockPos blockPos) {
        if(!isWaxed(blockState)) {
            level.setBlockAndUpdate(blockPos, getFirst(blockState));
            BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
            int blocks = level.random.nextInt(3) + 3;

            for(int j = 0; j < blocks; ++j) {
                mutableBlockPos.set(blockPos);
                for(int i = 0; i < (level.random.nextInt(8) + 1); ++i) {
                    Optional<BlockPos> optional = randomStepCleaningCopper(level, mutableBlockPos);
                    if (optional.isEmpty()) {
                        break;
                    }
                    mutableBlockPos.set(optional.get());
                }
            }
        }

    }

    /**
     * Get a random block in a range and if is a {@link IMWWeatheringBlock oxidizable block}
     * clear a {@link WeatheringCopper.WeatherState weather state}
     *
     * @param level {@link Level The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link Optional<BlockPos> The cleaned block pos}
     */
    private static Optional<BlockPos> randomStepCleaningCopper(Level level, BlockPos blockPos) {
        for(BlockPos blockpos : BlockPos.randomInCube(level.random, 10, blockPos, 1)) {
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.getBlock() instanceof IMWWeatheringBlock) {
                getPrevious(blockstate).ifPresent(blockState -> level.setBlockAndUpdate(blockpos, blockState));
                level.levelEvent(3002, blockpos, -1);
                return Optional.of(blockpos);
            }
        }
        return Optional.empty();
    }
}
