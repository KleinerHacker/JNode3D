package org.pcsoft.framework.jnode3d.type;

import org.pcsoft.framework.jnode3d.internal.ogl.NativeGL;

public enum TextureBorderFilter {
    Repeated(NativeGL.GL_REPEAT),
    Mirrored(NativeGL.GL_MIRRORED_REPEAT),
    ClampToEdge(NativeGL.GL_CLAMP_TO_EDGE),
    ClampToBorder(NativeGL.GL_CLAMP_TO_BORDER),
    ;

    private final int value;

    TextureBorderFilter(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
