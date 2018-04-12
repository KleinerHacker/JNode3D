package org.pcsoft.framework.jnode3d.material;

import org.pcsoft.framework.jnode3d.internal.manager.ShaderManager;
import org.pcsoft.framework.jnode3d.internal.shader.BaseShader;
import org.pcsoft.framework.jnode3d.material.shader.Shader;
import org.pcsoft.framework.jnode3d.type.CullMode;

import java.util.*;

public abstract class Material {
    private final UUID uuid = UUID.randomUUID();
    private final Collection<Shader> shaders;
    private BaseShader baseShader;

    private boolean disposed = false;

    public Material() {
        shaders = initShaders();
        ShaderManager.getInstance().registerShaderProgram(this);
    }

    public final CullMode getCullMode() {
        return CullMode.fromValue(baseShader.getCullMode());
    }

    public final void setCullMode(CullMode cullMode) {
        baseShader.setCullMode(cullMode.getValue());
    }

    public final float getOpacity() {
        return baseShader.getOpacity();
    }

    public final void setOpacity(float opacity) {
        baseShader.setOpacity(opacity);
    }

    protected Collection<Shader> initShaders() {
        baseShader = new BaseShader(this);
        return Collections.<Shader>unmodifiableCollection(Collections.singletonList(baseShader));
    }

    public final Collection<Shader> getShaders() {
        return shaders;
    }

    /**
     * Update the basic material values from {@link Material}:<br/>
     * <ul>
     * <li>Cull Mode, see {@link #getCullMode()}</li>
     * <li>Opacity, see {@link #getOpacity()}</li>
     * </ul>
     *
     * @param material
     */
    public void updateBaseValues(Material material) {
        setCullMode(material.getCullMode());
        setOpacity(material.getOpacity());
    }

    //<editor-fold desc="Dispose">

    /**
     * Dispose material.<br/>
     * Unregister from managers and make free resources. Please call always if material is not used anymore. <b>Otherwise: Memory Leak</b>
     */
    public final void dispose() {
        if (disposed)
            throw new IllegalStateException("Already disposed");

        ShaderManager.getInstance().unregisterShaderProgram(this);

        _dispose();
        this.disposed = true;
    }

    protected void _dispose() {
        //Empty
    }
    //</editor-fold>

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Objects.equals(uuid, material.uuid);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(uuid);
    }
}
