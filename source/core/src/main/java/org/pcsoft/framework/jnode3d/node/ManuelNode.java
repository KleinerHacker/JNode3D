package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.ogl.OGL;

public final class ManuelNode implements Node {
    public interface ProcessCallback {
        void process(OGL ogl);
    }

    private ProcessCallback callback;

    public ManuelNode() {
    }

    public ManuelNode(ProcessCallback callback) {
        this.callback = callback;
    }

    public ProcessCallback getCallback() {
        return callback;
    }

    public void setCallback(ProcessCallback callback) {
        this.callback = callback;
    }
}
