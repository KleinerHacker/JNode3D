package org.pcsoft.framework.jnode3d.type.geom;

import org.joml.Vector3f;

import java.util.Objects;

public class Point3D {
    private final float x, y, z;

    public Point3D() {
        this(0f, 0f, 0f);
    }

    public Point3D(Vector3f point) {
        this(point.x, point.y, point.z);
    }

    public Point3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public Vector3f getPoint() {
        return new Vector3f(x, y, z);
    }

    //<editor-fold desc="Equals / Hashcode">
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
    //</editor-fold>
}
