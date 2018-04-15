package org.pcsoft.framework.jnode3d.type.geom;

import org.joml.Vector2f;

import java.util.Objects;

public class Size2D {
    private final float width;
    private final float height;

    public Size2D() {
        this(0f, 0f);
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

    public float getHeight() {
        return height;
    }

    public Vector2f getSize() {
        return new Vector2f(width, height);
    }

    //<editor-fold desc="Equals / Hashcode">
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
    //</editor-fold>
}
