package org.pcsoft.framework.jnode3d.node.processing.render;

import org.pcsoft.framework.jnode3d.internal.manager.ShaderManager;
import org.pcsoft.framework.jnode3d.internal.manager.TextureManager;
import org.pcsoft.framework.jnode3d.node.ConstructedObjectNode;
import org.pcsoft.framework.jnode3d.node.RenderNode;
import org.pcsoft.framework.jnode3d.node.processing.RenderProcessor;
import org.pcsoft.framework.jnode3d.ogl.DrawingCallback;
import org.pcsoft.framework.jnode3d.ogl.OGL;
import org.pcsoft.framework.jnode3d.type.RenderMode;
import org.pcsoft.framework.jnode3d.type.TextureStack;

public final class BasicRenderProcessor implements RenderProcessor<RenderNode> {
    @Override
    public void render(final OGL ogl, final RenderNode node) {
        if (node instanceof ConstructedObjectNode) {
            final ConstructedObjectNode constructedObjectNode = (ConstructedObjectNode) node;

            if (constructedObjectNode.getTexture() != null) {
                ogl.glBindTexture(TextureManager.getInstance().getTextureIdentifier(constructedObjectNode.getTexture()), TextureStack.Texture0);
            }

            if (constructedObjectNode.getShaderInstance() != null) {
                ogl.glUseProgram(ShaderManager.getInstance().getShaderIdentifier(constructedObjectNode.getShaderInstance().getShader()));
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
    }
}
