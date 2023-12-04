package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWTags;
import org.mineworld.helper.PropertyHelper;

/**
 * Implementation class for a {@link BaseFireBlock end fire}
 */
public class EndFireBlock extends BaseFireBlock {

    /**
     * {@link Integer The fire block light level}
     */
    public static final int LIGHT_LEVEL = 13;

    /**
     * Constructor. Set the block properties
     */
    public EndFireBlock() {
        super(PropertyHelper.basicBlockProperties(MapColor.COLOR_PURPLE, 0F, 0F, false, SoundType.WOOL)
                .replaceable()
                .noCollission()
                .instabreak()
                .lightLevel(state -> LIGHT_LEVEL)
                .pushReaction(PushReaction.DESTROY), 1.5F);
    }

    /**
     * Update the {@link BlockState BlockState} based on neighbor updates
     *
     * @param state {@link BlockState The current BlockState}
     * @param facing {@link Direction The update Direction}
     * @param neighborState {@link BlockState The neighbor BlockState}
     * @param level {@link LevelAccessor The Level reference}
     * @param pos {@link BlockPos The current BlockPos}
     * @param neighborPos {@link BlockPos The neighbor BlockPos}
     * @return Placed {@link BlockState The updated BlockState}
     */
    public @NotNull BlockState updateShape(final @NotNull BlockState state, final @NotNull Direction facing, final @NotNull BlockState neighborState, final @NotNull LevelAccessor level, final @NotNull BlockPos pos, final @NotNull BlockPos neighborPos) {
        return this.canSurvive(state, level, pos) ? this.defaultBlockState() : Blocks.AIR.defaultBlockState();
    }

    /**
     * Checks if the {@link BaseFireBlock fire block} should extinguish
     *
     * @param state {@link BlockState The current BlockState}
     * @param level {@link LevelReader The Level reference}
     * @param pos {@link BlockPos The current BlockPos}
     * @return {@link Boolean True} if the {@link BaseFireBlock fire block} should extinguish
     */
    public boolean canSurvive(final @NotNull BlockState state, final @NotNull LevelReader level, final @NotNull BlockPos pos) {
        return canSurviveOnBlock(level.getBlockState(pos.below()));
    }

    /**
     * Check if the {@link BaseFireBlock fire block} can survive upon another block
     *
     * @param state {@link BlockState The BlockState} the fire is upon
     * @return {@link Boolean True if the fire block can survive}
     */
    public static boolean canSurviveOnBlock(final BlockState state) {
        return state.is(MWTags.Blocks.END_FIRE_BASE_BLOCKS);
    }

    /**
     * Check if this block can burn other entities
     *
     * @param state {@link BlockState The current BlockState}
     * @return {@link Boolean True}
     */
    protected boolean canBurn(final @NotNull BlockState state) {
        return true;
    }
}
