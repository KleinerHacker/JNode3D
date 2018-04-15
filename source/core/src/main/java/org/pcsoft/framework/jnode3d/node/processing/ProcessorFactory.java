package org.pcsoft.framework.jnode3d.node.processing;

import org.pcsoft.framework.jnode3d.node.ConstructedObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory to get processors for rendering, transformation etc.
 */
public final class ProcessorFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorFactory.class);

    private static final Map<Class<? extends ConstructedObjectNode>, VertexCalculationProcessor> VERTEX_CALCULATION_PROCESSOR_MAP = new HashMap<>();

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
