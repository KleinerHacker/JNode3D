package org.pcsoft.framework.jnode3d.node;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.pcsoft.framework.jnode3d.type.Color;

public final class Triangle3D extends ConstructedObject3D {
    private float width = 1f, height = 1f;
    private float topPercentage = 0.5f;

    public Triangle3D() {
    }

    public Triangle3D(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public Triangle3D(float width, float height, float topPercentage) {
        this.width = width;
        this.height = height;
        this.topPercentage = topPercentage;
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
        this.points = new Vector3D[]{
                new Vector3D(-width / 2f, height / 2f, 0f),
                new Vector3D(topPercentage * width, -height / 2f, 0f),
                new Vector3D(width / 2f, height / 2f, 0f)
        };
        this.colors = new Color[] {
                Color.WHITE,
                Color.WHITE,
                Color.WHITE
        };
        this.texCoords = new Vector2D[] {
                new Vector2D(0f, 1f),
                new Vector2D(0.5f, 0f),
                new Vector2D(1f, 1f)
        };
        this.normals = new Vector3D[] { //TODO
                Vector3D.ZERO,
                Vector3D.ZERO,
                Vector3D.ZERO
        };
    }
}
