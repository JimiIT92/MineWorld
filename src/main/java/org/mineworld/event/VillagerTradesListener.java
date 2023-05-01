package org.mineworld.event;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWItems;
import org.mineworld.core.MWVillagerProfessions;
import org.mineworld.helper.ItemHelper;
import org.mineworld.villager.MWItemsForVillagerTypeEmerald;

import java.util.Arrays;

/**
 * Handle the trading with villagers
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class VillagerTradesListener {

    /**
     * Add custom recipes to villager trades
     *
     * @param event {@link VillagerTradesEvent The villager trades event}
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
     * Add some trades to the {@link WanderingTrader wandering trader}
     *
     * @param event {@link WandererTradesEvent The wanderer trades event}
     */
    @SubscribeEvent
    public static void onWanderingVillagerTrades(final WandererTradesEvent event) {
        event.getRareTrades().add(new BasicItemListing(3, ItemHelper.getDefaultStack(MWBlocks.LAVA_ROCK), 6, 1));
        event.getRareTrades().add(new BasicItemListing(6, ItemHelper.getDefaultStack(MWBlocks.PERENNIAL_ICE), 6, 1));
    }

    /**
     * Add some trades to the {@link MWVillagerProfessions#CARPENTER carpenter villager}
     *
     * @param event {@link VillagerTradesEvent The villager trades event}
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
                        .put(VillagerType.TAIGA, ItemHelper.getItem(MWBlocks.SPRUCE_BOOKSHELF.get()))
                        .put(VillagerType.SNOW, ItemHelper.getItem(MWBlocks.SPRUCE_BOOKSHELF.get()))
                        .put(VillagerType.DESERT, ItemHelper.getItem(MWBlocks.JUNGLE_BOOKSHELF.get()))
                        .put(VillagerType.JUNGLE, ItemHelper.getItem(MWBlocks.JUNGLE_BOOKSHELF.get()))
                        .put(VillagerType.SAVANNA, ItemHelper.getItem(MWBlocks.ACACIA_BOOKSHELF.get()))
                        .put(VillagerType.SWAMP, ItemHelper.getItem(MWBlocks.DARK_OAK_BOOKSHELF.get()))
                        .build())
        );
        addVillagerTrades(event, 5,
                new MWItemsForVillagerTypeEmerald(1, 12,30, ImmutableMap.<VillagerType, Item>builder()
                        .put(VillagerType.PLAINS, Items.CHISELED_BOOKSHELF)
                        .put(VillagerType.TAIGA, ItemHelper.getItem(MWBlocks.SPRUCE_CHISELED_BOOKSHELF.get()))
                        .put(VillagerType.SNOW, ItemHelper.getItem(MWBlocks.SPRUCE_CHISELED_BOOKSHELF.get()))
                        .put(VillagerType.DESERT, ItemHelper.getItem(MWBlocks.JUNGLE_CHISELED_BOOKSHELF.get()))
                        .put(VillagerType.JUNGLE, ItemHelper.getItem(MWBlocks.JUNGLE_CHISELED_BOOKSHELF.get()))
                        .put(VillagerType.SAVANNA, ItemHelper.getItem(MWBlocks.ACACIA_CHISELED_BOOKSHELF.get()))
                        .put(VillagerType.SWAMP, ItemHelper.getItem(MWBlocks.DARK_OAK_CHISELED_BOOKSHELF.get()))
                        .build())
        );
    }

    /**
     * Add some trades to the {@link VillagerProfession#FARMER farmer villager}
     *
     * @param event {@link VillagerTradesEvent The villager trades event}
     */
    private static void addFarmerTrades(final VillagerTradesEvent event) {
        addVillagerTrades(event, 1, new VillagerTrades.EmeraldForItems(MWItems.COB.get(), 15, 16, 2));
        addVillagerTrades(event, 2, new VillagerTrades.ItemsAndEmeraldsToItems(MWItems.COB.get(), 6, MWItems.BAKED_COB.get(), 6, 16, 1));
    }

    /**
     * Add some trades to the {@link VillagerProfession#ARMORER armorer villager}
     *
     * @param event {@link VillagerTradesEvent The villager trades event}
     */
    private static void addArmorerTrades(final VillagerTradesEvent event) {
        addVillagerTrades(event, 1,
                new VillagerTrades.ItemsForEmeralds(ItemHelper.getDefaultStack(MWItems.SILVER_LEGGINGS), 7, 1, 12, 1, 0.2F),
                new VillagerTrades.ItemsForEmeralds(ItemHelper.getDefaultStack(MWItems.SILVER_BOOTS), 4, 1, 12, 1, 0.2F),
                new VillagerTrades.ItemsForEmeralds(ItemHelper.getDefaultStack(MWItems.SILVER_HELMET), 5, 1, 12, 1, 0.2F),
                new VillagerTrades.ItemsForEmeralds(ItemHelper.getDefaultStack(MWItems.SILVER_CHESTPLATE), 9, 1, 12, 1, 0.2F));
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
     * Add some trades to the {@link VillagerProfession#WEAPONSMITH weaponsmith villager}
     *
     * @param event {@link VillagerTradesEvent The villager trades event}
     */
    private static void addWeaponsmithTrades(final VillagerTradesEvent event) {
        addVillagerTrades(event, 1,
                new VillagerTrades.ItemsForEmeralds(ItemHelper.getDefaultStack(MWItems.SILVER_AXE), 3, 1, 12, 1, 0.2F),
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
     * Add some trades to the {@link VillagerProfession#TOOLSMITH toolsmith villager}
     *
     * @param event {@link VillagerTradesEvent The villager trades event}
     */
    private static void addToolsmithTrades(final VillagerTradesEvent event) {
        addVillagerTrades(event, 1,
                new VillagerTrades.ItemsForEmeralds(ItemHelper.getDefaultStack(MWItems.ALUMINUM_AXE), 1, 1, 12, 1, 0.2F),
                new VillagerTrades.ItemsForEmeralds(ItemHelper.getDefaultStack(MWItems.ALUMINUM_SHOVEL), 1, 1, 12, 1, 0.2F),
                new VillagerTrades.ItemsForEmeralds(ItemHelper.getDefaultStack(MWItems.ALUMINUM_PICKAXE), 1, 1, 12, 1, 0.2F),
                new VillagerTrades.ItemsForEmeralds(ItemHelper.getDefaultStack(MWItems.ALUMINUM_HOE), 1, 1, 12, 1, 0.2F)
        );
        addVillagerTrades(event, 2,
                new VillagerTrades.EmeraldForItems(MWItems.SILVER_INGOT.get(), 4, 12, 10)
        );
        addVillagerTrades(event, 3,
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.SILVER_AXE.get(), 1, 3, 10, 0.2F),
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.SILVER_SHOVEL.get(), 1, 3, 10, 0.2F),
                new VillagerTrades.EnchantedItemForEmeralds(MWItems.SILVER_PICKAXE.get(), 1, 3, 10, 0.2F),
                new VillagerTrades.ItemsForEmeralds(ItemHelper.getDefaultStack(MWItems.RUBY_HOE), 4, 1, 3, 10, 0.2F),
                new VillagerTrades.ItemsForEmeralds(ItemHelper.getDefaultStack(MWItems.SAPPHIRE_HOE), 4, 1, 3, 10, 0.2F)
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
     * Add some trades to a villager
     *
     * @param event {@link VillagerTradesEvent The villager trades event}
     * @param minVillagerLevel {@link Integer The minimum level the villager must have to show this recipe}
     * @param trades {@link VillagerTrades.ItemListing The trades to add}
     */
    private static void addVillagerTrades(final VillagerTradesEvent event, final int minVillagerLevel, final VillagerTrades.ItemListing... trades) {
        event.getTrades().get(minVillagerLevel).addAll(Arrays.asList(trades));
    }

}