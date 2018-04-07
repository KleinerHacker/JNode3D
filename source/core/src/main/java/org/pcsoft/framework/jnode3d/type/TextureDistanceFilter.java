package org.pcsoft.framework.jnode3d.type;

import org.pcsoft.framework.jnode3d.internal.ogl.NativeGL;

public enum TextureDistanceFilter {
    Linear(NativeGL.GL_LINEAR),
    Nearest(NativeGL.GL_NEAREST),
    ;

    private final int value;

    TextureDistanceFilter(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
