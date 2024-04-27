package org.mineworld.core;

import com.mojang.blaze3d.platform.InputConstants;
import cpw.mods.util.Lazy;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;
import org.mineworld.MineWorld;
import org.mineworld.helper.ResourceHelper;

/**
 * {@link MineWorld MineWorld} {@link KeyMapping Key Mappings}
 */
public final class MWKeyMappings {

    //#region Key Mappings

    public static final Lazy<KeyMapping> CRAWL = Lazy.of(() -> registerKeyMapping("crawl", GLFW.GLFW_KEY_C));

    //#endregion

    //#region Methods

    /**
     * Get a {@link KeyMapping Key Mapping}
     *
     * @param name {@link String The Key Mapping name}
     * @param keyCode {@link Integer The Key Code}
     * @return {@link KeyMapping The Key Mapping}
     */
    private static KeyMapping registerKeyMapping(final String name, final int keyCode) {
        return new KeyMapping("key." + MineWorld.MOD_ID + "." + ResourceHelper.lower(name), KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, keyCode, "key.category." + MineWorld.MOD_ID);
    }

    //#endregion
}