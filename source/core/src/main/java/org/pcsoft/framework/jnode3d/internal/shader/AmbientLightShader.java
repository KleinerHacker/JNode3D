package org.pcsoft.framework.jnode3d.internal.shader;

import org.pcsoft.framework.jnode3d.material.Material;
import org.pcsoft.framework.jnode3d.material.shader.Shader;
import org.pcsoft.framework.jnode3d.material.shader.ShaderDescriptor;
import org.pcsoft.framework.jnode3d.material.shader.ShaderProperty;
import org.pcsoft.framework.jnode3d.type.Color;

@ShaderDescriptor(fragmentResource = "/shader/light/ambiLight.frag", fragmentMain = "ambiLight_fs")
public final class AmbientLightShader extends Shader {
    public static final String AMBI_LIGHT_COLOR = "ambiLight_Color";
    public static final String AMBI_LIGHT_POWER = "ambiLight_Power";

    @ShaderProperty(name = AMBI_LIGHT_COLOR)
    private Color color = Color.WHITE;
    @ShaderProperty(name = AMBI_LIGHT_POWER)
    private float power = 0.3f;

    public AmbientLightShader(Material assignedMaterial) {
        super(assignedMaterial);
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
