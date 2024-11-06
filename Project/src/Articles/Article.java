package Articles;

import Articles.Words.ArticleWord;
import Util.Files.FileReader;

import java.util.ArrayList;

/**
 * Represents an article within a topic
 * */

public class Article {

    final String filePath;

    public String plainTextContents;
    public ArrayList<String> arrayListContents;

    public ArrayList<String> punctuationRemovedContents;
    public ArrayList<String> stopWordRemovedContents;
    public ArrayList<ArticleWord> uniqueWords;

    public WordManager wordManager;
    public SentimentRanker sentimentRanker;

    public Article(String filePath) throws Exception {

        this.filePath = filePath;

        this.sentimentRanker = new SentimentRanker(this);
        this.wordManager = new WordManager(this);

    }

    /**
     * getName splits the path to get the last element, which is the name of the file
     *
     * @return The name of the file
     */

    public String getName() {

        return this.filePath.split("/")[this.filePath.split("/").length - 1];

    }

    /**
     * Reads the file and stores the contents in plainTextContents and arrayListContents
     * Optionally, converts the contents to lowercase
     *
     * @param lower - Whether to convert the contents to lowercase
     * @throws Exception - If the file cannot be read or if the file does not exist
     */

    public void read(boolean lower) throws Exception {

        // If cached

        if (this.plainTextContents != null) {

            if (lower) {

                this.plainTextContents = this.plainTextContents.toLowerCase();

                this.arrayListContents.replaceAll(String::toLowerCase);

            }

            return;

        }

        this.plainTextContents = new FileReader(this.filePath).readAsString();
        this.arrayListContents = new FileReader(this.filePath).readAsStringList(true); // Gets individual words

        if (lower) {

            this.plainTextContents = this.plainTextContents.toLowerCase();

            // Help from the function reference operator

            this.arrayListContents.replaceAll(String::toLowerCase);

        }

    }

    /**
     * Processes the contents of the article
     * Removes punctuation and stop words
     * Gets the unique words
     *
     * @throws Exception - If the file cannot be read or if the file does not exist
     */

    public void process() throws Exception {

        this.read(true);
        
        this.wordManager.removeStopWords(this.wordManager.removePunctuation(this.arrayListContents));
        this.wordManager.removeStopWords(this.wordManager.removePunctuation(this.arrayListContents));
        this.wordManager.getUniqueWords();

    }
    
}