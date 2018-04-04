package org.pcsoft.framework.jnode3d.node.processing;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.node.ConstructedObjectNode;
import org.pcsoft.framework.jnode3d.type.Color;

public interface VertexCalculationProcessor<T extends ConstructedObjectNode> {
    Vector3f[] recalculatePoints(T node);

    Vector3f[] recalculateNormals(T node);

    Color[] recalculateColors(T node);

    Vector2f[] recalculateTextureCoordinates(T node);

    int getCountOfVertices();
}
