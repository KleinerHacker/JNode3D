package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.node.processing.ProcessorFactory;
import org.pcsoft.framework.jnode3d.node.processing.vert_calc.CylinderVertexCalculationProcessor;

public final class CylinderNode extends ConstructedObjectNode<CylinderNode.Points> {
    private float topRadius = 0.5f, bottomRadius = 0.5f, height = 1f;
    private int tiles = 64;
    private boolean topClosed = true, bottomClosed = true;

    static {
        ProcessorFactory.registerVertexCalculationProcessor(CylinderNode.class, new CylinderVertexCalculationProcessor());
    }

    public CylinderNode() {
        this(0.5f, 1f);
    }

    public CylinderNode(float radius, float height) {
        this(radius, height, 64);
    }

    public CylinderNode(float topRadius, float bottomRadius, float height) {
        this(topRadius, bottomRadius, height, 64);
    }

    public CylinderNode(float radius, float height, int tiles) {
        this(radius, radius, height, tiles);
    }

    public CylinderNode(float topRadius, float bottomRadius, float height, int tiles) {
        super(Points.class, CylinderNode.class);
        this.topRadius = topRadius;
        this.bottomRadius = bottomRadius;
        this.height = height;
        this.tiles = tiles;

        fireValueChangedForAll();
    }

    public float getTopRadius() {
        return topRadius;
    }

    public void setTopRadius(float topRadius) {
        this.topRadius = topRadius;
        fireValueChangedForPositionAndNormals();
    }

    public float getBottomRadius() {
        return bottomRadius;
    }

    public void setBottomRadius(float bottomRadius) {
        this.bottomRadius = bottomRadius;
        fireValueChangedForPositionAndNormals();
    }

    public void setRadius(float radius) {
        this.topRadius = radius;
        this.bottomRadius = radius;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
        fireValueChangedForPositionAndNormals();
    }

    public int getTiles() {
        return tiles;
    }

    public void setTiles(int tiles) {
        this.tiles = tiles;
        fireValueChangedForAll();
    }

    public boolean isTopClosed() {
        return topClosed;
    }

    public void setTopClosed(boolean topClosed) {
        this.topClosed = topClosed;
        fireValueChangedForAll();
    }

    public boolean isBottomClosed() {
        return bottomClosed;
    }

    public void setBottomClosed(boolean bottomClosed) {
        this.bottomClosed = bottomClosed;
        fireValueChangedForAll();
    }

    public enum Points {
        Top,
        TopCircleCenter,
        Bottom,
        BottomCircleCenter
    }
}
