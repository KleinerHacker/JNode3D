uniform float base_Opacity = 1.0;
vec4 base_Light;

___METHOD___;

void main() {
    gl_FragColor = vec4(gl_Color.r, gl_Color.g, gl_Color.b, gl_Color.a * base_Opacity);
    base_Light = gl_FragColor;

	___CONTENT___;

	gl_FragColor = base_Light;
}
