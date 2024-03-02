package org.mineworld.client.renderer.block;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.entity.block.chest.MWChestBlockEntity;
import org.mineworld.helper.TextureHelper;

/**
 * Renderer class for a {@link MineWorld MineWorld} {@link MWChestBlockEntity Chest Block Entity}
 */
@OnlyIn(Dist.CLIENT)
public class MWChestRenderer extends ChestRenderer<MWChestBlockEntity> {

    /**
     * Constructor. Set the renderer properties
     *
     * @param context {@link EntityRendererProvider.Context The Render Context}
     */
    public MWChestRenderer(final BlockEntityRendererProvider.Context context) {
        super(context);
    }

    /**
     * Get the {@link Material Chest Material} based on its {@link ChestType Chest Type}
     *
     * @param chest {@link ChestBlockEntity The Chest Block Entity}
     * @param chestType {@link ChestType The Chest Type}
     * @return {@link Material The Chest Material}
     */
    @Override
    protected @NotNull Material getMaterial(final @NotNull MWChestBlockEntity chest, final @NotNull ChestType chestType) {
        return new Material(Sheets.CHEST_SHEET, TextureHelper.chest(chest.getWoodType(), chestType, false));
    }

}
