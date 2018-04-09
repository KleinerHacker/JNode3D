package org.pcsoft.framework.jnode3d.type;

import org.joml.Vector2f;
import org.joml.Vector3f;

public final class Vertex {
    public static final int RAW_COUNT = 3 + 2 + 4 + 3;

    private Vector3f position;
    private Vector2f textureCoordinate;
    private Color color;
    private Vector3f normal;

    public Vertex() {
    }

    public Vertex(Vector3f position) {
        this(position, Color.WHITE);
    }

    public Vertex(Vector3f position, Color color) {
        this(position, new Vector2f(), color);
    }

    public Vertex(Vector3f position, Vector2f textureCoordinate) {
        this(position, textureCoordinate, Color.WHITE);
    }

    public Vertex(Vector3f position, Vector2f textureCoordinate, Color color) {
        this(position, textureCoordinate, color, new Vector3f());
    }

    public Vertex(Vector3f position, Vector2f textureCoordinate, Color color, Vector3f normal) {
        this.position = position;
        this.textureCoordinate = textureCoordinate;
        this.color = color;
        this.normal = normal;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector2f getTextureCoordinate() {
        return textureCoordinate;
    }

    public void setTextureCoordinate(Vector2f textureCoordinate) {
        this.textureCoordinate = textureCoordinate;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Vector3f getNormal() {
        return normal;
    }

    public void setNormal(Vector3f normal) {
        this.normal = normal;
    }

    public float[] getRawVertex() {
        return new float[]{
                textureCoordinate.x, textureCoordinate.y,
                color.getR(), color.getG(), color.getB(), color.getA(),
                normal.x, normal.y, normal.z,
                position.x, position.y, position.z
        };
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "position=" + position +
                ", textureCoordinate=" + textureCoordinate +
                ", color=" + color +
                ", normal=" + normal +
                '}';
    }
}
