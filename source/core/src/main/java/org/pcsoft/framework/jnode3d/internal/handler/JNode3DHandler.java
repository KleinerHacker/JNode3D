package org.pcsoft.framework.jnode3d.internal.handler;

import org.joml.Matrix4f;
import org.pcsoft.framework.jnode3d.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.node.GroupNode;
import org.pcsoft.framework.jnode3d.node.Node;
import org.pcsoft.framework.jnode3d.ogl.OpenGL;

public final class JNode3DHandler {
    public static void handleNode(Node root, Matrix4f rootMatrix, final OpenGL ogl, JNode3DConfiguration configuration) {
        handleNode(root, new Matrix4f(rootMatrix), ogl, configuration, HandleMode.Lights);
        handleNode(root, new Matrix4f(rootMatrix), ogl, configuration, HandleMode.Rendering);
    }

    @SuppressWarnings("unchecked")
    private static void handleNode(Node node, Matrix4f rootMatrix, final OpenGL ogl, JNode3DConfiguration configuration, HandleMode mode) {
        if (node == null)
            return;

        //Transformation
        final Matrix4f nodeMatrix = JNode3DTransformHandler.handleNode(node, rootMatrix, ogl);

        switch (mode) {
            case Rendering:
                //Texture
                JNode3DTextureHandler.handleNode(node, ogl, configuration);
                //Rendering
                JNode3DRenderHandler.handleNode(node, ogl);
                break;
            case Lights:
                JNode3DLightHandler.handleNode(node, nodeMatrix);
                break;
            default:
                throw new RuntimeException();
        }

        //Sub elements
        handleNodeChildren(node, ogl, configuration, nodeMatrix, mode);
    }

    private static void handleNodeChildren(Node root, OpenGL ogl, JNode3DConfiguration configuration, Matrix4f nodeMatrix, HandleMode mode) {
        if (root instanceof GroupNode) {
            for (final Node child : ((GroupNode) root).getChildren()) {
                handleNode(child, nodeMatrix, ogl, configuration, mode);
            }
        }
    }

    private JNode3DHandler() {
    }

    private enum HandleMode {
        Lights,
        Rendering
    }
}
