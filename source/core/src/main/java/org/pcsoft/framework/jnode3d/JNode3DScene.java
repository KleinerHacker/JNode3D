package org.pcsoft.framework.jnode3d;

import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.camera.Camera;
import org.pcsoft.framework.jnode3d.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.node.Node;
import org.pcsoft.framework.jnode3d.type.Color;

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

    Color getAmbientLightColor();
    void setAmbientLightColor(Color color);
    float getAmbientLightPower();
    void setAmbientLightPower(float value);

    Vector3f getDirectionalLightDirection();
    void setDirectionalLightDirection(Vector3f direction);
    Color getDirectionalLightColor();
    void setDirectionalLightColor(Color color);
    float getDirectionalLightPower();
    void setDirectionalLightPower(float value);

    JNode3DConfiguration getConfiguration();
}
