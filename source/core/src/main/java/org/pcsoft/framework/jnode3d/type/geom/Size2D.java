package org.pcsoft.framework.jnode3d.type.geom;

import org.joml.Vector2f;

import java.util.Objects;

public class Size2D {
    private float width;
    private float height;

    public Size2D() {
    }

    public Size2D(Vector2f size) {
        this(size.x, size.y);
    }

    public Size2D(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size2D size2D = (Size2D) o;
        return Float.compare(size2D.width, width) == 0 &&
                Float.compare(size2D.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    @Override
    public String toString() {
        return "Size2D{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
