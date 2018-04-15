package org.pcsoft.framework.jnode3d.type.geom;

import org.joml.Vector3f;

import java.util.Objects;

public class Size3D extends Size2D {
    private float depth;

    public Size3D() {
    }

    public Size3D(Vector3f size) {
        this(size.x, size.y, size.z);
    }

    public Size3D(float width, float height, float depth) {
        super(width, height);
        this.depth = depth;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Size3D size3D = (Size3D) o;
        return Float.compare(size3D.depth, depth) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), depth);
    }

    @Override
    public String toString() {
        return "Size3D{" +
                "depth=" + depth +
                "} " + super.toString();
    }
}
