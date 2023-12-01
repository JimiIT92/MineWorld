package org.mineworld.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.client.model.StrawHatModel;
import org.mineworld.core.MWArmorMaterials;
import org.mineworld.helper.PropertyHelper;

/**
 * Implementation class for a Straw Hat item
 */
public class StrawHatItem extends MWHatItem {

    /**
     * Constructor. Set the item properties
     */
    public StrawHatItem() {
        super(MWArmorMaterials.STRAW, PropertyHelper.basicItemProperties().rarity(Rarity.UNCOMMON));
    }

    /**
     * Get the {@link String armor texture}
     *
     * @param stack {@link ItemStack The ItemStack for the equipped armor}
     * @param entity {@link Entity The entity wearing the armor}
     * @param slot {@link EquipmentSlot The slot the armor is in}
     * @param layer {@link String The subtype, can be null or "overlay"}
     * @return {@link String The armor texture}
     */
    @Override
    public String getArmorTexture(final ItemStack stack, final Entity entity, final EquipmentSlot slot, final @Nullable String layer) {
        return MineWorld.MOD_ID + ":textures/models/armor/straw_hat.png";
    }

    /**
     * Get the {@link IClientItemExtensions client armor renderer}
     *
     * @return The {@link IClientItemExtensions client armor renderer}
     */
    @Override
    public IClientItemExtensions getArmorRenderer() {
        return ArmorRender.INSTANCE;
    }

    /**
     * The Straw Hat {@link IClientItemExtensions armor renderer} implementation
     */
    private static final class ArmorRender implements IClientItemExtensions {

        /**
         * The {@link ArmorRender Armor Renderer} instance
         */
        private static final ArmorRender INSTANCE = new ArmorRender();

        /**
         * Get the {@link HumanoidModel armor model}
         *
         * @param living {@link LivingEntity The entity wearing the armor}
         * @param stack {@link ItemStack The item stack}
         * @param slot {@link EquipmentSlot The slot the item is in}
         * @param model {@link HumanoidModel The original armor model. Will have attributes set}
         * @return {@link HumanoidModel The armor model}
         */
        @Override
        public @NotNull HumanoidModel<?> getHumanoidArmorModel(final LivingEntity living, final ItemStack stack, final EquipmentSlot slot, final HumanoidModel<?> model) {
            final EntityModelSet models = Minecraft.getInstance().getEntityModels();
            final ModelPart root = models.bakeLayer(StrawHatModel.LAYER_LOCATION);
            return new StrawHatModel(root);
        }
    }

}
