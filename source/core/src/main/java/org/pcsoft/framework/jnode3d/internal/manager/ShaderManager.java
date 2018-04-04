package org.pcsoft.framework.jnode3d.internal.manager;

import org.pcsoft.framework.jnode3d.ogl.OGL;
import org.pcsoft.framework.jnode3d.shader.Shader;
import org.pcsoft.framework.jnode3d.type.ShaderType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public final class ShaderManager implements Manager {
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
        for (final Shader shader : new HashSet<>(shaderIdentifierMap.keySet())) {
            final ShaderIdentifier shaderIdentifier = shaderIdentifierMap.get(shader);
            if (shaderIdentifier == null)
                continue;

            ogl.glDeleteProgram(shaderIdentifier.getProgramId());
            ogl.glDeleteShader(shaderIdentifier.getFragmentShaderId());
            ogl.glDeleteShader(shaderIdentifier.getVertexShaderId());

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
        if (isInitialized()) {
            final ShaderIdentifier shaderIdentifier = buildShader(ogl, shader);
            shaderIdentifierMap.put(shader, shaderIdentifier);
        } else {
            shaderIdentifierMap.put(shader, null);
        }
    }

    public int getShaderIdentifier(Shader shader) {
        if (!isInitialized())
            throw new IllegalStateException("System not initialized yet");
        if (!shaderIdentifierMap.containsKey(shader))
            throw new IllegalArgumentException("Unable to find shader!");

        return shaderIdentifierMap.get(shader).getProgramId();
    }

    private ShaderIdentifier buildShader(OGL ogl, Shader shader) {
        final int fragId = ogl.glCreateShader(ShaderType.FragmentShader, shader.getFragmentShader());
        final int vertId = ogl.glCreateShader(ShaderType.VertexShader, shader.getVertexShader());
        final int progId = ogl.glCreateProgram(vertId, fragId);
        return new ShaderIdentifier(fragId, vertId, progId);
    }

    private static final class ShaderIdentifier {
        private final int fragmentShaderId, vertexShaderId;
        private final int programId;

        public ShaderIdentifier(int fragmentShaderId, int vertexShaderId, int programId) {
            this.fragmentShaderId = fragmentShaderId;
            this.vertexShaderId = vertexShaderId;
            this.programId = programId;
        }

        public int getFragmentShaderId() {
            return fragmentShaderId;
        }

        public int getVertexShaderId() {
            return vertexShaderId;
        }

        public int getProgramId() {
            return programId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ShaderIdentifier that = (ShaderIdentifier) o;
            return fragmentShaderId == that.fragmentShaderId &&
                    vertexShaderId == that.vertexShaderId &&
                    programId == that.programId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(fragmentShaderId, vertexShaderId, programId);
        }
    }
}
