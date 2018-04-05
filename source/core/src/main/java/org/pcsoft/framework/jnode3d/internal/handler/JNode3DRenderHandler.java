package org.pcsoft.framework.jnode3d.internal.handler;

import org.pcsoft.framework.jnode3d.internal.manager.TextureManager;
import org.pcsoft.framework.jnode3d.node.ConstructedObjectNode;
import org.pcsoft.framework.jnode3d.node.Node;
import org.pcsoft.framework.jnode3d.node.RenderNode;
import org.pcsoft.framework.jnode3d.ogl.DrawingCallback;
import org.pcsoft.framework.jnode3d.ogl.OGL;
import org.pcsoft.framework.jnode3d.type.RenderMode;
import org.pcsoft.framework.jnode3d.type.TextureStack;

final class JNode3DRenderHandler {
    public static void handleNode(Node root, final OGL ogl) {
        if (root instanceof RenderNode) {
            if (root instanceof ConstructedObjectNode) {
                handleNodeConstructed(ogl, (ConstructedObjectNode) root);
            }
        }
    }

    private static void handleNodeConstructed(final OGL ogl, final ConstructedObjectNode constructedObjectNode) {
        if (constructedObjectNode.getTexture() != null) {
            ogl.glBindTexture(TextureManager.getInstance().getTextureIdentifier(constructedObjectNode.getTexture()), TextureStack.Texture0);
        }

        if (constructedObjectNode.getShaderInstance() != null) {
            JNode3DShaderHandler.activateShader(constructedObjectNode.getShaderInstance(), ogl);
        }

        ogl.glDraw(RenderMode.Triangles, new DrawingCallback() {
            @Override
            public void draw() {
                for (int i = 0; i < constructedObjectNode.getVertices().length; i++) {
                    ogl.glVertex(constructedObjectNode.getVertices()[i].getPosition());
                    ogl.glColor(constructedObjectNode.getVertices()[i].getColor());
                    ogl.glTexCoord(constructedObjectNode.getVertices()[i].getTextureCoordinate());
                    ogl.glNormal(constructedObjectNode.getVertices()[i].getNormal());
                }
            }
        });
    }

    private JNode3DRenderHandler() {
    }
}