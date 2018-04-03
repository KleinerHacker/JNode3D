package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.type.Color;

public abstract class Light extends Renderable {
    private Color color = Color.WHITE;

    public Light() {
    }

    public Light(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
