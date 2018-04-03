package org.pcsoft.framework.jnode3d.desktop;

import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.camera.PerspectiveLookAtCamera;
import org.pcsoft.framework.jnode3d.node.Group;
import org.pcsoft.framework.jnode3d.node.Triangle;
import org.pcsoft.framework.jnode3d.type.Color;

public abstract class JNode3DTest {
    protected static void buildScene(JNode3DStandalone standalone) {
        final Group root = new Group();
        root.getChildren().add(buildTriangle());

        standalone.setRoot(root);
        standalone.setCamera(buildCamera());
    }

    private static Triangle buildTriangle() {
        final Triangle triangle = new Triangle();
        triangle.setColorAt(Triangle.Points.Top, Color.BLUE);
        triangle.setColorAt(Triangle.Points.LeftCorner, Color.RED);
        triangle.setColorAt(Triangle.Points.RightCorner, Color.GREEN);

        return triangle;
    }

    private static PerspectiveLookAtCamera buildCamera() {
        final PerspectiveLookAtCamera camera = new PerspectiveLookAtCamera();
        camera.setPosition(new Vector3f(-1f, -0.5f, -1f));
        
        return camera;
    }
}
