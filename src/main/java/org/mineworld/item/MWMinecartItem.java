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
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.entity.vehicle.MWMinecartChest;
import org.mineworld.entity.vehicle.MWMinecartTNT;
import org.mineworld.helper.PropertyHelper;

/**
 * Implementation class for a {@link MinecartItem minecart id}
 */
public class MWMinecartItem extends Item {

    /**
     * {@link Type The minecart type}
     */
    private final Type type;

    /**
     * Constructor. Set the {@link Type minecart type}
     *
     * @param type {@link Type The minecart type}
     * @param featureFlags {@link FeatureFlag The feature flags that needs to be enabled for this id to be registered}
     */
    public MWMinecartItem(final Type type, final FeatureFlag... featureFlags) {
        super(PropertyHelper.basicItemProperties(featureFlags).stacksTo(1));
        this.type = type;
    }

    /**
     * Place the minecart
     *
     * @param context {@link UseOnContext The id use on context}
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
                AbstractMinecart abstractminecart = createMinecart(level, (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.0625D + (railshape.isAscending() ? 0.5D : 0), (double)blockpos.getZ() + 0.5D, this.type);
                if (itemstack.hasCustomHoverName()) {
                    abstractminecart.setCustomName(itemstack.getHoverName());
                }

                level.addFreshEntity(abstractminecart);
                level.gameEvent(GameEvent.ENTITY_PLACE, blockpos, GameEvent.Context.of(context.getPlayer(), level.getBlockState(blockpos.below())));
            }

            itemstack.shrink(1);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    /**
     * Create a {@link AbstractMinecart minecart} based on the {@link Type type}
     *
     * @param level {@link Level The level reference}
     * @param posX {@link Double The entity X coordinate}
     * @param posY {@link Double The entity Y coordinate}
     * @param posZ {@link Double The entity Z coordinate}
     * @param type {@link Type The minecart type}
     * @return {@link AbstractMinecart The minecart entity}
     */
    private static AbstractMinecart createMinecart(final Level level, final double posX, final double posY, final double posZ, final Type type) {
        return switch (type) {
            case MEGA_TNT -> new MWMinecartTNT(level, posX, posY, posZ, MWPrimedTnt.Type.MEGA);
            case SUPER_TNT -> new MWMinecartTNT(level, posX, posY, posZ, MWPrimedTnt.Type.SUPER);
            case HYPER_TNT -> new MWMinecartTNT(level, posX, posY, posZ, MWPrimedTnt.Type.HYPER);
            case DISGUISED_GRASS_TNT -> new MWMinecartTNT(level, posX, posY, posZ, MWPrimedTnt.Type.DISGUISED_GRASS);
            case DISGUISED_DIRT_TNT -> new MWMinecartTNT(level, posX, posY, posZ, MWPrimedTnt.Type.DISGUISED_DIRT);
            case DISGUISED_SAND_TNT -> new MWMinecartTNT(level, posX, posY, posZ, MWPrimedTnt.Type.DISGUISED_SAND);
            case DISGUISED_RED_SAND_TNT -> new MWMinecartTNT(level, posX, posY, posZ, MWPrimedTnt.Type.DISGUISED_RED_SAND);
            case DISGUISED_STONE_TNT -> new MWMinecartTNT(level, posX, posY, posZ, MWPrimedTnt.Type.DISGUISED_STONE);
            case DISGUISED_CAKE_TNT -> new MWMinecartTNT(level, posX, posY, posZ, MWPrimedTnt.Type.DISGUISED_CAKE);
            case SPRUCE_CHEST -> new MWMinecartChest(level, posX, posY, posZ, WoodType.SPRUCE);
            case BIRCH_CHEST -> new MWMinecartChest(level, posX, posY, posZ, WoodType.BIRCH);
            case JUNGLE_CHEST -> new MWMinecartChest(level, posX, posY, posZ, WoodType.JUNGLE);
            case ACACIA_CHEST -> new MWMinecartChest(level, posX, posY, posZ, WoodType.ACACIA);
            case DARK_OAK_CHEST -> new MWMinecartChest(level, posX, posY, posZ, WoodType.DARK_OAK);
            case MANGROVE_CHEST -> new MWMinecartChest(level, posX, posY, posZ, WoodType.MANGROVE);
            case CHERRY_CHEST -> new MWMinecartChest(level, posX, posY, posZ, WoodType.CHERRY);
            case BAMBOO_CHEST -> new MWMinecartChest(level, posX, posY, posZ, WoodType.BAMBOO);
            case CRIMSON_CHEST -> new MWMinecartChest(level, posX, posY, posZ, WoodType.CRIMSON);
            case WARPED_CHEST -> new MWMinecartChest(level, posX, posY, posZ, WoodType.WARPED);
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
        WARPED_CHEST;

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