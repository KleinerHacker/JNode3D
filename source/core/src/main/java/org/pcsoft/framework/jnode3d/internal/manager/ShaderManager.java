package org.pcsoft.framework.jnode3d.internal.manager;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.node.Group;
import org.pcsoft.framework.jnode3d.node.Node;
import org.pcsoft.framework.jnode3d.node.RenderableObjectNode;
import org.pcsoft.framework.jnode3d.ogl.GLFactory;
import org.pcsoft.framework.jnode3d.ogl.OpenGL;
import org.pcsoft.framework.jnode3d.shader.Shader;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.ShaderType;
import org.pcsoft.framework.jnode3d.type.reference.ShaderProgramReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public final class ShaderManager implements Manager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShaderManager.class);
    private static final ShaderManager instance = new ShaderManager();

    public static ShaderManager getInstance() {
        return instance;
    }

    private final Map<RenderableObjectNode, ShaderProgramReference> shaderProgramMap = new HashMap<>();
    private boolean initialized = false;

    private final ShaderUpdateListener shaderUpdateListener = new ShaderUpdateListener();
    private final SceneUpdateListener sceneUpdateListener = new SceneUpdateListener();

    private ShaderManager() {
    }

    //<editor-fold desc="Init / Destroy">
    @Override
    public void initialize() {
        LOGGER.info("Initialize Shader Manager");

        for (final RenderableObjectNode node : shaderProgramMap.keySet()) {
            final ShaderProgramReference shaderIdentifier = buildShader(node, false);
            if (shaderIdentifier == null)
                continue;

            shaderProgramMap.put(node, shaderIdentifier);
        }

        this.initialized = true;
    }

    @Override
    public void destroy() {
        LOGGER.info("Destroy Shader Manager");

        for (final RenderableObjectNode node : new HashSet<>(shaderProgramMap.keySet())) {
            if (!deleteShader(node))
                continue;

            shaderProgramMap.put(node, null);
        }

        this.initialized = false;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }
    //</editor-fold>

    //<editor-fold desc="Register / Unregister">
    public void registerShaderCollection(RenderableObjectNode node) {
        LOGGER.debug("Register node (initialized: " + isInitialized() + ")");

        if (isInitialized()) {
            final ShaderProgramReference shaderProgramReference = buildShader(node, true);
            shaderProgramMap.put(node, shaderProgramReference);
        } else {
            shaderProgramMap.put(node, null);
        }

        node.addShaderListChangedListener(shaderUpdateListener);
        node.addSceneChangedListener(sceneUpdateListener);
    }

    public void unregisterShaderCollection(RenderableObjectNode node) {
        LOGGER.debug("Unregister node (initialized: " + isInitialized() + ")");

        if (isInitialized()) {
            deleteShader(node);
        }
        shaderProgramMap.remove(node);
        node.removeListShaderChangedListener(shaderUpdateListener);
        node.removeSceneChangedListener(sceneUpdateListener);
    }
    //</editor-fold>

    public void updateGlobalUniformValues(Node node, String propertyName) {
        if (!isInitialized())
            throw new IllegalStateException("Unable to update uniform values yet: not initialized");

        if (node instanceof RenderableObjectNode) {
            updateUniformValues((RenderableObjectNode) node, propertyName);
        } else if (node instanceof Group) {
            for (final Node child : ((Group) node).getChildren()) {
                updateGlobalUniformValues(child, propertyName);
            }
        }
    }

    public void updateUniformValues(RenderableObjectNode node, String propertyName) {
        if (!isInitialized())
            throw new IllegalStateException("Unable to update uniform values yet: not initialized");

        final ShaderProgramReference shaderProgramReference = shaderProgramMap.get(node);
        updateUniformValues(node, shaderProgramReference, propertyName);
    }

    private void updateUniformValues(RenderableObjectNode node, ShaderProgramReference shaderProgramReference, String propertyName) {
        for (final Shader shader : node.getShaders()) {
            for (final Shader.PropertyInfo propertyInfo : shader.getPropertyInfos()) {
                if (StringUtils.isEmpty(propertyName)) {
                    setupShaderPropertyVariable(shader, shaderProgramReference, propertyInfo);
                } else if (propertyName.equals(propertyInfo.getName())) {
                    setupShaderPropertyVariable(shader, shaderProgramReference, propertyInfo);
                    return;
                }
            }
        }
    }

    public ShaderProgramReference getShaderProgramReference(RenderableObjectNode node) {
        if (!shaderProgramMap.containsKey(node))
            throw new IllegalArgumentException("Unable to find node");

        return shaderProgramMap.get(node);
    }

    //<editor-fold desc="Shader Helper Methods">
    private static void setupShaderPropertyVariable(Shader shader, ShaderProgramReference shaderProgramReference, Shader.PropertyInfo propertyInfo) {
        final OpenGL ogl = GLFactory.getOpenGL();

        try {
            final Object value = propertyInfo.getField().get(shader);
            final boolean success;

            if (propertyInfo.getType() == int.class || propertyInfo.getType() == Integer.class ||
                    propertyInfo.getType() == byte.class || propertyInfo.getType() == Byte.class ||
                    propertyInfo.getType() == short.class || propertyInfo.getType() == Short.class ||
                    propertyInfo.getType() == long.class || propertyInfo.getType() == Long.class) {
                success = ogl.glSetProgramVar(shaderProgramReference, propertyInfo.getName(), (int) value);
            } else if (propertyInfo.getType() == boolean.class || propertyInfo.getType() == Boolean.class) {
                success = ogl.glSetProgramVar(shaderProgramReference, propertyInfo.getName(), (boolean) value);
            } else if (propertyInfo.getType() == float.class || propertyInfo.getType() == Float.class ||
                    propertyInfo.getType() == double.class || propertyInfo.getType() == Double.class) {
                success = ogl.glSetProgramVar(shaderProgramReference, propertyInfo.getName(), (float) value);
            } else if (propertyInfo.getType() == Vector3f.class) {
                success = ogl.glSetProgramVar(shaderProgramReference, propertyInfo.getName(), (Vector3f) value);
            } else if (propertyInfo.getType() == Color.class) {
                success = ogl.glSetProgramVar(shaderProgramReference, propertyInfo.getName(), (Color) value);
            } else
                throw new IllegalStateException("Not supported shader var type: " + propertyInfo.getType().getName());

            if (success) {
                LOGGER.trace("Setup variable " + propertyInfo.getName() + " successfully to <" + value + ">");
            } else {
                LOGGER.warn("Setup variable " + propertyInfo.getName() + " to <" + value + "> failed: not found");
            }
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Unable to access uniform variable field " + propertyInfo.getName() + ": " + propertyInfo.getField());
        }
    }

    private ShaderProgramReference buildShader(RenderableObjectNode node, boolean overwrite) {
        if (!overwrite && shaderProgramMap.get(node) != null)
            return null;
        if (node.getScene() == null)
            return null;

        LOGGER.debug("Build shader of node " + node.getClass().getName());
        final OpenGL ogl = GLFactory.getOpenGL();

        final int fragId = buildShaderOfType(ShaderType.FragmentShader, node.getShaders(), ogl);
        final int vertId = buildShaderOfType(ShaderType.VertexShader, node.getShaders(), ogl);

        final ShaderProgramReference progId = ogl.glCreateProgram(vertId, fragId);
        final String progLog = ogl.glProgramLog(progId);
        if (StringUtils.isEmpty(progLog)) {
            LOGGER.info("Program of shader " + node.getClass().getName() + " was successfully created");
        } else {
            LOGGER.warn("Program of shader " + node.getClass().getName() + " contains warnings and/or errors:" + SystemUtils.LINE_SEPARATOR + progLog);
        }

        ogl.glDeleteShader(fragId);
        ogl.glDeleteShader(vertId);

        updateUniformValues(node, progId, null);

        return progId;
    }

    private int buildShaderOfType(ShaderType shaderType, Shader[] shaders, OpenGL ogl) {
        final List<String> scriptList = new ArrayList<>();
        final StringBuilder mainMethodCallString = new StringBuilder();
        mainMethodCallString.append(SystemUtils.LINE_SEPARATOR).append("\t");
        for (final Shader shader : shaders) {
            switch (shaderType) {
                case FragmentShader:
                    if (!shader.hasFragmentShader())
                        continue;
                    scriptList.add(shader.getFragmentShader());
                    mainMethodCallString.append(shader.getDescriptor().fragmentMain()).append("();");
                    break;
                case VertexShader:
                    if (!shader.hasVertexShader())
                        continue;
                    scriptList.add(shader.getVertexShader());
                    mainMethodCallString.append(shader.getDescriptor().vertexMain()).append("();");
                    break;
                default:
                    throw new RuntimeException();
            }
            mainMethodCallString.append(SystemUtils.LINE_SEPARATOR).append("\t");
        }

        final String fullProgram;
        try {
            switch (shaderType) {
                case FragmentShader:
                    fullProgram = IOUtils.toString(getClass().getResourceAsStream("/shader/template.frag"))
                            .replace("___CONTENT___;", mainMethodCallString.toString())
                            .replace("___METHOD___;", StringUtils.join(scriptList, StringUtils.repeat(SystemUtils.LINE_SEPARATOR, 2)));
                    break;
                case VertexShader:
                    fullProgram = IOUtils.toString(getClass().getResourceAsStream("/shader/template.vert"))
                            .replace("___CONTENT___;", mainMethodCallString.toString())
                            .replace("___METHOD___;", StringUtils.join(scriptList, StringUtils.repeat(SystemUtils.LINE_SEPARATOR, 2)));
                    break;
                default:
                    throw new RuntimeException();
            }
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load base shader!", e);
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Script Generation of " + shaderType.name() + ": " + SystemUtils.LINE_SEPARATOR +
                    fullProgram);
        }

        final int shaderId = ogl.glCreateShader(shaderType, fullProgram);
        final String shaderLog = ogl.glShaderLog(shaderId);
        if (StringUtils.isEmpty(shaderLog)) {
            LOGGER.info(shaderType.name() + " was compiled successfully");
        } else {
            LOGGER.warn(shaderType.name() + " contains warnings and/or errors:" + SystemUtils.LINE_SEPARATOR + shaderLog);
        }
        return shaderId;
    }

    private boolean deleteShader(RenderableObjectNode node) {
        final ShaderProgramReference shaderIdentifier = shaderProgramMap.get(node);
        if (shaderIdentifier == null)
            return false;

        LOGGER.debug("Delete shader of node " + node.getClass().getName());
        final OpenGL ogl = GLFactory.getOpenGL();

        ogl.glDeleteProgram(shaderIdentifier);

        return true;
    }

    private void rebuildAndUpdateShaderProgram(RenderableObjectNode node) {
        deleteShader(node);
        final ShaderProgramReference shaderProgramReference = buildShader(node, true);
        shaderProgramMap.put(node, shaderProgramReference);//Overwrite
    }
    //</editor-fold>

    //<editor-fold desc="Listeners">
    private final class ShaderUpdateListener implements RenderableObjectNode.ChangedListener<RenderableObjectNode> {
        @Override
        public void onChanged(RenderableObjectNode node) {
            if (!shaderProgramMap.containsKey(node))
                return;
            if (!isInitialized())
                return;
            if (node.getScene() == null)
                return;

            rebuildAndUpdateShaderProgram(node);
        }
    }

    private final class SceneUpdateListener implements Node.ChangedListener<Node> {
        @Override
        public void onChanged(Node node) {
            if (!(node instanceof RenderableObjectNode))
                return;
            if (!shaderProgramMap.containsKey(node))
                return;
            if (!isInitialized())
                return;
            if (node.getScene() == null)
                return;

            rebuildAndUpdateShaderProgram((RenderableObjectNode) node);
        }
    }
    //</editor-fold>
}
