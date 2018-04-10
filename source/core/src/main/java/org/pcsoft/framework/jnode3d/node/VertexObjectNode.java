package org.pcsoft.framework.jnode3d.node;

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

    private final List<ChangedListener> shaderListChangedListenerList = new ArrayList<>();

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
        return shaderList.toArray(new Shader[shaderList.size()]);
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

    public void addShaderListChangedListener(ChangedListener listener) {
        shaderListChangedListenerList.add(listener);
    }

    public void removeListShaderChangedListener(ChangedListener listener) {
        shaderListChangedListenerList.remove(listener);
    }

    protected final void fireShaderListChanged() {
        for (final ChangedListener listener : shaderListChangedListenerList) {
            listener.onChanged(this);
        }
    }

    @Override
    protected void _dispose() {
        super._dispose();
        ShaderManager.getInstance().unregisterShaderCollection(this);
    }

    public interface ChangedListener {
        void onChanged(VertexObjectNode node);
    }
}
