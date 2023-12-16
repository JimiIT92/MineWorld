package org.mineworld.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.client.model.ReaperModel;
import org.mineworld.entity.Reaper;
import org.mineworld.helper.KeyHelper;

/**
 * Renderer class for a {@link Reaper reaper entity}
 */
@OnlyIn(Dist.CLIENT)
public class ReaperRenderer extends MobRenderer<Reaper, ReaperModel> {

    /**
     * Constructor. Set the renderer context
     *
     * @param context {@link EntityRendererProvider.Context The renderer context}
     */
    public ReaperRenderer(final EntityRendererProvider.Context context) {
        super(context, new ReaperModel(context.bakeLayer(ReaperModel.LAYER_LOCATION)), 0F);
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
    }

    /**
     * Get the {@link ResourceLocation entity texture location}
     *
     * @param reaper {@link Reaper The reaper entity}
     * @return {@link ResourceLocation The entity texture location}
     */
    @Override
    public @NotNull ResourceLocation getTextureLocation(final @NotNull Reaper reaper) {
        return KeyHelper.entityTexture("reaper/reaper");
    }

}