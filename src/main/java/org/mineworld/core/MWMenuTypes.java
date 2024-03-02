package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.client.screen.ForgingTableScreen;
import org.mineworld.client.screen.WoodcutterScreen;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.inventory.ForgingTableMenu;
import org.mineworld.inventory.WoodcutterMenu;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link MenuType Menu Types}
 */
public final class MWMenuTypes {

    //#region Registry

    /**
     * The {@link DeferredRegister<MenuType> Menu Types Registry}
     */
    private static final DeferredRegister<MenuType<?>> MENU_TYPES = RegistryHelper.registry(ForgeRegistries.MENU_TYPES);

    //#endregion

    //#region Menu Types

    public static final RegistryObject<MenuType<WoodcutterMenu>> WOODCUTTER = registerMenuType("woodcutter", WoodcutterMenu::new);
    public static final RegistryObject<MenuType<ForgingTableMenu>> FORGING_TABLE = registerMenuType("forging_table", ForgingTableMenu::new);

    //#endregion

    //#region Methods

    /**
     * Register a {@link MenuType Menu Type}
     *
     * @param name {@link String The Menu name}
     * @param menuContainerFactory {@link IContainerFactory The Menu Container Factory}
     * @return {@link RegistryObject<MenuType> The registered Menu Type}
     * @param <T> {@link T The Menu Type}
     */
    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(final String name, final IContainerFactory<T> menuContainerFactory) {
        return MENU_TYPES.register(name, Suppliers.memoize(() -> IForgeMenuType.create(menuContainerFactory)));
    }

    /**
     * Register a {@link AbstractContainerScreen Menu Screen}
     *
     * @param menuScreenConstructor {@link MenuScreens.ScreenConstructor The Menu Screen Constructor}
     * @param menuTypeSuppliers {@link Supplier<MenuType> The Menu Type Supplier}
     * @param <M> {@link M The Menu Type}
     * @param <U> {@link U The Screen Type}
     */
    @SafeVarargs
    private static <M extends AbstractContainerMenu, U extends Screen & MenuAccess<M>> void registerScreen(final MenuScreens.ScreenConstructor<M, U> menuScreenConstructor, final Supplier<MenuType<? extends M>>... menuTypeSuppliers) {
        Arrays.stream(menuTypeSuppliers).forEach(menuTypeSupplier -> MenuScreens.register(menuTypeSupplier.get(), menuScreenConstructor));
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link MenuType Menu Types}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }

    /**
     * Register all {@link MineWorld MineWorld} {@link AbstractContainerScreen Screens}
     */
    public static void registerScreens() {
        registerScreen(WoodcutterScreen::new, () -> WOODCUTTER.get());
        registerScreen(ForgingTableScreen::new, () -> FORGING_TABLE.get());
    }

    //#endregion

}