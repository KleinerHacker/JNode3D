package org.pcsoft.framework.jnode3d.internal.manager;

/**
 * Interface for am OpenGL depend manager. This manager contains methods for initialization an destroying
 */
public interface OpenGLDependendManager extends Manager {
    /**
     * Initialize the manager. Is called if the OpenGL system is initialized
     */
    void initialize();

    /**
     * Destroy the manager. Is called if the OpenGL system want to destroy
     */
    void destroy();

    /**
     * Check that this manager is initialized or not
     * @return
     */
    boolean isInitialized();
}
