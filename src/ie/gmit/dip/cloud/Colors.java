package ie.gmit.dip.cloud;

import ie.gmit.dip.utils.Random;


import java.awt.Color;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;


/**
 * @author Nikonchuk Marina
 *
 * Class to work with background and word colors
 */
public class Colors {
    private static Background bg = Background.COLOR;
    private static final Set<String> customColourScheme = new HashSet<>();

    private Colors() {
    }

    /**
     * Method to get the value of the background color
     * @return - returns the background color
     */
    public static String getBackgroundColour() {
        return bg.colour();
    }


    /**
     * Retrieves the colors of the words displayed on the image.
     * @return List of retrieved word colors.
     */
    public static String[] getColourScheme() { // O(n2)
        if (!customColourScheme.isEmpty()) { 
            return customColourScheme.toArray(new String[0]);
        } else {
            DefaultColours[] values = DefaultColours.values();
            return values[Random.generate(values.length - 1)].list();
        }
    }
}
