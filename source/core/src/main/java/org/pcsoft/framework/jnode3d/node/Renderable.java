package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.internal.GL;

public abstract class Renderable extends Node {
    public abstract void render(GL gl);
}
