uniform float base_Opacity = 1.0;

void base_fs() {
    gl_FragColor = vec4(gl_Color.r, gl_Color.g, gl_Color.b, gl_Color.a * base_Opacity);
}
