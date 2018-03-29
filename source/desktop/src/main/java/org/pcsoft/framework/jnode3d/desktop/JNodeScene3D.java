package org.pcsoft.framework.jnode3d.desktop;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.pcsoft.framework.jnode3d.Scene3D;
import org.pcsoft.framework.jnode3d.internal.InternalScene3D;
import org.pcsoft.framework.jnode3d.node.Node3D;
import org.pcsoft.framework.jnode3d.type.Color3D;


public class JNodeScene3D implements Scene3D {
    private final InternalScene3D internalScene3D = new InternalScene3D();

    public JNodeScene3D() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!GLFW.glfwInit())
            throw new IllegalStateException("Unable to initialize OpenGL");
    }

    @Override
    public Node3D getRoot() {
        return internalScene3D.getRoot();
    }

    @Override
    public void setRoot(Node3D root) {
        internalScene3D.setRoot(root);
    }

    @Override
    public Color3D getBackColor() {
        return internalScene3D.getBackColor();
    }

    @Override
    public void setBackColor(Color3D color) {
        internalScene3D.setBackColor(color);
    }
}
