package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.JNode3DScene;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class Node {
    private final UUID uuid = UUID.randomUUID();

    private boolean disposed = false;
    private JNode3DScene scene = null;

    private final List<ChangedListener<Node>> sceneChangedListenerList = new ArrayList<>();

    public JNode3DScene getScene() {
        return scene;
    }

    public void setScene(JNode3DScene scene) {
        this.scene = scene;
        fireSceneChanged();
    }

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

    //<editor-fold desc="Listeners">
    public void addSceneChangedListener(ChangedListener<Node> listener) {
        sceneChangedListenerList.add(listener);
    }

    public void removeSceneChangedListener(ChangedListener<Node> listener) {
        sceneChangedListenerList.remove(listener);
    }

    protected final void fireSceneChanged() {
        for (final ChangedListener<Node> listener : sceneChangedListenerList) {
            listener.onChanged(this);
        }
    }
    //</editor-fold>

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

    public interface ChangedListener<T extends Node> {
        void onChanged(T node);
    }
}
