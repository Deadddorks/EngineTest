#version 330 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec4 color;

out struct Vertex {
    vec3 position;
    vec4 color;
} v;

void main(void)
{
    v.position = position;
    v.color = color;
    gl_Position = vec4(v.position, 1.0);
}