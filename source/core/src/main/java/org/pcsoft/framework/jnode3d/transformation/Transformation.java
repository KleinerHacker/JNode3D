package org.pcsoft.framework.jnode3d.transformation;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public abstract class Transformation {
    //<editor-fold desc="Rotation">
    public static Rotation createRotation() {
        return createRotation(0f);
    }

    public static Rotation createRotation(float angle) {
        return createRotation(angle, new Vector3D(0, 1, 0));
    }

    public static Rotation createRotation(float angle, Vector3D axis) {
        return createRotation(angle, (float) axis.getX(), (float) axis.getY(), (float) axis.getZ());
    }

    public static Rotation createRotation(float angle, float axisX, float axisY, float axisZ) {
        return new Rotation(angle, axisX, axisY, axisZ);
    }
    //</editor-fold>

    //<editor-fold desc="Translation">
    public static Translation createTranslation() {
        return createTranslation(Vector3D.ZERO);
    }

    public static Translation createTranslation(Vector3D position) {
        return createTranslation((float) position.getX(), (float) position.getY(), (float) position.getZ());
    }

    public static Translation createTranslation(float x, float y, float z) {
        return new Translation(x, y, z);
    }
    //</editor-fold>

    //<editor-fold desc="Scaling">
    public static Scaling createScaling() {
        return createScaling(1f);
    }

    public static Scaling createScaling(Vector3D scale) {
        return createScaling((float) scale.getX(), (float) scale.getY(), (float) scale.getZ());
    }

    public static Scaling createScaling(float scale) {
        return createScaling(scale, scale, scale);
    }

    public static Scaling createScaling(float x, float y, float z) {
        return new Scaling(x, y, z);
    }
    //</editor-fold>
}
