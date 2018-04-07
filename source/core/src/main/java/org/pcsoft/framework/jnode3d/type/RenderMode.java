package org.pcsoft.framework.jnode3d.type;

import org.pcsoft.framework.jnode3d.internal.ogl.NativeGL;

public enum RenderMode {
    Points(NativeGL.GL_POINTS),
    Lines(NativeGL.GL_LINES),
    LineLoop(NativeGL.GL_LINE_LOOP),
    LineStrip(NativeGL.GL_LINE_STRIP),
    Triangles(NativeGL.GL_TRIANGLES),
    TriangleStrip(NativeGL.GL_TRIANGLE_STRIP),
    TriangleFan(NativeGL.GL_TRIANGLE_FAN),
    Quads(NativeGL.GL_QUADS),
    QuadStrip(NativeGL.GL_QUAD_STRIP),
    Polygon(NativeGL.GL_POLYGON),;

    private final int value;

    RenderMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}