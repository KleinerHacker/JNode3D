package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.type.Color;

public final class AmbientLight extends LightNode {
    private float direction;

    public AmbientLight() {
    }

    public AmbientLight(Color color) {
        super(color);
    }

    public AmbientLight(float direction) {
        this.direction = direction;
    }

    public AmbientLight(Color color, float direction) {
        super(color);
        this.direction = direction;
    }

    public float getDirection() {
        return direction;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }
}
