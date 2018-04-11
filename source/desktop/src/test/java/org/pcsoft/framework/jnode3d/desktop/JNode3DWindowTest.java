package org.pcsoft.framework.jnode3d.desktop;

import org.pcsoft.framework.jnode3d.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.config.JNode3DConfigurationBuilder;

public class JNode3DWindowTest extends JNode3DTest {

    public static void main(String[] args)  {
        final JNode3DConfiguration configuration = JNode3DConfigurationBuilder.create()
                .withVSync(true)
                .build();

        final JNode3DWindow window3D = new JNode3DWindow(configuration);
        window3D.setTitle("Test Window");
        window3D.setResizable(false);
        window3D.setWidth(800);
        window3D.setHeight(600);
        window3D.setAmbientLightPower(0.05f);
        window3D.setDirectionalLightPower(0.1f);
        buildScene(window3D);
        window3D.showAndWait();
        window3D.terminate();
    }
}