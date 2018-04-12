package org.pcsoft.framework.jnode3d.type.light;

import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.type.Color;

import java.util.ArrayList;
import java.util.List;

public final class DirectionalLight extends Light {
    private Vector3f direction = new Vector3f(1, -1, 1);

    private final List<ValueChangedListener<Vector3f>> directionChangedListenerList = new ArrayList<>();

    public DirectionalLight() {
    }

    public DirectionalLight(Vector3f direction) {
        this.direction = direction;
    }

    public DirectionalLight(Color color, float power) {
        super(color, power);
    }

    public DirectionalLight(Color color, Vector3f direction) {
        super(color);
        this.direction = direction;
    }

    public DirectionalLight(Color color, float power, Vector3f direction) {
        super(color, power);
        this.direction = direction;
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction;
        fireDirectionChanged(direction);
        fireChanged();
    }

    //<editor-fold desc="Listeners">
    public void addDirectionChangedListener(ValueChangedListener<Vector3f> listener) {
        directionChangedListenerList.add(listener);
    }

    public void removeDirectionChangedListener(ValueChangedListener<Vector3f> listener) {
        directionChangedListenerList.remove(listener);
    }

    protected final void fireDirectionChanged(Vector3f direction) {
        for (final ValueChangedListener<Vector3f> listener : directionChangedListenerList) {
            listener.onChanged(direction);
        }
    }
    //</editor-fold>
}
