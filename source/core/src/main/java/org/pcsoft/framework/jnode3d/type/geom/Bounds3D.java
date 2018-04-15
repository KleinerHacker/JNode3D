package org.pcsoft.framework.jnode3d.type.geom;

import org.joml.Vector3f;

import java.util.Objects;

public class Bounds3D extends Bounds2D {
    private final float front;
    private final float depth;

    public Bounds3D() {
        front = 0f;
        depth = 0f;
    }

    public Bounds3D(Point3D position, Size3D size) {
        this(position.getX(), position.getY(), position.getZ(), size.getWidth(), size.getHeight(), size.getDepth());
    }

    public Bounds3D(Vector3f position, Vector3f size) {
        this(position.x, position.y, position.z, size.x, size.y, size.z);
    }

    public Bounds3D(float left, float top, float front, float width, float height, float depth) {
        super(left, top, width, height);
        this.front = front;
        this.depth = depth;
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
}