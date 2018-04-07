package org.pcsoft.framework.jnode3d.internal.handler;

import org.pcsoft.framework.jnode3d.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.node.Node;
import org.pcsoft.framework.jnode3d.node.TexturedNode;
import org.pcsoft.framework.jnode3d.internal.ogl.OpenGL;

final class JNode3DTextureHandler {
    public static void handleNode(Node root, OpenGL ogl, JNode3DConfiguration configuration) {
        if (root instanceof TexturedNode) {
            ((TexturedNode) root).setupTextureAttributes(configuration, ogl);
        }
    }

    private JNode3DTextureHandler() {
    }
}
