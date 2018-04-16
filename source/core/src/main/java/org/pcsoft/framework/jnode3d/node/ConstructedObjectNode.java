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

    protected ConstructedObjectNode(Class<CK> colorKeyClass, Class<? extends ConstructedObjectNode<CK>> clazz) {
        super(ProcessorFactory.getVertexCalculationProcessor(clazz).getCountOfFragments());

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
        for (int i = 0; i < vertexCalculationProcessor.getCountOfFragments(); i++) {
            final Vector3f[] points = vertexCalculationProcessor.recalculatePoints(this, i);
            final Vector3f[] normals = vertexCalculationProcessor.recalculateNormals(this, i);

            final int countOfVertices = vertexCalculationProcessor.getCountOfVertices(this, i);
            for (int j = 0; j < countOfVertices; j++) {
                fragments[i].getVertices()[j].setPosition(points[j]);
                fragments[i].getVertices()[j].setNormal(normals[j]);
            }

            this.bounds = Bounds3D.fromVectors(points);

            BufferManager.getInstance().updateBuffer(this, i);
        }
    }

    @SuppressWarnings("unchecked")
    protected final void fireValueChangedForTextureCoordinates() {
        final VertexCalculationProcessor vertexCalculationProcessor = ProcessorFactory.getVertexCalculationProcessor(this.getClass());
        for (int i = 0; i < vertexCalculationProcessor.getCountOfFragments(); i++) {
            final Vector2f[] textureCoordinates = vertexCalculationProcessor.recalculateTextureCoordinates(this, i);

            final int countOfVertices = vertexCalculationProcessor.getCountOfVertices(this, i);
            for (int j = 0; j < countOfVertices; j++) {
                fragments[i].getVertices()[j].setTextureCoordinate(textureCoordinates[j]);
            }

            BufferManager.getInstance().updateBuffer(this, i);
        }
    }

    @SuppressWarnings("unchecked")
    protected final void fireValueChangedForColors() {
        final VertexCalculationProcessor vertexCalculationProcessor = ProcessorFactory.getVertexCalculationProcessor(this.getClass());
        for (int i = 0; i < vertexCalculationProcessor.getCountOfFragments(); i++) {
            final Color[] colors = vertexCalculationProcessor.recalculateColors(this, i);

            final int countOfVertices = vertexCalculationProcessor.getCountOfVertices(this, i);
            for (int j = 0; j < countOfVertices; j++) {
                fragments[i].getVertices()[j].setColor(colors[j]);
            }

            BufferManager.getInstance().updateBuffer(this, i);
        }
    }

    @SuppressWarnings("unchecked")
    protected final void fireValueChangedForAll() {
        final VertexCalculationProcessor vertexCalculationProcessor = ProcessorFactory.getVertexCalculationProcessor(this.getClass());
        for (int i = 0; i<vertexCalculationProcessor.getCountOfFragments(); i++) {
            final Vector3f[] points = vertexCalculationProcessor.recalculatePoints(this, i);
            final Vector3f[] normals = vertexCalculationProcessor.recalculateNormals(this, i);
            final Vector2f[] textureCoordinates = vertexCalculationProcessor.recalculateTextureCoordinates(this, i);
            final Color[] colors = vertexCalculationProcessor.recalculateColors(this, i);

            final int countOfVertices = vertexCalculationProcessor.getCountOfVertices(this, i);
            fragments[i].setVertices(new Vertex[countOfVertices]);
            for (int j = 0; j < countOfVertices; j++) {
                fragments[i].getVertices()[j] = new Vertex(points[j], textureCoordinates[j], colors[j], normals[j]);
            }
            
            final int[] indices = vertexCalculationProcessor.recalculateIndices(this, i);
            fragments[i].setIndices(indices);

            this.bounds = Bounds3D.fromVectors(points);

            BufferManager.getInstance().updateBuffer(this, i);
        }
    }
    //</editor-fold>
}
