package org.pcsoft.framework.jnode3d.internal.manager;

import org.pcsoft.framework.jnode3d.ogl.OGL;
import org.pcsoft.framework.jnode3d.texture.Texture;
import org.pcsoft.framework.jnode3d.type.TextureStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public final class TextureManager implements Manager {
    private static final TextureManager instance = new TextureManager();

    public static TextureManager getInstance() {
        return instance;
    }

    private final Map<Texture, TextureIdentifier> textureIdentifierMap = new HashMap<>();
    private boolean initialized = false;

    private OGL ogl;

    private TextureManager() {
    }

    @Override
    public void initialize(OGL ogl) {
        for (final Texture texture : textureIdentifierMap.keySet()) {
            if (textureIdentifierMap.get(texture) != null)
                continue;

            final TextureIdentifier textureIdentifier = buildTexture(ogl, texture);

            textureIdentifierMap.put(texture, textureIdentifier);
        }

        this.ogl = ogl;
        this.initialized = true;
    }

    @Override
    public void destroy(OGL ogl) {
        for (final Texture texture : new HashSet<>(textureIdentifierMap.keySet())) {
            final TextureIdentifier textureIdentifier = textureIdentifierMap.get(texture);
            if (textureIdentifier == null)
                continue;

            ogl.glDeleteTexture(textureIdentifier.getTextureId());

            textureIdentifierMap.put(texture, null);
        }

        this.ogl = null;
        this.initialized = false;
    }

    @Override
    public boolean isInitialized() {
        return initialized && ogl != null;
    }

    public void registerTexture(Texture texture) {
        if (isInitialized()) {
            final TextureIdentifier textureIdentifier = buildTexture(ogl, texture);
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

    private TextureIdentifier buildTexture(OGL ogl, Texture texture) {
        final int textureId = ogl.glLoadTexture(texture.getBuffer(), texture.getWidth(), texture.getHeight(), TextureStack.Texture0);
        return new TextureIdentifier(textureId);
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
