package org.pcsoft.framework.jnode3d.internal.manager;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.pcsoft.framework.jnode3d.ogl.OGL;
import org.pcsoft.framework.jnode3d.shader.Shader;
import org.pcsoft.framework.jnode3d.type.ShaderType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public final class ShaderManager implements Manager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShaderManager.class);
    private static final ShaderManager instance = new ShaderManager();

    public static ShaderManager getInstance() {
        return instance;
    }

    private final Map<Shader, ShaderIdentifier> shaderIdentifierMap = new HashMap<>();
    private boolean initialized = false;

    private OGL ogl;

    private ShaderManager() {
    }

    @Override
    public void initialize(OGL ogl) {
        LOGGER.info("Initialize Shader Manager");

        for (final Shader shader : shaderIdentifierMap.keySet()) {
            if (shaderIdentifierMap.get(shader) != null)
                continue;

            final ShaderIdentifier shaderIdentifier = buildShader(ogl, shader);

            shaderIdentifierMap.put(shader, shaderIdentifier);
        }

        this.ogl = ogl;
        this.initialized = true;
    }

    @Override
    public void destroy(OGL ogl) {
        LOGGER.info("Destroy Shader Manager");

        for (final Shader shader : new HashSet<>(shaderIdentifierMap.keySet())) {
            final ShaderIdentifier shaderIdentifier = shaderIdentifierMap.get(shader);
            if (shaderIdentifier == null)
                continue;

            ogl.glDeleteProgram(shaderIdentifier.getProgramId());
            ogl.glDeleteProgramPipeline(shaderIdentifier.getPipelineId());

            shaderIdentifierMap.put(shader, null);
        }

        this.ogl = null;
        this.initialized = false;
    }

    @Override
    public boolean isInitialized() {
        return initialized && ogl != null;
    }

    public void registerShader(Shader shader) {
        LOGGER.debug("Register new shader: " + shader.getClass().getName() + " (initialized: " + isInitialized() + ")");

        if (isInitialized()) {
            final ShaderIdentifier shaderIdentifier = buildShader(ogl, shader);
            shaderIdentifierMap.put(shader, shaderIdentifier);
        } else {
            shaderIdentifierMap.put(shader, null);
        }
    }

    public ShaderIdentifier getShaderIdentifier(Shader shader) {
        if (!isInitialized())
            throw new IllegalStateException("System not initialized yet");
        if (!shaderIdentifierMap.containsKey(shader))
            throw new IllegalArgumentException("Unable to find shader!");

        return shaderIdentifierMap.get(shader);
    }

    private ShaderIdentifier buildShader(OGL ogl, Shader shader) {
        LOGGER.debug("Build shader: " + shader.getClass().getName());

        final int fragId = ogl.glCreateShader(ShaderType.FragmentShader, shader.getFragmentShader());
        final String fragLog = ogl.glShaderLog(fragId);
        if (StringUtils.isEmpty(fragLog)) {
            LOGGER.info("Fragment Shader of " + shader.getClass().getName() + " was compiled successfully");
        } else {
            LOGGER.warn("Fragment Shader of " + shader.getClass().getName() + " contains warnings and/or errors:" + SystemUtils.LINE_SEPARATOR + fragLog);
        }

        final int vertId = ogl.glCreateShader(ShaderType.VertexShader, shader.getVertexShader());
        final String vertLog = ogl.glShaderLog(vertId);
        if (StringUtils.isEmpty(vertLog)) {
            LOGGER.info("Vertex Shader of " + shader.getClass().getName() + " was compiled successfully");
        } else {
            LOGGER.warn("Vertex Shader of " + shader.getClass().getName() + " contains warnings and/or errors:" + SystemUtils.LINE_SEPARATOR + vertLog);
        }

        final int progId = ogl.glCreateProgram(vertId, fragId);
        final String progLog = ogl.glProgramLog(progId);
        if (StringUtils.isEmpty(progLog)) {
            LOGGER.info("Program of shader " + shader.getClass().getName() + " was successfully created");
        } else {
            LOGGER.warn("Program of shader " + shader.getClass().getName() + " contains warnings and/or errors:" + SystemUtils.LINE_SEPARATOR + progLog);
        }

        ogl.glDeleteShader(fragId);
        ogl.glDeleteShader(vertId);

        final int pipeId = ogl.glCreateProgramPipeline(
                new OGL.ShaderProgramReference(progId, OGL.GL_VERTEX_SHADER_BIT | OGL.GL_FRAGMENT_SHADER_BIT)
        );

        return new ShaderIdentifier(pipeId, progId);
    }

    public static final class ShaderIdentifier {
        private final int pipelineId;
        private final int programId;

        public ShaderIdentifier(int pipelineId, int programId) {
            this.pipelineId = pipelineId;
            this.programId = programId;
        }

        public int getPipelineId() {
            return pipelineId;
        }

        public int getProgramId() {
            return programId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ShaderIdentifier that = (ShaderIdentifier) o;
            return pipelineId == that.pipelineId &&
                    programId == that.programId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(pipelineId, programId);
        }
    }
}
