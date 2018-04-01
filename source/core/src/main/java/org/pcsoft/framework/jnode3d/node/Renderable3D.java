package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.internal.GL;

public abstract class Renderable3D extends Node3D {
    public abstract void render(GL gl);
}
