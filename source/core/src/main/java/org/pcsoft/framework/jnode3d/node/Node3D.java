package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.transformation.Transformation;

import java.util.ArrayList;
import java.util.List;

public abstract class Node3D {
    private final List<Transformation> transformationList = new ArrayList<>();
    private final List<Node3D> children = new ArrayList<>();

    public List<Transformation> getTransformationList() {
        return transformationList;
    }

    public List<Node3D> getChildren() {
        return children;
    }
}
