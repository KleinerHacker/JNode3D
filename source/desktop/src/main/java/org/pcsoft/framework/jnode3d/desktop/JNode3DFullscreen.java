package org.pcsoft.framework.jnode3d.desktop;

import org.lwjgl.glfw.GLFW;
import org.pcsoft.framework.jnode3d.config.JNode3DConfiguration;

import java.awt.*;

public final class JNode3DFullscreen extends JNode3DStandalone {
    public JNode3DFullscreen(JNode3DConfiguration configuration) {
        super(configuration, DEF_WIDTH, DEF_HEIGHT);
    }

    @Override
    protected Dimension getGraphicDimension() {
        return new Dimension(getWidth(), getHeight());
    }

    public Dimension getDimension() {
        return new Dimension(getWidth(), getHeight());
    }

    public void setDimension(Dimension dimension) {
        setWidth(dimension.width);
        setHeight(dimension.height);
    }

    @Override
    protected long getGraphicMonitor() {
        return GLFW.glfwGetPrimaryMonitor();
    }


}
