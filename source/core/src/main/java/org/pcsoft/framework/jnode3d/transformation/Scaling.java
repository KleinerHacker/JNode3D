package org.pcsoft.framework.jnode3d.transformation;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public final class Scaling extends Transformation {
    private float x, y, z;

    Scaling(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public Vector3D getScale() {
        return new Vector3D(x, y, z);
    }

    public void setScale(Vector3D scale) {
        x = (float) scale.getX();
        y = (float) scale.getY();
        z = (float) scale.getZ();
    }
}
