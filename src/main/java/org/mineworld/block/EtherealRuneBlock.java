package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWItems;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.PlayerHelper;
import org.mineworld.helper.PropertyHelper;

/**
 * Class for an Ethereal Rune Block
 */
public class EtherealRuneBlock extends Block {

    /**
     * The {@link BooleanProperty Lit property}
     */
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    /**
     * Constructor. Set the block properties
     */
    public EtherealRuneBlock() {
        super(PropertyHelper.basicBlockProperties(MapColor.COLOR_YELLOW, 55F, 1200F, true, SoundType.SCULK_SHRIEKER)
                .noLootTable()
                .lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 10 : 0));
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, Boolean.FALSE));
    }

    /**
     * Create the {@link BlockState Block State} definition
     *
     * @param stateBuilder {@link StateDefinition.Builder The Block State builder}
     */
    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(LIT);
    }

    /**
     * Lit the Ethereal Rune when the {@link Player Player} right clicks
     * it with the {@link Item Sculk Heart}
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
        if(itemstack.is(Items.ECHO_SHARD) && !state.getValue(LIT)) {
            level.setBlock(pos, state.setValue(LIT, Boolean.TRUE), 2);
            ItemHelper.hurt(itemstack, player);
            PlayerHelper.playSound(player, SoundEvents.SCULK_CLICKING_STOP);

            final double searchRange = 5.0D;
            final double searchYRange = searchRange * 2;

            final AABB searchBox = new AABB(
                    pos.getX() - searchRange, pos.getY() - searchYRange, pos.getZ() - searchRange,
                    pos.getX() + searchRange, pos.getY() + searchYRange, pos.getZ() + searchRange);

            final int litRunes = (int) level.getBlockStates(searchBox).filter(blockState -> blockState.is(this) && blockState.hasProperty(LIT) && blockState.getValue(LIT)).count();

            if(litRunes >= 5) {
                player.addItem(ItemHelper.getDefaultStack(MWItems.ECHOING_CHARGE_FRAGMENT));
            }
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}