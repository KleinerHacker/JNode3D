package org.pcsoft.framework.jnode3d.camera;

import org.apache.commons.lang.ObjectUtils;
import org.pcsoft.framework.jnode3d.internal.ogl.OpenGL;
import org.pcsoft.framework.jnode3d.type.Bounds2D;

public final class OrthographicCamera extends Camera {
    private Bounds2D viewport = null;

    public OrthographicCamera() {
    }

    public OrthographicCamera(float near, float far) {
        super(near, far);
    }

    public OrthographicCamera(Bounds2D viewport) {
        this.viewport = viewport;
    }

    public OrthographicCamera(Bounds2D viewport, float near, float far) {
        super(near, far);
        this.viewport = viewport;
    }

    public Bounds2D getViewport() {
        return viewport;
    }

    public void setViewport(Bounds2D viewport) {
        this.viewport = viewport;
    }

    @Override
    protected void applyTransformation(OpenGL OGL, int width, int height) {
        OGL.glOrtho((Bounds2D) ObjectUtils.defaultIfNull(viewport, new Bounds2D(0, 0, width, height)),
                getNear(), getFar());
    }
}
