package org.pcsoft.framework.jnode3d.node.processing.vert_calc;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.node.CylinderNode;
import org.pcsoft.framework.jnode3d.node.processing.VertexCalculationProcessor;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.RenderMode;

public final class CylinderVertexCalculationProcessor implements VertexCalculationProcessor<CylinderNode> {
    private static final int FRAG_BODY = 0, FRAG_BOTTOM = 1, FRAG_TOP = 2;

    //<editor-fold desc="Points">
    @Override
    public Vector3f[] recalculatePoints(CylinderNode node, int fragmentIndex) {
        switch (fragmentIndex) {
            case FRAG_BODY:
                return recalculatePointsForBody(node);
            case FRAG_BOTTOM:
            case FRAG_TOP:
                return recalculatePointsForConclusion(node, ConclusionType.fromFragmentIndex(fragmentIndex));
            default:
                throw new RuntimeException();
        }
    }

    private Vector3f[] recalculatePointsForBody(CylinderNode node) {
        final int countOfVertices = getCountOfVertices(node, FRAG_BODY);
        final Vector3f[] vertices = new Vector3f[countOfVertices];

        final float part = (float) (2d * Math.PI / node.getTiles());

        int counter = 0;
        for (int i = 0; i < node.getTiles(); i++) {
            vertices[counter++] = new Vector3f(
                    (float) Math.sin(part * i) * node.getTopRadius(),
                    node.getHeight() / 2f,
                    (float) Math.cos(part * i) * node.getTopRadius()
            );

            vertices[counter++] = new Vector3f(
                    (float) Math.sin(part * i) * node.getBottomRadius(),
                    -node.getHeight() / 2f,
                    (float) Math.cos(part * i) * node.getBottomRadius()
            );
        }

        if (counter != countOfVertices)
            throw new AssertionError("Count of vertices wrong: expected is " + countOfVertices + ", found was " + counter);

        return vertices;
    }

    private Vector3f[] recalculatePointsForConclusion(CylinderNode node, ConclusionType type) {
        switch (type) {
            case Bottom:
                if (!node.isBottomClosed())
                    return new Vector3f[0];
                break;
            case Top:
                if (!node.isTopClosed())
                    return new Vector3f[0];
                break;
            default:
                throw new RuntimeException();
        }

        final int countOfVertices = getCountOfVertices(node, type.fragmentIndex);
        final Vector3f[] vertices = new Vector3f[countOfVertices];

        final float part = (float) (2d * Math.PI / node.getTiles());

        int counter = 0;

        vertices[counter++] = new Vector3f(
                0f,
                node.getHeight() / 2f * type.multiplier,
                0f
        );

        final float radius;
        switch (type) {
            case Bottom:
                radius = node.getBottomRadius();
                break;
            case Top:
                radius = node.getTopRadius();
                break;
            default:
                throw new RuntimeException();
        }

        for (int i = 0; i < node.getTiles(); i++) {
            vertices[counter++] = new Vector3f(
                    (float) Math.sin(part * i) * radius,
                    node.getHeight() / 2f * type.multiplier,
                    (float) Math.cos(part * i) * radius
            );
        }

        if (counter != countOfVertices)
            throw new AssertionError("Count of vertices wrong: expected is " + countOfVertices + ", found was " + counter);

        return vertices;
    }
    //</editor-fold>

    //<editor-fold desc="Normals">
    @Override
    public Vector3f[] recalculateNormals(CylinderNode node, int fragmentIndex) {
        switch (fragmentIndex) {
            case FRAG_BODY:
                return recalculateNormalsForBody(node);
            case FRAG_BOTTOM:
            case FRAG_TOP:
                return recalculateNormalsForConclusion(node, ConclusionType.fromFragmentIndex(fragmentIndex));
            default:
                throw new RuntimeException();
        }
    }

    private Vector3f[] recalculateNormalsForBody(CylinderNode node) {
        final int countOfVertices = getCountOfVertices(node, FRAG_BODY);
        final Vector3f[] normals = new Vector3f[countOfVertices];

        final float part = (float) (2d * Math.PI / node.getTiles());

        int counter = 0;
        for (int i = 0; i < node.getTiles(); i++) {
            normals[counter++] = new Vector3f(
                    (float) Math.sin(part * i) * node.getBottomRadius(),
                    0f, //TODO: Optimize for radius difference
                    (float) Math.cos(part * i) * node.getBottomRadius()
            ).normalize();

            normals[counter++] = new Vector3f(
                    (float) Math.sin(part * i) * node.getTopRadius(),
                    0f, //TODO: Optimize for radius difference
                    (float) Math.cos(part * i) * node.getTopRadius()
            ).normalize();
        }

        if (counter != countOfVertices)
            throw new AssertionError("Count of normals wrong: expected is " + countOfVertices + ", found was " + counter);

        return normals;
    }

    private Vector3f[] recalculateNormalsForConclusion(CylinderNode node, ConclusionType type) {
        switch (type) {
            case Bottom:
                if (!node.isBottomClosed())
                    return new Vector3f[0];
                break;
            case Top:
                if (!node.isTopClosed())
                    return new Vector3f[0];
                break;
            default:
                throw new RuntimeException();
        }

        final int countOfVertices = getCountOfVertices(node, type.fragmentIndex);
        final Vector3f[] normals = new Vector3f[countOfVertices];

        int counter = 0;

        normals[counter++] = new Vector3f(
                0f,
                type.multiplier,
                0f
        ).normalize();

        for (int i = 0; i < node.getTiles(); i++) {
            normals[counter++] = new Vector3f(
                    0f,
                    type.multiplier,
                    0f
            ).normalize();
        }

        if (counter != countOfVertices)
            throw new AssertionError("Count of normals wrong: expected is " + countOfVertices + ", found was " + counter);

        return normals;
    }
    //</editor-fold>

    //<editor-fold desc="Colors">
    @Override
    public Color[] recalculateColors(CylinderNode node, int fragmentIndex) {
        switch (fragmentIndex) {
            case FRAG_BODY:
                return recalculateColorsForBody(node);
            case FRAG_BOTTOM:
            case FRAG_TOP:
                return recalculateColorsForConclusion(node, ConclusionType.fromFragmentIndex(fragmentIndex));
            default:
                throw new RuntimeException();
        }
    }

    private Color[] recalculateColorsForBody(CylinderNode node) {
        final int countOfVertices = getCountOfVertices(node, FRAG_BODY);
        final Color[] colors = new Color[countOfVertices];

        int counter = 0;
        for (int i = 0; i < node.getTiles(); i++) {
            colors[counter++] = node.getColorAt(CylinderNode.Points.Top);
            colors[counter++] = node.getColorAt(CylinderNode.Points.Bottom);
        }

        if (counter != countOfVertices)
            throw new AssertionError("Count of colors wrong: expected is " + countOfVertices + ", found was " + counter);

        return colors;
    }

    private Color[] recalculateColorsForConclusion(CylinderNode node, ConclusionType type) {
        switch (type) {
            case Bottom:
                if (!node.isBottomClosed())
                    return new Color[0];
                break;
            case Top:
                if (!node.isTopClosed())
                    return new Color[0];
                break;
            default:
                throw new RuntimeException();
        }

        final int countOfVertices = getCountOfVertices(node, type.fragmentIndex);
        final Color[] colors = new Color[countOfVertices];

        int counter = 0;

        switch (type) {
            case Bottom:
                colors[counter++] = node.getColorAt(CylinderNode.Points.BottomCircleCenter);
                break;
            case Top:
                colors[counter++] = node.getColorAt(CylinderNode.Points.TopCircleCenter);
                break;
            default:
                throw new RuntimeException();
        }

        for (int i = 0; i < node.getTiles(); i++) {
            switch (type) {
                case Bottom:
                    colors[counter++] = node.getColorAt(CylinderNode.Points.Bottom);
                    break;
                case Top:
                    colors[counter++] = node.getColorAt(CylinderNode.Points.Top);
                    break;
                default:
                    throw new RuntimeException();
            }
        }

        if (counter != countOfVertices)
            throw new AssertionError("Count of colors wrong: expected is " + countOfVertices + ", found was " + counter);

        return colors;
    }
    //</editor-fold>

    //<editor-fold desc="Texture Coordinates">
    @Override
    public Vector2f[] recalculateTextureCoordinates(CylinderNode node, int fragmentIndex) {
        switch (fragmentIndex) {
            case FRAG_BODY:
                return recalculateTextureCoordinatesForBody(node);
            case FRAG_BOTTOM:
            case FRAG_TOP:
                return recalculateTextureCoordinatesForConclusion(node, ConclusionType.fromFragmentIndex(fragmentIndex));
            default:
                throw new RuntimeException();
        }
    }

    private Vector2f[] recalculateTextureCoordinatesForBody(CylinderNode node) {
        final int countOfVertices = getCountOfVertices(node, FRAG_BODY);
        final Vector2f[] textureCoordinates = new Vector2f[countOfVertices];

        int counter = 0;
        for (int i = 0; i < node.getTiles(); i++) {
            textureCoordinates[counter++] = new Vector2f((float) i / (float) node.getTiles(), 1f);
            textureCoordinates[counter++] = new Vector2f((float) i / (float) node.getTiles(), 0f);
        }

        if (counter != countOfVertices)
            throw new AssertionError("Count of texture coordinates wrong: expected is " + countOfVertices + ", found was " + counter);

        return textureCoordinates;
    }

    private Vector2f[] recalculateTextureCoordinatesForConclusion(CylinderNode node, ConclusionType type) {
        switch (type) {
            case Bottom:
                if (!node.isBottomClosed())
                    return new Vector2f[0];
                break;
            case Top:
                if (!node.isTopClosed())
                    return new Vector2f[0];
                break;
            default:
                throw new RuntimeException();
        }

        final int countOfVertices = getCountOfVertices(node, type.fragmentIndex);
        final Vector2f[] textureCoordinates = new Vector2f[countOfVertices];

        final float part = (float) (2d * Math.PI / node.getTiles());

        int counter = 0;

        textureCoordinates[counter++] = new Vector2f(0.5f, 0.5f);

        for (int i = 0; i < node.getTiles(); i++) {
            textureCoordinates[counter++] = new Vector2f((float) Math.sin(i * part), (float) Math.cos(i * part));
        }

        if (counter != countOfVertices)
            throw new AssertionError("Count of texture coordinates wrong: expected is " + countOfVertices + ", found was " + counter);

        return textureCoordinates;
    }
    //</editor-fold>

    //<editor-fold desc="Indices">
    @Override
    public int[] recalculateIndices(CylinderNode node, int fragmentIndex) {
        switch (fragmentIndex) {
            case FRAG_BODY:
                return recalculateIndicesForBody(node);
            case FRAG_BOTTOM:
            case FRAG_TOP:
                return recalculateIndicesForConclusion(node, ConclusionType.fromFragmentIndex(fragmentIndex));
            default:
                throw new RuntimeException();
        }
    }

    private int[] recalculateIndicesForBody(CylinderNode node) {
        final int countOfIndices = getCountOfIndices(node, FRAG_BODY);
        final int[] indices = new int[countOfIndices];

        int counter = 0;
        for (int i = 0; i < node.getTiles(); i++) {
            if (i == node.getTiles() - 1) {
                indices[counter++] = i * 2;
                indices[counter++] = i * 2 + 1;
                indices[counter++] = 0;

                indices[counter++] = 0;
                indices[counter++] = i * 2 + 1;
                indices[counter++] = 1;
            } else {
                indices[counter++] = i * 2;
                indices[counter++] = i * 2 + 1;
                indices[counter++] = i * 2 + 2;

                indices[counter++] = i * 2 + 2;
                indices[counter++] = i * 2 + 1;
                indices[counter++] = i * 2 + 3;
            }
        }

        if (counter != countOfIndices)
            throw new AssertionError("Count of indices wrong: expected is " + countOfIndices + ", found was " + counter);

        return indices;
    }

    private int[] recalculateIndicesForConclusion(CylinderNode node, ConclusionType type) {
        switch (type) {
            case Bottom:
                if (!node.isBottomClosed())
                    return new int[0];
                break;
            case Top:
                if (!node.isTopClosed())
                    return new int[0];
                break;
            default:
                throw new RuntimeException();
        }

        final int countOfIndices = getCountOfIndices(node, type.fragmentIndex);
        final int[] indices = new int[countOfIndices];

        int counter = 0;

        indices[counter++] = 0;

        for (int i = 0; i < node.getTiles(); i++) {
            indices[counter++] = i + 1;
        }

        indices[counter++] = 1;

        if (counter != countOfIndices)
            throw new AssertionError("Count of indices wrong: expected is " + countOfIndices + ", found was " + counter);

        return indices;
    }
    //</editor-fold>

    @Override
    public int getCountOfVertices(CylinderNode node, int fragmentIndex) {
        switch (fragmentIndex) {
            case FRAG_BODY:
                return node.getTiles() * 2;
            case FRAG_BOTTOM:
                return node.isBottomClosed() ? node.getTiles() + 1 : 0;
            case FRAG_TOP:
                return node.isTopClosed() ? node.getTiles() + 1 : 0;
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public int getCountOfIndices(CylinderNode node, int fragmentIndex) {
        switch (fragmentIndex) {
            case FRAG_BODY:
                return node.getTiles() * 2 * 3;
            case FRAG_BOTTOM:
                return node.isBottomClosed() ? node.getTiles() + 2 : 0;
            case FRAG_TOP:
                return node.isTopClosed() ? node.getTiles() + 2 : 0;
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public RenderMode getRenderMode(int fragmentIndex) {
        switch (fragmentIndex) {
            case FRAG_BODY:
                return RenderMode.Triangles;
            case FRAG_BOTTOM:
            case FRAG_TOP:
                return RenderMode.TriangleFan;
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public int getCountOfFragments() {
        return 3;
    }

    private enum ConclusionType {
        Top(FRAG_TOP, 1),
        Bottom(FRAG_BOTTOM, -1);

        public static ConclusionType fromFragmentIndex(int index) {
            for (final ConclusionType type : values()) {
                if (type.fragmentIndex == index)
                    return type;
            }

            throw new IllegalArgumentException("Unknown index: " + index);
        }

        private final int fragmentIndex;
        private final int multiplier;

        ConclusionType(int fragmentIndex, int multiplier) {
            this.fragmentIndex = fragmentIndex;
            this.multiplier = multiplier;
        }
    }
}
