package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWItems;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} {@link Block Ethereal Rune Block}
 */
public class EtherealRuneBlock extends Block {

    /**
     * {@link Boolean The Block Lit property}
     */
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     */
    public EtherealRuneBlock() {
        super(PropertyHelper.block(MapColor.COLOR_YELLOW, 55F, 1200F, true, SoundType.SCULK_SHRIEKER)
                .noLootTable()
                .lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 10 : 0)
        );
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, Boolean.FALSE));
    }

    /**
     * Create the {@link StateDefinition Block State definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The Block State builder}
     */
    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(LIT);
    }

    /**
     * Interact with the Block
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param player {@link Player The player who interacted with the Block}
     * @param hand {@link InteractionHand The hand the player has interacted with}
     * @param hitResult {@link BlockHitResult The hit result for the block interaction}
     * @return {@link InteractionResult The interaction result based on the Player's held Item}
     */
    @Override
    public @NotNull InteractionResult use(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull Player player, final @NotNull InteractionHand hand, final @NotNull BlockHitResult hitResult) {
        final ItemStack itemStack = player.getItemInHand(hand);
        if(itemStack.is(Items.ECHO_SHARD) && !blockState.getValue(LIT)) {
            level.setBlock(blockPos, blockState.setValue(LIT, Boolean.TRUE), 2);
            spawnParticles(level, blockPos);
            ItemHelper.hurt(itemStack, player);
            if (!level.isClientSide) {
                final Vec3 dropPosition = Vec3.atLowerCornerWithOffset(blockPos, 0.5D, 1.01D, 0.5D).offsetRandom(level.random, 0.1F);
                final ItemEntity echoingChargeFragment = new ItemEntity(level, dropPosition.x(), dropPosition.y(), dropPosition.z(), MWItems.ECHOING_CHARGE_FRAGMENT.get().getDefaultInstance());
                echoingChargeFragment.setDefaultPickUpDelay();
                level.addFreshEntity(echoingChargeFragment);
            }
            player.playSound(SoundEvents.SCULK_BLOCK_CHARGE);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    /**
     * Spawn the Activation Particles
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     */
    private static void spawnParticles(final Level level, final BlockPos blockPos) {
        final double particleOffset = 1.0625D;
        final RandomSource random = level.random;

        for(Direction direction : Direction.values()) {
            final BlockPos relativePos = blockPos.relative(direction);
            if (!level.getBlockState(relativePos).isSolidRender(level, relativePos)) {
                final Direction.Axis axis = direction.getAxis();
                final double x = axis.equals(Direction.Axis.X) ? particleOffset * (double)direction.getStepX() : (double)random.nextFloat();
                final double y = axis.equals(Direction.Axis.Y) ? particleOffset * (double)direction.getStepY() : (double)random.nextFloat();
                final double z = axis.equals(Direction.Axis.Z) ? particleOffset * (double)direction.getStepZ() : (double)random.nextFloat();
                level.addParticle(ParticleTypes.SCULK_CHARGE_POP, (double)blockPos.getX() + x, (double)blockPos.getY() + y, (double)blockPos.getZ() + z, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    /**
     * {@link MineWorld MineWorld} {@link EtherealRuneBlock Ethereal Rune} Types
     */
    public enum Types {
        ALPHA,
        BETA,
        GAMMA,
        DELTA,
        OMEGA
    }

}