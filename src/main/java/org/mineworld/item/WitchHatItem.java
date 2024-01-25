package org.mineworld.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.client.model.WitchHatModel;
import org.mineworld.core.MWArmorMaterials;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.helper.ResourceHelper;
import org.mineworld.helper.TextureHelper;

/**
 * {@link MineWorld MineWorld} {@link MWHatItem Witch Hat Item}
 */
public class WitchHatItem extends MWHatItem {

    /**
     * Constructor. Set the {@link Properties Item Properties}
     */
    public WitchHatItem() {
        super(MWArmorMaterials.WITCH, PropertyHelper.item().rarity(Rarity.UNCOMMON));
    }

    /**
     * Get the {@link String Armor Texture path}
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @param entity {@link Entity The Entity wearing the Armor}
     * @param slot {@link EquipmentSlot The Armor Slot}
     * @param layer {@link String The Armor layer, can be null or "overlay"}
     * @return {@link String The Armor Texture path}
     */
    @Override
    public String getArmorTexture(final ItemStack itemStack, final Entity entity, final EquipmentSlot slot, final @Nullable String layer) {
        return ResourceHelper.path(TextureHelper.entity("witch"));
    }

    /**
     * Get the {@link IClientItemExtensions Armor Model Renderer}
     *
     * @return {@link IClientItemExtensions The Armor Model Renderer}
     */
    @Override
    public IClientItemExtensions getArmorRenderer() {
        return ArmorRender.INSTANCE;
    }

    /**
     * {@link MineWorld MineWorld} Witch Hat {@link IClientItemExtensions Armor Model Renderer}
     */
    private static final class ArmorRender implements IClientItemExtensions {

        /**
         * The {@link ArmorRender Armor Model Renderer Instance}
         */
        private static final ArmorRender INSTANCE = new ArmorRender();

        /**
         * Get the {@link HumanoidModel Armor Model}
         *
         * @param entity {@link LivingEntity The Entity wearing the Armor}
         * @param itemStack {@link ItemStack The current Item Stack}
         * @param slot {@link EquipmentSlot The Armor Slot}
         * @param model {@link HumanoidModel The original Armor Model. Will have attributes set}
         * @return {@link HumanoidModel The Armor Model}
         */
        @Override
        public @NotNull HumanoidModel<?> getHumanoidArmorModel(final LivingEntity entity, final ItemStack itemStack, final EquipmentSlot slot, final HumanoidModel<?> model) {
            return new WitchHatModel(Minecraft.getInstance().getEntityModels().bakeLayer(WitchHatModel.LAYER_LOCATION));
        }
    }

}