varying vec3 pointLight_Normal;
varying vec3 pointLight_Direction;
uniform vec4 pointLight_Color = vec4(1, 1, 1, 1);
uniform float pointLight_Power = 1.0;
uniform float pointLight_Attitude = 0.5;
uniform float pointLight_Exponent = 2.0;

void pointLight_fs()
{
    float intensity = clamp(dot(pointLight_Normal, normalize(pointLight_Direction)), 0.0, 1.0);
    float length = length(pointLight_Direction);

    if (intensity > 0.0)
    {
        base_Light += pointLight_Color * gl_FragColor * intensity * max(0.0, pointLight_Power - pow(length * pointLight_Attitude, pointLight_Exponent)); 
    }
}