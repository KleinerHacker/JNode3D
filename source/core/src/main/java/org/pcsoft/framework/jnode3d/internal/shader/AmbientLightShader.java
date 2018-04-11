package org.pcsoft.framework.jnode3d.internal.shader;

import org.pcsoft.framework.jnode3d.shader.LightShader;
import org.pcsoft.framework.jnode3d.shader.ShaderDescriptor;
import org.pcsoft.framework.jnode3d.shader.ShaderProperty;
import org.pcsoft.framework.jnode3d.type.Color;

@ShaderDescriptor(fragmentMain = "ambiLight_fs")
public final class AmbientLightShader extends LightShader {
    public static final String AMBI_LIGHT_COLOR = "ambiLight_Color";
    public static final String AMBI_LIGHT_POWER = "ambiLight_Power";

    @ShaderProperty(name = AMBI_LIGHT_COLOR)
    private Color color = Color.WHITE;
    @ShaderProperty(name = AMBI_LIGHT_POWER)
    private float power = 0.3f;

    public AmbientLightShader() {
        super(0, null, loadShader(AmbientLightShader.class.getResourceAsStream("/shader/light/ambiLight.frag")));
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        updateUniformValue(AMBI_LIGHT_COLOR);
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
        updateUniformValue(AMBI_LIGHT_POWER);
    }
}
