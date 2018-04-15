package org.pcsoft.framework.jnode3d.node.processing;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.node.ConstructedObjectNode;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.RenderMode;

public interface VertexCalculationProcessor<T extends ConstructedObjectNode> {
    Vector3f[] recalculatePoints(T node);

    Vector3f[] recalculateNormals(T node);

    Color[] recalculateColors(T node);

    Vector2f[] recalculateTextureCoordinates(T node);

    int[] recalculateIndices(T node);

    int getCountOfVertices(T node);

    int getCountOfIndices(T node);

    RenderMode getRenderMode();
}
