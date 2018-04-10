package org.pcsoft.framework.jnode3d.node;

import java.util.Objects;
import java.util.UUID;

public abstract class Node {
    private final UUID uuid = UUID.randomUUID();
    private boolean disposed = false;

    public final void dispose() {
        _dispose();
        disposed = true;
    }

    public boolean isDisposed() {
        return disposed;
    }

    public void setDisposed(boolean disposed) {
        this.disposed = disposed;
    }

    protected void _dispose() {
        //Empty
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(uuid, node.uuid);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(uuid);
    }
}
