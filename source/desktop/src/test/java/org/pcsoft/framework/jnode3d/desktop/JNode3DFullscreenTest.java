package org.pcsoft.framework.jnode3d.desktop;

import org.pcsoft.framework.jnode3d.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.config.JNode3DConfigurationBuilder;

import java.awt.*;
import java.io.IOException;

public class JNode3DFullscreenTest extends JNode3DTest {
    public static void main(String[] args) throws IOException {
        final JNode3DConfiguration configuration = JNode3DConfigurationBuilder.create()
                .withVSync(true)
                .build();

        final JNode3DFullscreen fullscreen3D = new JNode3DFullscreen(configuration);
        fullscreen3D.setTitle("Test Fullscreen");
        fullscreen3D.setDimension(new Dimension(1920, 1080));
        buildScene(fullscreen3D);
        fullscreen3D.showAndWait();
    }
}