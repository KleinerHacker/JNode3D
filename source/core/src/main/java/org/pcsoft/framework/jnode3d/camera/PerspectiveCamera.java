package org.pcsoft.framework.jnode3d.camera;

import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.ogl.OpenGL;
import org.pcsoft.framework.jnode3d.type.geom.Bounds2D;

public abstract class PerspectiveCamera extends Camera {
    private float angle = 45.0f, aspect = 1f;
    private Vector3f position = new Vector3f();

    public PerspectiveCamera() {
    }

    public PerspectiveCamera(float angle) {
        this.angle = angle;
    }

    public PerspectiveCamera(float angle, float near, float far) {
        super(near, far);
        this.angle = angle;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getAspect() {
        return aspect;
    }

    public void setAspect(float aspect) {
        this.aspect = aspect;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    @Override
    protected void applyTransformation(OpenGL OGL, int width, int height) {
        OGL.glFrustum(new Bounds2D(0, 0, width, height), -1, 1);
    }
}
