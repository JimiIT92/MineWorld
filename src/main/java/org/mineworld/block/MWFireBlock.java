package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWTags;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} {@link BaseFireBlock Fire Block}
 */
public abstract class MWFireBlock extends BaseFireBlock {

    /**
     * {@link MWFireType The Fire Type}
     */
    private final MWFireType type;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param type {@link MWFireType The Fire Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public MWFireBlock(final MWFireType type, final FeatureFlag... featureFlags) {
        this(type, PropertyHelper.block(type.color(), 0F, 0F, false, SoundType.WOOL, featureFlags)
                .replaceable()
                .noCollission()
                .instabreak()
                .lightLevel(state -> type.lightLevel())
                .pushReaction(PushReaction.DESTROY));
    }

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param type {@link MWFireType The Fire Type}
     * @param properties The {@link BlockBehaviour.Properties Block Properties}
     */
    public MWFireBlock(final MWFireType type, final BlockBehaviour.Properties properties) {
        super(properties, type.damage());
        this.type = type;
    }

    /**
     * Update the {@link BlockState Block State} on neighbor changes
     *
     * @param blockState {@link BlockState The current Block State}
     * @param direction {@link Direction The direction the changes are coming}
     * @param neighborBlockState {@link BlockState The neighbor Block State}
     * @param levelAccessor {@link LevelAccessor The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param neighborBlockPos {@link BlockPos The neighbor Block Pos}
     * @return {@link BlockState The updated Block State}
     */
    public @NotNull BlockState updateShape(final @NotNull BlockState blockState, final @NotNull Direction direction, final @NotNull BlockState neighborBlockState, final @NotNull LevelAccessor levelAccessor, final @NotNull BlockPos blockPos, final @NotNull BlockPos neighborBlockPos) {
        return this.canSurvive(blockState, levelAccessor, blockPos) ? this.defaultBlockState() : Blocks.AIR.defaultBlockState();
    }

    /**
     * Check if the Block can stay at the given {@link BlockPos location}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param levelReader {@link LevelReader The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Boolean True if the block can survive}
     */
    @Override
    public boolean canSurvive(final @NotNull BlockState blockState, final @NotNull LevelReader levelReader, final @NotNull BlockPos blockPos) {
        return levelReader.getBlockState(blockPos.below()).is(this.type.survivableBlocks());
    }

    /**
     * Check if the {@link Block Block} can burn other Entities
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean#TRUE True}
     */
    @Override
    protected boolean canBurn(final @NotNull BlockState blockState) {
        return true;
    }

    /**
     * {@link MineWorld MineWorld} Fire Types
     */
    public enum MWFireType {

        //#region Fire Types

        END("end", MapColor.COLOR_PURPLE, 13, 1.5F, 1, MWTags.Blocks.END_FIRE_BASE_BLOCKS),
        SCULK("sculk", MapColor.COLOR_BLUE, 7, 2.5F, 3, MWTags.Blocks.SCULK_FIRE_BASE_BLOCKS);

        //#endregion

        /**
         * {@link MapColor The Block Color on maps}
         */
        private final MapColor color;
        /**
         * {@link Integer The Fire light level}
         */
        private final int lightLevel;
        /**
         * {@link Float The Fire damage}
         */
        private final float damage;
        /**
         * {@link Integer The Campfire damage}
         */
        private final int campfireDamage;
        /**
         * {@link TagKey<Block> The Block Tag for Blocks where the Fire can survive}
         */
        private final TagKey<Block> blockTagKey;

        /**
         * Constructor.
         *
         * @param name {@link String The Fire Type name}
         * @param color {@link MapColor The Block Color on maps}
         * @param lightLevel {@link Integer The Fire light level}
         * @param damage {@link Float The Fire damage}
         * @param campfireDamage {@link Integer The Campfire damage}
         * @param blockTagKey {@link TagKey<Block> The Block Tag for Blocks where the Fire can survive}
         */
        MWFireType(final String name, final MapColor color, final int lightLevel, final float damage, final int campfireDamage, final TagKey<Block> blockTagKey) {
            this.color = color;
            this.lightLevel = lightLevel;
            this.damage = damage;
            this.campfireDamage = campfireDamage;
            this.blockTagKey = blockTagKey;
        }

        /**
         * Get the {@link MapColor Block Color on maps}
         *
         * @return {@link MapColor The Block Color on maps}
         */
        public MapColor color() {
            return this.color;
        }

        /**
         * Get the {@link Integer Fire light level}
         *
         * @return {@link Integer The Fire light level}
         */
        public int lightLevel() {
            return this.lightLevel;
        }

        /**
         * Get the {@link Float Fire damage}
         *
         * @return {@link Float The Fire damage}
         */
        public float damage() {
            return this.damage;
        }

        /**
         * Get the {@link Integer Campfire damage}
         *
         * @return {@link Integer The Campfire damage}
         */
        public int campfireDamage() {
            return this.campfireDamage;
        }

        /**
         * Get the {@link TagKey<Block> Block Tag for Blocks where the Fire can survive}
         *
         * @return {@link TagKey<Block> The Block Tag for Blocks where the Fire can survive}
         */
        public TagKey<Block> survivableBlocks() {
            return this.blockTagKey;
        }

    }

}