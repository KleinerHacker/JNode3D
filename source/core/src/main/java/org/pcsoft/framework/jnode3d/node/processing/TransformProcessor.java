package org.pcsoft.framework.jnode3d.node.processing;

import org.joml.Matrix4f;
import org.pcsoft.framework.jnode3d.node.TransformableNode;
import org.pcsoft.framework.jnode3d.ogl.OGL;

public interface TransformProcessor<T extends TransformableNode> extends Processor<T> {
    Matrix4f transform(OGL ogl, T node, Matrix4f rootMatrix);
}
