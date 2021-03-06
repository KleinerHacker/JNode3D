package org.pcsoft.framework.jnode3d.node.processing.vert_calc;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.node.TriangleNode;
import org.pcsoft.framework.jnode3d.node.processing.SimpleVertexCalculationProcessor;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.RenderMode;

public final class TriangleVertexCalculationProcessor extends SimpleVertexCalculationProcessor<TriangleNode> {
    @Override
    protected Vector3f[] recalculatePoints(TriangleNode node) {
        return new Vector3f[]{
                new Vector3f(-node.getWidth() / 2f, -node.getHeight() / 2f, 0f),
                new Vector3f(node.getTopPercentage() * node.getWidth() - node.getWidth() / 2f, node.getHeight() / 2f, 0f),
                new Vector3f(node.getWidth() / 2f, -node.getHeight() / 2f, 0f)
        };

    }

    @Override
    protected Vector3f[] recalculateNormals(TriangleNode node) {
        return new Vector3f[]{
                new Vector3f(0, 0, 1),
                new Vector3f(0, 0, 1),
                new Vector3f(0, 0, 1)
        };
    }

    @Override
    protected Color[] recalculateColors(TriangleNode node) {
        return new Color[]{
                node.getColorAt(TriangleNode.Points.LeftCorner),
                node.getColorAt(TriangleNode.Points.Top),
                node.getColorAt(TriangleNode.Points.RightCorner)
        };
    }

    @Override
    protected Vector2f[] recalculateTextureCoordinates(TriangleNode node) {
        return new Vector2f[]{
                new Vector2f(0f, 1f),
                new Vector2f(0.5f, 0f),
                new Vector2f(1f, 1f)
        };
    }

    @Override
    protected int[] recalculateIndices(TriangleNode node) {
        return new int[] {
                0, 1, 2
        };
    }

    @Override
    protected int getCountOfVertices(TriangleNode node) {
        return 3;
    }

    @Override
    protected int getCountOfIndices(TriangleNode node) {
        return 3;
    }

    @Override
    protected RenderMode getRenderMode() {
        return RenderMode.Triangles;
    }
}
