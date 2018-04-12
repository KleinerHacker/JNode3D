varying vec3 pointLight_Normal;
varying vec3 pointLight_Direction;
uniform vec3 pointLight_Position = vec3(0, 0, 0);
uniform mat4 pointLight_TransformationMatrix;

void pointLight_vs()
{
    pointLight_Normal = normalize(gl_NormalMatrix * gl_Normal);
    pointLight_Direction = vec3(pointLight_TransformationMatrix * vec4(pointLight_Position, 1.0)) - vec3(gl_ModelViewMatrix * gl_Vertex);
}