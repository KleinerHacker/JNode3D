package org.pcsoft.framework.jnode3d.node;

import org.joml.Matrix4f;
import org.pcsoft.framework.jnode3d.ogl.OGL;
import org.pcsoft.framework.jnode3d.transformation.Rotation;
import org.pcsoft.framework.jnode3d.transformation.Scaling;
import org.pcsoft.framework.jnode3d.transformation.Transformation;
import org.pcsoft.framework.jnode3d.transformation.Translation;

import java.util.ArrayList;
import java.util.List;

public abstract class TransformableNode implements Node {
    private final List<Transformation> transformationList = new ArrayList<>();

    public List<Transformation> getTransformationList() {
        return transformationList;
    }

    public final Matrix4f transform(OGL OGL, Matrix4f rootMatrix) {
        final Matrix4f localMatrix = new Matrix4f();
        localMatrix.identity();

        for (final Transformation transformation : transformationList) {
            if (transformation instanceof Rotation) {
                localMatrix.rotate(((Rotation) transformation).getAngle(), ((Rotation) transformation).getAxis());
            } else if (transformation instanceof Translation) {
                localMatrix.translateLocal(((Translation) transformation).getPosition());
            } else if (transformation instanceof Scaling) {
                localMatrix.scale(((Scaling) transformation).getScale());
            }
        }

        final Matrix4f resultMatrix = rootMatrix.mul(localMatrix);
        OGL.glLoadMatrix(resultMatrix);

        return resultMatrix;
    }
}
