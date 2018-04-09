package org.pcsoft.framework.jnode3d.internal.handler;

import org.pcsoft.framework.jnode3d.internal.manager.TextureManager;
import org.pcsoft.framework.jnode3d.internal.ogl.OpenGL;
import org.pcsoft.framework.jnode3d.node.ConstructedObjectNode;
import org.pcsoft.framework.jnode3d.node.Node;
import org.pcsoft.framework.jnode3d.node.RenderNode;
import org.pcsoft.framework.jnode3d.type.RenderMode;
import org.pcsoft.framework.jnode3d.type.TextureStack;
import org.pcsoft.framework.jnode3d.type.reference.BufferReference;

final class JNode3DRenderHandler {
    public static void handleNode(Node root, final OpenGL ogl) {
        if (root instanceof RenderNode) {
            if (root instanceof ConstructedObjectNode) {
                handleNodeConstructed(ogl, (ConstructedObjectNode) root);
            }
        }
    }

    private static void handleNodeConstructed(final OpenGL ogl, final ConstructedObjectNode constructedObjectNode) {
        if (constructedObjectNode.getTexture() != null) {
            ogl.glBindTexture(TextureManager.getInstance().getTextureIdentifier(constructedObjectNode.getTexture()), TextureStack.Texture0);
        }

        if (constructedObjectNode.getShaderList() != null && !constructedObjectNode.getShaderList().isEmpty()) {
            JNode3DShaderHandler.activateShader(constructedObjectNode.getShaderList(), ogl);
        }

        final BufferReference reference = ogl.glCreateBuffer(constructedObjectNode.getVertices(), constructedObjectNode.getIndices());
        ogl.glDrawBuffer(RenderMode.Triangles, reference);
        ogl.glDeleteBuffer(reference);
    }

    private JNode3DRenderHandler() {
    }
}
