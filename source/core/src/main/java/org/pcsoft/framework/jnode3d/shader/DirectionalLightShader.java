package org.pcsoft.framework.jnode3d.shader;

import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.type.Color;

@ShaderDescriptor(vertexMain = "dir_light_vs", fragmentMain = "dir_light_fs")
public final class DirectionalLightShader extends Shader {
    private static final String DIR_LIGHT_DIRECTION = "dir_light_direction";
    private static final String DIR_LIGHT_COLOR = "dir_light_color";
    private static final String DIR_LIGHT_POWER = "dir_light_power";

    @ShaderProperty(name = DIR_LIGHT_DIRECTION)
    private Vector3f direction = new Vector3f(-0.5f, -1f, -1f);
    @ShaderProperty(name = DIR_LIGHT_COLOR)
    private Color color = Color.WHITE;
    @ShaderProperty(name = DIR_LIGHT_POWER)
    private float power = 1f;

    public DirectionalLightShader() {
        super(loadShader(DirectionalLightShader.class.getResourceAsStream("/shader/light/dir_light.vert")),
                loadShader(DirectionalLightShader.class.getResourceAsStream("/shader/light/dir_light.frag")));
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
