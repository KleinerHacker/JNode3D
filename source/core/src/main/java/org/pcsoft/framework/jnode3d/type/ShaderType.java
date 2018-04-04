package org.pcsoft.framework.jnode3d.type;

import org.pcsoft.framework.jnode3d.internal.NGL;

public enum ShaderType {
    VertexShader(NGL.GL_VERTEX_SHADER),
    FragmentShader(NGL.GL_FRAGMENT_SHADER),
    ;

    private final int value;

    ShaderType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
