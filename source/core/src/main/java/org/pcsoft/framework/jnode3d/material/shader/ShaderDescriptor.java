package org.pcsoft.framework.jnode3d.material.shader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ShaderDescriptor {
    String vertexResource() default "";
    String fragmentResource() default "";
    
    String vertexMain() default "";
    String fragmentMain() default "";
}
