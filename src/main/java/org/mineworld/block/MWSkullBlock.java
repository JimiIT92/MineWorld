package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link SkullBlock skull block}
 */
public class MWSkullBlock extends SkullBlock {

    /**
     * Constructor. Set the {@link Type skull block type}
     *
     * @param type {@link Type The skull block type}
     * @param properties {@link Properties The block properties}
     */
    public MWSkullBlock(Type type, Properties properties) {
        super(type, properties);
    }

    /**
     * Get the {@link BlockEntity skull block entity}
     *
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     * @return {@link BlockEntity The skull block entity}
     */
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new SkullBlockEntity(blockPos, blockState) {

            /**
             * Get the {@link BlockEntityType skull block entity type}
             *
             * @return {@link BlockEntityType The skull block entity type}
             */
            @Override
            public @NotNull BlockEntityType<?> getType() {
                return MWBlockEntityTypes.SKULL.get();
            }
        };
    }

    /**
     * {@link MineWorld MineWorld} skull block types
     */
    public enum Types implements SkullBlock.Type {
        STRAY,
        HUSK,
        DROWNED
    }
}
