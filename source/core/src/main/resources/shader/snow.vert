varying float snow_x, snow_y, snow_z;

vec4 snow_vs(vec4 in_Position)
{
    vec4 out_Position = in_Position;
    snow_x = out_Position.x; snow_y = out_Position.y; snow_z = out_Position.z;
    snow_x += snow_y; snow_y -= snow_x; snow_z += snow_x - snow_y;

    return out_Position;
}