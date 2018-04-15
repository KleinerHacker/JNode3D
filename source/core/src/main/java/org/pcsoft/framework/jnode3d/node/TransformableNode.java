package org.pcsoft.framework.jnode3d.node;

import org.joml.Matrix4f;
import org.pcsoft.framework.jnode3d.type.collection.ObservableCollection;
import org.pcsoft.framework.jnode3d.type.transformation.Rotation;
import org.pcsoft.framework.jnode3d.type.transformation.Scaling;
import org.pcsoft.framework.jnode3d.type.transformation.Transformation;
import org.pcsoft.framework.jnode3d.type.transformation.Translation;

public abstract class TransformableNode extends Node {
    private final ObservableCollection<Transformation> transformationList = new ObservableCollection<>();
    private Matrix4f localMatrix = new Matrix4f();

    public TransformableNode() {
        transformationList.addChangeListener(new ObservableCollection.ChangeListener<Transformation>() {
            @Override
            public void onChanged(ObservableCollection<Transformation> collection) {
                localMatrix = new Matrix4f();

                for (final Transformation transformation : transformationList) {
                    if (transformation instanceof Rotation) {
                        localMatrix.rotate(((Rotation) transformation).getAngle(), ((Rotation) transformation).getAxis());
                    } else if (transformation instanceof Translation) {
                        localMatrix.translateLocal(((Translation) transformation).getPosition());
                    } else if (transformation instanceof Scaling) {
                        localMatrix.scale(((Scaling) transformation).getScale());
                    }
                }
            }
        });
    }

    public ObservableCollection<Transformation> getTransformationList() {
        return transformationList;
    }

    public Matrix4f getLocalMatrix() {
        return localMatrix;
    }

    public Matrix4f getMatrix() {
        if (getParent() == null)
            return localMatrix;

        return new Matrix4f(getParent().getMatrix()).mul(new Matrix4f(localMatrix));
    }
}
