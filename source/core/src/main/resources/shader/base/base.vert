uniform int base_CullMode = 3;

void base_vs() {
    gl_FrontColor = vec4(0, 0, 0, 0);
    if ((base_CullMode & 1) == 1) {
        gl_FrontColor = gl_Color;
    }

    gl_BackColor = vec4(0, 0, 0, 0);
    if ((base_CullMode & 2) == 2) {
        gl_BackColor = gl_Color;
    }
}
