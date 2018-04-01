package org.pcsoft.framework.jnode3d.internal;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.Rectangle;

public abstract class GL {
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

    /**
     * BeginMode
     */
    public static final int
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
     * MatrixMode
     */
    public static final int
            GL_MODELVIEW = 0x1700,
            GL_PROJECTION = 0x1701,
            GL_TEXTURE = 0x1702;

    //<editor-fold desc="Clean Up">
    public abstract void glClearColor(float r, float g, float b, float a);

    public final void glClearColor(Color color) {
        glClearColor(color.getR(), color.getG(), color.getB(), color.getA());
    }

    public abstract void glClear(int mask);
    //</editor-fold>

    //<editor-fold desc="Rendering">
    public abstract void glVertex(float x, float y, float z);

    public final void glVertex(Vector3f vector3D) {
        glVertex(vector3D.x(), vector3D.y(), vector3D.z());
    }

    public abstract void glColor(float r, float g, float b);

    public final void glColor(Color color) {
        glColor(color.getR(), color.getG(), color.getB());
    }

    public abstract void glTexCoord(float x, float y);

    public final void glTexCoord(Vector2f vector2D) {
        glTexCoord(vector2D.x(), vector2D.y());
    }

    public abstract void glNormal(float x, float y, float z);

    public final void glNormal(Vector3f vector3D) {
        glNormal(vector3D.x(), vector3D.y(), vector3D.z());
    }

    public abstract void glBegin(int mode);

    public abstract void glEnd();

    public abstract void glFlush();
    //</editor-fold>

    //<editor-fold desc="Matrix">
    public abstract void glMatrixMode(int mode);

    public abstract void glLoadMatrix(float[] matrix);

    public final void glLoadMatrix(Matrix4f matrix) {
        glLoadMatrix(matrix.get(new float[4 * 4]));
    }

    public abstract void glLoadIdentity();

    public abstract void glOrtho(float left, float top, float right, float bottom, float near, float far);

    public final void glOrtho(Rectangle rectangle, float near, float far) {
        glOrtho(rectangle.getLeft(), rectangle.getTop(), rectangle.getRight(), rectangle.getBottom(), near, far);
    }

    public abstract void glFrustum(float left, float top, float right, float bottom, float near, float far);

    public final void glFrustum(Rectangle rectangle, float near, float far) {
        glFrustum(rectangle.getLeft(), rectangle.getTop(), rectangle.getRight(), rectangle.getBottom(), near, far);
    }
    //</editor-fold>
}
