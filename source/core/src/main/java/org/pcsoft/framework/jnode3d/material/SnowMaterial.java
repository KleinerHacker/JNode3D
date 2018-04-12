package org.pcsoft.framework.jnode3d.material;

import org.pcsoft.framework.jnode3d.material.shader.Shader;
import org.pcsoft.framework.jnode3d.material.shader.SnowShader;

import java.util.Collection;
import java.util.Collections;

public final class SnowMaterial extends LightingMaterial {
    private SnowShader snowShader;

    @Override
    protected Collection<Shader> _initShaders() {
        snowShader = new SnowShader(this);
        return Collections.<Shader>singletonList(snowShader);
    }

    public boolean isColored() {
        return snowShader.isColored();
    }

    public void setColored(boolean colored) {
        snowShader.setColored(colored);
    }

    public float getSeed() {
        return snowShader.getSeed();
    }

    public void setSeed(float seed) {
        snowShader.setSeed(seed);
    }
}
