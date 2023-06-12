package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link WallSkullBlock wall skull block}
 */
public class MWWallSkullBlock extends WallSkullBlock {

    /**
     * Constructor. Set the {@link SkullBlock.Type skull block type}
     *
     * @param type {@link SkullBlock.Type The skull block type}
     * @param properties {@link Properties The block properties}
     */
    public MWWallSkullBlock(final SkullBlock.Type type, final Properties properties) {
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
    public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
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

}
