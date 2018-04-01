package org.pcsoft.framework.jnode3d.desktop;

import org.pcsoft.framework.jnode3d.desktop.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.desktop.config.JNode3DConfigurationBuilder;

import java.awt.*;

public class JNode3DFullscreenTest {
    public static void main(String[] args) {
        final JNode3DConfiguration configuration = JNode3DConfigurationBuilder.create()
                .withVSync(true)
                .build();

        final JNode3DFullscreen fullscreen3D = new JNode3DFullscreen(configuration);
        fullscreen3D.setTitle("Test Fullscreen");
        fullscreen3D.setDimension(new Dimension(800, 600));
        fullscreen3D.showAndWait();
    }
}