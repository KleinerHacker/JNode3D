package org.pcsoft.framework.jnode3d.type.geom;

import org.joml.Vector2f;

import java.util.Objects;

public class Bounds2D {
    private final float left, top;
    private final float width, height;

    public Bounds2D() {
        this(0f, 0f, 0f, 0f);
    }

    public Bounds2D(Point2D position, Size2D size) {
        this(position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }

    public Bounds2D(Vector2f position, Vector2f size) {
        this(position.x, position.y, size.x, size.y);
    }

    public Bounds2D(float left, float top, float width, float height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    public float getLeft() {
        return left;
    }

    public float getTop() {
        return top;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRight() {
        return left + width;
    }

    public float getBottom() {
        return top + height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bounds2D bounds2D = (Bounds2D) o;
        return Float.compare(bounds2D.left, left) == 0 &&
                Float.compare(bounds2D.top, top) == 0 &&
                Float.compare(bounds2D.width, width) == 0 &&
                Float.compare(bounds2D.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, top, width, height);
    }

    @Override
    public String toString() {
        return "Bounds2D{" +
                "left=" + left +
                ", top=" + top +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
