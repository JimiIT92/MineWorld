package org.mineworld.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
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
 * Implementation class for a {@link MineWorld MineWorld} log block
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
     * Constructor. Set the log properties
     *
     * @param color {@link MapColor The log color on maps}
     */
    public MWLogBlock(final MapColor color) {
        this(color, color);
    }

    /**
     * Constructor. Set the log properties
     *
     * @param topColor {@link MapColor The log top color on maps}
     * @param sideColor {@link MapColor The log side color on maps}
     */
    public MWLogBlock(final MapColor topColor, final MapColor sideColor) {
        super(BlockBehaviour.Properties.of()
                .mapColor(state -> Direction.Axis.Y.equals(state.getValue(RotatedPillarBlock.AXIS)) ? topColor : sideColor)
                .strength(2.0F).sound(SoundType.WOOD)
        );
    }

    /**
     * Makes the block able to catch fire
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link Level The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Boolean True}
     */
    @Override
    public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return true;
    }

    /**
     * Get the block {@link Integer flammability value}
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link Level The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 20}
     */
    @Override
    public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return 5;
    }

    /**
     * Get the block {@link Integer fire spread speed value}
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link Level The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 5}
     */
    @Override
    public int getFireSpreadSpeed(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return 5;
    }

    /**
     * Sets the {@link RotatedPillarBlock stripped block} variant on
     * right click with an {@link AxeItem axe}
     *
     * @param blockState {@link BlockState The current block state}
     * @param context {@link UseOnContext Use Context}
     * @param toolAction {@link ToolAction Tool action}
     * @param isClientSide {@link Boolean If the code is executing client side}
     * @return {@link BlockState The modified block state}
     */
    @Nullable
    @Override
    public BlockState getToolModifiedState(final BlockState blockState, final UseOnContext context, final ToolAction toolAction, final boolean isClientSide) {
        ItemStack stack = context.getItemInHand();
        if(stack.getItem() instanceof AxeItem && toolAction.equals(ToolActions.AXE_STRIP)) {
            Optional<BlockState> optionalStrippedState = getStripped(blockState);
            if(optionalStrippedState.isPresent()) {
                return optionalStrippedState.get().setValue(AXIS, blockState.getValue(AXIS));
            }
        }
        return super.getToolModifiedState(blockState, context, toolAction, isClientSide);
    }

    /**
     * Get the {@link BlockState stripped log block state}
     * based on the {@link BlockState current block state}
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link Optional<BlockState> The hollow log block state, if any}
     */
    public static Optional<BlockState> getStripped(final BlockState blockState) {
        return Optional.ofNullable(STRIPPABLES.get().get(blockState.getBlock())).map(block -> block.withPropertiesOf(blockState));
    }

}