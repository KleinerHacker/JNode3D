package org.pcsoft.framework.jnode3d.camera;

import org.pcsoft.framework.jnode3d.internal.GL;

public abstract class Camera {
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

    public final void apply(GL gl, int width, int height) {
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        applyTransformation(gl, width, height);

        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    protected abstract void applyTransformation(GL gl, int width, int height);
}
