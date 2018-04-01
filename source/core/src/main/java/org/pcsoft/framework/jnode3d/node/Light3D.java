package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.type.Color;

public abstract class Light3D extends Renderable3D {
    private Color color = Color.WHITE;

    public Light3D() {
    }

    public Light3D(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
