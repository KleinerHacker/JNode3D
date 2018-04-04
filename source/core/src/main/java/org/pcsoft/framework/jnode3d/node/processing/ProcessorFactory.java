package org.pcsoft.framework.jnode3d.node.processing;

import org.pcsoft.framework.jnode3d.node.ConstructedObjectNode;
import org.pcsoft.framework.jnode3d.node.RenderNode;
import org.pcsoft.framework.jnode3d.node.TransformableNode;
import org.pcsoft.framework.jnode3d.node.Triangle;
import org.pcsoft.framework.jnode3d.node.processing.render.BasicRenderProcessor;
import org.pcsoft.framework.jnode3d.node.processing.transform.BasicTransformProcessor;
import org.pcsoft.framework.jnode3d.node.processing.vert_calc.TriangleVertexCalculationProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory to get processors for rendering, transformation etc.
 */
public final class ProcessorFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorFactory.class);

    private static final Map<Class<? extends RenderNode>, RenderProcessor> RENDER_PROCESSOR_MAP = new HashMap<>();
    private static final Map<Class<? extends TransformableNode>, TransformProcessor> TRANSFORM_PROCESSOR_MAP = new HashMap<>();
    private static final Map<Class<? extends ConstructedObjectNode>, VertexCalculationProcessor> VERTEX_CALCULATION_PROCESSOR_MAP = new HashMap<>();

    private static final RenderProcessor<RenderNode> DEFAULT_RENDER_PROCESSOR = new BasicRenderProcessor();
    private static final TransformProcessor<TransformableNode> DEFAULT_TRANSFORM_PROCESSOR = new BasicTransformProcessor();

    //Register builtin nodes
    static {
        //Vertex Calculation
        registerVertexCalculationProcessor(Triangle.class, new TriangleVertexCalculationProcessor());
    }

    //<editor-fold desc="Renderer">
    public static <T extends RenderNode> void registerRenderProcessor(Class<T> clazz, RenderProcessor<T> renderProcessor) {
        if (RENDER_PROCESSOR_MAP.containsKey(clazz)) {
            LOGGER.warn("Render processor already defined for node class " + clazz.getName() + ", overwrite...");
        }

        RENDER_PROCESSOR_MAP.put(clazz, renderProcessor);
    }

    /**
     * Returns a fit render processor for this node class. If no special render processor is defined, the {@link BasicRenderProcessor} is returned
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends RenderNode> RenderProcessor<T> getRenderProcessor(Class<T> clazz) {
        if (!RENDER_PROCESSOR_MAP.containsKey(clazz))
            return (RenderProcessor<T>) DEFAULT_RENDER_PROCESSOR;

        return RENDER_PROCESSOR_MAP.get(clazz);
    }

    public static boolean hasRenderProcessor(Class<? extends RenderNode> clazz) {
        return RENDER_PROCESSOR_MAP.containsKey(clazz);
    }
    //</editor-fold>

    //<editor-fold desc="Transform">
    public static <T extends TransformableNode> void registerTransformProcessor(Class<T> clazz, TransformProcessor<T> transformProcessor) {
        if (TRANSFORM_PROCESSOR_MAP.containsKey(clazz)) {
            LOGGER.warn("Transform processor already defined for node class " + clazz.getName() + ", overwrite...");
        }

        TRANSFORM_PROCESSOR_MAP.put(clazz, transformProcessor);
    }

    /**
     * Returns a fit transform processor for this node class. If no special transform processor is defined, the {@link BasicTransformProcessor} is returned
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends TransformableNode> TransformProcessor<T> getTransformProcessor(Class<T> clazz) {
        if (!TRANSFORM_PROCESSOR_MAP.containsKey(clazz))
            return (TransformProcessor<T>) DEFAULT_TRANSFORM_PROCESSOR;

        return TRANSFORM_PROCESSOR_MAP.get(clazz);
    }

    public static boolean hasTransformProcessor(Class<? extends TransformableNode> clazz) {
        return TRANSFORM_PROCESSOR_MAP.containsKey(clazz);
    }
    //</editor-fold>

    //<editor-fold desc="Vertex Calculation">
    public static <T extends ConstructedObjectNode> void registerVertexCalculationProcessor(Class<T> clazz, VertexCalculationProcessor<T> vertexCalculationProcessor) {
        if (VERTEX_CALCULATION_PROCESSOR_MAP.containsKey(clazz)) {
            LOGGER.warn("Vertex Calculation processor already defined for node class " + clazz.getName() + ", overwrite...");
        }

        VERTEX_CALCULATION_PROCESSOR_MAP.put(clazz, vertexCalculationProcessor);
    }

    /**
     * Returns a fit vertex calculation processor for this node class. If no special vertex calculation processor is defined, it throws an {@link IllegalArgumentException}
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends ConstructedObjectNode> VertexCalculationProcessor<T> getVertexCalculationProcessor(Class<T> clazz) {
        if (!VERTEX_CALCULATION_PROCESSOR_MAP.containsKey(clazz))
            throw new IllegalArgumentException("Unable to find vertex calculation processor for class " + clazz);

        return VERTEX_CALCULATION_PROCESSOR_MAP.get(clazz);
    }

    public static boolean hasVertexCalculationProcessor(Class<? extends ConstructedObjectNode> clazz) {
        return VERTEX_CALCULATION_PROCESSOR_MAP.containsKey(clazz);
    }
    //</editor-fold>


    private ProcessorFactory() {
    }
}
