package org.pcsoft.framework.jnode3d.node.processing.vert_calc;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.node.SphereNode;
import org.pcsoft.framework.jnode3d.node.processing.SimpleVertexCalculationProcessor;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.RenderMode;

import java.util.Arrays;

public final class SphereVertexCalculationProcessor extends SimpleVertexCalculationProcessor<SphereNode> {
    @Override
    protected Vector3f[] recalculatePoints(SphereNode node) {
        final int countOfVertices = getCountOfVertices(node);
        final Vector3f[] points = new Vector3f[countOfVertices];

        final float ringPart = 1f / (float) (node.getRings() - 1);
        final float sectorPart = 1f / (float) (node.getSectors() - 1);

        int counter = 0;
        for (int ring = 0; ring < node.getRings(); ring++) {
            final double ringSin = Math.sin(Math.PI * ring * ringPart);
            final float y = (float) Math.sin(-Math.PI / 2d + Math.PI * ring * ringPart);

            for (int sector = 0; sector < node.getSectors(); sector++) {
                final float x = (float) (Math.cos(2 * Math.PI * sector * sectorPart) * ringSin);
                final float z = (float) (Math.sin(2 * Math.PI * sector * sectorPart) * ringSin);

                points[counter] = new Vector3f(x * node.getRadius(), y * node.getRadius(), z * node.getRadius());
                counter++;
            }
        }

        if (counter != countOfVertices)
            throw new AssertionError("Wrong Count: expected " + countOfVertices + ", found: " + counter);
        return points;
    }

    @Override
    protected Vector3f[] recalculateNormals(SphereNode node) {
        final int countOfVertices = getCountOfVertices(node);
        final Vector3f[] normals = new Vector3f[countOfVertices];

        final float ringPart = 1f / (float) (node.getRings() - 1);
        final float sectorPart = 1f / (float) (node.getSectors() - 1);

        int counter = 0;
        for (int ring = 0; ring < node.getRings(); ring++) {
            final double ringSin = Math.sin(Math.PI * ring * ringPart);
            final float y = (float) Math.sin(-Math.PI / 2d + Math.PI * ring * ringPart);

            for (int sector = 0; sector < node.getSectors(); sector++) {
                final float x = (float) (Math.cos(2 * Math.PI * sector * sectorPart) * ringSin);
                final float z = (float) (Math.sin(2 * Math.PI * sector * sectorPart) * ringSin);

                normals[counter] = new Vector3f(x, y, z);
                counter++;
            }
        }

        if (counter != countOfVertices)
            throw new AssertionError("Wrong Count: expected " + countOfVertices + ", found: " + counter);
        return normals;
    }

    @Override
    protected Color[] recalculateColors(SphereNode node) {
        final int countOfVertices = getCountOfVertices(node);
        final Color[] colors = new Color[countOfVertices];
        Arrays.fill(colors, node.getColorAt(SphereNode.Points.Body));

        return colors;
    }

    @Override
    protected Vector2f[] recalculateTextureCoordinates(SphereNode node) {
        final int countOfVertices = getCountOfVertices(node);
        final Vector2f[] coordinates = new Vector2f[countOfVertices];

        final float ringPart = 1f / (float) (node.getRings() - 1);
        final float sectorPart = 1f / (float) (node.getSectors() - 1);

        int counter = 0;
        for (int ring = 0; ring < node.getRings(); ring++) {
            for (int sector = 0; sector < node.getSectors(); sector++) {
                coordinates[counter] = new Vector2f(sector * sectorPart, ring * ringPart);
                counter++;
            }
        }

        if (counter != countOfVertices)
            throw new AssertionError("Wrong Count: expected " + countOfVertices + ", found: " + counter);
        return coordinates;
    }

    @Override
    protected int[] recalculateIndices(SphereNode node) {
        final int countOfIndices = getCountOfIndices(node);
        final int[] indices = new int[countOfIndices];

        int counter = 0;
        for (int ring = 0; ring < node.getRings(); ring++) {
            for (int sector = 0; sector < node.getSectors(); sector++) {
                indices[counter * 4 + 0] = ring * node.getSectors() + sector;
                indices[counter * 4 + 1] = ring * node.getSectors() + (sector + 1);
                indices[counter * 4 + 2] = (ring + 1) * node.getSectors() + (sector + 1);
                indices[counter * 4 + 3] = (ring + 1) * node.getSectors() + sector;
                counter++;
            }
        }

        if (counter * 4 != countOfIndices)
            throw new AssertionError("Wrong Count: expected " + countOfIndices + ", found: " + counter);
        return indices;
    }

    @Override
    protected int getCountOfVertices(SphereNode node) {
        return node.getRings() * node.getSectors();
    }

    @Override
    protected int getCountOfIndices(SphereNode node) {
        return node.getRings() * node.getSectors() * 4;
    }

    @Override
    protected RenderMode getRenderMode() {
        return RenderMode.Quads;
    }
}
