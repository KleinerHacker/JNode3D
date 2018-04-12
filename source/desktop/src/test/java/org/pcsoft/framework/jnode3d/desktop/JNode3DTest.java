package org.pcsoft.framework.jnode3d.desktop;

import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.anim.AnimationBase;
import org.pcsoft.framework.jnode3d.camera.PerspectiveLookAtCamera;
import org.pcsoft.framework.jnode3d.desktop.type.ImageLoader;
import org.pcsoft.framework.jnode3d.material.SnowMaterial;
import org.pcsoft.framework.jnode3d.material.texture.Texture;
import org.pcsoft.framework.jnode3d.node.*;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.transformation.Translation;

import java.io.IOException;

public abstract class JNode3DTest {
    protected static void buildScene(JNode3DStandalone standalone) {
        final GroupNode root = new GroupNode();
        root.getChildren().add(buildBox());
        final PointLightNode pointLightNode = new PointLightNode();
        pointLightNode.getTransformationList().add(new Translation(new Vector3f(0.6f, 0.6f, 0.6f)));
        root.getChildren().add(pointLightNode);

        standalone.setRoot(root);
        final PerspectiveLookAtCamera camera = buildCamera();
        standalone.setCamera(camera);

        new AnimationBase() {
            private long counter = 0L;

            @Override
            protected void loop(long timeDelta) {
                counter += timeDelta;
                camera.setPosition(new Vector3f((float) (Math.sin(counter * 0.000000001) * -2d), 2f, (float) (Math.cos(counter * 0.000000001) * -2d)));
            }
        }.start();
    }

    private static RectangleNode buildRectangle() {
        final RectangleNode rectangleNode = new RectangleNode();
        rectangleNode.setColorAt(RectangleNode.Points.LeftTopCorner, Color.BLUE);
        rectangleNode.setColorAt(RectangleNode.Points.RightTopCorner, Color.RED);
        rectangleNode.setColorAt(RectangleNode.Points.LeftBottomCorner, Color.GREEN);
        rectangleNode.setColorAt(RectangleNode.Points.RightBottomCorner, Color.YELLOW);
        try {
            final Texture texture2D = new Texture(new ImageLoader(JNode3DTest.class.getResourceAsStream("/tex/tex01.png"), "png"));
            rectangleNode.setTexture(texture2D);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rectangleNode;
    }

    private static TriangleNode buildTriangle() {
        final TriangleNode triangleNode = new TriangleNode();
        triangleNode.setColorAt(TriangleNode.Points.Top, Color.BLUE);
        triangleNode.setColorAt(TriangleNode.Points.LeftCorner, Color.RED);
        triangleNode.setColorAt(TriangleNode.Points.RightCorner, Color.GREEN);
        try {
            final Texture texture2D = new Texture(new ImageLoader(JNode3DTest.class.getResourceAsStream("/tex/tex01.png"), "png"));
            triangleNode.setTexture(texture2D);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return triangleNode;
    }

    private static BoxNode buildBox() {
        final BoxNode boxNode = new BoxNode();
        boxNode.setColorAt(BoxNode.Points.TopLeftFront, Color.BLUE);
        boxNode.setColorAt(BoxNode.Points.TopRightFront, Color.RED);
        boxNode.setColorAt(BoxNode.Points.TopLeftBack, Color.GREEN);
        boxNode.setColorAt(BoxNode.Points.TopRightBack, Color.YELLOW);
        boxNode.setColorAt(BoxNode.Points.BottomLeftFront, Color.YELLOW);
        boxNode.setColorAt(BoxNode.Points.BottomRightFront, Color.GREEN);
        boxNode.setColorAt(BoxNode.Points.BottomLeftBack, Color.RED);
        boxNode.setColorAt(BoxNode.Points.BottomRightBack, Color.BLUE);
        try {
            final Texture texture2D = new Texture(new ImageLoader(JNode3DTest.class.getResourceAsStream("/tex/tex01.png"), "png"));
            boxNode.setTexture(texture2D);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final SnowMaterial snowMaterial = new SnowMaterial();
        boxNode.setMaterial(snowMaterial);

        return boxNode;
    }

    private static PerspectiveLookAtCamera buildCamera() {
        final PerspectiveLookAtCamera camera = new PerspectiveLookAtCamera();
        camera.setPosition(new Vector3f(-2f, 2f, -2f));

        return camera;
    }
}
