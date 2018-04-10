package org.pcsoft.framework.jnode3d.internal;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.JNode3DScene;
import org.pcsoft.framework.jnode3d.anim.AnimationBase;
import org.pcsoft.framework.jnode3d.camera.Camera;
import org.pcsoft.framework.jnode3d.camera.OrthographicCamera;
import org.pcsoft.framework.jnode3d.config.JNode3DConfiguration;
import org.pcsoft.framework.jnode3d.internal.handler.JNode3DHandler;
import org.pcsoft.framework.jnode3d.internal.manager.AnimationManager;
import org.pcsoft.framework.jnode3d.internal.manager.ShaderManager;
import org.pcsoft.framework.jnode3d.internal.manager.TextureManager;
import org.pcsoft.framework.jnode3d.ogl.GLFactory;
import org.pcsoft.framework.jnode3d.internal.shader.AmbientLightShader;
import org.pcsoft.framework.jnode3d.internal.shader.DirectionalLightShader;
import org.pcsoft.framework.jnode3d.node.Node;
import org.pcsoft.framework.jnode3d.ogl.OpenGL;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.MatrixMode;

public final class JNode3DInternalScene implements JNode3DScene {
    private Node root;
    private Color backColor = Color.BLACK;
    private Camera camera = new OrthographicCamera();
    private int width, height;

    private final JNode3DConfiguration configuration;
    private boolean initialized = false;

    private final AmbientLightShader ambientLightShader = new AmbientLightShader();
    private final DirectionalLightShader directionalLightShader = new DirectionalLightShader();

    public JNode3DInternalScene(JNode3DConfiguration configuration, int width, int height) {
        this.configuration = configuration;
        this.width = width;
        this.height = height;
    }

    @Override
    public Node getRoot() {
        return root;
    }

    @Override
    public void setRoot(Node root) {
        if (this.root != null) {
            this.root.setScene(null);
        }

        this.root = root;

        if (this.root != null) {
            this.root.setScene(this);
        }
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
    public Color getAmbientLightColor() {
        return ambientLightShader.getColor();
    }

    @Override
    public void setAmbientLightColor(Color color) {
        ambientLightShader.setColor(color);
        ShaderManager.getInstance().updateGlobalUniformValues(root, AmbientLightShader.AMBI_LIGHT_COLOR);
    }

    @Override
    public float getAmbientLightPower() {
        return ambientLightShader.getPower();
    }

    @Override
    public void setAmbientLightPower(float value) {
        ambientLightShader.setPower(value);
        ShaderManager.getInstance().updateGlobalUniformValues(root, AmbientLightShader.AMBI_LIGHT_POWER);
    }

    @Override
    public Vector3f getDirectionalLightDirection() {
        return directionalLightShader.getDirection();
    }

    @Override
    public void setDirectionalLightDirection(Vector3f direction) {
        directionalLightShader.setDirection(direction);
        ShaderManager.getInstance().updateGlobalUniformValues(root, DirectionalLightShader.DIR_LIGHT_DIRECTION);
    }

    @Override
    public Color getDirectionalLightColor() {
        return directionalLightShader.getColor();
    }

    @Override
    public void setDirectionalLightColor(Color color) {
        directionalLightShader.setColor(color);
        ShaderManager.getInstance().updateGlobalUniformValues(root, DirectionalLightShader.DIR_LIGHT_COLOR);
    }

    @Override
    public float getDirectionalLightPower() {
        return directionalLightShader.getPower();
    }

    @Override
    public void setDirectionalLightPower(float value) {
        directionalLightShader.setPower(value);
        ShaderManager.getInstance().updateGlobalUniformValues(root, DirectionalLightShader.DIR_LIGHT_POWER);
    }

    //<editor-fold desc="Internal use only">
    public AmbientLightShader getAmbientLightShader() {
        return ambientLightShader;
    }

    public DirectionalLightShader getDirectionalLightShader() {
        return directionalLightShader;
    }
    //</editor-fold>

    @Override
    public JNode3DConfiguration getConfiguration() {
        return configuration;
    }

    public void initialize() {
        if (isInitialized())
            throw new IllegalStateException("Already initialized");

        ShaderManager.getInstance().initialize();
        TextureManager.getInstance().initialize();

        this.initialized = true;
    }

    public void loop() {
        if (!isInitialized())
            throw new IllegalStateException("Not initialized yet");

        for (final AnimationBase animation : AnimationManager.getInstance().getAnimationList()) {
            animation.callLoop();
        }

        final OpenGL ogl = GLFactory.getOpenGL();

        camera.apply(ogl, width, height);

        ogl.glMatrixMode(MatrixMode.ModelView);
        ogl.glLoadIdentity();

        // Set the clear color
        ogl.glClear(backColor.getR(), backColor.getG(), backColor.getB(), backColor.getA(),
                OpenGL.GL_COLOR_BUFFER_BIT | OpenGL.GL_DEPTH_BUFFER_BIT | OpenGL.GL_STENCIL_BUFFER_BIT);

        JNode3DHandler.handleNode(root, new Matrix4f().identity(), ogl, configuration);
    }

    public void destroy() {
        if (!isInitialized())
            throw new IllegalStateException("Not initialized yet");

        ShaderManager.getInstance().destroy();
        TextureManager.getInstance().destroy();

        this.initialized = false;
    }

    public boolean isInitialized() {
        return initialized;
    }
}
