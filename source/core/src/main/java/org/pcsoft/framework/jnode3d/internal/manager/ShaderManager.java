package org.pcsoft.framework.jnode3d.internal.manager;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.material.Material;
import org.pcsoft.framework.jnode3d.material.shader.Shader;
import org.pcsoft.framework.jnode3d.ogl.GLFactory;
import org.pcsoft.framework.jnode3d.ogl.OpenGL;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.ShaderType;
import org.pcsoft.framework.jnode3d.type.reference.ShaderProgramReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public final class ShaderManager implements OpenGLDependendManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShaderManager.class);
    private static final ShaderManager instance = new ShaderManager();

    public static ShaderManager getInstance() {
        return instance;
    }

    private final Map<Material, ShaderProgramReference> shaderProgramMap = new HashMap<>();
    private boolean initialized = false;

    private ShaderManager() {
    }

    //<editor-fold desc="Init / Destroy">
    @Override
    public void initialize() {
        if (initialized)
            throw new IllegalStateException("Already initialized");

        LOGGER.info("Initialize Shader Manager");

        for (final Material material : shaderProgramMap.keySet()) {
            final ShaderProgramReference shaderIdentifier = buildShader(material, false);
            if (shaderIdentifier == null)
                continue;

            shaderProgramMap.put(material, shaderIdentifier);
        }

        this.initialized = true;
    }

    @Override
    public void destroy() {
        if (!initialized)
            throw new IllegalStateException("Not initialized yet");

        LOGGER.info("Destroy Shader Manager");

        for (final Material material : new HashSet<>(shaderProgramMap.keySet())) {
            if (!deleteShader(material))
                continue;

            shaderProgramMap.put(material, null);
        }

        this.initialized = false;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }
    //</editor-fold>

    //<editor-fold desc="Register / Unregister">
    public void registerShaderProgram(Material material) {
        LOGGER.debug("Register shader program for material " + material.getDebugName() + " (initialized: " + isInitialized() + ")");

        if (isInitialized()) {
            final ShaderProgramReference shaderProgramReference = buildShader(material, true);
            shaderProgramMap.put(material, shaderProgramReference);
        } else {
            shaderProgramMap.put(material, null);
        }
    }

    public void unregisterShaderProgram(Material material) {
        LOGGER.debug("Unregister shader program for material " + material.getDebugName() + " (initialized: " + isInitialized() + ")");

        if (initialized) {
            deleteShader(material);
        }
        shaderProgramMap.remove(material);
    }
    //</editor-fold>

    public void updateUniformValues(Material material, String propertyName) {
        if (!isInitialized())
            throw new IllegalStateException("Unable to update uniform values yet: not initialized (material: " + material.getDebugName() + ")");

        final ShaderProgramReference shaderProgramReference = shaderProgramMap.get(material);
        updateUniformValues(material, shaderProgramReference, propertyName);
    }

    private void updateUniformValues(Material material, ShaderProgramReference shaderProgramReference, String propertyName) {
        for (final Shader shader : material.getShaders()) {
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

    public ShaderProgramReference getShaderProgramReference(Material material) {
        if (!shaderProgramMap.containsKey(material))
            throw new IllegalArgumentException("Given material is not registered: " + material.getDebugName());

        return shaderProgramMap.get(material);
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
            } else if (propertyInfo.getType() == Matrix4f.class) {
                success = ogl.glSetProgramVar(shaderProgramReference, propertyInfo.getName(), (Matrix4f) value);
            } else
                throw new IllegalStateException("Not supported shader var type: " + propertyInfo.getType().getName());

            if (!success) {
                LOGGER.warn("Setup variable " + propertyInfo.getName() + " to <" + value + "> failed: not found");
            }
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Unable to access uniform variable field " + propertyInfo.getName() + ": " + propertyInfo.getField());
        }
    }

    private ShaderProgramReference buildShader(Material material, boolean overwrite) {
        if (!overwrite && shaderProgramMap.get(material) != null)
            return null;

        LOGGER.debug("Build shader of material " + material.getClass().getName());
        final OpenGL ogl = GLFactory.getOpenGL();

        final int fragId = buildShaderOfType(ShaderType.FragmentShader, material.getShaders(), ogl);
        final int vertId = buildShaderOfType(ShaderType.VertexShader, material.getShaders(), ogl);

        final ShaderProgramReference progId = ogl.glCreateProgram(vertId, fragId);
        final String progLog = ogl.glProgramLog(progId);
        if (StringUtils.isEmpty(progLog)) {
            LOGGER.info("Program of shader " + material.getClass().getName() + " was successfully created");
        } else {
            LOGGER.warn("Program of shader " + material.getClass().getName() + " contains warnings and/or errors:" + SystemUtils.LINE_SEPARATOR + progLog);
        }

        ogl.glDeleteShader(fragId);
        ogl.glDeleteShader(vertId);

        updateUniformValues(material, progId, null);

        return progId;
    }

    private int buildShaderOfType(ShaderType shaderType, Collection<Shader> shaders, OpenGL ogl) {
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

    private boolean deleteShader(Material material) {
        final ShaderProgramReference shaderIdentifier = shaderProgramMap.get(material);
        if (shaderIdentifier == null)
            return false;

        LOGGER.debug("Delete shader of material " + material.getClass().getName());
        final OpenGL ogl = GLFactory.getOpenGL();

        ogl.glDeleteProgram(shaderIdentifier);

        return true;
    }

    private void rebuildAndUpdateShaderProgram(Material material) {
        deleteShader(material);
        final ShaderProgramReference shaderProgramReference = buildShader(material, true);
        shaderProgramMap.put(material, shaderProgramReference);//Overwrite
    }
    //</editor-fold>
}
