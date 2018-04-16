package org.pcsoft.framework.jnode3d.internal.manager;

import org.pcsoft.framework.jnode3d.node.RenderableObjectNode;
import org.pcsoft.framework.jnode3d.ogl.GLFactory;
import org.pcsoft.framework.jnode3d.ogl.OpenGL;
import org.pcsoft.framework.jnode3d.type.Fragment;
import org.pcsoft.framework.jnode3d.type.reference.BufferReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represent a manager for the OpenGL native buffers
 */
public final class BufferManager implements OpenGLDependendManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(BufferManager.class);
    private static final BufferManager instance = new BufferManager();

    public static BufferManager getInstance() {
        return instance;
    }

    private final Map<Key, BufferReference> bufferMap = new HashMap<>();

    private boolean initialized = false;

    private BufferManager() {
    }

    //<editor-fold desc="Initialization">
    @Override
    public void initialize() {
        if (initialized)
            throw new IllegalStateException("Already initialized");

        LOGGER.info("Initialize Buffer Manager");
        for (final Key key : bufferMap.keySet()) {
            final BufferReference bufferReference = buildBuffer(key);
            if (bufferReference == null)
                continue;

            bufferMap.put(key, bufferReference);
        }

        initialized = true;
    }

    @Override
    public void destroy() {
        if (!initialized)
            throw new IllegalStateException("Not initialized yet");

        LOGGER.info("Destroy Buffer Manager");
        for (final Key key : bufferMap.keySet()) {
            if (!deleteBuffer(key))
                continue;

            bufferMap.put(key, null);
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
     *
     * @param node
     * @param fragmentIndex
     */
    public void registerBuffer(RenderableObjectNode node, int fragmentIndex) {
        LOGGER.debug("Register node " + node.getDebugName() + ", fragment " + fragmentIndex + " (initialized: " + initialized + ")");

        final Key key = new Key(node, fragmentIndex);
        if (initialized) {
            final BufferReference bufferReference = buildBuffer(key);
            bufferMap.put(key, bufferReference);
        } else {
            bufferMap.put(key, null);
        }
    }

    /**
     * Unregister a node. This method delete the assigned buffer of this node.
     *
     * @param node
     * @param fragmentIndex
     */
    public void unregisterBuffer(RenderableObjectNode node, int fragmentIndex) {
        LOGGER.debug("Unregister node " + node.getDebugName() + ", fragment " + fragmentIndex + " (initialized: " + initialized + ")");

        final Key key = new Key(node, fragmentIndex);
        if (initialized) {
            deleteBuffer(key);
        }
        bufferMap.remove(key);
    }
    //</editor-fold>

    /**
     * Update generated buffer for given node if system is initialized
     *
     * @param node
     * @param fragmentIndex
     */
    public void updateBuffer(RenderableObjectNode node, int fragmentIndex) {
        final Key key = new Key(node, fragmentIndex);

        if (!bufferMap.containsKey(key))
            throw new IllegalArgumentException("Given node is not registered: " + node.getDebugName());

        if (initialized) {
            final OpenGL ogl = GLFactory.getOpenGL();

            ogl.glDeleteBuffer(bufferMap.get(key));
            final BufferReference bufferReference = ogl.glCreateBuffer(key.getFragment().getVertices(), key.getFragment().getIndices());
            bufferMap.put(key, bufferReference); //Overwrite
        }
    }

    /**
     * Get reference to buffer for given node
     *
     * @param node
     * @param fragmentIndex
     * @return
     */
    public BufferReference getBuffer(RenderableObjectNode node, int fragmentIndex) {
        final Key key = new Key(node, fragmentIndex);

        if (!bufferMap.containsKey(key))
            throw new IllegalArgumentException("Given node is not registered: " + node.getDebugName());

        return bufferMap.get(key);
    }

    private BufferReference buildBuffer(Key key) {
        final OpenGL ogl = GLFactory.getOpenGL();
        return ogl.glCreateBuffer(key.getFragment().getVertices(), key.getFragment().getIndices());
    }

    private boolean deleteBuffer(Key key) {
        if (!bufferMap.containsKey(key))
            return false;

        final OpenGL ogl = GLFactory.getOpenGL();
        ogl.glDeleteBuffer(bufferMap.get(key));

        return true;
    }

    private static final class Key {
        private final RenderableObjectNode node;
        private final int fragmentIndex;

        public Key(RenderableObjectNode node, int fragmentIndex) {
            this.node = node;
            this.fragmentIndex = fragmentIndex;
        }

        public Fragment getFragment() {
            return this.node.getFragments()[fragmentIndex];
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return fragmentIndex == key.fragmentIndex &&
                    Objects.equals(node, key.node);
        }

        @Override
        public int hashCode() {
            return Objects.hash(node, fragmentIndex);
        }
    }
}
