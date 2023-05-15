package org.mineworld.client.renderer.blockentity.chest;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.KeyHelper;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link ChestRenderer Renderer} class for a {@link MineWorld MineWorld} chest
 */
@OnlyIn(Dist.CLIENT)
abstract class AbstractChestRenderer extends ChestRenderer<ChestBlockEntity> {

    /**
     * {@link Material The single chest texture location}
     */
    private final Material CHEST_LOCATION = getMaterial("normal");
    /**
     * {@link Material The left chest texture location}
     */
    private final Material CHEST_LOCATION_LEFT = getMaterial("normal_left");
    /**
     * {@link Material The right chest texture location}
     */
    private final Material CHEST_LOCATION_RIGHT = getMaterial("normal_right");
    /**
     * {@link Material The single trapped chest texture location}
     */
    private final Material TRAPPED_CHEST_LOCATION = getMaterial("trapped");
    /**
     * {@link Material The left trapped chest texture location}
     */
    private final Material TRAPPED_CHEST_LOCATION_LEFT = getMaterial("trapped_left");
    /**
     * {@link Material The right trapped chest texture location}
     */
    private final Material TRAPPED_CHEST_LOCATION_RIGHT = getMaterial("trapped_right");

    /**
     * Constructor. Set up the {@link BlockEntityRendererProvider.Context render context}
     *
     * @param context {@link BlockEntityRendererProvider.Context The block render context}
     */

    public AbstractChestRenderer(final BlockEntityRendererProvider.Context context) {
        super(context);
    }

    /**
     * Check if the chest is a trapped chest
     *
     * @return {@link Boolean True if is a trapped chest}
     */
    protected boolean isTrappedChest() {
        return false;
    }

    /**
     * Get the {@link Material chest material}
     *
     * @param blockEntity {@link ChestBlockEntity The chest block entity}
     * @param chestType {@link ChestType The chest type}
     * @return {@link Material The chest material}
     */
    @Override
    protected @NotNull Material getMaterial(final @NotNull ChestBlockEntity blockEntity, final ChestType chestType) {
        final boolean isTrapped = isTrappedChest();
        return switch (chestType) {
            case LEFT -> isTrapped ? TRAPPED_CHEST_LOCATION_LEFT : CHEST_LOCATION_LEFT;
            case RIGHT -> isTrapped ? TRAPPED_CHEST_LOCATION_RIGHT : CHEST_LOCATION_RIGHT;
            default -> isTrapped ? TRAPPED_CHEST_LOCATION : CHEST_LOCATION;
        };
    }

    /**
     * Get the {@link Material chest material} based on its type
     *
     * @param type {@link String The chest type}
     * @return {@link Material The chest material}
     */
    private Material getMaterial(final String type) {
        return new Material(Sheets.CHEST_SHEET, KeyHelper.entity("chest/" + PropertyHelper.getWoodTypeName(getWoodType()) + "/" + type));
    }

    /**
     * Get the {@link WoodType chest wood type}
     *
     * @return {@link WoodType The chest wood type}
     */
    protected abstract WoodType getWoodType();

}