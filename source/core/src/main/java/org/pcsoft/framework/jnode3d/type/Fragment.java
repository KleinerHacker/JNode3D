package org.pcsoft.framework.jnode3d.type;

public final class Fragment {
    private Vertex[] vertices;
    private int[] indices;

    public Fragment() {
        this.vertices = new Vertex[0];
        this.indices = new int[0];
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }

    public int[] getIndices() {
        return indices;
    }

    public void setIndices(int[] indices) {
        this.indices = indices;
    }
}
