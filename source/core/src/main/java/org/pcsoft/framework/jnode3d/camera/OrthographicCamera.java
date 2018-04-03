package org.pcsoft.framework.jnode3d.camera;

import org.pcsoft.framework.jnode3d.type.Rectangle;

public final class OrthographicCamera extends Camera {
    private Rectangle viewport = null;

    public OrthographicCamera() {
    }

    public OrthographicCamera(float near, float far) {
        super(near, far);
    }

    public OrthographicCamera(Rectangle viewport) {
        this.viewport = viewport;
    }

    public OrthographicCamera(Rectangle viewport, float near, float far) {
        super(near, far);
        this.viewport = viewport;
    }

    public Rectangle getViewport() {
        return viewport;
    }

    public void setViewport(Rectangle viewport) {
        this.viewport = viewport;
    }
}
