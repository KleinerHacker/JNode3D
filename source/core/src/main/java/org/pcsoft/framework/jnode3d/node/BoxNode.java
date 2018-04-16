package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.node.processing.ProcessorFactory;
import org.pcsoft.framework.jnode3d.node.processing.vert_calc.BoxVertexCalculationProcessor;

public final class BoxNode extends ConstructedObjectNode<BoxNode.Points> {
    private float width, height, depth;

    static {
        ProcessorFactory.registerVertexCalculationProcessor(BoxNode.class, new BoxVertexCalculationProcessor());
    }

    public BoxNode() {
        this(1f);
    }

    public BoxNode(float size) {
        this(size, size, size);
    }

    public BoxNode(float width, float height, float depth) {
        super(Points.class, BoxNode.class);
        this.width = width;
        this.height = height;
        this.depth = depth;

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

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
        fireValueChangedForPositionAndNormals();
    }

    public enum Points {
        TopLeftFront,
        TopRightFront,
        TopLeftBack,
        TopRightBack,
        BottomLeftFront,
        BottomRightFront,
        BottomLeftBack,
        BottomRightBack,
    }
}
