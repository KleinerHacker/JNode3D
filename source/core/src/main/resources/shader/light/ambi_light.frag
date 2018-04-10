uniform vec4 ambi_light_color = vec4(1, 1, 1, 1);
uniform float ambi_light_power = 0.3;

void ambi_light_fs()
{
    gl_FragColor = vec4(
        gl_FragColor.r * ambi_light_color.r * ambi_light_power,
        gl_FragColor.g * ambi_light_color.g * ambi_light_power,
        gl_FragColor.b * ambi_light_color.b * ambi_light_power,
        gl_Color.a
    );
}