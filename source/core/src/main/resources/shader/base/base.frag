uniform int base_CullMode = 3;
uniform float base_Opacity = 1.0;

void base_fs() {
    if ((((base_CullMode == 1 || base_CullMode == 3) && gl_FrontFacing) || ((base_CullMode == 2 || base_CullMode == 3) && !gl_FrontFacing))) {
        gl_FragColor = vec4(gl_FragColor.r, gl_FragColor.g, gl_FragColor.b, gl_FragColor.a * base_Opacity);
    }
}
