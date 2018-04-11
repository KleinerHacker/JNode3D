varying vec3 pointLight_Normal;
varying vec3 pointLight_Direction;
uniform vec3 pointLight_Position = vec3(0, 0, 0);

void pointLight_vs()
{
    pointLight_Normal = normalize(gl_NormalMatrix * gl_Normal);
    pointLight_Direction = pointLight_Position - vec3(gl_ModelViewMatrix * gl_Vertex);
}