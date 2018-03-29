package org.pcsoft.framework.jnode3d.desktop;

import org.lwjgl.glfw.GLFW;
import org.pcsoft.framework.jnode3d.desktop.config.JNode3DConfiguration;

import java.awt.*;

public final class JNodeFullscreen3D extends JNodeStandalone3D {
    private Dimension dimension = new Dimension(800, 600);

    public JNodeFullscreen3D(JNode3DConfiguration configuration) {
        super(configuration);
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    protected Dimension getGraphicDimension() {
        return dimension;
    }

    @Override
    protected long getGraphicMonitor() {
        return GLFW.glfwGetPrimaryMonitor();
    }
}
