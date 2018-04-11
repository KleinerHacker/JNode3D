package org.pcsoft.framework.jnode3d.shader;

@ShaderDescriptor(fragmentMain = "snow_fs", vertexMain = "snow_vs")
public final class SnowShader extends EffectShader {
    private static final String SNOW_COLORED = "snow_Colored";
    private static final String SNOW_R_MOD = "snow_Seed";

    @ShaderProperty(name = SNOW_COLORED)
    private boolean colored = true;
    @ShaderProperty(name = SNOW_R_MOD)
    private float seed = -15.4457f;

    public SnowShader() {
        super(loadShader(SnowShader.class.getResourceAsStream("/shader/effect/snow.vert")),
                loadShader(SnowShader.class.getResourceAsStream("/shader/effect/snow.frag")));
    }

    public SnowShader(boolean colored) {
        this();
        this.colored = colored;
    }

    public SnowShader(boolean colored, float seed) {
        this(colored);
        this.seed = seed;
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
