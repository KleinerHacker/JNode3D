package org.pcsoft.framework.jnode3d.type;

import org.pcsoft.framework.jnode3d.internal.NGL;

public enum TextureDistanceFilter {
    Linear(NGL.GL_LINEAR),
    Nearest(NGL.GL_NEAREST),
    ;

    private final int value;

    TextureDistanceFilter(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
