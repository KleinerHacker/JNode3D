package org.pcsoft.framework.jnode3d.shader;

public abstract class ShaderInstance<T extends Shader> {
    private final T shader;

    protected ShaderInstance(T shader) {
        this.shader = shader;
    }

    public T getShader() {
        return shader;
    }
}
