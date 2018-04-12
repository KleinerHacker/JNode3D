package org.pcsoft.framework.jnode3d.internal.handler;

import org.joml.Matrix4f;
import org.pcsoft.framework.jnode3d.node.LightNode;
import org.pcsoft.framework.jnode3d.node.Node;

final class JNode3DLightHandler {
    public static void handleNode(Node node, Matrix4f nodeMatrix) {
        if (!(node instanceof LightNode))
            return;

        ((LightNode) node).setTransformationMatrix(nodeMatrix);
    }

    private JNode3DLightHandler() {
    }
}
