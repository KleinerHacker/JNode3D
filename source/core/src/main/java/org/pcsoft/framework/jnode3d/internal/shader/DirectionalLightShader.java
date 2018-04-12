package org.pcsoft.framework.jnode3d.internal.shader;

import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.material.Material;
import org.pcsoft.framework.jnode3d.material.shader.Shader;
import org.pcsoft.framework.jnode3d.material.shader.ShaderDescriptor;
import org.pcsoft.framework.jnode3d.material.shader.ShaderProperty;
import org.pcsoft.framework.jnode3d.type.Color;

@ShaderDescriptor(vertexResource = "/shader/light/dirLight.vert", fragmentResource = "/shader/light/dirLight.frag",
        vertexMain = "dirLight_vs", fragmentMain = "dirLight_fs")
public final class DirectionalLightShader extends Shader {
    public static final String DIR_LIGHT_DIRECTION = "dirLight_Direction";
    public static final String DIR_LIGHT_COLOR = "dirLight_Color";
    public static final String DIR_LIGHT_POWER = "dirLight_Power";

    @ShaderProperty(name = DIR_LIGHT_DIRECTION)
    private Vector3f direction = new Vector3f(-0.5f, -1f, -1f);
    @ShaderProperty(name = DIR_LIGHT_COLOR)
    private Color color = Color.WHITE;
    @ShaderProperty(name = DIR_LIGHT_POWER)
    private float power = 1f;

    public DirectionalLightShader(Material assignedMaterial) {
        super(assignedMaterial);
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
