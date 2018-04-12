package org.pcsoft.framework.jnode3d.type.light;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.type.Color;

import java.util.ArrayList;
import java.util.List;

public abstract class PositionableLight extends Light {
    private Vector3f position = new Vector3f();
    private Matrix4f transformationMatrix = new Matrix4f();

    private final List<ValueChangedListener<Vector3f>> positionChangedListenerList = new ArrayList<>();
    private final List<ValueChangedListener<Matrix4f>> transformationMatrixChangedListenerList = new ArrayList<>();

    public PositionableLight() {
    }

    public PositionableLight(Vector3f position) {
        this.position = position;
    }

    public PositionableLight(Color color, Vector3f position) {
        super(color);
        this.position = position;
    }

    public PositionableLight(Color color, float power, Vector3f position) {
        super(color, power);
        this.position = position;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
        firePositionChanged(position);
        fireChanged();
    }

    public Matrix4f getTransformationMatrix() {
        return transformationMatrix;
    }

    public void setTransformationMatrix(Matrix4f transformationMatrix) {
        this.transformationMatrix = transformationMatrix;
        fireTransformationMatrixChanged(transformationMatrix);
        fireChanged();
    }

    //<editor-fold desc="Listeners">
    public void addPositionChangedListener(ValueChangedListener<Vector3f> listener) {
        positionChangedListenerList.add(listener);
    }

    public void removePositionChangedListener(ValueChangedListener<Vector3f> listener) {
        positionChangedListenerList.remove(listener);
    }

    protected final void firePositionChanged(Vector3f position) {
        for (final ValueChangedListener<Vector3f> listener : positionChangedListenerList) {
            listener.onChanged(position);
        }
    }

    public void addTransformationMatrixChangedListener(ValueChangedListener<Matrix4f> listener) {
        transformationMatrixChangedListenerList.add(listener);
    }

    public void removeTransformationMatrixChangedListener(ValueChangedListener<Matrix4f> listener) {
        transformationMatrixChangedListenerList.remove(listener);
    }

    protected final void fireTransformationMatrixChanged(Matrix4f transformationMatrix) {
        for (final ValueChangedListener<Matrix4f> listener : transformationMatrixChangedListenerList) {
            listener.onChanged(transformationMatrix);
        }
    }
    //</editor-fold>
}
