#version 330 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec4 color;

out struct Vertex {
    vec3 position;
    vec4 color;
} v;

uniform vec3 location;

void main(void)
{
    v.position = vec3(location.x + position.x, location.y + position.y, location.z + position.z);
    v.color = color;
    gl_Position = vec4(v.position, 1.0);
}