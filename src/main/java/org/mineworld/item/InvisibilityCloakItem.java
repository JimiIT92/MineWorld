package org.mineworld.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWArmorMaterials;
import org.mineworld.core.MWSounds;
import org.mineworld.helper.PlayerHelper;
import org.mineworld.helper.PropertyHelper;

/**
 * Implementation class for the Invisibility Cloak {@link ArmorItem armor item}
 */
public class InvisibilityCloakItem extends ArmorItem {

    /**
     * Constructor. Set the item properties
     */
    public InvisibilityCloakItem() {
        super(MWArmorMaterials.INVISIBILITY_CLOAK, Type.CHESTPLATE, PropertyHelper.basicItemProperties().rarity(Rarity.UNCOMMON));
    }

    /**
     * Get the {@link SoundEvent equip sound}
     *
     * @return {@link MWSounds#INVISIBILITY_CLOAK_EQUIP The invisibility cloak equip sound}
     */
    @Override
    public @NotNull SoundEvent getEquipSound() {
        return MWSounds.INVISIBILITY_CLOAK_EQUIP.get();
    }

    /**
     * Makes the {@link Player player} invisible
     *
     * @param stack {@link ItemStack The current Item Stack}
     * @param level {@link Level The level reference}
     * @param player {@link Player The player}
     */
    @Override
    public void onArmorTick(final ItemStack stack, final Level level, final Player player) {
        if(!level.isClientSide() && PlayerHelper.getArmorSlotItem(player, EquipmentSlot.CHEST).is(this)) {
            player.setInvisible(true);
        }
        super.onArmorTick(stack, level, player);
    }
}