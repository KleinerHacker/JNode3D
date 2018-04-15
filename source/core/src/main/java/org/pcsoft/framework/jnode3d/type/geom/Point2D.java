package org.pcsoft.framework.jnode3d.type.geom;

import org.joml.Vector2f;

import java.util.Objects;

public class Point2D {
    private final float x, y;

    public Point2D() {
        this(0f, 0f);
    }

    public Point2D(Vector2f point) {
        this(point.x, point.y);
    }

    public Point2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Vector2f getPoint() {
        return new Vector2f(x, y);
    }

    //<editor-fold desc="Equals / Hashcode">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2D point2D = (Point2D) o;
        return Float.compare(point2D.x, x) == 0 &&
                Float.compare(point2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    //</editor-fold>
}
