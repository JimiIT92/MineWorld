package org.mineworld.client.model.entity.boss;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.entity.boss.AncientGuardianBoss;

/**
 * {@link MineWorld MineWorld} {@link HierarchicalModel Ancient Guardian Model}
 */
@OnlyIn(Dist.CLIENT)
public class AncientGuardianModel extends HierarchicalModel<AncientGuardianBoss> {

    /**
     * The {@link ModelLayerLocation Model Layer Location}
     */
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MineWorld.MOD_ID, "ancient_guardian"), "main");
    /**
     * The {@link ModelLayerLocation Armor Model Layer Location}
     */
    public static final ModelLayerLocation ARMOR_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MineWorld.MOD_ID, "ancient_guardian"), "armor");

    /**
     * {@link ModelPart The Root Model Part}
     */
    private final ModelPart root;

    /**
     * Constructor. Set the {@link ModelPart Root Model Part}
     *
     * @param root {@link ModelPart The Root Model Part}
     */
    public AncientGuardianModel(final ModelPart root) {
        this.root = root.getChild("root");
    }

    /**
     * Create the {@link LayerDefinition Model Layer Definition}
     *
     * @return {@link LayerDefinition The Model Layer Definition}
     */
    public static LayerDefinition createBodyLayer() {
        final MeshDefinition meshDefinition = new MeshDefinition();
        final PartDefinition root = meshDefinition.getRoot().addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        root.addOrReplaceChild("left_horn", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F))
                .addOrReplaceChild("left_horn_r1", CubeListBuilder.create().texOffs(9, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.3959F, -9.2287F, -2.0F, 0.0F, 0.0F, -0.9163F));
        root.addOrReplaceChild("right_horn", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F))
                .addOrReplaceChild("right_horn_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.2426F, -8.4853F, -2.0F, 0.0F, 0.0F, 0.7854F));

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
    public void setupAnim(final @NotNull AncientGuardianBoss entity, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float headYaw, final float headPitch) {

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

}