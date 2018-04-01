package org.pcsoft.framework.jnode3d.node;

public abstract class ProjectionCamera extends Camera {
    private float angle;

    public ProjectionCamera() {
    }

    public ProjectionCamera(float angle) {
        this.angle = angle;
    }

    public ProjectionCamera(float angle, float near, float far) {
        super(near, far);
        this.angle = angle;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
