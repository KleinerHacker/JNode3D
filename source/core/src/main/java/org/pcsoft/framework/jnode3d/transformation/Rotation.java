package org.pcsoft.framework.jnode3d.transformation;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public final class Rotation extends Transformation {
    private float angle;
    private float axisX, axisY, axisZ;

    Rotation(float angle, float axisX, float axisY, float axisZ) {
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

    public Vector3D getAxis() {
        return new Vector3D(axisX, axisY, axisZ);
    }

    public void setAxis(Vector3D axis) {
        axisX = (float) axis.getX();
        axisY = (float) axis.getY();
        axisZ = (float) axis.getZ();
    }
}
