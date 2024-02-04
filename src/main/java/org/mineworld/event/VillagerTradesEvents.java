package org.mineworld.event;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.entity.npc.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWItems;
import org.mineworld.core.MWVillagerProfessions;
import org.mineworld.villager.MWItemsForVillagerTypeEmerald;

import java.util.Arrays;

/**
 * Handle all events for {@link VillagerTrades Villager Trades}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public class VillagerTradesEvents {

    /**
     * Add some {@link VillagerTrades.ItemListing Trades} to some {@link Villager Villagers}
     *
     * @param event {@link VillagerTradesEvent The Villager Trades Event}
     */
    @SubscribeEvent
    public static void onVillagerTrades(final VillagerTradesEvent event) {
        if(!event.isCanceled()) {
            final VillagerProfession profession = event.getType();
            if(MWVillagerProfessions.CARPENTER.get().equals(profession)) {
                addCarpenterTrades(event);
                return;
            }
            if(VillagerProfession.FARMER.equals(profession)) {
                addFarmerTrades(event);
                return;
            }
            if(VillagerProfession.ARMORER.equals(profession)) {
                addArmorerTrades(event);
                return;
            }
            if(VillagerProfession.WEAPONSMITH.equals(profession)) {
                addWeaponsmithTrades(event);
                return;
            }
            if(VillagerProfession.TOOLSMITH.equals(profession)) {
                addToolsmithTrades(event);
            }
        }
    }

    /**
     * Add some {@link BasicItemListing Trades} to the {@link WanderingTrader Wandering Trader}
     *
     * @param event {@link WandererTradesEvent The Wandering Trades Event}
     */
    @SubscribeEvent
    public static void onWanderingVillagerTrades(final WandererTradesEvent event) {
        addWanderingTrade(event, 3, MWBlocks.LAVA_ROCK.get());
        addWanderingTrade(event, 6, MWBlocks.PERENNIAL_ICE.get());
    }

    /**
     * Add some {@link VillagerTrades.ItemListing Trades} to the {@link MWVillagerProfessions#CARPENTER Carpenter Villager}
     *
     * @param event {@link VillagerTradesEvent The Villager Trades Event}
     */
    private static void addCarpenterTrades(final VillagerTradesEvent event) {
        addVillagerTrades(event, 1,
                new VillagerTrades.EmeraldsForVillagerTypeItem(10, 16,2, ImmutableMap.<VillagerType, Item>builder()
                        .put(VillagerType.PLAINS, Items.OAK_LOG)
                        .put(VillagerType.TAIGA, Items.SPRUCE_LOG)
                        .put(VillagerType.SNOW, Items.SPRUCE_LOG)
                        .put(VillagerType.DESERT, Items.JUNGLE_LOG)
                        .put(VillagerType.JUNGLE, Items.JUNGLE_LOG)
                        .put(VillagerType.SAVANNA, Items.ACACIA_LOG)
                        .put(VillagerType.SWAMP, Items.DARK_OAK_LOG)
                        .build()),
                new VillagerTrades.ItemsForEmeralds(Items.STICK, 1, 2, 16, 1)
        );
        addVillagerTrades(event, 2,
                new MWItemsForVillagerTypeEmerald(4, 16,10, ImmutableMap.<VillagerType, Item>builder()
                        .put(VillagerType.PLAINS, Items.OAK_PLANKS)
                        .put(VillagerType.TAIGA, Items.SPRUCE_PLANKS)
                        .put(VillagerType.SNOW, Items.SPRUCE_PLANKS)
                        .put(VillagerType.DESERT, Items.JUNGLE_PLANKS)
                        .put(VillagerType.JUNGLE, Items.JUNGLE_PLANKS)
                        .put(VillagerType.SAVANNA, Items.ACACIA_PLANKS)
                        .put(VillagerType.SWAMP, Items.DARK_OAK_PLANKS)
                        .build())
        );
        addVillagerTrades(event, 3,
                new VillagerTrades.EmeraldsForVillagerTypeItem(15, 16,20, ImmutableMap.<VillagerType, Item>builder()
                        .put(VillagerType.PLAINS, Items.OAK_BUTTON)
                        .put(VillagerType.TAIGA, Items.SPRUCE_BUTTON)
                        .put(VillagerType.SNOW, Items.SPRUCE_BUTTON)
                        .put(VillagerType.DESERT, Items.JUNGLE_BUTTON)
                        .put(VillagerType.JUNGLE, Items.JUNGLE_BUTTON)
                        .put(VillagerType.SAVANNA, Items.ACACIA_BUTTON)
                        .put(VillagerType.SWAMP, Items.DARK_OAK_BUTTON)
                        .build()),
                new MWItemsForVillagerTypeEmerald(4, 16,10, ImmutableMap.<VillagerType, Item>builder()
                        .put(VillagerType.PLAINS, Items.CHEST)
                        .put(VillagerType.TAIGA, MWItems.SPRUCE_CHEST.get())
                        .put(VillagerType.SNOW, MWItems.SPRUCE_CHEST.get())
                        .put(VillagerType.DESERT, MWItems.JUNGLE_CHEST.get())
                        .put(VillagerType.JUNGLE, MWItems.JUNGLE_CHEST.get())
                        .put(VillagerType.SAVANNA, MWItems.ACACIA_CHEST.get())
                        .put(VillagerType.SWAMP, MWItems.DARK_OAK_CHEST.get())
                        .build())
        );
        addVillagerTrades(event, 4,
                new VillagerTrades.EmeraldsForVillagerTypeItem(10, 12,30, ImmutableMap.<VillagerType, Item>builder()
                        .put(VillagerType.PLAINS, Items.OAK_STAIRS)
                        .put(VillagerType.TAIGA, Items.SPRUCE_STAIRS)
                        .put(VillagerType.SNOW, Items.SPRUCE_STAIRS)
                        .put(VillagerType.DESERT, Items.JUNGLE_STAIRS)
                        .put(VillagerType.JUNGLE, Items.JUNGLE_STAIRS)
                        .put(VillagerType.SAVANNA, Items.ACACIA_STAIRS)
                        .put(VillagerType.SWAMP, Items.DARK_OAK_STAIRS)
                        .build()),
                new MWItemsForVillagerTypeEmerald(1, 12,15, ImmutableMap.<VillagerType, Item>builder()
                        .put(VillagerType.PLAINS, Items.BOOKSHELF)
                        .put(VillagerType.TAIGA, MWBlocks.SPRUCE_BOOKSHELF.get().asItem())
                        .put(VillagerType.SNOW, MWBlocks.SPRUCE_BOOKSHELF.get().asItem())
                        .put(VillagerType.DESERT, MWBlocks.JUNGLE_BOOKSHELF.get().asItem())
                        .put(VillagerType.JUNGLE, MWBlocks.JUNGLE_BOOKSHELF.get().asItem())
                        .put(VillagerType.SAVANNA, MWBlocks.ACACIA_BOOKSHELF.get().asItem())
                        .put(VillagerType.SWAMP, MWBlocks.DARK_OAK_BOOKSHELF.get().asItem())
                        .build())
        );
        addVillagerTrades(event, 5,
                new MWItemsForVillagerTypeEmerald(1, 12,30, ImmutableMap.<VillagerType, Item>builder()
                        .put(VillagerType.PLAINS, Items.CHISELED_BOOKSHELF)
                        .put(VillagerType.TAIGA, MWBlocks.SPRUCE_CHISELED_BOOKSHELF.get().asItem())
                        .put(VillagerType.SNOW, MWBlocks.SPRUCE_CHISELED_BOOKSHELF.get().asItem())
                        .put(VillagerType.DESERT, MWBlocks.JUNGLE_CHISELED_BOOKSHELF.get().asItem())
                        .put(VillagerType.JUNGLE, MWBlocks.JUNGLE_CHISELED_BOOKSHELF.get().asItem())
                        .put(VillagerType.SAVANNA, MWBlocks.ACACIA_CHISELED_BOOKSHELF.get().asItem())
                        .put(VillagerType.SWAMP, MWBlocks.DARK_OAK_CHISELED_BOOKSHELF.get().asItem())
                        .build())
        );
    }

    /**
     * Add some {@link VillagerTrades.ItemListing Trades} to the {@link VillagerProfession#FARMER Farmer Villager}
     *
     * @param event {@link VillagerTradesEvent The Villager Trades Event}
     */
    private static void addFarmerTrades(final VillagerTradesEvent event) {
        addVillagerTrades(event, 1,
                new VillagerTrades.EmeraldForItems(MWItems.COB.get(), 15, 16, 2)
        );
        addVillagerTrades(event, 2,
                new VillagerTrades.ItemsAndEmeraldsToItems(MWItems.COB.get(), 6, 1, MWItems.BAKED_COB.get(), 6, 16, 1, 0.05F)
        );
    }

    /**
     * Add some {@link VillagerTrades.ItemListing Trades} to the {@link VillagerProfession#ARMORER Armorer Villager}
     *
     * @param event {@link VillagerTradesEvent The Villager Trades Event}
     */
    private static void addArmorerTrades(final VillagerTradesEvent event) {
        addVillagerTrades(event, 1,
                new VillagerTrades.ItemsForEmeralds(MWItems.SILVER_LEGGINGS.get().getDefaultInstance(), 7, 1, 12, 1, 0.2F),
                new VillagerTrades.ItemsForEmeralds(MWItems.SILVER_BOOTS.get().getDefaultInstance(), 4, 1, 12, 1, 0.2F),
                new VillagerTrades.ItemsForEmeralds(MWItems.SILVER_HELMET.get().getDefaultInstance(), 5, 1, 12, 1, 0.2F),
                new VillagerTrades.ItemsForEmeralds(MWItems.SILVER_CHESTPLATE.get().getDefaultInstance(), 9, 1, 12, 1, 0.2F));
        addVillagerTrades(event, 2, new VillagerTrades.EmeraldForItems(MWItems.SILVER_INGOT.get(), 4, 12, 10));
        addVillagerTrades(event, 3,
                new VillagerTrades.EmeraldForItems(MWItems.RUBY.get(), 1,12, 20),
                new VillagerTrades.EmeraldForItems(MWItems.SAPPHIRE.get(), 1,12, 20)
        );
        addVillagerTrades(event, 4,
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.RUBY_LEGGINGS.get(), 14, 3, 15, 0.2F),
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.RUBY_BOOTS.get(), 8, 3, 15, 0.2F),
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.SAPPHIRE_LEGGINGS.get(), 14, 3, 15, 0.2F),
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.SAPPHIRE_BOOTS.get(), 8, 3, 15, 0.2F)
        );
        addVillagerTrades(event, 5,
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.RUBY_HELMET.get(), 8, 3, 30, 0.2F),
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.RUBY_CHESTPLATE.get(), 16, 3, 30, 0.2F),
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.SAPPHIRE_HELMET.get(), 8, 3, 30, 0.2F),
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.SAPPHIRE_CHESTPLATE.get(), 16, 3, 30, 0.2F)
        );
    }

    /**
     * Add some {@link VillagerTrades.ItemListing Trades} to the {@link VillagerProfession#WEAPONSMITH Weaponsmith Villager}
     *
     * @param event {@link VillagerTradesEvent The Villager Trades Event}
     */
    private static void addWeaponsmithTrades(final VillagerTradesEvent event) {
        addVillagerTrades(event, 1,
                new VillagerTrades.ItemsForEmeralds(MWItems.SILVER_AXE.get().getDefaultInstance(), 3, 1, 12, 1, 0.2F),
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.SILVER_SWORD.get(), 2, 3, 1)
        );
        addVillagerTrades(event, 2,
                new VillagerTrades.EmeraldForItems(MWItems.SILVER_INGOT.get(), 4, 12, 10)
        );
        addVillagerTrades(event, 4,
                new VillagerTrades.EmeraldForItems(MWItems.RUBY.get(), 1,12, 30),
                new VillagerTrades.EmeraldForItems(MWItems.SAPPHIRE.get(), 1,12, 30),
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.RUBY_AXE.get(), 12, 3, 15, 0.2F),
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.SAPPHIRE_AXE.get(), 12, 3, 15, 0.2F)
        );
        addVillagerTrades(event, 5,
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.RUBY_SWORD.get(), 8, 3, 30, 0.2F),
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.SAPPHIRE_SWORD.get(), 8, 3, 30, 0.2F)
        );
    }

    /**
     * Add some {@link VillagerTrades.ItemListing Trades} to the {@link VillagerProfession#TOOLSMITH Toolsmith Villager}
     *
     * @param event {@link VillagerTradesEvent The Villager Trades Event}
     */
    private static void addToolsmithTrades(final VillagerTradesEvent event) {
        addVillagerTrades(event, 1,
                new VillagerTrades.ItemsForEmeralds(MWItems.ALUMINUM_AXE.get().getDefaultInstance(), 1, 1, 12, 1, 0.2F),
                new VillagerTrades.ItemsForEmeralds(MWItems.ALUMINUM_SHOVEL.get().getDefaultInstance(), 1, 1, 12, 1, 0.2F),
                new VillagerTrades.ItemsForEmeralds(MWItems.ALUMINUM_PICKAXE.get().getDefaultInstance(), 1, 1, 12, 1, 0.2F),
                new VillagerTrades.ItemsForEmeralds(MWItems.ALUMINUM_HOE.get().getDefaultInstance(), 1, 1, 12, 1, 0.2F)
        );
        addVillagerTrades(event, 2,
                new VillagerTrades.EmeraldForItems(MWItems.SILVER_INGOT.get(), 4, 12, 10)
        );
        addVillagerTrades(event, 3,
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.SILVER_AXE.get(), 1, 3, 10, 0.2F),
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.SILVER_SHOVEL.get(), 1, 3, 10, 0.2F),
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.SILVER_PICKAXE.get(), 1, 3, 10, 0.2F),
                new VillagerTrades.ItemsForEmeralds(MWItems.RUBY_HOE.get().getDefaultInstance(), 4, 1, 3, 10, 0.2F),
                new VillagerTrades.ItemsForEmeralds(MWItems.SAPPHIRE_HOE.get().getDefaultInstance(), 4, 1, 3, 10, 0.2F)
        );
        addVillagerTrades(event, 4,
                new VillagerTrades.EmeraldForItems(MWItems.RUBY.get(), 1,12, 30),
                new VillagerTrades.EmeraldForItems(MWItems.SAPPHIRE.get(), 1,12, 30),
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.RUBY_AXE.get(), 12, 3, 15, 0.2F),
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.SAPPHIRE_AXE.get(), 12, 3, 15, 0.2F),
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.RUBY_SHOVEL.get(), 5, 3, 15, 0.2F),
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.SAPPHIRE_SHOVEL.get(), 5, 3, 15, 0.2F)
        );
        addVillagerTrades(event, 5,
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.RUBY_PICKAXE.get(), 13, 3, 30, 0.2F),
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.SAPPHIRE_PICKAXE.get(), 13, 3, 30, 0.2F)
        );
    }

    /**
     * Add some {@link BasicItemListing Trades} to the {@link WanderingTrader Wandering Trader}
     *
     * @param event {@link WandererTradesEvent The Wandering Trades Event}
     * @param emeralds {@link Integer The amount of emeralds required for the Trade}
     * @param item {@link ItemLike The Block or Item to trade}
     */
    private static void addWanderingTrade(final WandererTradesEvent event, final int emeralds, final ItemLike item) {
        event.getRareTrades().add(new BasicItemListing(emeralds, item.asItem().getDefaultInstance(), 6, 1));
    }

    /**
     * Add some {@link VillagerTrades.ItemListing Trades} to some {@link Villager Villagers}
     *
     * @param event {@link VillagerTradesEvent The Wandering Trades Event}
     * @param villagerLevel {@link Integer The amount of emeralds required for the Trade}
     * @param trades {@link VillagerTrades.ItemListing The Trades to add}
     */
    private static void addVillagerTrades(final VillagerTradesEvent event, final int villagerLevel, final VillagerTrades.ItemListing... trades) {
        event.getTrades().get(villagerLevel).addAll(Arrays.asList(trades));
    }

}