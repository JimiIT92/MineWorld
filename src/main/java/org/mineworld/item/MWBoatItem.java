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
import org.mineworld.helper.PropertyHelper;

import java.util.List;
import java.util.function.Predicate;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link BoatItem boat item}
 */
public class MWBoatItem extends Item {

    /**
     * {@link Predicate<Entity> The boat entity predicate}
     */
    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
    /**
     * {@link MWBoat.Type The boat type}
     */
    private final MWBoat.Type type;
    /**
     * {@link Boolean If the boas has a chest}
     */
    private final boolean hasChest;

    /**
     * Constructor. Set the boat properties
     *
     * @param hasChest {@link Boolean If the boat has a chest}
     * @param type {@link MWBoat.Type The boat type}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this item to be registered}
     */
    public MWBoatItem(final boolean hasChest, final MWBoat.Type type, final FeatureFlag... featureFlags) {
        super(PropertyHelper.basicItemProperties(featureFlags).stacksTo(1));
        this.hasChest = hasChest;
        this.type = type;
    }

    /**
     * Place the boat
     *
     * @param level {@link Level The level reference}
     * @param player {@link Player The player placing the boat}
     * @param interactionHand {@link InteractionHand The hand the player is interacting with}
     * @return {@link InteractionResultHolder<ItemStack> The interaction result}
     */
    public @NotNull InteractionResultHolder<ItemStack> use(final @NotNull Level level, final Player player, final @NotNull InteractionHand interactionHand) {
        final ItemStack itemstack = player.getItemInHand(interactionHand);
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
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                }
                player.awardStat(Stats.ITEM_USED.get(this));
                return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
            }
        }
        return InteractionResultHolder.pass(itemstack);
    }

    /**
     * Get the {@link MWBoat boat entity}
     *
     * @param level {@link Level The level reference}
     * @param hitResult {@link HitResult The hit result}
     * @return {@link MWBoat The boat entity}
     */
    private MWBoat getBoat(final Level level, final HitResult hitResult) {
        return this.hasChest ? new MWChestBoat(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z) :
                new MWBoat(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z);
    }

}