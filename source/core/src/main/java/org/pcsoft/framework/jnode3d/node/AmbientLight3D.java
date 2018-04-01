package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.internal.GL;
import org.pcsoft.framework.jnode3d.type.Color;

public final class AmbientLight3D extends Light3D {
    private float direction;

    public AmbientLight3D() {
    }

    public AmbientLight3D(Color color) {
        super(color);
    }

    public AmbientLight3D(float direction) {
        this.direction = direction;
    }

    public AmbientLight3D(Color color, float direction) {
        super(color);
        this.direction = direction;
    }

    public float getDirection() {
        return direction;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }

    @Override
    public void render(GL gl) {

    }
}
