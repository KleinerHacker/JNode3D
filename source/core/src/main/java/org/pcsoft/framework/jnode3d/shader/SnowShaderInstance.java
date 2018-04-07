package org.pcsoft.framework.jnode3d.shader;

public final class SnowShaderInstance extends ShaderInstance<SnowShader> {
    @ShaderProperty(name = "snow_colored")
    private boolean colored = true;
    @ShaderProperty(name = "snow_r_mod")
    private float seed = -15.4457f;

    SnowShaderInstance(SnowShader shader) {
        super(shader);
    }

    public boolean isColored() {
        return colored;
    }

    public void setColored(boolean colored) {
        this.colored = colored;
    }

    public float getSeed() {
        return seed;
    }

    public void setSeed(float seed) {
        this.seed = seed;
    }

    @Override
    public String toString() {
        return "SnowShaderInstance{" +
                "colored=" + colored +
                ", seed=" + seed +
                "} " + super.toString();
    }
}
