package org.pcsoft.framework.jnode3d.desktop;

import org.pcsoft.framework.jnode3d.desktop.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.desktop.config.JNode3DConfigurationBuilder;

public class JNodeWindow3DTest {

    public static void main(String[] args) {
        final JNode3DConfiguration configuration = JNode3DConfigurationBuilder.create()
                .withVSync(true)
                .build();

        final JNodeWindow3D window3D = new JNodeWindow3D(configuration);
        window3D.setTitle("Test Window");
        window3D.setWidth(800);
        window3D.setHeight(600);
        window3D.setResizable(false);
        window3D.showAndWait();
    }

}