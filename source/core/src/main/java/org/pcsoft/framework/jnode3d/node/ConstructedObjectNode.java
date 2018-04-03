package org.pcsoft.framework.jnode3d.node;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.ogl.DrawingCallback;
import org.pcsoft.framework.jnode3d.ogl.OGL;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.RenderMode;

import java.util.HashMap;
import java.util.Map;

public abstract class ConstructedObjectNode<T> extends VertexObjectNode {
    protected Vector3f[] points = new Vector3f[0];
    protected Vector2f[] texCoords = new Vector2f[0];
    protected Color[] colors = new Color[0];
    protected Vector3f[] normals = new Vector3f[0];

    private final Class<T> colorKeyClass;
    private final Map<T, Color> colorMap = new HashMap<>();

    private float textureWidth = 1f, textureHeight = 1f;
    private float textureTranslationX = 0f, textureTranslationY = 0f;

    protected ConstructedObjectNode(Class<T> colorKeyClass) {
        if (!colorKeyClass.isEnum())
            throw new IllegalArgumentException("colorKeyClass must be an enumeration");

        this.colorKeyClass = colorKeyClass;
        setAllColors(Color.WHITE);
    }

    public void setColorAt(T key, Color color) {
        colorMap.put(key, color);
        recalculateColors();
    }

    public Color getColorAt(T key) {
        return colorMap.get(key);
    }

    public void setAllColors(Color color) {
        for (final T key : colorKeyClass.getEnumConstants()) {
            colorMap.put(key, color);
        }
        recalculateColors();
    }

    public float getTextureWidth() {
        return textureWidth;
    }

    public void setTextureWidth(float textureWidth) {
        this.textureWidth = textureWidth;
        recalculateTexCoords();
    }

    public float getTextureHeight() {
        return textureHeight;
    }

    public void setTextureHeight(float textureHeight) {
        this.textureHeight = textureHeight;
        recalculateTexCoords();
    }

    public float getTextureTranslationX() {
        return textureTranslationX;
    }

    public void setTextureTranslationX(float textureTranslationX) {
        this.textureTranslationX = textureTranslationX;
        recalculateTexCoords();
    }

    public float getTextureTranslationY() {
        return textureTranslationY;
    }

    public void setTextureTranslationY(float textureTranslationY) {
        this.textureTranslationY = textureTranslationY;
        recalculateTexCoords();
    }

    protected abstract void recalculatePointsAndNormals();

    protected abstract void recalculateColors();

    protected abstract void recalculateTexCoords();

    protected final void recalcualteAll() {
        recalculatePointsAndNormals();
        recalculateColors();
        recalculateTexCoords();
    }

    @Override
    public final void render(final OGL ogl) {
        ogl.glDraw(RenderMode.Triangles, new DrawingCallback() {
            @Override
            public void draw() {
                for (int i = 0; i < points.length; i++) {
                    ogl.glVertex(points[i]);
                    ogl.glColor(colors[i]);
                    ogl.glTexCoord(texCoords[i]);
                    ogl.glNormal(normals[i]);
                }
            }
        });
    }
}
