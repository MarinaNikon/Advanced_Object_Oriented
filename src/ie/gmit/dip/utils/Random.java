package ie.gmit.dip.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Nikonchuk Marina
 *
 * This class generates random numbers that will be used when choosing word cloud colors.
 */
public final class Random {
    private Random() {
    }

    /**
     * Creates a random number from the specified minimum to maximum.
     * @param min - minimum
     * @param max - maximum
     * @return - returns the found random number
     */
    public static int generate(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Creates a random number between 0 and the specified maximum
     * @param max - maximum
     * @return returns the found random number
     */
    public static int generate(int max) {
        return generate(0, max);
    }

    /**
     * Creates a random number from 0 to the length of an array of numbers
     * @param arr - array of numbers to generate
     * @return random number
     */
    public static <T> int generate(T[] arr) {
        return generate(0, arr.length - 1);
    }
}
