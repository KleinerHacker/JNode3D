varying float snow_Position;

void snow_vs()
{
    snow_Position.x = gl_Position.x; snow_Position.y = gl_Position.y; snow_Position.z = gl_Position.z;
    snow_Position.x += snow_Position.y; snow_Position.y -= snow_Position.x; snow_Position.z += snow_Position.x - snow_Position.y;
}