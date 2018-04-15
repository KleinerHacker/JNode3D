package org.pcsoft.framework.jnode3d.material;

import org.pcsoft.framework.jnode3d.internal.manager.LightManager;
import org.pcsoft.framework.jnode3d.internal.shader.AmbientLightShader;
import org.pcsoft.framework.jnode3d.internal.shader.DirectionalLightShader;
import org.pcsoft.framework.jnode3d.material.shader.PointLightShader;
import org.pcsoft.framework.jnode3d.material.shader.Shader;
import org.pcsoft.framework.jnode3d.type.light.PointLight;
import org.pcsoft.framework.jnode3d.type.light.PositionableLight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Represent a material with light effect
 */
public abstract class LightingMaterial extends Material {
    private AmbientLightShader ambientLightShader;
    private DirectionalLightShader directionalLightShader;
    private PointLightShader pointLightShader;

    public LightingMaterial() {
        LightManager.getInstance().addAmbientLightChangedListener(new LightManager.LightChangedListener() {
            @Override
            public void onChanged() {
                updateAmbientLight();
            }
        });
        LightManager.getInstance().addDirectionalLightChangedListener(new LightManager.LightChangedListener() {
            @Override
            public void onChanged() {
                updateDirectionalLight();
            }
        });
        LightManager.getInstance().addLightListChangedListener(new LightManager.LightChangedListener() {
            @Override
            public void onChanged() {
                updatePointLight();
            }
        });

        updateAmbientLight();
        updateDirectionalLight();
        updatePointLight();
    }

    private void updateDirectionalLight() {
        directionalLightShader.setColor(LightManager.getInstance().getDirectionalLight().getColor());
        directionalLightShader.setPower(LightManager.getInstance().getDirectionalLight().getPower());
        directionalLightShader.setDirection(LightManager.getInstance().getDirectionalLight().getDirection());
    }

    private void updateAmbientLight() {
        ambientLightShader.setColor(LightManager.getInstance().getAmbientLight().getColor());
        ambientLightShader.setPower(LightManager.getInstance().getAmbientLight().getPower());
    }

    private void updatePointLight() {
        for (final PositionableLight light : LightManager.getInstance().getLights()) {
            if (!(light instanceof PointLight))
                continue;

            pointLightShader.setColor(light.getColor());
            pointLightShader.setPower(light.getPower());
            pointLightShader.setPosition(light.getPosition());
            pointLightShader.setAttitude(((PointLight) light).getAttitude());
            pointLightShader.setExponent(((PointLight) light).getExponent());
            pointLightShader.setTransformationMatrix(light.getTransformationMatrix());
            break;
        }
    }

    @Override
    protected final Collection<Shader> initShaders() {
        ambientLightShader = new AmbientLightShader(this);
        directionalLightShader = new DirectionalLightShader(this);
        pointLightShader = new PointLightShader(this);

        final List<Shader> shaders = new ArrayList<>(super.initShaders());
        shaders.addAll(_initShaders());
        shaders.add(ambientLightShader);
        shaders.add(directionalLightShader);
        shaders.add(pointLightShader);

        return Collections.unmodifiableCollection(shaders);
    }

    protected Collection<Shader> _initShaders() {
        return Collections.emptyList();
    }
}
