package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.internal.GL;

public abstract class RenderNode extends TransformableNode {
    public abstract void render(GL gl);
}
