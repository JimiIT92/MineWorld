package org.mineworld.client.renderer.blockentity.chest;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineworld.core.MWWoodTypes;

/**
 * {@link ChestRenderer Renderer} class for an ice chest
 */
@OnlyIn(Dist.CLIENT)
public class IceChestRenderer extends AbstractChestRenderer {

    /**
     * Constructor. Set up the {@link BlockEntityRendererProvider.Context render context}
     *
     * @param context {@link BlockEntityRendererProvider.Context The block render context}
     */
    public IceChestRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    /**
     * Get the {@link WoodType chest wood type}
     *
     * @return {@link MWWoodTypes#ICE Ice wood type}
     */
    @Override
    protected WoodType getWoodType() {
        return MWWoodTypes.ICE;
    }

}