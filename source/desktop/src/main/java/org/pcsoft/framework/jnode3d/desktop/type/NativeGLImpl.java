package org.pcsoft.framework.jnode3d.desktop.type;

import org.joml.Vector3f;
import org.lwjgl.opengl.*;
import org.pcsoft.framework.jnode3d.ogl.DrawingCallback;
import org.pcsoft.framework.jnode3d.ogl.NativeGL;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.Vertex;
import org.pcsoft.framework.jnode3d.type.reference.BufferReference;
import org.pcsoft.framework.jnode3d.type.reference.ShaderProgramReference;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class NativeGLImpl implements NativeGL {
    //<editor-fold desc="Clean Up">

    @Override
    public void glClear(float r, float g, float b, float a, int mask) {
        GL11.glClearColor(r, g, b, a);
        GL11.glClear(mask);
    }

    //</editor-fold>

    //<editor-fold desc="Rendering">
    @Override
    public void glVertex(float x, float y, float z) {
        GL11.glVertex3f(x, y, z);
    }

    @Override
    public void glVertex(float x, float y) {
        GL11.glVertex2f(x, y);
    }

    @Override
    public void glColor(float r, float g, float b, float a) {
        GL11.glColor4f(r, g, b, a);
    }

    @Override
    public void glTexCoord(float x, float y) {
        GL11.glTexCoord2f(x, y);
    }

    @Override
    public void glNormal(float x, float y, float z) {
        GL11.glNormal3f(x, y, z);
    }

    @Override
    public void glDraw(int mode, DrawingCallback drawingCallback) {
        GL11.glBegin(mode);
        drawingCallback.draw();
        GL11.glEnd();
    }

    @Override
    public BufferReference glCreateBuffer(Vertex[] vertices, int[] indices) {
        final int size = vertices.length * Vertex.RAW_COUNT;

        final FloatBuffer floatBuffer = FloatBuffer.allocate(size);
        for (final Vertex vertex : vertices) {
            floatBuffer.put(vertex.getRawVertex());
        }

        final int vertexIdentifier = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexIdentifier);
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, floatBuffer.array(), GL15.GL_STATIC_DRAW);

        final int indexIdentifier = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indexIdentifier);
        GL11.glEnableClientState(GL11.GL_INDEX_ARRAY);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);

        return new BufferReference(vertexIdentifier, indexIdentifier, vertices.length, indices.length);
    }

    @Override
    public void glDrawBuffer(int mode, BufferReference reference) {
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, reference.getVertexIdentifier());
        GL11.glInterleavedArrays(GL11.GL_T2F_C4F_N3F_V3F, Vertex.RAW_COUNT * Float.BYTES, 0);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, reference.getIndexIdentifier());
        GL11.glDrawElements(mode, reference.getIndicesCount(), GL11.GL_UNSIGNED_INT, 0);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    @Override
    public void glDeleteBuffer(BufferReference reference) {
        GL15.glDeleteBuffers(reference.getVertexIdentifier());
        GL15.glDeleteBuffers(reference.getIndexIdentifier());
    }

    @Override
    public void glEnableDepthTest() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    @Override
    public void glDisableDepthTest() {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
    }
//</editor-fold>

    //<editor-fold desc="Texture">
    @Override
    public void glTextureWrap(int sWrap, int tWrap) {
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, sWrap);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, tWrap);
    }

    @Override
    public void glTextureBorder(float r, float g, float b, float a) {
        GL11.glTexParameterfv(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_BORDER_COLOR, new float[]{r, g, b, a});
    }

    @Override
    public void glTextureMinMagFilter(int minFilter, int magFilter) {
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, minFilter);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, magFilter);
    }

    @Override
    public int glLoadTexture(ByteBuffer buffer, int width, int height, int textureStack) {
        final int identifier = GL11.glGenTextures();
        GL13.glActiveTexture(1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, identifier);
        GL42.glBindImageTexture(1, identifier, 0, true, 0, GL15.GL_READ_WRITE, GL11.GL_RGBA);

        GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

        GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, width, height, 0,
                GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
        /*final float pixels[] = {
                0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f,
                1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f
        };
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, 2, 2, 0, GL11.GL_RGB, GL11.GL_FLOAT, pixels);*/

        return identifier;
    }

    @Override
    public void glBindTexture(int textureIdentifier, int textureStack) {
        GL13.glActiveTexture(1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureIdentifier);
        GL42.glBindImageTexture(1, textureIdentifier, 0, true, 0, GL15.GL_READ_WRITE, GL11.GL_RGBA);
    }

    @Override
    public void glDeleteTexture(int textureIdentifier) {
        GL11.glDeleteTextures(textureIdentifier);
    }
//</editor-fold>

    //<editor-fold desc="Matrix">
    @Override
    public void glMatrixMode(int mode) {
        GL11.glMatrixMode(mode);
    }

    @Override
    public void glLoadIdentity() {
        GL11.glLoadIdentity();
    }

    @Override
    public void glOrtho(float left, float top, float right, float bottom, float near, float far) {
        GL11.glOrtho(left, right, bottom, top, near, far);
    }

    @Override
    public void glFrustum(float left, float top, float right, float bottom, float near, float far) {
        GL11.glFrustum(left, right, bottom, top, near, far);
    }

    @Override
    public void glLoadMatrix(float[] matrix) {
        GL11.glLoadMatrixf(matrix);
    }

    //</editor-fold>

    //<editor-fold desc="Shaders">
    @Override
    public int glCreateShader(int shaderType, String... script) {
        final int identifier = GL20.glCreateShader(shaderType);
        GL20.glShaderSource(identifier, script);
        GL20.glCompileShader(identifier);

        return identifier;
    }

    @Override
    public ShaderProgramReference glCreateProgram(int... shaderIdentifiers) {
        final int identifier = GL20.glCreateProgram();
        for (final int shaderIdentifier : shaderIdentifiers) {
            GL20.glAttachShader(identifier, shaderIdentifier);
        }
        GL20.glLinkProgram(identifier);

        return new ShaderProgramReference(identifier);
    }

    @Override
    public void glUseProgram(ShaderProgramReference programReference) {
        GL20.glUseProgram(programReference.getProgramId());
    }

    @Override
    public void glDeleteShader(int shaderIdentifier) {
        GL20.glDeleteShader(shaderIdentifier);
    }

    @Override
    public void glDeleteProgram(ShaderProgramReference programReference) {
        GL20.glDeleteProgram(programReference.getProgramId());
    }

    @Override
    public String glShaderLog(int shaderIdentifier) {
        return GL20.glGetShaderInfoLog(shaderIdentifier);
    }

    @Override
    public String glProgramLog(ShaderProgramReference programReference) {
        return GL20.glGetProgramInfoLog(programReference.getProgramId());
    }

    @Override
    public boolean glSetProgramVar(ShaderProgramReference programReference, String varName, boolean value) {
        final int location = GL20.glGetUniformLocation(programReference.getProgramId(), varName);
        if (location < 0)
            return false;

        GL41.glProgramUniform1i(programReference.getProgramId(), location, value ? 1 : 0);
        return true;
    }

    @Override
    public boolean glSetProgramVar(ShaderProgramReference programReference, String varName, float value) {
        final int location = GL20.glGetUniformLocation(programReference.getProgramId(), varName);
        if (location < 0)
            return false;

        GL41.glProgramUniform1f(programReference.getProgramId(), location, value);
        return true;
    }

    @Override
    public boolean glSetProgramVar(ShaderProgramReference programReference, String varName, int value) {
        final int location = GL20.glGetUniformLocation(programReference.getProgramId(), varName);
        if (location < 0)
            return false;

        GL41.glProgramUniform1i(programReference.getProgramId(), location, value);
        return true;
    }

    @Override
    public boolean glSetProgramVar(ShaderProgramReference programReference, String varName, Vector3f value) {
        final int location = GL20.glGetUniformLocation(programReference.getProgramId(), varName);
        if (location < 0)
            return false;

        GL41.glProgramUniform3f(programReference.getProgramId(), location, value.x, value.y, value.z);
        return true;
    }

    @Override
    public boolean glSetProgramVar(ShaderProgramReference programReference, String varName, Color value) {
        final int location = GL20.glGetUniformLocation(programReference.getProgramId(), varName);
        if (location < 0)
            return false;

        GL41.glProgramUniform4f(programReference.getProgramId(), location, value.getR(), value.getG(), value.getB(), value.getA());
        return true;
    }

    //</editor-fold>
}
