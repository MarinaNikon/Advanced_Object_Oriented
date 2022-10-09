package ie.gmit.dip.cloud;

import ie.gmit.dip.Word;
import ie.gmit.dip.utils.Random;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Queue;

/**
 * @author Nikonchuk Marina
 *
 * A class that implements work with drawing words on an image.
 */
public class ImageGenerator {
    private final int imageHeight;
    private final int imageWidth;
    private final double maxFontSize;
    private final double minFontSize;
    private final String outputFormat;
    private final Integer[] fontStyles;
    private final String[] fontFamilies;

    private final BufferedImage image;

    /**
     * Class constructor
     * @param builder - Incoming builder class to initialize
     */
    public ImageGenerator(Builder builder) {
        imageHeight = builder.imageHeight;
        imageWidth = builder.imageWidth;
        maxFontSize = builder.maxFontSize;
        minFontSize = builder.minFontSize;
        outputFormat = builder.outputFormat;
        fontStyles = builder.fontStyles;
        fontFamilies = builder.fontFamilies;

        image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_4BYTE_ABGR);
    }

    /**
     * A method that draws words on an image
     * @param wordQ word queue
     * @param maxWords maximum word number
     */
    public void drawWords(Queue<Word> wordQ, int maxWords) { // O(n2)
        int max = 0;
        int min = 0;

        String[] colorScheme = Colors.getColourScheme();

        Graphics2D graphics = image.createGraphics();

        graphics.setColor(Color.decode(Colors.getBackgroundColour()));
        graphics.fillRect(0, 0, imageWidth, imageHeight);

        int counter = 0;
        Iterator<Word> i = wordQ.iterator();

        while (i.hasNext() && counter < maxWords) { 
            int j = i.next().getFrequency();

            if (j > max) max = j;
            if (j < min) min = j;

            while (!wordQ.isEmpty() && counter < maxWords) {
                Word word = wordQ.poll();

                graphics.setFont(new Font(
                        fontFamilies[Random.generate(fontFamilies)],
                        fontStyles[Random.generate(fontStyles)],
                        scale(word.getFrequency(), min, max)
                ));
                graphics.setColor(Color.decode(colorScheme[Random.generate(colorScheme)]));

                FontMetrics fm = graphics.getFontMetrics();

                int x = Random.generate(imageWidth - fm.stringWidth(word.getWord()));
                int y = Random.generate(fm.getHeight(), imageHeight);

                graphics.drawString(word.getWord(), x, y);
                counter++;
            }
        }

        graphics.dispose();
    }

    /**
     * Method that writes the final file to the specified path
     * @param path
     * @throws IOException
     */
    public void write(String path) throws IOException {
        ImageIO.write(image, outputFormat, new File(path));
    }

    /**
     * Scales a word according to its frequency
     * @param freq - frequency
     * @param min - minimum value
     * @param max - maximum value
     * @return - the resulting word size
     */
    private int scale(double freq, double min, double max) {
        return (int) Math.floor((maxFontSize - minFontSize) * (freq - min) / (max - min) + minFontSize);
    }

    /**
     * The builder class that builds the final image
     */
    public static class Builder {
        private int imageHeight = 600;
        private int imageWidth = 800;
        private double maxFontSize = 100;
        private double minFontSize = 10;
        private String outputFormat = "png";
        private Integer[] fontStyles = {Font.PLAIN, Font.BOLD, Font.ITALIC};
        private String[] fontFamilies = {Font.SERIF, Font.SANS_SERIF};

        public Builder imageHeight(int value) {
            imageHeight = value;
            return this;
        }

        public Builder imageWidth(int value) {
            imageWidth = value;
            return this;
        }

        public Builder maxFontSize(double value) {
            maxFontSize = value;
            return this;
        }

        public Builder minFontSize(double value) {
            minFontSize = value;
            return this;
        }

        public Builder outputFormat(String value) {
            outputFormat = value;
            return this;
        }

        public Builder fontStyles(Integer... value) {
            fontStyles = value;
            return this;
        }

        public Builder fontFamilies(String... value) {
            fontFamilies = value;
            return this;
        }

        public ImageGenerator build() {
            return new ImageGenerator(this);
        }
    }
}
