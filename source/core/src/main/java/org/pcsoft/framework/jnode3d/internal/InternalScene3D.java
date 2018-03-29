package org.pcsoft.framework.jnode3d.internal;

import org.pcsoft.framework.jnode3d.Scene3D;
import org.pcsoft.framework.jnode3d.node.Node3D;
import org.pcsoft.framework.jnode3d.type.Color3D;

public final class InternalScene3D implements Scene3D {
    private Node3D root;

    @Override
    public Node3D getRoot() {
        return root;
    }

    @Override
    public void setRoot(Node3D root) {
        this.root = root;
    }

    @Override
    public Color3D getBackColor() {
        return null;
    }

    @Override
    public void setBackColor(Color3D color) {

    }
}
