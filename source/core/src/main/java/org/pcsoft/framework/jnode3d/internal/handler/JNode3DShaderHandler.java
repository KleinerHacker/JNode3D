package org.pcsoft.framework.jnode3d.internal.handler;

import org.pcsoft.framework.jnode3d.internal.manager.ShaderManager;
import org.pcsoft.framework.jnode3d.ogl.OGL;
import org.pcsoft.framework.jnode3d.shader.Shader;
import org.pcsoft.framework.jnode3d.shader.ShaderInstance;

import java.util.List;

final class JNode3DShaderHandler {
    public static void activateShader(ShaderInstance shaderInstance, OGL ogl) {
        final ShaderManager.ShaderIdentifier shaderIdentifier = ShaderManager.getInstance().getShaderIdentifier(shaderInstance.getShader());

        ogl.glUseProgram(shaderIdentifier.getProgramId());
        setupShaderPropertyVariables(shaderIdentifier.getProgramId(), shaderInstance, ogl);
    }

    private static void setupShaderPropertyVariables(int shaderIdentifier, ShaderInstance shaderInstance, OGL ogl) {
        for (final Shader.PropertyInfo propertyInfo : (List<Shader.PropertyInfo>)shaderInstance.getShader().getPropertyInfoList()) {
            try {
                setupShaderPropertyVariable(shaderInstance, ogl, shaderIdentifier, propertyInfo);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("No access for field " + propertyInfo.getName(), e);
            }
        }
    }

    private static void setupShaderPropertyVariable(ShaderInstance shaderInstance, OGL ogl, int shaderIdentifier, Shader.PropertyInfo propertyInfo) throws IllegalAccessException {
        if (propertyInfo.getType() == int.class || propertyInfo.getType() == Integer.class ||
                propertyInfo.getType() == byte.class || propertyInfo.getType() == Byte.class ||
                propertyInfo.getType() == short.class || propertyInfo.getType() == Short.class ||
                propertyInfo.getType() == long.class || propertyInfo.getType() == Long.class) {
            ogl.glSetProgramVar(shaderIdentifier, propertyInfo.getName(), (int)propertyInfo.getField().get(shaderInstance));
        } else if (propertyInfo.getType() == boolean.class || propertyInfo.getType() == Boolean.class) {
            ogl.glSetProgramVar(shaderIdentifier, propertyInfo.getName(), (boolean)propertyInfo.getField().get(shaderInstance));
        } else if (propertyInfo.getType() == float.class || propertyInfo.getType() == Float.class ||
                propertyInfo.getType() == double.class || propertyInfo.getType() == Double.class) {
            ogl.glSetProgramVar(shaderIdentifier, propertyInfo.getName(), (float)propertyInfo.getField().get(shaderInstance));
        } else
            throw new IllegalStateException("Not supported shader var type: " + propertyInfo.getType().getName());
    }

    private JNode3DShaderHandler() {
    }
}