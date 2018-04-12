package org.pcsoft.framework.jnode3d.material.texture;

import java.nio.ByteBuffer;

public interface ImageLoader {
    ByteBuffer decodeImage();
    int getWidth();
    int getHeight();
}
