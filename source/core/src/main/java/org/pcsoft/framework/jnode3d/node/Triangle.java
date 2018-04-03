package org.pcsoft.framework.jnode3d.node;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.type.Color;

public final class Triangle extends ConstructedObjectNode<Triangle.Points> {
    private float width, height;
    private float topPercentage;

    public Triangle() {
        this(1f, 1f);
    }

    public Triangle(float width, float height) {
        this(width, height, 0.5f);
    }

    public Triangle(float width, float height, float topPercentage) {
        super(Points.class);
        this.width = width;
        this.height = height;
        this.topPercentage = topPercentage;

        recalcualteAll();
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
        recalculatePointsAndNormals();
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
        recalculatePointsAndNormals();
    }

    public float getTopPercentage() {
        return topPercentage;
    }

    public void setTopPercentage(float topPercentage) {
        this.topPercentage = topPercentage;
        recalculatePointsAndNormals();
    }

    @Override
    protected void recalculatePointsAndNormals() {
        this.points = new Vector3f[]{
                new Vector3f(-width / 2f, -height / 2f, 0f),
                new Vector3f(topPercentage * width -width / 2f, height / 2f, 0f),
                new Vector3f(width / 2f, -height / 2f, 0f)
        };
        this.normals = new Vector3f[] { //TODO
                new Vector3f(),
                new Vector3f(),
                new Vector3f()
        };
    }

    @Override
    protected void recalculateColors() {
        this.colors = new Color[] {
                getColorAt(Points.LeftCorner),
                getColorAt(Points.Top),
                getColorAt(Points.RightCorner)
        };
    }

    @Override
    protected void recalculateTexCoords() {
        this.texCoords = new Vector2f[] {
                new Vector2f(0f, 1f),
                new Vector2f(0.5f, 0f),
                new Vector2f(1f, 1f)
        };
    }

    public enum Points {
        LeftCorner,
        RightCorner,
        Top
    }
}
