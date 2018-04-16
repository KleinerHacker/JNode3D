package org.pcsoft.framework.jnode3d.node.processing.vert_calc;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.node.BoxNode;
import org.pcsoft.framework.jnode3d.node.processing.SimpleVertexCalculationProcessor;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.RenderMode;

public final class BoxVertexCalculationProcessor extends SimpleVertexCalculationProcessor<BoxNode> {
    private static final int SIDES = 6, VERTICES_PER_SIDE = 4, INDICES_PER_SIDE = 6;

    @Override
    protected Vector3f[] recalculatePoints(BoxNode node) {
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
    protected Vector3f[] recalculateNormals(BoxNode node) {
        return new Vector3f[] {
                //Top
                new Vector3f(0, -1, 0),
                new Vector3f(0, -1, 0),
                new Vector3f(0, -1, 0),
                new Vector3f(0, -1, 0),
                //Bottom
                new Vector3f(0, 1, 0),
                new Vector3f(0, 1, 0),
                new Vector3f(0, 1, 0),
                new Vector3f(0, 1, 0),
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
    protected Color[] recalculateColors(BoxNode node) {
        return new Color[] {
                //Top
                node.getColorAt(BoxNode.Points.TopLeftBack),
                node.getColorAt(BoxNode.Points.TopRightBack),
                node.getColorAt(BoxNode.Points.TopLeftFront),
                node.getColorAt(BoxNode.Points.TopRightFront),
                //Bottom
                node.getColorAt(BoxNode.Points.BottomLeftBack),
                node.getColorAt(BoxNode.Points.BottomRightBack),
                node.getColorAt(BoxNode.Points.BottomLeftFront),
                node.getColorAt(BoxNode.Points.BottomRightFront),
                //Left
                node.getColorAt(BoxNode.Points.TopLeftBack),
                node.getColorAt(BoxNode.Points.TopLeftFront),
                node.getColorAt(BoxNode.Points.BottomLeftBack),
                node.getColorAt(BoxNode.Points.BottomLeftFront),
                //Right
                node.getColorAt(BoxNode.Points.TopRightBack),
                node.getColorAt(BoxNode.Points.TopRightFront),
                node.getColorAt(BoxNode.Points.BottomRightBack),
                node.getColorAt(BoxNode.Points.BottomRightFront),
                //Front
                node.getColorAt(BoxNode.Points.TopLeftFront),
                node.getColorAt(BoxNode.Points.TopRightFront),
                node.getColorAt(BoxNode.Points.BottomLeftFront),
                node.getColorAt(BoxNode.Points.BottomRightFront),
                //Back
                node.getColorAt(BoxNode.Points.TopLeftBack),
                node.getColorAt(BoxNode.Points.TopRightBack),
                node.getColorAt(BoxNode.Points.BottomLeftBack),
                node.getColorAt(BoxNode.Points.BottomRightBack),
        };
    }

    @Override
    protected Vector2f[] recalculateTextureCoordinates(BoxNode node) {
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
    protected int[] recalculateIndices(BoxNode node) {
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
    protected int getCountOfVertices(BoxNode node) {
        return SIDES * VERTICES_PER_SIDE;
    }

    @Override
    protected int getCountOfIndices(BoxNode node) {
        return SIDES * INDICES_PER_SIDE;
    }

    @Override
    protected RenderMode getRenderMode() {
        return RenderMode.Triangles;
    }
}
