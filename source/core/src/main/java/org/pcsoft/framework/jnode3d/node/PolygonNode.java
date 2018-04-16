package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.node.processing.ProcessorFactory;
import org.pcsoft.framework.jnode3d.node.processing.vert_calc.PolygonVertexCalculationProcessor;
import org.pcsoft.framework.jnode3d.type.collection.ObservableCollection;
import org.pcsoft.framework.jnode3d.type.geom.Point3D;

public final class PolygonNode extends ConstructedObjectNode<PolygonNode.Points> {
    private final ObservableCollection<Point3D> pointList = new ObservableCollection<>();
    private Axis useAxisforTextureCoordinateX = Axis.X;
    private Axis useAxisforTextureCoordinateY = Axis.Y;

    static {
        ProcessorFactory.registerVertexCalculationProcessor(PolygonNode.class, new PolygonVertexCalculationProcessor());
    }

    public PolygonNode(Point3D... points) {
        super(Points.class, PolygonNode.class);

        pointList.addAll(points);
        pointList.addChangeListener(new ObservableCollection.ChangeListener<Point3D>() {
            @Override
            public void onChanged(ObservableCollection<Point3D> collection) {
                fireValueChangedForAll();
            }
        });

        fireValueChangedForAll();
    }

    public ObservableCollection<Point3D> getPointList() {
        return pointList;
    }

    public Axis getUseAxisforTextureCoordinateX() {
        return useAxisforTextureCoordinateX;
    }

    public void setUseAxisforTextureCoordinateX(Axis useAxisforTextureCoordinateX) {
        this.useAxisforTextureCoordinateX = useAxisforTextureCoordinateX;
        fireValueChangedForTextureCoordinates();
    }

    public Axis getUseAxisforTextureCoordinateY() {
        return useAxisforTextureCoordinateY;
    }

    public void setUseAxisforTextureCoordinateY(Axis useAxisforTextureCoordinateY) {
        this.useAxisforTextureCoordinateY = useAxisforTextureCoordinateY;
        fireValueChangedForTextureCoordinates();
    }

    public enum Points {
        Body
    }

    public enum Axis {
        X,
        Y,
        Z
    }
}
