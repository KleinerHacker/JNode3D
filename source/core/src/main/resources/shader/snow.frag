varying float x, y, z;
uniform float r_mod = -15.4457;
uniform bool colored = true;

float rand(float s, float r)
{
    return mod(mod(s, r + r_mod) * 112341.0, 1.0);
}

void snow_fs()
{
    float r, g, b;
    if (colored)
    {
        r = rand(gl_FragCoord.x, x);
        g = rand(gl_FragCoord.y, y);
        b = rand(gl_FragCoord.z, z);
    }
    else
    {
        float gray = rand(gl_FragCoord.x, x) * 0.33 + rand(gl_FragCoord.y, y) * 0.33 + rand(gl_FragCoord.z, z) * 0.33;
        r = gray;
        g = gray;
        b = gray;
    }
    gl_FragColor = vec4(r, g, b, 1);
}