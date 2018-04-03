package org.pcsoft.framework.jnode3d.node;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.internal.GL;
import org.pcsoft.framework.jnode3d.type.Color;

import java.util.HashMap;
import java.util.Map;

public abstract class ConstructedObjectNode<T> extends VertexObjectNode {
    protected Vector3f[] points = new Vector3f[0];
    protected Vector2f[] texCoords = new Vector2f[0];
    protected Color[] colors = new Color[0];
    protected Vector3f[] normals = new Vector3f[0];

    private final Class<T> colorKeyClass;
    private final Map<T, Color> colorMap = new HashMap<>();

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

    protected abstract void recalculatePointsAndNormals();
    protected abstract void recalculateColors();
    protected abstract void recalculateTexCoords();
    protected final void recalcualteAll() {
        recalculatePointsAndNormals();
        recalculateColors();
        recalculateTexCoords();
    }

    @Override
    public final void render(GL gl) {
        gl.glBegin(GL.GL_TRIANGLES);
        {
            for (int i = 0; i < points.length; i++) {
                gl.glVertex(points[i]);
                gl.glColor(colors[i]);
                gl.glTexCoord(texCoords[i]);
                gl.glNormal(normals[i]);
            }
        }
        gl.glEnd();
    }
}
