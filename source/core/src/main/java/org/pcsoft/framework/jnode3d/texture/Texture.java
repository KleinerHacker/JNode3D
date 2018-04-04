package org.pcsoft.framework.jnode3d.texture;

import org.pcsoft.framework.jnode3d.internal.manager.TextureManager;

import java.nio.ByteBuffer;
import java.util.Objects;

public final class Texture {
    private final ByteBuffer buffer;
    private final int width, height;

    public Texture(ImageLoader loader) {
        this(loader.decodeImage(), loader.getWidth(), loader.getHeight());
    }

    public Texture(ByteBuffer buffer, int width, int height) {
        this.buffer = buffer;
        this.width = width;
        this.height = height;

        TextureManager.getInstance().registerTexture(this);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Texture texture = (Texture) o;
        return Objects.equals(buffer, texture.buffer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buffer.hashCode());
    }
}
