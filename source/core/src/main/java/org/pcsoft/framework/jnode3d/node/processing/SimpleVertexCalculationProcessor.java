package org.pcsoft.framework.jnode3d.node.processing;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.node.ConstructedObjectNode;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.RenderMode;

public abstract class SimpleVertexCalculationProcessor<T extends ConstructedObjectNode> implements VertexCalculationProcessor<T> {
    @Override
    public final Vector3f[] recalculatePoints(T node, int fragmentIndex) {
        return recalculatePoints(node);
    }

    @Override
    public final Vector3f[] recalculateNormals(T node, int fragmentIndex) {
        return recalculateNormals(node);
    }

    @Override
    public final Color[] recalculateColors(T node, int fragmentIndex) {
        return recalculateColors(node);
    }

    @Override
    public final Vector2f[] recalculateTextureCoordinates(T node, int fragmentIndex) {
        return recalculateTextureCoordinates(node);
    }

    @Override
    public final int[] recalculateIndices(T node, int fragmentIndex) {
        return recalculateIndices(node);
    }

    @Override
    public final int getCountOfVertices(T node, int fragmentIndex) {
        return getCountOfVertices(node);
    }

    @Override
    public final int getCountOfIndices(T node, int fragmentIndex) {
        return getCountOfIndices(node);
    }

    @Override
    public final RenderMode getRenderMode(int fragmentIndex) {
        return getRenderMode();
    }

    @Override
    public final int getCountOfFragments() {
        return 1;
    }

    protected abstract Vector3f[] recalculatePoints(T node);

    protected abstract Vector3f[] recalculateNormals(T node);

    protected abstract Color[] recalculateColors(T node);

    protected abstract Vector2f[] recalculateTextureCoordinates(T node);

    protected abstract int[] recalculateIndices(T node);

    protected abstract int getCountOfVertices(T node);

    protected abstract int getCountOfIndices(T node);

    protected abstract RenderMode getRenderMode();
}
