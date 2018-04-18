package org.pcsoft.framework.jnode3d.node.processing.vert_calc;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.node.HeightMapNode;
import org.pcsoft.framework.jnode3d.node.processing.SimpleVertexCalculationProcessor;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.RenderMode;

public final class HeightMapVertexCalculationProcessor extends SimpleVertexCalculationProcessor<HeightMapNode> {
    @Override
    protected Vector3f[] recalculatePoints(HeightMapNode node) {
        final int countOfVertices = getCountOfVertices(node);
        final Vector3f[] vertices = new Vector3f[countOfVertices];

        int counter = 0;
        for (int row = 0; row < node.getHeightMap().length; row++) {
            for (int column = 0; column < node.getHeightMap()[row].length; column++) {
                vertices[counter++] = new Vector3f(column - node.getHeightMapWidth() / 2, node.getHeightMap()[row][column] - 0.5f, row - node.getHeightMapHeight() / 2);
            }
        }

        if (counter != countOfVertices)
            throw new AssertionError("Count of vertices wrong: expected is " + countOfVertices + ", found was " + counter);

        return vertices;
    }

    @Override
    protected Vector3f[] recalculateNormals(HeightMapNode node) {
        final int countOfVertices = getCountOfVertices(node);
        final Vector3f[] normals = new Vector3f[countOfVertices];

        int counter = 0;
        for (int row = 0; row < node.getHeightMap().length; row++) {
            for (int column = 0; column < node.getHeightMap()[row].length; column++) {
                normals[counter++] = new Vector3f(column - node.getHeightMapWidth() / 2, node.getHeightMap()[row][column] - 0.5f, row - node.getHeightMapHeight() / 2).normalize();
            }
        }

        if (counter != countOfVertices)
            throw new AssertionError("Count of normals wrong: expected is " + countOfVertices + ", found was " + counter);

        return normals;
    }

    @Override
    protected Color[] recalculateColors(HeightMapNode node) {
        final int countOfVertices = getCountOfVertices(node);
        final Color[] colors = new Color[countOfVertices];

        int counter = 0;
        for (int row = 0; row < node.getHeightMap().length; row++) {
            for (int column = 0; column < node.getHeightMap()[row].length; column++) {
                final float heightValue = node.getHeightMap()[row][column];

                if (heightValue < 0.1f) {
                    colors[counter++] = node.getColorAt(HeightMapNode.Points.Bottom);
                } else if (heightValue < 0.25f) {
                    colors[counter++] = node.getColorAt(HeightMapNode.Points.VeryLow);
                } else if (heightValue < 0.4f) {
                    colors[counter++] = node.getColorAt(HeightMapNode.Points.Low);
                } else if (heightValue < 0.6f) {
                    colors[counter++] = node.getColorAt(HeightMapNode.Points.Medium);
                } else if (heightValue < 0.75f) {
                    colors[counter++] = node.getColorAt(HeightMapNode.Points.Height);
                } else if (heightValue < 0.9f) {
                    colors[counter++] = node.getColorAt(HeightMapNode.Points.VeryHeight);
                } else {
                    colors[counter++] = node.getColorAt(HeightMapNode.Points.Top);
                }
            }
        }

        if (counter != countOfVertices)
            throw new AssertionError("Count of colors wrong: expected is " + countOfVertices + ", found was " + counter);

        return colors;
    }

    @Override
    protected Vector2f[] recalculateTextureCoordinates(HeightMapNode node) {
        final int countOfVertices = getCountOfVertices(node);
        final Vector2f[] textureCoordinates = new Vector2f[countOfVertices];

        int counter = 0;
        for (int row = 0; row < node.getHeightMap().length; row++) {
            for (int column = 0; column < node.getHeightMap()[row].length; column++) {
                textureCoordinates[counter++] = new Vector2f(column / node.getHeightMapWidth(), row / node.getHeightMapHeight());
            }
        }

        if (counter != countOfVertices)
            throw new AssertionError("Count of texture coordinates wrong: expected is " + countOfVertices + ", found was " + counter);

        return textureCoordinates;
    }

    @Override
    protected int[] recalculateIndices(HeightMapNode node) {
        final int countOfIndices = getCountOfIndices(node);
        final int[] indices = new int[countOfIndices];

        int counter = 0;
        for (int row = 0; row < node.getHeightMap().length - 1; row++) {
            for (int column = 0; column < node.getHeightMap()[row].length - 1; column++) {
                indices[counter++] = row * node.getHeightMapWidth() + column;
                indices[counter++] = row * node.getHeightMapWidth() + column + 1;
                indices[counter++] = (row + 1) * node.getHeightMapWidth() + column + 1;
                indices[counter++] = (row + 1) * node.getHeightMapWidth() + column;
            }
        }

        if (counter != countOfIndices)
            throw new AssertionError("Count of indices wrong: expected is " + countOfIndices + ", found was " + counter);

        return indices;
    }

    @Override
    protected int getCountOfVertices(HeightMapNode node) {
        if (node.getHeightMap().length == 0)
            return 0;

        return node.getHeightMapWidth() * node.getHeightMapHeight();
    }

    @Override
    protected int getCountOfIndices(HeightMapNode node) {
        return (node.getHeightMapWidth() - 1) * (node.getHeightMapHeight() - 1) * 4;
    }

    @Override
    protected RenderMode getRenderMode() {
        return RenderMode.Quads;
    }
}
