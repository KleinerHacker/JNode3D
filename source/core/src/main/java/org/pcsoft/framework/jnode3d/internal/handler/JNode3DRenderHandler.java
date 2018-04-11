package org.pcsoft.framework.jnode3d.internal.handler;

import org.pcsoft.framework.jnode3d.internal.manager.ShaderManager;
import org.pcsoft.framework.jnode3d.internal.manager.TextureManager;
import org.pcsoft.framework.jnode3d.node.ConstructedObjectNode;
import org.pcsoft.framework.jnode3d.node.Node;
import org.pcsoft.framework.jnode3d.node.RenderableObjectNode;
import org.pcsoft.framework.jnode3d.ogl.OpenGL;
import org.pcsoft.framework.jnode3d.type.RenderMode;
import org.pcsoft.framework.jnode3d.type.TextureStack;
import org.pcsoft.framework.jnode3d.type.reference.BufferReference;
import org.pcsoft.framework.jnode3d.type.reference.ShaderProgramReference;

final class JNode3DRenderHandler {
    public static void handleNode(Node root, final OpenGL ogl) {
        if (root instanceof RenderableObjectNode) {
            if (((RenderableObjectNode) root).isDepthTestActive()) {
                ogl.glEnableDepthTest();
            } else {
                ogl.glDisableDepthTest();
            }

            if (root instanceof ConstructedObjectNode) {
                handleNodeConstructed(ogl, (ConstructedObjectNode) root);
            }
        }
    }

    private static void handleNodeConstructed(final OpenGL ogl, final ConstructedObjectNode constructedObjectNode) {
        if (constructedObjectNode.getTexture() != null) {
            ogl.glBindTexture(TextureManager.getInstance().getTextureIdentifier(constructedObjectNode.getTexture()), TextureStack.Texture0);
        }

        final ShaderProgramReference shaderProgramReference = ShaderManager.getInstance().getShaderProgramReference(constructedObjectNode);
        if (shaderProgramReference != null) {
            ogl.glUseProgram(shaderProgramReference);
        }

        final BufferReference reference = ogl.glCreateBuffer(constructedObjectNode.getVertices(), constructedObjectNode.getIndices());
        ogl.glDrawBuffer(RenderMode.Triangles, reference);
        ogl.glDeleteBuffer(reference);
    }

    private JNode3DRenderHandler() {
    }
}
