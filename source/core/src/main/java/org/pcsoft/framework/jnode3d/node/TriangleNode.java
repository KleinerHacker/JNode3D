package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.node.processing.ProcessorFactory;
import org.pcsoft.framework.jnode3d.node.processing.vert_calc.TriangleVertexCalculationProcessor;

public final class TriangleNode extends ConstructedObjectNode<TriangleNode.Points> {
    private float width, height;
    private float topPercentage;

    static {
        ProcessorFactory.registerVertexCalculationProcessor(TriangleNode.class, new TriangleVertexCalculationProcessor());
    }

    public TriangleNode() {
        this(1f, 1f);
    }

    public TriangleNode(float width, float height) {
        this(width, height, 0.5f);
    }

    public TriangleNode(float width, float height, float topPercentage) {
        super(Points.class);
        this.width = width;
        this.height = height;
        this.topPercentage = topPercentage;

        fireValueChangedForAll();
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
        fireValueChangedForPositionAndNormals();
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
        fireValueChangedForPositionAndNormals();
    }

    public float getTopPercentage() {
        return topPercentage;
    }

    public void setTopPercentage(float topPercentage) {
        this.topPercentage = topPercentage;
        fireValueChangedForPositionAndNormals();
    }

    public enum Points {
        LeftCorner,
        RightCorner,
        Top
    }
}
