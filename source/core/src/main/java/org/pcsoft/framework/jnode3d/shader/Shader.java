package org.pcsoft.framework.jnode3d.shader;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.pcsoft.framework.jnode3d.internal.manager.ShaderManager;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Shader<T extends ShaderInstance> {
    protected static String loadShader(InputStream in) {
        try {
            return IOUtils.toString(in);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load shader", e);
        }
    }

    private final String vertexShader, fragmentShader;
    private final List<PropertyInfo> propertyInfoList = new ArrayList<>();

    protected Shader(Class<T> instanceClass, String vertexShader, String fragmentShader) {
        buildPropertyInfoList(instanceClass);

        this.vertexShader = vertexShader;
        this.fragmentShader = fragmentShader;

        ShaderManager.getInstance().registerShader(this);
    }

    public String getVertexShader() {
        return vertexShader;
    }

    public String getFragmentShader() {
        return fragmentShader;
    }

    public List<PropertyInfo> getPropertyInfoList() {
        return propertyInfoList;
    }

    public abstract T buildInstance();

    private void buildPropertyInfoList(Class<T> instanceClass) {
        Class current = instanceClass;
        while (current != null) {
            for (final Field field : current.getDeclaredFields()) {
                final ShaderProperty shaderProperty = field.getAnnotation(ShaderProperty.class);
                if (shaderProperty == null)
                    continue;

                final PropertyInfo propertyInfo = new PropertyInfo(
                        StringUtils.isEmpty(shaderProperty.name()) ? field.getName() : shaderProperty.name(),
                        field.getType(), field
                );
                propertyInfoList.add(propertyInfo);
            }

            current = current.getSuperclass();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shader<?> shader = (Shader<?>) o;
        return Objects.equals(vertexShader, shader.vertexShader) &&
                Objects.equals(fragmentShader, shader.fragmentShader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexShader, fragmentShader);
    }

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
