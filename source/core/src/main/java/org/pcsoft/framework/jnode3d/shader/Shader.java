package org.pcsoft.framework.jnode3d.shader;

import org.apache.commons.io.IOUtils;
import org.pcsoft.framework.jnode3d.internal.manager.ShaderManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public abstract class Shader<T extends ShaderInstance> {
    protected static String loadShader(InputStream in) {
        try {
            return IOUtils.toString(in);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load shader", e);
        }
    }

    private final String vertexShader, fragmentShader;

    protected Shader(String vertexShader, String fragmentShader) {
        this.vertexShader = vertexShader;
        this.fragmentShader = fragmentShader;

        ShaderManager.getInstance().registerShader(this);
    }

    public String getVertexShader() {
        return vertexShader;
    }

    public String getFragmentShader() {
        return fragmentShader;
    }

    public abstract T buildInstance();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shader<?> shader = (Shader<?>) o;
        return Objects.equals(vertexShader, shader.vertexShader) &&
                Objects.equals(fragmentShader, shader.fragmentShader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexShader, fragmentShader);
    }
}
