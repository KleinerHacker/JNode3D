varying vec3 dirLight_Normal;
uniform vec3 dirLight_Direction = vec3(1, -1, 1);
uniform vec4 dirLight_Color = vec4(1, 1, 1, 1);
uniform float dirLight_Power = 1.0;

void dirLight_fs()
{
    float intensity = clamp(dot(normalize(dirLight_Normal), normalize(dirLight_Direction)), 0.0, 1.0);

    if (intensity > 0.0)
    {
        gl_FragColor = vec4(
            gl_FragColor.r + dirLight_Color.r * gl_Color.r * intensity * dirLight_Power,
            gl_FragColor.g + dirLight_Color.g * gl_Color.g * intensity * dirLight_Power,
            gl_FragColor.b + dirLight_Color.b * gl_Color.b * intensity * dirLight_Power,
            gl_Color.a
        );
    }
}