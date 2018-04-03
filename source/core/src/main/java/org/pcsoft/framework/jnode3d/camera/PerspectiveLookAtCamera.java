package org.pcsoft.framework.jnode3d.camera;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.ogl.OGL;

public final class PerspectiveLookAtCamera extends PerspectiveCamera {
    private Vector3f lookAt = new Vector3f();
    private Vector3f upDirection = new Vector3f(0f, 1f, 0f);

    public Vector3f getLookAt() {
        return lookAt;
    }

    public void setLookAt(Vector3f lookAt) {
        this.lookAt = lookAt;
    }

    public void setLookAt(float x, float y, float z) {
        setLookAt(new Vector3f(x, y, z));
    }

    public Vector3f getUpDirection() {
        return upDirection;
    }

    public void setUpDirection(Vector3f upDirection) {
        this.upDirection = upDirection;
    }

    public void setUpDirection(float x, float y, float z) {
        setUpDirection(new Vector3f(x, y, z));
    }

    @Override
    protected void applyTransformation(OGL OGL, int width, int height) {
        super.applyTransformation(OGL, width, height);

        Matrix4f matrix4f = new Matrix4f();
        matrix4f.perspective((float) Math.toRadians(getAngle()), getAspect(), getNear(), getFar());
            matrix4f.lookAt(getPosition(),lookAt, upDirection);
        OGL.glLoadMatrix(matrix4f);
    }
}
