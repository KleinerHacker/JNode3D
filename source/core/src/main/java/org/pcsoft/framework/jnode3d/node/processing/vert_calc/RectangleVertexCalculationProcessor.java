package org.pcsoft.framework.jnode3d.node.processing.vert_calc;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.node.Rectangle;
import org.pcsoft.framework.jnode3d.node.processing.VertexCalculationProcessor;
import org.pcsoft.framework.jnode3d.type.Color;

public final class RectangleVertexCalculationProcessor implements VertexCalculationProcessor<Rectangle> {
    @Override
    public Vector3f[] recalculatePoints(Rectangle node) {
        return new Vector3f[]{
                new Vector3f(-node.getWidth() / 2f, -node.getHeight() / 2f, 0f),
                new Vector3f(node.getWidth() / 2f, -node.getHeight() / 2f, 0f),
                new Vector3f(-node.getWidth() / 2f, node.getHeight() / 2f, 0f),
                new Vector3f(node.getWidth() / 2f, node.getHeight() / 2f, 0f),
        };

    }

    @Override
    public Vector3f[] recalculateNormals(Rectangle node) {
        return new Vector3f[]{ //TODO
                new Vector3f(),
                new Vector3f(),
                new Vector3f(),
                new Vector3f(),
        };
    }

    @Override
    public Color[] recalculateColors(Rectangle node) {
        return new Color[]{
                node.getColorAt(Rectangle.Points.LeftTopCorner),
                node.getColorAt(Rectangle.Points.RightTopCorner),
                node.getColorAt(Rectangle.Points.LeftBottomCorner),
                node.getColorAt(Rectangle.Points.RightBottomCorner),
        };
    }

    @Override
    public Vector2f[] recalculateTextureCoordinates(Rectangle node) {
        return new Vector2f[]{
                new Vector2f(0f, 0f),
                new Vector2f(1f, 0f),
                new Vector2f(0f, 1f),
                new Vector2f(1f, 1f),
        };
    }

    @Override
    public int[] recalculateIndices(Rectangle node) {
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
