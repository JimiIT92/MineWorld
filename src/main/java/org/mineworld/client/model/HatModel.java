package org.mineworld.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

/**
 * Model class for a Hat
 */
@OnlyIn(Dist.CLIENT)
public abstract class HatModel extends HumanoidModel<LivingEntity> {

    /**
     * Constructor. Set the {@link ModelPart root model part}
     *
     * @param modelPart {@link ModelPart The root model part}
     */
    public HatModel(final ModelPart modelPart) {
        super(modelPart);
    }

    /**
     * Get the {@link PartDefinition base part definition}
     *
     * @param meshDefinition {@link MeshDefinition The model mesh definition}
     * @return {@link PartDefinition The base part definition}
     */
    public static PartDefinition getBasePartDefinition(final MeshDefinition meshDefinition) {
        final PartDefinition partDefinition = meshDefinition.getRoot();
        final PartDefinition head = partDefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        final PartDefinition hat = partDefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        final PartDefinition body = partDefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        final PartDefinition rightArm = partDefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        final PartDefinition leftArm = partDefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        final PartDefinition rightLeg = partDefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        final PartDefinition leftLeg = partDefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        return partDefinition;
    }

    /**
     * Render the model
     *
     * @param poseStack {@link PoseStack The model pose stack}
     * @param vertexConsumer {@link VertexConsumer The model vertex consumer}
     * @param packedLight {@link Integer The model packed light}
     * @param packedOverlay {@link Integer The model packed overlay}
     * @param red {@link Float The model red channel value}
     * @param green {@link Float The model green channel value}
     * @param blue {@link Float The model blue channel value}
     * @param alpha {@link Float The model alpha channel value}
     */
    @Override
    public void renderToBuffer(final @NotNull PoseStack poseStack, final @NotNull VertexConsumer vertexConsumer, final int packedLight, final int packedOverlay, final float red, final float green, final float blue, final float alpha) {
        this.hat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    /**
     * Setup the model animations
     *
     * @param entity {@link LivingEntity The entity wearing the armor}
     * @param limbSwing {@link Float The entity limb swing}
     * @param limbSwingAmount {@link Float The entity limb swing amount}
     * @param ageInTicks {@link Float The model age in ticks}
     * @param netHeadYaw {@link Float The model head yaw}
     * @param headPitch {@link Float The model head pitch}
     */
    @Override
    public void setupAnim(final @NotNull LivingEntity entity, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

}