package org.mineworld.core;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.*;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.block.weathering.IMWWaxableBlock;
import org.mineworld.entity.block.MWPrimedTnt;
import org.mineworld.entity.projectile.ThrownPebble;
import org.mineworld.entity.vehicle.MWBoat;
import org.mineworld.entity.vehicle.MWChestBoat;
import org.mineworld.helper.ItemHelper;
import org.mineworld.item.PebbleItem;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link DispenseItemBehavior Dispense Item Behaviors}
 */
public final class MWDispenseBehaviors {

    //#region Dispense Item Behaviors

    /**
     * Get the {@link DispenseItemBehavior Dispense Item Behavior} for a {@link TntBlock TNT Block}
     *
     * @param type {@link MWPrimedTnt.Type The TNT Type}
     * @return {@link DispenseItemBehavior The TNT Dispense Item Behavior}
     */
    private static DispenseItemBehavior tntDispenseItemBehavior(final MWPrimedTnt.Type type) {
        return new DefaultDispenseItemBehavior() {

            /**
             * Dispense the {@link PrimedTnt TNT} when activated from a dispenser
             *
             * @param blockSource {@link BlockSource The Block Source reference}
             * @param itemStack   {@link ItemStack The Item Stack inside the dispenser}
             * @return {@link ItemStack The modified Item Stack}
             */
            @Override
            protected @NotNull ItemStack execute(final @NotNull BlockSource blockSource, @NotNull ItemStack itemStack) {
                final Level level = blockSource.level();
                final BlockPos blockPos = blockSource.pos().relative(blockSource.state().getValue(DispenserBlock.FACING));
                final MWPrimedTnt primedTnt = new MWPrimedTnt(level, (double) blockPos.getX() + 0.5D, blockPos.getY(), (double) blockPos.getZ() + 0.5D, null, type);
                level.addFreshEntity(primedTnt);
                level.playSound(null, primedTnt.getX(), primedTnt.getY(), primedTnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, GameEvent.ENTITY_PLACE, blockPos);
                ItemHelper.hurt(itemStack);
                return itemStack;
            }
        };
    }

    /**
     * Get the {@link DispenseItemBehavior Dispense Item Behavior} for an {@link HorseArmorItem Horse Armor}
     *
     * @return {@link DispenseItemBehavior The Horse Armor Dispense Item Behavior}
     */
    private static DispenseItemBehavior horseArmorItemDispenseBehavior() {
        return new OptionalDispenseItemBehavior() {

            /**
             * Equip the {@link HorseArmorItem Horse Armor} if there's a Horse in front of the dispenser
             *
             * @param blockSource {@link BlockSource The Block Source reference}
             * @param itemStack   {@link ItemStack The Item Stack inside the dispenser}
             * @return {@link ItemStack The modified Item Stack}
             */
            @Override
            protected @NotNull ItemStack execute(final @NotNull BlockSource blockSource, @NotNull ItemStack itemStack) {
                final BlockPos blockPos = blockSource.pos().relative(blockSource.state().getValue(DispenserBlock.FACING));
                for(final AbstractHorse horse : blockSource.level().getEntitiesOfClass(AbstractHorse.class, new AABB(blockPos), entity -> entity.isAlive() && entity.canWearArmor())) {
                    if (horse.isArmor(itemStack) && !horse.isWearingArmor() && horse.isTamed()) {
                        horse.getSlot(401).set(itemStack.split(1));
                        this.setSuccess(true);
                        return itemStack;
                    }
                }
                return super.execute(blockSource, itemStack);
            }
        };
    }

    /**
     * Get the {@link DispenseItemBehavior Dispense Item Behavior} for a {@link PebbleItem Pebble}
     *
     * @return {@link DispenseItemBehavior The Pebble Dispense Item Behavior}
     */
    private static DispenseItemBehavior pebbleDispenseBehavior() {
        return new AbstractProjectileDispenseBehavior() {

            /**
             * Get the {@link Projectile Projectile} fired by the dispenser
             *
             * @param level {@link Level The level reference}
             * @param position {@link Position The dispenser position}
             * @param itemStack {@link ItemStack The Item Stack inside the dispenser}
             * @return {@link Projectile The fired projectile}
             */
            protected @NotNull Projectile getProjectile(final @NotNull Level level, final @NotNull Position position, final @NotNull ItemStack itemStack) {
                return Util.make(new ThrownPebble(level, position.x(), position.y(), position.z()), pebble -> pebble.setItem(itemStack));
            }
        };
    }

    /**
     * Get the {@link DispenseItemBehavior Dispense Item Behavior} for a {@link HoneycombItem Honeycomb}
     *
     * @return {@link DispenseItemBehavior The Honeycomb Dispense Item Behavior}
     */
    private static DispenseItemBehavior honeycombDispenseBehavior() {
        return new OptionalDispenseItemBehavior() {

            /**
             * Wax a {@link WeatheringCopper Copper Block} if is in front of the dispenser
             *
             * @param blockSource {@link BlockSource The Block Source reference}
             * @param itemStack   {@link ItemStack The Item Stack inside the dispenser}
             * @return {@link ItemStack The modified Item Stack}
             */
            public @NotNull ItemStack execute(final @NotNull BlockSource blockSource, final @NotNull ItemStack itemStack) {
                final BlockPos blockPos = blockSource.pos().relative(blockSource.state().getValue(DispenserBlock.FACING));
                final Level level = blockSource.level();
                final Optional<BlockState> waxedBlockState = IMWWaxableBlock.getWaxed(level.getBlockState(blockPos));
                if (waxedBlockState.isPresent()) {
                    level.setBlockAndUpdate(blockPos, waxedBlockState.get());
                    level.levelEvent(3003, blockPos, 0);
                    ItemHelper.hurt(itemStack);
                    this.setSuccess(true);
                    return itemStack;
                }
                return super.execute(blockSource, itemStack);
            }
        };
    }

    /**
     * Get the {@link DispenseItemBehavior Dispense Item Behavior} for a {@link ChestBlock Chest}
     *
     * @return {@link DispenseItemBehavior The Chest Dispense Item Behavior}
     */
    private static DispenseItemBehavior chestHorseDispenseBehavior() {
        return new OptionalDispenseItemBehavior() {

            /**
             * Equip an {@link Horse Horse} with a {@link ChestBlock Chest} if is in front of the dispenser
             *
             * @param blockSource {@link BlockSource The Block Source reference}
             * @param itemStack   {@link ItemStack The Item Stack inside the dispenser}
             * @return {@link ItemStack The modified Item Stack}
             */
            public @NotNull ItemStack execute(final @NotNull BlockSource blockSource, final @NotNull ItemStack itemStack) {
                final BlockPos blockPos = blockSource.pos().relative(blockSource.state().getValue(DispenserBlock.FACING));
                for(final AbstractChestedHorse horse : blockSource.level().getEntitiesOfClass(AbstractChestedHorse.class, new AABB(blockPos), entity -> entity.isAlive() && !entity.hasChest())) {
                    if (horse.isTamed() && horse.getSlot(499).set(itemStack)) {
                        ItemHelper.hurt(itemStack);
                        this.setSuccess(true);
                        return itemStack;
                    }
                }

                return super.execute(blockSource, itemStack);
            }
        };
    }

    /**
     * Get the {@link DispenseItemBehavior Dispense Item Behavior} for a {@link MWBoat Boat}
     *
     * @param type {@link MWBoat.Type The Boat Type}
     * @param isChestBoat {@link Boolean If the Boat is a Chest Boat}
     * @return {@link DispenseItemBehavior The Boat Dispense Item Behavior}
     */
    private static DispenseItemBehavior boatDispenseBehavior(final MWBoat.Type type, final boolean isChestBoat) {
        return new DefaultDispenseItemBehavior() {

            /**
             * Place a {@link Boat Boat} if there is water in front of the Dispenser
             *
             * @param blockSource {@link BlockSource The Block Source reference}
             * @param itemStack   {@link ItemStack The Item Stack inside the dispenser}
             * @return {@link ItemStack The modified Item Stack}
             */
            public @NotNull ItemStack execute(final @NotNull BlockSource blockSource, final @NotNull ItemStack itemStack) {
                final Direction direction = blockSource.state().getValue(DispenserBlock.FACING);
                final Level level = blockSource.level();
                final BlockPos pos = blockSource.pos();
                final double x = pos.getX() + (double)((float)direction.getStepX() * 1.125F);
                final double y = pos.getY() + (double)((float)direction.getStepY() * 1.125F);
                final double z = pos.getZ() + (double)((float)direction.getStepZ() * 1.125F);
                final BlockPos blockPos = blockSource.pos().relative(direction);
                final MWBoat boat = isChestBoat ? new MWChestBoat(level, x, y, z) : new MWBoat(level, x, y, z);
                boat.setBoatType(type);
                boat.setYRot(direction.toYRot());
                double offset;
                if (boat.canBoatInFluid(level.getFluidState(blockPos))) {
                    offset = 1.0D;
                } else {
                    if (!level.getBlockState(blockPos).isAir() || !boat.canBoatInFluid(level.getFluidState(blockPos.below()))) {
                        return new DefaultDispenseItemBehavior().dispense(blockSource, itemStack);
                    }
                    offset = 0.0D;
                }
                boat.setPos(x, y + offset, z);
                level.addFreshEntity(boat);
                itemStack.shrink(1);
                return itemStack;
            }

            /**
             * Play a {@link SoundEvent Sound} when placing the {@link Boat Boat}
             *
             * @param blockSource {@link BlockSource The Block Source reference}
             */
            protected void playSound(final @NotNull BlockSource blockSource) {
                blockSource.level().levelEvent(1000, blockSource.pos(), 0);
            }
        };
    }

    //#endregion

    //#region Methods

    /**
     * Register some {@link DispenseItemBehavior Dispense Item Behaviors}
     *
     * @param dispenseBehavior {@link DispenseItemBehavior The Dispense Item Behavior to register}
     * @param items {@link Supplier<ItemLike> The Items to apply the Dispense Item Behavior}
     */
    @SafeVarargs
    private static void registerDispenseBehaviors(final DispenseItemBehavior dispenseBehavior, final Supplier<? extends ItemLike>... items) {
        Arrays.stream(items).forEach(item -> registerDispenseBehavior(dispenseBehavior, item));
    }

    /**
     * Register some {@link DispenseItemBehavior Dispense Item Behaviors}
     *
     * @param dispenseBehaviors {@link Supplier<Map.Entry> The Dispense Item Behavior Suppliers to register}
     */
    private static void registerDispenseBehaviors(final Map<DispenseItemBehavior, Supplier<? extends ItemLike>> dispenseBehaviors) {
        dispenseBehaviors.forEach(MWDispenseBehaviors::registerDispenseBehavior);
    }

    /**
     * Register a {@link DispenseItemBehavior Dispense Item Behavior}
     *
     * @param dispenseItemBehavior {@link DispenseItemBehavior The Dispense Item Behavior to register}
     * @param item {@link Supplier<ItemLike> The Item to apply the Dispense Item Behavior}
     */
    private static void registerDispenseBehavior(final DispenseItemBehavior dispenseItemBehavior, final Supplier<? extends ItemLike> item) {
        DispenserBlock.registerBehavior(item.get(), dispenseItemBehavior);
    }

    //#endregion

    //#region Register

    /**
     * Register all {@link DispenseItemBehavior Dispense Item Behaviors}
     */
    public static void register() {
        registerDispenseBehaviors(Map.of(
                tntDispenseItemBehavior(MWPrimedTnt.Type.DISGUISED_DIRT), MWBlocks.DISGUISED_DIRT_TNT,
                tntDispenseItemBehavior(MWPrimedTnt.Type.DISGUISED_GRASS), MWBlocks.DISGUISED_GRASS_TNT,
                tntDispenseItemBehavior(MWPrimedTnt.Type.DISGUISED_SAND), MWBlocks.DISGUISED_SAND_TNT,
                tntDispenseItemBehavior(MWPrimedTnt.Type.DISGUISED_RED_SAND), MWBlocks.DISGUISED_RED_SAND_TNT,
                tntDispenseItemBehavior(MWPrimedTnt.Type.DISGUISED_STONE), MWBlocks.DISGUISED_STONE_TNT,
                tntDispenseItemBehavior(MWPrimedTnt.Type.DISGUISED_CAKE), MWBlocks.DISGUISED_CAKE_TNT,
                tntDispenseItemBehavior(MWPrimedTnt.Type.MEGA), MWBlocks.MEGA_TNT,
                tntDispenseItemBehavior(MWPrimedTnt.Type.SUPER), MWBlocks.SUPER_TNT,
                tntDispenseItemBehavior(MWPrimedTnt.Type.HYPER), MWBlocks.HYPER_TNT
        ));
        registerDispenseBehaviors(Map.of(
                boatDispenseBehavior(MWBoat.Type.CRIMSON, false), MWItems.CRIMSON_BOAT,
                boatDispenseBehavior(MWBoat.Type.CRIMSON, true), MWItems.CRIMSON_CHEST_BOAT,
                boatDispenseBehavior(MWBoat.Type.WARPED, false), MWItems.WARPED_BOAT,
                boatDispenseBehavior(MWBoat.Type.WARPED, true), MWItems.WARPED_CHEST_BOAT,
                boatDispenseBehavior(MWBoat.Type.APPLE, false), MWItems.APPLE_BOAT,
                boatDispenseBehavior(MWBoat.Type.APPLE, true), MWItems.APPLE_CHEST_BOAT,
                boatDispenseBehavior(MWBoat.Type.PALM, false), MWItems.PALM_RAFT,
                boatDispenseBehavior(MWBoat.Type.PALM, true), MWItems.PALM_CHEST_RAFT,
                boatDispenseBehavior(MWBoat.Type.DEAD, false), MWItems.DEAD_BOAT,
                boatDispenseBehavior(MWBoat.Type.DEAD, true), MWItems.DEAD_CHEST_BOAT
        ));
        registerDispenseBehaviors(Map.of(
                boatDispenseBehavior(MWBoat.Type.SCULK, false), MWItems.SCULK_BOAT,
                boatDispenseBehavior(MWBoat.Type.SCULK, true), MWItems.SCULK_CHEST_BOAT
        ));
        registerDispenseBehaviors(horseArmorItemDispenseBehavior(),
                MWItems.CHAINMAIL_HORSE_ARMOR,
                MWItems.EMERALD_HORSE_ARMOR,
                MWItems.RUBY_HORSE_ARMOR,
                MWItems.SAPPHIRE_HORSE_ARMOR,
                MWItems.NETHERITE_HORSE_ARMOR,
                MWItems.ALUMINUM_HORSE_ARMOR,
                MWItems.BRONZE_HORSE_ARMOR,
                MWItems.COPPER_HORSE_ARMOR,
                MWItems.SILVER_HORSE_ARMOR
        );
        registerDispenseBehaviors(pebbleDispenseBehavior(),
                MWPebbles.Items.STONE_PEBBLE,
                MWPebbles.Items.COBBLESTONE_PEBBLE,
                MWPebbles.Items.MOSSY_STONE_PEBBLE,
                MWPebbles.Items.MOSSY_COBBLESTONE_PEBBLE,
                MWPebbles.Items.SMOOTH_STONE_PEBBLE,
                MWPebbles.Items.STONE_BRICKS_PEBBLE,
                MWPebbles.Items.MOSSY_STONE_BRICKS_PEBBLE,
                MWPebbles.Items.GRANITE_PEBBLE,
                MWPebbles.Items.POLISHED_GRANITE_PEBBLE,
                MWPebbles.Items.DIORITE_PEBBLE,
                MWPebbles.Items.POLISHED_DIORITE_PEBBLE,
                MWPebbles.Items.ANDESITE_PEBBLE,
                MWPebbles.Items.POLISHED_ANDESITE_PEBBLE,
                MWPebbles.Items.DEEPSLATE_PEBBLE,
                MWPebbles.Items.COBBLED_DEEPSLATE_PEBBLE,
                MWPebbles.Items.POLISHED_DEEPSLATE_PEBBLE,
                MWPebbles.Items.DEEPSLATE_BRICKS_PEBBLE,
                MWPebbles.Items.DEEPSLATE_TILES_PEBBLE,
                MWPebbles.Items.REINFORCED_DEEPSLATE_PEBBLE,
                MWPebbles.Items.BRICKS_PEBBLE,
                MWPebbles.Items.MUD_BRICKS_PEBBLE,
                MWPebbles.Items.SANDSTONE_PEBBLE,
                MWPebbles.Items.SMOOTH_SANDSTONE_PEBBLE,
                MWPebbles.Items.RED_SANDSTONE_PEBBLE,
                MWPebbles.Items.SMOOTH_RED_SANDSTONE_PEBBLE,
                MWPebbles.Items.PRISMARINE_PEBBLE,
                MWPebbles.Items.PRISMARINE_BRICKS_PEBBLE,
                MWPebbles.Items.DARK_PRISMARINE_PEBBLE,
                MWPebbles.Items.NETHERRACK_PEBBLE,
                MWPebbles.Items.NETHER_BRICKS_PEBBLE,
                MWPebbles.Items.RED_NETHER_BRICKS_PEBBLE,
                MWPebbles.Items.BASALT_PEBBLE,
                MWPebbles.Items.SMOOTH_BASALT_PEBBLE,
                MWPebbles.Items.POLISHED_BASALT_PEBBLE,
                MWPebbles.Items.BLACKSTONE_PEBBLE,
                MWPebbles.Items.POLISHED_BLACKSTONE_PEBBLE,
                MWPebbles.Items.POLISHED_BLACKSTONE_BRICKS_PEBBLE,
                MWPebbles.Items.GILDED_BLACKSTONE_PEBBLE,
                MWPebbles.Items.END_STONE_PEBBLE,
                MWPebbles.Items.END_STONE_BRICKS_PEBBLE,
                MWPebbles.Items.PURPUR_PEBBLE,
                MWPebbles.Items.PURPUR_PILLAR_PEBBLE,
                MWPebbles.Items.QUARTZ_PEBBLE,
                MWPebbles.Items.SMOOTH_QUARTZ_PEBBLE,
                MWPebbles.Items.QUARTZ_BRICKS_PEBBLE,
                MWPebbles.Items.QUARTZ_PILLAR_PEBBLE,
                MWPebbles.Items.TERRACOTTA_PEBBLE,
                MWPebbles.Items.WHITE_TERRACOTTA_PEBBLE,
                MWPebbles.Items.ORANGE_TERRACOTTA_PEBBLE,
                MWPebbles.Items.MAGENTA_TERRACOTTA_PEBBLE,
                MWPebbles.Items.LIGHT_BLUE_TERRACOTTA_PEBBLE,
                MWPebbles.Items.YELLOW_TERRACOTTA_PEBBLE,
                MWPebbles.Items.LIME_TERRACOTTA_PEBBLE,
                MWPebbles.Items.PINK_TERRACOTTA_PEBBLE,
                MWPebbles.Items.GRAY_TERRACOTTA_PEBBLE,
                MWPebbles.Items.LIGHT_GRAY_TERRACOTTA_PEBBLE,
                MWPebbles.Items.CYAN_TERRACOTTA_PEBBLE,
                MWPebbles.Items.PURPLE_TERRACOTTA_PEBBLE,
                MWPebbles.Items.BLUE_TERRACOTTA_PEBBLE,
                MWPebbles.Items.BROWN_TERRACOTTA_PEBBLE,
                MWPebbles.Items.GREEN_TERRACOTTA_PEBBLE,
                MWPebbles.Items.RED_TERRACOTTA_PEBBLE,
                MWPebbles.Items.BLACK_TERRACOTTA_PEBBLE,
                MWPebbles.Items.WHITE_GLAZED_TERRACOTTA_PEBBLE,
                MWPebbles.Items.ORANGE_GLAZED_TERRACOTTA_PEBBLE,
                MWPebbles.Items.MAGENTA_GLAZED_TERRACOTTA_PEBBLE,
                MWPebbles.Items.LIGHT_BLUE_GLAZED_TERRACOTTA_PEBBLE,
                MWPebbles.Items.YELLOW_GLAZED_TERRACOTTA_PEBBLE,
                MWPebbles.Items.LIME_GLAZED_TERRACOTTA_PEBBLE,
                MWPebbles.Items.PINK_GLAZED_TERRACOTTA_PEBBLE,
                MWPebbles.Items.GRAY_GLAZED_TERRACOTTA_PEBBLE,
                MWPebbles.Items.LIGHT_GRAY_GLAZED_TERRACOTTA_PEBBLE,
                MWPebbles.Items.CYAN_GLAZED_TERRACOTTA_PEBBLE,
                MWPebbles.Items.PURPLE_GLAZED_TERRACOTTA_PEBBLE,
                MWPebbles.Items.BLUE_GLAZED_TERRACOTTA_PEBBLE,
                MWPebbles.Items.BROWN_GLAZED_TERRACOTTA_PEBBLE,
                MWPebbles.Items.GREEN_GLAZED_TERRACOTTA_PEBBLE,
                MWPebbles.Items.RED_GLAZED_TERRACOTTA_PEBBLE,
                MWPebbles.Items.BLACK_GLAZED_TERRACOTTA_PEBBLE,
                MWPebbles.Items.WHITE_CONCRETE_PEBBLE,
                MWPebbles.Items.ORANGE_CONCRETE_PEBBLE,
                MWPebbles.Items.MAGENTA_CONCRETE_PEBBLE,
                MWPebbles.Items.LIGHT_BLUE_CONCRETE_PEBBLE,
                MWPebbles.Items.YELLOW_CONCRETE_PEBBLE,
                MWPebbles.Items.LIME_CONCRETE_PEBBLE,
                MWPebbles.Items.PINK_CONCRETE_PEBBLE,
                MWPebbles.Items.GRAY_CONCRETE_PEBBLE,
                MWPebbles.Items.LIGHT_GRAY_CONCRETE_PEBBLE,
                MWPebbles.Items.CYAN_CONCRETE_PEBBLE,
                MWPebbles.Items.PURPLE_CONCRETE_PEBBLE,
                MWPebbles.Items.BLUE_CONCRETE_PEBBLE,
                MWPebbles.Items.BROWN_CONCRETE_PEBBLE,
                MWPebbles.Items.GREEN_CONCRETE_PEBBLE,
                MWPebbles.Items.RED_CONCRETE_PEBBLE,
                MWPebbles.Items.BLACK_CONCRETE_PEBBLE,
                MWPebbles.Items.CALCITE_PEBBLE,
                MWPebbles.Items.TUFF_PEBBLE,
                MWPebbles.Items.DRIPSTONE_PEBBLE,
                MWPebbles.Items.OBSIDIAN_PEBBLE,
                MWPebbles.Items.CRYING_OBSIDIAN_PEBBLE,
                MWPebbles.Items.MARBLE_PEBBLE,
                MWPebbles.Items.WHITE_MARBLE_PEBBLE,
                MWPebbles.Items.ORANGE_MARBLE_PEBBLE,
                MWPebbles.Items.MAGENTA_MARBLE_PEBBLE,
                MWPebbles.Items.LIGHT_BLUE_MARBLE_PEBBLE,
                MWPebbles.Items.YELLOW_MARBLE_PEBBLE,
                MWPebbles.Items.LIME_MARBLE_PEBBLE,
                MWPebbles.Items.PINK_MARBLE_PEBBLE,
                MWPebbles.Items.GRAY_MARBLE_PEBBLE,
                MWPebbles.Items.LIGHT_GRAY_MARBLE_PEBBLE,
                MWPebbles.Items.CYAN_MARBLE_PEBBLE,
                MWPebbles.Items.PURPLE_MARBLE_PEBBLE,
                MWPebbles.Items.BLUE_MARBLE_PEBBLE,
                MWPebbles.Items.BROWN_MARBLE_PEBBLE,
                MWPebbles.Items.GREEN_MARBLE_PEBBLE,
                MWPebbles.Items.RED_MARBLE_PEBBLE,
                MWPebbles.Items.BLACK_MARBLE_PEBBLE
        );
        registerDispenseBehaviors(honeycombDispenseBehavior(),
                () -> Items.HONEYCOMB
        );
    }

    //#endregion

}