package org.pcsoft.framework.jnode3d.desktop.config;

public final class JNode3DConfiguration {
    private boolean useVSync;

    JNode3DConfiguration() {
    }

    public boolean isUseVSync() {
        return useVSync;
    }

    public void setUseVSync(boolean useVSync) {
        this.useVSync = useVSync;
    }
}
