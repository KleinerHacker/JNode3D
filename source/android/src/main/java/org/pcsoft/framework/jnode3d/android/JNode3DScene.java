package org.pcsoft.framework.jnode3d.android;

import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.camera.Camera;
import org.pcsoft.framework.jnode3d.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.internal.JNode3DInternalScene;
import org.pcsoft.framework.jnode3d.node.Node;
import org.pcsoft.framework.jnode3d.type.Color;

public class JNode3DScene implements org.pcsoft.framework.jnode3d.JNode3DScene {
    private final JNode3DInternalScene internalScene = new JNode3DInternalScene(null,800, 600);//TODO

    @Override
    public Node getRoot() {
        return internalScene.getRoot();
    }

    @Override
    public void setRoot(Node root) {
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

    @Override
    public int getWidth() {
        return internalScene.getWidth();
    }

    @Override
    public void setWidth(int width) {
        internalScene.setWidth(width);
    }

    @Override
    public int getHeight() {
        return internalScene.getHeight();
    }

    @Override
    public void setHeight(int height) {
        internalScene.setHeight(height);
    }

    @Override
    public Color getAmbientLightColor() {
        return internalScene.getAmbientLightColor();
    }

    @Override
    public void setAmbientLightColor(Color color) {
        internalScene.setAmbientLightColor(color);
    }

    @Override
    public float getAmbientLightPower() {
        return internalScene.getAmbientLightPower();
    }

    @Override
    public void setAmbientLightPower(float value) {
        internalScene.setAmbientLightPower(value);
    }

    @Override
    public Vector3f getDirectionalLightDirection() {
        return internalScene.getDirectionalLightDirection();
    }

    @Override
    public void setDirectionalLightDirection(Vector3f direction) {
        internalScene.setDirectionalLightDirection(direction);
    }

    @Override
    public Color getDirectionalLightColor() {
        return internalScene.getDirectionalLightColor();
    }

    @Override
    public void setDirectionalLightColor(Color color) {
        internalScene.setDirectionalLightColor(color);
    }

    @Override
    public float getDirectionalLightPower() {
        return internalScene.getDirectionalLightPower();
    }

    @Override
    public void setDirectionalLightPower(float value) {
        internalScene.setDirectionalLightPower(value);
    }

    @Override
    public JNode3DConfiguration getConfiguration() {
        return internalScene.getConfiguration();
    }
}
