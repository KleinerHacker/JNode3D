package org.pcsoft.framework.jnode3d.internal;

import org.apache.commons.lang.ObjectUtils;
import org.joml.Matrix4f;
import org.pcsoft.framework.jnode3d.JNode3DScene;
import org.pcsoft.framework.jnode3d.camera.Camera;
import org.pcsoft.framework.jnode3d.camera.OrthographicCamera;
import org.pcsoft.framework.jnode3d.camera.PerspectiveCamera;
import org.pcsoft.framework.jnode3d.camera.PerspectiveLookAtCamera;
import org.pcsoft.framework.jnode3d.node.Node;
import org.pcsoft.framework.jnode3d.node.Renderable;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.Rectangle;

public final class JNode3DInternalScene implements JNode3DScene {
    private Node root;
    private Color backColor = Color.BLACK;
    private Camera camera = new OrthographicCamera();
    private int width, height;

    private final GL gl;
    private boolean initialized = false;
    private float counter = 0f;

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
        counter += 0.01f;

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        if (camera instanceof OrthographicCamera) {
            gl.glOrtho((Rectangle) ObjectUtils.defaultIfNull(((OrthographicCamera) camera).getViewport(), new Rectangle(0, 0, width, height)),
                    camera.getNear(), camera.getFar());
        } else if (camera instanceof PerspectiveCamera) {
            gl.glFrustum(new Rectangle(0, 0, width, height), -1, 1);
        } else
            throw new IllegalStateException("No camera was set for scene");
        gl.glMatrixMode(GL.GL_MODELVIEW);
        if (camera instanceof PerspectiveCamera) {
            final PerspectiveCamera perspectiveCamera = (PerspectiveCamera) this.camera;

            Matrix4f matrix4f = new Matrix4f();
            matrix4f.perspective((float) Math.toRadians(perspectiveCamera.getAngle()), perspectiveCamera.getAspect(), camera.getNear(), camera.getFar());
            if (perspectiveCamera instanceof PerspectiveLookAtCamera) {
                matrix4f.lookAt(perspectiveCamera.getPosition(), ((PerspectiveLookAtCamera) perspectiveCamera).getLookAt(), ((PerspectiveLookAtCamera) perspectiveCamera).getUpDirection());
            }
            gl.glLoadMatrix(matrix4f);
        }

        // Set the clear color
        gl.glClearColor(backColor.getR(), backColor.getG(), backColor.getB(), backColor.getA());

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT | GL.GL_STENCIL_BUFFER_BIT);

        renderChildren(root);
    }

    private void renderChildren(Node root) {
        if (root == null)
            return;

        if (root instanceof Renderable) {
            ((Renderable) root).render(gl);
        }

        for (final Node child : root.getChildren()) {
            if (!(child instanceof Renderable))
                continue;

            renderChildren(child);
        }
    }
}
