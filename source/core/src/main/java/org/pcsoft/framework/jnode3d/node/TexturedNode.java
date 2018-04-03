package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.ogl.OGL;
import org.pcsoft.framework.jnode3d.type.TextureBorderFilter;
import org.pcsoft.framework.jnode3d.type.TextureDistanceFilter;

public abstract class TexturedNode extends RenderNode {
    private TextureBorderFilter textureBorderFilter = TextureBorderFilter.Repeated;
    private TextureDistanceFilter textureMinifying = null, textureMagnifying = null;

    public TextureBorderFilter getTextureBorderFilter() {
        return textureBorderFilter;
    }

    public void setTextureBorderFilter(TextureBorderFilter textureBorderFilter) {
        this.textureBorderFilter = textureBorderFilter;
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

    public void setupTextureAttributes(JNode3DConfiguration configuration, OGL OGL) {

    }
}
