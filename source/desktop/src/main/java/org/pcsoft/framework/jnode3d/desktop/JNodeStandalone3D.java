package org.pcsoft.framework.jnode3d.desktop;

import org.apache.commons.lang.StringUtils;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.pcsoft.framework.jnode3d.Scene3D;
import org.pcsoft.framework.jnode3d.desktop.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.node.Node3D;
import org.pcsoft.framework.jnode3d.type.Color3D;

import java.awt.*;

public abstract class JNodeStandalone3D implements Scene3D {
    protected Node3D root;
    protected String title = "";
    protected boolean autoExit = true;
    private Color3D backColor = Color3D.BLACK;

    protected final JNode3DConfiguration configuration;

    protected long windowPtr;

    protected JNodeStandalone3D(JNode3DConfiguration configuration) {
        this.configuration = configuration;

        if (!LWJGL.isInitialized()) {
            LWJGL.initialize();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAutoExit() {
        return autoExit;
    }

    public void setAutoExit(boolean autoExit) {
        this.autoExit = autoExit;
    }

    @Override
    public Color3D getBackColor() {
        return backColor;
    }

    @Override
    public void setBackColor(Color3D backColor) {
        this.backColor = backColor;
    }

    @Override
    public Node3D getRoot() {
        return root;
    }

    @Override
    public void setRoot(Node3D root) {
        this.root = root;
    }

    public final void showAndWait() {
        init();

        // Make the window visible
        GLFW.glfwShowWindow(windowPtr);

        loop();

        done();
    }

    //<editor-fold desc="Natives">
    protected final void init() {
        final Dimension graphicDimension = getGraphicDimension();
        final long graphicMonitor = getGraphicMonitor();

        windowPtr = GLFW.glfwCreateWindow(graphicDimension.width, graphicDimension.height, StringUtils.isEmpty(title) ? "" : title, graphicMonitor, 0);
        if (windowPtr == 0L)
            throw new IllegalStateException("Unable to create OpenGL window");

        GLFW.glfwSetKeyCallback(windowPtr, (window, key, scancode, action, mods) -> {
            if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE && autoExit) {
                GLFW.glfwSetWindowShouldClose(window, true);
            }
        });

        // Make the OpenGL context current
        GLFW.glfwMakeContextCurrent(windowPtr);
        // Enable v-sync
        if (configuration.isUseVSync()) {
            GLFW.glfwSwapInterval(1);
        }

        onInit();
    }

    private void done() {
        // Free the window callbacks and destroy the window
        Callbacks.glfwFreeCallbacks(windowPtr);
        GLFW.glfwDestroyWindow(windowPtr);

        // Terminate GLFW and free the error callback
        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();

        onDone();
    }

    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        GL11.glClearColor(backColor.getR(), backColor.getG(), backColor.getB(), backColor.getA());

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!GLFW.glfwWindowShouldClose(windowPtr)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            GLFW.glfwSwapBuffers(windowPtr); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            GLFW.glfwPollEvents();

            onLoop();
        }
    }
    //</editor-fold>

    protected abstract Dimension getGraphicDimension();

    protected abstract long getGraphicMonitor();

    protected void onInit() {
        //Empty
    }

    protected void onLoop() {
        //Empty
    }

    protected void onDone() {
        //Empty
    }
}
