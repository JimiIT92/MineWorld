package org.mineworld.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link RotatedPillarBlock Log Block}
 */
public class MWLogBlock extends RotatedPillarBlock {

    /**
     * {@link Supplier<Map> Strippable blocks}
     */
    public static final Supplier<Map<Block, Block>> STRIPPABLES = Suppliers.memoize(() -> ImmutableMap.<Block, Block>builder()
            .put(MWBlocks.APPLE_LOG.get(), MWBlocks.STRIPPED_APPLE_LOG.get())
            .put(MWBlocks.APPLE_WOOD.get(), MWBlocks.STRIPPED_APPLE_WOOD.get())
            .put(MWBlocks.PALM_LOG.get(), MWBlocks.STRIPPED_PALM_LOG.get())
            .put(MWBlocks.PALM_WOOD.get(), MWBlocks.STRIPPED_PALM_WOOD.get())
            .put(MWBlocks.DEAD_LOG.get(), MWBlocks.STRIPPED_DEAD_LOG.get())
            .put(MWBlocks.DEAD_WOOD.get(), MWBlocks.STRIPPED_DEAD_WOOD.get())
            .put(MWBlocks.SCULK_LOG.get(), MWBlocks.STRIPPED_SCULK_LOG.get())
            .put(MWBlocks.SCULK_WOOD.get(), MWBlocks.STRIPPED_SCULK_WOOD.get())
    .build());

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param color {@link MapColor The Block Color on maps}
     */
    public MWLogBlock(final MapColor color) {
        this(color, color);
    }

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties} and {@link SoundType Sound}
     *
     * @param color {@link MapColor The Block Color on maps}
     * @param soundType {@link SoundType The Block Sound}
     */
    public MWLogBlock(final MapColor color, final SoundType soundType) {
        this(color, color, soundType);
    }

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param topColor {@link MapColor The Log Top Color on maps}
     * @param sideColor {@link MapColor The Log Side Color on maps}
     */
    public MWLogBlock(final MapColor topColor, final MapColor sideColor) {
        this(topColor, sideColor, SoundType.WOOD);
    }

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties} and {@link SoundType Sound}
     *
     * @param topColor {@link MapColor The Log Top Color on maps}
     * @param sideColor {@link MapColor The Log Side Color on maps}
     * @param soundType {@link SoundType The Block Sound}
     */
    public MWLogBlock(final MapColor topColor, final MapColor sideColor, final SoundType soundType) {
        super(BlockBehaviour.Properties.of()
                .mapColor(state -> Direction.Axis.Y.equals(state.getValue(RotatedPillarBlock.AXIS)) ? topColor : sideColor)
                .strength(2.0F).sound(soundType)
        );
    }

    /**
     * Check if the Block can catch fire
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Boolean#TRUE True}
     */
    @Override
    public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return true;
    }

    /**
     * Get the Block {@link Integer flammability value}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 5}
     */
    @Override
    public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return 5;
    }

    /**
     * Get the Block {@link Integer fire spread speed value}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 5}
     */
    @Override
    public int getFireSpreadSpeed(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return 5;
    }

    /**
     * Get the {@link BlockState modified Block State} after interacting with a tool
     *
     * @param blockState {@link BlockState The current Block State}
     * @param context {@link UseOnContext The Item Use Context}
     * @param toolAction {@link ToolAction The tool action}
     * @param isClient {@link Boolean If the action only happened on the Client}
     * @return {@link BlockState The modified Block State}
     */
    @Nullable
    @Override
    public BlockState getToolModifiedState(final BlockState blockState, final UseOnContext context, final ToolAction toolAction, final boolean isClient) {
        ItemStack stack = context.getItemInHand();
        if(stack.getItem() instanceof AxeItem && toolAction.equals(ToolActions.AXE_STRIP)) {
            Optional<BlockState> optionalStrippedState = getStripped(blockState);
            if(optionalStrippedState.isPresent()) {
                return optionalStrippedState.get().setValue(AXIS, blockState.getValue(AXIS));
            }
        }
        return super.getToolModifiedState(blockState, context, toolAction, isClient);
    }

    /**
     * Get the {@link BlockState Stripped Log Block State}
     * based on the {@link BlockState current Block State}
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Optional<BlockState> The Stripped Log Block State, if any}
     */
    public static Optional<BlockState> getStripped(final BlockState blockState) {
        return Optional.ofNullable(STRIPPABLES.get().get(blockState.getBlock())).map(block -> block.withPropertiesOf(blockState));
    }

}