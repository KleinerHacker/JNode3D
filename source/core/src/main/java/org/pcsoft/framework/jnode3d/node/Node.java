package org.pcsoft.framework.jnode3d.node;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {
    private final List<Node> children = new ArrayList<>();

    public List<Node> getChildren() {
        return children;
    }
}
