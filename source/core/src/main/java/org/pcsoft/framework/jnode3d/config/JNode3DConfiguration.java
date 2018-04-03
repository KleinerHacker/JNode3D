package org.pcsoft.framework.jnode3d.config;

import org.pcsoft.framework.jnode3d.type.TextureDistanceFilter;

public final class JNode3DConfiguration {
    private boolean useVSync = true;
    private TextureDistanceFilter textureMinifying = TextureDistanceFilter.Nearest, textureMagnifying = TextureDistanceFilter.Nearest;

    JNode3DConfiguration() {
    }

    public boolean isUseVSync() {
        return useVSync;
    }

    public void setUseVSync(boolean useVSync) {
        this.useVSync = useVSync;
    }

    public TextureDistanceFilter getTextureMinifying() {
        return textureMinifying;
    }

    public void setTextureMinifying(TextureDistanceFilter textureMinifying) {
        this.textureMinifying = textureMinifying;
    }

    public TextureDistanceFilter getTextureMagnifying() {
        return textureMagnifying;
    }

    public void setTextureMagnifying(TextureDistanceFilter textureMagnifying) {
        this.textureMagnifying = textureMagnifying;
    }
}
