package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.shader.ShaderInstance;
import org.pcsoft.framework.jnode3d.type.Vertex;

public abstract class VertexObjectNode extends TexturedNode {
    protected Vertex[] vertices = new Vertex[0];
    protected ShaderInstance shaderInstance = null;

    public Vertex[] getVertices() {
        return vertices;
    }

    public ShaderInstance getShaderInstance() {
        return shaderInstance;
    }

    public void setShaderInstance(ShaderInstance shaderInstance) {
        this.shaderInstance = shaderInstance;
    }
}
