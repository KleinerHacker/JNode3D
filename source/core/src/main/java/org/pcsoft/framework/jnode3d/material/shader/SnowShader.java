package org.pcsoft.framework.jnode3d.material.shader;

import org.pcsoft.framework.jnode3d.material.Material;

@ShaderDescriptor(fragmentResource = "/shader/effect/snow.frag", vertexResource = "/shader/effect/snow.vert",
        fragmentMain = "snow_fs", vertexMain = "snow_vs")
public final class SnowShader extends Shader {
    private static final String SNOW_COLORED = "snow_Colored";
    private static final String SNOW_R_MOD = "snow_Seed";

    @ShaderProperty(name = SNOW_COLORED)
    private boolean colored = true;
    @ShaderProperty(name = SNOW_R_MOD)
    private float seed = -15.4457f;

    public SnowShader(Material assignedMaterial) {
        super(assignedMaterial);
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
