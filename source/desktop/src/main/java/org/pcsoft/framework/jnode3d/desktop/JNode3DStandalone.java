package org.pcsoft.framework.jnode3d.desktop;

import org.apache.commons.lang.StringUtils;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.pcsoft.framework.jnode3d.JNode3DScene;
import org.pcsoft.framework.jnode3d.camera.Camera;
import org.pcsoft.framework.jnode3d.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.desktop.type.NativeGLImpl;
import org.pcsoft.framework.jnode3d.internal.JNode3DInternalScene;
import org.pcsoft.framework.jnode3d.internal.ogl.GLFactory;
import org.pcsoft.framework.jnode3d.node.Node;
import org.pcsoft.framework.jnode3d.type.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JNode3DStandalone implements JNode3DScene {
    private static final Logger LOGGER = LoggerFactory.getLogger(JNode3DStandalone.class);
    protected static final int DEF_WIDTH = 800;
    protected static final int DEF_HEIGHT = 600;

    protected String title = "";
    protected boolean autoExit = true;

    protected long windowPtr;
    private final JNode3DInternalScene internalScene;

    protected JNode3DStandalone(JNode3DConfiguration configuration) {
        if (!LWJGL.isInitialized()) {
            LWJGL.initialize();
        }

        this.init(configuration);

        if (!GLFactory.isInitialized()) {
            GLFactory.initialize(new NativeGLImpl());
        }

        this.internalScene = new JNode3DInternalScene(configuration, DEF_WIDTH, DEF_HEIGHT);
        this.internalScene.initialize();
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

    @Override
    public JNode3DConfiguration getConfiguration() {
        return internalScene.getConfiguration();
    }

    public final void showAndWait() {
        if (!internalScene.isInitialized()) {
            internalScene.initialize();
        }

        GLFW.glfwSetWindowShouldClose(windowPtr, false);
        GLFW.glfwSetWindowSize(windowPtr, internalScene.getWidth(), internalScene.getHeight());
        GLFW.glfwSetWindowTitle(windowPtr, StringUtils.isEmpty(title) ? "" : title);
        onShowing();

        // Make the window visible
        GLFW.glfwShowWindow(windowPtr);

        loop();
        done();
    }

    public final void terminate() {
        GL.destroy();

        // Free the window callbacks and destroy the window
        Callbacks.glfwFreeCallbacks(windowPtr);
        GLFW.glfwDestroyWindow(windowPtr);

        if (LWJGL.isInitialized()) {
            LWJGL.terminate();
        }
        if (GLFactory.isInitialized()) {
            GLFactory.terminate();
        }
    }

    //<editor-fold desc="Natives">
    protected final void init(JNode3DConfiguration configuration) {
        LOGGER.info("Initialize standalone OpenGL system");

        final long graphicMonitor = getGraphicMonitor();

        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        windowPtr = GLFW.glfwCreateWindow(DEF_WIDTH, DEF_HEIGHT, "", graphicMonitor, 0);
        if (windowPtr == 0L)
            throw new IllegalStateException("Unable to create OpenGL window");

        GLFW.glfwSetKeyCallback(windowPtr, (window, key, scancode, action, mods) -> {
            if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE && autoExit) {
                GLFW.glfwSetWindowShouldClose(window, true);
            }
        });

        // Make the OpenGL context current
        GLFW.glfwMakeContextCurrent(windowPtr);
        setupConfiguration(configuration);

        LOGGER.info(">>> OGL Version: " + GL11.glGetString(GL11.GL_VERSION));

        onInit();
    }

    private void setupConfiguration(JNode3DConfiguration configuration) {
        // Enable v-sync
        if (configuration.isUseVSync()) {
            GLFW.glfwSwapInterval(1);
        }

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
    }

    private void done() {
        LOGGER.info("Destroy standalone OpenGL system");

        internalScene.destroy();

        onDone();
    }

    private void loop() {
        LOGGER.debug("Start standalone OpenGL system LOOP");

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

    protected void onShowing() {
        //Empty
    }

    protected void onTerminate() {
        //Empty
    }
}
