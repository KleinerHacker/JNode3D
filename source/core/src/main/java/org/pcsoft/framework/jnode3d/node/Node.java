package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.transformation.Transformation;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {
    private final List<Transformation> transformationList = new ArrayList<>();
    private final List<Node> children = new ArrayList<>();

    public List<Transformation> getTransformationList() {
        return transformationList;
    }

    public List<Node> getChildren() {
        return children;
    }
}
