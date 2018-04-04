package org.pcsoft.framework.jnode3d.texture;

import java.nio.ByteBuffer;

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
}
