package org.pcsoft.framework.jnode3d.node.processing.vert_calc;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.node.Box;
import org.pcsoft.framework.jnode3d.node.processing.VertexCalculationProcessor;
import org.pcsoft.framework.jnode3d.type.Color;

public final class BoxVertexCalculationProcessor implements VertexCalculationProcessor<Box> {
    @Override
    public Vector3f[] recalculatePoints(Box node) {
        return new Vector3f[] {
                //Top
                new Vector3f(-node.getWidth() / 2, -node.getHeight() / 2, -node.getDepth() / 2),
                new Vector3f(node.getWidth() / 2, -node.getHeight() / 2, -node.getDepth() / 2),
                new Vector3f(-node.getWidth() / 2, -node.getHeight() / 2, node.getDepth() / 2),
                new Vector3f(node.getWidth() / 2, -node.getHeight() / 2, node.getDepth() / 2),
                //Bottom
                new Vector3f(-node.getWidth() / 2, node.getHeight() / 2, -node.getDepth() / 2),
                new Vector3f(node.getWidth() / 2, node.getHeight() / 2, -node.getDepth() / 2),
                new Vector3f(-node.getWidth() / 2, node.getHeight() / 2, node.getDepth() / 2),
                new Vector3f(node.getWidth() / 2, node.getHeight() / 2, node.getDepth() / 2),
        };
    }

    @Override
    public Vector3f[] recalculateNormals(Box node) {
        return new Vector3f[] {
                //Top
                new Vector3f(),
                new Vector3f(),
                new Vector3f(),
                new Vector3f(),
                //Bottom
                new Vector3f(),
                new Vector3f(),
                new Vector3f(),
                new Vector3f(),
        };
    }

    @Override
    public Color[] recalculateColors(Box node) {
        return new Color[] {
                //Top
                node.getColorAt(Box.Points.TopLeftFront),
                node.getColorAt(Box.Points.TopRightFront),
                node.getColorAt(Box.Points.TopLeftBack),
                node.getColorAt(Box.Points.TopRightBack),
                //Bottom
                node.getColorAt(Box.Points.BottomLeftFront),
                node.getColorAt(Box.Points.BottomRightFront),
                node.getColorAt(Box.Points.BottomLeftBack),
                node.getColorAt(Box.Points.BottomRightBack),
        };
    }

    @Override
    public Vector2f[] recalculateTextureCoordinates(Box node) {
        return new Vector2f[] {
                //Top
                new Vector2f(0, 0),
                new Vector2f(1, 0),
                new Vector2f(0, 1),
                new Vector2f(1, 1),
                //Bottom
                new Vector2f(1, 1),
                new Vector2f(0, 1),
                new Vector2f(1, 0),
                new Vector2f(0, 0),
        };
    }

    @Override
    public int[] recalculateIndices(Box node) {
        return new int[] {
                //Top
                0, 1, 2,
                2, 1, 3,
                //Bottom
                4, 5, 6,
                6, 5, 7,
                //Left
                0, 2, 4,
                4, 2, 6,
                //Right
                1, 3, 5,
                5, 3, 7,
                //Front
                2, 3, 6,
                6, 3, 7,
                //Back
                0, 1, 4,
                4, 1, 5
        };
    }

    @Override
    public int getCountOfVertices() {
        return 8;
    }

    @Override
    public int getCountOfIndices() {
        return 36;
    }
}
