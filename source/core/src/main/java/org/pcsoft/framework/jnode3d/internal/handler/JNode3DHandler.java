package org.pcsoft.framework.jnode3d.internal.handler;

import org.joml.Matrix4f;
import org.pcsoft.framework.jnode3d.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.node.Group;
import org.pcsoft.framework.jnode3d.node.Node;
import org.pcsoft.framework.jnode3d.node.RenderNode;
import org.pcsoft.framework.jnode3d.ogl.OGL;

public final class JNode3DHandler {
    @SuppressWarnings("unchecked")
    public static void handleNode(Node root, Matrix4f rootMatrix, final OGL ogl, JNode3DConfiguration configuration) {
        if (root == null)
            return;

        //Transformation
        final Matrix4f childRootMatrix = JNode3DTrandformHandler.handleNode(root, rootMatrix, ogl);

        //Texture
        JNode3DTextureHandler.handleNode(root, ogl, configuration);

        //Rendering
        JNode3DRenderHandler.handleNode(root, ogl);

        //Sub elements
        handleNodeChildren(root, ogl, configuration, childRootMatrix);
    }

    private static void handleNodeChildren(Node root, OGL ogl, JNode3DConfiguration configuration, Matrix4f childRootMatrix) {
        if (root instanceof Group) {
            for (final Node child : ((Group) root).getChildren()) {
                if (!(child instanceof RenderNode))
                    continue;

                handleNode(child, childRootMatrix, ogl, configuration);
            }
        }
    }

    private JNode3DHandler() {
    }
}
