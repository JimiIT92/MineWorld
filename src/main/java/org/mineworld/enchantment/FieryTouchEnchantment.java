package org.mineworld.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;

/**
 * Implementation class for a {@link Enchantment fiery touch enchantment}
 */
public class FieryTouchEnchantment extends Enchantment {

    /**
     * Constructor. Set the enchantment properties
     */
    public FieryTouchEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[]{ EquipmentSlot.MAINHAND });
    }

    /**
     * Check the compatibility with another {@link Enchantment enchantment}
     *
     * @param other {@link Enchantment The enchantment to check against}
     * @return {@link Boolean True if the other enchantment is not Silk Touch}
     */
    public boolean checkCompatibility(final @NotNull Enchantment other) {
        return super.checkCompatibility(other) && other != Enchantments.SILK_TOUCH && other != Enchantments.BLOCK_FORTUNE;
    }

    /**
     * Get the {@link Integer minimum anvil cost} at an anvil
     *
     * @param level {@link Integer The anvil repair XP levels}
     * @return {@link Integer 15}
     */
    public int getMinCost(int level) {
        return 15;
    }

    /**
     * Get the {@link Integer maximum anvil cost} at an anvil
     *
     * @param level {@link Integer The anvil repair XP levels}
     * @return The {@link #getMinCost minimum anvil cost} plus {@link Integer 50}
     */
    public int getMaxCost(int level) {
        return super.getMinCost(level) + 50;
    }

}