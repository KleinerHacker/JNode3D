package org.pcsoft.framework.jnode3d.shader;

@ShaderDescriptor(fragmentMain = "snow_fs", vertexMain = "snow_vs")
public final class SnowShader extends Shader {
    private static final String SNOW_COLORED = "snow_colored";
    private static final String SNOW_R_MOD = "snow_r_mod";

    @ShaderProperty(name = SNOW_COLORED)
    private boolean colored = true;
    @ShaderProperty(name = SNOW_R_MOD)
    private float seed = -15.4457f;

    public SnowShader() {
        super(loadShader(SnowShader.class.getResourceAsStream("/shader/effect/snow.vert")),
                loadShader(SnowShader.class.getResourceAsStream("/shader/effect/snow.frag")));
    }

    public boolean isColored() {
        return colored;
    }

    public void setColored(boolean colored) {
        this.colored = colored;
        updateUniformValue(SNOW_COLORED);
    }

    public float getSeed() {
        return seed;
    }

    public void setSeed(float seed) {
        this.seed = seed;
        updateUniformValue(SNOW_R_MOD);
    }
}
