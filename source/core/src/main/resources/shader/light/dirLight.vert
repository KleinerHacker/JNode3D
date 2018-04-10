varying vec3 dirLight_Normal;

void dirLight_vs()
{
    dirLight_Normal = normalize(gl_NormalMatrix * gl_Normal);
}