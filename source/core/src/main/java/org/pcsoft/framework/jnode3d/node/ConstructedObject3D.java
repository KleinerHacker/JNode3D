package org.pcsoft.framework.jnode3d.node;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.internal.GL;
import org.pcsoft.framework.jnode3d.type.Color;

public abstract class ConstructedObject3D extends Object3D {
    protected Vector3f[] points = new Vector3f[0];
    protected Vector2f[] texCoords = new Vector2f[0];
    protected Color[] colors = new Color[0];
    protected Vector3f[] normals = new Vector3f[0];

    protected abstract void recalculate();

    @Override
    public final void render(GL gl) {
        gl.glBegin(GL.GL_TRIANGLES);
        {
            for (int i = 0; i < points.length; i++) {
                gl.glVertex(points[i]);
                gl.glColor(colors[i]);
                gl.glTexCoord(texCoords[i]);
                gl.glNormal(normals[i]);
            }
        }
        gl.glEnd();
        gl.glFlush();
    }
}
