package org.pcsoft.framework.jnode3d.desktop;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class LWJGL {
    private static final Logger LOGGER = LoggerFactory.getLogger(LWJGL.class);
    private static boolean initialized = false;

    static {
        LOGGER.info("LWJGL Version: " + Version.getVersion());
    }

    public static void initialize() {
        if (initialized)
            return;

        if (!GLFW.glfwInit())
            throw new IllegalStateException("Unable to initialize OpenGL");


        GLFWErrorCallback.createPrint(System.err).set();

        initialized = true;
    }

    public static boolean isInitialized() {
        return initialized;
    }

    private LWJGL() {
    }
}
