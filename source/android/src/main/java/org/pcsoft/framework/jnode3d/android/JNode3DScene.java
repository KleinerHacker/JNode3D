package org.pcsoft.framework.jnode3d.android;

import org.pcsoft.framework.jnode3d.Scene3D;
import org.pcsoft.framework.jnode3d.internal.InternalScene3D;
import org.pcsoft.framework.jnode3d.node.Node3D;
import org.pcsoft.framework.jnode3d.type.Color3D;

public class JNode3DScene implements Scene3D {
    private final InternalScene3D scene3D = new InternalScene3D();

    @Override
    public Node3D getRoot() {
        return scene3D.getRoot();
    }

    @Override
    public void setRoot(Node3D root) {
        scene3D.setRoot(root);
    }

    @Override
    public Color3D getBackColor() {
        return scene3D.getBackColor();
    }

    @Override
    public void setBackColor(Color3D color) {
        scene3D.setBackColor(color);
    }
}
