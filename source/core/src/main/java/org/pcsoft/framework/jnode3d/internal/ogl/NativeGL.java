package org.pcsoft.framework.jnode3d.internal.ogl;

import java.nio.ByteBuffer;
import java.util.Objects;

public interface NativeGL {
    /**
     * BeginMode
     */
    int
            GL_POINTS = 0x0,
            GL_LINES = 0x1,
            GL_LINE_LOOP = 0x2,
            GL_LINE_STRIP = 0x3,
            GL_TRIANGLES = 0x4,
            GL_TRIANGLE_STRIP = 0x5,
            GL_TRIANGLE_FAN = 0x6,
            GL_QUADS = 0x7,
            GL_QUAD_STRIP = 0x8,
            GL_POLYGON = 0x9;

    /**
     * TextureWrapMode
     */
    int
            GL_CLAMP = 0x2900,
            GL_REPEAT = 0x2901,
            GL_MIRRORED_REPEAT = 0x8370,
            GL_CLAMP_TO_EDGE = 0x812F,
            GL_CLAMP_TO_BORDER = 0x812D;

    /**
     * MatrixMode
     */
    int
            GL_MODELVIEW = 0x1700,
            GL_PROJECTION = 0x1701,
            GL_TEXTURE = 0x1702;

    /**
     * TextureMagFilter
     */
    int
            GL_NEAREST = 0x2600,
            GL_LINEAR = 0x2601;

    /**
     * TextureMinFilter
     */
    int
            GL_NEAREST_MIPMAP_NEAREST = 0x2700,
            GL_LINEAR_MIPMAP_NEAREST = 0x2701,
            GL_NEAREST_MIPMAP_LINEAR = 0x2702,
            GL_LINEAR_MIPMAP_LINEAR = 0x2703;

    /**
     * Accepted by the {@code texture} parameter of ActiveTexture and MultiTexCoord.
     */
    int
            GL_TEXTURE0 = 0x84C0,
            GL_TEXTURE1 = 0x84C1,
            GL_TEXTURE2 = 0x84C2,
            GL_TEXTURE3 = 0x84C3,
            GL_TEXTURE4 = 0x84C4,
            GL_TEXTURE5 = 0x84C5,
            GL_TEXTURE6 = 0x84C6,
            GL_TEXTURE7 = 0x84C7,
            GL_TEXTURE8 = 0x84C8,
            GL_TEXTURE9 = 0x84C9,
            GL_TEXTURE10 = 0x84CA,
            GL_TEXTURE11 = 0x84CB,
            GL_TEXTURE12 = 0x84CC,
            GL_TEXTURE13 = 0x84CD,
            GL_TEXTURE14 = 0x84CE,
            GL_TEXTURE15 = 0x84CF,
            GL_TEXTURE16 = 0x84D0,
            GL_TEXTURE17 = 0x84D1,
            GL_TEXTURE18 = 0x84D2,
            GL_TEXTURE19 = 0x84D3,
            GL_TEXTURE20 = 0x84D4,
            GL_TEXTURE21 = 0x84D5,
            GL_TEXTURE22 = 0x84D6,
            GL_TEXTURE23 = 0x84D7,
            GL_TEXTURE24 = 0x84D8,
            GL_TEXTURE25 = 0x84D9,
            GL_TEXTURE26 = 0x84DA,
            GL_TEXTURE27 = 0x84DB,
            GL_TEXTURE28 = 0x84DC,
            GL_TEXTURE29 = 0x84DD,
            GL_TEXTURE30 = 0x84DE,
            GL_TEXTURE31 = 0x84DF;

    int
            GL_VERTEX_SHADER = 0x8B31,
            GL_FRAGMENT_SHADER = 0x8B30;

    /** Accepted by {@code stages} parameter to UseProgramStages. */
    int
            GL_VERTEX_SHADER_BIT          = 0x1,
            GL_FRAGMENT_SHADER_BIT        = 0x2,
            GL_GEOMETRY_SHADER_BIT        = 0x4,
            GL_TESS_CONTROL_SHADER_BIT    = 0x8,
            GL_TESS_EVALUATION_SHADER_BIT = 0x10,
            GL_ALL_SHADER_BITS            = 0xFFFFFFFF;

    //<editor-fold desc="Clear">

    void glClear(float r, float g, float b, float a, int mask);

    //</editor-fold>

    //<editor-fold desc="Rendering">
    void glVertex(float x, float y, float z);

    void glVertex(float x, float y);

    void glColor(float r, float g, float b, float a);

    void glTexCoord(float x, float y);

    void glNormal(float x, float y, float z);

    void glDraw(int mode, DrawingCallback drawingCallback);
    //</editor-fold>

    //<editor-fold desc="Textures">
    void glTextureWrap(int sWrap, int tWrap);

    void glTextureBorder(float r, float g, float b, float a);

    void glTextureMinMagFilter(int minFilter, int magFilter);

    int glLoadTexture(ByteBuffer buffer, int width, int height, int textureStack);

    void glBindTexture(int textureIdentifier, int textureStack);

    void glDeleteTexture(int textureIdentifier);
    //</editor-fold>

    //<editor-fold desc="Matrix">
    void glMatrixMode(int mode);

    void glLoadMatrix(float[] matrix);

    void glLoadIdentity();

    void glOrtho(float left, float top, float right, float bottom, float near, float far);

    void glFrustum(float left, float top, float right, float bottom, float near, float far);
    //</editor-fold>

    //<editor-fold desc="Shaders">
    int glCreateShader(int shaderType, String... script);
    void glDeleteShader(int shaderIdentifier);
    String glShaderLog(int shaderIdentifier);

    int glCreateProgram(int... shaderIdentifiers);
    void glDeleteProgram(int programIdentifier);
    String glProgramLog(int programIdentifier);

    void glUseProgram(int programIdentifier);

    void glSetProgramVar(int programIdentifier, String varName, boolean value);
    void glSetProgramVar(int programIdentifier, String varName, float value);
    void glSetProgramVar(int programIdentifier, String varName, int value);
    //</editor-fold>

    final class ShaderProgramReference {
        private final int identifier;
        private final int stages;

        public ShaderProgramReference(int identifier, int stages) {
            this.identifier = identifier;
            this.stages = stages;
        }

        public int getIdentifier() {
            return identifier;
        }

        public int getStages() {
            return stages;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ShaderProgramReference that = (ShaderProgramReference) o;
            return identifier == that.identifier;
        }

        @Override
        public int hashCode() {
            return Objects.hash(identifier);
        }

        @Override
        public String toString() {
            return "ShaderProgramReference{" +
                    "identifier=" + identifier +
                    ", stages=" + stages +
                    '}';
        }
    }
}
