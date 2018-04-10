package org.pcsoft.framework.jnode3d.desktop;

import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.anim.AnimationBase;
import org.pcsoft.framework.jnode3d.camera.PerspectiveLookAtCamera;
import org.pcsoft.framework.jnode3d.desktop.type.ImageLoader;
import org.pcsoft.framework.jnode3d.node.Box;
import org.pcsoft.framework.jnode3d.node.Group;
import org.pcsoft.framework.jnode3d.node.Rectangle;
import org.pcsoft.framework.jnode3d.node.Triangle;
import org.pcsoft.framework.jnode3d.shader.SnowShader;
import org.pcsoft.framework.jnode3d.texture.Texture;
import org.pcsoft.framework.jnode3d.type.Color;

import java.io.IOException;

public abstract class JNode3DTest {
    protected static void buildScene(JNode3DStandalone standalone) {
        final Group root = new Group();
        root.getChildren().add(buildBox());

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

    private static Rectangle buildRectangle() {
        final Rectangle rectangle = new Rectangle();
        rectangle.setColorAt(Rectangle.Points.LeftTopCorner, Color.BLUE);
        rectangle.setColorAt(Rectangle.Points.RightTopCorner, Color.RED);
        rectangle.setColorAt(Rectangle.Points.LeftBottomCorner, Color.GREEN);
        rectangle.setColorAt(Rectangle.Points.RightBottomCorner, Color.YELLOW);
        try {
            final Texture texture2D = new Texture(new ImageLoader(JNode3DTest.class.getResourceAsStream("/tex/tex01.png"), "png"));
            rectangle.setTexture(texture2D);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*rectangle.getShaderList().addAll(Arrays.asList(
                SnowShader.get().buildInstance(false),
                OpacityShader.get().buildInstance(0.1f)
        ));*/

        return rectangle;
    }

    private static Triangle buildTriangle() {
        final Triangle triangle = new Triangle();
        triangle.setColorAt(Triangle.Points.Top, Color.BLUE);
        triangle.setColorAt(Triangle.Points.LeftCorner, Color.RED);
        triangle.setColorAt(Triangle.Points.RightCorner, Color.GREEN);
        try {
            final Texture texture2D = new Texture(new ImageLoader(JNode3DTest.class.getResourceAsStream("/tex/tex01.png"), "png"));
            triangle.setTexture(texture2D);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*triangle.getShaderList().addAll(Arrays.asList(
                SnowShader.get().buildInstance(false),
                OpacityShader.get().buildInstance(0.5f)
        ));*/

        return triangle;
    }

    private static Box buildBox() {
        final Box box = new Box();
        box.setColorAt(Box.Points.TopLeftFront, Color.BLUE);
        box.setColorAt(Box.Points.TopRightFront, Color.RED);
        box.setColorAt(Box.Points.TopLeftBack, Color.GREEN);
        box.setColorAt(Box.Points.TopRightBack, Color.YELLOW);
        box.setColorAt(Box.Points.BottomLeftFront, Color.YELLOW);
        box.setColorAt(Box.Points.BottomRightFront, Color.GREEN);
        box.setColorAt(Box.Points.BottomLeftBack, Color.RED);
        box.setColorAt(Box.Points.BottomRightBack, Color.BLUE);
        try {
            final Texture texture2D = new Texture(new ImageLoader(JNode3DTest.class.getResourceAsStream("/tex/tex01.png"), "png"));
            box.setTexture(texture2D);
        } catch (IOException e) {
            e.printStackTrace();
        }
        box.addShader(new SnowShader());

        return box;
    }

    private static PerspectiveLookAtCamera buildCamera() {
        final PerspectiveLookAtCamera camera = new PerspectiveLookAtCamera();
        camera.setPosition(new Vector3f(-2f, 2f, -2f));

        return camera;
    }
}
