package org.pcsoft.framework.jnode3d.internal.manager;

import org.pcsoft.framework.jnode3d.anim.AnimationBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public final class AnimationManager implements Manager {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnimationManager.class);
    private static final AnimationManager instance = new AnimationManager();

    public static AnimationManager getInstance() {
        return instance;
    }

    private final Set<AnimationBase> animationList = new HashSet<>();

    private AnimationManager() {
    }

    public void registerAnimation(AnimationBase animation) {
        LOGGER.debug("Register animation");
        animationList.add(animation);
    }

    public void unregisterAnimation(AnimationBase animation) {
        LOGGER.debug("Unregister animation");
        animationList.remove(animation);
    }

    public Collection<AnimationBase> getAnimations() {
        return Collections.unmodifiableCollection(animationList);
    }
}
