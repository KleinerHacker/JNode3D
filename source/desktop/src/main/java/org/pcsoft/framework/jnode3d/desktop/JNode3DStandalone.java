package org.pcsoft.framework.jnode3d.desktop;

import org.apache.commons.lang.StringUtils;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.pcsoft.framework.jnode3d.JNode3DScene;
import org.pcsoft.framework.jnode3d.desktop.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.desktop.type.GLImpl;
import org.pcsoft.framework.jnode3d.internal.JNode3DInternalScene;
import org.pcsoft.framework.jnode3d.camera.Camera;
import org.pcsoft.framework.jnode3d.node.Node;
import org.pcsoft.framework.jnode3d.type.Color;

import java.awt.*;

public abstract class JNode3DStandalone implements JNode3DScene {
    protected static final int DEF_WIDTH = 800;
    protected static final int DEF_HEIGHT = 600;

    protected String title = "";
    protected boolean autoExit = true;

    protected final JNode3DConfiguration configuration;

    protected long windowPtr;
    private final JNode3DInternalScene internalScene;

    protected JNode3DStandalone(JNode3DConfiguration configuration, int width, int height) {
        internalScene = new JNode3DInternalScene(new GLImpl(), width, height);

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

        internalScene.initialize();

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

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!GLFW.glfwWindowShouldClose(windowPtr)) {
            internalScene.loop();

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
