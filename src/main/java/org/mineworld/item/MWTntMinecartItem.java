package org.mineworld.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.block.MWTntBlock;
import org.mineworld.entity.block.MWPrimedTnt;

import java.util.List;

/**
 * {@link MineWorld MineWorld} class for a {@link MWMinecartItem TNT Minecart Item}
 */
public class MWTntMinecartItem extends MWMinecartItem {

    /**
     * {@link MWPrimedTnt.Type The TNT Type}
     */
    private final MWPrimedTnt.Type tntType;

    /**
     * Constructor. Set the {@link Properties Item Properties}
     *
     * @param minecartType {@link Type The Minecart Type}
     * @param tntType {@link MWPrimedTnt.Type The TNT type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     */
    public MWTntMinecartItem(final Type minecartType, final MWPrimedTnt.Type tntType, final FeatureFlag... featureFlags) {
        super(minecartType, featureFlags);
        this.tntType = tntType;
    }

    /**
     * Add tooltips to the disguised TNT Minecart
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @param level {@link Level The level reference}
     * @param tooltips {@link List<Component> The current tooltips}
     * @param tooltipFlag {@link TooltipFlag The tooltip flag}
     */
    @Override
    public void appendHoverText(final @NotNull ItemStack itemStack, final @Nullable Level level, @NotNull List<Component> tooltips, final @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, tooltips, tooltipFlag);
        MWTntBlock.appendTntHoverText(tooltips, this.tntType);
    }

}