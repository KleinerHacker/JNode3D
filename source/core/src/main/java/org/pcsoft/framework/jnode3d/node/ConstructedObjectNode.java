package org.pcsoft.framework.jnode3d.node;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.internal.manager.BufferManager;
import org.pcsoft.framework.jnode3d.node.processing.ProcessorFactory;
import org.pcsoft.framework.jnode3d.node.processing.VertexCalculationProcessor;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.Vertex;
import org.pcsoft.framework.jnode3d.type.geom.Bounds3D;

import java.util.HashMap;
import java.util.Map;

public abstract class ConstructedObjectNode<CK> extends RenderableObjectNode {
    private final Class<CK> colorKeyClass;
    private final Map<CK, Color> colorMap = new HashMap<>();

    private float textureWidth = 1f, textureHeight = 1f;
    private float textureTranslationX = 0f, textureTranslationY = 0f;
    private Bounds3D bounds = new Bounds3D();

    protected ConstructedObjectNode(Class<CK> colorKeyClass) {
        if (!colorKeyClass.isEnum())
            throw new IllegalArgumentException("colorKeyClass must be an enumeration");

        this.colorKeyClass = colorKeyClass;
        for (final CK key : colorKeyClass.getEnumConstants()) {
            colorMap.put(key, Color.WHITE);
        }
    }

    public void setColorAt(CK key, Color color) {
        colorMap.put(key, color);
        fireValueChangedForColors();
    }

    public Color getColorAt(CK key) {
        return colorMap.get(key);
    }

    public void setAllColors(Color color) {
        for (final CK key : colorKeyClass.getEnumConstants()) {
            colorMap.put(key, color);
        }
        fireValueChangedForColors();
    }

    public float getTextureWidth() {
        return textureWidth;
    }

    public void setTextureWidth(float textureWidth) {
        this.textureWidth = textureWidth;
        fireValueChangedForTextureCoordinates();
    }

    public float getTextureHeight() {
        return textureHeight;
    }

    public void setTextureHeight(float textureHeight) {
        this.textureHeight = textureHeight;
        fireValueChangedForTextureCoordinates();
    }

    public float getTextureTranslationX() {
        return textureTranslationX;
    }

    public void setTextureTranslationX(float textureTranslationX) {
        this.textureTranslationX = textureTranslationX;
        fireValueChangedForTextureCoordinates();
    }

    public float getTextureTranslationY() {
        return textureTranslationY;
    }

    public void setTextureTranslationY(float textureTranslationY) {
        this.textureTranslationY = textureTranslationY;
        fireValueChangedForTextureCoordinates();
    }

    @Override
    public Bounds3D getLocalBounds() {
        return bounds;
    }

    //<editor-fold desc="Value Changed">
    @SuppressWarnings("unchecked")
    protected final void fireValueChangedForPositionAndNormals() {
        final VertexCalculationProcessor vertexCalculationProcessor = ProcessorFactory.getVertexCalculationProcessor(this.getClass());
        final Vector3f[] points = vertexCalculationProcessor.recalculatePoints(this);
        final Vector3f[] normals = vertexCalculationProcessor.recalculateNormals(this);

        final int countOfVertices = vertexCalculationProcessor.getCountOfVertices(this);
        for (int i = 0; i< countOfVertices; i++) {
            vertices[i].setPosition(points[i]);
            vertices[i].setNormal(normals[i]);
        }

        recalculateBounds(points);

        BufferManager.getInstance().updateBuffer(this);
    }

    @SuppressWarnings("unchecked")
    protected final void fireValueChangedForTextureCoordinates() {
        final VertexCalculationProcessor vertexCalculationProcessor = ProcessorFactory.getVertexCalculationProcessor(this.getClass());
        final Vector2f[] textureCoordinates = vertexCalculationProcessor.recalculateTextureCoordinates(this);

        final int countOfVertices = vertexCalculationProcessor.getCountOfVertices(this);
        for (int i = 0; i< countOfVertices; i++) {
            vertices[i].setTextureCoordinate(textureCoordinates[i]);
        }

        BufferManager.getInstance().updateBuffer(this);
    }

    @SuppressWarnings("unchecked")
    protected final void fireValueChangedForColors() {
        final VertexCalculationProcessor vertexCalculationProcessor = ProcessorFactory.getVertexCalculationProcessor(this.getClass());
        final Color[] colors = vertexCalculationProcessor.recalculateColors(this);

        final int countOfVertices = vertexCalculationProcessor.getCountOfVertices(this);
        for (int i = 0; i< countOfVertices; i++) {
            vertices[i].setColor(colors[i]);
        }

        BufferManager.getInstance().updateBuffer(this);
    }

    @SuppressWarnings("unchecked")
    protected final void fireValueChangedForAll() {
        final VertexCalculationProcessor vertexCalculationProcessor = ProcessorFactory.getVertexCalculationProcessor(this.getClass());
        final Vector3f[] points = vertexCalculationProcessor.recalculatePoints(this);
        final Vector3f[] normals = vertexCalculationProcessor.recalculateNormals(this);
        final Vector2f[] textureCoordinates = vertexCalculationProcessor.recalculateTextureCoordinates(this);
        final Color[] colors = vertexCalculationProcessor.recalculateColors(this);

        final int countOfVertices = vertexCalculationProcessor.getCountOfVertices(this);
        vertices = new Vertex[countOfVertices];
        for (int i = 0; i< countOfVertices; i++) {
            vertices[i] = new Vertex(points[i], textureCoordinates[i], colors[i], normals[i]);
        }
        this.indices = vertexCalculationProcessor.recalculateIndices(this);

        recalculateBounds(points);

        BufferManager.getInstance().updateBuffer(this);
    }
    //</editor-fold>
    
    private void recalculateBounds(Vector3f[] points) {
        float minX, minY, minZ;
        minX = minY = minZ = Float.MAX_VALUE;
        float maxX, maxY, maxZ;
        maxX = maxY = maxZ = Float.MIN_VALUE;

        for (final Vector3f point : points) {
            if (minX > point.x) {
                minX = point.x;
            }
            if (minY > point.y) {
                minY = point.y;
            }
            if (minZ > point.z) {
                minZ = point.z;
            }

            if (maxX < point.x) {
                maxX = point.x;
            }
            if (maxY < point.y) {
                maxY = point.y;
            }
            if (maxZ < point.z) {
                maxZ = point.z;
            }
        }

        bounds = new Bounds3D(minX, minY, minZ, maxX, maxY, maxZ);
    }
}
