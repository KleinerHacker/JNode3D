uniform float opacity = 1.0;

void opacity_fs()
{
    gl_FragColor = vec4(gl_FragColor.r, gl_FragColor.g, gl_FragColor.b, opacity);
}