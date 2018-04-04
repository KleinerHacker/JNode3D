package org.pcsoft.framework.jnode3d.internal.manager;

import org.pcsoft.framework.jnode3d.ogl.OGL;

public interface Manager {
    void initialize(OGL ogl);
    void destroy(OGL ogl);

    boolean isInitialized();
}
