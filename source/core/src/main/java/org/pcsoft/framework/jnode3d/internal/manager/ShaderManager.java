package org.pcsoft.framework.jnode3d.internal.manager;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.pcsoft.framework.jnode3d.internal.ogl.GLFactory;
import org.pcsoft.framework.jnode3d.internal.ogl.OpenGL;
import org.pcsoft.framework.jnode3d.shader.ShaderInstance;
import org.pcsoft.framework.jnode3d.type.ShaderType;
import org.pcsoft.framework.jnode3d.type.collection.ObservableCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public final class ShaderManager implements Manager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShaderManager.class);
    private static final ShaderManager instance = new ShaderManager();

    public static ShaderManager getInstance() {
        return instance;
    }

    private final Map<ObservableCollection<ShaderInstance>, ShaderIdentifier> shaderIdentifierMap = new HashMap<>();
    private boolean initialized = false;

    private final ShaderUpdateListener shaderUpdateListener = new ShaderUpdateListener();

    private ShaderManager() {
    }

    @Override
    public void initialize() {
        LOGGER.info("Initialize Shader Manager");

        for (final ObservableCollection<ShaderInstance> shaderCollection : shaderIdentifierMap.keySet()) {
            final ShaderIdentifier shaderIdentifier = buildShader(shaderCollection, false);
            if (shaderIdentifier == null)
                continue;

            shaderIdentifierMap.put(shaderCollection, shaderIdentifier);
        }

        this.initialized = true;
    }

    @Override
    public void destroy() {
        LOGGER.info("Destroy Shader Manager");

        for (final ObservableCollection<ShaderInstance> shaderCollection : new HashSet<>(shaderIdentifierMap.keySet())) {
            if (!deleteShader(shaderCollection))
                continue;

            shaderIdentifierMap.put(shaderCollection, null);
        }

        this.initialized = false;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    public void registerShaderCollection(ObservableCollection<ShaderInstance> shaderCollection) {
        LOGGER.debug("Register shader (initialized: " + isInitialized() + ")");

        if (isInitialized()) {
            final ShaderIdentifier shaderIdentifier = buildShader(shaderCollection, true);
            shaderIdentifierMap.put(shaderCollection, shaderIdentifier);
        } else {
            shaderIdentifierMap.put(shaderCollection, null);
        }

        shaderCollection.addChangeListener(shaderUpdateListener);
    }

    public void unregisterShaderCollection(ObservableCollection<ShaderInstance> shaderCollection) {
        LOGGER.debug("Unregister shader (initialized: " + isInitialized() + ")");

        if (isInitialized()) {
            deleteShader(shaderCollection);
        }
        shaderIdentifierMap.remove(shaderCollection);
        shaderCollection.removeChangeListener(shaderUpdateListener);
    }

    public ShaderIdentifier getShaderIdentifier(ObservableCollection<ShaderInstance> shaderInstanceObservableCollection) {
        if (!isInitialized())
            throw new IllegalStateException("System not initialized yet");
        if (!shaderIdentifierMap.containsKey(shaderInstanceObservableCollection))
            throw new IllegalArgumentException("Unable to find shaderInstance!");

        return shaderIdentifierMap.get(shaderInstanceObservableCollection);
    }

    private ShaderIdentifier buildShader(ObservableCollection<ShaderInstance> shaderCollection, boolean overwrite) {
        if (!overwrite && shaderIdentifierMap.get(shaderCollection) != null)
            return null;

        LOGGER.debug("Build shader: " + shaderCollection.getClass().getName());
        final OpenGL ogl = GLFactory.getOpenGL();

        final int fragId = buildShaderOfType(ShaderType.FragmentShader, shaderCollection, ogl);
        final int vertId = buildShaderOfType(ShaderType.VertexShader, shaderCollection, ogl);

        final int progId = ogl.glCreateProgram(vertId, fragId);
        final String progLog = ogl.glProgramLog(progId);
        if (StringUtils.isEmpty(progLog)) {
            LOGGER.info("Program of shader " + shaderCollection.getClass().getName() + " was successfully created");
        } else {
            LOGGER.warn("Program of shader " + shaderCollection.getClass().getName() + " contains warnings and/or errors:" + SystemUtils.LINE_SEPARATOR + progLog);
        }

        ogl.glDeleteShader(fragId);
        ogl.glDeleteShader(vertId);

        return new ShaderIdentifier(progId);
    }

    private int buildShaderOfType(ShaderType shaderType, ObservableCollection<ShaderInstance> shaderCollection, OpenGL ogl) {
        final List<String> scriptList = new ArrayList<>();
        final StringBuilder mainMethodCallString = new StringBuilder();
        for (final ShaderInstance shaderInstance : shaderCollection) {
            switch (shaderType) {
                case FragmentShader:
                    if (!shaderInstance.getShader().hasFragmentShader())
                        continue;
                    scriptList.add(shaderInstance.getShader().getFragmentShader());
                    mainMethodCallString.append(shaderInstance.getShader().getDescriptor().fragmentMain()).append("();");
                    break;
                case VertexShader:
                    if (!shaderInstance.getShader().hasVertexShader())
                        continue;
                    scriptList.add(shaderInstance.getShader().getVertexShader());
                    mainMethodCallString.append("gl_Position = ").append(shaderInstance.getShader().getDescriptor().vertexMain()).append("(gl_Position);");
                    break;
                default:
                    throw new RuntimeException();
            }
        }

        switch (shaderType) {
            case FragmentShader:
                scriptList.add("void main() { " + mainMethodCallString.toString() + " }");
                break;
            case VertexShader:
                scriptList.add("void main() { gl_Position = ftransform(); " + mainMethodCallString.toString() + " }");
                break;
            default:
                throw new RuntimeException();
        }

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Script Generation of " + shaderType.name() + ": " + SystemUtils.LINE_SEPARATOR +
                    StringUtils.join(scriptList, SystemUtils.LINE_SEPARATOR + StringUtils.repeat("*", 50) + SystemUtils.LINE_SEPARATOR));
        }

        final int shaderId = ogl.glCreateShader(shaderType, scriptList.toArray(new String[scriptList.size()]));
        final String shaderLog = ogl.glShaderLog(shaderId);
        if (StringUtils.isEmpty(shaderLog)) {
            LOGGER.info(shaderType.name() + " was compiled successfully");
        } else {
            LOGGER.warn(shaderType.name() + " contains warnings and/or errors:" + SystemUtils.LINE_SEPARATOR + shaderLog);
        }
        return shaderId;
    }

    private boolean deleteShader(ObservableCollection<ShaderInstance> shader) {
        final ShaderIdentifier shaderIdentifier = shaderIdentifierMap.get(shader);
        if (shaderIdentifier == null)
            return false;

        LOGGER.debug("Delete shader " + shader.getClass().getName());
        final OpenGL ogl = GLFactory.getOpenGL();

        ogl.glDeleteProgram(shaderIdentifier.getProgramId());

        return true;
    }

    //<editor-fold desc="Identifier Class">
    public static final class ShaderIdentifier {
        private final int programId;

        public ShaderIdentifier(int programId) {
            this.programId = programId;
        }

        public int getProgramId() {
            return programId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ShaderIdentifier that = (ShaderIdentifier) o;
            return programId == that.programId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(programId);
        }
    }
    //</editor-fold>

    private final class ShaderUpdateListener implements ObservableCollection.ChangeListener<ShaderInstance> {
        @Override
        public void onChanged(ObservableCollection<ShaderInstance> collection) {
            if (!shaderIdentifierMap.containsKey(collection))
                return;
            if (!isInitialized())
                return;

            deleteShader(collection);
            buildShader(collection, true);
        }
    }
}
