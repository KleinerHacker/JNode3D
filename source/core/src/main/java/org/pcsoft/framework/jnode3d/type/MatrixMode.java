package org.pcsoft.framework.jnode3d.type;

import org.pcsoft.framework.jnode3d.ogl.NativeGL;

public enum MatrixMode {
    ModelView(NativeGL.GL_MODELVIEW),
    Projection(NativeGL.GL_PROJECTION),
    Texture(NativeGL.GL_TEXTURE),
    ;

    private final int value;

    MatrixMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
