package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.type.transformation.Transformation;

import java.util.ArrayList;
import java.util.List;

public abstract class TransformableNode extends Node {
    private final List<Transformation> transformationList = new ArrayList<>();

    public List<Transformation> getTransformationList() {
        return transformationList;
    }
}
