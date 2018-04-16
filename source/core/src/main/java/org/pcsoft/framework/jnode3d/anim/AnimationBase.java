package org.pcsoft.framework.jnode3d.anim;

import org.pcsoft.framework.jnode3d.internal.manager.AnimationManager;

public abstract class AnimationBase {
    private long lastCall = 0;
    private boolean disposed = false;
    private boolean started = false;
    private String debugName;

    public AnimationBase() {
        debugName = getClass().getName();
    }

    /**
     * Returns the debug name for logging
     * @return
     */
    public String getDebugName() {
        return debugName;
    }

    public void setDebugName(String debugName) {
        this.debugName = debugName;
    }

    public final void callLoop() {
        final long nanoTime = System.nanoTime();

        if (this.lastCall == 0) {
            loop(0);
        } else {
            loop(nanoTime - this.lastCall);
        }
        
        this.lastCall = nanoTime;
    }

    protected abstract void loop(long timeDelta);

    public final void start() {
        if (disposed)
            throw new IllegalStateException("Already disposed");
        if (started)
            return;

        AnimationManager.getInstance().registerAnimation(this);
        started = true;
    }

    public  final void stop() {
        if (disposed)
            throw new IllegalStateException("Already disposed");
        if (!started)
            return;

        AnimationManager.getInstance().unregisterAnimation(this);
        started = false;
    }

    public boolean isStarted() {
        return started;
    }

    public boolean isDisposed() {
        return disposed;
    }

    public final void dispose() {
        stop();

        _dispose();
        disposed = true;
    }

    protected void _dispose() {
        //Empty
    }
}
