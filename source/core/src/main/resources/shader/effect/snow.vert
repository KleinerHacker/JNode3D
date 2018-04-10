varying float snow_x, snow_y, snow_z;

void snow_vs()
{
    snow_x = gl_Position.x; snow_y = gl_Position.y; snow_z = gl_Position.z;
    snow_x += snow_y; snow_y -= snow_x; snow_z += snow_x - snow_y;
}