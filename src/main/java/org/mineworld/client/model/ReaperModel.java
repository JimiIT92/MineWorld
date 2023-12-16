package org.mineworld.client.model;

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
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.entity.Reaper;

/**
 * Model class for a {@link Reaper Reaper} entity
 */
@OnlyIn(Dist.CLIENT)
public class ReaperModel extends HierarchicalModel<Reaper> implements ArmedModel {

    /**
     * The {@link ModelLayerLocation Model Layer Location}
     */
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MineWorld.MOD_ID, "reaper"), "main");
    /**
     * The {@link ModelPart root model part}
     */
    private final ModelPart root;
    /**
     * The {@link ModelPart body model part}
     */
    private final ModelPart body;
    /**
     * The {@link ModelPart right arm model part}
     */
    private final ModelPart rightArm;
    /**
     * The {@link ModelPart left arm model part}
     */
    private final ModelPart leftArm;
    /**
     * The {@link ModelPart head model part}
     */
    private final ModelPart head;

    /**
     * Constructor. Set the entity {@link ModelPart model parts}
     *
     * @param modelPart {@link ModelPart The root model part}
     */
    public ReaperModel(final ModelPart modelPart) {
        super(RenderType::entityTranslucent);
        this.root = modelPart.getChild("root");
        this.body = this.root.getChild("body");
        this.rightArm = this.body.getChild("right_arm");
        this.leftArm = this.body.getChild("left_arm");
        this.head = this.root.getChild("head");
    }

    /**
     * Create the entity body layer
     *
     * @return {@link LayerDefinition The entity layer definition}
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
     * Setup the {@link Reaper Reper} animations
     *
     * @param reaper {@link Reaper The Reaper entity}
     * @param limbSwing {@link Float The entity limb swing angle}
     * @param limbSwingAmount {@link Float The entity limb swing amount}
     * @param ageInTicks {@link Float The entity age in ticks}
     * @param netHeadYaw {@link Float The entity head yaw value}
     * @param headPitch {@link Float The entity head pitch value}
     */
    @Override
    public void setupAnim(final Reaper reaper, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        final float armAngle = Mth.cos(ageInTicks * 5.5F * ((float)Math.PI / 180F)) * 0.1F;
        this.rightArm.zRot = ((float)Math.PI / 5F) + armAngle;
        this.leftArm.zRot = -(((float)Math.PI / 5F) + armAngle);
        if (reaper.isCharging()) {
            this.body.xRot = 0.0F;
            this.setArmsCharging(reaper.getMainHandItem(), reaper.getOffhandItem(), armAngle);
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
     * Get the {@link ModelPart root Model Part}
     *
     * @return The {@link #root root Model Part}
     */
    @Override
    public @NotNull ModelPart root() {
        return this.root;
    }

    /**
     * Translate the hold {@link ItemStack Item Stack}
     *
     * @param arm {@link HumanoidArm The arm holding the Item}
     * @param poseStack {@link PoseStack The entity pose stack}
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