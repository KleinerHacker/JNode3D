vec4 base_Light;

___METHOD___;

void main() {
    gl_FragColor = gl_Color;
    base_Light = gl_FragColor;

	___CONTENT___;

	gl_FragColor = base_Light;
}
