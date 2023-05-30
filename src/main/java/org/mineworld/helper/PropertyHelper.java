package org.mineworld.helper;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.TrappedChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.block.weathering.IMWWaxableBlock;
import org.mineworld.core.MWWoodTypes;
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.entity.ThrownGrenade;
import org.mineworld.entity.block.chest.*;
import org.mineworld.entity.vehicle.MWBoat;
import org.mineworld.entity.vehicle.MWChestBoat;
import org.mineworld.item.MWBoatItem;
import org.mineworld.item.PebbleItem;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Helper methods for creating {@link BlockBehaviour.Properties block} and {@link Item.Properties id} properties
 */
public final class PropertyHelper {

    /**
     * Get the basic {@link Item.Properties id properties}
     *
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this id to be registered}
     * @return {@link Item.Properties id properties}
     */
    public static Item.Properties basicItemProperties(final FeatureFlag... featureFlags) {
        return applyFeatureFlags(new Item.Properties(), featureFlags);
    }

    /**
     * Create some {@link FoodProperties basic food properties}
     *
     * @param nutrition {@link Integer How much hunger this food will restore}
     * @param saturation {@link Float How much saturation this food will restore}
     * @return {@link FoodProperties.Builder Food properties builder}
     */
    public static FoodProperties.Builder basicFoodProperties(final int nutrition, final float saturation) {
        return new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation);
    }

    /**
     * Create a cooked variant of {@link FoodProperties the provided food properties}
     *
     * @param foodProperties {@link FoodProperties The raw food properties}
     * @return {@link FoodProperties.Builder The cooked food properties builder}
     */
    public static FoodProperties.Builder cookedFoodProperties(final FoodProperties foodProperties) {
        return getFoodPropertiesBuilder(foodProperties).nutrition(foodProperties.getNutrition() * 2).saturationMod(foodProperties.getSaturationModifier() * 2);
    }

    /**
     * Get a {@link FoodProperties.Builder food properties builder} instance based on the
     * provided {@link FoodProperties food properties}
     *
     * @param foodProperties {@link FoodProperties The food properties to get the builder}
     * @return {@link FoodProperties.Builder The food properties builder}
     */
    static FoodProperties.Builder getFoodPropertiesBuilder(final FoodProperties foodProperties) {
        FoodProperties.Builder propertiesBuilder = new FoodProperties.Builder()
                .nutrition(foodProperties.getNutrition())
                .saturationMod(foodProperties.getSaturationModifier());
        if(foodProperties.isFastFood()) {
            propertiesBuilder = propertiesBuilder.fast();
        }
        if(foodProperties.isMeat()) {
            propertiesBuilder = propertiesBuilder.meat();
        }
        if(foodProperties.canAlwaysEat()) {
            propertiesBuilder = propertiesBuilder.alwaysEat();
        }
        for(Pair<MobEffectInstance, Float> effect : foodProperties.getEffects()) {
            propertiesBuilder = propertiesBuilder.effect(effect::getFirst, effect.getSecond());
        }
        return propertiesBuilder;
    }

    /**
     * Create some basic {@link BlockBehaviour.Properties block properties}
     * using the {@link Material material color} and the base {@link SoundType block sound}
     *
     * @param material {@link Material The block material}
     * @param hardnessAndBlastResistance {@link Float The block hardness and blast resistance values}
     * @param requiresTool {@link Boolean If the block requires the correct tool to drop}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    static BlockBehaviour.Properties basicBlockProperties(final Material material, final float hardnessAndBlastResistance, final boolean requiresTool, final FeatureFlag... featureFlags) {
        return basicBlockProperties(material, hardnessAndBlastResistance, hardnessAndBlastResistance, requiresTool, featureFlags);
    }

    /**
     * Create some basic {@link BlockBehaviour.Properties block properties}
     * using the {@link Material material color} and the base {@link SoundType block sound}
     *
     * @param material {@link Material The block material}
     * @param hardness {@link Float The block hardness value}
     * @param blastResistance {@link Float The block blast resistance value}
     * @param requiresTool {@link Boolean If the block requires the correct tool to drop}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    static BlockBehaviour.Properties basicBlockProperties(final Material material, final float hardness, final float blastResistance, final boolean requiresTool, final FeatureFlag... featureFlags) {
        return basicBlockProperties(material, material.getColor(), hardness, blastResistance, requiresTool, featureFlags);
    }

    /**
     * Create some basic {@link BlockBehaviour.Properties block properties}
     * using the provided {@link MaterialColor color} and the base {@link SoundType block sound}
     *
     * @param material {@link Material The block material}
     * @param color {@link MaterialColor The block color on maps}
     * @param hardness {@link Float The block hardness value}
     * @param blastResistance {@link Float The block blast resistance value}
     * @param requiresTool {@link Boolean If the block requires the correct tool to drop}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    static BlockBehaviour.Properties basicBlockProperties(final Material material, final MaterialColor color, final float hardness, final float blastResistance, final boolean requiresTool, final FeatureFlag... featureFlags) {
        return basicBlockProperties(material, color, hardness, blastResistance, requiresTool, null, featureFlags);
    }

    /**
     * Create some basic {@link BlockBehaviour.Properties block properties}
     *
     * @param material {@link Material The block material}
     * @param color {@link MaterialColor The block color on maps}
     * @param hardness {@link Float The block hardness value}
     * @param blastResistance {@link Float The block blast resistance value}
     * @param requiresTool {@link Boolean If the block requires the correct tool to drop}
     * @param sound {@link SoundType The block sound}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    public static BlockBehaviour.Properties basicBlockProperties(final Material material, final MaterialColor color, final float hardness, final float blastResistance, final boolean requiresTool, final SoundType sound, final FeatureFlag... featureFlags) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of(material, color).strength(hardness, blastResistance);
        if(requiresTool) {
            properties = properties.requiresCorrectToolForDrops();
        }
        if(sound != null) {
            properties = properties.sound(sound);
        }
        return applyFeatureFlags(properties, featureFlags);
    }

    /**
     * Create the {@link BlockBehaviour.Properties block properties} for an {@link Block ore block}
     *
     * @param isDeepslateOre {@link Boolean Whether the ore is a deepslate ore}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The ore block properties}
     */
    static BlockBehaviour.Properties oreBlockProperties(final boolean isDeepslateOre, final FeatureFlag... featureFlags) {
        return basicBlockProperties(Material.STONE,
                isDeepslateOre ? MaterialColor.DEEPSLATE : Material.STONE.getColor(),
                isDeepslateOre ? 4.5F : 3.0F, 3.0F,
                true,
                isDeepslateOre ? SoundType.DEEPSLATE : null,
                featureFlags);
    }

    /**
     * Get the {@link BlockBehaviour.Properties block properties} for an ore storage block
     *
     * @param color {@link MaterialColor The block color on maps}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    static BlockBehaviour.Properties oreStorageBlockProperties(final MaterialColor color, final FeatureFlag... featureFlags) {
        return oreStorageBlockProperties(Material.STONE, color, SoundType.STONE, featureFlags);
    }

    /**
     * Get the {@link BlockBehaviour.Properties block properties} for a metal ore storage block
     *
     * @param color {@link MaterialColor The block color on maps}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    static BlockBehaviour.Properties oreStorageMetalBlockProperties(final MaterialColor color, final FeatureFlag... featureFlags) {
        return oreStorageBlockProperties(Material.METAL, color, SoundType.METAL, featureFlags);
    }

    /**
     * Get the {@link BlockBehaviour.Properties block properties} for an ore storage block
     *
     * @param material {@link Material The block material}
     * @param color {@link MaterialColor The block color on maps}
     * @param sound {@link SoundType The block sound}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    static BlockBehaviour.Properties oreStorageBlockProperties(final Material material, final MaterialColor color, final SoundType sound, final FeatureFlag... featureFlags) {
        return basicBlockProperties(material, color,5.0F, 6.0F, true, sound, featureFlags);
    }

    /**
     * Get the translucent properties equivalent for the provided {@link Block block}
     *
     * @param block {@link Block The block to make the translucent properties}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    public static BlockBehaviour.Properties makeTranslucentBlock(final Block block, final FeatureFlag... featureFlags) {
        return translucentBlockProperties(copyFromBlock(block, featureFlags));
    }

    /**
     * Set the translucent values to some {@link BlockBehaviour.Properties block properties}
     *
     * @param properties {@link BlockBehaviour.Properties The block properties}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    public static BlockBehaviour.Properties translucentBlockProperties(final BlockBehaviour.Properties properties, final FeatureFlag... featureFlags) {
        return applyFeatureFlags(properties.noOcclusion()
                .isValidSpawn((state, level, blockPos, entityType) -> false)
                .isRedstoneConductor((state, level, blockPos) -> false)
                .isSuffocating((state, level, blockPos) -> false)
                .isViewBlocking((state, level, blockPos) -> false), featureFlags);
    }

    /**
     * Get the {@link BlockBehaviour.Properties block properties} for a {@link LanternBlock lantern}
     *
     * @param isSoulLantern {@link Boolean If the lantern is a soul lantern}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    public static BlockBehaviour.Properties lanternProperties(final Boolean isSoulLantern, final FeatureFlag... featureFlags) {
        return copyFromBlock(isSoulLantern ? Blocks.SOUL_LANTERN : Blocks.LANTERN, featureFlags);
    }

    /**
     * Copy the {@link BlockBehaviour.Properties block properties} of an existing block
     * and makes them required by the provided {@link FeatureFlag feature flags}
     *
     * @param block {@link Block The block to copy the properties from}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    public static BlockBehaviour.Properties copyFromBlock(final Block block, final FeatureFlag... featureFlags) {
        return applyFeatureFlags(BlockBehaviour.Properties.copy(block), featureFlags);
    }

    /**
     * Apply some {@link FeatureFlag feature flags} to some {@link Item.Properties id properties}
     *
     * @param properties {@link Item.Properties The id properties}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the id to be functional}
     * @return {@link Item.Properties The id properties}
     */
    static Item.Properties applyFeatureFlags(Item.Properties properties, final FeatureFlag... featureFlags) {
        if(featureFlags != null && featureFlags.length > 0) {
            properties = properties.requiredFeatures(featureFlags);
        }
        return properties;
    }

    /**
     * Apply some {@link FeatureFlag feature flags} to some {@link BlockBehaviour.Properties block properties}
     *
     * @param properties {@link BlockBehaviour.Properties The block properties}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     * @return {@link BlockBehaviour.Properties The block properties}
     */
    static BlockBehaviour.Properties applyFeatureFlags(BlockBehaviour.Properties properties, final FeatureFlag... featureFlags) {
        if(featureFlags != null && featureFlags.length > 0) {
            properties = properties.requiredFeatures(featureFlags);
        }
        return properties;
    }

    /**
     * Get the Overworld {@link OreConfiguration.TargetBlockState target block states}
     *
     * @param stoneOreSupplier {@link Supplier <Block> The stone ore block supplier}
     * @param deepslateOreSupplier {@link Supplier<Block> The deepslate ore block supplier}
     * @return {@link OreConfiguration.TargetBlockState Overworld target block states}
     */
    static Supplier<List<OreConfiguration.TargetBlockState>> createOverworldTargetStates(final Supplier<Block> stoneOreSupplier, final Supplier<Block> deepslateOreSupplier) {
        return Suppliers.memoize(() -> List.of(
                OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), stoneOreSupplier.get().defaultBlockState()),
                OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), deepslateOreSupplier.get().defaultBlockState())));
    }

    /**
     * Get the Overworld {@link OreConfiguration.TargetBlockState target block states}
     * for an ore that doesn't have a variant
     *
     * @param oreSupplier {@link Supplier<Block> The stone ore block supplier}
     * @return {@link OreConfiguration.TargetBlockState Overworld target block states}
     */
    static Supplier<List<OreConfiguration.TargetBlockState>> createOverworldTargetStates(final Supplier<Block> oreSupplier) {
        return Suppliers.memoize(() -> List.of(OreConfiguration.target(new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), oreSupplier.get().defaultBlockState())));
    }

    /**
     * Get the Nether {@link OreConfiguration.TargetBlockState target block states}
     *
     * @param oreSupplier {@link Supplier<Block> The ore block supplier}
     * @return {@link OreConfiguration.TargetBlockState Nether target block states}
     */
    static Supplier<List<OreConfiguration.TargetBlockState>> createNetherTargetState(final Supplier<Block> oreSupplier) {
        return Suppliers.memoize(() -> List.of(OreConfiguration.target(new TagMatchTest(BlockTags.BASE_STONE_NETHER), oreSupplier.get().defaultBlockState())));
    }

    /**
     * Get the {@link List<PlacementModifier> ore placement modifiers}
     *
     * @param rarityPlacementModifier {@link PlacementModifier The rarity placement modifier. Affects the frequency of the ore}
     * @param heightPlacementModifier {@link PlacementModifier The height placement modifier. Affects the levels at which the ore can spawn}
     * @return {@link List<PlacementModifier> The ore placement modifiers}
     */
    static List<PlacementModifier> orePlacement(final PlacementModifier rarityPlacementModifier, final PlacementModifier heightPlacementModifier) {
        return List.of(rarityPlacementModifier, InSquarePlacement.spread(), heightPlacementModifier, BiomeFilter.biome());
    }

    /**
     * Get the {@link List<PlacementModifier> ore placement modifiers} for a common ore
     *
     * @param count {@link Integer How many ores per chunk should be generated}
     * @param heightPlacementModifier {@link PlacementModifier The height placement modifier. Affects the levels at which the ore can spawn}
     * @return {@link List<PlacementModifier> The common ore placement modifiers}
     */
    public static List<PlacementModifier> commonOrePlacement(final int count, final PlacementModifier heightPlacementModifier) {
        return orePlacement(CountPlacement.of(count), heightPlacementModifier);
    }

    /**
     * Get the {@link List<PlacementModifier> ore placement modifiers} for a rare ore
     *
     * @param chance {@link Integer The rarity, in chunks, for the ore to be placed (eg: 1 in X chunks)}
     * @param heightPlacementModifier {@link PlacementModifier The height placement modifier. Affects the levels at which the ore can spawn}
     * @return {@link List<PlacementModifier> The rare ore placement modifiers}
     */
    static List<PlacementModifier> rareOrePlacement(final int chance, final PlacementModifier heightPlacementModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), heightPlacementModifier);
    }

    /**
     * Get the {@link DispenseItemBehavior dispense behavior} based on the {@link MWPrimedTnt.Type primed tnt type}
     *
     * @param type {@link MWPrimedTnt.Type The primed tnt type}
     * @return {@link DispenseItemBehavior The tnt dispense behavior}
     */
    public static DispenseItemBehavior tntDispenseItemBehavior(final MWPrimedTnt.Type type) {
        return new DefaultDispenseItemBehavior() {
            /**
             * Dispense the {@link PrimedTnt tnt} when activated from a dispenser
             *
             * @param blockSource {@link BlockSource The block source reference}
             * @param itemStack   {@link ItemStack The id stack inside the dispenser}
             * @return {@link ItemStack The shrinked id stack if the tnt has been primed}
             */
            @Override
            protected @NotNull ItemStack execute(final @NotNull BlockSource blockSource, @NotNull ItemStack itemStack) {
                Level level = blockSource.getLevel();
                BlockPos blockpos = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));
                MWPrimedTnt primedtnt = new MWPrimedTnt(level, (double) blockpos.getX() + 0.5D, blockpos.getY(), (double) blockpos.getZ() + 0.5D, null, type);
                level.addFreshEntity(primedtnt);
                level.playSound(null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, GameEvent.ENTITY_PLACE, blockpos);
                ItemHelper.hurt(itemStack);
                return itemStack;
            }
        };
    }

    /**
     * Get the {@link DispenseItemBehavior dispense behavior} for a {@link HorseArmorItem horse armor id}
     *
     * @return {@link DispenseItemBehavior The horse armor id dispense behavior}
     */
    public static DispenseItemBehavior horseArmorItemDispenseBehavior() {
        return new OptionalDispenseItemBehavior() {
            /**
             * Equiq the {@link HorseArmorItem horse armor} if there's a horse in front of the dispenser
             *
             * @param blockSource  {@link BlockSource The block source reference}
             * @param itemStack The {@link ItemStack The id stack inside the dispenser}
             * @return {@link ItemStack The shrinked id stack if the armor has been equipped}
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
    }

    /**
     * Get the {@link DispenseItemBehavior dispense behavior} for a {@link PebbleItem pebble}
     *
     * @return {@link DispenseItemBehavior The pebble dispense behavior}
     */
    public static DispenseItemBehavior pebbleDispenseBehavior() {
        return new AbstractProjectileDispenseBehavior() {
            protected @NotNull Projectile getProjectile(final @NotNull Level level, final @NotNull Position position, final @NotNull ItemStack itemStack) {
                return Util.make(new ThrownGrenade(level, position.x(), position.y(), position.z()), pebble -> pebble.setItem(itemStack));
            }
        };
    }

    /**
     * Get the {@link DispenseItemBehavior dispense behavior} for a {@link HoneycombItem honeycomb}
     *
     * @return {@link DispenseItemBehavior The honeycomb dispense behavior}
     */
    public static DispenseItemBehavior honeycombDispenseBehavior() {
        return new OptionalDispenseItemBehavior() {
            public @NotNull ItemStack execute(final @NotNull BlockSource blockSource, final @NotNull ItemStack itemStack) {
                final BlockPos blockPos = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));
                final Level level = blockSource.getLevel();
                final Optional<BlockState> optionalBlockState = IMWWaxableBlock.getWaxed(level.getBlockState(blockPos));
                if (optionalBlockState.isPresent()) {
                    level.setBlockAndUpdate(blockPos, optionalBlockState.get());
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
     * Get the {@link DispenseItemBehavior dispense behavior} for a {@link ChestBlock chest}
     *
     * @return {@link DispenseItemBehavior The chest dispense behavior}
     */
    public static DispenseItemBehavior chestDispenseBehavior() {
        return new OptionalDispenseItemBehavior() {
            public @NotNull ItemStack execute(final @NotNull BlockSource blockSource, final @NotNull ItemStack itemStack) {
                final BlockPos blockPos = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));

                for(AbstractChestedHorse abstractchestedhorse : blockSource.getLevel().getEntitiesOfClass(AbstractChestedHorse.class, new AABB(blockPos), horse -> horse.isAlive() && !horse.hasChest())) {
                    if (abstractchestedhorse.isTamed() && abstractchestedhorse.getSlot(499).set(itemStack)) {
                        itemStack.shrink(1);
                        this.setSuccess(true);
                        return itemStack;
                    }
                }

                return super.execute(blockSource, itemStack);
            }
        };
    }

    /**
     * Get the {@link DispenseItemBehavior dispense behavior} for a {@link MWBoatItem MineWorld boat}
     *
     * @param type {@link MWBoat.Type The boat type}
     * @param isChestBoat {@link Boolean If the dispense behavior is for a chest boat}
     * @return {@link DispenseItemBehavior The boat dispense behavior}
     */
    public static DispenseItemBehavior boatDispenseBehavior(final MWBoat.Type type, final boolean isChestBoat) {
        return new DefaultDispenseItemBehavior() {
            public @NotNull ItemStack execute(final @NotNull BlockSource blockSource, final @NotNull ItemStack itemStack) {
                final Direction direction = blockSource.getBlockState().getValue(DispenserBlock.FACING);
                final Level level = blockSource.getLevel();
                final double x = blockSource.x() + (double)((float)direction.getStepX() * 1.125F);
                final double y = blockSource.y() + (double)((float)direction.getStepY() * 1.125F);
                final double z = blockSource.z() + (double)((float)direction.getStepZ() * 1.125F);
                final BlockPos blockPos = blockSource.getPos().relative(direction);
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

            protected void playSound(final @NotNull BlockSource blockSource) {
                blockSource.getLevel().levelEvent(1000, blockSource.getPos(), 0);
            }
        };
    }

    /**
     * Get the {@link ChestBlockEntity chest block entity} based on the {@link WoodType wood type}
     *
     * @param woodType {@link WoodType The chest wood type}
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     * @param isTrappedChest {@link Boolean If the chest is a trapped chest}
     * @return {@link ChestBlockEntity The chest block entity}
     */
    public static ChestBlockEntity getChestBlockEntity(final WoodType woodType, final BlockPos blockPos, final BlockState blockState, final boolean isTrappedChest) {
        ChestBlockEntity blockEntity = null;
        if(woodType.equals(WoodType.SPRUCE)) {
            blockEntity = isTrappedChest ? new SpruceTrappedChestBlockEntity(blockPos, blockState) : new SpruceChestBlockEntity(blockPos, blockState);
        }
        else if(woodType.equals(WoodType.BIRCH)) {
            blockEntity = isTrappedChest ? new BirchTrappedChestBlockEntity(blockPos, blockState) : new BirchChestBlockEntity(blockPos, blockState);
        }
        else if(woodType.equals(WoodType.JUNGLE)) {
            blockEntity = isTrappedChest ? new JungleTrappedChestBlockEntity(blockPos, blockState) : new JungleChestBlockEntity(blockPos, blockState);
        }
        else if(woodType.equals(WoodType.ACACIA)) {
            blockEntity = isTrappedChest ? new AcaciaTrappedChestBlockEntity(blockPos, blockState) : new AcaciaChestBlockEntity(blockPos, blockState);
        }
        else if(woodType.equals(WoodType.DARK_OAK)) {
            blockEntity = isTrappedChest ? new DarkOakTrappedChestBlockEntity(blockPos, blockState) : new DarkOakChestBlockEntity(blockPos, blockState);
        }
        else if(woodType.equals(WoodType.MANGROVE)) {
            blockEntity = isTrappedChest ? new MangroveTrappedChestBlockEntity(blockPos, blockState) : new MangroveChestBlockEntity(blockPos, blockState);
        }
        else if(woodType.equals(WoodType.CHERRY)) {
            blockEntity = isTrappedChest ? new CherryTrappedChestBlockEntity(blockPos, blockState) : new CherryChestBlockEntity(blockPos, blockState);
        }
        else if(woodType.equals(WoodType.BAMBOO)) {
            blockEntity = isTrappedChest ? new BambooTrappedChestBlockEntity(blockPos, blockState) : new BambooChestBlockEntity(blockPos, blockState);
        }
        else if(woodType.equals(WoodType.CRIMSON)) {
            blockEntity = isTrappedChest ? new CrimsonTrappedChestBlockEntity(blockPos, blockState) : new CrimsonChestBlockEntity(blockPos, blockState);
        }
        else if(woodType.equals(WoodType.WARPED)) {
            blockEntity = isTrappedChest ? new WarpedTrappedChestBlockEntity(blockPos, blockState) : new WarpedChestBlockEntity(blockPos, blockState);
        }
        else if(woodType.equals(MWWoodTypes.APPLE)) {
            blockEntity = isTrappedChest ? new AppleTrappedChestBlockEntity(blockPos, blockState) : new AppleChestBlockEntity(blockPos, blockState);
        }
        else if(woodType.equals(MWWoodTypes.PALM)) {
            blockEntity = isTrappedChest ? new PalmTrappedChestBlockEntity(blockPos, blockState) : new PalmChestBlockEntity(blockPos, blockState);
        }
        else if(woodType.equals(MWWoodTypes.DEAD)) {
            blockEntity = isTrappedChest ? new DeadTrappedChestBlockEntity(blockPos, blockState) : new DeadChestBlockEntity(blockPos, blockState);
        }
        return blockEntity != null ? blockEntity : (isTrappedChest ? new TrappedChestBlockEntity(blockPos, blockState) : new ChestBlockEntity(blockPos, blockState));
    }

    /**
     * Get the {@link WoodType wood type name}
     *
     * @param woodType {@link WoodType The wood type}
     * @return {@link String The wood type name}
     */
    public static String getWoodTypeName(final WoodType woodType) {
        return woodType.name().replace(MineWorld.MOD_ID, "").replace(":", "");
    }

}