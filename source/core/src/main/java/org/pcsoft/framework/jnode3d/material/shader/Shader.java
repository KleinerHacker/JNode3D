package org.pcsoft.framework.jnode3d.material.shader;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.pcsoft.framework.jnode3d.internal.manager.ShaderManager;
import org.pcsoft.framework.jnode3d.material.Material;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

public abstract class Shader {
    protected static String loadShader(InputStream in) {
        try {
            return IOUtils.toString(in);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load shader", e);
        }
    }

    private final String vertexShader, fragmentShader;
    private final Map<String, PropertyInfo> propertyInfoMap = new HashMap<>();
    private final ShaderDescriptor descriptor;
    private final Material assignedMaterial;

    protected Shader(Material assignedMaterial) {
        this.assignedMaterial = assignedMaterial;

        this.descriptor = findDescriptor();
        buildPropertyInfoList();

        try {
            this.vertexShader = StringUtils.isEmpty(descriptor.vertexResource()) ? null : IOUtils.toString(getClass().getResourceAsStream(descriptor.vertexResource()));
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load Vertex Shader from resource " + descriptor.vertexResource(), e);
        }
        try {
            this.fragmentShader = StringUtils.isEmpty(descriptor.fragmentResource()) ? null : IOUtils.toString(getClass().getResourceAsStream(descriptor.fragmentResource()));
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load Fragment Shader from resource " + descriptor.fragmentResource(), e);
        }
    }

    public Material getAssignedMaterial() {
        return assignedMaterial;
    }

    public String getVertexShader() {
        return vertexShader;
    }

    public boolean hasVertexShader() {
        return !StringUtils.isEmpty(vertexShader);
    }

    public String getFragmentShader() {
        return fragmentShader;
    }

    public boolean hasFragmentShader() {
        return !StringUtils.isEmpty(fragmentShader);
    }

    public Collection<PropertyInfo> getPropertyInfos() {
        return Collections.unmodifiableCollection(propertyInfoMap.values());
    }

    public ShaderDescriptor getDescriptor() {
        return descriptor;
    }

    //<editor-fold desc="Helper Methods">
    private ShaderDescriptor findDescriptor() {
        Class current = this.getClass();
        while (current != null) {
            final ShaderDescriptor shaderDescriptor = (ShaderDescriptor) current.getAnnotation(ShaderDescriptor.class);
            if (shaderDescriptor != null)
                return shaderDescriptor;

            current = current.getSuperclass();
        }

        throw new IllegalStateException("Unable to find annotation " + ShaderDescriptor.class.getName() + " on shader " + this.getClass().getName());
    }

    private void buildPropertyInfoList() {
        Class current = getClass();
        while (current != null) {
            for (final Field field : current.getDeclaredFields()) {
                final ShaderProperty shaderProperty = field.getAnnotation(ShaderProperty.class);
                if (shaderProperty == null)
                    continue;

                final PropertyInfo propertyInfo = new PropertyInfo(
                        StringUtils.isEmpty(shaderProperty.name()) ? field.getName() : shaderProperty.name(),
                        field.getType(), field
                );
                propertyInfoMap.put(propertyInfo.name, propertyInfo);
            }

            current = current.getSuperclass();
        }
    }
    //</editor-fold>

    protected final void updateUniformValue(String value) {
        if (ShaderManager.getInstance().isInitialized() && assignedMaterial != null) {
            ShaderManager.getInstance().updateUniformValues(assignedMaterial, value);
        }
    }

    //<editor-fold desc="Equals / Hashcode">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shader shader = (Shader) o;
        return Objects.equals(vertexShader, shader.vertexShader) &&
                Objects.equals(fragmentShader, shader.fragmentShader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexShader, fragmentShader);
    }
    //</editor-fold>

    public static final class PropertyInfo {
        private final String name;
        private final Class<?> type;
        private final Field field;

        public PropertyInfo(String name, Class<?> type, Field field) {
            this.name = name;
            this.type = type;
            this.field = field;
            this.field.setAccessible(true);
        }

        public String getName() {
            return name;
        }

        public Class<?> getType() {
            return type;
        }

        public Field getField() {
            return field;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PropertyInfo that = (PropertyInfo) o;
            return Objects.equals(name, that.name) &&
                    Objects.equals(type, that.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, type);
        }

        @Override
        public String toString() {
            return "PropertyInfo{" +
                    "name='" + name + '\'' +
                    ", type=" + type +
                    '}';
        }
    }
}
