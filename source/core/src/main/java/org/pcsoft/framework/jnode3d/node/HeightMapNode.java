package org.pcsoft.framework.jnode3d.node;

import org.pcsoft.framework.jnode3d.node.processing.ProcessorFactory;
import org.pcsoft.framework.jnode3d.node.processing.vert_calc.HeightMapVertexCalculationProcessor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public final class HeightMapNode extends ConstructedObjectNode<HeightMapNode.Points> {
    private float[][] heightMap = new float[0][];
    private int heightMapWidth = 0, heightMapHeight = 0;

    static {
        ProcessorFactory.registerVertexCalculationProcessor(HeightMapNode.class, new HeightMapVertexCalculationProcessor());
    }

    public HeightMapNode() {
        super(Points.class, HeightMapNode.class);
        fireValueChangedForAll();
    }

    public HeightMapNode(File image) throws IOException {
        super(Points.class, HeightMapNode.class);
        updateHeightMap(image);
    }

    public HeightMapNode(InputStream image) throws IOException {
        super(Points.class, HeightMapNode.class);
        updateHeightMap(image);
    }

    public HeightMapNode(byte[] image) throws IOException {
        super(Points.class, HeightMapNode.class);
        updateHeightMap(image);
    }

    public void updateHeightMap(File file) throws IOException {
        try (final InputStream in = new FileInputStream(file)) {
            updateHeightMap(in);
        }
    }

    public void updateHeightMap(InputStream in) throws IOException {
        final BufferedImage image = ImageIO.read(in);
        final int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
        if (pixels.length != image.getWidth() * image.getHeight())
            throw new IllegalStateException("Inconsistency image: pixel count != width * height");

        heightMap = new float[image.getHeight()][];
        for (int row = 0; row < image.getHeight(); row++) {
            heightMap[row] = new float[image.getWidth()];

            for (int column = 0; column < image.getWidth(); column++) {
                final int index = row * image.getWidth() + column;
                final int pixel = pixels[index];
                final Color color = new Color(pixel);

                final int gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                final float heightValue = (float) gray / 255f;
                heightMap[row][column] = heightValue;
            }
        }

        this.heightMapWidth = image.getWidth();
        this.heightMapHeight = image.getHeight();

        fireValueChangedForAll();
    }

    public void updateHeightMap(byte[] image) throws IOException {
        try (final InputStream in = new ByteArrayInputStream(image)) {
            updateHeightMap(in);
        }
    }

    /**
     * Only for internal use
     * @return
     */
    public float[][] getHeightMap() {
        return heightMap;
    }

    /**
     * Only for internal use
     * @return
     */
    public int getHeightMapWidth() {
        return heightMapWidth;
    }

    /**
     * Only for internal use
     * @return
     */
    public int getHeightMapHeight() {
        return heightMapHeight;
    }

    /**
     * Points for height map colors. In this case height regions.
     */
    public enum Points {
        /**
         * 0.0-0.1
         */
        Bottom,
        /**
         * 0.1-0.25
         */
        VeryLow,
        /**
         * 0.25-0.4
         */
        Low,
        /**
         * 0.4-0.6
         */
        Medium,
        /**
         * 0.6-0.75
         */
        Height,
        /**
         * 0.75-0.9
         */
        VeryHeight,
        /**
         * 0.9-1.0
         */
        Top
    }
}
