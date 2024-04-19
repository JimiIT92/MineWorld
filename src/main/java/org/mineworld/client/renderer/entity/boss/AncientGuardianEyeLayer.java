package org.mineworld.client.renderer.entity.boss;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.client.model.entity.boss.AncientGuardianModel;
import org.mineworld.entity.boss.AncientGuardianBoss;
import org.mineworld.helper.TextureHelper;

/**
 * Render layer for the {@link AncientGuardianBoss Ancient Guardian} eye
 */
@OnlyIn(Dist.CLIENT)
public class AncientGuardianEyeLayer extends EyesLayer<AncientGuardianBoss, AncientGuardianModel> {

    /**
     * {@link RenderType Ancient Guardian Eye Render Type}
     */
    private static final RenderType ANCIENT_GUARDIAN_EYE = RenderType.entityCutout(TextureHelper.entity("ancient_guardian/ancient_guardian_eye"));
    /**
     * {@link RenderType Ancient Guardian Agry Eye Render Type}
     */
    private static final RenderType ANCIENT_GUARDIAN_ANGRY_EYE = RenderType.entityCutout(TextureHelper.entity("ancient_guardian/ancient_guardian_eye_angry"));

    /**
     * Layer constructor
     *
     * @param parent {@link RenderLayerParent The parent Renderer}
     */
    public AncientGuardianEyeLayer(final RenderLayerParent<AncientGuardianBoss, AncientGuardianModel> parent) {
        super(parent);
    }

    /**
     * Render the layer
     *
     * @param poseStack The {@link PoseStack Entity Pose Stack}
     * @param multiBufferSource The {@link MultiBufferSource Entity Multi Buffer Source}
     * @param packedLight The {@link Integer client packed light}
     * @param entity The {@link AncientGuardianBoss entity}
     * @param limbSwing The {@link Float entity limb swing value}
     * @param limbSwingAmount The {@link Float entity limb swing amount}
     * @param partialTick The {@link Float entity partial ticks}
     * @param ageInTicks The {@link Float entity age in ticks}
     * @param netHeadYaw The {@link Float entity head yaw value}
     * @param headPitch The {@link Float entity head pitch value}
     */
    @Override
    public void render(final @NotNull PoseStack poseStack, final MultiBufferSource multiBufferSource, final int packedLight, final AncientGuardianBoss entity, final float limbSwing, final float limbSwingAmount, final float partialTick, final float ageInTicks, final float netHeadYaw, final float headPitch) {
        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(ANCIENT_GUARDIAN_EYE);
        if (entity.isAggressive()) {
            vertexconsumer = multiBufferSource.getBuffer(ANCIENT_GUARDIAN_ANGRY_EYE);
        }
        this.getParentModel().renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F, 1.0F);
    }

    /**
     * Get the {@link RenderType Render Type}
     *
     * @return {@link #ANCIENT_GUARDIAN_EYE The Ancient Guardian Eye Render Type}
     */
    public @NotNull RenderType renderType() {
        return ANCIENT_GUARDIAN_EYE;
    }

}