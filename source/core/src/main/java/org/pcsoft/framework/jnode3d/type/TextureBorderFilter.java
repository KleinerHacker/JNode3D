package org.pcsoft.framework.jnode3d.type;

import org.pcsoft.framework.jnode3d.internal.NGL;

public enum TextureBorderFilter {
    Repeated(NGL.GL_REPEAT),
    Mirrored(NGL.GL_MIRRORED_REPEAT),
    ClampToEdge(NGL.GL_CLAMP_TO_EDGE),
    ClampToBorder(NGL.GL_CLAMP_TO_BORDER),
    ;

    private final int value;

    TextureBorderFilter(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
