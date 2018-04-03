package org.pcsoft.framework.jnode3d.camera;

import org.apache.commons.lang.ObjectUtils;
import org.pcsoft.framework.jnode3d.internal.GL;
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

    @Override
    protected void applyTransformation(GL gl, int width, int height) {
        gl.glOrtho((Rectangle) ObjectUtils.defaultIfNull(viewport, new Rectangle(0, 0, width, height)),
                getNear(), getFar());
    }
}
