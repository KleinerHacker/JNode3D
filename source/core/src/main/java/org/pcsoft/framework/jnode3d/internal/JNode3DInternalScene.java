package org.pcsoft.framework.jnode3d.internal;

import org.joml.Matrix4f;
import org.pcsoft.framework.jnode3d.JNode3DScene;
import org.pcsoft.framework.jnode3d.camera.Camera;
import org.pcsoft.framework.jnode3d.camera.OrthographicCamera;
import org.pcsoft.framework.jnode3d.node.Node;
import org.pcsoft.framework.jnode3d.node.RenderNode;
import org.pcsoft.framework.jnode3d.node.TransformableNode;
import org.pcsoft.framework.jnode3d.type.Color;

public final class JNode3DInternalScene implements JNode3DScene {
    private Node root;
    private Color backColor = Color.BLACK;
    private Camera camera = new OrthographicCamera();
    private int width, height;

    private final GL gl;
    private boolean initialized = false;

    public JNode3DInternalScene(GL gl, int width, int height) {
        this.gl = gl;
        this.width = width;
        this.height = height;
    }

    @Override
    public Node getRoot() {
        return root;
    }

    @Override
    public void setRoot(Node root) {
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

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    public void initialize() {
        if (initialized)
            throw new IllegalStateException("Already initialized");

        initialized = true;
    }

    public void loop() {
        camera.apply(gl, width, height);

        // Set the clear color
        gl.glClearColor(backColor.getR(), backColor.getG(), backColor.getB(), backColor.getA());
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT | GL.GL_STENCIL_BUFFER_BIT);

        renderChildren(root, new Matrix4f().identity());
    }

    private void renderChildren(Node root, Matrix4f rootMatrix) {
        if (root == null)
            return;

        //Transformation
        final Matrix4f childRootMatrix; //Store for child
        if (root instanceof TransformableNode) {
            childRootMatrix = ((TransformableNode) root).transform(gl, rootMatrix);
        } else {
            childRootMatrix = rootMatrix;
        }

        //Rendering
        if (root instanceof RenderNode) {
            ((RenderNode) root).render(gl);
        }

        for (final Node child : root.getChildren()) {
            if (!(child instanceof RenderNode))
                continue;

            renderChildren(child, childRootMatrix);
        }
    }
}
