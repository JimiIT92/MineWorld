package org.mineworld.client.model;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineworld.MineWorld;
import org.mineworld.core.MWArmorMaterials;
import org.mineworld.helper.ResourceHelper;

/**
 * {@link MineWorld MineWorld} {@link HatModel Witch Hat Model}
 */
@OnlyIn(Dist.CLIENT)
public class WitchHatModel extends HatModel {

    /**
     * The {@link ModelLayerLocation Model Layer Location}
     */
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MineWorld.MOD_ID, ResourceHelper.hat(MWArmorMaterials.WITCH)), "main");

    /**
     * Constructor. Set the {@link ModelPart Root Model Part}
     *
     * @param root {@link ModelPart The Root Model Part}
     */
    public WitchHatModel(final ModelPart root) {
        super(root);
    }

    /**
     * Create the {@link LayerDefinition Model Layer Definition}
     *
     * @return {@link LayerDefinition The Model Layer Definition}
     */
    public static LayerDefinition createBodyLayer() {
        final MeshDefinition meshDefinition = new MeshDefinition();
        final PartDefinition partDefinition = HatModel.getBasePartDefinition(meshDefinition);

        final PartDefinition hat = partDefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        final PartDefinition armorHead = hat.addOrReplaceChild("armorHead", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        final PartDefinition head = armorHead.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        final PartDefinition hat1 = head.addOrReplaceChild("hat1", CubeListBuilder.create()
                        .texOffs(0, 64).addBox(0.0F, 0.0F, 0.0F, 10.0F, 2.0F, 10.0F),
                PartPose.offset(-5.0F, -8.03125F, -5.0F));
        final PartDefinition hat2 = hat1.addOrReplaceChild("hat2", CubeListBuilder.create()
                        .texOffs(0, 76).addBox(0.0F, 0.0F, 0.0F, 7.0F, 4.0F, 7.0F),
                PartPose.offsetAndRotation(1.75F, -4.0F, 2.0F, -0.05235988F, 0.0F, 0.02617994F));
        final PartDefinition hat3 = hat2.addOrReplaceChild("hat3", CubeListBuilder.create()
                        .texOffs(0, 87).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F),
                PartPose.offsetAndRotation(1.75F, -4.0F, 2.0F, -0.10471976F, 0.0F, 0.05235988F));
        hat3.addOrReplaceChild("hat4", CubeListBuilder.create()
                        .texOffs(0, 95).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)),
                PartPose.offsetAndRotation(1.75F, -2.0F, 2.0F, -0.20943952F, 0.0F, 0.10471976F));

        return LayerDefinition.create(meshDefinition, 64, 128);
    }

}