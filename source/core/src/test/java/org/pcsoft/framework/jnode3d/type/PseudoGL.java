package org.pcsoft.framework.jnode3d.type;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.ogl.DrawingCallback;
import org.pcsoft.framework.jnode3d.ogl.NativeGL;
import org.pcsoft.framework.jnode3d.type.reference.BufferReference;
import org.pcsoft.framework.jnode3d.type.reference.ShaderProgramReference;

import java.nio.ByteBuffer;
import java.util.UUID;

public final class PseudoGL implements NativeGL {
    @Override
    public void glClear(float r, float g, float b, float a, int mask) {

    }

    @Override
    public void glVertex(float x, float y, float z) {

    }

    @Override
    public void glVertex(float x, float y) {

    }

    @Override
    public void glColor(float r, float g, float b, float a) {

    }

    @Override
    public void glTexCoord(float x, float y) {

    }

    @Override
    public void glNormal(float x, float y, float z) {

    }

    @Override
    public void glDraw(int mode, DrawingCallback drawingCallback) {

    }

    @Override
    public BufferReference glCreateBuffer(Vertex[] vertices, int[] indices) {
        return new BufferReference(UUID.randomUUID().hashCode(), UUID.randomUUID().hashCode(), vertices.length, indices.length);
    }

    @Override
    public void glDrawBuffer(int mode, BufferReference reference) {

    }

    @Override
    public void glDeleteBuffer(BufferReference reference) {

    }

    @Override
    public void glEnableDepthTest() {

    }

    @Override
    public void glDisableDepthTest() {

    }

    @Override
    public void glPolygonMode(int cullMode, int mode) {

    }

    @Override
    public void glTextureWrap(int sWrap, int tWrap) {

    }

    @Override
    public void glTextureBorder(float r, float g, float b, float a) {

    }

    @Override
    public void glTextureMinMagFilter(int minFilter, int magFilter) {

    }

    @Override
    public int glLoadTexture(ByteBuffer buffer, int width, int height, int textureStack) {
        return UUID.randomUUID().hashCode();
    }

    @Override
    public void glBindTexture(int textureIdentifier, int textureStack) {

    }

    @Override
    public void glDeleteTexture(int textureIdentifier) {

    }

    @Override
    public void glMatrixMode(int mode) {

    }

    @Override
    public void glLoadMatrix(float[] matrix) {

    }

    @Override
    public void glLoadIdentity() {

    }

    @Override
    public void glOrtho(float left, float top, float right, float bottom, float near, float far) {

    }

    @Override
    public void glFrustum(float left, float top, float right, float bottom, float near, float far) {

    }

    @Override
    public int glCreateShader(int shaderType, String... script) {
        return UUID.randomUUID().hashCode();
    }

    @Override
    public void glDeleteShader(int shaderIdentifier) {

    }

    @Override
    public String glShaderLog(int shaderIdentifier) {
        return "";
    }

    @Override
    public ShaderProgramReference glCreateProgram(int... shaderIdentifiers) {
        return new ShaderProgramReference(UUID.randomUUID().hashCode());
    }

    @Override
    public void glDeleteProgram(ShaderProgramReference programIdentifier) {

    }

    @Override
    public String glProgramLog(ShaderProgramReference programIdentifier) {
        return "";
    }

    @Override
    public void glUseProgram(ShaderProgramReference programIdentifier) {

    }

    @Override
    public boolean glSetProgramVar(ShaderProgramReference programIdentifier, String varName, boolean value) {
        return true;
    }

    @Override
    public boolean glSetProgramVar(ShaderProgramReference programIdentifier, String varName, float value) {
        return true;
    }

    @Override
    public boolean glSetProgramVar(ShaderProgramReference programIdentifier, String varName, int value) {
        return true;
    }

    @Override
    public boolean glSetProgramVar(ShaderProgramReference programIdentifier, String varName, Vector3f value) {
        return true;
    }

    @Override
    public boolean glSetProgramVar(ShaderProgramReference programIdentifier, String varName, Color value) {
        return true;
    }

    @Override
    public boolean glSetProgramVar(ShaderProgramReference programIdentifier, String varName, Matrix4f value) {
        return true;
    }
}
