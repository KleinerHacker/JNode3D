package org.pcsoft.framework.jnode3d.internal.handler;

import org.pcsoft.framework.jnode3d.internal.manager.BufferManager;
import org.pcsoft.framework.jnode3d.internal.manager.ShaderManager;
import org.pcsoft.framework.jnode3d.node.Node;
import org.pcsoft.framework.jnode3d.node.RenderableObjectNode;
import org.pcsoft.framework.jnode3d.node.processing.ProcessorFactory;
import org.pcsoft.framework.jnode3d.ogl.OpenGL;
import org.pcsoft.framework.jnode3d.type.RenderMode;
import org.pcsoft.framework.jnode3d.type.reference.ShaderProgramReference;

final class JNode3DRenderHandler {
    public static void handleNode(Node root, final OpenGL ogl) {
        if (root instanceof RenderableObjectNode) {
            final RenderableObjectNode renderableObjectNode = (RenderableObjectNode) root;

            //Initial base setup
            if (renderableObjectNode.isDepthTestActive()) {
                ogl.glEnableDepthTest();
            } else {
                ogl.glDisableDepthTest();
            }
            ogl.glPolygonMode(renderableObjectNode.getPolygonMode());

            //Initial shader setup
            final ShaderProgramReference shaderProgramReference = ShaderManager.getInstance().getShaderProgramReference(renderableObjectNode.getMaterial());
            if (shaderProgramReference != null) {
                ogl.glUseProgram(shaderProgramReference);
            }

            //Draw
            final RenderMode renderMode = ProcessorFactory.getVertexCalculationProcessor((Class)renderableObjectNode.getClass()).getRenderMode();
            ogl.glDrawBuffer(renderMode, BufferManager.getInstance().getBuffer(renderableObjectNode));

            //Cleanup
            ogl.glUseProgram(null);
        }
    }

    private JNode3DRenderHandler() {
    }
}
