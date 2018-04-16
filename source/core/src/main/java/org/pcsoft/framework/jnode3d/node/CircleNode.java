package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.node.processing.ProcessorFactory;
import org.pcsoft.framework.jnode3d.node.processing.vert_calc.CircleVertexCalculationProcessor;

public final class CircleNode extends ConstructedObjectNode<CircleNode.Points> {
    private float radius;
    private int tiles;

    static {
        ProcessorFactory.registerVertexCalculationProcessor(CircleNode.class, new CircleVertexCalculationProcessor());
    }

    public CircleNode() {
        this(0.5f);
    }

    public CircleNode(float radius) {
        this(radius, 64);
    }

    public CircleNode(float radius, int tiles) {
        super(Points.class, CircleNode.class);
        this.radius = radius;
        this.tiles = tiles;

        fireValueChangedForAll();
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        fireValueChangedForPositionAndNormals();
    }

    public int getTiles() {
        return tiles;
    }

    public void setTiles(int tiles) {
        this.tiles = tiles;
        fireValueChangedForAll();
    }

    public enum Points {
        Center,
        Border
    }
}
