package org.pcsoft.framework.jnode3d.internal.shader;

import org.pcsoft.framework.jnode3d.shader.Shader;
import org.pcsoft.framework.jnode3d.shader.ShaderDescriptor;
import org.pcsoft.framework.jnode3d.shader.ShaderProperty;

@ShaderDescriptor(vertexMain = "base_vs", fragmentMain = "base_fs")
public final class BaseShader extends Shader {
    private static final String BASE_OPACITY = "base_Opacity";
    private static final String BASE_CULL_MODE = "base_CullMode";

    @ShaderProperty(name = BASE_OPACITY)
    private float opacity = 1f;
    @ShaderProperty(name = BASE_CULL_MODE)
    private int cullMode = 3;

    public BaseShader() {
        super(Long.MIN_VALUE, loadShader(BaseShader.class.getResourceAsStream("/shader/base/base.vert")),
                loadShader(BaseShader.class.getResourceAsStream("/shader/base/base.frag")));
    }

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
        updateUniformValue(BASE_OPACITY);
    }

    public int getCullMode() {
        return cullMode;
    }

    public void setCullMode(int cullMode) {
        this.cullMode = cullMode;
        updateUniformValue(BASE_CULL_MODE);
    }
}
