package org.mineworld.core;

import net.minecraft.client.animation.AnimationDefinition;
import org.mineworld.MineWorld;
import org.mineworld.client.model.ReaperModel;

/**
 * {@link MineWorld MineWorld} {@link AnimationDefinition animations}
 */
public final class MWAnimations {

    public static final AnimationDefinition REAPER_IDLE = ReaperModel.getIdleAnimation();
    public static final AnimationDefinition REAPER_CHARGE = ReaperModel.getChargeAnimation();

}