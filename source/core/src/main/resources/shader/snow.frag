varying float x, y, z;
uniform float r_mod;

float rand(float s, float r)
{
    return mod(mod(s, r + r_mod) * 112341, 1);
}

void main()
{
    gl_FragColor = vec4(rand(gl_FragCoord.x, x), rand(gl_FragCoord.y, y), rand(gl_FragCoord.z, z), 1);
}