package org.mineworld.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.client.model.entity.ReaperModel;
import org.mineworld.entity.Reaper;
import org.mineworld.helper.TextureHelper;

/**
 * Renderer class for a {@link MineWorld MineWorld} {@link Reaper Reaper}
 */
@OnlyIn(Dist.CLIENT)
public class ReaperRenderer extends MobRenderer<Reaper, ReaperModel> {

    /**
     * Constructor. Set the renderer properties
     *
     * @param context {@link EntityRendererProvider.Context The Render Context}
     */
    public ReaperRenderer(final EntityRendererProvider.Context context) {
        super(context, new ReaperModel(context.bakeLayer(ReaperModel.LAYER_LOCATION)), 0F);
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
    }

    /**
     * Scale the {@link ReaperModel Entity Model}
     *
     * @param entity {@link Reaper The Entity}
     * @param poseStack {@link PoseStack The Entity Pose Stack}
     * @param partialTicks {@link Float The Entity partial Ticks}
     */
    @Override
    protected void scale(final @NotNull Reaper entity, final PoseStack poseStack, final float partialTicks) {
        final float scale = 2F;
        poseStack.scale(scale, scale, scale);
    }

    /**
     * Get the {@link ResourceLocation Entity texture}
     *
     * @param reaper {@link Reaper The Reaper}
     * @return {@link ResourceLocation The Entity Texture Location}
     */
    @Override
    public @NotNull ResourceLocation getTextureLocation(final @NotNull Reaper reaper) {
        return TextureHelper.entity("reaper/reaper");
    }

}