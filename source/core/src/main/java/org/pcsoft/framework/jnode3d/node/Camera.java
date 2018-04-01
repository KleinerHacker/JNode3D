package org.pcsoft.framework.jnode3d.node;

public abstract class Camera extends Node3D {
    private float near = 0.001f, far = 100.0f;

    public Camera() {
    }

    public Camera(float near, float far) {
        this.near = near;
        this.far = far;
    }

    public float getNear() {
        return near;
    }

    public void setNear(float near) {
        this.near = near;
    }

    public float getFar() {
        return far;
    }

    public void setFar(float far) {
        this.far = far;
    }
}
