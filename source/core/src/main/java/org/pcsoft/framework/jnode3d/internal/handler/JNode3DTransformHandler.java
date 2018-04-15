package org.pcsoft.framework.jnode3d.internal.handler;

import org.joml.Matrix4f;
import org.pcsoft.framework.jnode3d.node.Node;
import org.pcsoft.framework.jnode3d.node.TransformableNode;
import org.pcsoft.framework.jnode3d.ogl.OpenGL;

final class JNode3DTransformHandler {
    public static Matrix4f handleNode(Node root, Matrix4f rootMatrix, OpenGL ogl) {
        if (!(root instanceof TransformableNode))
            return rootMatrix;

//        final Matrix4f localMatrix = new Matrix4f();
//        localMatrix.identity();
//
//        for (final Transformation transformation : ((TransformableNode) root).getTransformationList()) {
//            if (transformation instanceof Rotation) {
//                localMatrix.rotate(((Rotation) transformation).getAngle(), ((Rotation) transformation).getAxis());
//            } else if (transformation instanceof Translation) {
//                localMatrix.translateLocal(((Translation) transformation).getPosition());
//            } else if (transformation instanceof Scaling) {
//                localMatrix.scale(((Scaling) transformation).getScale());
//            }
//        }
//
        final Matrix4f resultMatrix = new Matrix4f(rootMatrix).mul(((TransformableNode) root).getLocalMatrix());
        ogl.glLoadMatrix(resultMatrix);

        return resultMatrix;
    }

    private JNode3DTransformHandler() {
    }
}
