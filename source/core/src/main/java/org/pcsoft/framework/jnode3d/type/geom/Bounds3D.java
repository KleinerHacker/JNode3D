package org.pcsoft.framework.jnode3d.type.geom;

import org.joml.Vector3f;

import java.util.*;

public class Bounds3D {
    public static Bounds3D create(float left, float top, float front, float right, float bottom, float back) {
        return new Bounds3D(left, top, front, right - top, bottom - top, back - front);
    }

    public static Bounds3D create(Vector3f position, Vector3f rbPosition) {
        return create(position.x, position.y, position.z, rbPosition.x, rbPosition.y, rbPosition.z);
    }

    public static Bounds3D create(Point3D position, Point3D rbPosition) {
        return create(position.getX(), position.getY(), position.getZ(), rbPosition.getX(), rbPosition.getY(), rbPosition.getZ());
    }

    public static Bounds3D fromPoints(Collection<Point3D> points) {
        float minX, minY, minZ;
        minX = minY = minZ = Float.MAX_VALUE;
        float maxX, maxY, maxZ;
        maxX = maxY = maxZ = Float.MIN_VALUE;

        for (final Point3D point : points) {
            if (minX > point.getX()) {
                minX = point.getX();
            }
            if (minY > point.getY()) {
                minY = point.getY();
            }
            if (minZ > point.getZ()) {
                minZ = point.getZ();
            }

            if (maxX < point.getX()) {
                maxX = point.getX();
            }
            if (maxY < point.getY()) {
                maxY = point.getY();
            }
            if (maxZ < point.getZ()) {
                maxZ = point.getZ();
            }
        }

        return new Bounds3D(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public static Bounds3D fromPoints(Point3D... points) {
        return fromPoints(Arrays.asList(points));
    }

    public static Bounds3D fromVectors(Collection<Vector3f> points) {
        final List<Point3D> list = new ArrayList<>();
        for (final Vector3f point : points) {
            list.add(new Point3D(point));
        }
        return fromPoints(list);
    }

    public static Bounds3D fromVectors(Vector3f... points) {
        return fromVectors(Arrays.asList(points));
    }

    private final float left, top, front;
    private final float width, height, depth;

    public Bounds3D() {
        this(0f, 0f, 0f, 0f, 0f, 0f);
    }

    public Bounds3D(Point3D position, Size3D size) {
        this(position.getX(), position.getY(), position.getZ(), size.getWidth(), size.getHeight(), size.getDepth());
    }

    public Bounds3D(Vector3f position, Vector3f size) {
        this(position.x, position.y, position.z, size.x, size.y, size.z);
    }

    public Bounds3D(float left, float top, float front, float width, float height, float depth) {
        this.left = left;
        this.top = top;
        this.front = front;
        this.width = width;
        this.height = height;
        this.depth = depth;
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

    public float getFront() {
        return front;
    }

    public float getDepth() {
        return depth;
    }

    public float getBack() {
        return front + depth;
    }

    public Point3D getPosition() {
        return new Point3D(left, top, front);
    }

    public Vector3f getPositionVector() {
        return new Vector3f(left, top, front);
    }

    public Point3D getRBBPosition() {
        return new Point3D(getRight(), getBottom(), getBack());
    }

    public Vector3f getRBBPositionVector() {
        return new Vector3f(getRight(), getBottom(), getBack());
    }

    public Size3D getSize() {
        return new Size3D(width, height, depth);
    }

    public Vector3f getSizeVector() {
        return new Vector3f(width, height, depth);
    }

    //<editor-fold desc="Equals / Hashcode">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bounds3D bounds3D = (Bounds3D) o;
        return Float.compare(bounds3D.front, front) == 0 &&
                Float.compare(bounds3D.depth, depth) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), front, depth);
    }

    @Override
    public String toString() {
        return "Bounds3D{" +
                "front=" + front +
                ", depth=" + depth +
                "} " + super.toString();
    }
    //</editor-fold>
}
