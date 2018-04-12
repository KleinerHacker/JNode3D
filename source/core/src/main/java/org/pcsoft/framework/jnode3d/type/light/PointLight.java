package org.pcsoft.framework.jnode3d.type.light;

import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.type.Color;

import java.util.ArrayList;
import java.util.List;

public final class PointLight extends PositionableLight {
    private float attitude = 0.5f;

    private final List<ValueChangedListener<Float>> attitudeChangedListenerList = new ArrayList<>();

    public PointLight() {
    }

    public PointLight(Vector3f position) {
        super(position);
    }

    public PointLight(Color color, Vector3f position) {
        super(color, position);
    }

    public PointLight(Color color, Vector3f position, float attitude) {
        super(color, position);
        this.attitude = attitude;
    }

    public PointLight(Color color, float power, Vector3f position, float attitude) {
        super(color, power, position);
        this.attitude = attitude;
    }

    public float getAttitude() {
        return attitude;
    }

    public void setAttitude(float attitude) {
        this.attitude = attitude;
        fireAttitudeChanged(attitude);
        fireChanged();
    }

    //<editor-fold desc="Listeners">
    public void addAttitudeChangedListener(ValueChangedListener<Float> listener) {
        attitudeChangedListenerList.add(listener);
    }

    public void removeAttitudeChangedListener(ValueChangedListener<Float> listener) {
        attitudeChangedListenerList.remove(listener);
    }

    protected final void fireAttitudeChanged(float attitude) {
        for (final ValueChangedListener<Float> listener : attitudeChangedListenerList) {
            listener.onChanged(attitude);
        }
    }
    //</editor-fold>
}
