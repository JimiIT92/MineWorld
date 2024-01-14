package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWDamageTypes;
import org.mineworld.core.MWItems;
import org.mineworld.helper.DamageHelper;
import org.mineworld.helper.PropertyHelper;

/**
 * Implementation class for a {@link SweetBerryBushBlock Blueberry Bush Block}
 */
public class BlueberryBushBlock extends SweetBerryBushBlock {

    /**
     * Constructor. Set the Block Properties
     */
    public BlueberryBushBlock() {
        super(PropertyHelper.copy(Blocks.SWEET_BERRY_BUSH));
    }

    /**
     * Get the {@link ItemStack Item Stack} for the inventory when the {@link Player player} middle mouse click the block
     *
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link ItemStack The Block Item Stack}
     */
    public @NotNull ItemStack getCloneItemStack(final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return MWItems.BLUEBERRIES.get().getDefaultInstance();
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
    public @NotNull InteractionResult use(final BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull Player player, final @NotNull InteractionHand hand, final @NotNull BlockHitResult hitResult) {
        final int age = blockState.getValue(AGE);
        final boolean isMaxAge = age == 3;
        if (!isMaxAge && player.getItemInHand(hand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        }
        if (age > 1) {
            popResource(level, blockPos, new ItemStack(MWItems.BLUEBERRIES.get(), 1 + level.random.nextInt(2) + (isMaxAge ? 1 : 0)));
            level.playSound(null, blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            final BlockState harvestedBlockState = blockState.setValue(AGE, 1);
            level.setBlock(blockPos, harvestedBlockState, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, harvestedBlockState));
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.use(blockState, level, blockPos, player, hand, hitResult);
    }

    /**
     * Damage an {@link Entity Entity} if is inside the Block
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param entity {@link Entity The Entity inside the Block}
     */
    public void entityInside(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull Entity entity) {
        if (entity instanceof LivingEntity && !entity.getType().equals(EntityType.FOX) && !entity.getType().equals(EntityType.BEE)) {
            entity.makeStuckInBlock(blockState, new Vec3(0.8F, 0.75D, 0.8F));
            if (!level.isClientSide && blockState.getValue(AGE) > 0 && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
                final double deltaX = Math.abs(entity.getX() - entity.xOld);
                final double deltaZ = Math.abs(entity.getZ() - entity.zOld);
                if (deltaX >= (double)0.003F || deltaZ >= (double)0.003F) {
                    entity.hurt(DamageHelper.source(level, MWDamageTypes.BLUEBERRY_BUSH), 1.0F);
                }
            }
        }
    }

}