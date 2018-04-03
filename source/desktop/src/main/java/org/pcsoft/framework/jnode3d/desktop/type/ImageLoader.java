package org.pcsoft.framework.jnode3d.desktop.type;

import org.newdawn.slick.opengl.PNGDecoder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class ImageLoader implements org.pcsoft.framework.jnode3d.texture.ImageLoader {
    private ByteBuffer buffer;
    private int width, height;

    public ImageLoader(InputStream in, String type) throws IOException {
        switch (type.toLowerCase()) {
            case "png":
                loadPng(in);
                break;
            default:
                throw new IllegalArgumentException("Not supported image format: " + type);
        }
    }

    @Override
    public ByteBuffer decodeImage() {
        return buffer;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    private void loadPng(InputStream in) throws IOException {
        final PNGDecoder decoder = new PNGDecoder(in);
        this.width = decoder.getWidth();
        this.height = decoder.getHeight();

        this.buffer = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
        decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.RGBA);
        this.buffer.flip();
    }
}
