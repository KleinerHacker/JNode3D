uniform vec4 ambiLight_Color = vec4(1, 1, 1, 1);
uniform float ambiLight_Power = 0.3;

void ambiLight_fs()
{
    base_Light = vec4(
        gl_FragColor.r * ambiLight_Color.r * ambiLight_Power,
        gl_FragColor.g * ambiLight_Color.g * ambiLight_Power,
        gl_FragColor.b * ambiLight_Color.b * ambiLight_Power,
        gl_Color.a
    );
}