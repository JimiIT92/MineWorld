package org.mineworld.core;

import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.item.MWFuelItem;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link Item items}
 */
public final class MWItems {

    /**
     * {@link DeferredRegister<Item> The item registry}
     */
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MineWorld.MODID);

    //#region Items

    public static final RegistryObject<Item> RUBY = registerSimpleItem("ruby");
    public static final RegistryObject<Item> SAPPHIRE = registerSimpleItem("sapphire");
    public static final RegistryObject<Item> PYRITE = registerFuel("pyrite", 120);
    public static final RegistryObject<Item> RAW_SILVER = registerSimpleItem("raw_silver");
    public static final RegistryObject<Item> SILVER_INGOT = registerSimpleItem("silver_ingot");
    public static final RegistryObject<Item> SILVER_NUGGET = registerSimpleItem("silver_nugget");
    public static final RegistryObject<Item> RAW_ALUMINUM = registerSimpleItem("raw_aluminum");
    public static final RegistryObject<Item> ALUMINUM_INGOT = registerSimpleItem("aluminum_ingot");
    public static final RegistryObject<Item> ALUMINUM_NUGGET = registerSimpleItem("aluminum_nugget");
    public static final RegistryObject<Item> RAW_BRONZE = registerSimpleItem("raw_bronze");
    public static final RegistryObject<Item> BRONZE_INGOT = registerSimpleItem("bronze_ingot");
    public static final RegistryObject<Item> BRONZE_NUGGET = registerSimpleItem("bronze_nugget");
    public static final RegistryObject<Item> COPPER_NUGGET = registerSimpleItem("copper_nugget");
    public static final RegistryObject<Item> RAW_BRONZE_SMITHING_TEMPLATE = registerSimpleItem("raw_bronze_smithing_template", FeatureFlags.UPDATE_1_20);

    //#endregion

    /**
     * Get the basic {@link Item.Properties item properties}
     *
     * @param featureFlags {@link FeatureFlag Required feature flags} to be enabled for this item to be added
     * @return {@link Item.Properties item properties}
     */
    public static Item.Properties basicProperties(FeatureFlag... featureFlags) {
        Item.Properties properties = new Item.Properties();
        if(featureFlags != null) {
            properties = properties.requiredFeatures(featureFlags);
        }
        return properties;
    }

    /**
     * Register a basic {@link Item item}
     *
     * @param name {@link String The item name}
     * @param featureFlags {@link FeatureFlag Required feature flags} to be enabled for this item to be added
     * @return {@link RegistryObject<Item> The registered item}
     */
    private static RegistryObject<Item> registerSimpleItem(final String name, FeatureFlag... featureFlags) {
        return registerItem(name, () -> new Item(basicProperties(featureFlags)));
    }

    /**
     * Register a {@link MWFuelItem fuel item}
     *
     * @param name {@link String The item name}
     * @param burnTime {@link Integer The fuel burn time} in seconds
     * @return {@link RegistryObject<Item> The registered item}
     */
    private static RegistryObject<Item> registerFuel(final String name, final int burnTime) {
        return registerItem(name, () -> new MWFuelItem(burnTime));
    }

    /**
     * Register an {@link Item item}
     *
     * @param name {@link String The item name}
     * @param itemSupplier {@link Supplier<Item> The item supplier}
     * @return {@link RegistryObject<Item> The registered item}
     */
    public static RegistryObject<Item> registerItem(final String name, final Supplier<Item> itemSupplier) {
        return ITEMS.register(name, itemSupplier);
    }

    /**
     * Get a default {@link ItemStack Item Stack} from a {@link RegistryObject<Block> block registry object}
     *
     * @param registryObject {@link RegistryObject<Block> The block registry object}
     * @return {@link ItemStack The default Item Stack}
     */
    public static ItemStack getDefaultStack(RegistryObject<? extends ItemLike> registryObject) {
        return getDefaultStack(registryObject.get().asItem());
    }

    /**
     * Get a default {@link ItemStack Item Stack} from an {@link ItemLike Item Like} object
     * (namely blocks and items)
     *
     * @param object {@link ItemLike The object}
     * @return {@link ItemStack The default Item Stack}
     */
    public static ItemStack getDefaultStack(ItemLike object) {
        return getDefaultStack(object.asItem());
    }

    /**
     * Get a default {@link ItemStack Item Stack} from an {@link Item item}
     *
     * @param item {@link Item The item}
     * @return {@link ItemStack The default Item Stack}
     */
    public static ItemStack getDefaultStack(Item item) {
        return item.getDefaultInstance();
    }

    /**
     * Register the {@link MineWorld MineWorld} {@link Item items}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
