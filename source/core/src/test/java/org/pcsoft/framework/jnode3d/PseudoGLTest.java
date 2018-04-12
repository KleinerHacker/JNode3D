package org.pcsoft.framework.jnode3d;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.pcsoft.framework.jnode3d.config.JNode3DConfigurationBuilder;
import org.pcsoft.framework.jnode3d.internal.JNode3DInternalScene;
import org.pcsoft.framework.jnode3d.ogl.GLFactory;
import org.pcsoft.framework.jnode3d.type.PseudoGL;

public abstract class PseudoGLTest {
    protected static JNode3DScene scene = new JNode3DInternalScene(JNode3DConfigurationBuilder.create().build(), 800, 600);

    @BeforeClass
    public static void init() {
        GLFactory.initialize(new PseudoGL());
        ((JNode3DInternalScene)scene).initialize();
    }

    @AfterClass
    public static void done() {
        ((JNode3DInternalScene)scene).destroy();
    }
}
