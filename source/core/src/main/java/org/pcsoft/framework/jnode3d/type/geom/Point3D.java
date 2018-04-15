package org.pcsoft.framework.jnode3d.type.geom;

import org.joml.Vector3f;

import java.util.Objects;

public class Point3D extends Point2D {
    private final float z;

    public Point3D() {
        z = 0f;
    }

    public Point3D(Vector3f point) {
        this(point.x, point.y, point.z);
    }

    public Point3D(float x, float y, float z) {
        super(x, y);
        this.z = z;
    }

    public float getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Point3D point3D = (Point3D) o;
        return Float.compare(point3D.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), z);
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "z=" + z +
                "} " + super.toString();
    }
}
