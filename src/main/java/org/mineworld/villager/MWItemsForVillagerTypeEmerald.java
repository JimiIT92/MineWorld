package org.mineworld.villager;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerDataHolder;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;

import java.util.Map;

/**
 * {@link MineWorld MineWorld} {@link Item Item} for Emerald Villager Trade
 */
public class MWItemsForVillagerTypeEmerald implements VillagerTrades.ItemListing {

    /**
     * {@link Map The Villager trades}
     */
    private final Map<VillagerType, Item> trades;
    /**
     * {@link Integer The amount of Items to get}
     */
    private final int amount;
    /**
     * {@link Integer The maximum amount of Times the Item can be traded}
     */
    private final int maxUses;
    /**
     * {@link Integer The amount of experience the villager will get from the Trade}
     */
    private final int villagerXp;

    /**
     * Constructor. Set the Trade Properties
     *
     * @param amount {@link Integer The amount of Items to get}
     * @param maxUses {@link Integer The maximum amount of Times the Item can be traded}
     * @param villagerXp {@link Integer The amount of experience the villager will get from the Trade}
     * @param trades {@link Map The Villager trades}
     */
    public MWItemsForVillagerTypeEmerald(final int amount, final int maxUses, final int villagerXp, final Map<VillagerType, Item> trades) {
        BuiltInRegistries.VILLAGER_TYPE.stream().filter((p_35680_) -> !trades.containsKey(p_35680_)).findAny().ifPresent((p_258962_) -> {
            throw new IllegalStateException("Missing trade for villager type: " + BuiltInRegistries.VILLAGER_TYPE.getKey(p_258962_));
        });
        this.trades = trades;
        this.amount = amount;
        this.maxUses = maxUses;
        this.villagerXp = villagerXp;
    }

    /**
     * Get the {@link MerchantOffer Trade Merchant Offer}
     *
     * @param trader {@link Entity The Entity Trading}
     * @param random {@link RandomSource The Random reference}
     * @return {@link MerchantOffer The Merchant Trade Offer}
     */
    @Nullable
    public MerchantOffer getOffer(final @NotNull Entity trader, final @NotNull RandomSource random) {
        if (trader instanceof VillagerDataHolder) {
            final ItemStack offer = new ItemStack(this.trades.get(((VillagerDataHolder)trader).getVillagerData().getType()), this.amount);
            return new MerchantOffer(new ItemStack(Items.EMERALD), offer, this.maxUses, this.villagerXp, 0.05F);
        }
        return null;
    }

}