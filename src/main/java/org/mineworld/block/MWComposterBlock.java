package org.mineworld.block;

import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.mineworld.MineWorld;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.helper.ResourceHelper;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link ComposterBlock Composter Block}
 */
public class MWComposterBlock extends ComposterBlock {

    /**
     * Constructor. Set the {@link Properties Block Properties}
     *
     * @param woodTypeSupplier {@link Supplier<WoodType> The Supplier for the Wood Type this Composter is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public MWComposterBlock(final Supplier<WoodType> woodTypeSupplier, final FeatureFlag... featureFlags) {
        super(PropertyHelper.copy(Blocks.COMPOSTER, featureFlags).mapColor(ResourceHelper.woodColor(woodTypeSupplier.get()))
                .instrument(NoteBlockInstrument.BASS).strength(0.6F)
                .sound(woodTypeSupplier.get().soundType()).ignitedByLava()
        );
    }

}