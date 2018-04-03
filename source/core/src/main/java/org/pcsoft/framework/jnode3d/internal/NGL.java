package org.pcsoft.framework.jnode3d.internal;

import org.pcsoft.framework.jnode3d.ogl.DrawingCallback;

public interface NGL {
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

    //<editor-fold desc="Clear">

    void glClear(float r, float g, float b, float a, int mask);

    //</editor-fold>

    //<editor-fold desc="Rendering">
    void glVertex(float x, float y, float z);

    void glVertex(float x, float y);

    void glColor(float r, float g, float b);

    void glTexCoord(float x, float y);

    void glNormal(float x, float y, float z);

    void glDraw(int mode, DrawingCallback drawingCallback);
    //</editor-fold>

    //<editor-fold desc="Textures">
    void glTextureWrap(int sWrap, int tWrap);

    void glTextureBorder(float r, float g, float b, float a);

    void glMinMagFilter(int minFilter, int magFilter);
    //</editor-fold>

    //<editor-fold desc="Matrix">
    void glMatrixMode(int mode);

    void glLoadMatrix(float[] matrix);

    void glLoadIdentity();

    void glOrtho(float left, float top, float right, float bottom, float near, float far);

    void glFrustum(float left, float top, float right, float bottom, float near, float far);
    //</editor-fold>
}
