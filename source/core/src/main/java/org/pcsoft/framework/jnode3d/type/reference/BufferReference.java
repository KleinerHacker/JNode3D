package org.pcsoft.framework.jnode3d.type.reference;

import java.util.Objects;

public final class BufferReference {
    private final int vertexIdentifier, indexIdentifier;
    private final int verticesCount, indicesCount;

    public BufferReference(int vertexIdentifier, int indexIdentifier, int verticesCount, int indicesCount) {
        this.vertexIdentifier = vertexIdentifier;
        this.indexIdentifier = indexIdentifier;
        this.verticesCount = verticesCount;
        this.indicesCount = indicesCount;
    }

    public int getVertexIdentifier() {
        return vertexIdentifier;
    }

    public int getIndexIdentifier() {
        return indexIdentifier;
    }

    public int getVerticesCount() {
        return verticesCount;
    }

    public int getIndicesCount() {
        return indicesCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BufferReference that = (BufferReference) o;
        return vertexIdentifier == that.vertexIdentifier &&
                indexIdentifier == that.indexIdentifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexIdentifier, indexIdentifier);
    }

    @Override
    public String toString() {
        return "BufferReference{" +
                "vertexIdentifier=" + vertexIdentifier +
                ", indexIdentifier=" + indexIdentifier +
                ", verticesCount=" + verticesCount +
                ", indicesCount=" + indicesCount +
                '}';
    }
}
