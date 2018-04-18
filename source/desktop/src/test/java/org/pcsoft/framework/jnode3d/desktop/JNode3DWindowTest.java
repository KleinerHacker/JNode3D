package org.pcsoft.framework.jnode3d.desktop;

import org.pcsoft.framework.jnode3d.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.config.JNode3DConfigurationBuilder;

import java.io.IOException;

public class JNode3DWindowTest extends JNode3DTest {

    public static void main(String[] args) throws IOException {
        final JNode3DConfiguration configuration = JNode3DConfigurationBuilder.create()
                .withVSync(true)
                .build();

        final JNode3DWindow window3D = new JNode3DWindow(configuration);
        window3D.setTitle("Test Window");
        window3D.setResizable(false);
        window3D.setWidth(800);
        window3D.setHeight(600);
        window3D.getAmbientLight().setPower(0.1f);
        window3D.getDirectionalLight().setPower(0.5f);
        buildScene(window3D);

//        new AnimationBase() {
//            private long counter = 0L;
//
//            @Override
//            protected void loop(long timeDelta) {
//                counter += timeDelta;
//
//                window3D.setAmbientLight(new AmbientLight(Color.WHITE, (float) Math.abs(Math.sin(counter * 0.000000001))));
//                window3D.setDirectionalLight(new DirectionalLight(Color.WHITE, (float) Math.abs(Math.sin(counter * 0.0000000001))));
//            }
//        }.start();

        window3D.showAndWait();
        window3D.terminate();
    }
}