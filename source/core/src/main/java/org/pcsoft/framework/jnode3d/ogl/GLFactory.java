package org.pcsoft.framework.jnode3d.ogl;

public final class GLFactory {
    private static NativeGL nativeGLImplementation;
    private static StateListener stateListener;

    public static StateListener getStateListener() {
        return stateListener;
    }

    public static void setStateListener(StateListener stateListener) {
        GLFactory.stateListener = stateListener;
    }

    public static void initialize(NativeGL impl) {
        nativeGLImplementation = impl;
        if (stateListener != null) {
            stateListener.onInitialized();
        }
    }

    public static void terminate() {
        if (stateListener != null) {
            stateListener.onTerminating();
        }
        nativeGLImplementation = null;
    }

    public static boolean isInitialized() {
        return nativeGLImplementation != null;
    }

    public static OpenGL getOpenGL() {
        if (!isInitialized())
            throw new IllegalStateException("Factory is not initialized yet");

        return new OpenGL(nativeGLImplementation);
    }

    private GLFactory() {
    }

    public interface StateListener {
        void onInitialized();
        void onTerminating();
    }
}
