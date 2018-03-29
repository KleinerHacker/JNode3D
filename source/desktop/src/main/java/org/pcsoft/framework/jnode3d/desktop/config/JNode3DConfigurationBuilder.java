package org.pcsoft.framework.jnode3d.desktop.config;

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

    public JNode3DConfiguration build() {
        return configuration;
    }
}
