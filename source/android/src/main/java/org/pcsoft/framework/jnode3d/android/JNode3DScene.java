package org.pcsoft.framework.jnode3d.android;

import org.pcsoft.framework.jnode3d.internal.JNode3DInternalScene;
import org.pcsoft.framework.jnode3d.node.Node3D;
import org.pcsoft.framework.jnode3d.type.Color;

public class JNode3DScene implements org.pcsoft.framework.jnode3d.JNode3DScene {
    private final JNode3DInternalScene scene3D = new JNode3DInternalScene(null);//TODO

    @Override
    public Node3D getRoot() {
        return scene3D.getRoot();
    }

    @Override
    public void setRoot(Node3D root) {
        scene3D.setRoot(root);
    }

    @Override
    public Color getBackColor() {
        return scene3D.getBackColor();
    }

    @Override
    public void setBackColor(Color color) {
        scene3D.setBackColor(color);
    }
}
