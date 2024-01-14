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
import org.mineworld.core.MWCopperBlocks;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} interface for an {@link ChangeOverTimeBlock waxable Block}
 */
public interface IMWWeatheringBlock extends IMWChangeOverTimeBlock {

    /**
     * {@link Supplier<BiMap> Oxidized Blocks by Block}
     */
    Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(MWCopperBlocks.COPPER_STAIRS.get(), MWCopperBlocks.EXPOSED_COPPER_STAIRS.get())
            .put(MWCopperBlocks.EXPOSED_COPPER_STAIRS.get(), MWCopperBlocks.WEATHERED_COPPER_STAIRS.get())
            .put(MWCopperBlocks.WEATHERED_COPPER_STAIRS.get(), MWCopperBlocks.OXIDIZED_COPPER_STAIRS.get())
            .put(MWCopperBlocks.COPPER_SLAB.get(), MWCopperBlocks.EXPOSED_COPPER_SLAB.get())
            .put(MWCopperBlocks.EXPOSED_COPPER_SLAB.get(), MWCopperBlocks.WEATHERED_COPPER_SLAB.get())
            .put(MWCopperBlocks.WEATHERED_COPPER_SLAB.get(), MWCopperBlocks.OXIDIZED_COPPER_SLAB.get())
            .put(MWCopperBlocks.COPPER_PRESSURE_PLATE.get(), MWCopperBlocks.EXPOSED_COPPER_PRESSURE_PLATE.get())
            .put(MWCopperBlocks.EXPOSED_COPPER_PRESSURE_PLATE.get(), MWCopperBlocks.WEATHERED_COPPER_PRESSURE_PLATE.get())
            .put(MWCopperBlocks.WEATHERED_COPPER_PRESSURE_PLATE.get(), MWCopperBlocks.OXIDIZED_COPPER_PRESSURE_PLATE.get())
            .put(MWCopperBlocks.CUT_COPPER_PRESSURE_PLATE.get(), MWCopperBlocks.EXPOSED_CUT_COPPER_PRESSURE_PLATE.get())
            .put(MWCopperBlocks.EXPOSED_CUT_COPPER_PRESSURE_PLATE.get(), MWCopperBlocks.WEATHERED_CUT_COPPER_PRESSURE_PLATE.get())
            .put(MWCopperBlocks.WEATHERED_CUT_COPPER_PRESSURE_PLATE.get(), MWCopperBlocks.OXIDIZED_CUT_COPPER_PRESSURE_PLATE.get())
            .put(MWCopperBlocks.COPPER_TRAPDOOR.get(), MWCopperBlocks.EXPOSED_COPPER_TRAPDOOR.get())
            .put(MWCopperBlocks.EXPOSED_COPPER_TRAPDOOR.get(), MWCopperBlocks.WEATHERED_COPPER_TRAPDOOR.get())
            .put(MWCopperBlocks.WEATHERED_COPPER_TRAPDOOR.get(), MWCopperBlocks.OXIDIZED_COPPER_TRAPDOOR.get())
            .put(MWCopperBlocks.COPPER_CHAIN.get(), MWCopperBlocks.EXPOSED_COPPER_CHAIN.get())
            .put(MWCopperBlocks.EXPOSED_COPPER_CHAIN.get(), MWCopperBlocks.WEATHERED_COPPER_CHAIN.get())
            .put(MWCopperBlocks.WEATHERED_COPPER_CHAIN.get(), MWCopperBlocks.OXIDIZED_COPPER_CHAIN.get())
            .put(MWCopperBlocks.COPPER_LANTERN.get(), MWCopperBlocks.EXPOSED_COPPER_LANTERN.get())
            .put(MWCopperBlocks.EXPOSED_COPPER_LANTERN.get(), MWCopperBlocks.WEATHERED_COPPER_LANTERN.get())
            .put(MWCopperBlocks.WEATHERED_COPPER_LANTERN.get(), MWCopperBlocks.OXIDIZED_COPPER_LANTERN.get())
            .put(MWCopperBlocks.COPPER_SOUL_LANTERN.get(), MWCopperBlocks.EXPOSED_COPPER_SOUL_LANTERN.get())
            .put(MWCopperBlocks.EXPOSED_COPPER_SOUL_LANTERN.get(), MWCopperBlocks.WEATHERED_COPPER_SOUL_LANTERN.get())
            .put(MWCopperBlocks.WEATHERED_COPPER_SOUL_LANTERN.get(), MWCopperBlocks.OXIDIZED_COPPER_SOUL_LANTERN.get())
            .put(MWCopperBlocks.COPPER_BARS.get(), MWCopperBlocks.EXPOSED_COPPER_BARS.get())
            .put(MWCopperBlocks.EXPOSED_COPPER_BARS.get(), MWCopperBlocks.WEATHERED_COPPER_BARS.get())
            .put(MWCopperBlocks.WEATHERED_COPPER_BARS.get(), MWCopperBlocks.OXIDIZED_COPPER_BARS.get())
            .put(MWCopperBlocks.COPPER_CAGE.get(), MWCopperBlocks.EXPOSED_COPPER_CAGE.get())
            .put(MWCopperBlocks.EXPOSED_COPPER_CAGE.get(), MWCopperBlocks.WEATHERED_COPPER_CAGE.get())
            .put(MWCopperBlocks.WEATHERED_COPPER_CAGE.get(), MWCopperBlocks.OXIDIZED_COPPER_CAGE.get())
            .put(MWCopperBlocks.COPPER_GRATE.get(), MWCopperBlocks.EXPOSED_COPPER_GRATE.get())
            .put(MWCopperBlocks.EXPOSED_COPPER_GRATE.get(), MWCopperBlocks.WEATHERED_COPPER_GRATE.get())
            .put(MWCopperBlocks.WEATHERED_COPPER_GRATE.get(), MWCopperBlocks.OXIDIZED_COPPER_GRATE.get())
            /*.put(MWCopperBlocks.WALL_HANGING_COPPER_LANTERN.get(), MWCopperBlocks.WALL_HANGING_EXPOSED_COPPER_LANTERN.get())
            .put(MWCopperBlocks.WALL_HANGING_EXPOSED_COPPER_LANTERN.get(), MWCopperBlocks.WALL_HANGING_WEATHERED_COPPER_LANTERN.get())
            .put(MWCopperBlocks.WALL_HANGING_WEATHERED_COPPER_LANTERN.get(), MWCopperBlocks.WALL_HANGING_OXIDIZED_COPPER_LANTERN.get())
            .put(MWCopperBlocks.WALL_HANGING_COPPER_SOUL_LANTERN.get(), MWCopperBlocks.WALL_HANGING_EXPOSED_COPPER_SOUL_LANTERN.get())
            .put(MWCopperBlocks.WALL_HANGING_EXPOSED_COPPER_SOUL_LANTERN.get(), MWCopperBlocks.WALL_HANGING_WEATHERED_COPPER_SOUL_LANTERN.get())
            .put(MWCopperBlocks.WALL_HANGING_WEATHERED_COPPER_SOUL_LANTERN.get(), MWCopperBlocks.WALL_HANGING_OXIDIZED_COPPER_SOUL_LANTERN.get())
            .put(MWCopperBlocks.COPPER_END_LANTERN.get(), MWCopperBlocks.EXPOSED_COPPER_END_LANTERN.get())
            .put(MWCopperBlocks.EXPOSED_COPPER_END_LANTERN.get(), MWCopperBlocks.WEATHERED_COPPER_END_LANTERN.get())
            .put(MWCopperBlocks.WEATHERED_COPPER_END_LANTERN.get(), MWCopperBlocks.OXIDIZED_COPPER_END_LANTERN.get())
            .put(MWCopperBlocks.WALL_HANGING_COPPER_END_LANTERN.get(), MWCopperBlocks.WALL_HANGING_EXPOSED_COPPER_END_LANTERN.get())
            .put(MWCopperBlocks.WALL_HANGING_EXPOSED_COPPER_END_LANTERN.get(), MWCopperBlocks.WALL_HANGING_WEATHERED_COPPER_END_LANTERN.get())
            .put(MWCopperBlocks.WALL_HANGING_WEATHERED_COPPER_END_LANTERN.get(), MWCopperBlocks.WALL_HANGING_OXIDIZED_COPPER_END_LANTERN.get())
            .put(MWCopperBlocks.COPPER_SCULK_LANTERN.get(), MWCopperBlocks.EXPOSED_COPPER_SCULK_LANTERN.get())
            .put(MWCopperBlocks.EXPOSED_COPPER_SCULK_LANTERN.get(), MWCopperBlocks.WEATHERED_COPPER_SCULK_LANTERN.get())
            .put(MWCopperBlocks.WEATHERED_COPPER_SCULK_LANTERN.get(), MWCopperBlocks.OXIDIZED_COPPER_SCULK_LANTERN.get())
            .put(MWCopperBlocks.WALL_HANGING_COPPER_SCULK_LANTERN.get(), MWCopperBlocks.WALL_HANGING_EXPOSED_COPPER_SCULK_LANTERN.get())
            .put(MWCopperBlocks.WALL_HANGING_EXPOSED_COPPER_SCULK_LANTERN.get(), MWCopperBlocks.WALL_HANGING_WEATHERED_COPPER_SCULK_LANTERN.get())
            .put(MWCopperBlocks.WALL_HANGING_WEATHERED_COPPER_SCULK_LANTERN.get(), MWCopperBlocks.WALL_HANGING_OXIDIZED_COPPER_SCULK_LANTERN.get())*/
    .build());

    /**
     * {@link Supplier<BiMap> Unoxidized Blocks by Block}
     */
    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> NEXT_BY_BLOCK.get().inverse());

    /**
     * Get the previous {@link Block Weathering Block} based on the {@link Block current Block}
     *
     * @param block {@link Block The current Block}
     * @return {@link Block The previous Weathering Block}
     */
    static Optional<Block> getPrevious(final Block block) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(block));
    }

    /**
     * Get the previous {@link BlockState Weathering Block State} based on the {@link BlockState current Block State}
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link BlockState The previous Weathering Block State}
     */
    static Optional<BlockState> getPrevious(final BlockState blockState) {
        return getPrevious(blockState.getBlock()).map(block -> block.withPropertiesOf(IMWChangeOverTimeBlock.getAdjustedBlockState(blockState)));
    }

    /**
     * Get the first {@link Block Weathering Block} based on the {@link Block current Block}
     *
     * @param block {@link Block The current Block}
     * @return {@link Block The first Weathering Block}
     */
    static Block getFirst(final Block block) {
        Block firstBlock = block;
        for(Block previousBlock = PREVIOUS_BY_BLOCK.get().get(block); previousBlock != null; previousBlock = PREVIOUS_BY_BLOCK.get().get(previousBlock)) {
            firstBlock = previousBlock;
        }
        return firstBlock;
    }

    /**
     * Get the first {@link BlockState Weathering Block State} based on the {@link BlockState current Block State}
     *
     * @param blockState {@link BlockState The current Weathering Block State}
     * @return {@link BlockState The first Weathering Block State}
     */
    static BlockState getFirst(final BlockState blockState) {
        return getFirst(blockState.getBlock()).withPropertiesOf(IMWChangeOverTimeBlock.getAdjustedBlockState(blockState));
    }

    /**
     * Get the next {@link Block Weathering Block} based on the {@link Block current Block}
     *
     * @param block {@link Block The current Weathering Block}
     * @return {@link Block The next Weathering Block}
     */
    static Optional<Block> getNext(final Block block) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(block));
    }

    /**
     * Get the next {@link BlockState Weathering Block State} based on the {@link BlockState current Block State}
     *
     * @param blockState {@link BlockState The current Weathering Block State}
     * @return {@link Block The next Weathering Block State}
     */
    default @NotNull Optional<BlockState> getNext(final BlockState blockState) {
        return getNext(blockState.getBlock()).map(block -> block.withPropertiesOf(IMWChangeOverTimeBlock.getAdjustedBlockState(blockState)));
    }

    /**
     * Randomly ticks the Block
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    static void randomTick(final ChangeOverTimeBlock<WeatheringCopper.WeatherState> block, final BlockState blockState, final ServerLevel level, final BlockPos blockPos, final RandomSource randomSource) {
        if(!isWaxed(blockState)) {
            block.onRandomTick(blockState, level, blockPos, randomSource);
        }
    }

    /**
     * Check if the Block should randomly ticking
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if the Block is not waxed and fully oxidized}
     */
    static boolean isRandomlyTicking(final BlockState blockState) {
        return !isWaxed(blockState) && getNext(blockState.getBlock()).isPresent();
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
        if(!isWaxed(blockState)) {
            return scrapeWeatherState(blockState, context, toolAction, isClient);
        }
        return IMWWaxableBlock.getToolModifiedState(blockState, context, toolAction, isClient);
    }

    /**
     * Set the previous {@link WeatheringCopper.WeatherState Weather State} on right click with an {@link AxeItem Axe}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param context {@link UseOnContext The Item Use Context}
     * @param toolAction {@link ToolAction The tool action}
     * @param isClient {@link Boolean If the scraping only happened on the Client}
     * @return {@link BlockState The modified Block State}
     */
    @Nullable
    static BlockState scrapeWeatherState(final BlockState blockState, final UseOnContext context, final ToolAction toolAction, final boolean isClient) {
        final ItemStack itemStack = context.getItemInHand();
        if(itemStack.getItem() instanceof AxeItem && toolAction.equals(ToolActions.AXE_SCRAPE)) {
            final Optional<BlockState> previousBlockState = getPrevious(blockState);
            if(previousBlockState.isPresent()) {
                context.getLevel().levelEvent(context.getPlayer(), 3005, context.getClickedPos(), 0);
                return previousBlockState.get();
            }
        }
        return null;
    }

    /**
     * Check if a {@link Block Block} is waxed
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if the Block is waxed}
     */
    private static boolean isWaxed(final BlockState blockState) {
        return IMWWaxableBlock.isWaxed(blockState);
    }

    /**
     * Clear some oxidization on struck by a {@link LightningBolt Lightning Bolt}
     *
     * @param blockState {@link Block The current Block State}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     */
    static void lightningStrike(final BlockState blockState, final Level level, final BlockPos blockPos) {
        if(!IMWWaxableBlock.isWaxed(blockState)) {
            level.setBlockAndUpdate(blockPos, getFirst(blockState));
            final BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
            final int blocks = level.random.nextInt(3) + 3;

            for(int j = 0; j < blocks; ++j) {
                mutableBlockPos.set(blockPos);
                for(int i = 0; i < (level.random.nextInt(8) + 1); ++i) {
                    final Optional<BlockPos> optionalCopperPos = randomStepCleaningCopper(level, mutableBlockPos);
                    if (optionalCopperPos.isEmpty()) {
                        break;
                    }
                    mutableBlockPos.set(optionalCopperPos.get());
                }
            }
        }
    }

    /**
     * Get a random block in a range and if is an {@link IMWWeatheringBlock oxidizable block}
     * clear a {@link WeatheringCopper.WeatherState Weather State}
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Optional<BlockPos> The cleaned Block Pos}
     */
    private static Optional<BlockPos> randomStepCleaningCopper(final Level level, final BlockPos blockPos) {
        for(BlockPos blockpos : BlockPos.randomInCube(level.random, 10, blockPos, 1)) {
            final BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.getBlock() instanceof IMWWeatheringBlock) {
                getPrevious(blockstate).ifPresent(blockState -> level.setBlockAndUpdate(blockpos, blockState));
                level.levelEvent(3002, blockpos, -1);
                return Optional.of(blockpos);
            }
        }
        return Optional.empty();
    }

}