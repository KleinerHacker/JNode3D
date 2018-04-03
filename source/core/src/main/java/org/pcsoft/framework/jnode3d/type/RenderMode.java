package org.pcsoft.framework.jnode3d.type;

import org.pcsoft.framework.jnode3d.internal.NGL;

public enum RenderMode {
    Points(NGL.GL_POINTS),
    Lines(NGL.GL_LINES),
    LineLoop(NGL.GL_LINE_LOOP),
    LineStrip(NGL.GL_LINE_STRIP),
    Triangles(NGL.GL_TRIANGLES),
    TriangleStrip(NGL.GL_TRIANGLE_STRIP),
    TriangleFan(NGL.GL_TRIANGLE_FAN),
    Quads(NGL.GL_QUADS),
    QuadStrip(NGL.GL_QUAD_STRIP),
    Polygon(NGL.GL_POLYGON),;

    private final int value;

    RenderMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}