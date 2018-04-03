package org.pcsoft.framework.jnode3d.node;

import java.util.ArrayList;
import java.util.List;

public class Group extends TransformableNode {
    private final List<Node> children = new ArrayList<>();

    public List<Node> getChildren() {
        return children;
    }
}
