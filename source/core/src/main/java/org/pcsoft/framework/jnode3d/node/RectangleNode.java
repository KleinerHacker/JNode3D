package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.node.processing.ProcessorFactory;
import org.pcsoft.framework.jnode3d.node.processing.vert_calc.RectangleVertexCalculationProcessor;

public final class RectangleNode extends ConstructedObjectNode<RectangleNode.Points> {
    private float width, height;

    static {
        ProcessorFactory.registerVertexCalculationProcessor(RectangleNode.class, new RectangleVertexCalculationProcessor());
    }

    public RectangleNode() {
        this(1f, 1f);
    }

    public RectangleNode(float width, float height) {
        super(Points.class);
        this.width = width;
        this.height = height;

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

    public enum Points {
        LeftTopCorner,
        LeftBottomCorner,
        RightTopCorner,
        RightBottomCorner,
    }
}
