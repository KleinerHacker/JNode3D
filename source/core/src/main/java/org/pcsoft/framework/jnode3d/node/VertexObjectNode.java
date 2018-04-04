package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.type.Vertex;

public abstract class VertexObjectNode extends TexturedNode {
    protected Vertex[] vertices = new Vertex[0];

    public Vertex[] getVertices() {
        return vertices;
    }
}
