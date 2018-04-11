package org.pcsoft.framework.jnode3d.node;

import org.apache.commons.lang.ObjectUtils;
import org.pcsoft.framework.jnode3d.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.ogl.OpenGL;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.TextureBorderFilter;
import org.pcsoft.framework.jnode3d.type.TextureDistanceFilter;

public abstract class TexturedNode extends RenderableObjectNode {
    private TextureBorderFilter textureBorderFilterS = TextureBorderFilter.Repeated, textureBorderFilterT = TextureBorderFilter.Repeated;
    private Color textureBorderColor = Color.WHITE;
    private TextureDistanceFilter textureDistanceMinifying = null, textureDistanceMagnifying = null;

    public TextureBorderFilter getTextureBorderFilterS() {
        return textureBorderFilterS;
    }

    public void setTextureBorderFilterS(TextureBorderFilter textureBorderFilterS) {
        this.textureBorderFilterS = textureBorderFilterS;
    }

    public TextureBorderFilter getTextureBorderFilterT() {
        return textureBorderFilterT;
    }

    public void setTextureBorderFilterT(TextureBorderFilter textureBorderFilterT) {
        this.textureBorderFilterT = textureBorderFilterT;
    }

    public void setTextureBorderFilter(TextureBorderFilter textureBorderFilter) {
        setTextureBorderFilterS(textureBorderFilter);
        setTextureBorderFilterT(textureBorderFilter);
    }

    public TextureDistanceFilter getTextureDistanceMinifying() {
        return textureDistanceMinifying;
    }

    public void setTextureDistanceMinifying(TextureDistanceFilter textureDistanceMinifying) {
        this.textureDistanceMinifying = textureDistanceMinifying;
    }

    public TextureDistanceFilter getTextureDistanceMagnifying() {
        return textureDistanceMagnifying;
    }

    public void setTextureDistanceMagnifying(TextureDistanceFilter textureDistanceMagnifying) {
        this.textureDistanceMagnifying = textureDistanceMagnifying;
    }

    public void setTextureDistance(TextureDistanceFilter filter) {
        setTextureDistanceMinifying(filter);
        setTextureDistanceMagnifying(filter);
    }

    public Color getTextureBorderColor() {
        return textureBorderColor;
    }

    public void setTextureBorderColor(Color textureBorderColor) {
        this.textureBorderColor = textureBorderColor;
    }

    public void setupTextureAttributes(JNode3DConfiguration configuration, OpenGL ogl) {
        final TextureDistanceFilter textureMinifying = (TextureDistanceFilter) ObjectUtils.defaultIfNull(this.textureDistanceMinifying, configuration.getTextureMinifying());
        final TextureDistanceFilter textureMagnifying = (TextureDistanceFilter) ObjectUtils.defaultIfNull(this.textureDistanceMagnifying, configuration.getTextureMagnifying());

        ogl.glTextureMinMagFilter(textureMinifying, textureMagnifying);
        ogl.glTextureWrap(textureBorderFilterS, textureBorderFilterT);
        ogl.glTextureBorder(textureBorderColor);
    }
}
