package org.pcsoft.framework.jnode3d.node;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.pcsoft.framework.jnode3d.internal.GL;
import org.pcsoft.framework.jnode3d.type.Color;

public abstract class ConstructedObject3D extends Object3D {
    protected Vector3D[] points = new Vector3D[0];
    protected Vector2D[] texCoords = new Vector2D[0];
    protected Color[] colors = new Color[0];
    protected Vector3D[] normals = new Vector3D[0];

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
    }
}
