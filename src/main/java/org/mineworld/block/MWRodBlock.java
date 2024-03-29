package org.mineworld.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RodBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWItems;
import org.mineworld.helper.PropertyHelper;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link RodBlock Rod Block}
 */
public class MWRodBlock extends RodBlock {

    /**
     * {@link MapCodec The Block Codec}
     */
    public static final MapCodec<MWRodBlock> CODEC = simpleCodec(MWRodBlock::new);
    /**
     * {@link Supplier<BiMap> Rod Blocks by Item}
     */
    private static final Supplier<BiMap<Item, Block>> RODS = Suppliers.memoize(() -> ImmutableBiMap.<Item, Block>builder()
            .put(Items.STICK, MWBlocks.STICK_ROD_BLOCK.get())
            .put(Items.BONE, MWBlocks.BONE_ROD_BLOCK.get())
            .put(MWItems.WITHER_BONE.get(), MWBlocks.WITHER_BONE_ROD_BLOCK.get())
            .put(Items.BLAZE_ROD, MWBlocks.BLAZE_ROD_BLOCK.get())
    .build());

    /**
     * {@link Supplier<BiMap> Items by Rod Blocks}
     */
    private static final Supplier<BiMap<Block, Item>> ITEM_BY_ROD = Suppliers.memoize(() -> RODS.get().inverse());

    /**
     * {@link BooleanProperty The Block Waterlogged property}
     */
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param color {@link MapColor The Block Color on maps}
     * @param sound {@link SoundType The Block Sound}
     * @param lightLevel {@link Integer The Block Light Level}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public MWRodBlock(final MapColor color, final SoundType sound, final int lightLevel, final FeatureFlag... featureFlags) {
        this(PropertyHelper.block(color, 1.5F, 3.0F, false, sound, featureFlags).lightLevel(state -> lightLevel).noOcclusion().forceSolidOn());
    }

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param properties The {@link BlockBehaviour.Properties Block Properties}
     */
    public MWRodBlock(final BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP).setValue(WATERLOGGED, Boolean.FALSE));
    }

    /**
     * Get the {@link BlockState Block State} after the block has been placed
     *
     * @param placeContext {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed Block State}
     */
    public BlockState getStateForPlacement(final BlockPlaceContext placeContext) {
        final Direction direction = placeContext.getClickedFace();
        final BlockState blockState = placeContext.getLevel().getBlockState(placeContext.getClickedPos().relative(direction.getOpposite()));
        final boolean isWaterlogged = Fluids.WATER.equals(placeContext.getLevel().getFluidState(placeContext.getClickedPos()).getType());
        return blockState.is(this) && blockState.getValue(FACING) == direction ?
                this.defaultBlockState().setValue(FACING, direction.getOpposite()).setValue(WATERLOGGED, isWaterlogged) :
                this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, isWaterlogged);
    }

    /**
     * Update the {@link BlockState Block State} on neighbor changes
     *
     * @param blockState {@link BlockState The current Block State}
     * @param direction {@link Direction The direction the changes are coming}
     * @param neighborBlockState {@link BlockState The neighbor Block State}
     * @param levelAccessor {@link LevelAccessor The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param neighborBlockPos {@link BlockPos The neighbor Block Pos}
     * @return {@link BlockState The updated Block State}
     */
    public @NotNull BlockState updateShape(final BlockState blockState, final @NotNull Direction direction, final @NotNull BlockState neighborBlockState, final @NotNull LevelAccessor levelAccessor, final @NotNull BlockPos blockPos, final @NotNull BlockPos neighborBlockPos) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return super.updateShape(blockState, direction, neighborBlockState, levelAccessor, blockPos, neighborBlockPos);
    }

    /**
     * Get the {@link FluidState Block Fluid State}
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Fluids#WATER Water if is Waterlogged}
     */
    public @NotNull FluidState getFluidState(final BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    /**
     * Create the {@link StateDefinition Block State definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The Block State builder}
     */
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, WATERLOGGED);
    }

    /**
     * Get a {@link MWRodBlock Rod Block} for the given {@link Item Item}
     *
     * @param item {@link Item The Item to get the Rod Block for}
     * @return {@link MWRodBlock The Rod Block}
     */
    public static Block getRodBlockFor(final Item item) {
        final Optional<Block> rod = Optional.ofNullable(RODS.get().get(item));
        return rod.orElse(null);
    }

    /**
     * Get an {@link Item Item} for the given {@link Block Rod Block}
     *
     * @param block {@link BlockPos The Rod Block to get the Item for}
     * @return {@link Item The Item}
     */
    public static Item getItemForRod(final Block block) {
        final Optional<Item> rod = Optional.ofNullable(ITEM_BY_ROD.get().get(block));
        return rod.orElse(null);
    }

    /**
     * Get the {@link ItemStack Item Stack} for the inventory when the {@link Player player} middle mouse click the block
     *
     * @param blockState {@link BlockState The current Block State}
     * @param hitResult {@link HitResult The hit result}
     * @param levelReader {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param player {@link Player The player who is middle mouse clicking}
     * @return {@link ItemStack The Block Item Stack}
     */
    @Override
    public ItemStack getCloneItemStack(final BlockState blockState, final HitResult hitResult, final LevelReader levelReader, final BlockPos blockPos, final Player player) {
        final Item item = getItemForRod(this);
        return item == null ? ItemStack.EMPTY : item.getDefaultInstance();
    }

    /**
     * Get the {@link MapCodec Block Codec}
     *
     * @return {@link MapCodec The Block Codec}
     */
    @Override
    protected @NotNull MapCodec<? extends RodBlock> codec() {
        return CODEC;
    }

}