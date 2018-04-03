package org.pcsoft.framework.jnode3d.type;

import org.pcsoft.framework.jnode3d.internal.NGL;

public enum MatrixMode {
    ModelView(NGL.GL_MODELVIEW),
    Projection(NGL.GL_PROJECTION),
    Texture(NGL.GL_TEXTURE),
    ;

    private final int value;

    MatrixMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
