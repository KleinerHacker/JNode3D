package org.pcsoft.framework.jnode3d.node;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.pcsoft.framework.jnode3d.internal.manager.LightManager;
import org.pcsoft.framework.jnode3d.type.Color;
import org.pcsoft.framework.jnode3d.type.light.PositionableLight;

public abstract class LightNode<T extends PositionableLight> extends TransformableNode {
    protected final T light;

    public LightNode(T light) {
        this.light = light;
        LightManager.getInstance().registerLight(light);
    }

    public final Color getColor() {
        return light.getColor();
    }

    public final void setColor(Color color) {
        light.setColor(color);
    }

    public final float getPower() {
        return light.getPower();
    }

    public final void setPower(float value) {
        light.setPower(value);
    }

    public final Vector3f getPosition() {
        return light.getPosition();
    }

    public final void setPosition(Vector3f position) {
        light.setPosition(position);
    }

    public Matrix4f getTransformationMatrix() {
        return light.getTransformationMatrix();
    }

    public void setTransformationMatrix(Matrix4f transformationMatrix) {
        light.setTransformationMatrix(transformationMatrix);
    }

    @Override
    protected void _dispose() {
        super._dispose();
        LightManager.getInstance().unregisterLight(light);
    }
}
