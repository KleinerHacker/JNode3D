package org.pcsoft.framework.jnode3d;

import org.pcsoft.framework.jnode3d.camera.Camera;
import org.pcsoft.framework.jnode3d.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.node.Node;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.light.AmbientLight;
import org.pcsoft.framework.jnode3d.type.light.DirectionalLight;

public interface JNode3DScene {
    Node getRoot();
    void setRoot(Node root);

    Color getBackColor();
    void setBackColor(Color color);

    Camera getCamera();
    void setCamera(Camera camera);

    int getWidth();
    void setWidth(int width);

    int getHeight();
    void setHeight(int height);

    AmbientLight getAmbientLight();
    DirectionalLight getDirectionalLight();

    JNode3DConfiguration getConfiguration();
}
