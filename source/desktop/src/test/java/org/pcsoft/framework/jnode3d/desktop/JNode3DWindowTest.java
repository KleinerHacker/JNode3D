package org.pcsoft.framework.jnode3d.desktop;

import org.pcsoft.framework.jnode3d.desktop.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.desktop.config.JNode3DConfigurationBuilder;
import org.pcsoft.framework.jnode3d.node.Triangle3D;

public class JNode3DWindowTest {

    public static void main(String[] args) {
        final JNode3DConfiguration configuration = JNode3DConfigurationBuilder.create()
                .withVSync(true)
                .build();

        final JNode3DWindow window3D = new JNode3DWindow(configuration);
        window3D.setTitle("Test Window");
        window3D.setWidth(800);
        window3D.setHeight(600);
        window3D.setResizable(false);
        window3D.setRoot(new Triangle3D());
        window3D.showAndWait();
    }

}