package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.internal.manager.ShaderManager;
import org.pcsoft.framework.jnode3d.shader.ShaderInstance;
import org.pcsoft.framework.jnode3d.type.Vertex;
import org.pcsoft.framework.jnode3d.type.collection.ObservableCollection;

public abstract class VertexObjectNode extends TexturedNode {
    protected Vertex[] vertices = new Vertex[0];
    protected final ObservableCollection<ShaderInstance> shaderList = new ObservableCollection<>();

    public VertexObjectNode() {
        ShaderManager.getInstance().registerShaderCollection(shaderList);
    }

    @Override
    protected void finalize() {
        ShaderManager.getInstance().unregisterShaderCollection(shaderList);
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public ObservableCollection<ShaderInstance> getShaderList() {
        return shaderList;
    }
}
