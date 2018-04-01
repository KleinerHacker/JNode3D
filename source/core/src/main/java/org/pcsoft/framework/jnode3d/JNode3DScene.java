package org.pcsoft.framework.jnode3d;

import org.pcsoft.framework.jnode3d.node.Camera;
import org.pcsoft.framework.jnode3d.node.Node3D;
import org.pcsoft.framework.jnode3d.type.Color;

public interface JNode3DScene {
    Node3D getRoot();
    void setRoot(Node3D root);

    Color getBackColor();
    void setBackColor(Color color);

    Camera getCamera();
    void setCamera(Camera camera);
}
