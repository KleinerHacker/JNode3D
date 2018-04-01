package org.pcsoft.framework.jnode3d.type;

import java.util.Objects;

public final class Rectangle {
    private float left, top;
    private float width, height;

    public Rectangle(float left, float top, float width, float height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getRight() {
        return left + width;
    }

    public void setRight(float right) {
        this.width = right - left;
    }

    public float getBottom() {
        return top + height;
    }

    public void setBottom(float bottom) {
        this.height = bottom - top;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Float.compare(rectangle.left, left) == 0 &&
                Float.compare(rectangle.top, top) == 0 &&
                Float.compare(rectangle.width, width) == 0 &&
                Float.compare(rectangle.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, top, width, height);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "left=" + left +
                ", top=" + top +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
