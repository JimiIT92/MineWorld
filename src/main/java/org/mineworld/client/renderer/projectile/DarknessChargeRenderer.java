package org.mineworld.client.renderer.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.entity.projectile.DarknessCharge;
import org.mineworld.helper.TextureHelper;

/**
 * Renderer class for a {@link MineWorld MineWorld} {@link DarknessCharge Darkness Charge}
 */
@OnlyIn(Dist.CLIENT)
public class DarknessChargeRenderer extends EntityRenderer<DarknessCharge> {

    /**
     * The {@link SkullModel Skull Model}
     */
    private final SkullModel model;

    /**
     * Constructor. Set the renderer properties
     *
     * @param context {@link EntityRendererProvider.Context The Render Context}
     */
    public DarknessChargeRenderer(final EntityRendererProvider.Context context) {
        super(context);
        this.model = new SkullModel(context.bakeLayer(ModelLayers.PLAYER_HEAD));
    }

    /**
     * Create the {@link LayerDefinition Skull Layer Definition}
     *
     * @return {@link LayerDefinition The Skull Layer Definition}
     */
    public static LayerDefinition createSkullLayer() {
        final MeshDefinition meshDefinition = new MeshDefinition();
        final PartDefinition root = meshDefinition.getRoot();
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 35).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    /**
     * Get the {@link Integer Entity Block Light level}
     *
     * @param entity The {@link LivingEntity Entity}
     * @param blockPos The {@link BlockPos current Block Pos}
     * @return {@link Integer 15}
     */
    @Override
    protected int getBlockLightLevel(final @NotNull DarknessCharge entity, final @NotNull BlockPos blockPos) {
        return 15;
    }
    /**
     * Render the {@link DarknessCharge Darkness Charge}
     *
     * @param darknessCharge {@link DarknessCharge The Darkness Charge}
     * @param yaw {@link Float The Entity yaw}
     * @param partialTicks {@link Float The Entity partial ticks}
     * @param poseStack {@link PoseStack The Entity pose}
     * @param buffer {@link MultiBufferSource The Entity buffer}
     * @param packedLight {@link Integer The level packed light}
     */
    @Override
    public void render(final @NotNull DarknessCharge darknessCharge, final float yaw, final float partialTicks, final PoseStack poseStack, final MultiBufferSource buffer, final int packedLight) {
        poseStack.pushPose();
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        final VertexConsumer vertexConsumer = buffer.getBuffer(this.model.renderType(this.getTextureLocation(darknessCharge)));
        this.model.setupAnim(0.0F, Mth.rotLerp(partialTicks, darknessCharge.yRotO, darknessCharge.getYRot()), Mth.lerp(partialTicks, darknessCharge.xRotO, darknessCharge.getXRot()));
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(darknessCharge, yaw, partialTicks, poseStack, buffer, packedLight);
    }

    /**
     * Get the {@link ResourceLocation Entity texture}
     *
     * @param darknessCharge {@link DarknessCharge The Darkness Charge}
     * @return {@link ResourceLocation The Entity Texture Location}
     */
    @Override
    public @NotNull ResourceLocation getTextureLocation(final @NotNull DarknessCharge darknessCharge) {
        return TextureHelper.entity("darkness_charge/darkness_charge");
    }

}