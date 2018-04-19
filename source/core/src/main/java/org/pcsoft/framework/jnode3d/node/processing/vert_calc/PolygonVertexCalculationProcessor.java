package org.pcsoft.framework.jnode3d.node.processing.vert_calc;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.node.PolygonNode;
import org.pcsoft.framework.jnode3d.node.processing.SimpleVertexCalculationProcessor;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.RenderMode;
import org.pcsoft.framework.jnode3d.type.geom.Bounds3D;
import org.pcsoft.framework.jnode3d.type.geom.Point3D;

import java.util.Arrays;

public final class PolygonVertexCalculationProcessor extends SimpleVertexCalculationProcessor<PolygonNode> {
    @Override
    protected Vector3f[] recalculatePoints(PolygonNode node) {
        final int countOfVertices = getCountOfVertices(node);
        final Vector3f[] vertices = new Vector3f[countOfVertices];

        for (int i = 0; i < countOfVertices; i++) {
            vertices[i] = node.getPointList().get(i).getPoint();
        }

        return vertices;
    }

    @Override
    protected Vector3f[] recalculateNormals(PolygonNode node) {
        final int countOfVertices = getCountOfVertices(node);
        final Vector3f[] normals = new Vector3f[countOfVertices];

        for (int i = 0; i < countOfVertices; i++) {
            final Vector3f levVec1 = i > 0 ? node.getPointList().get(i - 1).getPoint() :
                    node.getPointList().get(node.getPointList().size() - 1).getPoint();
            final Vector3f levVec2 = i < node.getPointList().size() - 1 ? node.getPointList().get(i + 1).getPoint() :
                    node.getPointList().get(0).getPoint();

            normals[i] = levVec1.cross(levVec2);
        }

        return normals;
    }

    @Override
    protected Color[] recalculateColors(PolygonNode node) {
        final int countOfVertices = getCountOfVertices(node);
        final Color[] colors = new Color[countOfVertices];
        Arrays.fill(colors, node.getColorAt(PolygonNode.Points.Body));

        return colors;
    }

    @Override
    protected Vector2f[] recalculateTextureCoordinates(PolygonNode node) {
        final int countOfVertices = getCountOfVertices(node);
        final Vector2f[] textureCoordinates = new Vector2f[countOfVertices];
        final Bounds3D bounds = Bounds3D.fromPoints(node.getPointList().toCollection());

        for (int i = 0; i < countOfVertices; i++) {
            final Point3D point = node.getPointList().get(i);

            final float tx = calculateTextureCoordinateFromBounds(point, bounds, node.getUseAxisforTextureCoordinateX());
            final float ty = calculateTextureCoordinateFromBounds(point, bounds, node.getUseAxisforTextureCoordinateY());

            textureCoordinates[i] = new Vector2f(tx, ty);
        }

        return textureCoordinates;
    }

    private float calculateTextureCoordinateFromBounds(Point3D point, Bounds3D bounds, PolygonNode.Axis axis) {
        switch (axis) {
            case X:
                return (point.getX() - bounds.getLeft()) / bounds.getWidth();
            case Y:
                return (point.getY() - bounds.getTop()) / bounds.getHeight();
            case Z:
                return (point.getZ() - bounds.getFront()) / bounds.getDepth();
            default:
                throw new RuntimeException();
        }
    }

    @Override
    protected int[] recalculateIndices(PolygonNode node) {
        final int countOfIndices = getCountOfIndices(node);
        final int[] indices = new int[countOfIndices];

        for (int i = 0; i < countOfIndices; i++) {
            indices[i] = i;
        }

        return indices;
    }

    @Override
    protected int getCountOfVertices(PolygonNode node) {
        return node.getPointList().size();
    }

    @Override
    protected int getCountOfIndices(PolygonNode node) {
        return node.getPointList().size();
    }

    @Override
    protected RenderMode getRenderMode() {
        return RenderMode.Polygon;
    }
}
