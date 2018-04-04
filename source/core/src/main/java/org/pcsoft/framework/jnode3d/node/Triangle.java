package org.pcsoft.framework.jnode3d.node;

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

        fireValueChangedForAll();
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
        fireValueChangedForPositionAndNormals();
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
        fireValueChangedForPositionAndNormals();
    }

    public float getTopPercentage() {
        return topPercentage;
    }

    public void setTopPercentage(float topPercentage) {
        this.topPercentage = topPercentage;
        fireValueChangedForPositionAndNormals();
    }

    public enum Points {
        LeftCorner,
        RightCorner,
        Top
    }
}
