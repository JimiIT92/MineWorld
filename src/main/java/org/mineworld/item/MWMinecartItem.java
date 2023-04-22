package org.mineworld.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.entity.MWPrimedTnt;
import org.mineworld.entity.vehicle.MWMinecartTNT;
import org.mineworld.helper.PropertyHelper;

import java.util.List;

/**
 * Implementation class for a {@link MinecartItem minecart item}
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
     */
    public MWMinecartItem(final Type type) {
        super(PropertyHelper.basicItemProperties().stacksTo(1));
        this.type = type;
    }

    /**
     * Place the minecart
     *
     * @param context {@link UseOnContext The item use on context}
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
            case DISGUISED_GRASS -> new MWMinecartTNT(level, posX, posY, posZ, MWPrimedTnt.Type.DISGUISED_GRASS);
            case DISGUISED_DIRT -> new MWMinecartTNT(level, posX, posY, posZ, MWPrimedTnt.Type.DISGUISED_DIRT);
            case DISGUISED_SAND -> new MWMinecartTNT(level, posX, posY, posZ, MWPrimedTnt.Type.DISGUISED_SAND);
            case DISGUISED_RED_SAND -> new MWMinecartTNT(level, posX, posY, posZ, MWPrimedTnt.Type.DISGUISED_RED_SAND);
            case DISGUISED_STONE -> new MWMinecartTNT(level, posX, posY, posZ, MWPrimedTnt.Type.DISGUISED_STONE);
            case DISGUISED_CAKE -> new MWMinecartTNT(level, posX, posY, posZ, MWPrimedTnt.Type.DISGUISED_CAKE);
        };
    }

    /**
     * Add tooltips to the disguised tnts
     *
     * @param itemStack {@link ItemStack The current item stack}
     * @param level {@link Level The world reference}
     * @param tooltips {@link List<Component> The current tooltips}
     * @param tooltipFlag {@link TooltipFlag The tooltip flag}
     */
    @Override
    public void appendHoverText(final @NotNull ItemStack itemStack, final @Nullable Level level, @NotNull List<Component> tooltips, final @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, tooltips, tooltipFlag);
        if(this.type.isDisguised()) {
            MutableComponent blockName = Blocks.GRASS_BLOCK.getName();
            TextColor color = TextColor.fromLegacyFormat(ChatFormatting.DARK_GREEN);
            switch (this.type) {
                case DISGUISED_DIRT -> {
                    blockName = Blocks.DIRT.getName();
                    color = TextColor.fromRgb(0x7B553D);
                }
                case DISGUISED_SAND -> {
                    blockName = Blocks.SAND.getName();
                    color = TextColor.fromLegacyFormat(ChatFormatting.YELLOW);
                }
                case DISGUISED_RED_SAND -> {
                    blockName = Blocks.RED_SAND.getName();
                    color = TextColor.fromRgb(0xB8602C);
                }
                case DISGUISED_STONE -> {
                    blockName = Blocks.STONE.getName();
                    color = TextColor.fromLegacyFormat(ChatFormatting.GRAY);
                }
                case DISGUISED_CAKE -> {
                    blockName = Blocks.CAKE.getName();
                    color = TextColor.fromRgb(0xF6E8CB);
                }
            }
            tooltips.add(blockName.withStyle(Style.EMPTY.withColor(color)));
        }
    }

    /**
     * The minecart types
     */
    public enum Type {
        SUPER_TNT(false),
        MEGA_TNT(false),
        HYPER_TNT(false),
        DISGUISED_GRASS(true),
        DISGUISED_DIRT(true),
        DISGUISED_SAND(true),
        DISGUISED_RED_SAND(true),
        DISGUISED_STONE(true),
        DISGUISED_CAKE(true);

        /**
         * {@link Boolean If the tnt type represents a disguised one}
         */
        private final boolean isDisguised;

        /**
         * Constructor. Set if the tnt type is disguised
         *
         * @param isDisguised {@link Boolean If the tnt type is disguised}
         */
        Type(final boolean isDisguised) {
            this.isDisguised = isDisguised;
        }

        /**
         * Get if the tnt type is disguised
         *
         * @return {@link Boolean True if the tnt type is disguised}
         */
        public boolean isDisguised() {
            return this.isDisguised;
        }
    }

}