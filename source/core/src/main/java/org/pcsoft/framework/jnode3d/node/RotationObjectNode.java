package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.node.processing.ProcessorFactory;
import org.pcsoft.framework.jnode3d.node.processing.vert_calc.RotationObjectVertexCalculationProcessor;

public final class RotationObjectNode extends ConstructedObjectNode<RotationObjectNode.Points> {
    private FunctionCallback functionCallback = new FunctionCallback() {
        @Override
        public float func(float x) {
            return 0.5f;
        }
    };
    private FunctionRange range = new FunctionRange(-1f, 1f);
    private int sectors = 64, rings = 64;
    private float height = 1f;
    private boolean topClosed = true, bottomClosed = true;

    static {
        ProcessorFactory.registerVertexCalculationProcessor(RotationObjectNode.class, new RotationObjectVertexCalculationProcessor());
    }

    public RotationObjectNode() {
        this(new FunctionCallback() {
            @Override
            public float func(float x) {
                return 0.5f;
            }
        }, new FunctionRange(-1f, 1f));
    }

    public RotationObjectNode(FunctionCallback functionCallback, FunctionRange range) {
        this(functionCallback, range, 1f);
    }

    public RotationObjectNode(FunctionCallback functionCallback, FunctionRange range, float height) {
        this(functionCallback, range, height, 64);
    }

    public RotationObjectNode(FunctionCallback functionCallback, FunctionRange range, float height, int tiles) {
        this(functionCallback, range, height, tiles, tiles);
    }

    public RotationObjectNode(FunctionCallback functionCallback, FunctionRange range, float height, int rings, int sectors) {
        super(Points.class, RotationObjectNode.class);
        this.functionCallback = functionCallback;
        this.range = range;
        this.height = height;
        this.rings = rings;
        this.sectors = sectors;

        fireValueChangedForAll();
    }

    public FunctionCallback getFunctionCallback() {
        return functionCallback;
    }

    public void setFunctionCallback(FunctionCallback functionCallback) {
        this.functionCallback = functionCallback;
        fireValueChangedForPositionAndNormals();
    }

    public FunctionRange getRange() {
        return range;
    }

    public void setRange(FunctionRange range) {
        this.range = range;
        fireValueChangedForPositionAndNormals();
    }

    public int getSectors() {
        return sectors;
    }

    public void setSectors(int sectors) {
        this.sectors = sectors;
        fireValueChangedForAll();
    }

    public int getRings() {
        return rings;
    }

    public void setRings(int rings) {
        this.rings = rings;
        fireValueChangedForAll();
    }

    public void setTiles(int tiles) {
        this.rings = tiles;
        this.sectors = tiles;
        fireValueChangedForAll();
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
        fireValueChangedForPositionAndNormals();
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
        Body,
        TopCircleCenter,
        BottomCircleCenter
    }

    public interface FunctionCallback {
        float func(float x);
    }

    public static final class FunctionRange {
        private final float start, stop;

        public FunctionRange(float start, float stop) {
            this.start = start;
            this.stop = stop;
        }

        public float getStart() {
            return start;
        }

        public float getStop() {
            return stop;
        }

        public float getLength() {
            return stop - start;
        }
    }
}
