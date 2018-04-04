package org.pcsoft.framework.jnode3d.node.processing;

import org.pcsoft.framework.jnode3d.node.RenderNode;
import org.pcsoft.framework.jnode3d.ogl.OGL;

public interface RenderProcessor<T extends RenderNode> extends Processor<T> {
    void render(OGL ogl, T node);
}
