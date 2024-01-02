package org.mineworld.event;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWTabs;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Handle all events for {@link CreativeModeTab Creative Mode Tabs}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class CreativeModeTabEvents {

    /**
     * Set the {@link MineWorld MineWorld} {@link CreativeModeTab Creative Mode Tab contents}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    @SubscribeEvent
    public static void onTabContentsEvent(final BuildCreativeModeTabContentsEvent event) {
        final ResourceKey<CreativeModeTab> tabKey = event.getTabKey();
        if(isTab(tabKey, CreativeModeTabs.INGREDIENTS)) {
            removeMineWorldEnchantmentsFromVanillaIngredientsTab(event);
        }
        else if(isTab(tabKey, MWTabs.BUILDING_BLOCKS)) {
            setBuildingBlocksTab(event);
        }
        else if(isTab(tabKey, MWTabs.COLORED_BLOCKS)) {
            setColoredBlocksTab(event);
        }
        else if(isTab(tabKey, MWTabs.NATURAL)) {
            setNaturalBlocksTab(event);
        }
        else if(isTab(tabKey, MWTabs.FUNCTIONAL)) {
            setFunctionalBlocksTab(event);
        }
        else if(isTab(tabKey, MWTabs.REDSTONE)) {
            setRedstoneBlocksTab(event);
        }
        else if(isTab(tabKey, MWTabs.TOOLS)) {
            setToolsTab(event);
        }
        else if(isTab(tabKey, MWTabs.COMBAT)) {
            setCombatTab(event);
        }
        else if(isTab(tabKey, MWTabs.FOOD_AND_DRINK)) {
            setFoodAndDrinkTab(event);
        }
        else if(isTab(tabKey, MWTabs.INGREDIENTS)) {
            setIngredientsTab(event);
        }
        else if(isTab(tabKey, MWTabs.SPAWN_EGGS)) {
            setSpawnEggsTab(event);
        }
    }

    /**
     * Check if the provided {@link ResourceKey<CreativeModeTab> Creative Mode Tab Key} corresponds to a
     * {@link MineWorld MineWorld} {@link RegistryObject<CreativeModeTab> Registered Creative Mode Tab}
     *
     * @param tabKey {@link ResourceKey<CreativeModeTab> The Creative Mode Tab Key}
     * @param target {@link RegistryObject<CreativeModeTab> The target Creative Mode Tab}
     * @return {@link Boolean True if the key corresponds to the target tab}
     */
    private static boolean isTab(final ResourceKey<CreativeModeTab> tabKey, final RegistryObject<CreativeModeTab> target) {
        return isTab(tabKey, target.getKey());
    }

    /**
     * Check if the provided {@link ResourceKey<CreativeModeTab> Creative Mode Tab Key} corresponds to a
     * {@link MineWorld MineWorld} {@link RegistryObject<CreativeModeTab> Registered Creative Mode Tab}
     *
     * @param tabKey {@link ResourceKey<CreativeModeTab> The Creative Mode Tab Key}
     * @param target {@link ResourceKey<CreativeModeTab> The target Creative Mode Tab}
     * @return {@link Boolean True if the key corresponds to the target tab}
     */
    private static boolean isTab(final ResourceKey<CreativeModeTab> tabKey, final ResourceKey<CreativeModeTab> target) {
        return tabKey.equals(target);
    }

    /**
     * Removes the {@link MineWorld MineWorld} {@link Enchantment enchantments} from the {@link CreativeModeTabs#INGREDIENTS Vanilla Ingredients Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void removeMineWorldEnchantmentsFromVanillaIngredientsTab(final BuildCreativeModeTabContentsEvent event) {
        final Iterator<Map.Entry<ItemStack, CreativeModeTab.TabVisibility>> iterator = event.getEntries().iterator();
        final ArrayList<ItemStack> itemsToRemove = new ArrayList<>();
        while (iterator.hasNext()) {
            final ItemStack stack = iterator.next().getKey();
            if(stack.is(Items.ENCHANTED_BOOK) && ResourceLocation.tryParse(EnchantedBookItem.getEnchantments(stack).getCompound(0).getString("id")).getNamespace().equals(MineWorld.MOD_ID)) {
                itemsToRemove.add(stack);
            }
        }
        itemsToRemove.forEach(stack -> event.getEntries().remove(stack));
    }

    /**
     * Set the content of the {@link MWTabs#BUILDING_BLOCKS Building Blocks Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setBuildingBlocksTab(final BuildCreativeModeTabContentsEvent event) {
        addToTab(event,
                Blocks.STRUCTURE_VOID
        );
    }

    /**
     * Set the content of the {@link MWTabs#COLORED_BLOCKS Colored Blocks Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setColoredBlocksTab(final BuildCreativeModeTabContentsEvent event) {
        addToTab(event,
                Blocks.STRUCTURE_VOID
        );
    }

    /**
     * Set the content of the {@link MWTabs#NATURAL Natural Blocks Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setNaturalBlocksTab(final BuildCreativeModeTabContentsEvent event) {
        addToTab(event,
                Blocks.STRUCTURE_VOID
        );
    }

    /**
     * Set the content of the {@link MWTabs#FUNCTIONAL Functional Blocks Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setFunctionalBlocksTab(final BuildCreativeModeTabContentsEvent event) {
        addToTab(event,
                Blocks.STRUCTURE_VOID
        );
    }

    /**
     * Set the content of the {@link MWTabs#REDSTONE Redstone Blocks Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setRedstoneBlocksTab(final BuildCreativeModeTabContentsEvent event) {
        addToTab(event,
                Blocks.STRUCTURE_VOID
        );
    }

    /**
     * Set the content of the {@link MWTabs#TOOLS Tools Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setToolsTab(final BuildCreativeModeTabContentsEvent event) {
        addToTab(event,
                Blocks.STRUCTURE_VOID
        );
    }

    /**
     * Set the content of the {@link MWTabs#COMBAT Combat Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setCombatTab(final BuildCreativeModeTabContentsEvent event) {
        addToTab(event,
                Blocks.STRUCTURE_VOID
        );
    }

    /**
     * Set the content of the {@link MWTabs#FOOD_AND_DRINK Food and Drink Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setFoodAndDrinkTab(final BuildCreativeModeTabContentsEvent event) {
        addToTab(event,
                Blocks.STRUCTURE_VOID
        );
    }

    /**
     * Set the content of the {@link MWTabs#INGREDIENTS Ingredients Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setIngredientsTab(final BuildCreativeModeTabContentsEvent event) {
        setIngredientsTabEnchantments(event);
    }

    /**
     * Add the {@link MineWorld MineWorld} {@link Enchantment Enchantments} to the {@link MWTabs#INGREDIENTS Ingredients Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setIngredientsTabEnchantments(final BuildCreativeModeTabContentsEvent event) {
        event.getParameters().holders().lookup(Registries.ENCHANTMENT).ifPresent(enchantmentRegistryLookup -> {
            final Supplier<Stream<Enchantment>> enchantments = () -> enchantmentRegistryLookup.listElements()
                    .filter(enchantment -> enchantment.key().location().getNamespace().equals(MineWorld.MOD_ID))
                    .map(Holder::value)
                    .filter(enchantment -> enchantment.allowedInCreativeTab(Items.ENCHANTED_BOOK, EnumSet.allOf(EnchantmentCategory.class)));
            enchantments.get().map(enchantment -> EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, enchantment.getMaxLevel())))
                    .forEach(enchantedBook -> event.accept(enchantedBook, CreativeModeTab.TabVisibility.PARENT_TAB_ONLY));
            enchantments.get().flatMap(enchantment -> IntStream.rangeClosed(enchantment.getMinLevel(), enchantment.getMaxLevel())
                            .mapToObj(level -> EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, level))))
                    .forEach(enchantedBook -> event.accept(enchantedBook, CreativeModeTab.TabVisibility.PARENT_TAB_ONLY));
        });
    }

    /**
     * Set the content of the {@link MWTabs#SPAWN_EGGS Spawn Eggs Tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent The Creative Mode Tab Build Contents event}
     */
    private static void setSpawnEggsTab(final BuildCreativeModeTabContentsEvent event) {
        addToTab(event,
                Blocks.STRUCTURE_VOID
        );
    }

    /**
     * Add some {@link T items} to a {@link CreativeModeTab creative tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent Creative mode tab build contents event}
     * @param items {@link T The items to add}
     */
    @SafeVarargs
    private static <T extends ItemLike> void addToTab(final BuildCreativeModeTabContentsEvent event, @NotNull final T... items) {
        addToTab(event, Arrays.stream(items).map(item -> item.asItem().getDefaultInstance()).toList());
    }

    /**
     * Add some {@link RegistryObject items} to a {@link CreativeModeTab creative tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent Creative mode tab build contents event}
     * @param items {@link RegistryObject The items to add}
     */
    @SafeVarargs
    private static void addToTab(final BuildCreativeModeTabContentsEvent event, @NotNull final RegistryObject<? extends ItemLike>... items) {
        addToTab(event, Arrays.stream(items).map(item -> item.get().asItem().getDefaultInstance()).toList());
    }

    /**
     * Add some {@link ItemStack items} to a {@link CreativeModeTab creative tab}
     *
     * @param event {@link BuildCreativeModeTabContentsEvent Creative mode tab build contents event}
     * @param items {@link List<ItemStack> The items to add}
     */
    private static void addToTab(final BuildCreativeModeTabContentsEvent event, @NotNull final List<ItemStack> items) {
        event.acceptAll(items);
    }

}