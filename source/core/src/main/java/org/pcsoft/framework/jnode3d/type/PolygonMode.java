package org.pcsoft.framework.jnode3d.type;

import org.pcsoft.framework.jnode3d.ogl.NativeGL;

public enum PolygonMode {
    Point(NativeGL.GL_POINT),
    Line(NativeGL.GL_LINE),
    Fill(NativeGL.GL_FILL),
    ;

    private final int value;

    PolygonMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
