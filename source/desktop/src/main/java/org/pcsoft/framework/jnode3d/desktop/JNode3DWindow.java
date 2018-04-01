package org.pcsoft.framework.jnode3d.desktop;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;
import org.pcsoft.framework.jnode3d.desktop.config.JNode3DConfiguration;

import java.awt.*;
import java.nio.IntBuffer;

public class JNode3DWindow extends JNode3DStandalone {
    private boolean resizable = true;
    private int width = 300, height = 300;
    private boolean centerWindow = true;

    public JNode3DWindow(JNode3DConfiguration configuration) {
        super(configuration);

        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
    }

    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, resizable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isCenterWindow() {
        return centerWindow;
    }

    public void setCenterWindow(boolean centerWindow) {
        this.centerWindow = centerWindow;
    }

    @Override
    protected Dimension getGraphicDimension() {
        return new Dimension(width, height);
    }

    @Override
    protected long getGraphicMonitor() {
        return 0;
    }

    @Override
    protected void onInit() {
        if (centerWindow) {
            // Get the thread stack and push a new frame
            try (MemoryStack stack = MemoryStack.stackPush()) {
                IntBuffer pWidth = stack.mallocInt(1); // int*
                IntBuffer pHeight = stack.mallocInt(1); // int*

                // Get the window size passed to glfwCreateWindow
                GLFW.glfwGetWindowSize(windowPtr, pWidth, pHeight);

                // Get the resolution of the primary monitor
                GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

                // Center the window
                GLFW.glfwSetWindowPos(
                        windowPtr,
                        (vidmode.width() - pWidth.get(0)) / 2,
                        (vidmode.height() - pHeight.get(0)) / 2
                );
            } // the stack frame is popped automatically
        }
    }
}
