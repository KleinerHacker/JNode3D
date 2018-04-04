package org.pcsoft.framework.jnode3d.desktop.type;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;
import org.pcsoft.framework.jnode3d.internal.NGL;
import org.pcsoft.framework.jnode3d.ogl.DrawingCallback;

import java.nio.ByteBuffer;

public class NGLImpl implements NGL {
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
    public void glColor(float r, float g, float b) {
        GL11.glColor3f(r, g, b);
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
        //GL13.glActiveTexture(textureStack);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, identifier);

        GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

        //GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

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
        GL13.glActiveTexture(textureStack);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureIdentifier);
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
}
