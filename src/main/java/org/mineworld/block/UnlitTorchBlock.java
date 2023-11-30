package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.PlayerHelper;

/**
 * Implementation class for an unlit {@link TorchBlock Torch Block}
 */
public class UnlitTorchBlock extends Block {

    /**
     * {@link Boolean If the torch is a soul torch}
     */
    protected final boolean isSoulTorch;


    /**
     * Constructor. Set the block properties
     *
     * @param isSoulTorch {@link Boolean If the torch is a soul torch}
     */
    public UnlitTorchBlock(final boolean isSoulTorch) {
        this(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY), isSoulTorch);
    }

    /**
     * Constructor. Set the block properties
     *
     * @param properties {@link BlockBehaviour.Properties The block properties}
     * @param isSoulTorch {@link Boolean If the torch is a soul torch}
     */
    protected UnlitTorchBlock(final BlockBehaviour.Properties properties, final boolean isSoulTorch) {
        super(properties);
        this.isSoulTorch = isSoulTorch;
    }

    /**
     * Get the {@link VoxelShape Block Bounding Box}
     *
     * @param state {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param pos {@link BlockPos The current Block Pos}
     * @param context {@link CollisionContext The collision context}
     * @return {@link VoxelShape The Block Bounding Box}
     */
    @Override
    public @NotNull VoxelShape getShape(final @NotNull BlockState state, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos pos, final @NotNull CollisionContext context) {
        return Block.box(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);
    }

    /**
     * Update the {@link BlockState Block State} based on neighbor updates
     *
     * @param state {@link BlockState The current Block State}
     * @param direction {@link Direction The update direction}
     * @param neighborBlockState {@link BlockState The neighbor Block State}
     * @param level {@link LevelAccessor The level reference}
     * @param pos {@link BlockPos The current Block Pos}
     * @param neighborPos {@link BlockPos The neighbor Block Pos}
     * @return {@link BlockState The updated Block State}
     */
    @Override
    public @NotNull BlockState updateShape(final @NotNull BlockState state, final @NotNull Direction direction, final @NotNull BlockState neighborBlockState, final @NotNull LevelAccessor level, final @NotNull BlockPos pos, final @NotNull BlockPos neighborPos) {
        return direction == Direction.DOWN && !this.canSurvive(state, level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborBlockState, level, pos, neighborPos);
    }

    /**
     * Check if the Block can survive at the given {@link BlockPos location}
     *
     * @param state {@link BlockState The current Block State}
     * @param level {@link LevelReader The level reference}
     * @param pos {@link BlockPos The current Block Pos}
     * @return {@link Boolean True if the Block can survive}
     */
    @Override
    public boolean canSurvive(final @NotNull BlockState state, final @NotNull LevelReader level, final BlockPos pos) {
        return canSupportCenter(level, pos.below(), Direction.UP);
    }

    /**
     * Get the corresponding {@link Block Torch Block}
     *
     * @return {@link Block The Torch Block}
     */
    protected Block getTorchBlock() {
        return isSoulTorch ? Blocks.SOUL_TORCH : Blocks.TORCH;
    }

    /**
     * Lit the torch when the {@link Player Player} right clicks
     * it with a {@link FlintAndSteelItem flint and steel}
     *
     * @param state {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current Block Pos}
     * @param player {@link Player The player that interacted with the Block}
     * @param hand {@link InteractionHand The hand used to interact with the Block}
     * @param hitResult {@link BlockHitResult The Block hit result}
     * @return {@link InteractionResult The interaction result}
     */
    @Override
    public @NotNull InteractionResult use(final @NotNull BlockState state, final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull Player player, final @NotNull InteractionHand hand, final @NotNull BlockHitResult hitResult) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(itemstack.getItem() instanceof FlintAndSteelItem) {
            level.setBlock(pos, getTorchBlock().withPropertiesOf(state), 2);
            ItemHelper.hurt(itemstack, player);
            PlayerHelper.playSound(player, SoundEvents.FLINTANDSTEEL_USE);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

}