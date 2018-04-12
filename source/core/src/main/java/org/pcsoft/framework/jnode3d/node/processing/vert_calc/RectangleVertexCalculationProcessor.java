package org.pcsoft.framework.jnode3d.node.processing.vert_calc;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.node.RectangleNode;
import org.pcsoft.framework.jnode3d.node.processing.VertexCalculationProcessor;
import org.pcsoft.framework.jnode3d.type.Color;

public final class RectangleVertexCalculationProcessor implements VertexCalculationProcessor<RectangleNode> {
    @Override
    public Vector3f[] recalculatePoints(RectangleNode node) {
        return new Vector3f[]{
                new Vector3f(-node.getWidth() / 2f, -node.getHeight() / 2f, 0f),
                new Vector3f(node.getWidth() / 2f, -node.getHeight() / 2f, 0f),
                new Vector3f(-node.getWidth() / 2f, node.getHeight() / 2f, 0f),
                new Vector3f(node.getWidth() / 2f, node.getHeight() / 2f, 0f),
        };

    }

    @Override
    public Vector3f[] recalculateNormals(RectangleNode node) {
        return new Vector3f[]{
                new Vector3f(0, 0, 1),
                new Vector3f(0, 0, 1),
                new Vector3f(0, 0, 1),
                new Vector3f(0, 0, 1),
        };
    }

    @Override
    public Color[] recalculateColors(RectangleNode node) {
        return new Color[]{
                node.getColorAt(RectangleNode.Points.LeftTopCorner),
                node.getColorAt(RectangleNode.Points.RightTopCorner),
                node.getColorAt(RectangleNode.Points.LeftBottomCorner),
                node.getColorAt(RectangleNode.Points.RightBottomCorner),
        };
    }

    @Override
    public Vector2f[] recalculateTextureCoordinates(RectangleNode node) {
        return new Vector2f[]{
                new Vector2f(0f, 0f),
                new Vector2f(1f, 0f),
                new Vector2f(0f, 1f),
                new Vector2f(1f, 1f),
        };
    }

    @Override
    public int[] recalculateIndices(RectangleNode node) {
        return new int[] {
                0, 1, 2,
                2, 1, 3
        };
    }

    @Override
    public int getCountOfVertices() {
        return 4;
    }

    @Override
    public int getCountOfIndices() {
        return 6;
    }
}
