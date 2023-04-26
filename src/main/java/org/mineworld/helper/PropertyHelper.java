package org.mineworld.helper;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.food.FoodProperties;
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
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.entity.Pebble;
import org.mineworld.entity.block.chest.BirchChestBlockEntity;
import org.mineworld.entity.block.chest.BirchTrappedChestBlockEntity;
import org.mineworld.entity.block.chest.SpruceChestBlockEntity;
import org.mineworld.entity.block.chest.SpruceTrappedChestBlockEntity;
import org.mineworld.item.PebbleItem;

import java.util.List;
import java.util.function.Supplier;

/**
 * Helper methods for creating {@link BlockBehaviour.Properties block} and {@link Item.Properties item} properties
 */
public final class PropertyHelper {

    /**
     * Get the basic {@link Item.Properties item properties}
     *
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     * @return {@link Item.Properties item properties}
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
     * Apply some {@link FeatureFlag feature flags} to some {@link Item.Properties item properties}
     *
     * @param properties {@link Item.Properties The item properties}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the item to be functional}
     * @return {@link Item.Properties The item properties}
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
     * Get the {@link DispenseItemBehavior dispense behavior} for a {@link HorseArmorItem horse armor item}
     *
     * @return {@link DispenseItemBehavior The horse armor item dispense behavior}
     */
    public static DispenseItemBehavior horseArmorItemDispenseBehavior() {
        return new OptionalDispenseItemBehavior() {
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
    }

    /**
     * Get the {@link DispenseItemBehavior dispense behavior} for a {@link PebbleItem pebble}
     *
     * @return {@link DispenseItemBehavior The pebble dispense behavior}
     */
    public static DispenseItemBehavior pebbleDispenseBehavior() {
        return new AbstractProjectileDispenseBehavior() {
            protected @NotNull Projectile getProjectile(final @NotNull Level level, final @NotNull Position position, final @NotNull ItemStack itemStack) {
                return Util.make(new Pebble(level, position.x(), position.y(), position.z()), pebble -> pebble.setItem(itemStack));
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
        return blockEntity != null ? blockEntity : (isTrappedChest ? new TrappedChestBlockEntity(blockPos, blockState) : new ChestBlockEntity(blockPos, blockState));
    }
}