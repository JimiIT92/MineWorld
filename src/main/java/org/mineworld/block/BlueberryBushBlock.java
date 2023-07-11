package org.mineworld.block;

import net.minecraft.core.BlockPos;
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
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.PropertyHelper;

/**
 * Implementation class for a {@link SweetBerryBushBlock blueberry bush block}
 */
public class BlueberryBushBlock extends SweetBerryBushBlock {

    /**
     * Constructor. Set the block properties
     */
    public BlueberryBushBlock() {
        super(PropertyHelper.copyFromBlock(Blocks.SWEET_BERRY_BUSH));
    }

    /**
     * Get the {@link ItemStack block item stack}
     *
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     * @return {@link ItemStack The block item stack}
     */
    public @NotNull ItemStack getCloneItemStack(@NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return ItemHelper.getDefaultStack(MWItems.BLUEBERRIES);
    }

    /**
     * Harvest blueberries on block right click or when bonemealed
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param player {@link Player The player interacting with the block}
     * @param hand {@link InteractionHand The hand the player is interacting with}
     * @param blockHitResult {@link BlockHitResult The block hit result}
     * @return {@link InteractionResult The interaction result}
     */
    public @NotNull InteractionResult use(BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult blockHitResult) {
        int age = blockState.getValue(AGE);
        boolean isMaxAge = age == 3;
        if (!isMaxAge && player.getItemInHand(hand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        }
        if (age > 1) {
            popResource(level, blockPos, new ItemStack(MWItems.BLUEBERRIES.get(), 1 + level.random.nextInt(2) + (isMaxAge ? 1 : 0)));
            level.playSound(null, blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            BlockState harvestedBlockState = blockState.setValue(AGE, 1);
            level.setBlock(blockPos, harvestedBlockState, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, harvestedBlockState));
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.use(blockState, level, blockPos, player, hand, blockHitResult);
    }

    /**
     * Damage an entity if is inside the bush
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param entity {@link Entity The entity inside the bush}
     */
    public void entityInside(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Entity entity) {
        if (entity instanceof LivingEntity && !entity.getType().equals(EntityType.FOX) && !entity.getType().equals(EntityType.BEE)) {
            entity.makeStuckInBlock(blockState, new Vec3(0.8F, 0.75D, 0.8F));
            if (!level.isClientSide && blockState.getValue(AGE) > 0 && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
                double deltaX = Math.abs(entity.getX() - entity.xOld);
                double deltaZ = Math.abs(entity.getZ() - entity.zOld);
                if (deltaX >= (double)0.003F || deltaZ >= (double)0.003F) {
                    entity.hurt(DamageHelper.source(level, MWDamageTypes.BLUEBERRY_BUSH), 1.0F);
                }
            }
        }
    }

}