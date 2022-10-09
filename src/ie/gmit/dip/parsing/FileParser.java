package ie.gmit.dip.parsing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Nikonchuk Marina
 *
 * A class that extends Parser and implements functionality for reading a file
 */

public class FileParser extends Parser {

    /**
     * @param path - a parameter that sets the path to the file
     */
    @Override
    public void parse(String path)  { // O(n)
        try (BufferedReader inputFile = new BufferedReader(new FileReader(path))) {
            String next;
            while ((next = inputFile.readLine()) != null) {
                String[] words = next.toLowerCase().split(" ");
                preparationWords(words);
            }
            sortMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
