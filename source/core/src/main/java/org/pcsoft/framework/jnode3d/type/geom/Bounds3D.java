package org.pcsoft.framework.jnode3d.type.geom;

import java.util.Objects;

public class Bounds3D extends Bounds2D {
    private float front;
    private float depth;

    public Bounds3D(float left, float top, float front, float width, float height, float depth) {
        super(left, top, width, height);
        this.front = front;
        this.depth = depth;
    }

    public float getFront() {
        return front;
    }

    public void setFront(float front) {
        this.front = front;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    public float getBack() {
        return front + depth;
    }

    public void setBack(float back) {
        this.depth = back - front;
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
