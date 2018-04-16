package org.pcsoft.framework.jnode3d.desktop;

import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.anim.AnimationBase;
import org.pcsoft.framework.jnode3d.camera.PerspectiveLookAtCamera;
import org.pcsoft.framework.jnode3d.node.*;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.PolygonMode;
import org.pcsoft.framework.jnode3d.type.geom.Point3D;
import org.pcsoft.framework.jnode3d.type.transformation.Rotation;
import org.pcsoft.framework.jnode3d.type.transformation.Translation;

public abstract class JNode3DTest {
    private static final Vector3f camPos = new Vector3f(-3f, 2f, -3f);

    protected static void buildScene(JNode3DStandalone standalone) {
        final GroupNode root = new GroupNode();
        root.getChildren().add(buildSphere());
        root.getChildren().add(buildBox());
        root.getChildren().add(buildCircle());
        root.getChildren().add(buildPolygon());
        root.getChildren().add(buildCylinder());
        root.getChildren().add(buildRotationObject());
        final PointLightNode pointLightNode = new PointLightNode();
        pointLightNode.getTransformationList().add(new Translation(new Vector3f(1.25f, 1f, 1.25f)));
        root.getChildren().add(pointLightNode);

        standalone.setRoot(root);
        final PerspectiveLookAtCamera camera = buildCamera();
        standalone.setCamera(camera);

        new AnimationBase() {
            private long counter = 0L;

            @Override
            protected void loop(long timeDelta) {
                counter += timeDelta;
                camera.setPosition(new Vector3f((float) (Math.sin(counter * 0.000000001) * camPos.x), camPos.y, (float) (Math.cos(counter * 0.000000001) * camPos.z)));
            }
        }.start();
    }

    private static Node buildRectangle() {
        final RectangleNode rectangleNode = new RectangleNode();
        rectangleNode.setColorAt(RectangleNode.Points.LeftTopCorner, Color.BLUE);
        rectangleNode.setColorAt(RectangleNode.Points.RightTopCorner, Color.RED);
        rectangleNode.setColorAt(RectangleNode.Points.LeftBottomCorner, Color.GREEN);
        rectangleNode.setColorAt(RectangleNode.Points.RightBottomCorner, Color.YELLOW);

        return rectangleNode;
    }

    private static Node buildTriangle() {
        final TriangleNode triangleNode = new TriangleNode();
        triangleNode.setColorAt(TriangleNode.Points.Top, Color.BLUE);
        triangleNode.setColorAt(TriangleNode.Points.LeftCorner, Color.RED);
        triangleNode.setColorAt(TriangleNode.Points.RightCorner, Color.GREEN);

        return triangleNode;
    }

    private static Node buildBox() {
        final BoxNode boxNode = new BoxNode();
        boxNode.setColorAt(BoxNode.Points.TopLeftFront, Color.BLUE);
        boxNode.setColorAt(BoxNode.Points.TopRightFront, Color.RED);
        boxNode.setColorAt(BoxNode.Points.TopLeftBack, Color.GREEN);
        boxNode.setColorAt(BoxNode.Points.TopRightBack, Color.YELLOW);
        boxNode.setColorAt(BoxNode.Points.BottomLeftFront, Color.YELLOW);
        boxNode.setColorAt(BoxNode.Points.BottomRightFront, Color.GREEN);
        boxNode.setColorAt(BoxNode.Points.BottomLeftBack, Color.RED);
        boxNode.setColorAt(BoxNode.Points.BottomRightBack, Color.BLUE);
        boxNode.getTransformationList().add(new Translation(-0.5f, 0f, -0.5f));
        boxNode.getTransformationList().add(new Rotation(40f));
        //final SnowMaterial snowMaterial = new SnowMaterial();
        //boxNode.setMaterial(snowMaterial);

        return boxNode;
    }

    private static Node buildSphere() {
        final SphereNode sphereNode = new SphereNode(1f);
        sphereNode.setPolygonMode(PolygonMode.Line);

        return sphereNode;
    }

    private static Node buildCircle() {
        final CircleNode circleNode = new CircleNode(1.5f);
        circleNode.setColorAt(CircleNode.Points.Center, Color.RED);
        circleNode.setColorAt(CircleNode.Points.Border, Color.GREEN);

        return circleNode;
    }

    private static Node buildPolygon() {
        final PolygonNode polygonNode = new PolygonNode(new Point3D(-1f, -0.3f, -1f), new Point3D(1f, -0.5f, -1f), new Point3D(1.5f, 0f, 0f),
                new Point3D(-0.5f, -0.5f, 0.5f), new Point3D(-0.8f, -0.1f, -0.5f));
        polygonNode.setColorAt(PolygonNode.Points.Body, Color.TURKEY);
        polygonNode.getTransformationList().add(new Translation(0f, 1.5f, 0f));

        return polygonNode;
    }

    private static Node buildCylinder() {
        final CylinderNode cylinderNode = new CylinderNode();
        cylinderNode.setTopRadius(0.1f);
        cylinderNode.setColorAt(CylinderNode.Points.Top, Color.GREEN);
        cylinderNode.setColorAt(CylinderNode.Points.Bottom, Color.RED);
        cylinderNode.setColorAt(CylinderNode.Points.TopCircleCenter, Color.YELLOW);
        cylinderNode.setColorAt(CylinderNode.Points.BottomCircleCenter, Color.BLUE);
        cylinderNode.getTransformationList().add(new Translation(0.5f, 0f, 0.5f));

        return cylinderNode;
    }

    private static Node buildRotationObject() {
        final RotationObjectNode rotationObjectNode = new RotationObjectNode(x -> (float) (1d / 8d * Math.pow(x, 3d) - 3d / 2d * Math.pow(x, 2d) + 4d * x) * 0.33f,
                new RotationObjectNode.FunctionRange(0.0f, 8.0f), 2f);
        rotationObjectNode.setColorAt(RotationObjectNode.Points.Body, Color.BLUE);
        rotationObjectNode.setColorAt(RotationObjectNode.Points.TopCircleCenter, Color.RED);
        rotationObjectNode.setColorAt(RotationObjectNode.Points.BottomCircleCenter, Color.GREEN);

        return rotationObjectNode;
    }

    private static PerspectiveLookAtCamera buildCamera() {
        final PerspectiveLookAtCamera camera = new PerspectiveLookAtCamera();
        camera.setPosition(camPos);

        return camera;
    }
}
