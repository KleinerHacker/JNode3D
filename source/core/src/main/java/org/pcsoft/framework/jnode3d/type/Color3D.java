package org.pcsoft.framework.jnode3d.type;

import java.util.Objects;

public final class Color3D {
    public static final Color3D BLACK = new Color3D(0, 0, 0, 1);
    public static final Color3D WHITE = new Color3D(1, 1, 1, 1);

    public static final Color3D RED = new Color3D(1, 0, 0, 1);
    public static final Color3D GREEN = new Color3D(0, 1, 0, 1);
    public static final Color3D BLUE = new Color3D(0, 0, 1, 1);

    private final float r, g, b, a;

    public Color3D(Color3D color3D) {
        this(color3D.r, color3D.g, color3D.b, color3D.a);
    }

    public Color3D(Color3D color3D, float a) {
        this(color3D.r, color3D.g, color3D.b, a);
    }

    public Color3D(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }

    public float getA() {
        return a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color3D color3D = (Color3D) o;
        return Float.compare(color3D.r, r) == 0 &&
                Float.compare(color3D.g, g) == 0 &&
                Float.compare(color3D.b, b) == 0 &&
                Float.compare(color3D.a, a) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, g, b, a);
    }

    @Override
    public String toString() {
        return "Color3D{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                ", a=" + a +
                '}';
    }
}
