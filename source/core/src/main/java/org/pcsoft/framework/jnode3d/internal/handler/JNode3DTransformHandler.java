package org.pcsoft.framework.jnode3d.internal.handler;

import org.joml.Matrix4f;
import org.pcsoft.framework.jnode3d.node.Node;
import org.pcsoft.framework.jnode3d.node.TransformableNode;
import org.pcsoft.framework.jnode3d.ogl.OpenGL;

final class JNode3DTransformHandler {
    public static Matrix4f handleNode(Node root, Matrix4f rootMatrix, OpenGL ogl) {
        if (!(root instanceof TransformableNode))
            return rootMatrix;

        final Matrix4f resultMatrix = new Matrix4f(rootMatrix).mul(((TransformableNode) root).getLocalMatrix());
        ogl.glLoadMatrix(resultMatrix);

        return resultMatrix;
    }

    private JNode3DTransformHandler() {
    }
}
