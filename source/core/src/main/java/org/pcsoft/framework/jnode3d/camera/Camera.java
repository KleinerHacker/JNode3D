package org.pcsoft.framework.jnode3d.camera;

import org.pcsoft.framework.jnode3d.internal.ogl.OpenGL;
import org.pcsoft.framework.jnode3d.type.MatrixMode;

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

    public final void apply(OpenGL OGL, int width, int height) {
        OGL.glMatrixMode(MatrixMode.Projection);
        OGL.glLoadIdentity();
        applyTransformation(OGL, width, height);
    }

    protected abstract void applyTransformation(OpenGL OGL, int width, int height);
}
