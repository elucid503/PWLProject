package Classes.Articles;

import Classes.Word;
import Util.Files.FileReader;

import java.util.ArrayList;

public class Article {

    private final String filePath;

    public String plainTextContents;
    public ArrayList<String> arrayListContents;

    public ArrayList<String> punctuationRemovedContents;
    public ArrayList<String> stopWordRemovedContents;
    public ArrayList<Word> uniqueWords;

    public int uniqueWordCount;

    public ArticleStats stats;
    public WordManager wordManager;

    public Article(String filePath) throws Exception {

        this.filePath = filePath;

        this.stats = new ArticleStats(this);
        this.wordManager = new WordManager();

    }

    public void Read(boolean lower) throws Exception {

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

}
