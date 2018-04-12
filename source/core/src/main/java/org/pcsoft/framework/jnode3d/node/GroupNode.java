package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.JNode3DScene;

import java.util.ArrayList;
import java.util.List;

public class GroupNode extends TransformableNode {
    private final List<Node> children = new ArrayList<>();

    public List<Node> getChildren() {
        return children;
    }

    @Override
    public void setScene(JNode3DScene scene) {
        super.setScene(scene);
        for (final Node node : children) {
            node.setScene(scene);
        }
    }
}
