package org.pcsoft.framework.jnode3d.node.processing.vert_calc;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.node.RotationObjectNode;
import org.pcsoft.framework.jnode3d.node.processing.VertexCalculationProcessor;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.RenderMode;

public final class RotationObjectVertexCalculationProcessor implements VertexCalculationProcessor<RotationObjectNode> {
    private static final int FRAG_BODY = 0, FRAG_BOTTOM = 1, FRAG_TOP = 2;

    //<editor-fold desc="Points">
    @Override
    public Vector3f[] recalculatePoints(RotationObjectNode node, int fragmentIndex) {
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

    private Vector3f[] recalculatePointsForBody(RotationObjectNode node) {
        final int countOfVertices = getCountOfVertices(node, FRAG_BODY);
        final Vector3f[] vertices = new Vector3f[countOfVertices];

        final float sectorPart = (float) (2d * Math.PI / node.getSectors());
        final float ringPart = node.getHeight() / node.getRings();
        final float xPart = node.getRange().getLength() / node.getRings();

        int counter = 0;
        for (int ring = 0; ring < node.getRings() + 1; ring++) {
            final float funcX = node.getRange().getStart() + ring * xPart;
            final float funcY = node.getFunctionCallback().func(funcX);

            for (int sector = 0; sector < node.getSectors(); sector++) {
                vertices[counter++] = calculatePoint(node, funcY, ringPart * ring, sectorPart * sector);
            }
        }

        if (counter != countOfVertices)
            throw new AssertionError("Count of vertices wrong: expected is " + countOfVertices + ", found was " + counter);

        return vertices;
    }

    private Vector3f[] recalculatePointsForConclusion(RotationObjectNode node, ConclusionType type) {
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

        final float part = (float) (2d * Math.PI / node.getSectors());

        int counter = 0;

        vertices[counter++] = new Vector3f(
                0f,
                node.getHeight() / 2f * type.multiplier,
                0f
        );

        final float radius;
        switch (type) {
            case Bottom:
                radius = node.getFunctionCallback().func(node.getRange().getStart());
                break;
            case Top:
                radius = node.getFunctionCallback().func(node.getRange().getStop());
                break;
            default:
                throw new RuntimeException();
        }

        for (int i = 0; i < node.getSectors(); i++) {
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
    public Vector3f[] recalculateNormals(RotationObjectNode node, int fragmentIndex) {
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

    private Vector3f[] recalculateNormalsForBody(RotationObjectNode node) {
        final int countOfVertices = getCountOfVertices(node, FRAG_BODY);
        final Vector3f[] normals = new Vector3f[countOfVertices];

        final float sectorPart = (float) (2d * Math.PI / node.getSectors());
        final float ringPart = node.getHeight() / node.getRings();
        final float xPart = node.getRange().getLength() / node.getRings();

        int counter = 0;
        for (int ring = 0; ring < node.getRings() + 1; ring++) {
            final float funcX = node.getRange().getStart() + ring * xPart;
            final float funcY = node.getFunctionCallback().func(funcX);

            for (int sector = 0; sector < node.getSectors(); sector++) {
                final Vector3f levVec1;
                if (sector < node.getSectors() - 1) {
                    levVec1 = calculatePoint(node, funcY, ringPart * ring, sectorPart * (sector + 1)).sub(
                            calculatePoint(node, funcY, ringPart * ring, sectorPart * sector));
                } else {
                    levVec1 = calculatePoint(node, funcY, ringPart * ring, 0).sub(
                            calculatePoint(node, funcY, ringPart * ring, sectorPart * sector));
                }

                final Vector3f levVec2;
                if (ring < node.getRings() - 1) {
                    levVec2 = calculatePoint(node, funcY, ringPart * (ring + 1), sectorPart * sector).sub(
                            calculatePoint(node, funcY, ringPart * ring, sectorPart * sector));
                } else {
                    levVec2 = calculatePoint(node, funcY, ringPart * ring, sectorPart * sector).sub(
                            calculatePoint(node, funcY, ringPart * (ring - 1), sectorPart * sector));
                }

                normals[counter++] = levVec1.cross(levVec2).normalize();
            }
        }

        if (counter != countOfVertices)
            throw new AssertionError("Count of normals wrong: expected is " + countOfVertices + ", found was " + counter);

        return normals;
    }

    private Vector3f[] recalculateNormalsForConclusion(RotationObjectNode node, ConclusionType type) {
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

        for (int i = 0; i < node.getSectors(); i++) {
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
    public Color[] recalculateColors(RotationObjectNode node, int fragmentIndex) {
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

    private Color[] recalculateColorsForBody(RotationObjectNode node) {
        final int countOfVertices = getCountOfVertices(node, FRAG_BODY);
        final Color[] colors = new Color[countOfVertices];

        int counter = 0;
        for (int ring = 0; ring < node.getRings() + 1; ring++) {
            for (int sector = 0; sector < node.getSectors(); sector++) {
                colors[counter++] = node.getColorAt(RotationObjectNode.Points.Body);
            }
        }

        if (counter != countOfVertices)
            throw new AssertionError("Count of colors wrong: expected is " + countOfVertices + ", found was " + counter);

        return colors;
    }

    private Color[] recalculateColorsForConclusion(RotationObjectNode node, ConclusionType type) {
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
                colors[counter++] = node.getColorAt(RotationObjectNode.Points.BottomCircleCenter);
                break;
            case Top:
                colors[counter++] = node.getColorAt(RotationObjectNode.Points.TopCircleCenter);
                break;
            default:
                throw new RuntimeException();
        }

        for (int i = 0; i < node.getSectors(); i++) {
            colors[counter++] = node.getColorAt(RotationObjectNode.Points.Body);
        }

        if (counter != countOfVertices)
            throw new AssertionError("Count of colors wrong: expected is " + countOfVertices + ", found was " + counter);

        return colors;
    }

    //</editor-fold>
    //<editor-fold desc="Texture Coordinates">

    @Override
    public Vector2f[] recalculateTextureCoordinates(RotationObjectNode node, int fragmentIndex) {
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

    private Vector2f[] recalculateTextureCoordinatesForBody(RotationObjectNode node) {
        final int countOfVertices = getCountOfVertices(node, FRAG_BODY);
        final Vector2f[] textureCoordinates = new Vector2f[countOfVertices];

        final float ringPart = node.getHeight() / node.getRings();

        int counter = 0;
        for (int ring = 0; ring < node.getRings() + 1; ring++) {
            for (int sector = 0; sector < node.getSectors(); sector++) {
                textureCoordinates[counter++] = new Vector2f((float) sector / (float) node.getSectors(), ringPart * ring);
            }
        }

        if (counter != countOfVertices)
            throw new AssertionError("Count of texture coordinates wrong: expected is " + countOfVertices + ", found was " + counter);

        return textureCoordinates;
    }

    private Vector2f[] recalculateTextureCoordinatesForConclusion(RotationObjectNode node, ConclusionType type) {
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

        final float sectorPart = (float) (2d * Math.PI / node.getSectors());

        int counter = 0;

        textureCoordinates[counter++] = new Vector2f(0.5f, 0.5f);

        for (int i = 0; i < node.getSectors(); i++) {
            textureCoordinates[counter++] = new Vector2f((float) Math.sin(i * sectorPart), (float) Math.cos(i * sectorPart));
        }

        if (counter != countOfVertices)
            throw new AssertionError("Count of texture coordinates wrong: expected is " + countOfVertices + ", found was " + counter);

        return textureCoordinates;
    }

    //</editor-fold>
    //<editor-fold desc="Indices">

    @Override
    public int[] recalculateIndices(RotationObjectNode node, int fragmentIndex) {
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

    private int[] recalculateIndicesForBody(RotationObjectNode node) {
        final int countOfIndices = getCountOfIndices(node, FRAG_BODY);
        final int[] indices = new int[countOfIndices];

        int counter = 0;
        for (int ring = 0; ring < node.getRings(); ring++) {
            for (int sector = 0; sector < node.getSectors(); sector++) {
                if (sector == node.getSectors() - 1) {
                    indices[counter++] = sector + ring * node.getSectors();
                    indices[counter++] = sector + (ring + 1) * node.getSectors();
                    indices[counter++] = ring * node.getSectors();

                    indices[counter++] = ring * node.getSectors();
                    indices[counter++] = sector + (ring + 1) * node.getSectors();
                    indices[counter++] = (ring + 1) * node.getSectors();
                } else {
                    indices[counter++] = sector + ring * node.getSectors();
                    indices[counter++] = sector + (ring + 1) * node.getSectors();
                    indices[counter++] = sector + 1 + ring * node.getSectors();

                    indices[counter++] = sector + 1 + ring * node.getSectors();
                    indices[counter++] = sector + (ring + 1) * node.getSectors();
                    indices[counter++] = sector + 1 + (ring + 1) * node.getSectors();
                }
            }
        }

        if (counter != countOfIndices)
            throw new AssertionError("Count of indices wrong: expected is " + countOfIndices + ", found was " + counter);

        return indices;
    }

    private int[] recalculateIndicesForConclusion(RotationObjectNode node, ConclusionType type) {
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

        for (int i = 0; i < node.getSectors(); i++) {
            indices[counter++] = i + 1;
        }

        indices[counter++] = 1;

        if (counter != countOfIndices)
            throw new AssertionError("Count of indices wrong: expected is " + countOfIndices + ", found was " + counter);

        return indices;
    }

    //</editor-fold>
    @Override
    public int getCountOfVertices(RotationObjectNode node, int fragmentIndex) {
        switch (fragmentIndex) {
            case FRAG_BODY:
                return node.getSectors() * (node.getRings() + 1);
            case FRAG_BOTTOM:
                return node.isBottomClosed() ? node.getSectors() + 1 : 0;
            case FRAG_TOP:
                return node.isTopClosed() ? node.getSectors() + 1 : 0;
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public int getCountOfIndices(RotationObjectNode node, int fragmentIndex) {
        switch (fragmentIndex) {
            case FRAG_BODY:
                return node.getSectors() * node.getRings() * 2 * 3;
            case FRAG_BOTTOM:
                return node.isBottomClosed() ? node.getSectors() + 2 : 0;
            case FRAG_TOP:
                return node.isTopClosed() ? node.getSectors() + 2 : 0;
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

    private Vector3f calculatePoint(RotationObjectNode node, float funcY, float ringResult, float sectorResult) {
        return new Vector3f(
                (float) Math.sin(sectorResult) * funcY,
                -node.getHeight() / 2f + ringResult,
                (float) Math.cos(sectorResult) * funcY
        );
    }
}
