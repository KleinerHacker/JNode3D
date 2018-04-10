uniform int cull_mode = 3;

void main() {
	gl_Position = ftransform();

    gl_FrontColor = vec4(0, 0, 0, 0);
	if ((cull_mode & 1) == 1) {
	    gl_FrontColor = gl_Color;
	}
	gl_BackColor = vec4(0, 0, 0, 0);
	if ((cull_mode & 2) == 2) {
	    gl_BackColor = gl_Color;
	}

	$CONTENT$
}
