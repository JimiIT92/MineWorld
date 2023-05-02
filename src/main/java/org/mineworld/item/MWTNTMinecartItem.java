package org.mineworld.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.block.MWTntBlock;
import org.mineworld.entity.MWPrimedTnt;

import java.util.List;

/**
 * Implementation class for a {@link MinecartItem minecart id}
 */
public class MWTNTMinecartItem extends MWMinecartItem {

    /**
     * {@link MWPrimedTnt.Type The tnt type}
     */
    private final MWPrimedTnt.Type tntType;

    /**
     * Constructor. Set the {@link Type minecart type}
     *
     * @param type {@link Type The minecart type}
     * @param tntType {@link MWPrimedTnt.Type The tnt type}
     */
    public MWTNTMinecartItem(final Type type, final MWPrimedTnt.Type tntType) {
        super(type);
        this.tntType = tntType;
    }

    /**
     * Add tooltips to the disguised tnts
     *
     * @param itemStack {@link ItemStack The current id stack}
     * @param level {@link Level The world reference}
     * @param tooltips {@link List<Component> The current tooltips}
     * @param tooltipFlag {@link TooltipFlag The tooltip flag}
     */
    @Override
    public void appendHoverText(final @NotNull ItemStack itemStack, final @Nullable Level level, @NotNull List<Component> tooltips, final @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, tooltips, tooltipFlag);
        MWTntBlock.appendTntHoverText(tooltips, this.tntType);
    }

}