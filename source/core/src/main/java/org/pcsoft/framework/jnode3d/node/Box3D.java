package org.pcsoft.framework.jnode3d.node;

public final class Box3D extends ConstructedObject3D {
    private float width, height, depth;

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
        recalculate();
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
        recalculate();
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
        recalculate();
    }

    @Override
    protected void recalculate() {

    }
}
