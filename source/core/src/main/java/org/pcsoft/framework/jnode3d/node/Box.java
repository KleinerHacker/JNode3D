package org.pcsoft.framework.jnode3d.node;

public final class Box extends ConstructedObjectNode<Box.Points> {
    private float width, height, depth;

    public Box() {
        this(1f);
    }

    public Box(float size) {
        this(size, size, size);
    }

    public Box(float width, float height, float depth) {
        super(Points.class);
        this.width = width;
        this.height = height;
        this.depth = depth;

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

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
        recalculatePointsAndNormals();
    }

    @Override
    protected void recalculatePointsAndNormals() {

    }

    @Override
    protected void recalculateColors() {

    }

    @Override
    protected void recalculateTexCoords() {

    }

    public enum Points {
        TopLeftFront,
        TopRightFront,
        TopLeftBack,
        TopRightBack,
        BottomLeftFront,
        BottomRightFront,
        BottomLeftBack,
        BottomRightBack,
    }
}
