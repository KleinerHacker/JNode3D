package org.pcsoft.framework.jnode3d.type;

import org.pcsoft.framework.jnode3d.ogl.NativeGL;

public enum ShaderType {
    VertexShader(NativeGL.GL_VERTEX_SHADER),
    FragmentShader(NativeGL.GL_FRAGMENT_SHADER),
    ;

    private final int value;

    ShaderType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
