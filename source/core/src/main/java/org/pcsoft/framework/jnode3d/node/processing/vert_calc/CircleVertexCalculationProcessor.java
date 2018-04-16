package org.pcsoft.framework.jnode3d.node.processing.vert_calc;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.node.CircleNode;
import org.pcsoft.framework.jnode3d.node.processing.SimpleVertexCalculationProcessor;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.RenderMode;

import java.util.Arrays;

public final class CircleVertexCalculationProcessor extends SimpleVertexCalculationProcessor<CircleNode> {
    @Override
    protected Vector3f[] recalculatePoints(CircleNode node) {
        final int countOfVertices = getCountOfVertices(node);
        final Vector3f[] points = new Vector3f[countOfVertices];

        final float part = (float) (2d * Math.PI / node.getTiles());

        points[0] = new Vector3f(0f, 0f, 0f);
        for (int i = 0; i < node.getTiles(); i++) {
            points[i + 1] = new Vector3f(
                    (float) (Math.sin(part * i) * node.getRadius()),
                    (float) (Math.cos(part * i) * node.getRadius()),
                    0f
            );
        }

        return points;
    }

    @Override
    protected Vector3f[] recalculateNormals(CircleNode node) {
        final int countOfVertices = getCountOfVertices(node);
        final Vector3f[] normals = new Vector3f[countOfVertices];
        Arrays.fill(normals, new Vector3f(0f, 0f, -1f));

        return normals;
    }

    @Override
    protected Color[] recalculateColors(CircleNode node) {
        final int countOfVertices = getCountOfVertices(node);
        final Color[] colors = new Color[countOfVertices];
        colors[0] = node.getColorAt(CircleNode.Points.Center);
        Arrays.fill(colors, 1, colors.length, node.getColorAt(CircleNode.Points.Border));

        return colors;
    }

    @Override
    protected Vector2f[] recalculateTextureCoordinates(CircleNode node) {
        final int countOfVertices = getCountOfVertices(node);
        final Vector2f[] coordinates = new Vector2f[countOfVertices];

        final float part = (float) (2d * Math.PI / node.getTiles());

        coordinates[0] = new Vector2f(0.5f, 0.5f);
        for (int i = 0; i < node.getTiles(); i++) {
            coordinates[i + 1] = new Vector2f(
                    (float) Math.sin(part * i),
                    (float) Math.cos(part * i)
            );
        }

        return coordinates;
    }

    @Override
    protected int[] recalculateIndices(CircleNode node) {
        final int countOfIndices = getCountOfIndices(node);
        final int[] indices = new int[countOfIndices];

        indices[0] = 0;
        for (int i = 0; i < countOfIndices - 1; i++) {
            indices[i + 1] = i + 1;
        }
        indices[indices.length - 1] = 1;

        return indices;
    }

    @Override
    protected int getCountOfVertices(CircleNode node) {
        return node.getTiles() + 1;
    }

    @Override
    protected int getCountOfIndices(CircleNode node) {
        return node.getTiles() + 2;
    }

    @Override
    protected RenderMode getRenderMode() {
        return RenderMode.TriangleFan;
    }
}
