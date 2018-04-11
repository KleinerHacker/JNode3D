varying vec3 pointLight_Normal;
varying vec3 pointLight_Direction;
uniform vec4 pointLight_Color = vec4(1, 1, 1, 1);
uniform float pointLight_Power = 1.0;
uniform float pointLight_Attitude = 0.5;

void pointLight_fs()
{
    float intensity = clamp(dot(pointLight_Normal, normalize(pointLight_Direction)), 0.0, 1.0);
    float length = length(pointLight_Direction);

    if (intensity > 0.0)
    {
        base_Light = vec4(
            base_Light.r + pointLight_Color.r * gl_FragColor.r * intensity * max(0.0, pointLight_Power - length * pointLight_Attitude),
            base_Light.g + pointLight_Color.g * gl_FragColor.g * intensity * max(0.0, pointLight_Power - length * pointLight_Attitude),
            base_Light.b + pointLight_Color.b * gl_FragColor.b * intensity * max(0.0, pointLight_Power - length * pointLight_Attitude),
            gl_Color.a
        );
    }
}