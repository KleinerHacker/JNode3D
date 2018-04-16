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
    private GroupNode parent = null;
    private String debugName;

    private final List<ChangedListener<Node>> sceneChangedListenerList = new ArrayList<>();

    public Node() {
        debugName = getClass().getName();
    }

    /**
     * Returns the name for this node for checking debug log
     * @return
     */
    public String getDebugName() {
        return debugName;
    }

    public void setDebugName(String debugName) {
        this.debugName = debugName;
    }

    public JNode3DScene getScene() {
        return scene;
    }

    /**
     * For internal use only!
     * @param scene
     */
    public void setScene(JNode3DScene scene) {
        this.scene = scene;
        fireSceneChanged();
    }

    public GroupNode getParent() {
        return parent;
    }

    /**
     * For internal use only!
     * @param parent
     */
    public void setParent(GroupNode parent) {
        this.parent = parent;
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
