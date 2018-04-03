package org.pcsoft.framework.jnode3d.texture;

import org.pcsoft.framework.jnode3d.ogl.OGL;
import org.pcsoft.framework.jnode3d.type.TextureStack;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

public final class Texture2D implements Closeable {
    private final int width, height;
    private final int identifier;

    private final OGL ogl;

    public Texture2D(OGL ogl, ImageLoader loader) {
        this(ogl, loader.decodeImage(), loader.getWidth(), loader.getHeight());
    }

    public Texture2D(OGL ogl, ByteBuffer buffer, int width, int height) {
        this.ogl = ogl;

        this.width = width;
        this.height = height;

        this.identifier = ogl.glLoadTexture(buffer, width, height, TextureStack.Texture0);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void bind() {
        ogl.glBindTexture(identifier, TextureStack.Texture0);
    }

    @Override
    public void close() throws IOException {
        ogl.glDeleteTexture(identifier);
    }
}
