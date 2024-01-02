package org.mineworld.item;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.entity.vehicle.MWMinecartTnt;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} class for {@link MinecartItem Minecart Item}
 */
public class MWMinecartItem extends Item {

    /**
     * {@link Type The Minecart Type}
     */
    private final Type type;

    /**
     * Constructor. Set the Item properties
     *
     * @param type {@link Type The minecart type}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     */
    public MWMinecartItem(final Type type, final FeatureFlag... featureFlags) {
        super(PropertyHelper.item(featureFlags).stacksTo(1));
        this.type = type;
    }

    /**
     * Place the minecart
     *
     * @param context {@link UseOnContext The Use On Context}
     * @return {@link InteractionResult Interaction result}
     */
    public @NotNull InteractionResult useOn(final UseOnContext context) {
        final Level level = context.getLevel();
        final BlockPos blockpos = context.getClickedPos();
        final BlockState blockstate = level.getBlockState(blockpos);
        if (!blockstate.is(BlockTags.RAILS)) {
            return InteractionResult.FAIL;
        } else {
            final ItemStack itemstack = context.getItemInHand();
            if (!level.isClientSide) {
                final RailShape railshape = blockstate.getBlock() instanceof BaseRailBlock ? ((BaseRailBlock)blockstate.getBlock()).getRailDirection(blockstate, level, blockpos, null) : RailShape.NORTH_SOUTH;
                final AbstractMinecart minecart = createMinecart(level, (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.0625D + (railshape.isAscending() ? 0.5D : 0), (double)blockpos.getZ() + 0.5D, this.type);
                if (itemstack.hasCustomHoverName()) {
                    minecart.setCustomName(itemstack.getHoverName());
                }

                level.addFreshEntity(minecart);
                level.gameEvent(GameEvent.ENTITY_PLACE, blockpos, GameEvent.Context.of(context.getPlayer(), level.getBlockState(blockpos.below())));
            }

            ItemHelper.hurt(itemstack);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    /**
     * Create a {@link AbstractMinecart Minecart} based on the {@link Type type}
     *
     * @param level {@link Level The level reference}
     * @param x {@link Double The minecart X coordinate}
     * @param y {@link Double The minecart Y coordinate}
     * @param z {@link Double The minecart Z coordinate}
     * @param type {@link Type The minecart type}
     * @return {@link AbstractMinecart The minecart entity}
     */
    private static AbstractMinecart createMinecart(final Level level, final double x, final double y, final double z, final Type type) {
        return switch (type) {
            case MEGA_TNT -> new MWMinecartTnt(level, x, y, z, MWPrimedTnt.Type.MEGA);
            case SUPER_TNT -> new MWMinecartTnt(level, x, y, z, MWPrimedTnt.Type.SUPER);
            case HYPER_TNT -> new MWMinecartTnt(level, x, y, z, MWPrimedTnt.Type.HYPER);
            case DISGUISED_GRASS_TNT -> new MWMinecartTnt(level, x, y, z, MWPrimedTnt.Type.DISGUISED_GRASS);
            case DISGUISED_DIRT_TNT -> new MWMinecartTnt(level, x, y, z, MWPrimedTnt.Type.DISGUISED_DIRT);
            case DISGUISED_SAND_TNT -> new MWMinecartTnt(level, x, y, z, MWPrimedTnt.Type.DISGUISED_SAND);
            case DISGUISED_RED_SAND_TNT -> new MWMinecartTnt(level, x, y, z, MWPrimedTnt.Type.DISGUISED_RED_SAND);
            case DISGUISED_STONE_TNT -> new MWMinecartTnt(level, x, y, z, MWPrimedTnt.Type.DISGUISED_STONE);
            case DISGUISED_CAKE_TNT -> new MWMinecartTnt(level, x, y, z, MWPrimedTnt.Type.DISGUISED_CAKE);
            /*case SPRUCE_CHEST -> new MWMinecartChest(level, x, y, z, WoodType.SPRUCE);
            case BIRCH_CHEST -> new MWMinecartChest(level, x, y, z, WoodType.BIRCH);
            case JUNGLE_CHEST -> new MWMinecartChest(level, x, y, z, WoodType.JUNGLE);
            case ACACIA_CHEST -> new MWMinecartChest(level, x, y, z, WoodType.ACACIA);
            case DARK_OAK_CHEST -> new MWMinecartChest(level, x, y, z, WoodType.DARK_OAK);
            case MANGROVE_CHEST -> new MWMinecartChest(level, x, y, z, WoodType.MANGROVE);
            case CHERRY_CHEST -> new MWMinecartChest(level, x, y, z, WoodType.CHERRY);
            case BAMBOO_CHEST -> new MWMinecartChest(level, x, y, z, WoodType.BAMBOO);
            case CRIMSON_CHEST -> new MWMinecartChest(level, x, y, z, WoodType.CRIMSON);
            case WARPED_CHEST -> new MWMinecartChest(level, x, y, z, WoodType.WARPED);
            case APPLE_CHEST -> new MWMinecartChest(level, x, y, z, MWWoodTypes.APPLE);
            case PALM_CHEST -> new MWMinecartChest(level, x, y, z, MWWoodTypes.PALM);
            case DEAD_CHEST -> new MWMinecartChest(level, x, y, z, MWWoodTypes.DEAD);
            case ICE_CHEST -> new MWMinecartChest(level, x, y, z, MWWoodTypes.ICE);
            case SCULK_CHEST -> new MWMinecartChest(level, x, y, z, MWWoodTypes.SCULK.get());*/
            default -> null;
        };
    }

    /**
     * The minecart types
     */
    public enum Type {
        SUPER_TNT,
        MEGA_TNT,
        HYPER_TNT,
        DISGUISED_GRASS_TNT(true),
        DISGUISED_DIRT_TNT(true),
        DISGUISED_SAND_TNT(true),
        DISGUISED_RED_SAND_TNT(true),
        DISGUISED_STONE_TNT(true),
        DISGUISED_CAKE_TNT(true),
        SPRUCE_CHEST,
        BIRCH_CHEST,
        JUNGLE_CHEST,
        ACACIA_CHEST,
        DARK_OAK_CHEST,
        MANGROVE_CHEST,
        CHERRY_CHEST,
        BAMBOO_CHEST,
        CRIMSON_CHEST,
        WARPED_CHEST,
        APPLE_CHEST,
        PALM_CHEST,
        DEAD_CHEST,
        ICE_CHEST,
        SCULK_CHEST;

        /**
         * {@link Boolean If the tnt type represents a disguised one}
         */
        private final boolean isDisguisedTNT;

        /**
         * Constructor. Set if the tnt type is disguised
         */
        Type() {
            this(false);
        }

        /**
         * Constructor. Set if the tnt type is disguised
         *
         * @param isDisguised {@link Boolean If the tnt type is disguised}
         */
        Type(final boolean isDisguised) {
            this.isDisguisedTNT = isDisguised;
        }

        /**
         * Get if the tnt type is disguised
         *
         * @return {@link Boolean True if the tnt type is disguised}
         */
        public boolean isDisguisedTNT() {
            return this.isDisguisedTNT;
        }
    }

}