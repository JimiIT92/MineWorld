package org.mineworld.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.entity.MWPrimedTnt;

/**
 * {@link MineWorld MineWorld} {@link DispenseItemBehavior dispense item behaviors}
 */
public final class MWDispenseBehaviors {

    //#region Dispense behaviors

    private static final DispenseItemBehavior HORSE_ARMOR_DISPENSE_ITEM_BEHAVIOR = new OptionalDispenseItemBehavior() {
        /**
         * Equiq the {@link HorseArmorItem horse armor} if there's a horse in front of the dispenser
         *
         * @param blockSource  {@link BlockSource The block source reference}
         * @param itemStack The {@link ItemStack The item stack inside the dispenser}
         * @return {@link ItemStack The shrinked item stack if the armor has been equipped}
         */
        @Override
        protected @NotNull ItemStack execute(final @NotNull BlockSource blockSource, @NotNull ItemStack itemStack) {
            final BlockPos blockpos = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));
            for(AbstractHorse horse : blockSource.getLevel().getEntitiesOfClass(AbstractHorse.class, new AABB(blockpos), entity -> entity.isAlive() && entity.canWearArmor())) {
                if (horse.isArmor(itemStack) && !horse.isWearingArmor() && horse.isTamed()) {
                    horse.getSlot(401).set(itemStack.split(1));
                    this.setSuccess(true);
                    return itemStack;
                }
            }
            return super.execute(blockSource, itemStack);
        }
    };

    //#endregion

    /**
     * Get the {@link DispenseItemBehavior tnt dispense behavior} based on the {@link MWPrimedTnt.Type primed tnt type}
     *
     * @param type {@link MWPrimedTnt.Type The primed tnt type}
     * @return {@link DispenseItemBehavior The tnt dispense behavior}
     */
    private static DispenseItemBehavior getTntDispenseItemBehavior(final MWPrimedTnt.Type type) {
        return new DefaultDispenseItemBehavior() {
            /**
             * Dispense the {@link PrimedTnt tnt} when activated from a dispenser
             *
             * @param blockSource {@link BlockSource The block source reference}
             * @param itemStack   {@link ItemStack The item stack inside the dispenser}
             * @return {@link ItemStack The shrinked item stack if the tnt has been primed}
             */
            @Override
            protected @NotNull ItemStack execute(final @NotNull BlockSource blockSource, @NotNull ItemStack itemStack) {
                Level level = blockSource.getLevel();
                BlockPos blockpos = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));
                MWPrimedTnt primedtnt = new MWPrimedTnt(level, (double) blockpos.getX() + 0.5D, blockpos.getY(), (double) blockpos.getZ() + 0.5D, null, type);
                level.addFreshEntity(primedtnt);
                level.playSound(null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, GameEvent.ENTITY_PLACE, blockpos);
                itemStack.shrink(1);
                return itemStack;
            }
        };
    }

    /**
     * Register the {@link DispenseItemBehavior dispense item behaviors}
     */
    public static void registerDispenseBehaviors() {
        DispenserBlock.registerBehavior(MWBlocks.DISGUISED_GRASS_TNT.get(), getTntDispenseItemBehavior(MWPrimedTnt.Type.DISGUISED_GRASS));
        DispenserBlock.registerBehavior(MWBlocks.DISGUISED_DIRT_TNT.get(), getTntDispenseItemBehavior(MWPrimedTnt.Type.DISGUISED_DIRT));
        DispenserBlock.registerBehavior(MWBlocks.DISGUISED_SAND_TNT.get(), getTntDispenseItemBehavior(MWPrimedTnt.Type.DISGUISED_SAND));
        DispenserBlock.registerBehavior(MWBlocks.DISGUISED_RED_SAND_TNT.get(), getTntDispenseItemBehavior(MWPrimedTnt.Type.DISGUISED_RED_SAND));
        DispenserBlock.registerBehavior(MWBlocks.DISGUISED_STONE_TNT.get(), getTntDispenseItemBehavior(MWPrimedTnt.Type.DISGUISED_STONE));
        DispenserBlock.registerBehavior(MWBlocks.MEGA_TNT.get(), getTntDispenseItemBehavior(MWPrimedTnt.Type.MEGA));
        DispenserBlock.registerBehavior(MWBlocks.SUPER_TNT.get(), getTntDispenseItemBehavior(MWPrimedTnt.Type.SUPER));
        DispenserBlock.registerBehavior(MWBlocks.HYPER_TNT.get(), getTntDispenseItemBehavior(MWPrimedTnt.Type.HYPER));

        DispenserBlock.registerBehavior(MWArmors.EMERALD_HORSE_ARMOR.get(), HORSE_ARMOR_DISPENSE_ITEM_BEHAVIOR);
    }

}