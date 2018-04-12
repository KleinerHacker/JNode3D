package org.pcsoft.framework.jnode3d;

import org.junit.Test;
import org.pcsoft.framework.jnode3d.camera.PerspectiveLookAtCamera;
import org.pcsoft.framework.jnode3d.internal.JNode3DInternalScene;
import org.pcsoft.framework.jnode3d.node.BoxNode;
import org.pcsoft.framework.jnode3d.node.GroupNode;
import org.pcsoft.framework.jnode3d.node.PointLightNode;

public class SceneTest extends PseudoGLTest {

    @Test
    public void test() {
        scene.setCamera(new PerspectiveLookAtCamera());

        final GroupNode groupNode = new GroupNode();
        groupNode.getChildren().add(new BoxNode());
        groupNode.getChildren().add(new PointLightNode());
        scene.setRoot(groupNode);

        ((JNode3DInternalScene)scene).loop();
    }

}
