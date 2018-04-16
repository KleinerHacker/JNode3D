package org.pcsoft.framework.jnode3d.internal.manager;

import org.pcsoft.framework.jnode3d.type.light.AmbientLight;
import org.pcsoft.framework.jnode3d.type.light.DirectionalLight;
import org.pcsoft.framework.jnode3d.type.light.Light;
import org.pcsoft.framework.jnode3d.type.light.PositionableLight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public final class LightManager implements OpenGLDependendManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(LightManager.class);
    private static final LightManager instance = new LightManager();

    public static LightManager getInstance() {
        return instance;
    }

    private boolean initialized = false;

    private final AmbientLight ambientLight = new AmbientLight();
    private final DirectionalLight directionalLight = new DirectionalLight();
    private final Set<PositionableLight> lightList = new HashSet<>();

    private final List<LightChangedListener> ambientLightChangedListenerList = new ArrayList<>();
    private final List<LightChangedListener> directionalLightChangedListenerList = new ArrayList<>();
    private final List<LightChangedListener> lightListChangedListenerList = new ArrayList<>();

    private final PositionableLightHandler positionableLightHandler = new PositionableLightHandler();

    private LightManager() {
        ambientLight.addChangedListener(new Light.ValueChangedListener<Light>() {
            @Override
            public void onChanged(Light value) {
                fireAmbientLightChanged();
            }
        });
        directionalLight.addChangedListener(new Light.ValueChangedListener<Light>() {
            @Override
            public void onChanged(Light value) {
                fireDirectionalLightChanged();
            }
        });
    }

    //<editor-fold desc="Initialisation">
    @Override
    public void initialize() {
        if (initialized)
            throw new IllegalStateException("Already initialized");

        LOGGER.info("Initialize Light Manager");

        initialized = true;
    }

    @Override
    public void destroy() {
        if (!initialized)
            throw new IllegalStateException("Not initialized yet");

        LOGGER.info("Destroy Light Manager");

        initialized = false;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }
    //</editor-fold>

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public DirectionalLight getDirectionalLight() {
        return directionalLight;
    }

    public void registerLight(PositionableLight light) {
        LOGGER.debug("Register light " + light.getDebugName());

        lightList.add(light);
        light.addChangedListener(positionableLightHandler);
        fireLightListChanged();
    }

    public void unregisterLight(PositionableLight light) {
        LOGGER.debug("Unregister light " + light.getDebugName());

        light.removeChangedListener(positionableLightHandler);
        lightList.remove(light);
        fireLightListChanged();
    }

    public Collection<PositionableLight> getLights() {
        return Collections.unmodifiableCollection(lightList);
    }

    //<editor-fold desc="Listeners">
    public void addAmbientLightChangedListener(LightChangedListener listener) {
        ambientLightChangedListenerList.add(listener);
    }

    public void removeAmbientLightChangedListener(LightChangedListener listener) {
        ambientLightChangedListenerList.remove(listener);
    }

    protected final void fireAmbientLightChanged() {
        for (final LightChangedListener listener : ambientLightChangedListenerList) {
            listener.onChanged();
        }
    }

    public void addDirectionalLightChangedListener(LightChangedListener listener) {
        directionalLightChangedListenerList.add(listener);
    }

    public void removeDirectionalLightChangedListener(LightChangedListener listener) {
        directionalLightChangedListenerList.remove(listener);
    }

    protected final void fireDirectionalLightChanged() {
        for (final LightChangedListener listener : directionalLightChangedListenerList) {
            listener.onChanged();
        }
    }

    public void addLightListChangedListener(LightChangedListener listener) {
        lightListChangedListenerList.add(listener);
    }

    public void removeLightListChangedListener(LightChangedListener listener) {
        lightListChangedListenerList.add(listener);
    }

    protected final void fireLightListChanged() {
        for (final LightChangedListener listener : lightListChangedListenerList) {
            listener.onChanged();
        }
    }
    //</editor-fold>

    public interface LightChangedListener {
        void onChanged();
    }

    private final class PositionableLightHandler implements Light.ValueChangedListener<Light> {
        @Override
        public void onChanged(Light value) {
            fireLightListChanged();
        }
    }
}
