package org.mineworld;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

/**
 * MineWorld! Just another Minecraft mod
 */
@Mod(MineWorld.MODID)
public final class MineWorld {

    /**
     * {@link MineWorld MineWorld} {@link String mod ID}
     */
    public static final String MODID = "mineworld";

    /**
     * Constructor. Initialize the mod
     */
    public MineWorld() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}