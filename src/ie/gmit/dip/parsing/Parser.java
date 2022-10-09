package ie.gmit.dip.parsing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import ie.gmit.dip.Word;

/**
 * @author Nikonchuk Marina
 *
 * An abstract class that contains methods and tools for parsing words 
 * and processing them in data structures.
 */
public abstract class Parser {

    public static final String STOPWORDS_FILE = "ignorewords.txt";
    private final Set<String> ignoreSet = new HashSet<>();
    private final Map<String, Integer> map = new HashMap<>();
    private final Queue<Word> wordQueue = new PriorityQueue<>();

    /**
     * The method through which you can get the word queue.
     * @return returns word queue
     */
    public Queue<Word> getWordQueue() {
        return wordQueue;
    }

    /**
     * Abstract method for parsing url
     * @param uri is a link to the text file that we are processing.
     * @throws IOException
     */
    public abstract void parse(String uri) throws IOException;

    /**
     * Read each word from ignorefiles.txt into a set, to get rid of duplicates
     * @throws IOException
     */
    public void parseStopWords() throws IOException { //O(n)
        try (BufferedReader ignoreFile = new BufferedReader(new FileReader(STOPWORDS_FILE))) {
            String next;
            while ((next = ignoreFile.readLine()) != null) {
                if (next.trim().isEmpty())
                    continue;
                ignoreSet.add(next.toLowerCase());
            }
        }
    }



    /**
     * A method that prepares words by deleting punctuation marks for subsequent output from the map
     * @param words - an array of processed words
     */
    protected void preparationWords(String[] words) {
        for (String word : words) {
            word = stripPunctuation(word).trim();
            if (!ignoreSet.contains(word)) {
                if (!word.isEmpty()) {
                    updateMap(word);
                }
            }
        }
    }

    /**
     * Method for sorting words depending on their frequency
     */
    protected void sortMap() {
        for (String key : map.keySet()) {
            wordQueue.offer(new Word(key, map.get(key)));
        }
    }

    /**
     * Method for updating the map. If the incoming words already exist
     * we increase their frequency and add them to the map.
     * @param word Input word
     */
    private void updateMap(String word) {
        if (map.containsKey(word)) {
            int freq = map.get(word);
            map.put(word, ++freq);
        } else {
            map.put(word, 1);
        }
    }

    /**
     * A method in which we clear words from unnecessary punctuation and leave them clean
     * @param word - the word that we are processing
     * @return returns a word without punctuation marks.
     */
    private static String stripPunctuation(String word) {
        return word.replaceAll("(?:(?<!\\S)\\p{Punct}+)|(?:\\p{Punct}+(?!\\S))", "");
    }
}
