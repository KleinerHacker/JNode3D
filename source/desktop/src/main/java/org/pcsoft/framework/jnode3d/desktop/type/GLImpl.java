package org.pcsoft.framework.jnode3d.desktop.type;

import org.lwjgl.opengl.GL11;
import org.pcsoft.framework.jnode3d.internal.GL;

public class GLImpl extends GL {
    //<editor-fold desc="Clean Up">
    @Override
    public void glClearColor(float r, float g, float b, float a) {
        GL11.glClearColor(r, g, b, a);
    }

    @Override
    public void glClear(int mask) {
        GL11.glClear(mask);
    }
    //</editor-fold>

    //<editor-fold desc="Rendering">
    @Override
    public void glVertex(float x, float y, float z) {
        GL11.glVertex3f(x, y, z);
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
    public void glBegin(int mode) {
        GL11.glBegin(mode);
    }

    @Override
    public void glEnd() {
        GL11.glEnd();
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

    //</editor-fold>
}
