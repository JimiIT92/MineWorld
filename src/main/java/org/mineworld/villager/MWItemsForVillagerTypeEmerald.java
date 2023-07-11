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

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Implementation class for an id for emerald trades based on the {@link VillagerType villager type}
 */
public class MWItemsForVillagerTypeEmerald implements VillagerTrades.ItemListing {

    /**
     * {@link Map The villager trades}
     */
    private Map<VillagerType, Item> trades;
    /**
     * {@link Integer The amount of items to get}
     */
    private int amount;
    /**
     * {@link Integer The maximum amount of times the recipe can be traded}
     */
    private int maxUses;
    /**
     * {@link Integer The amount of xp the villager will get from this trade}
     */
    private int villagerXp;

    /**
     * Constructor. Set the trade properties
     *
     * @param amount {@link Integer The amount of items to get}
     * @param maxUses {@link Integer The maximum amount of times the recipe can be traded}
     * @param villagerXp {@link Integer The amount of xp the villager will get from this trade}
     * @param trades {@link Map The villager trades}
     */
    public MWItemsForVillagerTypeEmerald(int amount, int maxUses, int villagerXp, Map<VillagerType, Item> trades) {
        BuiltInRegistries.VILLAGER_TYPE.stream().filter((p_35680_) -> !trades.containsKey(p_35680_)).findAny().ifPresent((p_258962_) -> {
            throw new IllegalStateException("Missing trade for villager type: " + BuiltInRegistries.VILLAGER_TYPE.getKey(p_258962_));
        });
        this.trades = trades;
        this.amount = amount;
        this.maxUses = maxUses;
        this.villagerXp = villagerXp;
    }

    /**
     * Get the {@link MerchantOffer trade merchant offer}
     *
     * @param trader {@link Entity The entity trading}
     * @param random {@link RandomSource The random reference}
     * @return {@link MerchantOffer The merchant trade}
     */
    @Nullable
    public MerchantOffer getOffer(@NotNull Entity trader, @NotNull RandomSource random) {
        if (trader instanceof VillagerDataHolder) {
            ItemStack offer = new ItemStack(this.trades.get(((VillagerDataHolder)trader).getVillagerData().getType()), this.amount);
            return new MerchantOffer(new ItemStack(Items.EMERALD), offer, this.maxUses, this.villagerXp, 0.05F);
        }
        return null;
    }

}