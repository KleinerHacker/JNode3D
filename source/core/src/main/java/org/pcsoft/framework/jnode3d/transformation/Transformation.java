package org.pcsoft.framework.jnode3d.transformation;

import org.joml.Vector3f;

public abstract class Transformation {
    //<editor-fold desc="Rotation">
    public static Rotation createRotation() {
        return createRotation(0f);
    }

    public static Rotation createRotation(float angle) {
        return createRotation(angle, new Vector3f(0, 1, 0));
    }

    public static Rotation createRotation(float angle, Vector3f axis) {
        return createRotation(angle, axis.x(), axis.y(), axis.z());
    }

    public static Rotation createRotation(float angle, float axisX, float axisY, float axisZ) {
        return new Rotation(angle, axisX, axisY, axisZ);
    }
    //</editor-fold>

    //<editor-fold desc="Translation">
    public static Translation createTranslation() {
        return createTranslation(new Vector3f());
    }

    public static Translation createTranslation(Vector3f position) {
        return createTranslation(position.x(), position.y(), position.z());
    }

    public static Translation createTranslation(float x, float y, float z) {
        return new Translation(x, y, z);
    }
    //</editor-fold>

    //<editor-fold desc="Scaling">
    public static Scaling createScaling() {
        return createScaling(1f);
    }

    public static Scaling createScaling(Vector3f scale) {
        return createScaling(scale.x(), scale.y(), scale.z());
    }

    public static Scaling createScaling(float scale) {
        return createScaling(scale, scale, scale);
    }

    public static Scaling createScaling(float x, float y, float z) {
        return new Scaling(x, y, z);
    }
    //</editor-fold>
}
