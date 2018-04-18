package org.pcsoft.framework.jnode3d.type;

import java.util.Objects;

public final class Color {
    public static final Color BLACK = new Color(0, 0, 0, 1);
    public static final Color WHITE = new Color(1, 1, 1, 1);

    public static final Color RED = new Color(1, 0, 0, 1);
    public static final Color GREEN = new Color(0, 1, 0, 1);
    public static final Color BLUE = new Color(0, 0, 1, 1);
    public static final Color YELLOW = new Color(1, 1, 0, 1);
    public static final Color MAGENTA = new Color(1, 0, 1, 1);
    public static final Color TURKEY = new Color(0, 1, 1, 1);

    private final float r, g, b, a;

    public Color(Color color) {
        this(color.r, color.g, color.b, color.a);
    }

    public Color(Color color, float a) {
        this(color.r, color.g, color.b, a);
    }

    public Color(float r, float g, float b) {
        this(r, g, b, 1f);
    }

    public Color(float r, float g, float b, float a) {
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
        Color color = (Color) o;
        return Float.compare(color.r, r) == 0 &&
                Float.compare(color.g, g) == 0 &&
                Float.compare(color.b, b) == 0 &&
                Float.compare(color.a, a) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, g, b, a);
    }

    @Override
    public String toString() {
        return "Color{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                ", a=" + a +
                '}';
    }
}
