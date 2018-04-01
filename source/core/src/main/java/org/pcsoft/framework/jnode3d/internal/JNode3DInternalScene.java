package org.pcsoft.framework.jnode3d.internal;

import org.joml.Matrix4f;
import org.pcsoft.framework.jnode3d.JNode3DScene;
import org.pcsoft.framework.jnode3d.node.Camera;
import org.pcsoft.framework.jnode3d.node.Node3D;
import org.pcsoft.framework.jnode3d.node.Renderable3D;
import org.pcsoft.framework.jnode3d.type.Color;

public final class JNode3DInternalScene implements JNode3DScene {
    private Node3D root;
    private Color backColor = Color.BLACK;
    private Camera camera;

    private final GL gl;
    private boolean initialized = false;
    private float counter = 0f;

    public JNode3DInternalScene(GL gl) {
        this.gl = gl;
    }

    @Override
    public Node3D getRoot() {
        return root;
    }

    @Override
    public void setRoot(Node3D root) {
        this.root = root;
    }

    @Override
    public Color getBackColor() {
        return backColor;
    }

    @Override
    public void setBackColor(Color color) {
        this.backColor = color;
    }

    @Override
    public Camera getCamera() {
        return camera;
    }

    @Override
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void initialize() {
        if (initialized)
            throw new IllegalStateException("Already initialized");

        initialized = true;
    }

    public void loop() {
        counter += 0.1f;

        // Set the clear color
        gl.glClearColor(backColor.getR(), backColor.getG(), backColor.getB(), backColor.getA());

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT | GL.GL_STENCIL_BUFFER_BIT);

        final Matrix4f matrix4f = new Matrix4f()
                .perspective((float) Math.toRadians(45.0), 1f, 0.001f, 100.0f)
                .lookAt((float) Math.sin(counter) * 10f, -10f, (float) Math.cos(counter) * 10f, 0f, 0f, 0f, 0f, 1f, 0f);
        gl.glLoadMatrix(matrix4f);

        renderChildren(root);
    }

    private void renderChildren(Node3D root) {
        if (root == null)
            return;

        if (root instanceof Renderable3D) {
            ((Renderable3D) root).render(gl);
        }

        for (final Node3D child : root.getChildren()) {
            if (!(child instanceof Renderable3D))
                continue;

            renderChildren(child);
        }
    }
}
