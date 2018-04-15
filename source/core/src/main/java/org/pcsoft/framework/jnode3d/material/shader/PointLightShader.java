package org.pcsoft.framework.jnode3d.material.shader;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.material.Material;
import org.pcsoft.framework.jnode3d.type.Color;

@ShaderDescriptor(vertexResource = "/shader/light/pointLight.vert", fragmentResource = "/shader/light/pointLight.frag",
        vertexMain = "pointLight_vs", fragmentMain = "pointLight_fs")
public final class PointLightShader extends Shader {
    private static final String POINT_LIGHT_POSITION = "pointLight_Position";
    private static final String POINT_LIGHT_COLOR = "pointLight_Color";
    private static final String POINT_LIGHT_POWER = "pointLight_Power";
    private static final String POINT_LIGHT_ATTITUDE = "pointLight_Attitude";
    private static final String POINT_LIGHT_TRANSFORMATION_MATRIX = "pointLight_TransformationMatrix";
    private static final String POINT_LIGHT_EXPONENT = "pointLight_Exponent";

    @ShaderProperty(name = POINT_LIGHT_POSITION)
    private Vector3f position = new Vector3f();
    @ShaderProperty(name = POINT_LIGHT_COLOR)
    private Color color = Color.WHITE;
    @ShaderProperty(name = POINT_LIGHT_POWER)
    private float power = 1f;
    @ShaderProperty(name = POINT_LIGHT_ATTITUDE)
    private float attitude = 0.5f;
    @ShaderProperty(name = POINT_LIGHT_EXPONENT)
    private float exponent = 2f;
    @ShaderProperty(name = POINT_LIGHT_TRANSFORMATION_MATRIX)
    private Matrix4f transformationMatrix = new Matrix4f();

    public PointLightShader(Material assignedMaterial) {
        super(assignedMaterial);
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

    public float getExponent() {
        return exponent;
    }

    public void setExponent(float exponent) {
        this.exponent = exponent;
        updateUniformValue(POINT_LIGHT_EXPONENT);
    }

    public Matrix4f getTransformationMatrix() {
        return transformationMatrix;
    }

    public void setTransformationMatrix(Matrix4f transformationMatrix) {
        this.transformationMatrix = transformationMatrix;
        updateUniformValue(POINT_LIGHT_TRANSFORMATION_MATRIX);
    }
}
