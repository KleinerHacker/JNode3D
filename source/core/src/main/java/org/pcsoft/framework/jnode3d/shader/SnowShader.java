package org.pcsoft.framework.jnode3d.shader;

public final class SnowShader extends Shader<SnowShaderInstance> {
    public static final SnowShader instance = new SnowShader();

    public static SnowShader get() {
        return instance;
    }

    private SnowShader() {
        super(SnowShaderInstance.class, loadShader(SnowShader.class.getResourceAsStream("/shader/snow.vert")),
                loadShader(SnowShader.class.getResourceAsStream("/shader/snow.frag")));
    }

    @Override
    public SnowShaderInstance buildInstance() {
        return new SnowShaderInstance(this);
    }

    public SnowShaderInstance buildInstance(boolean colored, float seed) {
        final SnowShaderInstance instance = buildInstance(colored);
        instance.setSeed(seed);

        return instance;
    }

    public SnowShaderInstance buildInstance(boolean colored) {
        final SnowShaderInstance instance = buildInstance();
        instance.setColored(colored);

        return instance;
    }
}
