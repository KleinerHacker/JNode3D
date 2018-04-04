package org.pcsoft.framework.jnode3d.node.processing.vert_calc;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.node.Triangle;
import org.pcsoft.framework.jnode3d.node.processing.VertexCalculationProcessor;
import org.pcsoft.framework.jnode3d.type.Color;

public final class TriangleVertexCalculationProcessor implements VertexCalculationProcessor<Triangle> {
    @Override
    public Vector3f[] recalculatePoints(Triangle node) {
        return new Vector3f[]{
                new Vector3f(-node.getWidth() / 2f, -node.getHeight() / 2f, 0f),
                new Vector3f(node.getTopPercentage() * node.getWidth() - node.getWidth() / 2f, node.getHeight() / 2f, 0f),
                new Vector3f(node.getWidth() / 2f, -node.getHeight() / 2f, 0f)
        };

    }

    @Override
    public Vector3f[] recalculateNormals(Triangle node) {
        return new Vector3f[]{ //TODO
                new Vector3f(),
                new Vector3f(),
                new Vector3f()
        };
    }

    @Override
    public Color[] recalculateColors(Triangle node) {
        return new Color[]{
                node.getColorAt(Triangle.Points.LeftCorner),
                node.getColorAt(Triangle.Points.Top),
                node.getColorAt(Triangle.Points.RightCorner)
        };
    }

    @Override
    public Vector2f[] recalculateTextureCoordinates(Triangle node) {
        return new Vector2f[]{
                new Vector2f(0f, 1f),
                new Vector2f(0.5f, 0f),
                new Vector2f(1f, 1f)
        };
    }

    @Override
    public int getCountOfVertices() {
        return 3;
    }
}
