package ie.gmit.dip.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


/**
 * @author Nikonchuk Marina
 *
 * A class that implements reading data from a URL
 */
public class UrlParser extends Parser {
    /**
     * Method for reading words
     * @param url link to text with words
     * @throws IOException
     */
    @Override
    public void parse(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        String content = doc.select("p, br, h1, h2, h3, h4, h5, h6").text();
        String[] words = content.toLowerCase().split(" ");
        preparationWords(words);
        sortMap();
    }
}
