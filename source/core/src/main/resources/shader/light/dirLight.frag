varying vec3 dirLight_Normal;
uniform vec3 dirLight_Direction = vec3(1, -1, 1);
uniform vec4 dirLight_Color = vec4(1, 1, 1, 1);
uniform float dirLight_Power = 1.0;

void dirLight_fs()
{
    float intensity = clamp(dot(normalize(dirLight_Normal), normalize(dirLight_Direction)), 0.0, 1.0);

    if (intensity > 0.0)
    {
        base_Light = vec4(
            base_Light.r + dirLight_Color.r * gl_FragColor.r * intensity * dirLight_Power,
            base_Light.g + dirLight_Color.g * gl_FragColor.g * intensity * dirLight_Power,
            base_Light.b + dirLight_Color.b * gl_FragColor.b * intensity * dirLight_Power,
            gl_Color.a
        );
    }
}