package org.pcsoft.framework.jnode3d.node.processing;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.node.ConstructedObjectNode;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.RenderMode;

public interface VertexCalculationProcessor<T extends ConstructedObjectNode> {
    Vector3f[] recalculatePoints(T node, int fragmentIndex);

    Vector3f[] recalculateNormals(T node, int fragmentIndex);

    Color[] recalculateColors(T node, int fragmentIndex);

    Vector2f[] recalculateTextureCoordinates(T node, int fragmentIndex);

    int[] recalculateIndices(T node, int fragmentIndex);

    int getCountOfVertices(T node, int fragmentIndex);

    int getCountOfIndices(T node, int fragmentIndex);

    RenderMode getRenderMode(int fragmentIndex);

    /**
     * Returns the count of fragments for this vertex calculation processor. This must be a very static value!
     * @return
     */
    int getCountOfFragments();
}
