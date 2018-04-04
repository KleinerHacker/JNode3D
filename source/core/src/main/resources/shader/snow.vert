varying float x, y, z;

void main()
{
    gl_Position = ftransform();
    x = gl_Position.x; y = gl_Position.y; z = gl_Position.z;
    x += y; y -= x; z += x - y;
}