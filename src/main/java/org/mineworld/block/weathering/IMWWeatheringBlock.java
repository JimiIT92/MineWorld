package org.mineworld.block.weathering;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} interface for an {@link IMWChangeOverTimeBlock oxidizable block}
 */
public interface IMWWeatheringBlock extends IMWChangeOverTimeBlock {

    Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(MWBlocks.COPPER_STAIRS.get(), MWBlocks.EXPOSED_COPPER_STAIRS.get())
            .put(MWBlocks.EXPOSED_COPPER_STAIRS.get(), MWBlocks.WEATHERED_COPPER_STAIRS.get())
            .put(MWBlocks.WEATHERED_COPPER_STAIRS.get(), MWBlocks.OXIDIZED_COPPER_STAIRS.get())
            .put(MWBlocks.COPPER_SLAB.get(), MWBlocks.EXPOSED_COPPER_SLAB.get())
            .put(MWBlocks.EXPOSED_COPPER_SLAB.get(), MWBlocks.WEATHERED_COPPER_SLAB.get())
            .put(MWBlocks.WEATHERED_COPPER_SLAB.get(), MWBlocks.OXIDIZED_COPPER_SLAB.get())
            .put(MWBlocks.COPPER_PRESSURE_PLATE.get(), MWBlocks.EXPOSED_COPPER_PRESSURE_PLATE.get())
            .put(MWBlocks.EXPOSED_COPPER_PRESSURE_PLATE.get(), MWBlocks.WEATHERED_COPPER_PRESSURE_PLATE.get())
            .put(MWBlocks.WEATHERED_COPPER_PRESSURE_PLATE.get(), MWBlocks.OXIDIZED_COPPER_PRESSURE_PLATE.get())
            .put(MWBlocks.CUT_COPPER_PRESSURE_PLATE.get(), MWBlocks.EXPOSED_CUT_COPPER_PRESSURE_PLATE.get())
            .put(MWBlocks.EXPOSED_CUT_COPPER_PRESSURE_PLATE.get(), MWBlocks.WEATHERED_CUT_COPPER_PRESSURE_PLATE.get())
            .put(MWBlocks.WEATHERED_CUT_COPPER_PRESSURE_PLATE.get(), MWBlocks.OXIDIZED_CUT_COPPER_PRESSURE_PLATE.get())
            .put(MWBlocks.COPPER_TRAPDOOR.get(), MWBlocks.EXPOSED_COPPER_TRAPDOOR.get())
            .put(MWBlocks.EXPOSED_COPPER_TRAPDOOR.get(), MWBlocks.WEATHERED_COPPER_TRAPDOOR.get())
            .put(MWBlocks.WEATHERED_COPPER_TRAPDOOR.get(), MWBlocks.OXIDIZED_COPPER_TRAPDOOR.get())
            .put(MWBlocks.COPPER_CHAIN.get(), MWBlocks.EXPOSED_COPPER_CHAIN.get())
            .put(MWBlocks.EXPOSED_COPPER_CHAIN.get(), MWBlocks.WEATHERED_COPPER_CHAIN.get())
            .put(MWBlocks.WEATHERED_COPPER_CHAIN.get(), MWBlocks.OXIDIZED_COPPER_CHAIN.get())
            .put(MWBlocks.COPPER_LANTERN.get(), MWBlocks.EXPOSED_COPPER_LANTERN.get())
            .put(MWBlocks.EXPOSED_COPPER_LANTERN.get(), MWBlocks.WEATHERED_COPPER_LANTERN.get())
            .put(MWBlocks.WEATHERED_COPPER_LANTERN.get(), MWBlocks.OXIDIZED_COPPER_LANTERN.get())
            .put(MWBlocks.COPPER_SOUL_LANTERN.get(), MWBlocks.EXPOSED_COPPER_SOUL_LANTERN.get())
            .put(MWBlocks.EXPOSED_COPPER_SOUL_LANTERN.get(), MWBlocks.WEATHERED_COPPER_SOUL_LANTERN.get())
            .put(MWBlocks.WEATHERED_COPPER_SOUL_LANTERN.get(), MWBlocks.OXIDIZED_COPPER_SOUL_LANTERN.get())
            .put(MWBlocks.COPPER_BARS.get(), MWBlocks.EXPOSED_COPPER_BARS.get())
            .put(MWBlocks.EXPOSED_COPPER_BARS.get(), MWBlocks.WEATHERED_COPPER_BARS.get())
            .put(MWBlocks.WEATHERED_COPPER_BARS.get(), MWBlocks.OXIDIZED_COPPER_BARS.get())
            .put(MWBlocks.COPPER_CAGE.get(), MWBlocks.EXPOSED_COPPER_CAGE.get())
            .put(MWBlocks.EXPOSED_COPPER_CAGE.get(), MWBlocks.WEATHERED_COPPER_CAGE.get())
            .put(MWBlocks.WEATHERED_COPPER_CAGE.get(), MWBlocks.OXIDIZED_COPPER_CAGE.get())
            .put(MWBlocks.COPPER_GRATE.get(), MWBlocks.EXPOSED_COPPER_GRATE.get())
            .put(MWBlocks.EXPOSED_COPPER_GRATE.get(), MWBlocks.WEATHERED_COPPER_GRATE.get())
            .put(MWBlocks.WEATHERED_COPPER_GRATE.get(), MWBlocks.OXIDIZED_COPPER_GRATE.get())
            .put(MWBlocks.WALL_HANGING_COPPER_LANTERN.get(), MWBlocks.WALL_HANGING_EXPOSED_COPPER_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_EXPOSED_COPPER_LANTERN.get(), MWBlocks.WALL_HANGING_WEATHERED_COPPER_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_WEATHERED_COPPER_LANTERN.get(), MWBlocks.WALL_HANGING_OXIDIZED_COPPER_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_COPPER_SOUL_LANTERN.get(), MWBlocks.WALL_HANGING_EXPOSED_COPPER_SOUL_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_EXPOSED_COPPER_SOUL_LANTERN.get(), MWBlocks.WALL_HANGING_WEATHERED_COPPER_SOUL_LANTERN.get())
            .put(MWBlocks.WALL_HANGING_WEATHERED_COPPER_SOUL_LANTERN.get(), MWBlocks.WALL_HANGING_OXIDIZED_COPPER_SOUL_LANTERN.get())
    .build());

    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> NEXT_BY_BLOCK.get().inverse());

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
        return getPrevious(blockState.getBlock()).map(block -> block.withPropertiesOf(IMWChangeOverTimeBlock.getAdjustedBlockState(blockState)));
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
        return getFirst(blockState.getBlock()).withPropertiesOf(IMWChangeOverTimeBlock.getAdjustedBlockState(blockState));
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
        return getNext(blockState.getBlock()).map(block -> block.withPropertiesOf(IMWChangeOverTimeBlock.getAdjustedBlockState(blockState)));
    }

    /**
     * Sets the previous {@link WeatheringCopper.WeatherState weather state}
     * on right click with an {@link AxeItem axe}
     *
     * @param state {@link BlockState The current block state}
     * @param context {@link UseOnContext The id use context}
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
     * Change the {@link WeatheringCopper.WeatherState weather state}
     * on random tick
     *
     * @param block {@link ChangeOverTimeBlock The block to change}
     * @param blockState {@link BlockState The current block state}
     * @param level {@link ServerLevel The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    static void randomTick(final ChangeOverTimeBlock<WeatheringCopper.WeatherState> block, final BlockState blockState, final ServerLevel level, final BlockPos blockPos, final RandomSource randomSource) {
        if(!isWaxed(blockState)) {
            block.onRandomTick(blockState, level, blockPos, randomSource);
        }
    }

    /**
     * Check if a {@link Block block} should randomly ticking
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link Boolean True if the block should randomly ticking}
     */
    static boolean isRandomlyTicking(final BlockState blockState) {
        return !isWaxed(blockState) && getNext(blockState.getBlock()).isPresent();
    }

    /**
     * Sets the previous {@link WeatheringCopper.WeatherState weather state}
     * on right click with an {@link AxeItem axe}
     *
     * @param blockState {@link BlockState The current block state}
     * @param context {@link UseOnContext The id use context}
     * @param toolAction {@link ToolAction The tool action}
     * @param isClient {@link Boolean If the code is executed client side}
     * @return {@link BlockState The modified block state}
     */
    static BlockState getToolModifiedState(final BlockState blockState, final UseOnContext context, final ToolAction toolAction, final boolean isClient) {
        if(!isWaxed(blockState)) {
            return scrapeWeatherState(blockState, context, toolAction, isClient);
        }
        return IMWWaxableBlock.getToolModifiedState(blockState, context, toolAction, isClient);
    }

    /**
     * Check if a {@link BlockState block state} corresponds to a waxed state
     *
     * @param blockState {@link BlockState The block state to check}
     * @return {@link Boolean True if the block state is waxed}
     */
    private static boolean isWaxed(final BlockState blockState) {
        return IMWWaxableBlock.isWaxed(blockState);
    }

    /**
     * Clear some oxidization on struck by {@link LightningBolt lightning}
     *
     * @param blockState {@link Block The current block state}
     * @param level {@link Level The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     */
    static void lightningStrike(final BlockState blockState, final Level level, final BlockPos blockPos) {
        if(!IMWWaxableBlock.isWaxed(blockState)) {
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