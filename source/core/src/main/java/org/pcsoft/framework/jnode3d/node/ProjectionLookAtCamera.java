package org.pcsoft.framework.jnode3d.node;

import org.joml.Vector3f;

public final class ProjectionLookAtCamera extends ProjectionCamera {
    private Vector3f lookAt;

    public Vector3f getLookAt() {
        return lookAt;
    }

    public void setLookAt(Vector3f lookAt) {
        this.lookAt = lookAt;
    }

    public void setLookAt(float x, float y, float z) {
        setLookAt(new Vector3f(x, y, z));
    }
}
