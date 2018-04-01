package org.pcsoft.framework.jnode3d.android;

import org.pcsoft.framework.jnode3d.internal.JNode3DInternalScene;
import org.pcsoft.framework.jnode3d.node.Camera;
import org.pcsoft.framework.jnode3d.node.Node3D;
import org.pcsoft.framework.jnode3d.type.Color;

public class JNode3DScene implements org.pcsoft.framework.jnode3d.JNode3DScene {
    private final JNode3DInternalScene internalScene = new JNode3DInternalScene(null);//TODO

    @Override
    public Node3D getRoot() {
        return internalScene.getRoot();
    }

    @Override
    public void setRoot(Node3D root) {
        internalScene.setRoot(root);
    }

    @Override
    public Color getBackColor() {
        return internalScene.getBackColor();
    }

    @Override
    public void setBackColor(Color color) {
        internalScene.setBackColor(color);
    }

    @Override
    public Camera getCamera() {
        return internalScene.getCamera();
    }

    @Override
    public void setCamera(Camera camera) {
        internalScene.setCamera(camera);
    }
}
