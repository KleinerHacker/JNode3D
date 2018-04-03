package org.pcsoft.framework.jnode3d.config;

import org.pcsoft.framework.jnode3d.type.TextureDistanceFilter;

public final class JNode3DConfigurationBuilder {
    public static JNode3DConfigurationBuilder create() {
        return new JNode3DConfigurationBuilder();
    }

    private JNode3DConfiguration configuration = new JNode3DConfiguration();

    private JNode3DConfigurationBuilder() {
    }

    public JNode3DConfigurationBuilder withVSync(boolean use) {
        configuration.setUseVSync(use);
        return this;
    }

    public JNode3DConfigurationBuilder withTextureMinifying(TextureDistanceFilter textureMinifying) {
        configuration.setTextureMinifying(textureMinifying);
        return this;
    }

    public JNode3DConfigurationBuilder withTextureMagnifying(TextureDistanceFilter textureMagnifying) {
        configuration.setTextureMagnifying(textureMagnifying);
        return this;
    }

    public JNode3DConfiguration build() {
        return configuration;
    }
}
