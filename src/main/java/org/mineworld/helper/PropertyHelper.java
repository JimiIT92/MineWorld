package org.mineworld.helper;

import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

/**
 * Helper methods for {@link BlockBehaviour.Properties Block Properties} and {@link Item.Properties Item Properties}
 */
public final class PropertyHelper {

    /**
     * Get the {@link Item.Properties Item Properties} for a simple {@link Item Item}
     *
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     * @return {@link Item.Properties The Item Properties}
     */
    public static Item.Properties item(final FeatureFlag... featureFlags) {
        return new Item.Properties().requiredFeatures(featureFlags);
    }

    /**
     * Get some {@link FoodProperties simple Food Properties}
     *
     * @param nutrition {@link Integer The Food nutrition value}
     * @param saturation {@link Float The Food saturation value}
     * @return {@link FoodProperties The Food Properties}
     */
    public static FoodProperties food(final int nutrition, final float saturation) {
        return new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationMod(saturation)
                .build();
    }

    /**
     * Get some {@link BlockBehaviour.Properties Block Properties}
     *
     * @param hardnessAndBlastResistance {@link Float The Block Hardness and Blast Resistance values}
     * @param requiresTool {@link Boolean If an appropriate tool is required for the Block to drop some loot}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link BlockBehaviour.Properties The Block Properties}
     */
    public static BlockBehaviour.Properties block(final float hardnessAndBlastResistance, final boolean requiresTool, final FeatureFlag... featureFlags) {
        return block(hardnessAndBlastResistance, hardnessAndBlastResistance, requiresTool, featureFlags);
    }

    /**
     * Get some {@link BlockBehaviour.Properties Block Properties}
     *
     * @param hardness {@link Float The Block Hardness value}
     * @param blastResistance {@link Float The Block Blast Resistance value}
     * @param requiresTool {@link Boolean If an appropriate tool is required for the Block to drop some loot}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link BlockBehaviour.Properties The Block Properties}
     */
    public static BlockBehaviour.Properties block(final float hardness, final float blastResistance, final boolean requiresTool, final FeatureFlag... featureFlags) {
        return block(MapColor.STONE, hardness, blastResistance, requiresTool, featureFlags);
    }

    /**
     * Get some {@link BlockBehaviour.Properties Block Properties}
     *
     * @param mapColor {@link MapColor The Block Map color}
     * @param hardness {@link Float The Block Hardness value}
     * @param blastResistance {@link Float The Block Blast Resistance value}
     * @param requiresTool {@link Boolean If an appropriate tool is required for the Block to drop some loot}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link BlockBehaviour.Properties The Block Properties}
     */
    public static BlockBehaviour.Properties block(final MapColor mapColor, final float hardness, final float blastResistance, final boolean requiresTool, final FeatureFlag... featureFlags) {
        return block(mapColor, hardness, blastResistance, requiresTool, SoundType.STONE, featureFlags);
    }

    /**
     * Get some {@link BlockBehaviour.Properties Block Properties}
     *
     * @param mapColor {@link MapColor The Block Map color}
     * @param hardness {@link Float The Block Hardness value}
     * @param blastResistance {@link Float The Block Blast Resistance value}
     * @param requiresTool {@link Boolean If an appropriate tool is required for the Block to drop some loot}
     * @param sound {@link SoundType The Block Sound}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link BlockBehaviour.Properties The Block Properties}
     */
    public static BlockBehaviour.Properties block(final MapColor mapColor, final float hardness, final float blastResistance, final boolean requiresTool, final SoundType sound, final FeatureFlag... featureFlags) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
                .mapColor(mapColor)
                .strength(hardness, blastResistance)
                .sound(sound)
                .requiredFeatures(featureFlags);
        if(requiresTool) {
            properties = properties.requiresCorrectToolForDrops();
        }
        return properties;
    }

    /**
     * Get some {@link BlockBehaviour.Properties Block Properties} for an Ore
     *
     * @param isDeepslateOre {@link Boolean If the Ore is a Deepslate Ore}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link BlockBehaviour.Properties The Block Properties}
     */
    public static BlockBehaviour.Properties ore(final boolean isDeepslateOre, final FeatureFlag... featureFlags) {
        return block(
                isDeepslateOre ? MapColor.DEEPSLATE : MapColor.STONE,
                isDeepslateOre ? 4.5F : 3F,
                3F,
                true,
                isDeepslateOre ? SoundType.DEEPSLATE : SoundType.STONE,
                featureFlags
        );
    }

    /**
     * Get some {@link BlockBehaviour.Properties Block Properties} for an Ore Storage Block
     *
     * @param mapColor {@link MapColor The Block Map color}
     * @param sound {@link SoundType The Block sound}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link BlockBehaviour.Properties The Block Properties}
     */
    public static BlockBehaviour.Properties oreStorage(final MapColor mapColor, final SoundType sound, final FeatureFlag... featureFlags) {
        return block(mapColor, 5F, 6F, true, sound, featureFlags);
    }

    /**
     * Get the translucent properties equivalent for the provided {@link Block Block}
     *
     * @param block {@link Block The Block to make the translucent properties}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link BlockBehaviour.Properties The Block Properties}
     */
    public static BlockBehaviour.Properties makeTranslucent(final Block block, final FeatureFlag... featureFlags) {
        return translucent(copy(block, featureFlags));
    }

    /**
     * Set the translucent values to some {@link BlockBehaviour.Properties Block Properties}
     *
     * @param properties {@link BlockBehaviour.Properties The Block Properties}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link BlockBehaviour.Properties The Block Properties}
     */
    public static BlockBehaviour.Properties translucent(final BlockBehaviour.Properties properties, final FeatureFlag... featureFlags) {
        return properties
                .requiredFeatures(featureFlags)
                .noOcclusion()
                .isValidSpawn((state, level, blockPos, entityType) -> false)
                .isRedstoneConductor((state, level, blockPos) -> false)
                .isSuffocating((state, level, blockPos) -> false)
                .isViewBlocking((state, level, blockPos) -> false);
    }

    /**
     * Copy the {@link BlockBehaviour.Properties Block Properties} from another {@link Block Block}
     * and apply some {@link FeatureFlag Feature Flags} if provided
     *
     * @param block {@link Block The Block to copy the properties from}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link BlockBehaviour.Properties The Block Properties}
     */
    public static BlockBehaviour.Properties copy(final Block block, final FeatureFlag... featureFlags) {
        return BlockBehaviour.Properties.copy(block).requiredFeatures(featureFlags);
    }

}