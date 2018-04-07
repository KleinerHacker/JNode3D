package org.pcsoft.framework.jnode3d.shader;

public final class OpacityShaderInstance extends ShaderInstance<OpacityShader> {
    @ShaderProperty
    private float opacity = 1f;

    OpacityShaderInstance(OpacityShader shader) {
        super(shader);
    }

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }
}
