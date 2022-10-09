package ie.gmit.dip;


/**
 * @author Nikonchuk Marina
 *
 * A Word class that implements the Comparable interface 
 * and is a class that characterizes a word and its frequency.
 */
public class Word implements Comparable<Word> {
    private final String word;
    private final int frequency;

    /**
     * Class constructor
     * @param word - input word
     * @param frequency - word frequency
     */
    public Word(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    /**
     * Get method for word
     * @return - returns a word
     */
    public String getWord() {
        return word;
    }

    /**
     * Get method for frequency
     * @return - returns frequency
     */
    public int getFrequency() {
        return frequency;
    }

     /**
     * Method that compares the incoming word with the current one in frequency
     * @param word - the word that will be compared with the current one
     * @return - numeric value of word comparison
     */
    @Override
    public int compareTo(Word word) {
        return -Integer.compare(frequency, word.frequency);
    }
}
