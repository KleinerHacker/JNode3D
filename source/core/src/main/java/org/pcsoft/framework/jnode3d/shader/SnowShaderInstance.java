package org.pcsoft.framework.jnode3d.shader;

public final class SnowShaderInstance extends ShaderInstance<SnowShader> {
    private boolean colored = true;

    SnowShaderInstance(SnowShader shader) {
        super(shader);
    }

    public boolean isColored() {
        return colored;
    }

    public void setColored(boolean colored) {
        this.colored = colored;
    }

    @Override
    public String toString() {
        return "SnowShaderInstance{" +
                "colored=" + colored +
                "} " + super.toString();
    }
}
