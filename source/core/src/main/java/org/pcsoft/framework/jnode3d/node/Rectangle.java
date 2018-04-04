package org.pcsoft.framework.jnode3d.node;

public final class Rectangle extends ConstructedObjectNode<Rectangle.Points> {
    private float width, height;

    public Rectangle() {
        this(1f, 1f);
    }

    public Rectangle(float width, float height) {
        super(Points.class);
        this.width = width;
        this.height = height;

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

    public enum Points {
        LeftTopCorner,
        LeftBottomCorner,
        RightTopCorner,
        RightBottomCorner,
    }
}
