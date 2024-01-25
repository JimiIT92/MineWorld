package org.mineworld.item;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.entity.vehicle.MWBoat;
import org.mineworld.entity.vehicle.MWChestBoat;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.PropertyHelper;

import java.util.List;
import java.util.function.Predicate;

/**
 * {@link MineWorld MineWorld} {@link BoatItem Boat Item}
 */
public class MWBoatItem extends Item {

    /**
     * {@link Predicate<Entity> The Boat Entity Predicate}
     */
    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
    /**
     * {@link MWBoat.Type The Boat Type}
     */
    private final MWBoat.Type type;
    /**
     * {@link Boolean If the Boat is a Chest Boat}
     */
    private final boolean hasChest;

    /**
     * Constructor. Set the {@link Properties Item Properties}
     *
     * @param hasChest {@link Boolean If the Boat is a Chest Boat}
     * @param type {@link MWBoat.Type The Boat Type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     */
    public MWBoatItem(final boolean hasChest, final MWBoat.Type type, final FeatureFlag... featureFlags) {
        super(PropertyHelper.item(featureFlags).stacksTo(1));
        this.hasChest = hasChest;
        this.type = type;
    }

    /**
     * Shoot the pebble on right click
     *
     * @param level {@link Level The level reference}
     * @param player {@link Player The player that is shooting the pebble}
     * @param hand {@link InteractionHand The hand the player is shooting with}
     * @return {@link InteractionResultHolder<ItemStack> The interaction result}
     */
    public @NotNull InteractionResultHolder<ItemStack> use(final @NotNull Level level, final Player player, final @NotNull InteractionHand hand) {
        final ItemStack itemstack = player.getItemInHand(hand);
        final HitResult hitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        if (hitresult.getType().equals(HitResult.Type.MISS)) {
            return InteractionResultHolder.pass(itemstack);
        }
        final List<Entity> list = level.getEntities(player, player.getBoundingBox().expandTowards(player.getViewVector(1.0F).scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
        if (!list.isEmpty()) {
            final Vec3 playerEyePosition = player.getEyePosition();
            for(final Entity entity : list) {
                if (entity.getBoundingBox().inflate(entity.getPickRadius()).contains(playerEyePosition)) {
                    return InteractionResultHolder.pass(itemstack);
                }
            }
        }

        if (hitresult.getType().equals(HitResult.Type.BLOCK)) {
            final MWBoat boat = this.getBoat(level, hitresult);
            boat.setBoatType(this.type);
            boat.setYRot(player.getYRot());
            if (!level.noCollision(boat, boat.getBoundingBox())) {
                return InteractionResultHolder.fail(itemstack);
            } else {
                if (!level.isClientSide) {
                    level.addFreshEntity(boat);
                    level.gameEvent(player, GameEvent.ENTITY_PLACE, hitresult.getLocation());
                    ItemHelper.hurt(itemstack, player);
                }
                player.awardStat(Stats.ITEM_USED.get(this));
                return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
            }
        }
        return InteractionResultHolder.pass(itemstack);
    }

    /**
     * Get the {@link MWBoat Boat Entity}
     *
     * @param level {@link Level The level reference}
     * @param hitResult {@link HitResult The hit result}
     * @return {@link MWBoat The Boat Entity}
     */
    private MWBoat getBoat(final Level level, final HitResult hitResult) {
        return this.hasChest ? new MWChestBoat(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z) :
                new MWBoat(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z);
    }

}