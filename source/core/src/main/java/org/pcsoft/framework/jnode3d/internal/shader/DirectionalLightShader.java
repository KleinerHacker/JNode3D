package org.pcsoft.framework.jnode3d.internal.shader;

import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.shader.LightShader;
import org.pcsoft.framework.jnode3d.shader.ShaderDescriptor;
import org.pcsoft.framework.jnode3d.shader.ShaderProperty;
import org.pcsoft.framework.jnode3d.type.Color;

@ShaderDescriptor(vertexMain = "dirLight_vs", fragmentMain = "dirLight_fs")
public final class DirectionalLightShader extends LightShader {
    public static final String DIR_LIGHT_DIRECTION = "dirLight_Direction";
    public static final String DIR_LIGHT_COLOR = "dirLight_Color";
    public static final String DIR_LIGHT_POWER = "dirLight_Power";

    @ShaderProperty(name = DIR_LIGHT_DIRECTION)
    private Vector3f direction = new Vector3f(-0.5f, -1f, -1f);
    @ShaderProperty(name = DIR_LIGHT_COLOR)
    private Color color = Color.WHITE;
    @ShaderProperty(name = DIR_LIGHT_POWER)
    private float power = 1f;

    public DirectionalLightShader() {
        super(1, loadShader(DirectionalLightShader.class.getResourceAsStream("/shader/light/dirLight.vert")),
                loadShader(DirectionalLightShader.class.getResourceAsStream("/shader/light/dirLight.frag")));
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction;
        updateUniformValue(DIR_LIGHT_DIRECTION);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        updateUniformValue(DIR_LIGHT_COLOR);
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
        updateUniformValue(DIR_LIGHT_POWER);
    }
}
