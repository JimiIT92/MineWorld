package org.mineworld.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.entity.Reaper;

/**
 * {@link MineWorld MineWorld} {@link HierarchicalModel Reaper Model}
 */
@OnlyIn(Dist.CLIENT)
public class ReaperModel extends HierarchicalModel<Reaper> implements ArmedModel {

    /**
     * The {@link ModelLayerLocation Model Layer Location}
     */
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MineWorld.MOD_ID, "reaper"), "main");
    /**
     * {@link ModelPart The Root Model Part}
     */
    private final ModelPart root;
    /**
     * {@link ModelPart The Body Model Part}
     */
    private final ModelPart body;
    /**
     * {@link ModelPart The Right Arm Model Part}
     */
    private final ModelPart rightArm;
    /**
     * {@link ModelPart The Left Arm Model Part}
     */
    private final ModelPart leftArm;
    /**
     * {@link ModelPart The Head Model Part}
     */
    private final ModelPart head;

    /**
     * Constructor. Set the {@link ModelPart Root Model Part}
     *
     * @param root {@link ModelPart The Root Model Part}
     */
    public ReaperModel(final ModelPart root) {
        super(RenderType::entityTranslucent);
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.rightArm = this.body.getChild("right_arm");
        this.leftArm = this.body.getChild("left_arm");
        this.head = this.root.getChild("head");
    }

    /**
     * Create the {@link LayerDefinition Model Layer Definition}
     *
     * @return {@link LayerDefinition The Model Layer Definition}
     */
    public static LayerDefinition createBodyLayer() {
        final MeshDefinition meshDefinition = new MeshDefinition();
        final PartDefinition root = meshDefinition.getRoot().addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, -2.5F, 0.0F));
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -5.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 0.0F));
        final PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 10).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 16).addBox(-1.5F, 1.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, 20.0F, 0.0F));
        body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(23, 0).addBox(-1.25F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(-1.75F, 0.25F, 0.0F));
        body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(23, 6).addBox(-0.75F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(1.75F, 0.25F, 0.0F));
        return LayerDefinition.create(meshDefinition, 32, 32);
    }

    /**
     * Setup the {@link HierarchicalModel Model} Animations
     *
     * @param entity {@link LivingEntity The Entity}
     * @param limbSwing {@link Float The Entity Limb Swing}
     * @param limbSwingAmount {@link Float The Entity Limb Swing amount}
     * @param ageInTicks {@link Float The Entity age in ticks}
     * @param headYaw {@link Float The Entity head yaw}
     * @param headPitch {@link Float The Entity head pitch}
     */
    @Override
    public void setupAnim(final Reaper entity, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float headYaw, final float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.head.yRot = headYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        final float armAngle = Mth.cos(ageInTicks * 5.5F * ((float)Math.PI / 180F)) * 0.1F;
        this.rightArm.zRot = ((float)Math.PI / 5F) + armAngle;
        this.leftArm.zRot = -(((float)Math.PI / 5F) + armAngle);
        if (entity.isCharging()) {
            this.body.xRot = 0.0F;
            this.setArmsCharging(entity.getMainHandItem(), entity.getOffhandItem(), armAngle);
        } else {
            this.body.xRot = 0.15707964F;
        }
    }

    /**
     * Set the {@link Reaper Reaper} arms charging
     *
     * @param rightArmStack {@link ItemStack The right arm hold Item Stack}
     * @param leftArmStack {@link ItemStack The left arm Item Stack}
     * @param angle {@link Float The arm angle}
     */
    private void setArmsCharging(final ItemStack rightArmStack, final ItemStack leftArmStack, final float angle) {
        if (rightArmStack.isEmpty() && leftArmStack.isEmpty()) {
            this.rightArm.xRot = -1.2217305F;
            this.rightArm.yRot = 0.2617994F;
            this.rightArm.zRot = -0.47123888F - angle;
            this.leftArm.xRot = -1.2217305F;
            this.leftArm.yRot = -0.2617994F;
            this.leftArm.zRot = 0.47123888F + angle;
        } else {
            if (!rightArmStack.isEmpty()) {
                this.rightArm.xRot = 3.6651914F;
                this.rightArm.yRot = 0.2617994F;
                this.rightArm.zRot = -0.47123888F - angle;
            }

            if (!leftArmStack.isEmpty()) {
                this.leftArm.xRot = 3.6651914F;
                this.leftArm.yRot = -0.2617994F;
                this.leftArm.zRot = 0.47123888F + angle;
            }

        }
    }

    /**
     * Get the {@link ModelPart Root Model Part}
     *
     * @return The {@link #root Root Model Part}
     */
    @Override
    public @NotNull ModelPart root() {
        return this.root;
    }

    /**
     * Translate the held {@link ItemStack Item Stack}
     *
     * @param arm {@link HumanoidArm The arm holding the Item}
     * @param poseStack {@link PoseStack The Entity Pose Stack}
     */
    @Override
    public void translateToHand(final @NotNull HumanoidArm arm, final @NotNull PoseStack poseStack) {
        boolean isRightArm = arm == HumanoidArm.RIGHT;
        this.root.translateAndRotate(poseStack);
        this.body.translateAndRotate(poseStack);
        (isRightArm ? this.rightArm : this.leftArm).translateAndRotate(poseStack);
        poseStack.scale(0.55F, 0.55F, 0.55F);
        poseStack.translate(0.046875D * (isRightArm ? 1 : -1), -0.15625D, 0.078125D);
    }

}