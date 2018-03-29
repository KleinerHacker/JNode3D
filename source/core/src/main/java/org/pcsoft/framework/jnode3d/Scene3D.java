package org.pcsoft.framework.jnode3d;

import org.pcsoft.framework.jnode3d.node.Node3D;
import org.pcsoft.framework.jnode3d.type.Color3D;

public interface Scene3D {
    Node3D getRoot();
    void setRoot(Node3D root);

    Color3D getBackColor();
    void setBackColor(Color3D color);
}
