package org.pcsoft.framework.jnode3d.type.transformation;

import org.joml.Vector3f;

public final class Rotation extends Transformation {
    private float angle;
    private float axisX, axisY, axisZ;

    public Rotation(float angle) {
        this(angle, new Vector3f(0, 1, 0));
    }

    public Rotation(float angle, Vector3f axis) {
        this(angle, axis.x, axis.y, axis.z);
    }

    public Rotation(float angle, float axisX, float axisY, float axisZ) {
        this.angle = angle;
        this.axisX = axisX;
        this.axisY = axisY;
        this.axisZ = axisZ;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getAxisX() {
        return axisX;
    }

    public void setAxisX(float axisX) {
        this.axisX = axisX;
    }

    public float getAxisY() {
        return axisY;
    }

    public void setAxisY(float axisY) {
        this.axisY = axisY;
    }

    public float getAxisZ() {
        return axisZ;
    }

    public void setAxisZ(float axisZ) {
        this.axisZ = axisZ;
    }

    public Vector3f getAxis() {
        return new Vector3f(axisX, axisY, axisZ);
    }

    public void setAxis(Vector3f axis) {
        axisX = axis.x();
        axisY = axis.y();
        axisZ = axis.z();
    }
}
