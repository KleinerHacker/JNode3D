package org.pcsoft.framework.jnode3d.shader;

import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.type.Color;

@ShaderDescriptor(vertexMain = "pointLight_vs", fragmentMain = "pointLight_fs")
public final class PointLightShader extends LightShader {
    private static final String POINT_LIGHT_POSITION = "pointLight_Position";
    private static final String POINT_LIGHT_COLOR = "pointLight_Color";
    private static final String POINT_LIGHT_POWER = "pointLight_Power";
    private static final String POINT_LIGHT_ATTITUDE = "pointLight_Attitude";

    @ShaderProperty(name = POINT_LIGHT_POSITION)
    private Vector3f position = new Vector3f();
    @ShaderProperty(name = POINT_LIGHT_COLOR)
    private Color color = Color.WHITE;
    @ShaderProperty(name = POINT_LIGHT_POWER)
    private float power = 1f;
    @ShaderProperty(name = POINT_LIGHT_ATTITUDE)
    private float attitude = 0.5f;

    public PointLightShader() {
        super(2, loadShader(PointLightShader.class.getResourceAsStream("/shader/light/pointLight.vert")),
                loadShader(PointLightShader.class.getResourceAsStream("/shader/light/pointLight.frag")));
    }

    public PointLightShader(Vector3f position) {
        this();
        this.position = position;
    }

    public PointLightShader(Vector3f position, Color color) {
        this(position);
        this.color = color;
    }

    public PointLightShader(Vector3f position, Color color, float attitude) {
        this(position, color);
        this.attitude = attitude;
    }

    public PointLightShader(Vector3f position, Color color, float attitude, float power) {
        this(position, color, attitude);
        this.power = power;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
        updateUniformValue(POINT_LIGHT_POSITION);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        updateUniformValue(POINT_LIGHT_COLOR);
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
        updateUniformValue(POINT_LIGHT_POWER);
    }

    public float getAttitude() {
        return attitude;
    }

    public void setAttitude(float attitude) {
        this.attitude = attitude;
        updateUniformValue(POINT_LIGHT_ATTITUDE);
    }
}
