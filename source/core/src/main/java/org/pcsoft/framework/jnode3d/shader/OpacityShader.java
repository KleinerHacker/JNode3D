package org.pcsoft.framework.jnode3d.shader;

@ShaderDescriptor(vertexMain = "", fragmentMain = "opacity_fs")
public final class OpacityShader extends Shader<OpacityShaderInstance> {
    public static final OpacityShader instance = new OpacityShader();

    public static OpacityShader get() {
        return instance;
    }

    private OpacityShader() {
        super(OpacityShaderInstance.class, null, loadShader(OpacityShader.class.getResourceAsStream("/shader/opacity.frag")));
    }

    @Override
    public OpacityShaderInstance buildInstance() {
        return new OpacityShaderInstance(this);
    }

    public OpacityShaderInstance buildInstance(float opacity) {
        final OpacityShaderInstance instance = buildInstance();
        instance.setOpacity(opacity);

        return instance;
    }
}
