package org.pcsoft.framework.jnode3d.node.processing.vert_calc;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.node.Box;
import org.pcsoft.framework.jnode3d.node.processing.VertexCalculationProcessor;
import org.pcsoft.framework.jnode3d.type.Color;

public final class BoxVertexCalculationProcessor implements VertexCalculationProcessor<Box> {
    private static final int SIDES = 6, VERTICES_PER_SIDE = 4, INDICES_PER_SIDE = 6;

    @Override
    public Vector3f[] recalculatePoints(Box node) {
        return new Vector3f[] {
                //Top
                new Vector3f(-node.getWidth() / 2, -node.getHeight() / 2, node.getDepth() / 2),
                new Vector3f(node.getWidth() / 2, -node.getHeight() / 2, node.getDepth() / 2),
                new Vector3f(-node.getWidth() / 2, -node.getHeight() / 2, -node.getDepth() / 2),
                new Vector3f(node.getWidth() / 2, -node.getHeight() / 2, -node.getDepth() / 2),
                //Bottom
                new Vector3f(-node.getWidth() / 2, node.getHeight() / 2, node.getDepth() / 2),
                new Vector3f(node.getWidth() / 2, node.getHeight() / 2, node.getDepth() / 2),
                new Vector3f(-node.getWidth() / 2, node.getHeight() / 2, -node.getDepth() / 2),
                new Vector3f(node.getWidth() / 2, node.getHeight() / 2, -node.getDepth() / 2),
                //Left
                new Vector3f(-node.getWidth() / 2, -node.getHeight() / 2, node.getDepth() / 2),
                new Vector3f(-node.getWidth() / 2, -node.getHeight() / 2, -node.getDepth() / 2),
                new Vector3f(-node.getWidth() / 2, node.getHeight() / 2, node.getDepth() / 2),
                new Vector3f(-node.getWidth() / 2, node.getHeight() / 2, -node.getDepth() / 2),
                //Right
                new Vector3f(node.getWidth() / 2, -node.getHeight() / 2, node.getDepth() / 2),
                new Vector3f(node.getWidth() / 2, -node.getHeight() / 2, -node.getDepth() / 2),
                new Vector3f(node.getWidth() / 2, node.getHeight() / 2, node.getDepth() / 2),
                new Vector3f(node.getWidth() / 2, node.getHeight() / 2, -node.getDepth() / 2),
                //Front
                new Vector3f(-node.getWidth() / 2, -node.getHeight() / 2, -node.getDepth() / 2),
                new Vector3f(node.getWidth() / 2, -node.getHeight() / 2, -node.getDepth() / 2),
                new Vector3f(-node.getWidth() / 2, node.getHeight() / 2, -node.getDepth() / 2),
                new Vector3f(node.getWidth() / 2, node.getHeight() / 2, -node.getDepth() / 2),
                //Back
                new Vector3f(-node.getWidth() / 2, -node.getHeight() / 2, node.getDepth() / 2),
                new Vector3f(node.getWidth() / 2, -node.getHeight() / 2, node.getDepth() / 2),
                new Vector3f(-node.getWidth() / 2, node.getHeight() / 2, node.getDepth() / 2),
                new Vector3f(node.getWidth() / 2, node.getHeight() / 2, node.getDepth() / 2),
        };
    }

    @Override
    public Vector3f[] recalculateNormals(Box node) {
        return new Vector3f[] {
                //Top
                new Vector3f(0, 1, 0),
                new Vector3f(0, 1, 0),
                new Vector3f(0, 1, 0),
                new Vector3f(0, 1, 0),
                //Bottom
                new Vector3f(0, -1, 0),
                new Vector3f(0, -1, 0),
                new Vector3f(0, -1, 0),
                new Vector3f(0, -1, 0),
                //Left
                new Vector3f(-1, 0, 0),
                new Vector3f(-1, 0, 0),
                new Vector3f(-1, 0, 0),
                new Vector3f(-1, 0, 0),
                //Right
                new Vector3f(1, 0, 0),
                new Vector3f(1, 0, 0),
                new Vector3f(1, 0, 0),
                new Vector3f(1, 0, 0),
                //Front
                new Vector3f(0, 0, -1),
                new Vector3f(0, 0, -1),
                new Vector3f(0, 0, -1),
                new Vector3f(0, 0, -1),
                //Back
                new Vector3f(0, 0, 1),
                new Vector3f(0, 0, 1),
                new Vector3f(0, 0, 1),
                new Vector3f(0, 0, 1),
        };
    }

    @Override
    public Color[] recalculateColors(Box node) {
        return new Color[] {
                //Top
                node.getColorAt(Box.Points.TopLeftBack),
                node.getColorAt(Box.Points.TopRightBack),
                node.getColorAt(Box.Points.TopLeftFront),
                node.getColorAt(Box.Points.TopRightFront),
                //Bottom
                node.getColorAt(Box.Points.BottomLeftBack),
                node.getColorAt(Box.Points.BottomRightBack),
                node.getColorAt(Box.Points.BottomLeftFront),
                node.getColorAt(Box.Points.BottomRightFront),
                //Left
                node.getColorAt(Box.Points.TopLeftBack),
                node.getColorAt(Box.Points.TopLeftFront),
                node.getColorAt(Box.Points.BottomLeftBack),
                node.getColorAt(Box.Points.BottomLeftFront),
                //Right
                node.getColorAt(Box.Points.TopRightBack),
                node.getColorAt(Box.Points.TopRightFront),
                node.getColorAt(Box.Points.BottomRightBack),
                node.getColorAt(Box.Points.BottomRightFront),
                //Front
                node.getColorAt(Box.Points.TopLeftFront),
                node.getColorAt(Box.Points.TopRightFront),
                node.getColorAt(Box.Points.BottomLeftFront),
                node.getColorAt(Box.Points.BottomRightFront),
                //Back
                node.getColorAt(Box.Points.TopLeftBack),
                node.getColorAt(Box.Points.TopRightBack),
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
                //Left
                new Vector2f(0, 0),
                new Vector2f(1, 0),
                new Vector2f(0, 1),
                new Vector2f(1, 1),
                //Right
                new Vector2f(1, 1),
                new Vector2f(0, 1),
                new Vector2f(1, 0),
                new Vector2f(0, 0),
                //Front
                new Vector2f(0, 0),
                new Vector2f(1, 0),
                new Vector2f(0, 1),
                new Vector2f(1, 1),
                //Back
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
                8, 9, 10,
                10, 9, 11,
                //Right
                12, 13, 14,
                14, 13, 15,
                //Front
                16, 17, 18,
                18, 17, 19,
                //Back
                20, 21, 22,
                22, 21, 23
        };
    }

    @Override
    public int getCountOfVertices() {
        return SIDES * VERTICES_PER_SIDE;
    }

    @Override
    public int getCountOfIndices() {
        return SIDES * INDICES_PER_SIDE;
    }
}
