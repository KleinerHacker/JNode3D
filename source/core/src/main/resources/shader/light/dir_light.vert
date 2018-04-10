varying vec3 dir_light_normal;

void dir_light_vs()
{
    dir_light_normal = normalize(gl_NormalMatrix * gl_Normal);
}