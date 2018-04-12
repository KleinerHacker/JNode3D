package org.pcsoft.framework.jnode3d.internal.manager;

import org.pcsoft.framework.jnode3d.ogl.GLFactory;
import org.pcsoft.framework.jnode3d.ogl.OpenGL;
import org.pcsoft.framework.jnode3d.material.texture.Texture;
import org.pcsoft.framework.jnode3d.type.TextureStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public final class TextureManager implements OpenGLDependendManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(TextureManager.class);
    private static final TextureManager instance = new TextureManager();

    public static TextureManager getInstance() {
        return instance;
    }

    private final Map<Texture, TextureIdentifier> textureIdentifierMap = new HashMap<>();
    private boolean initialized = false;

    //<editor-fold desc="Initialization">
    @Override
    public void initialize() {
        if (initialized)
            throw new IllegalStateException("Already initialized");

        LOGGER.info("Initialize Texture Manager");

        for (final Texture texture : textureIdentifierMap.keySet()) {
            final TextureIdentifier textureIdentifier = buildTexture(texture);
            if (textureIdentifier == null)
                continue;

            textureIdentifierMap.put(texture, textureIdentifier);
        }

        this.initialized = true;
    }

    @Override
    public void destroy() {
        if (!initialized)
            throw new IllegalStateException("Not initialized yet");

        LOGGER.info("Destroy Texture Manager");

        for (final Texture texture : new HashSet<>(textureIdentifierMap.keySet())) {
            if (!deleteTexture(texture))
                continue;

            textureIdentifierMap.put(texture, null);
        }

        this.initialized = false;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }
    //</editor-fold>

    public void registerTexture(Texture texture) {
        LOGGER.debug("Register texture (initialized: " + isInitialized() + ")");

        if (isInitialized()) {
            final TextureIdentifier textureIdentifier = buildTexture(texture);
            textureIdentifierMap.put(texture, textureIdentifier);
        } else {
            textureIdentifierMap.put(texture, null);
        }
    }

    public int getTextureIdentifier(Texture texture) {
        if (!isInitialized())
            throw new IllegalStateException("System not initialized yet");
        if (!textureIdentifierMap.containsKey(texture))
            throw new IllegalArgumentException("Unable to find texture!");

        return textureIdentifierMap.get(texture).getTextureId();
    }

    private TextureIdentifier buildTexture(Texture texture) {
        if (textureIdentifierMap.get(texture) != null)
            return null;

        LOGGER.debug("Build texture");
        final OpenGL ogl = GLFactory.getOpenGL();

        final int textureId = ogl.glLoadTexture(texture.getBuffer(), texture.getWidth(), texture.getHeight(), TextureStack.Texture0);
        return new TextureIdentifier(textureId);
    }

    private boolean deleteTexture(Texture texture) {
        final TextureIdentifier textureIdentifier = textureIdentifierMap.get(texture);
        if (textureIdentifier == null)
            return false;

        LOGGER.debug("Delete Texture");
        final OpenGL ogl = GLFactory.getOpenGL();

        ogl.glDeleteTexture(textureIdentifier.getTextureId());

        return true;
    }

    private static final class TextureIdentifier {
        private final int textureId;

        public TextureIdentifier(int textureId) {
            this.textureId = textureId;
        }

        public int getTextureId() {
            return textureId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TextureIdentifier that = (TextureIdentifier) o;
            return textureId == that.textureId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(textureId);
        }

        @Override
        public String toString() {
            return "TextureIdentifier{" +
                    "textureId=" + textureId +
                    '}';
        }
    }
}
