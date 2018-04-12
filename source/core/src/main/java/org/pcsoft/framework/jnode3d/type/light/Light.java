package org.pcsoft.framework.jnode3d.type.light;

import org.pcsoft.framework.jnode3d.type.Color;

import java.util.ArrayList;
import java.util.List;

public abstract class Light {
    private Color color = Color.WHITE;
    private float power = 1f;

    private final List<ValueChangedListener<Light>> changedListenerList = new ArrayList<>();
    private final List<ValueChangedListener<Color>> colorChangedListenerList = new ArrayList<>();
    private final List<ValueChangedListener<Float>> powerChangedListenerList = new ArrayList<>();

    public Light() {
    }

    public Light(Color color) {
        this();
        this.color = color;
    }

    public Light(Color color, float power) {
        this(color);
        this.power = power;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        fireColorChanged(color);
        fireChanged();
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
        firePowerChanged(power);
        fireChanged();
    }

    //<editor-fold desc="Listeners">
    public void addColorChangedListener(ValueChangedListener<Color> listener) {
        colorChangedListenerList.add(listener);
    }

    public void removeColorChangedListener(ValueChangedListener<Color> listener) {
        colorChangedListenerList.remove(listener);
    }

    protected final void fireColorChanged(Color color) {
        for (final ValueChangedListener<Color> listener : colorChangedListenerList) {
            listener.onChanged(color);
        }
    }

    public void addPowerChangedListener(ValueChangedListener<Float> listener) {
        powerChangedListenerList.add(listener);
    }

    public void removePowerChangedListener(ValueChangedListener<Float> listener) {
        powerChangedListenerList.remove(listener);
    }

    protected final void firePowerChanged(float power) {
        for (final ValueChangedListener<Float> listener : powerChangedListenerList) {
            listener.onChanged(power);
        }
    }

    public void addChangedListener(ValueChangedListener<Light> listener) {
        changedListenerList.add(listener);
    }

    public void removeChangedListener(ValueChangedListener<Light> listener) {
        changedListenerList.remove(listener);
    }

    protected final void fireChanged() {
        for (final ValueChangedListener<Light> listener : changedListenerList) {
            listener.onChanged(this);
        }
    }
    //</editor-fold>

    public interface ValueChangedListener<T> {
        void onChanged(T value);
    }
}
