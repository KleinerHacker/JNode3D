package org.pcsoft.framework.jnode3d.node;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.internal.manager.BufferManager;
import org.pcsoft.framework.jnode3d.material.EmptyMaterial;
import org.pcsoft.framework.jnode3d.material.Material;
import org.pcsoft.framework.jnode3d.type.CullMode;
import org.pcsoft.framework.jnode3d.type.PolygonMode;
import org.pcsoft.framework.jnode3d.type.Vertex;
import org.pcsoft.framework.jnode3d.type.geom.Bounds3D;

public abstract class RenderableObjectNode extends TransformableNode {
    protected Vertex[] vertices = new Vertex[0];
    protected int[] indices = new int[0];

    protected boolean depthTestActive = true;
    protected PolygonMode polygonMode = PolygonMode.Fill;
    private Material material = new EmptyMaterial();

    public RenderableObjectNode() {
        BufferManager.getInstance().registerBuffer(this);
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public int[] getIndices() {
        return indices;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        if (material == null)
            throw new IllegalArgumentException("Material cannot be set to null");

        this.material = material;
    }

    public boolean isDepthTestActive() {
        return depthTestActive;
    }

    public void setDepthTestActive(boolean depthTestActive) {
        this.depthTestActive = depthTestActive;
    }

    public CullMode getCullMode() {
        return material.getCullMode();
    }

    public void setCullMode(CullMode cullMode) {
        material.setCullMode(cullMode);
    }

    public float getOpacity() {
        return material.getOpacity();
    }

    public void setOpacity(float opacity) {
        material.setOpacity(opacity);
    }

    public PolygonMode getPolygonMode() {
        return polygonMode;
    }

    public void setPolygonMode(PolygonMode polygonMode) {
        this.polygonMode = polygonMode;
    }

    public abstract Bounds3D getLocalBounds();

    public final Bounds3D getBounds() {
        final Bounds3D localBounds = getLocalBounds();
        final Matrix4f matrix = getMatrix();

        return new Bounds3D(
                localBounds.getPositionVector().mulPosition(matrix),
                localBounds.getSizeVector().mul(matrix.getScale(new Vector3f()))
        );
    }

    @Override
    protected void _dispose() {
        super._dispose();
        BufferManager.getInstance().unregisterBuffer(this);
    }
}
