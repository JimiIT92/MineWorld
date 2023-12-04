package org.mineworld.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link HangingSignBlockEntity hanging sign block entity}
 */
public class MWHangingSignBlockEntity extends SignBlockEntity {

    /**
     * Constructor. Set the block entity properties
     *
     * @param pos {@link BlockPos The current BlockPos}
     * @param state {@link BlockState The current BlockState}
     */
    public MWHangingSignBlockEntity(final BlockPos pos, final BlockState state) {
        super(MWBlockEntityTypes.HANGING_SIGN.get(), pos, state);
    }

    /**
     * Get the {@link Integer sign text line height}
     *
     * @return {@link Integer 9}
     */
    public int getTextLineHeight() {
        return 9;
    }

    /**
     * Get the {@link Integer max sign text line width}
     *
     * @return {@link Integer 60}
     */
    public int getMaxTextLineWidth() {
        return 60;
    }

    /**
     * Get the {@link BlockEntityType hanging sign block entity type}
     * @return {@link MWBlockEntityTypes#HANGING_SIGN The hanging sign block entity type}
     */
    @Override
    public @NotNull BlockEntityType<?> getType() {
        return MWBlockEntityTypes.HANGING_SIGN.get();
    }

}