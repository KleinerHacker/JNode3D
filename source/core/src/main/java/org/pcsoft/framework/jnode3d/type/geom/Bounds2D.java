package org.pcsoft.framework.jnode3d.type.geom;

import org.joml.Vector2f;

import java.util.*;

public class Bounds2D {
    public static Bounds2D create(float left, float top, float right, float bottom) {
        return new Bounds2D(left, top, right - top, bottom - top);
    }

    public static Bounds2D create(Vector2f position, Vector2f rbPosition) {
        return create(position.x, position.y, rbPosition.x, rbPosition.y);
    }

    public static Bounds2D create(Point2D position, Point2D rbPosition) {
        return create(position.getX(), position.getY(), rbPosition.getX(), rbPosition.getY());
    }

    public static Bounds2D fromPoints(Collection<Point2D> points) {
        float minX, minY;
        minX = minY = Float.MAX_VALUE;
        float maxX, maxY;
        maxX = maxY = Float.MIN_VALUE;

        for (final Point2D point : points) {
            if (minX > point.getX()) {
                minX = point.getX();
            }
            if (minY > point.getY()) {
                minY = point.getY();
            }

            if (maxX < point.getX()) {
                maxX = point.getX();
            }
            if (maxY < point.getY()) {
                maxY = point.getY();
            }
        }

        return new Bounds2D(minX, minY, maxX, maxY);
    }

    public static Bounds2D fromPoints(Point2D... points) {
        return fromPoints(Arrays.asList(points));
    }

    public static Bounds2D fromVectors(Collection<Vector2f> points) {
        final List<Point2D> list = new ArrayList<>();
        for (final Vector2f point : points) {
            list.add(new Point2D(point));
        }
        return fromPoints(list);
    }

    public static Bounds2D fromVectors(Vector2f... points) {
        return fromVectors(Arrays.asList(points));
    }

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

    public Point2D getPosition() {
        return new Point2D(left, top);
    }

    public Point2D getRBPosition() {
        return new Point2D(getRight(), getBottom());
    }

    public Size2D getSize() {
        return new Size2D(width, height);
    }

    public Vector2f getPositionVector() {
        return new Vector2f(left, top);
    }

    public Vector2f getRBPositionVector() {
        return new Vector2f(left, top);
    }

    public Vector2f getSizeVector() {
        return new Vector2f(width, height);
    }

    //<editor-fold desc="Equals / Hashcode">
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
    //</editor-fold>
}
