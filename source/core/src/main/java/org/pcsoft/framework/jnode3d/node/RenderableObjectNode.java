package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.internal.JNode3DInternalScene;
import org.pcsoft.framework.jnode3d.internal.manager.ShaderManager;
import org.pcsoft.framework.jnode3d.internal.shader.BaseShader;
import org.pcsoft.framework.jnode3d.shader.Shader;
import org.pcsoft.framework.jnode3d.type.CullMode;
import org.pcsoft.framework.jnode3d.type.Vertex;
import org.pcsoft.framework.jnode3d.type.collection.ObservableCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class RenderableObjectNode extends TransformableNode {
    protected Vertex[] vertices = new Vertex[0];
    protected int[] indices = new int[0];
    protected final ObservableCollection<Shader> shaderList = new ObservableCollection<>();

    protected boolean depthTestActive = true;

    private final List<ChangedListener<RenderableObjectNode>> shaderListChangedListenerList = new ArrayList<>();
    private final BaseShader baseShader = new BaseShader();

    public RenderableObjectNode() {
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

    //<editor-fold desc="Shader">
    public Shader[] getShaders() {
        final List<Shader> list = new ArrayList<>(shaderList.toCollection());
        list.add(baseShader);
        if (getScene() != null) {
            list.add(((JNode3DInternalScene)getScene()).getAmbientLightShader());
            list.add(((JNode3DInternalScene)getScene()).getDirectionalLightShader());
        }
        Collections.sort(list);

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
    //</editor-fold>

    public boolean isDepthTestActive() {
        return depthTestActive;
    }

    public void setDepthTestActive(boolean depthTestActive) {
        this.depthTestActive = depthTestActive;
    }

    public CullMode getCullMode() {
        return CullMode.fromValue(baseShader.getCullMode());
    }

    public void setCullMode(CullMode cullMode) {
        baseShader.setCullMode(cullMode.getValue());
    }

    public float getOpacity() {
        return baseShader.getOpacity();
    }

    public void setOpacity(float opacity) {
        baseShader.setOpacity(opacity);
    }

    //<editor-fold desc="Listeners">
    public void addShaderListChangedListener(ChangedListener<RenderableObjectNode> listener) {
        shaderListChangedListenerList.add(listener);
    }

    public void removeListShaderChangedListener(ChangedListener<RenderableObjectNode> listener) {
        shaderListChangedListenerList.remove(listener);
    }

    protected final void fireShaderListChanged() {
        for (final ChangedListener<RenderableObjectNode> listener : shaderListChangedListenerList) {
            listener.onChanged(this);
        }
    }
    //</editor-fold>

    @Override
    protected void _dispose() {
        super._dispose();
        ShaderManager.getInstance().unregisterShaderCollection(this);
    }
}
