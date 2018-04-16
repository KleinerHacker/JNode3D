package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.JNode3DScene;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomNode extends TransformableNode {
    protected final List<Node> children = new ArrayList<>();

    @Override
    public final void setScene(JNode3DScene scene) {
        super.setScene(scene);
        for (final Node node : children) {
            node.setScene(scene);
        }
    }
}
