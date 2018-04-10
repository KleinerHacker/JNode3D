package org.pcsoft.framework.jnode3d.ogl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.type.*;
import org.pcsoft.framework.jnode3d.type.reference.BufferReference;
import org.pcsoft.framework.jnode3d.type.reference.ShaderProgramReference;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;

public final class OpenGL {
    /**
     * AttribMask
     */
    public static final int
            GL_CURRENT_BIT = 0x1,
            GL_POINT_BIT = 0x2,
            GL_LINE_BIT = 0x4,
            GL_POLYGON_BIT = 0x8,
            GL_POLYGON_STIPPLE_BIT = 0x10,
            GL_PIXEL_MODE_BIT = 0x20,
            GL_LIGHTING_BIT = 0x40,
            GL_FOG_BIT = 0x80,
            GL_DEPTH_BUFFER_BIT = 0x100,
            GL_ACCUM_BUFFER_BIT = 0x200,
            GL_STENCIL_BUFFER_BIT = 0x400,
            GL_VIEWPORT_BIT = 0x800,
            GL_TRANSFORM_BIT = 0x1000,
            GL_ENABLE_BIT = 0x2000,
            GL_COLOR_BUFFER_BIT = 0x4000,
            GL_HINT_BIT = 0x8000,
            GL_EVAL_BIT = 0x10000,
            GL_LIST_BIT = 0x20000,
            GL_TEXTURE_BIT = 0x40000,
            GL_SCISSOR_BIT = 0x80000,
            GL_ALL_ATTRIB_BITS = 0xFFFFF;

    /** Accepted by {@code stages} parameter to UseProgramStages. */
    public static final int
            GL_VERTEX_SHADER_BIT          = 0x1,
            GL_FRAGMENT_SHADER_BIT        = 0x2,
            GL_GEOMETRY_SHADER_BIT        = 0x4,
            GL_TESS_CONTROL_SHADER_BIT    = 0x8,
            GL_TESS_EVALUATION_SHADER_BIT = 0x10,
            GL_ALL_SHADER_BITS            = 0xFFFFFFFF;

    private final NativeGL ngl;

    OpenGL(NativeGL ngl) {
        this.ngl = ngl;
    }

    //<editor-fold desc="Clean Up">
    public void glClear(Color color, int mask) {
        glClear(color.getR(), color.getG(), color.getB(), color.getA(), mask);
    }

    public void glClear(float r, float g, float b, float a, int mask) {
        ngl.glClear(r, g, b, a, mask);
    }
    //</editor-fold>

    //<editor-fold desc="Rendering">
    public void glVertex(float x, float y, float z) {
        ngl.glVertex(x, y, z);
    }

    public void glVertex(Vector3f vector3D) {
        glVertex(vector3D.x(), vector3D.y(), vector3D.z());
    }

    public void glVertex(float x, float y) {
        ngl.glVertex(x, y);
    }

    public void glVertex(Vector2f vector2D) {
        glVertex(vector2D.x(), vector2D.y());
    }

    public void glColor(float r, float g, float b, float a) {
        ngl.glColor(r, g, b, a);
    }

    public void glColor(Color color) {
        glColor(color.getR(), color.getG(), color.getB(), color.getA());
    }

    public void glTexCoord(float x, float y) {
        ngl.glTexCoord(x, y);
    }

    public void glTexCoord(Vector2f vector2D) {
        glTexCoord(vector2D.x(), vector2D.y());
    }

    public void glNormal(float x, float y, float z) {
        ngl.glNormal(x, y, z);
    }

    public void glNormal(Vector3f vector3D) {
        glNormal(vector3D.x(), vector3D.y(), vector3D.z());
    }

    public void glDraw(RenderMode mode, DrawingCallback drawingCallback) {
        ngl.glDraw(mode.getValue(), drawingCallback);
    }

    public BufferReference glCreateBuffer(Vertex[] vertices, int[] indices) {
        return ngl.glCreateBuffer(vertices, indices);
    }

    public void glDrawBuffer(RenderMode mode, BufferReference reference) {
        ngl.glDrawBuffer(mode.getValue(), reference);
    }

    public void glDeleteBuffer(BufferReference reference) {
        ngl.glDeleteBuffer(reference);
    }

    public void glEnableDepthTest() {
        ngl.glEnableDepthTest();
    }

    public void glDisableDepthTest() {
        ngl.glDisableDepthTest();
    }

    //</editor-fold>

    //<editor-fold desc="Texture">
    public void glTextureWrap(TextureBorderFilter wrap) {
        glTextureWrap(wrap, wrap);
    }

    public void glTextureWrap(TextureBorderFilter sWrap, TextureBorderFilter tWrap) {
        ngl.glTextureWrap(sWrap.getValue(), tWrap.getValue());
    }

    public void glTextureBorder(Color color) {
        glTextureBorder(color.getR(), color.getG(), color.getB(), color.getA());
    }

    public void glTextureBorder(float r, float g, float b, float a) {
        ngl.glTextureBorder(r, g, b, a);
    }

    public void glTextureMinMagFilter(TextureDistanceFilter filter) {
        glTextureMinMagFilter(filter, filter);
    }

    public void glTextureMinMagFilter(TextureDistanceFilter minFilter, TextureDistanceFilter magFilter) {
        ngl.glTextureMinMagFilter(minFilter.getValue(), magFilter.getValue());
    }

    public int glLoadTexture(ByteBuffer buffer, int width, int height, TextureStack textureStack) {
        return ngl.glLoadTexture(buffer, width, height, textureStack.getValue());
    }

    public void glBindTexture(int textureIdentifier, TextureStack textureStack) {
        ngl.glBindTexture(textureIdentifier, textureStack.getValue());
    }

    public void glDeleteTexture(int textureIdentifier) {
        ngl.glDeleteTexture(textureIdentifier);
    }

    //</editor-fold>

    //<editor-fold desc="Matrix">
    public void glMatrixMode(MatrixMode mode) {
        ngl.glMatrixMode(mode.getValue());
    }

    public void glLoadMatrix(float[] matrix) {
        ngl.glLoadMatrix(matrix);
    }

    public void glLoadMatrix(Matrix4f matrix) {
        glLoadMatrix(matrix.get(new float[4 * 4]));
    }

    public void glLoadIdentity() {
        ngl.glLoadIdentity();
    }

    public void glOrtho(float left, float top, float right, float bottom, float near, float far) {
        ngl.glOrtho(left, top, right, bottom, near, far);
    }

    public void glOrtho(Bounds2D bounds2D, float near, float far) {
        glOrtho(bounds2D.getLeft(), bounds2D.getTop(), bounds2D.getRight(), bounds2D.getBottom(), near, far);
    }

    public void glFrustum(float left, float top, float right, float bottom, float near, float far) {
        ngl.glFrustum(left, top, right, bottom, near, far);
    }

    public void glFrustum(Bounds2D bounds2D, float near, float far) {
        glFrustum(bounds2D.getLeft(), bounds2D.getTop(), bounds2D.getRight(), bounds2D.getBottom(), near, far);
    }
    //</editor-fold>

    //<editor-fold desc="Shaders">
    public int glCreateShader(ShaderType shaderType, File file) throws IOException {
        return glCreateShader(shaderType, FileUtils.readFileToString(file));
    }

    public int glCreateShader(ShaderType shaderType, File file, String encoding) throws IOException {
        return glCreateShader(shaderType, FileUtils.readFileToString(file, encoding));
    }

    public int glCreateShader(ShaderType shaderType, Reader in) throws IOException {
        return glCreateShader(shaderType, IOUtils.toString(in));
    }

    public int glCreateShader(ShaderType shaderType, InputStream in) throws IOException {
        return glCreateShader(shaderType, IOUtils.toString(in));
    }

    public int glCreateShader(ShaderType shaderType, InputStream in, String encoding) throws IOException {
        return glCreateShader(shaderType, IOUtils.toString(in, encoding));
    }

    public int glCreateShader(ShaderType shaderType, String... script) {
        return ngl.glCreateShader(shaderType.getValue(), script);
    }

    public ShaderProgramReference glCreateProgram(int... shaderIdentifiers) {
        return ngl.glCreateProgram(shaderIdentifiers);
    }

    public void glUseProgram(ShaderProgramReference programIdentifier) {
        ngl.glUseProgram(programIdentifier);
    }

    public void glDeleteShader(int shaderIdentifier) {
        ngl.glDeleteShader(shaderIdentifier);
    }

    public void glDeleteProgram(ShaderProgramReference programIdentifier) {
        ngl.glDeleteProgram(programIdentifier);
    }

    public String glShaderLog(int shaderIdentifier) {
        return ngl.glShaderLog(shaderIdentifier);
    }

    public String glProgramLog(ShaderProgramReference programIdentifier) {
        return ngl.glProgramLog(programIdentifier);
    }

    public void glSetProgramVar(ShaderProgramReference programIdentifier, String varName, boolean value) {
        ngl.glSetProgramVar(programIdentifier, varName, value);
    }

    public void glSetProgramVar(ShaderProgramReference programIdentifier, String varName, float value) {
        ngl.glSetProgramVar(programIdentifier, varName, value);
    }

    public void glSetProgramVar(ShaderProgramReference programIdentifier, String varName, int value) {
        ngl.glSetProgramVar(programIdentifier, varName, value);
    }

    public void glSetProgramVar(ShaderProgramReference programIdentifier, String varName, Vector3f value) {
        ngl.glSetProgramVar(programIdentifier, varName, value);
    }

    public void glSetProgramVar(ShaderProgramReference programIdentifier, String varName, Color value) {
        ngl.glSetProgramVar(programIdentifier, varName, value);
    }
//</editor-fold>
}
