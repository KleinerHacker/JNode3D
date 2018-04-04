package org.pcsoft.framework.jnode3d.node.processing.transform;

import org.joml.Matrix4f;
import org.pcsoft.framework.jnode3d.node.TransformableNode;
import org.pcsoft.framework.jnode3d.node.processing.TransformProcessor;
import org.pcsoft.framework.jnode3d.ogl.OGL;
import org.pcsoft.framework.jnode3d.transformation.Rotation;
import org.pcsoft.framework.jnode3d.transformation.Scaling;
import org.pcsoft.framework.jnode3d.transformation.Transformation;
import org.pcsoft.framework.jnode3d.transformation.Translation;

public final class BasicTransformProcessor implements TransformProcessor<TransformableNode> {
    @Override
    public Matrix4f transform(OGL ogl, TransformableNode node, Matrix4f rootMatrix) {
        final Matrix4f localMatrix = new Matrix4f();
        localMatrix.identity();

        for (final Transformation transformation : node.getTransformationList()) {
            if (transformation instanceof Rotation) {
                localMatrix.rotate(((Rotation) transformation).getAngle(), ((Rotation) transformation).getAxis());
            } else if (transformation instanceof Translation) {
                localMatrix.translateLocal(((Translation) transformation).getPosition());
            } else if (transformation instanceof Scaling) {
                localMatrix.scale(((Scaling) transformation).getScale());
            }
        }

        final Matrix4f resultMatrix = rootMatrix.mul(localMatrix);
        ogl.glLoadMatrix(resultMatrix);

        return resultMatrix;
    }
}
