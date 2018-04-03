package org.pcsoft.framework.jnode3d.internal;

import org.joml.Matrix4f;
import org.pcsoft.framework.jnode3d.JNode3DScene;
import org.pcsoft.framework.jnode3d.camera.Camera;
import org.pcsoft.framework.jnode3d.camera.OrthographicCamera;
import org.pcsoft.framework.jnode3d.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.node.*;
import org.pcsoft.framework.jnode3d.ogl.OGL;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.MatrixMode;

public final class JNode3DInternalScene implements JNode3DScene {
    private Node root;
    private Color backColor = Color.BLACK;
    private Camera camera = new OrthographicCamera();
    private int width, height;

    private final OGL ogl;
    private final JNode3DConfiguration configuration;
    private boolean initialized = false;

    public JNode3DInternalScene(JNode3DConfiguration configuration, OGL ogl, int width, int height) {
        this.configuration = configuration;
        this.ogl = ogl;
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

    @Override
    public JNode3DConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public OGL getOpenGL() {
        return ogl;
    }

    public void initialize() {
        if (initialized)
            throw new IllegalStateException("Already initialized");

        initialized = true;
    }

    public void loop() {
        camera.apply(ogl, width, height);

        ogl.glMatrixMode(MatrixMode.ModelView);
        ogl.glLoadIdentity();

        // Set the clear color
        ogl.glClear(backColor.getR(), backColor.getG(), backColor.getB(), backColor.getA(),
                OGL.GL_COLOR_BUFFER_BIT | OGL.GL_DEPTH_BUFFER_BIT | OGL.GL_STENCIL_BUFFER_BIT);

        renderChildren(root, new Matrix4f().identity());
    }

    private void renderChildren(Node root, Matrix4f rootMatrix) {
        if (root == null)
            return;

        if (root instanceof ManuelNode) { //Manuel rendering
            ((ManuelNode) root).getCallback().process(ogl);
        } else {
            //Transformation
            final Matrix4f childRootMatrix; //Store for child
            if (root instanceof TransformableNode) {
                childRootMatrix = ((TransformableNode) root).transform(ogl, rootMatrix);
            } else {
                childRootMatrix = rootMatrix;
            }

            //Texture
            if (root instanceof TexturedNode) {
                ((TexturedNode) root).setupTextureAttributes(configuration, ogl);
            }

            //Rendering
            if (root instanceof RenderNode) {
                ((RenderNode) root).render(ogl);
            }

            //Sub elements
            if (root instanceof Group) {
                for (final Node child : ((Group) root).getChildren()) {
                    if (!(child instanceof RenderNode))
                        continue;

                    renderChildren(child, childRootMatrix);
                }
            }
        }
    }
}
