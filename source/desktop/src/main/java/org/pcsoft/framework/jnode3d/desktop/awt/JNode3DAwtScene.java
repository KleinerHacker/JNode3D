package org.pcsoft.framework.jnode3d.desktop.awt;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.pcsoft.framework.jnode3d.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.desktop.type.NGLImpl;
import org.pcsoft.framework.jnode3d.internal.JNode3DInternalScene;
import org.pcsoft.framework.jnode3d.camera.Camera;
import org.pcsoft.framework.jnode3d.node.Node;
import org.pcsoft.framework.jnode3d.ogl.OGL;
import org.pcsoft.framework.jnode3d.type.Color;


public class JNode3DAwtScene implements org.pcsoft.framework.jnode3d.JNode3DScene {
    private final JNode3DInternalScene JNode3DInternalScene;

    public JNode3DAwtScene(JNode3DConfiguration configuration, int width, int height) {
        JNode3DInternalScene = new JNode3DInternalScene(configuration, new OGL(new NGLImpl()), width, height);

        GLFWErrorCallback.createPrint(System.err).set();

        if (!GLFW.glfwInit())
            throw new IllegalStateException("Unable to initialize OpenGL");
    }

    @Override
    public Node getRoot() {
        return JNode3DInternalScene.getRoot();
    }

    @Override
    public void setRoot(Node root) {
        JNode3DInternalScene.setRoot(root);
    }

    @Override
    public Color getBackColor() {
        return JNode3DInternalScene.getBackColor();
    }

    @Override
    public void setBackColor(Color color) {
        JNode3DInternalScene.setBackColor(color);
    }

    @Override
    public Camera getCamera() {
        return JNode3DInternalScene.getCamera();
    }

    @Override
    public void setCamera(Camera camera) {
        JNode3DInternalScene.setCamera(camera);
    }

    @Override
    public int getWidth() {
        return JNode3DInternalScene.getWidth();
    }

    @Override
    public void setWidth(int width) {
        JNode3DInternalScene.setWidth(width);
    }

    @Override
    public int getHeight() {
        return JNode3DInternalScene.getHeight();
    }

    @Override
    public void setHeight(int height) {
        JNode3DInternalScene.setHeight(height);
    }

    @Override
    public JNode3DConfiguration getConfiguration() {
        return JNode3DInternalScene.getConfiguration();
    }
}
