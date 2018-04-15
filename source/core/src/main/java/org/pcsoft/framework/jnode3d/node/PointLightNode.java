package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.type.light.PointLight;

public final class PointLightNode extends LightNode<PointLight> {
    public PointLightNode() {
        super(new PointLight());
    }

    public float getAttitude() {
        return light.getAttitude();
    }

    public void setAttitude(float attitude) {
        light.setAttitude(attitude);
    }

    public float getExponent() {
        return light.getExponent();
    }

    public void setExponent(float exponent) {
        light.setExponent(exponent);
    }
}
