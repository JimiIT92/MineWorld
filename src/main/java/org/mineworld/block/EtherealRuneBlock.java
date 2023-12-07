package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
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
            spawnParticles(level, pos);
            ItemHelper.hurt(itemstack, player);
            if (!level.isClientSide) {
                final Vec3 vec3 = Vec3.atLowerCornerWithOffset(pos, 0.5D, 1.01D, 0.5D).offsetRandom(level.random, 0.1F);
                final ItemEntity echoingChargeFragment = new ItemEntity(level, vec3.x(), vec3.y(), vec3.z(), ItemHelper.getDefaultStack(MWItems.ECHOING_CHARGE_FRAGMENT));
                echoingChargeFragment.setDefaultPickUpDelay();
                level.addFreshEntity(echoingChargeFragment);
            }
            PlayerHelper.playSound(player, SoundEvents.SCULK_BLOCK_CHARGE);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    /**
     * Spawn the activation particles
     *
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current BlockPos}
     */
    private static void spawnParticles(final Level level, final BlockPos pos) {
        final double multiplier = 0.5625D;
        final RandomSource random = level.random;

        for(Direction direction : Direction.values()) {
            final BlockPos relativePos = pos.relative(direction);
            if (!level.getBlockState(relativePos).isSolidRender(level, relativePos)) {
                final Direction.Axis axis = direction.getAxis();
                final double x = axis.equals(Direction.Axis.X) ? 0.5D + 0.5625D * (double)direction.getStepX() : (double)random.nextFloat();
                final double y = axis.equals(Direction.Axis.Y) ? 0.5D + 0.5625D * (double)direction.getStepY() : (double)random.nextFloat();
                final double z = axis.equals(Direction.Axis.Z) ? 0.5D + 0.5625D * (double)direction.getStepZ() : (double)random.nextFloat();
                level.addParticle(ParticleTypes.SCULK_CHARGE_POP, (double)pos.getX() + x, (double)pos.getY() + y, (double)pos.getZ() + z, 0.0D, 0.0D, 0.0D);
            }
        }

    }
}