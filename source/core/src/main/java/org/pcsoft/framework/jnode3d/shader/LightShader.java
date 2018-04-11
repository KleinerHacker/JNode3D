package org.pcsoft.framework.jnode3d.shader;

public abstract class LightShader extends Shader {
    public LightShader(long orderValue, String vertexShader, String fragmentShader) {
        super(Long.MAX_VALUE - (100 - orderValue), vertexShader, fragmentShader);
    }
}
