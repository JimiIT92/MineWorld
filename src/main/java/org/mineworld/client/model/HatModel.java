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
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} {@link HumanoidModel Hat Model}
 */
@OnlyIn(Dist.CLIENT)
public abstract class HatModel extends HumanoidModel<LivingEntity> {

    /**
     * Constructor. Set the {@link ModelPart Root Model Part}
     *
     * @param root {@link ModelPart The Root Model Part}
     */
    public HatModel(final ModelPart root) {
        super(root);
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
     * Render the {@link HumanoidModel Model}
     *
     * @param poseStack {@link PoseStack The Model Pose Stack}
     * @param vertexConsumer {@link VertexConsumer The Vertex Consumer reference}
     * @param packedLight {@link Integer The Model Packed light}
     * @param packedOverlay {@link Integer The Model Packed overlay}
     * @param red {@link Float The Model red channel value}
     * @param green {@link Float The Model green channel value}
     * @param blue {@link Float The Model blue channel value}
     * @param alpha {@link Float The Model alpha channel value}
     */
    @Override
    public void renderToBuffer(final @NotNull PoseStack poseStack, final @NotNull VertexConsumer vertexConsumer, final int packedLight, final int packedOverlay, final float red, final float green, final float blue, final float alpha) {
        this.hat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    /**
     * Setup the {@link HumanoidModel Model} Animations
     *
     * @param entity {@link LivingEntity The Entity}
     * @param limbSwing {@link Float The Entity Limb Swing}
     * @param limbSwingAmount {@link Float The Entity Limb Swing amount}
     * @param ageInTicks {@link Float The Entity age in ticks}
     * @param headYaw {@link Float The Entity head yaw}
     * @param headPitch {@link Float The Entity head pitch}
     */
    @Override
    public void setupAnim(final @NotNull LivingEntity entity, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float headYaw, final float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch);
    }

}
