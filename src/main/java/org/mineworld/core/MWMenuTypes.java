package org.mineworld.core;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.client.screen.ForgingTableScreen;
import org.mineworld.client.screen.WoodcutterScreen;
import org.mineworld.helper.RegisterHelper;
import org.mineworld.inventory.ForgingTableMenu;
import org.mineworld.inventory.WoodcutterMenu;

/**
 * {@link MineWorld MineWorld} {@link MenuType menu types}
 */
public class MWMenuTypes {

    public static RegistryObject<MenuType<WoodcutterMenu>> WOODCUTTER = RegisterHelper.registerMenuType("woodcutter", WoodcutterMenu::new);
    public static RegistryObject<MenuType<ForgingTableMenu>> FORGING_TABLE = RegisterHelper.registerMenuType("forging_table", ForgingTableMenu::new);

    /**
     * Register the {@link MineWorld MineWorld} {@link MenuType menu types}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(IEventBus eventBus) {
        RegisterHelper.registerMenuTypes(eventBus);
    }

    /**
     * Register the {@link MineWorld MineWorld} screens
     */
    public static void registerScreens() {
        MenuScreens.register(WOODCUTTER.get(), WoodcutterScreen::new);
        MenuScreens.register(FORGING_TABLE.get(), ForgingTableScreen::new);
    }

}