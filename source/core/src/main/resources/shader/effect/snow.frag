varying float snow_Position;
uniform float snow_Seed = -15.4457;
uniform bool snow_Colored = true;

float rand(float s, float r)
{
    return mod(mod(s, r + snow_Seed) * 112341.0, 1.0);
}

void snow_fs()
{
    float r, g, b;
    if (snow_Colored)
    {
        r = rand(gl_FragCoord.x, snow_Position.x);
        g = rand(gl_FragCoord.y, snow_Position.y);
        b = rand(gl_FragCoord.z, snow_Position.z);
    }
    else
    {
        float gray = rand(gl_FragCoord.x, snow_Position.x) * 0.33 + rand(gl_FragCoord.y, snow_Position.y) * 0.33 + rand(gl_FragCoord.z, snow_Position.z) * 0.33;
        r = gray;
        g = gray;
        b = gray;
    }
    gl_FragColor = vec4(r, g, b, 1);
}