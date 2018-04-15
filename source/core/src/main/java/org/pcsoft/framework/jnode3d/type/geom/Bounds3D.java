package org.pcsoft.framework.jnode3d.type.geom;

import org.joml.Vector3f;

import java.util.Objects;

public class Bounds3D {
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
