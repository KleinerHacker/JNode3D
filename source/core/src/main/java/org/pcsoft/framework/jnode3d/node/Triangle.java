package org.pcsoft.framework.jnode3d.node;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.type.Color;

public final class Triangle extends ConstructedObject {
    private float width = 1f, height = 1f;
    private float topPercentage = 0.5f;

    public Triangle() {
        recalculate();
    }

    public Triangle(float width, float height) {
        this.width = width;
        this.height = height;

        recalculate();
    }

    public Triangle(float width, float height, float topPercentage) {
        this.width = width;
        this.height = height;
        this.topPercentage = topPercentage;

        recalculate();
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
        recalculate();
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
        recalculate();
    }

    public float getTopPercentage() {
        return topPercentage;
    }

    public void setTopPercentage(float topPercentage) {
        this.topPercentage = topPercentage;
        recalculate();
    }

    @Override
    protected void recalculate() {
        this.points = new Vector3f[]{
                new Vector3f(-width / 2f, -height / 2f, 0f),
                new Vector3f(topPercentage * width -width / 2f, height / 2f, 0f),
                new Vector3f(width / 2f, -height / 2f, 0f)
        };
        this.colors = new Color[] {
                Color.RED,
                Color.GREEN,
                Color.BLUE
        };
        this.texCoords = new Vector2f[] {
                new Vector2f(0f, 1f),
                new Vector2f(0.5f, 0f),
                new Vector2f(1f, 1f)
        };
        this.normals = new Vector3f[] { //TODO
                new Vector3f(),
                new Vector3f(),
                new Vector3f()
        };
    }
}
