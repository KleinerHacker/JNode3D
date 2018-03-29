package org.pcsoft.framework.jnode3d.transformation;

import org.pcsoft.framework.jnode3d.type.Vector3D;

public abstract class Transformation {
    //<editor-fold desc="Rotation">
    public static Rotation createRotation() {
        return createRotation(0f);
    }

    public static Rotation createRotation(float angle) {
        return createRotation(angle, Vector3D.Y);
    }

    public static Rotation createRotation(float angle, Vector3D axis) {
        return createRotation(angle, axis.getX(), axis.getY(), axis.getZ());
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
        return createTranslation(position.getX(), position.getY(), position.getZ());
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
        return createScaling(scale.getX(), scale.getY(), scale.getZ());
    }

    public static Scaling createScaling(float scale) {
        return createScaling(scale, scale, scale);
    }

    public static Scaling createScaling(float x, float y, float z) {
        return new Scaling(x, y, z);
    }
    //</editor-fold>
}
