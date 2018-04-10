package org.pcsoft.framework.jnode3d.internal.manager;

import org.pcsoft.framework.jnode3d.anim.AnimationBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public final class AnimationManager implements Manager {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnimationManager.class);
    private static final AnimationManager instance = new AnimationManager();

    public static AnimationManager getInstance() {
        return instance;
    }

    private final List<AnimationBase> animationList = new ArrayList<>();
    private boolean initialized = false;

    private AnimationManager() {
    }

    @Override
    public void initialize() {
        animationList.clear();
        initialized = true;
    }

    @Override
    public void destroy() {
        animationList.clear();
        initialized = false;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    public List<AnimationBase> getAnimationList() {
        return animationList;
    }
}
