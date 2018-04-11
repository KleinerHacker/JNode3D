package org.pcsoft.framework.jnode3d.shader;

public abstract class EffectShader extends Shader {
    public EffectShader(String vertexShader, String fragmentShader) {
        super(0, vertexShader, fragmentShader);
    }
}
