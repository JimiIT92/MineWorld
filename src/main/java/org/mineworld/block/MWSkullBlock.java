package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.entity.block.MWSkullBlockEntity;
import org.mineworld.helper.PropertyHelper;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link SkullBlock Skull Block}
 */
public class MWSkullBlock extends SkullBlock {

    /**
     * Constructor. Set the {@link Type Skull Block Type}
     *
     * @param type {@link Type The Skull Block Type}
     */
    public MWSkullBlock(final Type type) {
        super(type, PropertyHelper.block(1.0F, false));
    }

    /**
     * Create the {@link MWSkullBlockEntity Skull Block Entity}
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link BlockEntity The Skull Block Entity}
     */
    @Override
    public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return new MWSkullBlockEntity(blockPos, blockState);
    }

    /**
     * {@link MineWorld MineWorld} Skull Block Types
     */
    public enum Types implements SkullBlock.Type {
        STRAY("stray"),
        HUSK("husk"),
        DROWNED("drowned");

        /**
         * {@link String The skull type name}
         */
        private final String name;

        /**
         * Constructor. Set the {@link String skull type name}
         *
         * @param name {@link String The skull type name}
         */
        Types(final String name) {
            this.name = name;
            TYPES.put(name, this);
        }

        /**
         * Get the {@link String skull type serialized name}
         *
         * @return The {@link String skull type serialized name}
         */
        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }
    }

}