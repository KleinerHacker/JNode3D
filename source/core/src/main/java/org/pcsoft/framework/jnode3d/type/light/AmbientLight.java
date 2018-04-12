package org.pcsoft.framework.jnode3d.type.light;

import org.pcsoft.framework.jnode3d.type.Color;

public final class AmbientLight extends Light {
    public AmbientLight() {
    }

    public AmbientLight(Color color) {
        super(color);
    }

    public AmbientLight(Color color, float power) {
        super(color, power);
    }
}
