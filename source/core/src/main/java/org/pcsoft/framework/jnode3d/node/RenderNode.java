package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.ogl.OGL;

public abstract class RenderNode extends TransformableNode {
    public abstract void render(OGL OGL);
}
