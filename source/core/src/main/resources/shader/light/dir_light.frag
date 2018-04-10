varying vec3 dir_light_normal;
uniform vec3 dir_light_direction = vec3(1, -1, 1);
uniform vec4 dir_light_color = vec4(1, 1, 1, 1);
uniform float dir_light_power = 1.0;

void dir_light_fs()
{
    float intensity = clamp(dot(normalize(dir_light_normal), normalize(dir_light_direction)), 0.0, 1.0);

    if (intensity > 0.0)
    {
        gl_FragColor = vec4(
            gl_FragColor.r + dir_light_color.r * gl_Color.r * intensity * dir_light_power,
            gl_FragColor.g + dir_light_color.g * gl_Color.g * intensity * dir_light_power,
            gl_FragColor.b + dir_light_color.b * gl_Color.b * intensity * dir_light_power,
            gl_Color.a
        );
    }
}