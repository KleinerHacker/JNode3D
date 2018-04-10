package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.internal.JNode3DInternalScene;
import org.pcsoft.framework.jnode3d.internal.manager.ShaderManager;
import org.pcsoft.framework.jnode3d.shader.Shader;
import org.pcsoft.framework.jnode3d.type.Vertex;
import org.pcsoft.framework.jnode3d.type.collection.ObservableCollection;

import java.util.ArrayList;
import java.util.List;

public abstract class VertexObjectNode extends TexturedNode {
    protected Vertex[] vertices = new Vertex[0];
    protected int[] indices = new int[0];
    protected final ObservableCollection<Shader> shaderList = new ObservableCollection<>();
    protected boolean depthTestActive = true;

    private final List<ChangedListener<VertexObjectNode>> shaderListChangedListenerList = new ArrayList<>();

    public VertexObjectNode() {
        ShaderManager.getInstance().registerShaderCollection(this);
        shaderList.addChangeListener(new ObservableCollection.ChangeListener<Shader>() {
            @Override
            public void onChanged(ObservableCollection<Shader> collection) {
                fireShaderListChanged();
            }
        });
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public int[] getIndices() {
        return indices;
    }

    public Shader[] getShaders() {
        final List<Shader> list = new ArrayList<>(shaderList.toCollection());
        if (getScene() != null) {
            list.add(((JNode3DInternalScene)getScene()).getAmbientLightShader());
            list.add(((JNode3DInternalScene)getScene()).getDirectionalLightShader());
        }

        return list.toArray(new Shader[list.size()]);
    }

    public void addShader(Shader shader) {
        if (shaderList.add(shader)) {
            shader.setParent(this);
        }
    }

    public void removeShader(Shader shader) {
        if (shaderList.remove(shader)) {
            shader.setParent(null);
        }
    }

    public boolean isDepthTestActive() {
        return depthTestActive;
    }

    public void setDepthTestActive(boolean depthTestActive) {
        this.depthTestActive = depthTestActive;
    }

    public void addShaderListChangedListener(ChangedListener<VertexObjectNode> listener) {
        shaderListChangedListenerList.add(listener);
    }

    public void removeListShaderChangedListener(ChangedListener<VertexObjectNode> listener) {
        shaderListChangedListenerList.remove(listener);
    }

    protected final void fireShaderListChanged() {
        for (final ChangedListener<VertexObjectNode> listener : shaderListChangedListenerList) {
            listener.onChanged(this);
        }
    }

    @Override
    protected void _dispose() {
        super._dispose();
        ShaderManager.getInstance().unregisterShaderCollection(this);
    }
}
