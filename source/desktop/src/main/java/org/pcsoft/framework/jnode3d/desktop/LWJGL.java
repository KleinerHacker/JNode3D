package org.pcsoft.framework.jnode3d.desktop;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class LWJGL {
    private static final Logger LOGGER = LoggerFactory.getLogger(LWJGL.class);
    private static boolean initialized = false;
    private static GLFWErrorCallback errorCallback;

    static {
        LOGGER.info("LWJGL Version: " + Version.getVersion());
    }

    public synchronized static void initialize() {
        if (initialized)
            return;

        if (!GLFW.glfwInit())
            throw new IllegalStateException("Unable to initialize OpenGL");

        errorCallback = GLFWErrorCallback.createPrint(System.err);
        errorCallback.set();

        initialized = true;
    }

    public synchronized static void terminate() {
        if (!initialized)
            return;

        GLFW.glfwTerminate();

        errorCallback.free();
        errorCallback = null;

        initialized = false;
    }

    public static boolean isInitialized() {
        return initialized;
    }

    private LWJGL() {
    }
}
