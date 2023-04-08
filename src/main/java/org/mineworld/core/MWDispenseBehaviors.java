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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.entity.MWPrimedTnt;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;

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
        registerTntDispenseBehaviors(Map.of(
                MWBlocks.DISGUISED_DIRT_TNT, MWPrimedTnt.Type.DISGUISED_DIRT,
                MWBlocks.DISGUISED_GRASS_TNT, MWPrimedTnt.Type.DISGUISED_GRASS,
                MWBlocks.DISGUISED_SAND_TNT, MWPrimedTnt.Type.DISGUISED_SAND,
                MWBlocks.DISGUISED_RED_SAND_TNT, MWPrimedTnt.Type.DISGUISED_RED_SAND,
                MWBlocks.DISGUISED_STONE_TNT, MWPrimedTnt.Type.DISGUISED_STONE,
                MWBlocks.MEGA_TNT, MWPrimedTnt.Type.MEGA,
                MWBlocks.SUPER_TNT, MWPrimedTnt.Type.SUPER,
                MWBlocks.HYPER_TNT, MWPrimedTnt.Type.HYPER
            )
        );
        registerHorseArmorDispenseBehaviors(
                MWArmors.CHAINMAIL_HORSE_ARMOR,
                MWArmors.EMERALD_HORSE_ARMOR,
                MWArmors.RUBY_HORSE_ARMOR,
                MWArmors.SAPPHIRE_HORSE_ARMOR,
                MWArmors.NETHERITE_HORSE_ARMOR
        );
    }

    /**
     * Register the {@link DispenseItemBehavior tnt dispense behaviors}
     *
     * @param tnts {@link Map.Entry The tnt dispense behaviors to register}
     */
    private static void registerTntDispenseBehaviors(Map<Supplier<Block>, MWPrimedTnt.Type> tnts) {
        tnts.forEach((tnt, type) -> DispenserBlock.registerBehavior(tnt.get(), getTntDispenseItemBehavior(type)));
    }

    /**
     * Register the {@link HorseArmorItem horse armor dispense behaviors}
     *
     * @param horseArmorItems {@link Supplier<Item> The horse armor item suppliers to register}
     */
    @SafeVarargs
    private static void registerHorseArmorDispenseBehaviors(Supplier<Item>... horseArmorItems) {
        Arrays.stream(horseArmorItems).forEach(horseArmorItem -> DispenserBlock.registerBehavior(horseArmorItem.get(), HORSE_ARMOR_DISPENSE_ITEM_BEHAVIOR));
    }

}