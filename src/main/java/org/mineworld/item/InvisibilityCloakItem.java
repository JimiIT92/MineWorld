package org.mineworld.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWArmorMaterials;
import org.mineworld.core.MWCriteriaTriggers;
import org.mineworld.core.MWSounds;
import org.mineworld.helper.PlayerHelper;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} {@link ArmorItem Invisibility Cloak Item}
 */
public class InvisibilityCloakItem extends ArmorItem {

    /**
     * Constructor. Set the {@link Properties Item Properties}
     */
    public InvisibilityCloakItem() {
        super(MWArmorMaterials.INVISIBILITY_CLOAK, Type.CHESTPLATE, PropertyHelper.item().rarity(Rarity.UNCOMMON));
    }

    /**
     * Get the {@link SoundEvent Armor Equip Sound}
     *
     * @return {@link MWSounds#INVISIBILITY_CLOAK_EQUIP The Invisibility Cloak Equip Sound}
     */
    @Override
    public @NotNull SoundEvent getEquipSound() {
        return MWSounds.INVISIBILITY_CLOAK_EQUIP.get();
    }

    /**
     * Tick the Armor
     *
     * @param stack {@link ItemStack The current Item Stack}
     * @param level {@link Level The level reference}
     * @param player {@link Player The Player}
     */
    @Override
    public void onArmorTick(final ItemStack stack, final Level level, final Player player) {
        if(!level.isClientSide() && PlayerHelper.getArmorSlotItem(player, EquipmentSlot.CHEST).is(this)) {
            player.setInvisible(true);
            MWCriteriaTriggers.EQUIP_INVISIBILITY_CLOAK.trigger((ServerPlayer) player);
        }
        super.onArmorTick(stack, level, player);
    }

}