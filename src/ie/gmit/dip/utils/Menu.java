package ie.gmit.dip.utils;

/**
 * @author Nikonchuk Marina
 */

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import ie.gmit.dip.cloud.ImageGenerator;
import ie.gmit.dip.parsing.FileParser;
import ie.gmit.dip.parsing.Parser;
import ie.gmit.dip.Word;
import ie.gmit.dip.parsing.UrlParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Menu {
    private static final int DEFAULT_MAX_WORDS = 35;
    private static final Scanner console = new Scanner(System.in);

    private Parser parser;
    private ParsingType parsingType;
    private String inputUri;
    private String imageFileName;
    private int maxWords = DEFAULT_MAX_WORDS;

    public Menu(){}

    public static Menu createMenu() {
        Menu menu = new Menu();
        menu.setParsingType();
        menu.setInputUri();
        menu.autoSetImageFileName();
        return menu;
    }

    /**
     * A command-line menu that presents the user with the available options.
     */
    public void display() {

        boolean loop = true;
        do {
            System.out.println("============================================================================");
            System.out.println("| Options:                                                                 |");
            System.out.println("|  1) Set the maximum number of displayed words.                          |");
            System.out.println("|  2) Set the name of the generated word cloud image file.                 |");
            System.out.println("|  3) Generate the word-cloud.                                             |");
            System.out.println("|  4) Quit.                                                                |");
            System.out.println("============================================================================");
            System.out.println("\nSelect an option [1-4]>");
            char option = console.next().toUpperCase().charAt(0);
            switch (option) {
                case '1':
                    this.setMaxWords();
                    break;
                case '2':
                    this.setImageFileName();
                    break;
                case '3':
                    this.generateWordCloud();
                    break;
                case '4':
                    System.out.println("Goodbye...");
                    loop = false;
                    break;
                default:
                    System.out.println("Sorry, incorrect option.");
                    System.out.println("Please, repeat.");
            }

        } while (loop);
    }

    private void setParsingType() {
        String input;

        do {
            System.out.print("Do you want to parse a text file type - (1) or a URL type - (2)? ");
            input = console.next().toUpperCase();
        } while (!input.equals("1") && !input.equals("2"));

        if (input.equals("1")) {
            parsingType = ParsingType.FILE;
            parser = new FileParser();
        } else {
            parsingType = ParsingType.URL;
            parser = new UrlParser();
        }
    }

    private void setInputUri() {
        if (parsingType == ParsingType.FILE) {
            setInputFileName();
        } else {
            setUrl();
        }
    }

    private void setInputFileName() {
        Scanner console = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the path to the input file: ");
            String userInput = console.nextLine().trim();
            if (new File(userInput).isFile()) {
                inputUri = userInput;
                return;
            } else {
                System.out.printf("\"%s\" can not be found. Try again.\n", userInput);
            }
        }
    }

    private void setUrl() {
        while (true) {
            System.out.print("Enter the URL you want to parse: ");
            String url = console.next().trim();

            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }

            try {
                System.out.println("Connecting...");
                new URL(url).openConnection().connect();
            } catch (Exception e) {
                System.out.println("[Error] Unable to connect to " + url + "\nCheck your network connection and make sure the URL entered is valid.");
                continue;
            }

            inputUri = url;
            return;
        }
    }

    /**
     * Automatically sets the file name of the word-cloud image.
     */
    private void autoSetImageFileName() {
        if (parsingType == ParsingType.FILE) {
            // Set the imageFileName the same as the inputFileName.png by default.
            // Remove invalid chars if a path was entered.
            if (inputUri.contains(".")) {
                imageFileName = removeInvalidChars(inputUri.substring(0, inputUri.lastIndexOf('.')) + ".png");
            } else {
                imageFileName = removeInvalidChars(inputUri + ".png");
            }
        } else {
            // Be sure jsoup is available and set the name of the image file at the page title.
            try {
                Document doc = Jsoup.connect(inputUri).get();
                imageFileName = removeInvalidChars(doc.title() + ".png").trim();
            } catch (NoClassDefFoundError | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Ask the user to set the file name of the word-cloud image
     */
    private void setImageFileName() {
        Scanner console = new Scanner(System.in);

        while (true) {
            System.out.printf("Enter a name for the PNG file (current is \"%s\"): ", imageFileName);
            String input = console.nextLine().trim();

            if (!input.isEmpty()) {
                imageFileName = removeInvalidChars(input);

                // Append .png to the entered file name 
                if (imageFileName.contains(".")) {
                    imageFileName = imageFileName.substring(0, imageFileName.lastIndexOf('.')) + ".png";
                } else {
                    imageFileName += ".png";
                }

                return;
            }
        }
    }

    /**
     * The maximum number of words to display.
     */
    private void setMaxWords() {
        int maxWords = this.maxWords;
        boolean invalid = true;

        do {
            try {
                System.out.printf("Enter the maximum number of words to display in the word-cloud (current is %d): ", maxWords);
                maxWords = Math.abs(console.nextInt());
            } catch (InputMismatchException e) {
                System.out.println("[Error] Please enter an integer.");
                console.next();
                continue;
            }
            invalid = false;
        } while (invalid);

        this.maxWords = maxWords;
    }

    private void generateWordCloud() {
        try {
            parseStopWords();
            parser.parse(inputUri);
            Queue<Word> queueOfWords = parser.getWordQueue();
            ImageGenerator ig = new ImageGenerator.Builder().build();
            ig.drawWords(queueOfWords, maxWords);
            ig.write(imageFileName);
            System.out.printf("Done.");
        } catch (IOException e) {
            System.err.println("[Error] IO Error.");
            e.printStackTrace();
        }
    }

    private void parseStopWords() {
        try {
            parser.parseStopWords();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }

    /**
     * Removes any illegal characters in filenames and replaces them with underscores
     */
    private static String removeInvalidChars(String filename) {
        return filename.replaceAll("[\\\\\\\\/:*?\\\"<>|]", "_");
    }

}