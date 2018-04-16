package org.pcsoft.framework.jnode3d.type.transformation;

import org.joml.Vector3f;

public final class Translation extends Transformation {
    private final float x, y, z;

    public Translation(Vector3f trans) {
        this(trans.x, trans.y, trans.z);
    }

    public Translation(float x, float y, float z) {
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

    public Vector3f getPosition() {
        return new Vector3f(x, y, z);
    }
}
