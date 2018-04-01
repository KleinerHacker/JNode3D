package org.pcsoft.framework.jnode3d.desktop.awt;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.pcsoft.framework.jnode3d.desktop.type.GLImpl;
import org.pcsoft.framework.jnode3d.internal.JNode3DInternalScene;
import org.pcsoft.framework.jnode3d.node.Camera;
import org.pcsoft.framework.jnode3d.node.Node3D;
import org.pcsoft.framework.jnode3d.type.Color;


public class JNode3DAwtScene implements org.pcsoft.framework.jnode3d.JNode3DScene {
    private final JNode3DInternalScene JNode3DInternalScene = new JNode3DInternalScene(new GLImpl());

    public JNode3DAwtScene() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!GLFW.glfwInit())
            throw new IllegalStateException("Unable to initialize OpenGL");
    }

    @Override
    public Node3D getRoot() {
        return JNode3DInternalScene.getRoot();
    }

    @Override
    public void setRoot(Node3D root) {
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
}
