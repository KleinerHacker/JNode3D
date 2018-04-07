varying float snow_x, snow_y, snow_z;
uniform float snow_r_mod = -15.4457;
uniform bool snow_colored = true;

float rand(float s, float r)
{
    return mod(mod(s, r + snow_r_mod) * 112341.0, 1.0);
}

void snow_fs()
{
    float r, g, b;
    if (snow_colored)
    {
        r = rand(gl_FragCoord.x, snow_x);
        g = rand(gl_FragCoord.y, snow_y);
        b = rand(gl_FragCoord.z, snow_z);
    }
    else
    {
        float gray = rand(gl_FragCoord.x, snow_x) * 0.33 + rand(gl_FragCoord.y, snow_y) * 0.33 + rand(gl_FragCoord.z, snow_z) * 0.33;
        r = gray;
        g = gray;
        b = gray;
    }
    gl_FragColor = vec4(r, g, b, 1);
}