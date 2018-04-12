package org.pcsoft.framework.jnode3d.internal.manager;

import org.pcsoft.framework.jnode3d.node.RenderableObjectNode;
import org.pcsoft.framework.jnode3d.ogl.GLFactory;
import org.pcsoft.framework.jnode3d.ogl.OpenGL;
import org.pcsoft.framework.jnode3d.type.reference.BufferReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Represent a manager for the OpenGL native buffers
 */
public final class BufferManager implements OpenGLDependendManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(BufferManager.class);
    private static final BufferManager instance = new BufferManager();

    public static BufferManager getInstance() {
        return instance;
    }

    private final Map<RenderableObjectNode, BufferReference> bufferMap = new HashMap<>();

    private boolean initialized = false;

    private BufferManager() {
    }

    //<editor-fold desc="Initialization">
    @Override
    public void initialize() {
        if (initialized)
            throw new IllegalStateException("Already initialized");

        LOGGER.info("Initialize Buffer Manager");
        for (final RenderableObjectNode node : bufferMap.keySet()) {
            final BufferReference bufferReference = buildBuffer(node);
            if (bufferReference == null)
                continue;

            bufferMap.put(node, bufferReference);
        }

        initialized = true;
    }

    @Override
    public void destroy() {
        if (!initialized)
            throw new IllegalStateException("Not initialized yet");

        LOGGER.info("Destroy Buffer Manager");
        for (final RenderableObjectNode node : bufferMap.keySet()) {
            if (!deleteBuffer(node))
                continue;

            bufferMap.put(node, null);
        }

        initialized = false;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }
    //</editor-fold>

    //<editor-fold desc="Registration">

    /**
     * Register a node to create buffer for it. This method creates the buffer, if system is initialized,
     * otherwise it will generated if it will initialized.
     * @param node
     */
    public void registerBuffer(RenderableObjectNode node) {
        LOGGER.debug("Register node (initialized: " + initialized + ")");

        if (initialized) {
            final BufferReference bufferReference = buildBuffer(node);
            bufferMap.put(node, bufferReference);
        } else {
            bufferMap.put(node, null);
        }
    }

    /**
     * Unregister a node. This method delete the assigned buffer of this node.
     * @param node
     */
    public void unregisterBuffer(RenderableObjectNode node) {
        LOGGER.debug("Unregister node (initialized: " + initialized + ")");

        if (initialized) {
            deleteBuffer(node);
        }
        bufferMap.remove(node);
    }
    //</editor-fold>

    /**
     * Update generated buffer for given node if system is initialized
     * @param node
     */
    public void updateBuffer(RenderableObjectNode node) {
        if (!bufferMap.containsKey(node))
            throw new IllegalArgumentException("Given node is not registered");

        if (initialized) {
            final OpenGL ogl = GLFactory.getOpenGL();

            ogl.glDeleteBuffer(bufferMap.get(node));
            final BufferReference bufferReference = ogl.glCreateBuffer(node.getVertices(), node.getIndices());
            bufferMap.put(node, bufferReference); //Overwrite
        }
    }

    /**
     * Get reference to buffer for given node
     * @param node
     * @return
     */
    public BufferReference getBuffer(RenderableObjectNode node) {
        if (!bufferMap.containsKey(node))
            throw new IllegalArgumentException("Given node is not registered");

        return bufferMap.get(node);
    }

    private BufferReference buildBuffer(RenderableObjectNode node) {
        final OpenGL ogl = GLFactory.getOpenGL();
        return ogl.glCreateBuffer(node.getVertices(), node.getIndices());
    }

    private boolean deleteBuffer(RenderableObjectNode node) {
        if (!bufferMap.containsKey(node))
            return false;

        final OpenGL ogl = GLFactory.getOpenGL();
        ogl.glDeleteBuffer(bufferMap.get(node));

        return true;
    }
}
