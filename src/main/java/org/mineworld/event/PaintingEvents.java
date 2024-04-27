package org.mineworld.event;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.helper.ItemHelper;

/**
 * Handle all events for the {@link Painting paintings}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class PaintingEvents {

    /**
     * Drop the {@link Painting painting} with its variant when right clicking with the {@link Items#SHEARS Shears}
     *
     * @param event {@link PlayerInteractEvent.EntityInteractSpecific The Entity Interact Specific Event}
     */
    @SubscribeEvent
    public static void onEntityInteractSpecific(final PlayerInteractEvent.EntityInteractSpecific event) {
        if(!event.isCanceled()) {
            final Entity target = event.getTarget();
            final ItemStack itemStack = event.getItemStack();
            final Item item = itemStack.getItem();
            if(target instanceof Painting painting && item instanceof ShearsItem) {
                final Player player = event.getEntity();
                final Level level = event.getLevel();
                painting.kill();
                if (level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                    painting.playSound(SoundEvents.PAINTING_BREAK, 1.0F, 1.0F);
                    if (!player.isCreative()) {
                        final ItemStack drop = new ItemStack(Items.PAINTING);
                        final CompoundTag nbt = drop.getOrCreateTagElement("EntityTag");
                        Painting.storeVariant(nbt, painting.getVariant());
                        drop.getOrCreateTag();
                        painting.spawnAtLocation(drop);
                    }
                }
                ItemHelper.hurt(itemStack, player, level, event.getHand());
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.CONSUME);
            }
        }
    }
}