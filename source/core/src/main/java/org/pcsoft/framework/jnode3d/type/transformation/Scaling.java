package org.pcsoft.framework.jnode3d.type.transformation;

import org.joml.Vector3f;

public final class Scaling extends Transformation {
    private final float x, y, z;

    public Scaling(Vector3f scale) {
        this(scale.x, scale.y, scale.z);
    }

    public Scaling(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public Vector3f getScale() {
        return new Vector3f(x, y, z);
    }
}
