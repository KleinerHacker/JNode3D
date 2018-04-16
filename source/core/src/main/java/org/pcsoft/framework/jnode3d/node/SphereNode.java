package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.node.processing.ProcessorFactory;
import org.pcsoft.framework.jnode3d.node.processing.vert_calc.SphereVertexCalculationProcessor;

public final class SphereNode extends ConstructedObjectNode<SphereNode.Points> {
    private float radius;
    private int sectors, rings;

    static {
        ProcessorFactory.registerVertexCalculationProcessor(SphereNode.class, new SphereVertexCalculationProcessor());
    }

    public SphereNode() {
        this(0.5f);
    }

    public SphereNode(float radius) {
        this(radius, 64);
    }

    public SphereNode(float radius, int tiles) {
        this(radius, tiles, tiles);
    }

    public SphereNode(float radius, int sectors, int rings) {
        super(Points.class, SphereNode.class);
        this.radius = radius;
        this.sectors = sectors;
        this.rings = rings;

        fireValueChangedForAll();
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
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
        this.sectors = tiles;
        this.rings = tiles;
        fireValueChangedForAll();
    }

    public enum Points {
        Body
    }
}
